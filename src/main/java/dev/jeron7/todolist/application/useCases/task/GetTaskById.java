package dev.jeron7.todolist.application.useCases.task;

import dev.jeron7.todolist.application.repositories.TaskRepository;
import dev.jeron7.todolist.application.useCases.user.GetUserById;
import dev.jeron7.todolist.domain.entities.Task;
import dev.jeron7.todolist.domain.entities.User;

import java.time.LocalDateTime;
import java.util.UUID;

public class GetTaskById {

    private final TaskRepository taskRepository;

    public GetTaskById(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public Output execute(Input input) {
        UUID id = input.id();
        Task task = taskRepository.getById(id);
        if (task == null) {
           throw new RuntimeException("There is no task with id " + id);
        }

        return Output.from(task);
    }

    public record Input(UUID id) {}

    public record Output(
            UUID id,
            String name,
            String description,
            String status,
            String priority,
            LocalDateTime createdAt,
            LocalDateTime finishedAt,
            GetUserById.Output userOutput) {

        public static Output from(Task task) {
            User taskOwner = task.getOwner();

            GetUserById.Output userOutput = null;
            if (taskOwner != null) {
                userOutput = new GetUserById.Output(taskOwner.id(), taskOwner.fullName(), taskOwner.email());
            }

            return new Output(
                    task.getId(),
                    task.getName(),
                    task.getDescription(),
                    task.getStatus().toString(),
                    task.getPriority().toString(),
                    task.getCreatedAt(),
                    task.getFinishedAt(),
                    userOutput
            );
        }

    }
}
