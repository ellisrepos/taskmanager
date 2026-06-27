package org.ellis.taskmanager.service;

import org.ellis.taskmanager.model.User;
import org.ellis.taskmanager.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    public AuthenticationServiceImpl(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }
    @Override
    public User register(String email, String password) {
        String hashedPassword = passwordEncoder.encode(password);
        User newUser = new User(email, hashedPassword);
        return userRepository.save(newUser);
    }
}
