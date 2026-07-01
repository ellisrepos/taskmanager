package org.ellis.taskmanager.service;

import org.ellis.taskmanager.model.User;

public interface JwtService {
    String generateToken(User user);
    String extractEmail(String token);
    boolean isTokenValid(String token, User user);
}
