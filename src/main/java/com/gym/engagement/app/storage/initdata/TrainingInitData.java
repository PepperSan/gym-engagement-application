package com.gym.engagement.app.storage.initdata;

import lombok.Data;

import java.time.LocalDate;

@Data
public class TrainingInitData {
    private Long id;
    private Long traineeId;
    private Long trainerId;
    private String trainingName;
    private String trainingType;
    private int trainingDuration;
    private LocalDate trainingDate;
}
