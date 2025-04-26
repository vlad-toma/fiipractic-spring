package com.fiipractic.Services;

import com.fiipractic.Entity.User;
import com.fiipractic.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    PasswordEncoder passwordEncoder;

    public void authenticate(String username, String password) throws Exception {
        Optional<User> userOpt = userRepository.findByUsername(username);
        if (userOpt.isPresent()) {
            User user = userOpt.get();
            if  (!passwordEncoder.matches(password, user.getPassword()))
                throw new Exception("401");
        } else {
            throw new Exception("404");
        }
    }
}
