package org.ellis.taskmanager.controller;

import org.ellis.taskmanager.dto.UserResponse;
import org.ellis.taskmanager.model.User;
import org.ellis.taskmanager.service.AuthenticationService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
    public String login(@RequestParam String email, @RequestParam String password) {
        return authenticationService.login(email, password);
    }

    }
