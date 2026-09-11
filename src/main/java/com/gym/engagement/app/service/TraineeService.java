package com.gym.engagement.app.service;

import com.gym.engagement.app.dao.TraineeDao;
import com.gym.engagement.app.dao.TrainerDao;
import com.gym.engagement.app.domain.Trainee;
import com.gym.engagement.app.service.common.UserCredentialsManager;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TraineeService {

    private final TraineeDao traineeDao;
    private final TrainerDao trainerDao;
    private final UserCredentialsManager credentialsManager;
    private final PasswordEncoder passwordEncoder;

    public Trainee create(Trainee trainee) {
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

        return traineeToSave.toBuilder()
                .password(rawPassword)
                .build();
    }

    public Trainee selectById(Long id) {
        return traineeDao.findById(id);
    }

    public void update(Trainee trainee) {
        traineeDao.update(trainee);
    }

    public void deleteById(Long id) {
        traineeDao.deleteById(id);
    }
}