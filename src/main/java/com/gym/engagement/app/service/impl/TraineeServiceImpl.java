package com.gym.engagement.app.service.impl;

import com.gym.engagement.app.dao.TraineeDao;
import com.gym.engagement.app.dao.TrainerDao;
import com.gym.engagement.app.domain.Trainee;
import com.gym.engagement.app.service.TraineeService;
import com.gym.engagement.app.service.common.UserCredentialsManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class TraineeServiceImpl implements TraineeService {

    private final TraineeDao traineeDao;
    private final TrainerDao trainerDao;
    private final UserCredentialsManager credentialsManager;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Trainee create(Trainee trainee) {
        log.info("Creating trainee: firstName={}, lastName={}", trainee.getFirstName(), trainee.getLastName());

        String username = credentialsManager.generateUsername(
                trainee.getFirstName(),
                trainee.getLastName(),
                u -> traineeDao.existsByUsername(u) || trainerDao.existsByUsername(u));
        String rawPassword = credentialsManager.generateRandomPassword();
        String encodedPassword = passwordEncoder.encode(rawPassword);

        Trainee traineeToSave = Trainee.builder()
                .userId(trainee.getUserId())
                .firstName(trainee.getFirstName())
                .lastName(trainee.getLastName())
                .username(username)
                .password(encodedPassword)
                .isActive(trainee.isActive())
                .address(trainee.getAddress())
                .dateOfBirth(trainee.getDateOfBirth())
                .build();

        traineeDao.save(traineeToSave);
        log.info("Trainee created: userId={}, username={}", traineeToSave.getUserId(), username);

        return Trainee.builder()
                .userId(traineeToSave.getUserId())
                .firstName(traineeToSave.getFirstName())
                .lastName(traineeToSave.getLastName())
                .username(traineeToSave.getUsername())
                .password(rawPassword)
                .isActive(traineeToSave.isActive())
                .address(traineeToSave.getAddress())
                .dateOfBirth(traineeToSave.getDateOfBirth())
                .build();
    }

    @Override
    public Trainee selectById(Long id) {
        log.info("Selecting trainee by id={}", id);
        Trainee trainee = traineeDao.findById(id);

        if (trainee == null) {
            log.warn("Trainee not found for id={}", id);
        }

        return trainee;
    }

    @Override
    public void update(Trainee trainee) {
        traineeDao.update(trainee);
        log.info("Trainee updated: userId={}", trainee.getUserId());
    }

    @Override
    public void deleteById(Long id) {
        traineeDao.deleteById(id);
        log.info("Trainee deleted: userId={}", id);
    }
}