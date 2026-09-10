package com.gym.engagement.app.facade;

import com.gym.engagement.app.domain.Trainee;
import com.gym.engagement.app.dto.TraineeDTO;
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
}