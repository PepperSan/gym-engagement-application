package com.gym.engagement.app.mapper;

import com.gym.engagement.app.domain.Training;
import com.gym.engagement.app.dto.TrainingDTO;

public final class TrainingMapper {

    private TrainingMapper() {
    }

    public static TrainingDTO toDto(Training training) {
        if (training == null) {
            return null;
        }
        return TrainingDTO.builder()
                .id(training.getId())
                .traineeId(training.getTraineeId())
                .trainerId(training.getTrainerId())
                .trainingName(training.getTrainingName())
                .trainingType(training.getTrainingType())
                .trainingDuration(training.getTrainingDuration())
                .trainingDate(training.getTrainingDate())
                .build();
    }

    public static Training toEntity(TrainingDTO dto) {
        if (dto == null) {
            return null;
        }
        return Training.builder()
                .id(dto.getId())
                .traineeId(dto.getTraineeId())
                .trainerId(dto.getTrainerId())
                .trainingName(dto.getTrainingName())
                .trainingType(dto.getTrainingType())
                .trainingDuration(dto.getTrainingDuration())
                .trainingDate(dto.getTrainingDate())
                .build();
    }
}