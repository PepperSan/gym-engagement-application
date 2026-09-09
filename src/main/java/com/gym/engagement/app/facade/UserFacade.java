package com.gym.engagement.app.facade;

import com.gym.engagement.app.domain.Trainee;
import com.gym.engagement.app.domain.Trainer;
import com.gym.engagement.app.domain.Training;
import com.gym.engagement.app.dto.TraineeDTO;
import com.gym.engagement.app.dto.TrainerDTO;
import com.gym.engagement.app.dto.TrainingDTO;
import com.gym.engagement.app.mapper.TraineeMapper;
import com.gym.engagement.app.mapper.TrainerMapper;
import com.gym.engagement.app.mapper.TrainingMapper;
import com.gym.engagement.app.service.TraineeService;
import com.gym.engagement.app.service.TrainerService;
import com.gym.engagement.app.service.TrainingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserFacade {

    private final TrainerService trainerService;
    private final TraineeService traineeService;
    private final TrainingService trainingService;

    public TrainerDTO createTrainer(TrainerDTO trainerDto) {
        Trainer trainer = TrainerMapper.toEntity(trainerDto);
        trainerService.create(trainer);
        return TrainerMapper.toDto(trainer);
    }

    public TrainerDTO selectTrainerById(Long id) {
        return TrainerMapper.toDto(trainerService.selectById(id));
    }

    public TrainerDTO updateTrainer(TrainerDTO trainerDto) {
        Trainer trainer = TrainerMapper.toEntity(trainerDto);
        trainerService.update(trainer);
        return TrainerMapper.toDto(trainer);
    }

    public TraineeDTO createTrainee(TraineeDTO traineeDto) {
        Trainee trainee = TraineeMapper.toEntity(traineeDto);
        traineeService.create(trainee);
        return TraineeMapper.toDto(trainee);
    }

    public TraineeDTO selectTraineeById(Long id) {
        return TraineeMapper.toDto(traineeService.selectById(id));
    }

    public TraineeDTO updateTrainee(TraineeDTO traineeDto) {
        Trainee trainee = TraineeMapper.toEntity(traineeDto);
        traineeService.update(trainee);
        return TraineeMapper.toDto(trainee);
    }

    public void deleteTraineeById(Long id) {
        traineeService.deleteById(id);
    }

    public TrainingDTO createTraining(TrainingDTO trainingDto) {
        Training training = TrainingMapper.toEntity(trainingDto);
        trainingService.create(training);
        return TrainingMapper.toDto(training);
    }

    public TrainingDTO selectTrainingById(Long id) {
        return TrainingMapper.toDto(trainingService.selectById(id));
    }
}