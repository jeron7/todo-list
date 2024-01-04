package dev.jeron7.todolist.infrastructure.api.requests;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

public record CreateTaskRequest(
        @JsonProperty(required = true)
        String name,
        @JsonProperty(required = true)
        String description,
        @JsonProperty(value = "owner_id")
        UUID ownerId
) {
}
