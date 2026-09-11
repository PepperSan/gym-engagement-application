package com.gym.engagement.app.service.common;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class UserCredentialsManagerTest {

    private static final String FIRST_NAME = "Andrii";
    private static final String LAST_NAME = "Test";
    private static final String BASE_USERNAME = "Andrii.Test";

    private final UserCredentialsManager credentialsManager = new UserCredentialsManager();

    @Test
    void generateUsername_shouldReturnBaseUsername_whenUsernameDoesNotExist() {
        String actual = credentialsManager.generateUsername(FIRST_NAME, LAST_NAME, username -> false);

        assertThat(actual).isEqualTo(BASE_USERNAME);
    }

    @Test
    void generateUsername_shouldAppendSuffix_whenBaseUsernameExists() {
        String actual = credentialsManager.generateUsername(FIRST_NAME, LAST_NAME,
                username -> username.equals(BASE_USERNAME));

        assertThat(actual).isEqualTo(BASE_USERNAME + "1");
    }

    @Test
    void generateUsername_shouldIncrementSuffix_whenMultipleUsernamesExist() {
        String actual = credentialsManager.generateUsername(FIRST_NAME, LAST_NAME,
                username -> username.equals(BASE_USERNAME)
                        || username.equals(BASE_USERNAME + "1")
                        || username.equals(BASE_USERNAME + "2"));

        assertThat(actual).isEqualTo(BASE_USERNAME + "3");
    }

    @Test
    void generateRandomPassword_shouldReturnStringOfLengthTen() {
        String actual = credentialsManager.generateRandomPassword();

        assertThat(actual).hasSize(10);
    }

    @Test
    void generateRandomPassword_shouldReturnDifferentValues_onEachCall() {
        String first = credentialsManager.generateRandomPassword();
        String second = credentialsManager.generateRandomPassword();

        assertThat(first).isNotEqualTo(second);
    }
}