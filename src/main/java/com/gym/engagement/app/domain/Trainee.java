package com.gym.engagement.app.domain;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

@Getter
@SuperBuilder
public class Trainee extends User {

    private final Long userId;
    private final String address;
    private final LocalDate dateOfBirth;
}