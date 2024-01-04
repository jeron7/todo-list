package dev.jeron7.todolist.application.useCases.task;

import dev.jeron7.todolist.application.repositories.TaskRepository;
import dev.jeron7.todolist.application.repositories.UserRepository;
import dev.jeron7.todolist.domain.entities.Task;
import dev.jeron7.todolist.domain.entities.User;

import java.util.Objects;
import java.util.UUID;

public class CreateTask {

    private final TaskRepository taskRepository;
    private final UserRepository userRepository;

    public CreateTask(TaskRepository taskRepository, UserRepository userRepository) {
        this.taskRepository = Objects.requireNonNull(taskRepository);
        this.userRepository = Objects.requireNonNull(userRepository);
    }

    public Output execute(Input input) {
        User user = null;
        if (input.ownerId() != null) {
            user = userRepository.getById(input.ownerId());

            if (user == null) throw new RuntimeException("There is no user with id ["+ input.ownerId() +"]");
        }

        Task savedTask = taskRepository.save(Task.newTask(input.name(), input.description(), user));

        return new Output(savedTask.getId());
    }

    public record Input(String name, String description, UUID ownerId) {}

    public record Output(UUID id) {}
}
