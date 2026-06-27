package org.ellis.taskmanager.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@Entity
@Table(name = "tasks")
@NoArgsConstructor
public class Task implements Serializable {
    private String title;
    private String description;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private static final Long serialVersionUID = 1L;
    @Enumerated(EnumType.STRING)
    private Status status;
    public Task(String title, String description) {
        this.title = title;
        this.description = description;
        this.status = Status.OPEN;
    }
}
