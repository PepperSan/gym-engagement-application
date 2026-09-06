package com.gym.engagement.app.dao;

import com.gym.engagement.app.domain.Trainer;
import com.gym.engagement.app.storage.Storage;
import com.gym.engagement.app.storage.TrainerStorage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TrainerDaoImplTest {

    private Storage storage;
    private TrainerStorage trainerStorage;
    private TrainerDao trainerDao;
    private Trainer trainer;

    @BeforeEach
    void setUp() {
        storage = new Storage();
        trainerStorage = new TrainerStorage(storage);
        trainerDao = new TrainerDaoImpl(trainerStorage);
        trainer = buildTrainer();
    }

    @Test
    void shouldSaveTrainer() {
        trainerDao.save(trainer);

        Trainer result = trainerDao.findById(trainer.getUserId());

        assertEquals(trainer, result);
    }

    @Test
    void shouldFindTrainerById() {
        trainerDao.save(trainer);

        Trainer actual = trainerDao.findById(trainer.getUserId());

        assertNotNull(actual);
        assertEquals("trainer", actual.getUsername());
    }

    @Test
    void shouldUpdateTrainer() {
        trainerDao.save(trainer);

        Trainer updatedTrainer = buildUpdatedTrainer();

        trainerDao.update(updatedTrainer);

        Trainer actual = trainerDao.findById(1L);

        assertEquals("Trainer Updated", actual.getFirstName());

    }

    @Test
    void shouldDeleteTrainerById() {
        trainerDao.save(trainer);

        trainerDao.deleteById(trainer.getUserId());

        assertNull(trainerDao.findById(trainer.getUserId()));
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