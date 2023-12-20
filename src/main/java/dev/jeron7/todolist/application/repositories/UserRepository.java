package dev.jeron7.todolist.application.repositories;

import dev.jeron7.todolist.domain.entities.User;

import java.util.Collection;
import java.util.UUID;

public interface UserRepository {

    boolean containsByEmail(String email);

    boolean containsById(UUID id);

    User save(User user);

    User updateById(UUID id, User user);

    User getById(UUID id);

    User getByEmail(String email);

    void removeById(UUID id);

    Collection<User> getAll();
}
