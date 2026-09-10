package com.gym.engagement.app.dao.impl;

import com.gym.engagement.app.dao.TrainerDao;
import com.gym.engagement.app.domain.Trainer;
import com.gym.engagement.app.storage.CommonStorage;
import com.gym.engagement.app.storage.TrainerStorage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class TrainerDaoImplTest {

    private TrainerDao trainerDao;
    private Trainer trainer;

    @BeforeEach
    void setUp() {
        TrainerStorage trainerStorage = new TrainerStorage();
        CommonStorage commonStorage = new CommonStorage(null, trainerStorage, null);
        trainerDao = new TrainerDaoImpl();
        ((TrainerDaoImpl) trainerDao).setTrainerStorage(commonStorage);
        trainer = buildTrainer();
    }

    @Test
    void shouldSaveTrainer() {
        trainerDao.save(trainer);

        Trainer actual = trainerDao.findById(trainer.getUserId());

        assertEquals(trainer, actual);
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

        Trainer persisted = trainerDao.findById(updatedTrainer.getUserId());
        assertEquals("Trainer Updated", persisted.getFirstName());
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