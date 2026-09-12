package com.gym.engagement.app.dao.impl;

import com.gym.engagement.app.dao.TraineeDao;
import com.gym.engagement.app.domain.Trainee;
import com.gym.engagement.app.storage.CommonStorage;
import com.gym.engagement.app.storage.TraineeStorage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

class TraineeDaoImplTest {

    private final Trainee trainee = buildTrainee();
    private TraineeDao dao;

    @BeforeEach
    void setUp() {
        TraineeStorage traineeStorage = new TraineeStorage();
        CommonStorage commonStorage = new CommonStorage(traineeStorage, null, null);
        dao = new TraineeDaoImpl();
        ((TraineeDaoImpl) dao).setTraineeStorage(commonStorage);
    }

    @Test
    void shouldSaveTrainee() {
        dao.save(trainee);

        Optional<Trainee> actual = dao.findById(trainee.getUserId());

        assertTrue(actual.isPresent());
        assertEquals(trainee, actual.get());
    }

    @Test
    void shouldFindTraineeById() {
        dao.save(trainee);

        Optional<Trainee> actual = dao.findById(trainee.getUserId());

        assertTrue(actual.isPresent());
        assertEquals("andrii", actual.get().getUsername());
    }

    @Test
    void shouldUpdateTrainee() {
        dao.save(trainee);
        Trainee updatedTrainee = buildUpdatedTrainee();

        dao.update(updatedTrainee);

        Optional<Trainee> persisted = dao.findById(updatedTrainee.getUserId());
        assertTrue(persisted.isPresent());
        assertEquals("Andrii Updated", persisted.get().getFirstName());
        assertEquals("456 Oak Street", persisted.get().getAddress());
    }

    @Test
    void shouldDeleteTraineeById() {
        dao.save(trainee);

        dao.deleteById(trainee.getUserId());

        assertTrue(dao.findById(trainee.getUserId()).isEmpty());
    }

    @Test
    void existsByUsername_shouldReturnTrue_whenUsernameExists() {
        dao.save(trainee);

        boolean actual = dao.existsByUsername(trainee.getUsername());

        assertTrue(actual);
    }

    @Test
    void existsByUsername_shouldReturnFalse_whenUsernameDoesNotExist() {
        boolean actual = dao.existsByUsername("nonexistent.username");

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