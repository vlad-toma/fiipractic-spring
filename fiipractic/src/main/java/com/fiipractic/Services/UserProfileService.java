package com.fiipractic.Services;

import com.fiipractic.DTO.UserProfileDTO;
import com.fiipractic.Entity.GenericMapper;
import com.fiipractic.Entity.UserProfile;
import com.fiipractic.Repository.UserProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserProfileService {
    @Autowired
    private UserProfileRepository userProfileRepository;

    public List<UserProfileDTO> getAllUserProfiles() {
        return userProfileRepository.findAll().stream().map(GenericMapper::toUserProfileDTO).collect(Collectors.toList());
    }

    public Optional<UserProfileDTO> getUserProfileById(Long id) {
        return userProfileRepository.findById(id).map(GenericMapper::toUserProfileDTO);
    }

    public UserProfileDTO createUserProfile(UserProfile userProfile) {
        return GenericMapper.toUserProfileDTO(userProfileRepository.save(userProfile));
    }

    public UserProfileDTO updateUserProfile(UserProfile userProfile) {
        return GenericMapper.toUserProfileDTO(userProfileRepository.save(userProfile));
    }

    public void deleteUserProfile(Long id) {
        userProfileRepository.deleteById(id);
    }

    public Optional<UserProfile> getFullUserProfileById(Long id) {
        return userProfileRepository.findById(id);
    }

    public Optional<UserProfile> getUserProfileByUsername(Long id) {
        return userProfileRepository.customFindByUserId(id);
    }
}
