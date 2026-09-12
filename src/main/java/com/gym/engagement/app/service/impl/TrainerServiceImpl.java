package com.gym.engagement.app.service.impl;

import com.gym.engagement.app.dao.TraineeDao;
import com.gym.engagement.app.dao.TrainerDao;
import com.gym.engagement.app.domain.Trainer;
import com.gym.engagement.app.service.TrainerService;
import com.gym.engagement.app.service.common.UserCredentialsManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class TrainerServiceImpl implements TrainerService {

    private final TrainerDao trainerDao;
    private final TraineeDao traineeDao;
    private final UserCredentialsManager credentialsManager;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Trainer create(Trainer trainer) {
        log.debug("Creating trainer: firstName={}, lastName={}", trainer.getFirstName(), trainer.getLastName());

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
        log.info("Trainer created: userId={}, username={}", trainerToSave.getUserId(), username);

        return Trainer.builder()
                .userId(trainerToSave.getUserId())
                .firstName(trainerToSave.getFirstName())
                .lastName(trainerToSave.getLastName())
                .username(trainerToSave.getUsername())
                .password(rawPassword)
                .isActive(trainerToSave.isActive())
                .specialization(trainerToSave.getSpecialization())
                .build();
    }

    @Override
    public Trainer selectById(Long id) {
        log.debug("Selecting trainer by id={}", id);
        Trainer trainer = trainerDao.findById(id);

        if (trainer == null) {
            log.warn("Trainer not found for id={}", id);
        }

        return trainer;
    }

    @Override
    public void update(Trainer trainer) {
        trainerDao.update(trainer);
        log.info("Trainer updated: userId={}", trainer.getUserId());
    }
}