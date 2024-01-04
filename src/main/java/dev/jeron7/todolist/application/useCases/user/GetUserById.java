package dev.jeron7.todolist.application.useCases.user;

import dev.jeron7.todolist.application.repositories.UserRepository;
import dev.jeron7.todolist.domain.entities.User;

import java.util.Objects;
import java.util.UUID;

public class GetUserById {

    private final UserRepository userRepository;

    public GetUserById(UserRepository userRepository) {
        this.userRepository = Objects.requireNonNull(userRepository);
    }

    public Output execute(UUID id) {
        User user = userRepository.getById(id);

        if (user == null)
            throw new RuntimeException("There is no user with this id.");

        return Output.from(user);
    }

    public record Output(UUID id, String fullName, String email) {
        public static Output from(User user) {
            return new Output(user.id(), user.fullName(), user.email());
        }
    }
}
