package dev.jeron7.todolist.application.repositories;

import java.util.Collection;

public interface BaseRepository<ID, T> {

    boolean containsById(ID id);

    T save(T t);

    T updateById(ID id, T t);

    T getById(ID id);

    void removeById(ID id);

    Collection<T> getAll();
}
