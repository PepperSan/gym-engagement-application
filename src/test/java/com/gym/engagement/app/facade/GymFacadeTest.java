package com.gym.engagement.app.facade;

import com.gym.engagement.app.domain.Trainee;
import com.gym.engagement.app.domain.Trainer;
import com.gym.engagement.app.domain.Training;
import com.gym.engagement.app.domain.TrainingType;
import com.gym.engagement.app.dto.TraineeDTO;
import com.gym.engagement.app.dto.TrainerDTO;
import com.gym.engagement.app.dto.TrainingDTO;
import com.gym.engagement.app.mapper.TraineeMapper;
import com.gym.engagement.app.mapper.TrainerMapper;
import com.gym.engagement.app.mapper.TrainingMapper;
import com.gym.engagement.app.service.TraineeService;
import com.gym.engagement.app.service.TrainerService;
import com.gym.engagement.app.service.TrainingService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GymFacadeTest {

    private static final Long USER_ID = 1L;
    private static final String FIRST_NAME = "Andrii";
    private static final String LAST_NAME = "Test";
    private static final Long TRAINING_ID = 1L;
    private static final String TRAINING_NAME = "Cardio Session";

    @Mock
    private TrainerService trainerService;

    @Mock
    private TraineeService traineeService;

    @Mock
    private TrainingService trainingService;

    @Mock
    private TrainerMapper trainerMapper;

    @Mock
    private TraineeMapper traineeMapper;

    @Mock
    private TrainingMapper trainingMapper;

    @InjectMocks
    private GymFacade facade;

    @Test
    void createTrainee_shouldMapDelegateAndReturnDto() {
        TraineeDTO traineeDto = TraineeDTO.builder().firstName(FIRST_NAME).lastName(LAST_NAME).build();
        Trainee trainee = Trainee.builder().firstName(FIRST_NAME).lastName(LAST_NAME).build();

        when(traineeMapper.toEntity(traineeDto)).thenReturn(trainee);
        when(traineeMapper.toDto(trainee)).thenReturn(traineeDto);

        TraineeDTO actual = facade.createTrainee(traineeDto);

        verify(traineeService).create(trainee);
        assertThat(actual).isEqualTo(traineeDto);
    }

    @Test
    void selectTraineeById_shouldMapAndReturnDto() {
        Trainee trainee = Trainee.builder().userId(USER_ID).firstName(FIRST_NAME).build();
        TraineeDTO traineeDto = TraineeDTO.builder().userId(USER_ID).firstName(FIRST_NAME).build();

        when(traineeService.selectById(USER_ID)).thenReturn(trainee);
        when(traineeMapper.toDto(trainee)).thenReturn(traineeDto);

        TraineeDTO actual = facade.selectTraineeById(USER_ID);

        assertThat(actual).isEqualTo(traineeDto);
    }

    @Test
    void deleteTraineeById_shouldDelegateToService() {
        facade.deleteTraineeById(USER_ID);

        verify(traineeService).deleteById(USER_ID);
    }
    @Test
    void createTrainer_shouldMapDelegateAndReturnDto() {
        TrainerDTO trainerDto = TrainerDTO.builder().firstName(FIRST_NAME).lastName(LAST_NAME).build();
        Trainer trainer = Trainer.builder().firstName(FIRST_NAME).lastName(LAST_NAME).build();

        when(trainerMapper.toEntity(trainerDto)).thenReturn(trainer);
        when(trainerMapper.toDto(trainer)).thenReturn(trainerDto);

        TrainerDTO actual = facade.createTrainer(trainerDto);

        verify(trainerService).create(trainer);
        assertThat(actual).isEqualTo(trainerDto);
    }

    @Test
    void selectTrainerById_shouldMapAndReturnDto() {
        Trainer trainer = Trainer.builder().userId(USER_ID).firstName(FIRST_NAME).build();
        TrainerDTO trainerDto = TrainerDTO.builder().userId(USER_ID).firstName(FIRST_NAME).build();

        when(trainerService.selectById(USER_ID)).thenReturn(trainer);
        when(trainerMapper.toDto(trainer)).thenReturn(trainerDto);

        TrainerDTO actual = facade.selectTrainerById(USER_ID);

        assertThat(actual).isEqualTo(trainerDto);
    }

    @Test
    void updateTrainer_shouldMapDelegateAndReturnDto() {
        TrainerDTO trainerDto = TrainerDTO.builder().userId(USER_ID).firstName(FIRST_NAME).build();
        Trainer trainer = Trainer.builder().userId(USER_ID).firstName(FIRST_NAME).build();

        when(trainerMapper.toEntity(trainerDto)).thenReturn(trainer);
        when(trainerMapper.toDto(trainer)).thenReturn(trainerDto);

        TrainerDTO actual = facade.updateTrainer(trainerDto);

        verify(trainerService).update(trainer);
        assertThat(actual).isEqualTo(trainerDto);
    }

    @Test
    void updateTrainee_shouldMapDelegateAndReturnDto() {
        TraineeDTO traineeDto = TraineeDTO.builder().userId(USER_ID).firstName(FIRST_NAME).build();
        Trainee trainee = Trainee.builder().userId(USER_ID).firstName(FIRST_NAME).build();

        when(traineeMapper.toEntity(traineeDto)).thenReturn(trainee);
        when(traineeMapper.toDto(trainee)).thenReturn(traineeDto);

        TraineeDTO actual = facade.updateTrainee(traineeDto);

        verify(traineeService).update(trainee);
        assertThat(actual).isEqualTo(traineeDto);
    }

    @Test
    void createTraining_shouldMapDelegateAndReturnDto() {
        TrainingDTO trainingDto = TrainingDTO.builder().trainingName(TRAINING_NAME).build();
        Training training = Training.builder().trainingName(TRAINING_NAME).build();

        when(trainingMapper.toEntity(trainingDto)).thenReturn(training);
        when(trainingMapper.toDto(training)).thenReturn(trainingDto);

        TrainingDTO actual = facade.createTraining(trainingDto);

        verify(trainingService).create(training);
        assertThat(actual).isEqualTo(trainingDto);
    }

    @Test
    void selectTrainingById_shouldMapAndReturnDto() {
        Training training = Training.builder().id(TRAINING_ID).trainingName(TRAINING_NAME).build();
        TrainingDTO trainingDto = TrainingDTO.builder().id(TRAINING_ID).trainingName(TRAINING_NAME).build();

        when(trainingService.selectById(TRAINING_ID)).thenReturn(training);
        when(trainingMapper.toDto(training)).thenReturn(trainingDto);

        TrainingDTO actual = facade.selectTrainingById(TRAINING_ID);

        assertThat(actual).isEqualTo(trainingDto);
    }
}