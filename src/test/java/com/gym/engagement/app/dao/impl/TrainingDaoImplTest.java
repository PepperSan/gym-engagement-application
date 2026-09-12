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

    private TrainingDao trainingDao;
    private Training training;

    @BeforeEach
    void setUp() {
        TrainingStorage trainingStorage = new TrainingStorage();
        CommonStorage commonStorage = new CommonStorage(null, null, trainingStorage);
        trainingDao = new TrainingDaoImpl();
        ((TrainingDaoImpl) trainingDao).setTrainingStorage(commonStorage);
        training = buildTraining();
    }

    @Test
    void shouldSaveTraining() {
        trainingDao.save(training);

        Optional<Training> actual = trainingDao.findById(training.getId());

        assertTrue(actual.isPresent());
        assertEquals(training, actual.get());
    }

    @Test
    void shouldFindTrainingById() {
        trainingDao.save(training);

        Optional<Training> actual = trainingDao.findById(training.getId());

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