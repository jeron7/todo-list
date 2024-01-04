package dev.jeron7.todolist.application.useCases.user;

import dev.jeron7.todolist.application.repositories.UserRepository;
import dev.jeron7.todolist.domain.entities.User;

import java.util.Objects;
import java.util.UUID;

public class UpdateUserById {

    private final UserRepository userRepository;

    public UpdateUserById(UserRepository userRepository) {
        this.userRepository = Objects.requireNonNull(userRepository);
    }

    public Output execute(Input input) {
        UUID id = input.id();
        if (!userRepository.containsById(id))
            throw new RuntimeException("There is no user with this id.");

        User newUser = new User(id, input.fullName(), input.email(), input.password());
        User savedUser = userRepository.updateById(id, newUser);

        return new Output(savedUser.id(), savedUser.fullName(), savedUser.email());
    }

    public record Input(UUID id, String fullName, String email, String password){}

    public record Output(UUID id, String fullName, String email) {}
}
