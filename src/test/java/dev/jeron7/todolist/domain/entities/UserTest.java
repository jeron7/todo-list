package dev.jeron7.todolist.domain.entities;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.UUID;
import java.util.stream.Stream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

class UserTest {

    static final UUID DEFAULT_UUID = UUID.fromString("19710951-95a6-41b8-8c7d-28b33e3ceef3");
    static final String DEFAULT_FULL_NAME = "Default Full Name";
    static final String DEFAULT_EMAIL = "default@email.com";
    static final String DEFAULT_PASSWORD = "defaultpass";

    @DisplayName("Should instantiate User successfully")
    @ParameterizedTest
    @MethodSource("provideValidConstructorParams")
    void instantiateSuccess(UUID id, String fullName, String email, String password) {
        User user = new User(id, fullName, email, password);

        assertThat(user.id()).isEqualTo(id);
        assertThat(user.fullName()).isEqualTo(fullName);
        assertThat(user.email()).isEqualTo(email);
        assertThat(user.password()).isEqualTo(password);
    }

    @DisplayName("Should fail while instantiating User")
    @ParameterizedTest
    @MethodSource("provideInvalidConstructorParams")
    void instantiateFail(UUID id, String fullName, String email, String password) {
        assertThatThrownBy(() -> new User(id, fullName, email, password))
                .isExactlyInstanceOf(AssertionError.class);
    }

    @DisplayName("Should create User successfully")
    @ParameterizedTest
    @MethodSource("provideValidCreateParams")
    void createSuccess(String fullName, String email, String password) {
        User user = User.create(fullName, email, password);

        assertThat(user.id()).isNotNull();
        assertThat(user.fullName()).isEqualTo(fullName);
        assertThat(user.email()).isEqualTo(email);
        assertThat(user.password()).isEqualTo(password);
    }

    private static Stream<Arguments> provideValidConstructorParams() {
        return Stream.of(
                Arguments.of(DEFAULT_UUID, DEFAULT_FULL_NAME, DEFAULT_EMAIL, DEFAULT_PASSWORD),
                Arguments.of(UUID.randomUUID(), "New Full Name", "new@email.com", "new-password")
        );
    }

    private static Stream<Arguments> provideValidCreateParams() {
        return Stream.of(
                Arguments.of(DEFAULT_FULL_NAME, DEFAULT_EMAIL, DEFAULT_PASSWORD),
                Arguments.of("New Full Name", "new@email.com", "new-password")
        );
    }

    private static Stream<Arguments> provideInvalidConstructorParams() {
        return Stream.of(
                Arguments.of(null, DEFAULT_FULL_NAME, DEFAULT_EMAIL, DEFAULT_PASSWORD),
                Arguments.of(DEFAULT_UUID, "", DEFAULT_EMAIL, DEFAULT_PASSWORD),
                Arguments.of(DEFAULT_UUID, DEFAULT_FULL_NAME, " ", DEFAULT_PASSWORD),
                Arguments.of(DEFAULT_UUID, DEFAULT_FULL_NAME, DEFAULT_EMAIL, null)
        );
    }
}