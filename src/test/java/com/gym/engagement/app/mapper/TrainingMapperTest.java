package com.gym.engagement.app.mapper;

import com.gym.engagement.app.domain.Training;
import com.gym.engagement.app.domain.TrainingType;
import com.gym.engagement.app.dto.TrainingDTO;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

class TrainingMapperTest {

    private static final Long TRAINING_ID = 1L;
    private static final Long TRAINEE_ID = 2L;
    private static final Long TRAINER_ID = 3L;
    private static final String TRAINING_NAME = "Cardio Session";
    private static final TrainingType TRAINING_TYPE = new TrainingType("Cardio");
    private static final int DURATION = 60;
    private static final LocalDate TRAINING_DATE = LocalDate.of(2026, 1, 1);

    private final TrainingMapper mapper = new TrainingMapper();

    @Test
    void toDto_shouldMapAllFields_whenTrainingIsNotNull() {
        Training training = buildTraining();

        TrainingDTO actual = mapper.toDto(training);

        assertThat(actual.getId()).isEqualTo(TRAINING_ID);
        assertThat(actual.getTraineeId()).isEqualTo(TRAINEE_ID);
        assertThat(actual.getTrainerId()).isEqualTo(TRAINER_ID);
        assertThat(actual.getTrainingName()).isEqualTo(TRAINING_NAME);
        assertThat(actual.getTrainingType()).isEqualTo(TRAINING_TYPE);
        assertThat(actual.getTrainingDuration()).isEqualTo(DURATION);
        assertThat(actual.getTrainingDate()).isEqualTo(TRAINING_DATE);
    }

    @Test
    void toDto_shouldReturnNull_whenTrainingIsNull() {
        TrainingDTO actual = mapper.toDto(null);

        assertThat(actual).isNull();
    }

    @Test
    void toEntity_shouldMapAllFields_whenDtoIsNotNull() {
        TrainingDTO dto = buildTrainingDto();

        Training actual = mapper.toEntity(dto);

        assertThat(actual.getId()).isEqualTo(TRAINING_ID);
        assertThat(actual.getTraineeId()).isEqualTo(TRAINEE_ID);
        assertThat(actual.getTrainerId()).isEqualTo(TRAINER_ID);
        assertThat(actual.getTrainingName()).isEqualTo(TRAINING_NAME);
        assertThat(actual.getTrainingType()).isEqualTo(TRAINING_TYPE);
        assertThat(actual.getTrainingDuration()).isEqualTo(DURATION);
        assertThat(actual.getTrainingDate()).isEqualTo(TRAINING_DATE);
    }

    @Test
    void toEntity_shouldReturnNull_whenDtoIsNull() {
        Training actual = mapper.toEntity(null);

        assertThat(actual).isNull();
    }

    private static Training buildTraining() {
        return Training.builder()
                .id(TRAINING_ID)
                .traineeId(TRAINEE_ID)
                .trainerId(TRAINER_ID)
                .trainingName(TRAINING_NAME)
                .trainingType(TRAINING_TYPE)
                .trainingDuration(DURATION)
                .trainingDate(TRAINING_DATE)
                .build();
    }

    private static TrainingDTO buildTrainingDto() {
        return TrainingDTO.builder()
                .id(TRAINING_ID)
                .traineeId(TRAINEE_ID)
                .trainerId(TRAINER_ID)
                .trainingName(TRAINING_NAME)
                .trainingType(TRAINING_TYPE)
                .trainingDuration(DURATION)
                .trainingDate(TRAINING_DATE)
                .build();
    }
}