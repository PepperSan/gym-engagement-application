package com.gym.engagement.app.dao;

import com.gym.engagement.app.domain.Trainee;
import com.gym.engagement.app.storage.Storage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class TraineeDaoImplTest {

    private Storage storage;
    private TraineeDao traineeDao;
    private Trainee trainee;

    @BeforeEach
    void setUp() {
        storage = new Storage();
        traineeDao = new TraineeDaoImpl(storage);

        trainee = Trainee.builder()
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

    @Test
    void shouldSaveTrainee() {
        traineeDao.save(trainee);

        Trainee result = traineeDao.findById(trainee.getUserId());

        assertEquals(trainee, result);
    }

    @Test
    void shouldFindTraineeById() {
        traineeDao.save(trainee);

        Trainee result = traineeDao.findById(1L);

        assertNotNull(result);
        assertEquals("andrii", result.getUsername());
    }

    @Test
    void shouldUpdateTrainee() {
        traineeDao.save(trainee);

        trainee = Trainee.builder()
                .userId(1L)
                .username("andrii")
                .firstName("Andrii Updated")
                .lastName("Testov")
                .password("password")
                .isActive(true)
                .address("456 Oak Street")
                .dateOfBirth(LocalDate.of(1990, 1, 1))
                .build();

        traineeDao.update(trainee);

        Trainee result = traineeDao.findById(1L);

        assertEquals("Andrii Updated", result.getFirstName());
        assertEquals("456 Oak Street", result.getAddress());
    }

    @Test
    void shouldDeleteTraineeById() {
        traineeDao.save(trainee);

        traineeDao.deleteById(1L);

        assertNull(traineeDao.findById(1L));
    }
}