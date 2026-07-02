package org.ellis.taskmanager.mapper;

import org.ellis.taskmanager.dto.TaskResponse;
import org.ellis.taskmanager.model.Task;

public class TaskMapper {
    public static TaskResponse toResponse(Task task) {
        return new TaskResponse(
            task.getId(),
            task.getTitle(),
            task.getDescription()
        );
    }
}
