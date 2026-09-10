package com.gym.engagement.app.mapper;

import com.gym.engagement.app.domain.Trainee;
import com.gym.engagement.app.dto.TraineeDTO;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

class TraineeMapperTest {

    private final TraineeMapper traineeMapper = new TraineeMapper();

    @Test
    void toDto_shouldMapAllFields_whenTraineeIsNotNull() {
        Trainee trainee = buildTrainee();

        TraineeDTO actual = traineeMapper.toDto(trainee);

        assertThat(actual.getUserId()).isEqualTo(1L);
        assertThat(actual.getUsername()).isEqualTo("Andrii.Test");
        assertThat(actual.getFirstName()).isEqualTo("Andrii");
        assertThat(actual.getLastName()).isEqualTo("Test");
        assertThat(actual.getPassword()).isEqualTo("password123");
        assertThat(actual.isActive()).isTrue();
        assertThat(actual.getAddress()).isEqualTo("123 Main St");
        assertThat(actual.getDateOfBirth()).isEqualTo(LocalDate.of(2000, 1, 1));
    }

    @Test
    void toDto_shouldReturnNull_whenTraineeIsNull() {
        TraineeDTO actual = traineeMapper.toDto(null);

        assertThat(actual).isNull();
    }

    @Test
    void toEntity_shouldMapAllFields_whenDtoIsNotNull() {
        TraineeDTO dto = buildTraineeDto();

        Trainee actual = traineeMapper.toEntity(dto);

        assertThat(actual.getUserId()).isEqualTo(1L);
        assertThat(actual.getUsername()).isEqualTo("Andrii.Test");
        assertThat(actual.getFirstName()).isEqualTo("Andrii");
        assertThat(actual.getLastName()).isEqualTo("Test");
        assertThat(actual.getPassword()).isEqualTo("password123");
        assertThat(actual.isActive()).isTrue();
        assertThat(actual.getAddress()).isEqualTo("123 Main St");
        assertThat(actual.getDateOfBirth()).isEqualTo(LocalDate.of(2000, 1, 1));
    }

    @Test
    void toEntity_shouldReturnNull_whenDtoIsNull() {
        Trainee actual = traineeMapper.toEntity(null);

        assertThat(actual).isNull();
    }

    private static Trainee buildTrainee() {
        return Trainee.builder()
                .userId(1L)
                .username("Andrii.Test")
                .firstName("Andrii")
                .lastName("Test")
                .password("password123")
                .isActive(true)
                .address("123 Main St")
                .dateOfBirth(LocalDate.of(2000, 1, 1))
                .build();
    }

    private static TraineeDTO buildTraineeDto() {
        return TraineeDTO.builder()
                .userId(1L)
                .username("Andrii.Test")
                .firstName("Andrii")
                .lastName("Test")
                .password("password123")
                .isActive(true)
                .address("123 Main St")
                .dateOfBirth(LocalDate.of(2000, 1, 1))
                .build();
    }
}