package com.gym.engagement.app.domain;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@Builder
public class Training {

    private final Long id;
    private final Long traineeId;
    private final Long trainerId;
    private final String trainingName;
    private final TrainingType trainingType;
    private final int trainingDuration;
    private final LocalDate trainingDate;
}