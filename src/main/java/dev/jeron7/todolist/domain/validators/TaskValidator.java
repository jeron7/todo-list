package dev.jeron7.todolist.domain.validators;

import dev.jeron7.todolist.domain.TaskStatus;
import dev.jeron7.todolist.domain.entities.Task;

import java.util.Objects;

public class TaskValidator extends Validator<Task> {

    public TaskValidator(Task object) {
        super(object);
    }

    @Override
    public void validate() {
        Task task = this.getObject();

        Objects.requireNonNull(task.getId(), "Task id is null");
        Objects.requireNonNull(task.getDescription(), "Task description is null");
        Objects.requireNonNull(task.getCreatedAt(), "Task createdAt is null");
        Objects.requireNonNull(task.getPriority(), "Task priority is null");

        Objects.requireNonNull(task.getStatus(), "Task status is null");
        if (task.getStatus() == TaskStatus.DONE && task.getFinishedAt() == null) {
            throw new NullPointerException("Task finishedAt is null");
        }

        String name = task.getName();
        Objects.requireNonNull(name, "Task name is null");
        if (name.isBlank()) {
            throw new RuntimeException("Task name is blank.");
        }
    }
}
