package com.gym.engagement.app.dao;

import com.gym.engagement.app.dao.impl.TraineeDaoImpl;
import com.gym.engagement.app.domain.Trainee;
import com.gym.engagement.app.storage.TraineeStorage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

class TraineeDaoImplTest {

    private TraineeStorage traineeStorage;
    private TraineeDao traineeDao;
    private Trainee trainee;

    @BeforeEach
    void setUp() {
        traineeStorage = new TraineeStorage();
        traineeDao = new TraineeDaoImpl(traineeStorage);
        trainee = buildTrainee();
    }

    @Test
    void shouldSaveTrainee() {
        traineeDao.save(trainee);

        Trainee actual = traineeDao.findById(trainee.getUserId());

        assertEquals(trainee, actual);
    }

    @Test
    void shouldFindTraineeById() {
        traineeDao.save(trainee);

        Trainee actual = traineeDao.findById(trainee.getUserId());

        assertNotNull(actual);
        assertEquals("andrii", actual.getUsername());
    }

    @Test
    void shouldUpdateTrainee() {
        traineeDao.save(trainee);
        Trainee updatedTrainee = buildUpdatedTrainee();

        traineeDao.update(updatedTrainee);

        Trainee persisted = traineeDao.findById(updatedTrainee.getUserId());
        assertEquals("Andrii Updated", persisted.getFirstName());
        assertEquals("456 Oak Street", persisted.getAddress());
    }

    @Test
    void shouldDeleteTraineeById() {
        traineeDao.save(trainee);

        traineeDao.deleteById(trainee.getUserId());

        assertNull(traineeDao.findById(trainee.getUserId()));
    }

    private static Trainee buildTrainee() {
        return Trainee.builder()
                .userId(1L)
                .username("andrii")
                .firstName("Andrii")
                .lastName("Testov")
                .password("password")
                .isActive(true)
                .address("123 Main Street")
                .dateOfBirth(LocalDate.of(1990, 1, 1))
                .build();
    }

    private static Trainee buildUpdatedTrainee() {
        return Trainee.builder()
                .userId(1L)
                .username("andrii")
                .firstName("Andrii Updated")
                .lastName("Testov")
                .password("password")
                .isActive(true)
                .address("456 Oak Street")
                .dateOfBirth(LocalDate.of(1990, 1, 1))
                .build();
    }
}