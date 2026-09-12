package com.gym.engagement.app.service.impl;

import com.gym.engagement.app.dao.TrainerDao;
import com.gym.engagement.app.domain.Trainer;
import com.gym.engagement.app.service.common.UserCredentialsManager;
import com.gym.engagement.app.service.exception.CoreServiceException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TrainerServiceImplTest {

    private static final Long USER_ID = 1L;
    private static final String FIRST_NAME = "Andrii";
    private static final String LAST_NAME = "Test";
    private static final String GENERATED_USERNAME = "Andrii.Test";
    private static final String RAW_PASSWORD = "rawPass123";
    private static final String ENCODED_PASSWORD = "encodedPass123";

    @Mock
    private TrainerDao trainerDao;

    @Mock
    private UserCredentialsManager credentialsManager;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private TrainerServiceImpl service;

    @Test
    void create_shouldGenerateCredentialsAndSaveTrainer() {
        Trainer trainer = Trainer.builder().userId(USER_ID).firstName(FIRST_NAME).lastName(LAST_NAME).build();

        when(credentialsManager.generateUsername(eq(FIRST_NAME), eq(LAST_NAME), any())).thenReturn(GENERATED_USERNAME);
        when(credentialsManager.generateRandomPassword()).thenReturn(RAW_PASSWORD);
        when(passwordEncoder.encode(RAW_PASSWORD)).thenReturn(ENCODED_PASSWORD);

        Trainer actual = service.create(trainer);

        ArgumentCaptor<Trainer> captor = ArgumentCaptor.forClass(Trainer.class);
        verify(trainerDao).save(captor.capture());
        Trainer saved = captor.getValue();
        assertThat(saved.getUsername()).isEqualTo(GENERATED_USERNAME);
        assertThat(saved.getPassword()).isEqualTo(ENCODED_PASSWORD);

        assertThat(actual.getUsername()).isEqualTo(GENERATED_USERNAME);
        assertThat(actual.getPassword()).isEqualTo(RAW_PASSWORD);
    }

    @Test
    void selectById_shouldReturnTrainerFromDao() {
        Trainer trainer = Trainer.builder().userId(USER_ID).firstName(FIRST_NAME).build();
        when(trainerDao.findById(USER_ID)).thenReturn(Optional.of(trainer));

        Trainer actual = service.selectById(USER_ID);

        assertThat(actual).isEqualTo(trainer);
    }

    @Test
    void selectById_shouldThrowException_whenTrainerNotFound() {
        when(trainerDao.findById(USER_ID)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> service.selectById(USER_ID))
                .isInstanceOf(CoreServiceException.class)
                .hasMessage("Trainer with id " + USER_ID + " not found");
    }

    @Test
    void update_shouldUpdateTrainerViaDao() {
        Trainer trainer = Trainer.builder().userId(USER_ID).firstName(FIRST_NAME).build();
        when(trainerDao.findById(USER_ID)).thenReturn(Optional.of(trainer));

        service.update(trainer);

        verify(trainerDao).update(trainer);
    }

    @Test
    void update_shouldThrowException_whenTrainerNotFound() {
        Trainer trainer = Trainer.builder().userId(USER_ID).firstName(FIRST_NAME).build();
        when(trainerDao.findById(USER_ID)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> service.update(trainer))
                .isInstanceOf(CoreServiceException.class)
                .hasMessage("Trainer with id " + USER_ID + " not found");
    }
}