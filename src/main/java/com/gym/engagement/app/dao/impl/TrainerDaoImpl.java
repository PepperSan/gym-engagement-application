package com.gym.engagement.app.dao.impl;

import com.gym.engagement.app.dao.TrainerDao;
import com.gym.engagement.app.domain.Trainer;
import com.gym.engagement.app.storage.CommonStorage;
import com.gym.engagement.app.storage.TrainerStorage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
public class TrainerDaoImpl implements TrainerDao {

    private TrainerStorage trainerStorage;

    @Autowired
    public void setTrainerStorage(CommonStorage commonStorage) {
        this.trainerStorage = commonStorage.getTrainerStorage();
    }

    @Override
    public void save(Trainer trainer) {
        trainerStorage.getTrainers().put(trainer.getUserId(), trainer);
        log.debug("Trainer saved in storage: userId={}", trainer.getUserId());
    }

    @Override
    public Trainer findById(Long id) {
        Trainer trainer = trainerStorage.getTrainers().get(id);
        log.debug("Trainer lookup by id={}: found={}", id, trainer != null);

        return trainer;
    }

    @Override
    public void update(Trainer trainer) {
        trainerStorage.getTrainers().put(trainer.getUserId(), trainer);
        log.debug("Trainer updated in storage: userId={}", trainer.getUserId());
    }

    @Override
    public boolean existsByUsername(String username) {
        boolean exists = trainerStorage.getTrainers().values().stream()
                .anyMatch(trainer -> trainer.getUsername().equals(username));
        log.debug("Username existence check: username={}, exists={}", username, exists);

        return exists;
    }
}