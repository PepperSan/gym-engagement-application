package com.gym.engagement.app.domain;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
public class Trainer extends User {

    private final Long userId;
    private final TrainingType specialization;
}