package dev.jeron7.todolist.infrastructure.api.requests;

import com.fasterxml.jackson.annotation.JsonProperty;
import dev.jeron7.todolist.domain.TaskPriority;
import dev.jeron7.todolist.domain.TaskStatus;

import java.util.UUID;

public record UpdateByIdRequest(
        @JsonProperty(required = true)
        String name,
        @JsonProperty(required = true)
        String description,
        @JsonProperty(required = true)
        TaskStatus status,
        @JsonProperty(required = true)
        TaskPriority priority,
        @JsonProperty(value = "owner_id")
        UUID ownerId
) {
}
