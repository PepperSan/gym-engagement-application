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

    private static final Long USER_ID = 1L;
    private static final String FIRST_NAME = "Andrii";
    private static final String UPDATED_FIRST_NAME = "Andrii Updated";

    @Mock
    private TraineeDao traineeDao;

    @InjectMocks
    private TraineeService service;

    @Test
    void create_shouldSaveTraineeViaDao() {
        Trainee trainee = Trainee.builder().userId(USER_ID).firstName(FIRST_NAME).build();

        service.create(trainee);

        verify(traineeDao).save(trainee);
    }

    @Test
    void selectById_shouldReturnTraineeFromDao() {
        Trainee trainee = Trainee.builder().userId(USER_ID).firstName(FIRST_NAME).build();
        when(traineeDao.findById(USER_ID)).thenReturn(trainee);

        Trainee actual = service.selectById(USER_ID);

        assertThat(actual).isEqualTo(trainee);
    }

    @Test
    void update_shouldUpdateTraineeViaDao() {
        Trainee trainee = Trainee.builder().userId(USER_ID).firstName(UPDATED_FIRST_NAME).build();

        service.update(trainee);

        verify(traineeDao).update(trainee);
    }

    @Test
    void deleteById_shouldDeleteTraineeViaDao() {
        service.deleteById(USER_ID);

        verify(traineeDao).deleteById(USER_ID);
    }
}