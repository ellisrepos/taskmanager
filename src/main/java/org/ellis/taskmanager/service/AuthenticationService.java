package org.ellis.taskmanager.service;

import org.ellis.taskmanager.model.User;

public interface AuthenticationService {
    User register(String email, String password);
    String login(String email, String password);
}
