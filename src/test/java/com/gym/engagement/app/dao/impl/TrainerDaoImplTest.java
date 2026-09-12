package com.gym.engagement.app.dao.impl;

import com.gym.engagement.app.dao.TrainerDao;
import com.gym.engagement.app.domain.Trainer;
import com.gym.engagement.app.storage.CommonStorage;
import com.gym.engagement.app.storage.TrainerStorage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

class TrainerDaoImplTest {

    private TrainerDao dao;
    private Trainer trainer;

    @BeforeEach
    void setUp() {
        TrainerStorage trainerStorage = new TrainerStorage();
        CommonStorage commonStorage = new CommonStorage(null, trainerStorage, null);
        dao = new TrainerDaoImpl();
        ((TrainerDaoImpl) dao).setTrainerStorage(commonStorage);
        trainer = buildTrainer();
    }

    @Test
    void shouldSaveTrainer() {
        dao.save(trainer);

        Optional<Trainer> actual = dao.findById(trainer.getUserId());

        assertTrue(actual.isPresent());
        assertEquals(trainer, actual.get());
    }

    @Test
    void shouldFindTrainerById() {
        dao.save(trainer);

        Optional<Trainer> actual = dao.findById(trainer.getUserId());

        assertTrue(actual.isPresent());
        assertEquals("trainer", actual.get().getUsername());
    }

    @Test
    void shouldUpdateTrainer() {
        dao.save(trainer);
        Trainer updatedTrainer = buildUpdatedTrainer();

        dao.update(updatedTrainer);

        Optional<Trainer> persisted = dao.findById(updatedTrainer.getUserId());
        assertTrue(persisted.isPresent());
        assertEquals("Trainer Updated", persisted.get().getFirstName());
    }

    @Test
    void existsByUsername_shouldReturnTrue_whenUsernameExists() {
        dao.save(trainer);

        boolean actual = dao.existsByUsername(trainer.getUsername());

        assertTrue(actual);
    }

    @Test
    void existsByUsername_shouldReturnFalse_whenUsernameDoesNotExist() {
        boolean actual = dao.existsByUsername("nonexistent.username");

        assertFalse(actual);
    }

    private static Trainer buildTrainer() {
        return Trainer.builder()
                .userId(1L)
                .username("trainer")
                .firstName("Trainer")
                .lastName("Testov")
                .password("password")
                .isActive(true)
                .build();
    }

    private static Trainer buildUpdatedTrainer() {
        return Trainer.builder()
                .userId(1L)
                .username("trainer")
                .firstName("Trainer Updated")
                .lastName("Testov")
                .password("password")
                .isActive(true)
                .build();
    }
}