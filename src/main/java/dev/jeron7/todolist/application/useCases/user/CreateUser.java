package dev.jeron7.todolist.application.useCases.user;

import dev.jeron7.todolist.application.repositories.UserRepository;
import dev.jeron7.todolist.domain.entities.User;

import java.util.Objects;
import java.util.UUID;

public class CreateUser {

    private final UserRepository userRepository;

    public CreateUser(UserRepository userRepository) {
        this.userRepository = Objects.requireNonNull(userRepository);
    }

    public Output execute(Input input) {
        User user = User.create(input.fullName(), input.email(), input.password());

        if (userRepository.containsById(user.id()) || userRepository.containsByEmail(user.email()))
            throw new RuntimeException("A user with this id or email already saved.");

        User savedUser = userRepository.save(user);
        return new Output(savedUser.id());
    }

    public record Input(String fullName, String email, String password) {}

    public record Output(UUID id) {}
}
