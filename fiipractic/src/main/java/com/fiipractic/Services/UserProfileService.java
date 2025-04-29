package com.fiipractic.Services;

import com.fiipractic.DTO.UserProfileDTO;
import com.fiipractic.Entity.GenericMapper;
import com.fiipractic.Entity.User;
import com.fiipractic.Entity.UserProfile;
import com.fiipractic.Repository.UserProfileRepository;
import com.fiipractic.Util.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserProfileService {
    private final UserProfileRepository userProfileRepository;
    private final SecurityUtil securityUtil;

    @Autowired
    public UserProfileService(UserProfileRepository userProfileRepository, SecurityUtil securityUtil) {
        this.userProfileRepository = userProfileRepository;
        this.securityUtil = securityUtil;
    }

    @Cacheable("users")
    public Optional<UserProfileDTO> getCurrentUserProfile() throws Exception {
        Long userId = securityUtil.getCurrentUser().getId();
        Optional<UserProfile> profile = userProfileRepository.customFindByUserId(userId);

        if (profile.isEmpty()) {
            throw new Exception("404");
        }

        return profile.map(GenericMapper::toUserProfileDTO);
    }

    @CachePut(value = "users")
    public UserProfileDTO createOrUpdateCurrentUserProfile(UserProfile userProfile) throws Exception {
        User currentUser = securityUtil.getCurrentUser();

        if (userProfile.getEmail() == null || userProfile.getEmailNotification() == null || userProfile.getWeatherApiKey() == null)
            throw new Exception("422");

        Optional<UserProfile> existingWithEmail = userProfileRepository.findByEmail(userProfile.getEmail());
        if (existingWithEmail.isPresent() && !existingWithEmail.get().getUser().getId().equals(currentUser.getId())) {
            throw new Exception("409");
        }

        userProfile.setUser(currentUser);

        Optional<UserProfile> existingProfile = userProfileRepository.customFindByUserId(currentUser.getId());
        if (existingProfile.isPresent()) {
            userProfile.setId(existingProfile.get().getId());
        }

        return GenericMapper.toUserProfileDTO(userProfileRepository.save(userProfile));
    }

    @CachePut(value = "users")
    public UserProfileDTO updateUserProfile(UserProfile userProfile) throws Exception {
        if (userProfile.getEmail() == null || userProfile.getEmailNotification() == null || userProfile.getWeatherApiKey() == null)
            throw new Exception("422");

        if (userProfileRepository.findByEmail(userProfile.getEmail()).isPresent())
            throw new Exception("409");

        if (userProfileRepository.customFindByUserId(userProfile.getUser().getId()).isEmpty())
            throw new Exception("404");

        return GenericMapper.toUserProfileDTO(userProfileRepository.save(userProfile));
    }

    public void deleteCurrentUserProfile() throws Exception {
        User currentUser = securityUtil.getCurrentUser();
        Optional<UserProfile> profile = userProfileRepository.customFindByUserId(currentUser.getId());

        if (profile.isEmpty()) {
            throw new Exception("404");
        }

        UserProfile userProfile = profile.get();

        currentUser.setUserProfile(null);
        userProfile.setUser(null);

        userProfileRepository.delete(userProfile);
    }

    @Cacheable("users")
    public Optional<UserProfile> getUserProfileByUsername(Long id) {
        return userProfileRepository.customFindByUserId(id);
    }

    public Optional<UserProfileDTO> customFindByUserId(Long id) {
        return userProfileRepository.customFindByUserId(id).map(GenericMapper::toUserProfileDTO);
    }
}
