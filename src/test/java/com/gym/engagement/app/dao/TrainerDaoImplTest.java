package com.gym.engagement.app.dao;

import com.gym.engagement.app.dao.impl.TrainerDao;
import com.gym.engagement.app.domain.Trainer;
import com.gym.engagement.app.storage.TrainerStorage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class TrainerDaoImplTest {

    private TrainerStorage trainerStorage;
    private TrainerDao trainerDao;
    private Trainer trainer;

    @BeforeEach
    void setUp() {
        trainerStorage = new TrainerStorage();
        trainerDao = new TrainerDaoImpl(trainerStorage);
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

        Trainer actual = trainerDao.findById(updatedTrainer.getUserId());
        assertEquals("Trainer Updated", actual.getFirstName());
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