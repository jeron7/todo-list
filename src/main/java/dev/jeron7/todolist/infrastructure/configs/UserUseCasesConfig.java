package dev.jeron7.todolist.infrastructure.configs;

import dev.jeron7.todolist.application.repositories.UserRepository;
import dev.jeron7.todolist.application.useCases.user.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserUseCasesConfig {

    @Bean
    public CreateUser createUser(UserRepository userRepository) {
        return new CreateUser(userRepository);
    }

    @Bean
    public GetUserById getUserById(UserRepository userRepository) {
        return new GetUserById(userRepository);
    }

    @Bean
    public UpdateUserById updateUserById(UserRepository userRepository) {
        return new UpdateUserById(userRepository);
    }

    @Bean
    public DeleteById removeUserById(UserRepository userRepository) {
        return new DeleteById(userRepository);
    }

    @Bean
    public GetAllUsers getAllUsers(UserRepository userRepository) {
        return new GetAllUsers(userRepository);
    }
}
