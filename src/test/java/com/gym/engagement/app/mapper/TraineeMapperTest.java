package com.gym.engagement.app.mapper;

import com.gym.engagement.app.domain.Trainee;
import com.gym.engagement.app.dto.TraineeDTO;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

class TraineeMapperTest {

    private static final Long USER_ID = 1L;
    private static final String USERNAME = "Andrii.Test";
    private static final String FIRST_NAME = "Andrii";
    private static final String LAST_NAME = "Test";
    private static final String PASSWORD = "password123";
    private static final String ADDRESS = "123 Main St";
    private static final LocalDate DATE_OF_BIRTH = LocalDate.of(2000, 1, 1);

    private final TraineeMapper mapper = new TraineeMapper();

    @Test
    void toDto_shouldMapAllFields_whenTraineeIsNotNull() {
        Trainee trainee = buildTrainee();

        TraineeDTO actual = mapper.toDto(trainee);

        assertThat(actual.getUserId()).isEqualTo(USER_ID);
        assertThat(actual.getUsername()).isEqualTo(USERNAME);
        assertThat(actual.getFirstName()).isEqualTo(FIRST_NAME);
        assertThat(actual.getLastName()).isEqualTo(LAST_NAME);
        assertThat(actual.getPassword()).isEqualTo(PASSWORD);
        assertThat(actual.isActive()).isTrue();
        assertThat(actual.getAddress()).isEqualTo(ADDRESS);
        assertThat(actual.getDateOfBirth()).isEqualTo(DATE_OF_BIRTH);
    }

    @Test
    void toDto_shouldReturnNull_whenTraineeIsNull() {
        TraineeDTO actual = mapper.toDto(null);

        assertThat(actual).isNull();
    }

    @Test
    void toEntity_shouldMapAllFields_whenDtoIsNotNull() {
        TraineeDTO dto = buildTraineeDto();

        Trainee actual = mapper.toEntity(dto);

        assertThat(actual.getUserId()).isEqualTo(USER_ID);
        assertThat(actual.getUsername()).isEqualTo(USERNAME);
        assertThat(actual.getFirstName()).isEqualTo(FIRST_NAME);
        assertThat(actual.getLastName()).isEqualTo(LAST_NAME);
        assertThat(actual.getPassword()).isEqualTo(PASSWORD);
        assertThat(actual.isActive()).isTrue();
        assertThat(actual.getAddress()).isEqualTo(ADDRESS);
        assertThat(actual.getDateOfBirth()).isEqualTo(DATE_OF_BIRTH);
    }

    @Test
    void toEntity_shouldReturnNull_whenDtoIsNull() {
        Trainee actual = mapper.toEntity(null);

        assertThat(actual).isNull();
    }

    private static Trainee buildTrainee() {
        return Trainee.builder()
                .userId(USER_ID)
                .username(USERNAME)
                .firstName(FIRST_NAME)
                .lastName(LAST_NAME)
                .password(PASSWORD)
                .isActive(true)
                .address(ADDRESS)
                .dateOfBirth(DATE_OF_BIRTH)
                .build();
    }

    private static TraineeDTO buildTraineeDto() {
        return TraineeDTO.builder()
                .userId(USER_ID)
                .username(USERNAME)
                .firstName(FIRST_NAME)
                .lastName(LAST_NAME)
                .password(PASSWORD)
                .isActive(true)
                .address(ADDRESS)
                .dateOfBirth(DATE_OF_BIRTH)
                .build();
    }
}