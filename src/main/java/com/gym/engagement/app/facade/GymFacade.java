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
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
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
        log.debug("Facade: create trainer request received");
        Trainer trainer = trainerMapper.toEntity(trainerDto);
        Trainer createdTrainer = trainerService.create(trainer);

        return trainerMapper.toDto(createdTrainer);
    }

    public TrainerDTO selectTrainerById(Long id) {
        log.debug("Facade: select trainer by id={}", id);
        return trainerMapper.toDto(trainerService.selectById(id));
    }

    public TrainerDTO updateTrainer(TrainerDTO trainerDto) {
        log.debug("Facade: update trainer request received, id={}", trainerDto.getUserId());
        Trainer trainer = trainerMapper.toEntity(trainerDto);
        trainerService.update(trainer);

        return trainerMapper.toDto(trainer);
    }

    public TraineeDTO createTrainee(TraineeDTO traineeDto) {
        log.debug("Facade: create trainee request received");
        Trainee trainee = traineeMapper.toEntity(traineeDto);
        Trainee createdTrainee = traineeService.create(trainee);

        return traineeMapper.toDto(createdTrainee);
    }

    public TraineeDTO selectTraineeById(Long id) {
        log.debug("Facade: select trainee by id={}", id);
        return traineeMapper.toDto(traineeService.selectById(id));
    }

    public TraineeDTO updateTrainee(TraineeDTO traineeDto) {
        log.debug("Facade: update trainee request received, id={}", traineeDto.getUserId());
        Trainee trainee = traineeMapper.toEntity(traineeDto);
        traineeService.update(trainee);

        return traineeMapper.toDto(trainee);
    }

    public void deleteTraineeById(Long id) {
        log.debug("Facade: delete trainee request received, id={}", id);
        traineeService.deleteById(id);
    }

    public TrainingDTO createTraining(TrainingDTO trainingDto) {
        log.debug("Facade: create training request received");
        Training training = trainingMapper.toEntity(trainingDto);
        trainingService.create(training);

        return trainingMapper.toDto(training);
    }

    public TrainingDTO selectTrainingById(Long id) {
        log.debug("Facade: select training by id={}", id);
        return trainingMapper.toDto(trainingService.selectById(id));
    }
}