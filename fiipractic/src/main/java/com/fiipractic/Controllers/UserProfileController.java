package com.fiipractic.Controllers;

import com.fiipractic.DTO.UserProfileDTO;
import com.fiipractic.Entity.User;
import com.fiipractic.Entity.UserProfile;
import com.fiipractic.Services.UserProfileService;
import com.fiipractic.Util.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("profile")
public class UserProfileController {
    private final UserProfileService userProfileService;
    private final SecurityUtil securityUtil;

    @Autowired
    public UserProfileController(UserProfileService userProfileService, SecurityUtil securityUtil) {
        this.userProfileService = userProfileService;
        this.securityUtil = securityUtil;
    }

    @GetMapping
    public ResponseEntity<?> getCurrentUserProfile() {
        try {
            return new ResponseEntity<>(userProfileService.getCurrentUserProfile(), HttpStatus.OK);
        } catch (Exception e) {
            return mapException(e);
        }
    }

    @PostMapping
    public ResponseEntity<?> createOrUpdateUserProfile(@RequestBody UserProfile userProfile) {
        try {
            User currentUser = securityUtil.getCurrentUser();
            Optional<UserProfileDTO> existing = userProfileService.customFindByUserId(currentUser.getId());

            UserProfileDTO savedProfile = userProfileService.createOrUpdateCurrentUserProfile(userProfile);

            HttpStatus status = existing.isPresent() ? HttpStatus.OK : HttpStatus.CREATED;
            return new ResponseEntity<>(savedProfile, status);
        } catch (Exception e) {
            return mapException(e);
        }
    }

    @DeleteMapping
    public ResponseEntity<?> deleteCurrentUserProfile() {
        try {
            userProfileService.deleteCurrentUserProfile();
            return new ResponseEntity<>("", HttpStatus.OK);
        } catch (Exception e) {
            return mapException(e);
        }
    }

    private ResponseEntity<String> mapException(Exception e) {
        return switch (e.getMessage()) {
            case "404" -> new ResponseEntity<>("Not Found", HttpStatus.NOT_FOUND);
            case "409" -> new ResponseEntity<>("Conflict", HttpStatus.CONFLICT);
            case "422" -> new ResponseEntity<>("Unprocessable entity", HttpStatus.UNPROCESSABLE_ENTITY);
            default -> new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        };
    }
}
