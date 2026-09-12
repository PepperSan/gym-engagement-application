package com.gym.engagement.app.dao.impl;

import com.gym.engagement.app.dao.TraineeDao;
import com.gym.engagement.app.domain.Trainee;
import com.gym.engagement.app.storage.CommonStorage;
import com.gym.engagement.app.storage.TraineeStorage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
public class TraineeDaoImpl implements TraineeDao {

    private TraineeStorage traineeStorage;

    @Autowired
    public void setTraineeStorage(CommonStorage commonStorage) {
        this.traineeStorage = commonStorage.getTraineeStorage();
    }

    @Override
    public void save(Trainee trainee) {
        traineeStorage.getTrainees().put(trainee.getUserId(), trainee);
        log.debug("Trainee saved in storage: userId={}", trainee.getUserId());
    }

    @Override
    public Trainee findById(Long id) {
        Trainee trainee = traineeStorage.getTrainees().get(id);
        log.debug("Trainee lookup by id={}: found={}", id, trainee != null);

        return trainee;
    }

    @Override
    public void update(Trainee trainee) {
        traineeStorage.getTrainees().put(trainee.getUserId(), trainee);
        log.debug("Trainee updated in storage: userId={}", trainee.getUserId());
    }

    @Override
    public void deleteById(Long id) {
        traineeStorage.getTrainees().remove(id);
        log.debug("Trainee removed from storage: userId={}", id);
    }

    @Override
    public boolean existsByUsername(String username) {
        boolean exists = traineeStorage.getTrainees().values().stream()
                .anyMatch(trainee -> trainee.getUsername().equals(username));
        log.debug("Username existence check: username={}, exists={}", username, exists);

        return exists;
    }
}