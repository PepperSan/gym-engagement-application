package com.gym.engagement.app.mapper;

import com.gym.engagement.app.domain.Trainer;
import com.gym.engagement.app.domain.TrainingType;
import com.gym.engagement.app.dto.TrainerDTO;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class TrainerMapperTest {

    private static final Long USER_ID = 1L;
    private static final String USERNAME = "Andrii.Test";
    private static final String FIRST_NAME = "Andrii";
    private static final String LAST_NAME = "Test";
    private static final String PASSWORD = "password123";
    private static final TrainingType SPECIALIZATION = new TrainingType("Cardio");

    private final TrainerMapper mapper = new TrainerMapper();

    @Test
    void toDto_shouldMapAllFields_whenTrainerIsNotNull() {
        Trainer trainer = buildTrainer();

        TrainerDTO actual = mapper.toDto(trainer);

        assertThat(actual.getUserId()).isEqualTo(USER_ID);
        assertThat(actual.getUsername()).isEqualTo(USERNAME);
        assertThat(actual.getFirstName()).isEqualTo(FIRST_NAME);
        assertThat(actual.getLastName()).isEqualTo(LAST_NAME);
        assertThat(actual.getPassword()).isEqualTo(PASSWORD);
        assertThat(actual.isActive()).isTrue();
        assertThat(actual.getSpecialization()).isEqualTo(SPECIALIZATION);
    }

    @Test
    void toDto_shouldReturnNull_whenTrainerIsNull() {
        TrainerDTO actual = mapper.toDto(null);

        assertThat(actual).isNull();
    }

    @Test
    void toEntity_shouldMapAllFields_whenDtoIsNotNull() {
        TrainerDTO dto = buildTrainerDto();

        Trainer actual = mapper.toEntity(dto);

        assertThat(actual.getUserId()).isEqualTo(USER_ID);
        assertThat(actual.getUsername()).isEqualTo(USERNAME);
        assertThat(actual.getFirstName()).isEqualTo(FIRST_NAME);
        assertThat(actual.getLastName()).isEqualTo(LAST_NAME);
        assertThat(actual.getPassword()).isEqualTo(PASSWORD);
        assertThat(actual.isActive()).isTrue();
        assertThat(actual.getSpecialization()).isEqualTo(SPECIALIZATION);
    }

    @Test
    void toEntity_shouldReturnNull_whenDtoIsNull() {
        Trainer actual = mapper.toEntity(null);

        assertThat(actual).isNull();
    }

    private static Trainer buildTrainer() {
        return Trainer.builder()
                .userId(USER_ID)
                .username(USERNAME)
                .firstName(FIRST_NAME)
                .lastName(LAST_NAME)
                .password(PASSWORD)
                .isActive(true)
                .specialization(SPECIALIZATION)
                .build();
    }

    private static TrainerDTO buildTrainerDto() {
        return TrainerDTO.builder()
                .userId(USER_ID)
                .username(USERNAME)
                .firstName(FIRST_NAME)
                .lastName(LAST_NAME)
                .password(PASSWORD)
                .isActive(true)
                .specialization(SPECIALIZATION)
                .build();
    }
}