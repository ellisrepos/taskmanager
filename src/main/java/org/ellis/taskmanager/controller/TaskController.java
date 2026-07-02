package org.ellis.taskmanager.controller;

import org.ellis.taskmanager.dto.TaskRequest;
import org.ellis.taskmanager.dto.TaskResponse;
import org.ellis.taskmanager.mapper.TaskMapper;
import org.ellis.taskmanager.model.Task;
import org.ellis.taskmanager.service.TaskService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {
    private final TaskService taskService;
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }
    @GetMapping("/health")
    public String health() {
        return "api is running";
    }

    @PostMapping
    public TaskResponse createTask(@RequestBody TaskRequest task) {
        Task createdTask = taskService.createTask(task.title(), task.description());
        return TaskMapper.toResponse(createdTask);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskResponse> getTask(@PathVariable Long id) {
        return taskService.getTaskById(id)
                .map(TaskMapper::toResponse)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public List<TaskResponse> getAllTasks() {
        return taskService.getAllTasks().stream()
                .map(TaskMapper::toResponse)
                .toList();
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public TaskResponse updateTask(@PathVariable Long id, @RequestBody TaskRequest task) {
        Task updatedTask = taskService.updateTask(id,task.title(), task.description());
        return TaskMapper.toResponse(updatedTask);
    }
}
