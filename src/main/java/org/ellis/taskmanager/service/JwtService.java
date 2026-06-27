package org.ellis.taskmanager.service;

import org.ellis.taskmanager.model.User;

public interface JwtService {
    String generateToken(User user);

}
