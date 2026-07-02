package org.ellis.taskmanager.controller;

import org.ellis.taskmanager.dto.LoginRequest;
import org.ellis.taskmanager.dto.LoginResponse;
import org.ellis.taskmanager.dto.UserResponse;
import org.ellis.taskmanager.model.User;
import org.ellis.taskmanager.service.AuthenticationService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {
    private final AuthenticationService authenticationService;
    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/register")
    public UserResponse register(@RequestParam String email, @RequestParam String password) {
        User user = authenticationService.register(email, password);
        return new UserResponse(user.getId(), user.getEmail());
    }
    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest loginRequest) {
        String token =  authenticationService.login(loginRequest.email(), loginRequest.password());
        return new LoginResponse(token);
    }

    }
