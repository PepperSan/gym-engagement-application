package com.gym.engagement.app.dao.impl;

import com.gym.engagement.app.dao.TraineeDao;
import com.gym.engagement.app.domain.Trainee;
import com.gym.engagement.app.storage.CommonStorage;
import com.gym.engagement.app.storage.TraineeStorage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class TraineeDaoImplTest {

    private TraineeDao traineeDao;
    private Trainee trainee;

    @BeforeEach
    void setUp() {
        TraineeStorage traineeStorage = new TraineeStorage();
        CommonStorage commonStorage = new CommonStorage(traineeStorage, null, null);
        traineeDao = new TraineeDaoImpl();
        ((TraineeDaoImpl) traineeDao).setTraineeStorage(commonStorage);
        trainee = buildTrainee();
    }

    @Test
    void shouldSaveTrainee() {
        traineeDao.save(trainee);

        Optional<Trainee> actual = traineeDao.findById(trainee.getUserId());

        assertTrue(actual.isPresent());
        assertEquals(trainee, actual.get());
    }

    @Test
    void shouldFindTraineeById() {
        traineeDao.save(trainee);

        Optional<Trainee> actual = traineeDao.findById(trainee.getUserId());

        assertTrue(actual.isPresent());
        assertEquals("andrii", actual.get().getUsername());
    }

    @Test
    void shouldUpdateTrainee() {
        traineeDao.save(trainee);
        Trainee updatedTrainee = buildUpdatedTrainee();

        traineeDao.update(updatedTrainee);

        Optional<Trainee> persisted = traineeDao.findById(updatedTrainee.getUserId());
        assertTrue(persisted.isPresent());
        assertEquals("Andrii Updated", persisted.get().getFirstName());
        assertEquals("456 Oak Street", persisted.get().getAddress());
    }

    @Test
    void shouldDeleteTraineeById() {
        traineeDao.save(trainee);

        traineeDao.deleteById(trainee.getUserId());

        assertTrue(traineeDao.findById(trainee.getUserId()).isEmpty());
    }

    @Test
    void existsByUsername_shouldReturnTrue_whenUsernameExists() {
        traineeDao.save(trainee);

        boolean actual = traineeDao.existsByUsername(trainee.getUsername());

        assertTrue(actual);
    }

    @Test
    void existsByUsername_shouldReturnFalse_whenUsernameDoesNotExist() {
        boolean actual = traineeDao.existsByUsername("nonexistent.username");

        assertFalse(actual);
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
                .build();
    }
}