package com.gym.engagement.app.service;

import com.gym.engagement.app.dao.TraineeDao;
import com.gym.engagement.app.dao.TrainerDao;
import com.gym.engagement.app.domain.Trainer;
import com.gym.engagement.app.service.common.UserCredentialsManager;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TrainerService {

    private final TrainerDao trainerDao;
    private final TraineeDao traineeDao;
    private final UserCredentialsManager credentialsManager;
    private final PasswordEncoder passwordEncoder;

    public Trainer create(Trainer trainer) {
        String username = credentialsManager.generateUsername(
                trainer.getFirstName(),
                trainer.getLastName(),
                u -> trainerDao.existsByUsername(u) || traineeDao.existsByUsername(u));
        String rawPassword = credentialsManager.generateRandomPassword();
        String encodedPassword = passwordEncoder.encode(rawPassword);

        Trainer trainerToSave = Trainer.builder()
                .userId(trainer.getUserId())
                .firstName(trainer.getFirstName())
                .lastName(trainer.getLastName())
                .username(username)
                .password(encodedPassword)
                .isActive(trainer.isActive())
                .specialization(trainer.getSpecialization())
                .build();

        trainerDao.save(trainerToSave);

        return trainerToSave.toBuilder()
                .password(rawPassword)
                .build();
    }

    public Trainer selectById(Long id) {
        return trainerDao.findById(id);
    }

    public void update(Trainer trainer) {
        trainerDao.update(trainer);
    }
}