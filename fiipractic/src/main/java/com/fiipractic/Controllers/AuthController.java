package com.fiipractic.Controllers;

import com.fiipractic.DTO.LoginRequestDTO;
import com.fiipractic.Services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Base64;
import java.util.Objects;

@RestController
@RequestMapping("auth")
public class AuthController {
    private final AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequestDTO request) {
        try {
            authService.authenticate(request.getUsername(), request.getPassword());
        } catch (Exception e) {
            if (Objects.equals(e.getMessage(), "404"))
                return new ResponseEntity<>("User Not Found", HttpStatus.NOT_FOUND);
            else
                return new ResponseEntity<>("Incorrect password", HttpStatus.UNAUTHORIZED);
        }
        String basicAuth = Base64.getEncoder()
                .encodeToString((request.getUsername() + ":" + request.getPassword()).getBytes());
        return ResponseEntity.ok(basicAuth);
    }
}
