package dev.jeron7.todolist.infrastructure.repositories;

import dev.jeron7.todolist.application.repositories.TaskRepository;
import dev.jeron7.todolist.domain.entities.Task;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

@Component
public class InMemoryTaskRepository implements TaskRepository {

    private final Map<UUID, Task> tasks;

    public InMemoryTaskRepository(Map<UUID, Task> tasks) {
        this.tasks = Objects.requireNonNull(tasks);
    }

    @Override
    public boolean containsById(UUID id) {
        return tasks.containsKey(id);
    }

    @Override
    public Task save(Task task) {
        UUID id = task.getId();
        this.tasks.put(id, task);
        return this.tasks.get(id);
    }

    @Override
    public Task updateById(UUID id, Task task) {
        // Differently from User, tasks are not immutable and don't give us much work on updating.
        return save(task);
    }

    @Override
    public Task getById(UUID id) {
        return this.tasks.get(id);
    }

    @Override
    public void removeById(UUID id) {
        this.tasks.remove(id);
    }

    @Override
    public Collection<Task> getAll() {
        return this.tasks.values();
    }
}
