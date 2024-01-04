package dev.jeron7.todolist.infrastructure.api.responses;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

public record UpdateUserResponse(
        UUID id,
        @JsonProperty("full_name")
        String fullName,
        String email) {
}
