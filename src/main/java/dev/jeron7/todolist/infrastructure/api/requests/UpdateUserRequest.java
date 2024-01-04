package dev.jeron7.todolist.infrastructure.api.requests;

import com.fasterxml.jackson.annotation.JsonProperty;

public record UpdateUserRequest(
        @JsonProperty("full_name")
        String fullName,
        String email,
        String password) {
}
