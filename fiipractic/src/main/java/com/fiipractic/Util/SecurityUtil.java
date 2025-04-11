package com.fiipractic.Util;

import com.fiipractic.Entity.User;
import com.fiipractic.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class SecurityUtil {
    @Autowired
    private UserService userService;

    public User getCurrentUser() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return userService.getUserByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found in DB"));
    }
}
