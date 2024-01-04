package dev.jeron7.todolist.infrastructure.api.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import dev.jeron7.todolist.application.useCases.task.GetTaskById;

import java.time.LocalDateTime;
import java.util.UUID;

public record GetTaskByIdResponse(
        UUID id,
        String name,
        String description,
        String status,
        String priority,
        @JsonProperty("created_at")
        LocalDateTime createdAt,
        @JsonProperty("finished_at")
        LocalDateTime finishedAt,
        GetUserByIdResponse user
) {
    public static GetTaskByIdResponse from(GetTaskById.Output output) {
        var userOutput = output.userOutput();

        GetUserByIdResponse userResponse = null;
        if (userOutput != null)
            userResponse = GetUserByIdResponse.from(userOutput);

        return new GetTaskByIdResponse(
                output.id(),
                output.name(),
                output.description(),
                output.status(),
                output.priority(),
                output.createdAt(),
                output.finishedAt(),
                userResponse
        );
    }
}
