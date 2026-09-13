package com.gym.engagement.app.storage.initdata;

import lombok.Data;

import java.util.List;

@Data
public class InitialDataDto {
    private List<TraineeInitData> trainees;
    private List<TrainerInitData> trainers;
    private List<TrainingInitData> trainings;
}
