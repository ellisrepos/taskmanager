package org.ellis.taskmanager.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "tasks")
@NoArgsConstructor
public class Task {
    private String title;
    private String description;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private Status status;
    public Task(String title, String description) {
        this.title = title;
        this.description = description;
        this.status = Status.OPEN;
    }
}
