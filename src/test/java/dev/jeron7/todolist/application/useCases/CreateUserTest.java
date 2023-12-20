package dev.jeron7.todolist.application.useCases;

import dev.jeron7.todolist.application.repositories.UserRepository;
import dev.jeron7.todolist.application.useCases.user.CreateUser;
import dev.jeron7.todolist.domain.entities.User;
import dev.jeron7.todolist.infrastructure.repositories.InMemoryUserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.util.stream.Stream;

import static org.assertj.core.api.AssertionsForClassTypes.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class CreateUserTest {

    final UserRepository mockedUserRepository = Mockito.mock(UserRepository.class);
    final CreateUser defaultCreateUser = new CreateUser(mockedUserRepository);

    @Test
    @DisplayName("Should instantiate CreateUser successfully with InMemoryUserRepository")
    void instantiateCreateUserSuccess() {
        UserRepository repository = new InMemoryUserRepository();
        assertThatNoException()
            .isThrownBy(() -> new CreateUser(repository));
    }

    @Test
    @DisplayName("Should fail on instantiation of CreateUser with null")
    void instantiateCreateUserFail() {
        assertThatThrownBy(() -> new CreateUser(null))
            .isInstanceOf(NullPointerException.class);
    }

    @ParameterizedTest
    @DisplayName("Should execute successfully")
    @MethodSource("provideValidExecuteParams")
    void executeSuccess(CreateUser.Input input) {
        User aUser = User.create(input.fullName(), input.email(), input.password());

        try (MockedStatic<User> userMockedStatic = Mockito.mockStatic(User.class)) {
            userMockedStatic.when(() -> User.create(any(), any(), any())).thenReturn(aUser);
            when(mockedUserRepository.containsById(any())).thenReturn(false);
            when(mockedUserRepository.containsByEmail(any())).thenReturn(false);
            when(mockedUserRepository.save(any())).thenReturn(aUser);

            CreateUser.Output output = defaultCreateUser.execute(input);

            assertThat(output.id())
                    .isEqualTo(aUser.id());
        }
    }

    @Nested
    @DisplayName("Should fail when user with id or unique email was already created")
    class ExecuteFail1 {
        final CreateUser.Input input = new CreateUser.Input("fullname", "email", "pass");

        @Test
        void executeFail1() {
            when(mockedUserRepository.containsById(any())).thenReturn(true);
            assertThatThrownBy(() -> defaultCreateUser.execute(input))
                    .isInstanceOf(RuntimeException.class)
                    .hasMessage("A user with this id or email already saved.");
        }

        @Test
        void executeFail2() {
            when(mockedUserRepository.containsByEmail(any())).thenReturn(true);
            assertThatThrownBy(() -> defaultCreateUser.execute(input))
                    .isInstanceOf(RuntimeException.class)
                    .hasMessage("A user with this id or email already saved.");
        }
    }

    private static Stream<Arguments> provideValidExecuteParams() {
        return Stream.of(
            Arguments.of(new CreateUser.Input("fullname1", "email1", "pass1")),
            Arguments.of(new CreateUser.Input("fullname2", "email2", "pass2"))
        );
    }
}