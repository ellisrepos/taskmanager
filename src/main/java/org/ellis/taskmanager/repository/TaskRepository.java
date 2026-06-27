package org.ellis.taskmanager.repository;

import org.ellis.taskmanager.model.Task;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface TaskRepository {
    Task save(Task task);
    Optional<Task> findById(Long id);
    List<Task> findAll();
    void deleteById(Long id);
}
