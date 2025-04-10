package com.fiipractic.Util;

import com.fiipractic.Entity.User;
import com.fiipractic.Services.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Optional;

@Configuration
class StartupConfig {

    @Bean
    CommandLineRunner init(UserService userService, AppUserContext appUserContext) {
        return args -> {
            String defaultUsername = "defaultuser";
            Optional<User> userOptional = userService.findByUsername(defaultUsername);

            User user;
            if (userOptional.isPresent()) {
                user = userOptional.get();
                System.out.println("User already exists. Using existing user.");
            } else {
                user = new User();
                user.setName("DefaultUser");
                user.setUsername(defaultUsername);
                user.setPassword("password");
                user = userService.createUser(user);
                System.out.println("Created new user.");
            }
            appUserContext.setCurrentUser(user);
        };
    }
}

