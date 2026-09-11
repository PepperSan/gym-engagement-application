package com.gym.engagement.app.domain;

import lombok.Getter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Getter
@ToString
@SuperBuilder(toBuilder = true)
public abstract class User {

    private final String username;
    private final String firstName;
    private final String lastName;
    private final String password;
    private final boolean isActive;
}