package dev.jeron7.todolist.domain.entities;

import dev.jeron7.todolist.domain.TaskPriority;
import dev.jeron7.todolist.domain.TaskStatus;
import dev.jeron7.todolist.domain.validators.TaskValidator;

import java.time.LocalDateTime;
import java.util.UUID;

public class Task {

    private final TaskValidator validator;

    private final UUID id;
    private String name;
    private String description;
    private final LocalDateTime createdAt;
    private LocalDateTime finishedAt;
    private TaskStatus status;
    private TaskPriority priority;
    private User owner;

    public Task(UUID id, String name, String description, LocalDateTime createdAt, LocalDateTime finishedAt, TaskStatus status, TaskPriority priority, User owner) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.createdAt = createdAt;
        this.finishedAt = finishedAt;
        this.status = status;
        this.priority = priority;
        this.owner = owner;

        this.validator = new TaskValidator(this);
        validator.validate();
    }

    public static Task newTask(String name, String description, User owner) {
        return new Task(UUID.randomUUID(), name, description, LocalDateTime.now(), null, TaskStatus.NOT_STARTED, TaskPriority.LOW, owner);
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        this.validator.validate();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
        this.validator.validate();
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getFinishedAt() {
        return finishedAt;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
        if (this.status == TaskStatus.DONE){
            this.finishedAt = LocalDateTime.now();
        } else if (this.finishedAt != null) {
            this.finishedAt = null;
        }
        this.validator.validate();
    }

    public TaskPriority getPriority() {
        return priority;
    }

    public void setPriority(TaskPriority priority) {
        this.priority = priority;
        this.validator.validate();
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
        this.validator.validate();
    }
}
