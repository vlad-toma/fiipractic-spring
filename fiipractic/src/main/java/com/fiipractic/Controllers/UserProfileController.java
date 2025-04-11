package com.fiipractic.Controllers;

import com.fiipractic.DTO.UserProfileDTO;
import com.fiipractic.Entity.UserProfile;
import com.fiipractic.Services.UserProfileService;
import com.fiipractic.Util.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("profile")
public class UserProfileController {
    @Autowired
    private UserProfileService userProfileService;

    @Autowired
    private SecurityUtil securityUtil;

    @GetMapping
    public List<UserProfileDTO> getAllUserProfiles() {
        return userProfileService.getAllUserProfiles();
    }

    @GetMapping("/{id}")
    public Optional<UserProfileDTO> getUserProfileById(@PathVariable Long id) {
        return userProfileService.getUserProfileById(id);
    }

    @PostMapping
    public UserProfileDTO createUserProfile(@RequestBody UserProfile userProfile) {
        userProfile.setUser(securityUtil.getCurrentUser());
        return userProfileService.createUserProfile(userProfile);
    }

    @PutMapping
    public UserProfileDTO updateUserProfile(@RequestBody UserProfile userProfile) {
        return userProfileService.updateUserProfile(userProfile);
    }

    @DeleteMapping("/{id}")
    public void deleteUserProfile(@PathVariable Long id) {
        userProfileService.deleteUserProfile(id);
    }
}
