package com.gym.engagement.app.dao;

import com.gym.engagement.app.domain.Trainee;
import com.gym.engagement.app.storage.Storage;
import com.gym.engagement.app.storage.TraineeStorage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class TraineeDaoImplTest {

    private Storage storage;
    private TraineeStorage traineeStorage;
    private TraineeDao traineeDao;
    private Trainee trainee;

    @BeforeEach
    void setUp() {
        storage = new Storage();
        traineeStorage = new TraineeStorage(storage);
        traineeDao = new TraineeDaoImpl(traineeStorage);
        trainee = buildTrainee();
    }

    @Test
    void shouldSaveTrainee() {
        traineeDao.save(trainee);

        Trainee result = traineeDao.findById(trainee.getUserId());

        assertEquals(trainee, result);
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

        Trainee actual = traineeDao.findById(1L);

        assertEquals("Andrii Updated", actual.getFirstName());
        assertEquals("456 Oak Street", actual.getAddress());
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