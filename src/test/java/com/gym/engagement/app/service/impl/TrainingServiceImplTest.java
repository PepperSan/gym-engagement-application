package com.gym.engagement.app.service.impl;

import com.gym.engagement.app.dao.TrainingDao;
import com.gym.engagement.app.domain.Training;
import com.gym.engagement.app.service.exception.CoreServiceException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TrainingServiceImplTest {

    private static final Long TRAINING_ID = 1L;
    private static final String TRAINING_NAME = "Cardio";

    @Mock
    private TrainingDao trainingDao;

    @InjectMocks
    private TrainingServiceImpl service;

    @Test
    void create_shouldSaveTrainingViaDao() {
        Training training = Training.builder().id(TRAINING_ID).trainingName(TRAINING_NAME).build();

        service.create(training);

        verify(trainingDao).save(training);
    }

    @Test
    void selectById_shouldReturnTrainingFromDao() {
        Training training = Training.builder().id(TRAINING_ID).trainingName(TRAINING_NAME).build();
        when(trainingDao.findById(TRAINING_ID)).thenReturn(Optional.of(training));

        Training actual = service.selectById(TRAINING_ID);

        assertThat(actual).isEqualTo(training);
    }

    @Test
    void selectById_shouldThrowException_whenTrainingNotFound() {
        when(trainingDao.findById(TRAINING_ID)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> service.selectById(TRAINING_ID))
                .isInstanceOf(CoreServiceException.class)
                .hasMessage("Training with id " + TRAINING_ID + " not found");
    }
}