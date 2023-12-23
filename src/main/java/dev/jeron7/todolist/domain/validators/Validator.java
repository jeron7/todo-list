package dev.jeron7.todolist.domain.validators;

public abstract class Validator<T> {

    private final T object;

    public Validator(T object) {
        this.object = object;
    }

    public T getObject() {
        return object;
    }

    abstract void validate();
}
