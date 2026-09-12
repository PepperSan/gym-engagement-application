package com.gym.engagement.app.dao.impl;

import com.gym.engagement.app.dao.TrainingDao;
import com.gym.engagement.app.domain.Training;
import com.gym.engagement.app.storage.CommonStorage;
import com.gym.engagement.app.storage.TrainingStorage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TrainingDaoImplTest {

    private final Training training = buildTraining();
    private TrainingDao dao;

    @BeforeEach
    void setUp() {
        TrainingStorage trainingStorage = new TrainingStorage();
        CommonStorage commonStorage = new CommonStorage(null, null, trainingStorage);
        dao = new TrainingDaoImpl();
        ((TrainingDaoImpl) dao).setTrainingStorage(commonStorage);
    }

    @Test
    void shouldSaveTraining() {
        dao.save(training);

        Optional<Training> actual = dao.findById(training.getId());

        assertTrue(actual.isPresent());
        assertEquals(training, actual.get());
    }

    @Test
    void shouldFindTrainingById() {
        dao.save(training);

        Optional<Training> actual = dao.findById(training.getId());

        assertTrue(actual.isPresent());
        assertEquals("Cardio", actual.get().getTrainingName());
    }

    private static Training buildTraining() {
        return Training.builder()
                .id(1L)
                .trainingName("Cardio")
                .build();
    }
}