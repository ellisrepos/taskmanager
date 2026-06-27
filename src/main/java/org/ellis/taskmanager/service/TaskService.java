package org.ellis.taskmanager.service;

import org.ellis.taskmanager.model.Task;

import java.util.List;
import java.util.Optional;

public interface TaskService {
    Task createTask(String title, String description);
    Task updateTask(Long id, String title, String description);
    Optional<Task> getTaskById(Long id);
    List<Task> getAllTasks();
    void deleteTask(Long id);
}
