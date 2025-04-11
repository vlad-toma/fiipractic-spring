package com.fiipractic.Entity;

import com.fiipractic.DTO.RequestHistoryDTO;
import com.fiipractic.DTO.UserProfileDTO;

public class GenericMapper {
    public static UserProfileDTO toUserProfileDTO(UserProfile userProfile) {
        return new UserProfileDTO(
                userProfile.getId(),
                userProfile.getEmail(),
                userProfile.getEmailNotification()
        );
    }

    public static RequestHistoryDTO toRequestHistoryDTO(RequestHistory requestHistory) {
        return new RequestHistoryDTO(
                requestHistory.getId(),
                requestHistory.getLat(),
                requestHistory.getLon(),
                requestHistory.getQ(),
                requestHistory.getAqi(),
                requestHistory.getAlerts(),
                requestHistory.getDays(),
                requestHistory.getResponse(),
                requestHistory.getUser().getUsername()
        );
    }
}
