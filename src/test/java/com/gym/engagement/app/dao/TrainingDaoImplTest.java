package com.gym.engagement.app.dao;

import com.gym.engagement.app.dao.impl.TrainingDao;
import com.gym.engagement.app.domain.Training;
import com.gym.engagement.app.storage.TrainingStorage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class TrainingDaoImplTest {

    private TrainingStorage trainingStorage;
    private TrainingDao trainingDao;
    private Training training;

    @BeforeEach
    void setUp() {
        trainingStorage = new TrainingStorage();
        trainingDao = new TrainingDaoImpl(trainingStorage);
        training = buildTraining();
    }

    @Test
    void shouldSaveTraining() {
        trainingDao.save(training);

        Training actual = trainingDao.findById(training.getId());

        assertNotNull(actual);
        assertEquals(training, actual);
    }

    @Test
    void shouldFindTrainingById() {
        trainingDao.save(training);

        Training actual = trainingDao.findById(training.getId());

        assertNotNull(actual);
        assertEquals("Java Training", actual.getTrainingName());
    }


    private static Training buildTraining() {
        return Training.builder()
                .id(1L)
                .traineeId(1L)
                .trainerId(1L)
                .trainingName("Java Training")
                .trainingType(null)
                .trainingDuration(60)
                .trainingDate(LocalDate.of(2026, 9, 6))
                .build();
    }
}