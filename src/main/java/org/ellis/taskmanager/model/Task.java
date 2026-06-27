package org.ellis.taskmanager.model;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
public class Task {
    private String title;
    private String description;
    private Long id;
    private Status status;
    public Task(String title, String description, Long id) {
        this.title = title;
        this.description = description;
        this.id = id;
        this.status = Status.OPEN;
    }
}
