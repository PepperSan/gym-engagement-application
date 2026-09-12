package com.gym.engagement.app.service.impl;

import com.gym.engagement.app.dao.TraineeDao;
import com.gym.engagement.app.domain.Trainee;
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
class TraineeServiceImplTest {

    private static final Long USER_ID = 1L;
    private static final String FIRST_NAME = "Andrii";
    private static final String LAST_NAME = "Test";
    private static final String GENERATED_USERNAME = "Andrii.Test";
    private static final String RAW_PASSWORD = "rawPass123";
    private static final String ENCODED_PASSWORD = "encodedPass123";

    @Mock
    private TraineeDao traineeDao;

    @Mock
    private UserCredentialsManager credentialsManager;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private TraineeServiceImpl service;

    @Test
    void create_shouldGenerateCredentialsAndSaveTrainee() {
        Trainee trainee = Trainee.builder().userId(USER_ID).firstName(FIRST_NAME).lastName(LAST_NAME).build();

        when(credentialsManager.generateUsername(eq(FIRST_NAME), eq(LAST_NAME), any())).thenReturn(GENERATED_USERNAME);
        when(credentialsManager.generateRandomPassword()).thenReturn(RAW_PASSWORD);
        when(passwordEncoder.encode(RAW_PASSWORD)).thenReturn(ENCODED_PASSWORD);

        Trainee actual = service.create(trainee);

        ArgumentCaptor<Trainee> captor = ArgumentCaptor.forClass(Trainee.class);
        verify(traineeDao).save(captor.capture());
        Trainee saved = captor.getValue();
        assertThat(saved.getUsername()).isEqualTo(GENERATED_USERNAME);
        assertThat(saved.getPassword()).isEqualTo(ENCODED_PASSWORD);

        assertThat(actual.getUsername()).isEqualTo(GENERATED_USERNAME);
        assertThat(actual.getPassword()).isEqualTo(RAW_PASSWORD);
    }

    @Test
    void selectById_shouldReturnTraineeFromDao() {
        Trainee trainee = Trainee.builder().userId(USER_ID).firstName(FIRST_NAME).build();
        when(traineeDao.findById(USER_ID)).thenReturn(Optional.of(trainee));

        Trainee actual = service.selectById(USER_ID);

        assertThat(actual).isEqualTo(trainee);
    }

    @Test
    void selectById_shouldThrowException_whenTraineeNotFound() {
        when(traineeDao.findById(USER_ID)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> service.selectById(USER_ID))
                .isInstanceOf(CoreServiceException.class)
                .hasMessage(String.format("Trainee with id %s not found", USER_ID));
    }

    @Test
    void update_shouldUpdateTraineeViaDao() {
        Trainee trainee = Trainee.builder().userId(USER_ID).firstName(FIRST_NAME).build();
        when(traineeDao.findById(USER_ID)).thenReturn(Optional.of(trainee));

        service.update(trainee);

        verify(traineeDao).update(trainee);
    }

    @Test
    void update_shouldThrowException_whenTraineeNotFound() {
        Trainee trainee = Trainee.builder().userId(USER_ID).firstName(FIRST_NAME).build();
        when(traineeDao.findById(USER_ID)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> service.update(trainee))
                .isInstanceOf(CoreServiceException.class)
                .hasMessage(String.format("Trainee with id %s not found", USER_ID));
    }

    @Test
    void deleteById_shouldDeleteTraineeViaDao() {
        service.deleteById(USER_ID);

        verify(traineeDao).deleteById(USER_ID);
    }
}