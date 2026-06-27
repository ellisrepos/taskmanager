package org.ellis.taskmanager.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Task {
    private String title;
    private String description;
    private Long id;
    private Status status;
    public Task(String title, String description) {
        this.title = title;
        this.description = description;
        this.status = Status.OPEN;
    }
}
