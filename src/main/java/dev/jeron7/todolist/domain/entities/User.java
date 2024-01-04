package dev.jeron7.todolist.domain.entities;

import java.util.UUID;

public record User(
        UUID id,
        String fullName,
        String email,
        String password
) {
    public User {
        assert id != null  : "id is null";
        assert fullName != null && !fullName.isBlank()  : "full name is blank";
        assert email != null && !email.isBlank() : "email is blank";
        assert password != null && !password.isBlank() : "password is blank";
    }

    public static User create(String fullName, String email, String password) {
        return new User(UUID.randomUUID(), fullName, email, password);
    }
}
