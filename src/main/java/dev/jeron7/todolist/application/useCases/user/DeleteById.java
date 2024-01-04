package dev.jeron7.todolist.application.useCases.user;

import dev.jeron7.todolist.application.repositories.UserRepository;

import java.util.Objects;
import java.util.UUID;

public class DeleteById {

    private final UserRepository userRepository;

    public DeleteById(UserRepository userRepository) {
        this.userRepository = Objects.requireNonNull(userRepository);
    }

    public void execute(UUID id) {
        if(!userRepository.containsById(id))
            throw new RuntimeException("There is no user with this id.");

        userRepository.removeById(id);
    }
}
