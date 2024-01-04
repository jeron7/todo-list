package dev.jeron7.todolist.infrastructure.repositories;

import dev.jeron7.todolist.application.repositories.UserRepository;
import dev.jeron7.todolist.domain.entities.User;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class InMemoryUserRepository implements UserRepository {

    private final Map<UUID, User> users;
    private final Map<String, User> mappedByEmail;

    public InMemoryUserRepository() {
        this.users = new HashMap<>();
        this.mappedByEmail = new HashMap<>();
    }

    @Override
    public boolean containsById(UUID id) {
        return users.containsKey(id);
    }

    @Override
    public boolean containsByEmail(String email) {
        return mappedByEmail.containsKey(email);
    }

    @Override
    public User save(User user) {
        users.putIfAbsent(user.id(), user);
        mappedByEmail.putIfAbsent(user.email(), user);

        User savedUser = users.get(user.id());

        if (savedUser.equals(mappedByEmail.get(user.email())))
            return savedUser;

        return null;
    }

    @Override
    public User updateById(UUID id, User user) {
        User oldUser = users.get(id);
        String oldEmail = oldUser.email();
        String newEmail = user.email();
        if (!oldEmail.equals(newEmail))
            mappedByEmail.remove(oldEmail);

        users.put(id, user);
        mappedByEmail.put(newEmail, user);

        User updatedUser = users.get(user.id());

        if (updatedUser.equals(mappedByEmail.get(newEmail)))
            return updatedUser;
        return null;
    }

    @Override
    public User getById(UUID id) {
        return users.get(id);
    }

    @Override
    public User getByEmail(String email) {
        return null;
    }

    @Override
    public void removeById(UUID id) {
        User user = users.get(id);
        String email = user.email();

        users.remove(id);
        mappedByEmail.remove(email);
    }

    @Override
    public Collection<User> getAll() {
        return users.values();
    }
}
