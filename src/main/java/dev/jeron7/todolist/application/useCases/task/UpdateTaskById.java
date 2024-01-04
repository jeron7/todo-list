package dev.jeron7.todolist.application.useCases.task;

import dev.jeron7.todolist.application.repositories.TaskRepository;
import dev.jeron7.todolist.application.repositories.UserRepository;
import dev.jeron7.todolist.domain.TaskPriority;
import dev.jeron7.todolist.domain.TaskStatus;
import dev.jeron7.todolist.domain.entities.Task;
import dev.jeron7.todolist.domain.entities.User;

import java.util.Objects;
import java.util.UUID;

public class UpdateTaskById {

    private final TaskRepository taskRepository;
    private final UserRepository userRepository;

    public UpdateTaskById(TaskRepository taskRepository, UserRepository userRepository) {
        this.taskRepository = Objects.requireNonNull(taskRepository);
        this.userRepository = Objects.requireNonNull(userRepository);
    }

    public GetTaskById.Output execute(Input input) {
        UUID id = input.id();
        Task task = taskRepository.getById(id);
        if (task == null)
            throw new RuntimeException("There is no Task with id "+ id);

        task.setName(input.name());
        task.setDescription(input.description());
        task.setPriority(input.priority());
        task.setStatus(input.status());
        setOwner(input.ownerId(), task);

        var savedTask = taskRepository.updateById(id, task);
        return GetTaskById.Output.from(savedTask);
    }

    protected void setOwner(UUID ownerId, Task task) {
        User owner = userRepository.getById(ownerId);
        if (owner != null)
            task.setOwner(owner);
    }

    public record Input(UUID id,
                        String name,
                        String description,
                        TaskStatus status,
                        TaskPriority priority,
                        UUID ownerId) {}
}
