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
public class GymFacade {

    private final TrainerService trainerService;
    private final TraineeService traineeService;
    private final TrainingService trainingService;

    private final TrainerMapper trainerMapper;
    private final TraineeMapper traineeMapper;
    private final TrainingMapper trainingMapper;

    public TrainerDTO createTrainer(TrainerDTO trainerDto) {
        Trainer trainer = trainerMapper.toEntity(trainerDto);
        trainerService.create(trainer);

        return trainerMapper.toDto(trainer);
    }

    public TrainerDTO selectTrainerById(Long id) {
        return trainerMapper.toDto(trainerService.selectById(id));
    }

    public TrainerDTO updateTrainer(TrainerDTO trainerDto) {
        Trainer trainer = trainerMapper.toEntity(trainerDto);
        trainerService.update(trainer);

        return trainerMapper.toDto(trainer);
    }

    public TraineeDTO createTrainee(TraineeDTO traineeDto) {
        Trainee trainee = traineeMapper.toEntity(traineeDto);
        traineeService.create(trainee);

        return traineeMapper.toDto(trainee);
    }

    public TraineeDTO selectTraineeById(Long id) {
        return traineeMapper.toDto(traineeService.selectById(id));
    }

    public TraineeDTO updateTrainee(TraineeDTO traineeDto) {
        Trainee trainee = traineeMapper.toEntity(traineeDto);
        traineeService.update(trainee);

        return traineeMapper.toDto(trainee);
    }

    public void deleteTraineeById(Long id) {
        traineeService.deleteById(id);
    }

    public TrainingDTO createTraining(TrainingDTO trainingDto) {
        Training training = trainingMapper.toEntity(trainingDto);
        trainingService.create(training);

        return trainingMapper.toDto(training);
    }

    public TrainingDTO selectTrainingById(Long id) {
        return trainingMapper.toDto(trainingService.selectById(id));
    }
}