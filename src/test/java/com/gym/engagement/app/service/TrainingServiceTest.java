package com.gym.engagement.app.service;

import com.gym.engagement.app.dao.TrainingDao;
import com.gym.engagement.app.domain.Training;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TrainingServiceTest {

    private static final Long TRAINING_ID = 1L;
    private static final String TRAINING_NAME = "Cardio";

    @Mock
    private TrainingDao trainingDao;

    @InjectMocks
    private TrainingService service;

    @Test
    void create_shouldSaveTrainingViaDao() {
        Training training = Training.builder().id(TRAINING_ID).trainingName(TRAINING_NAME).build();

        service.create(training);

        verify(trainingDao).save(training);
    }

    @Test
    void selectById_shouldReturnTrainingFromDao() {
        Training training = Training.builder().id(TRAINING_ID).trainingName(TRAINING_NAME).build();
        when(trainingDao.findById(TRAINING_ID)).thenReturn(training);

        Training actual = service.selectById(TRAINING_ID);

        assertThat(actual).isEqualTo(training);
    }
}