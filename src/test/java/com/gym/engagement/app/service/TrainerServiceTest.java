package com.gym.engagement.app.service;

import com.gym.engagement.app.dao.TrainerDao;
import com.gym.engagement.app.domain.Trainer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TrainerServiceTest {

    private static final Long USER_ID = 1L;
    private static final String FIRST_NAME = "Andrii";
    private static final String UPDATED_FIRST_NAME = "Andrii Updated";

    @Mock
    private TrainerDao trainerDao;

    @InjectMocks
    private TrainerService service;

    @Test
    void create_shouldSaveTrainerViaDao() {
        Trainer trainer = Trainer.builder().userId(USER_ID).firstName(FIRST_NAME).build();

        service.create(trainer);

        verify(trainerDao).save(trainer);
    }

    @Test
    void selectById_shouldReturnTrainerFromDao() {
        Trainer trainer = Trainer.builder().userId(USER_ID).firstName(FIRST_NAME).build();
        when(trainerDao.findById(USER_ID)).thenReturn(trainer);

        Trainer actual = service.selectById(USER_ID);

        assertThat(actual).isEqualTo(trainer);
    }

    @Test
    void update_shouldUpdateTrainerViaDao() {
        Trainer trainer = Trainer.builder().userId(USER_ID).firstName(UPDATED_FIRST_NAME).build();

        service.update(trainer);

        verify(trainerDao).update(trainer);
    }
}