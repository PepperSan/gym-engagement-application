package com.gym.engagement.app.service;

import com.gym.engagement.app.dao.TraineeDao;
import com.gym.engagement.app.domain.Trainee;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TraineeServiceTest {

    @Mock
    private TraineeDao traineeDao;

    @InjectMocks
    private TraineeService traineeService;

    @Test
    void create_shouldSaveTraineeViaDao() {
        Trainee trainee = Trainee.builder().userId(1L).firstName("Andrii").build();

        traineeService.create(trainee);

        verify(traineeDao).save(trainee);
    }

    @Test
    void selectById_shouldReturnTraineeFromDao() {
        Trainee trainee = Trainee.builder().userId(1L).firstName("Andrii").build();
        when(traineeDao.findById(1L)).thenReturn(trainee);

        Trainee actual = traineeService.selectById(1L);

        assertThat(actual).isEqualTo(trainee);
    }

    @Test
    void update_shouldUpdateTraineeViaDao() {
        Trainee trainee = Trainee.builder().userId(1L).firstName("Andrii Updated").build();

        traineeService.update(trainee);

        verify(traineeDao).update(trainee);
    }

    @Test
    void deleteById_shouldDeleteTraineeViaDao() {
        traineeService.deleteById(1L);

        verify(traineeDao).deleteById(1L);
    }
}