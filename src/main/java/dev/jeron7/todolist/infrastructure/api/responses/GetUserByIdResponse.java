package dev.jeron7.todolist.infrastructure.api.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import dev.jeron7.todolist.application.useCases.user.GetUserById;

import java.util.UUID;

public record GetUserByIdResponse(
        UUID id,
        @JsonProperty("full_name")
        String fullName,
        String email) {

        public static GetUserByIdResponse from(GetUserById.Output output) {
                return new GetUserByIdResponse(output.id(), output.fullName(), output.email());
        }
}
