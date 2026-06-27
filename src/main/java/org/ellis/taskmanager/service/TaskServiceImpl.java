package org.ellis.taskmanager.service;

import lombok.extern.slf4j.Slf4j;
import org.ellis.taskmanager.model.Task;
import org.ellis.taskmanager.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class TaskServiceImpl implements TaskService {
    private final TaskRepository taskRepository;
    public TaskServiceImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public Task createTask(String title, String description) {
        if (title == null || title.isBlank()) {
            throw new IllegalArgumentException("Title is required");
        }
        Task task = new Task(title, description);
        return taskRepository.save(task);
    }

    @Override
    public Optional<Task> getTaskById(Long id) {
        return taskRepository.findById(id);
    }

    @Override
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    @Override
    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }

    @Override
    public Task updateTask(Long id, String title, String description) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Task not found"));
        task.setDescription(description);
        task.setTitle(title);
        return task;
    }
}
