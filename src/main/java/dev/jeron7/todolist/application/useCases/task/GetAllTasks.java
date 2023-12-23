package dev.jeron7.todolist.application.useCases.task;

import dev.jeron7.todolist.application.repositories.TaskRepository;
import dev.jeron7.todolist.domain.entities.Task;

import java.util.Collections;
import java.util.List;

public class GetAllTasks {

    private final TaskRepository taskRepository;

    public GetAllTasks(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public Output execute() {
        var tasks = taskRepository.getAll();

        return new Output(
                tasks.stream()
                        .map(GetTaskById.Output::from)
                        .toList()
        );
    }

    public record Output(List<GetTaskById.Output> tasks) {}
}
