package dev.jeron7.todolist.application.repositories;

import dev.jeron7.todolist.domain.entities.User;

import java.util.Collection;
import java.util.UUID;

public interface UserRepository extends BaseRepository<UUID, User>{

    boolean containsByEmail(String email);

    User getByEmail(String email);
}
