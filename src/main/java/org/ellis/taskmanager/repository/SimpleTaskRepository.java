package org.ellis.taskmanager.repository;

import org.ellis.taskmanager.model.Task;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class SimpleTaskRepository implements TaskRepository{
    private final List<Task> tasks = new ArrayList<>();
    @Override
    public Task save(Task task) {
        Objects.requireNonNull(task, "Task cannot be null");
        tasks.add(task);
        return task;
    }

    @Override
    public Optional<Task> findById(Long id) {
        return tasks.stream()
                .filter(task -> task.getId().equals(id))
                .findFirst();
    }

    @Override
    public List<Task> findAll() {
        return List.copyOf(tasks);
    }

    @Override
    public void deleteById(Long id) {
        findById(id).ifPresent(tasks::remove);
    }
}
