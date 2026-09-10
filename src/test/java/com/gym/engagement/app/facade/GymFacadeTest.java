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
    private GymFacade gymFacade;

    @Test
    void createTrainee_shouldMapDelegateAndReturnDto() {
        TraineeDTO traineeDto = TraineeDTO.builder().firstName("Andrii").lastName("Test").build();
        Trainee trainee = Trainee.builder().firstName("Andrii").lastName("Test").build();

        when(traineeMapper.toEntity(traineeDto)).thenReturn(trainee);
        when(traineeMapper.toDto(trainee)).thenReturn(traineeDto);

        TraineeDTO actual = gymFacade.createTrainee(traineeDto);

        verify(traineeService).create(trainee);
        assertThat(actual).isEqualTo(traineeDto);
    }

    @Test
    void selectTraineeById_shouldMapAndReturnDto() {
        Trainee trainee = Trainee.builder().userId(1L).firstName("Andrii").build();
        TraineeDTO traineeDto = TraineeDTO.builder().userId(1L).firstName("Andrii").build();

        when(traineeService.selectById(1L)).thenReturn(trainee);
        when(traineeMapper.toDto(trainee)).thenReturn(traineeDto);

        TraineeDTO actual = gymFacade.selectTraineeById(1L);

        assertThat(actual).isEqualTo(traineeDto);
    }

    @Test
    void deleteTraineeById_shouldDelegateToService() {
        gymFacade.deleteTraineeById(1L);

        verify(traineeService).deleteById(1L);
    }
}