package com.gym.engagement.app.service.common;

import org.springframework.stereotype.Component;

import java.security.SecureRandom;
import java.util.function.Predicate;

@Component
public class UserCredentialsManager {

    private static final String PASSWORD_CHARS =
            "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private static final int PASSWORD_LENGTH = 10;

    private final SecureRandom random = new SecureRandom();

    public String generateUsername(String firstName, String lastName, Predicate<String> usernameExists) {
        String baseUsername = firstName + "." + lastName;

        if (!usernameExists.test(baseUsername)) {
            return baseUsername;
        }

        int suffix = 1;
        String candidateUsername = baseUsername + suffix;
        while (usernameExists.test(candidateUsername)) {
            suffix++;
            candidateUsername = baseUsername + suffix;
        }

        return candidateUsername;
    }

    public String generateRandomPassword() {
        StringBuilder password = new StringBuilder(PASSWORD_LENGTH);
        for (int i = 0; i < PASSWORD_LENGTH; i++) {
            password.append(PASSWORD_CHARS.charAt(random.nextInt(PASSWORD_CHARS.length())));
        }

        return password.toString();
    }
}