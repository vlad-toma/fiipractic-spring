package com.fiipractic.Entity;

import com.fiipractic.DTO.UserProfileDTO;

public class GenericMapper {
    public static UserProfileDTO toUserProfileDTO(UserProfile userProfile) {
        return new UserProfileDTO(
                userProfile.getId(),
                userProfile.getEmail(),
                userProfile.getEmailNotification()
        );
    }
}
