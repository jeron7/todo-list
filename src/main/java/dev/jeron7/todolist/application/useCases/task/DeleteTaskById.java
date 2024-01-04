package dev.jeron7.todolist.application.useCases.task;

import dev.jeron7.todolist.application.repositories.TaskRepository;

import java.util.Objects;
import java.util.UUID;

public class DeleteTaskById {

    private final TaskRepository taskRepository;

    public DeleteTaskById(TaskRepository taskRepository) {
        this.taskRepository = Objects.requireNonNull(taskRepository);
    }

    public void execute(UUID id) {
        if (!taskRepository.containsById(id))
            throw new RuntimeException("There is no such task with id " + id);

        taskRepository.removeById(id);
    }
}
