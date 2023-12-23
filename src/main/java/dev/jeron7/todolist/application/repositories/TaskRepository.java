package dev.jeron7.todolist.application.repositories;

import dev.jeron7.todolist.domain.entities.Task;

import java.util.UUID;

public interface TaskRepository extends BaseRepository<UUID, Task> {}
