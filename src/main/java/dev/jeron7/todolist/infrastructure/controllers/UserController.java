package dev.jeron7.todolist.infrastructure.controllers;

import dev.jeron7.todolist.application.useCases.user.*;
import dev.jeron7.todolist.infrastructure.api.requests.CreateUserRequest;
import dev.jeron7.todolist.infrastructure.api.requests.UpdateUserRequest;
import dev.jeron7.todolist.infrastructure.api.responses.GetUserByIdResponse;
import dev.jeron7.todolist.infrastructure.api.responses.UpdateUserResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

import static org.springframework.web.servlet.support.ServletUriComponentsBuilder.fromCurrentRequestUri;

@RestController
@RequestMapping("users")
public class UserController {

    private final CreateUser createUser;
    private final GetUserById getUserById;
    private final UpdateUserById updateUserById;
    private final DeleteById deleteById;
    private final GetAllUsers getAllUsers;

    public UserController(CreateUser createUser, GetUserById getUserById, UpdateUserById updateUserById, DeleteById deleteById, GetAllUsers getAllUsers) {
        this.createUser = Objects.requireNonNull(createUser);
        this.getUserById = Objects.requireNonNull(getUserById);
        this.updateUserById = Objects.requireNonNull(updateUserById);
        this.deleteById = Objects.requireNonNull(deleteById);
        this.getAllUsers = Objects.requireNonNull(getAllUsers);
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody CreateUserRequest request) {
        CreateUser.Output output = createUser.execute(new CreateUser.Input(request.fullName(), request.email(), request.password()));

        return ResponseEntity.created(fromCurrentRequestUri().path("/{id}").build(output.id())).build();
    }

    @GetMapping
    public ResponseEntity<?> getAll() {
        List<GetUserByIdResponse> users = getAllUsers.execute().stream()
                .map(GetUserByIdResponse::from)
                .toList();

        return ResponseEntity.ok(users);
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getById(@PathVariable UUID id) {
        GetUserById.Output output = getUserById.execute(id);

        return ResponseEntity.ok(GetUserByIdResponse.from(output));
    }

    @PutMapping("{id}")
    public ResponseEntity<?> update(@PathVariable UUID id, @RequestBody UpdateUserRequest request) {
        UpdateUserById.Output output = updateUserById.execute(
                new UpdateUserById.Input(id, request.fullName(), request.email(), request.password())
        );

        return ResponseEntity.ok(new UpdateUserResponse(id, output.fullName(), output.email()));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> delete(@PathVariable UUID id) {
        deleteById.execute(id);

        return ResponseEntity.ok().build();
    }
}
