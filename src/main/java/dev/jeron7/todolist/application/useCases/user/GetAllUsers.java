package dev.jeron7.todolist.application.useCases.user;

import dev.jeron7.todolist.application.repositories.UserRepository;

import java.util.List;
import java.util.Objects;

public class GetAllUsers {

    private final UserRepository userRepository;

    public GetAllUsers(UserRepository userRepository) {
        this.userRepository = Objects.requireNonNull(userRepository);
    }

    public List<GetUserById.Output> execute() {
        return userRepository.getAll().stream()
            .map(GetUserById.Output::from)
            .toList();
    }
}
