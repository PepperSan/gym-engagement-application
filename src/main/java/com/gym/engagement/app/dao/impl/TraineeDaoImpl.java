package com.gym.engagement.app.dao.impl;

import com.gym.engagement.app.dao.TraineeDao;
import com.gym.engagement.app.domain.Trainee;
import com.gym.engagement.app.storage.CommonStorage;
import com.gym.engagement.app.storage.TraineeStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

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
    }

    @Override
    public Trainee findById(Long id) {
        return traineeStorage.getTrainees().get(id);
    }

    @Override
    public void update(Trainee trainee) {
        traineeStorage.getTrainees().put(trainee.getUserId(), trainee);
    }

    @Override
    public void deleteById(Long id) {
        traineeStorage.getTrainees().remove(id);
    }

    @Override
    public boolean existsByUsername(String username) {
        return traineeStorage.getTrainees().values().stream()
                .anyMatch(trainee -> trainee.getUsername().equals(username));
    }
}