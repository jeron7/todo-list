package dev.jeron7.todolist.application.useCases;

import dev.jeron7.todolist.application.repositories.UserRepository;
import dev.jeron7.todolist.application.useCases.user.GetUserById;
import dev.jeron7.todolist.domain.entities.User;
import dev.jeron7.todolist.infrastructure.repositories.InMemoryUserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

class GetUserByIdTest {

    final UserRepository mockedUserRepository = Mockito.mock(UserRepository.class);
    final GetUserById defaultGetUserById = new GetUserById(mockedUserRepository);

    @Test
    @DisplayName("Should instantiate GetUserById successfully with InMemoryUserRepository")
    void instantiateGetUserByIdSuccess() {
        UserRepository repository = new InMemoryUserRepository();
        assertThatNoException()
                .isThrownBy(() -> new GetUserById(repository));
    }

    @Test
    @DisplayName("Should fail on instantiation of GetUserById with null")
    void instantiateGetUserByIdFail() {
        assertThatThrownBy(() -> new GetUserById(null))
                .isInstanceOf(NullPointerException.class);
    }

    @Test
    @DisplayName("Should execute GetUserById successfully")
    void executeSuccess() {
        UUID aId = UUID.fromString("19710951-95a6-41b8-8c7d-28b33e3ceef3");
        User aUser = new User(aId, "fullname1", "email1", "pass1");

        when(mockedUserRepository.getById(eq(aId))).thenReturn(aUser);

        assertThat(defaultGetUserById.execute(aId))
                .isEqualTo(new GetUserById.Output(aUser.id(), aUser.fullName(), aUser.email()));
    }

    @Test
    @DisplayName("Should fail GetUserById when repository getById returns null")
    void executeFail() {
        UUID aId = UUID.fromString("19710951-95a6-41b8-8c7d-28b33e3ceef3");

        when(mockedUserRepository.getById(eq(aId))).thenReturn(null);

        assertThatThrownBy(() -> defaultGetUserById.execute(aId))
            .isInstanceOf(RuntimeException.class)
            .hasMessage("There is no user with this id.");
    }
}