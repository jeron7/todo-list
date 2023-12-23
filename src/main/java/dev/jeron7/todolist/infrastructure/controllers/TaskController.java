package dev.jeron7.todolist.infrastructure.controllers;

import dev.jeron7.todolist.application.useCases.task.CreateTask;
import dev.jeron7.todolist.application.useCases.task.GetAllTasks;
import dev.jeron7.todolist.application.useCases.task.GetTaskById;
import dev.jeron7.todolist.application.useCases.task.UpdateTaskById;
import dev.jeron7.todolist.infrastructure.api.requests.CreateTaskRequest;
import dev.jeron7.todolist.infrastructure.api.requests.UpdateByIdRequest;
import dev.jeron7.todolist.infrastructure.api.responses.GetTaskByIdResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

import static org.springframework.web.servlet.support.ServletUriComponentsBuilder.fromCurrentRequestUri;

@RestController
@RequestMapping("tasks")
public class TaskController {

    private final CreateTask createTask;
    private final GetTaskById getTaskById;
    private final GetAllTasks getAllTasks;
    private final UpdateTaskById  updateTaskById;

    public TaskController(CreateTask createTask, GetTaskById getTaskById, GetAllTasks getAllTasks, UpdateTaskById updateTaskById) {
        this.createTask = Objects.requireNonNull(createTask);
        this.getTaskById = Objects.requireNonNull(getTaskById);
        this.getAllTasks = Objects.requireNonNull(getAllTasks);
        this.updateTaskById = Objects.requireNonNull(updateTaskById);
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody CreateTaskRequest request) {
        CreateTask.Output output  = createTask.execute(
                new CreateTask.Input(request.name(), request.description(), request.ownerId())
        );

        return ResponseEntity.created(fromCurrentRequestUri().path("/{id}").build(output.id())).build();
    }

    @GetMapping
    public ResponseEntity<?> getAll() {
        List<GetTaskByIdResponse> tasks = getAllTasks.execute().tasks().stream()
                .map(GetTaskByIdResponse::from)
                .toList();

        return ResponseEntity.ok(tasks);
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getById(@PathVariable("id") UUID id) {
        GetTaskById.Output output = getTaskById.execute(new GetTaskById.Input(id));

        return ResponseEntity.ok(GetTaskByIdResponse.from(output));
    }

    @PutMapping("{id}")
    public ResponseEntity<?> updateById(@PathVariable("id") UUID id, @RequestBody UpdateByIdRequest request) {
        var input = new UpdateTaskById.Input(
                id,
                request.name(),
                request.description(),
                request.status(),
                request.priority(),
                request.ownerId());
        GetTaskById.Output output = updateTaskById.execute(input);
        return ResponseEntity.ok(GetTaskByIdResponse.from(output));
    }
}
