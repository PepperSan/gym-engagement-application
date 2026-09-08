package com.gym.engagement.app.dao.impl;

import com.gym.engagement.app.dao.TraineeDao;
import com.gym.engagement.app.domain.Trainee;
import com.gym.engagement.app.storage.TraineeStorage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class TraineeDaoImpl implements TraineeDao {

    private final TraineeStorage traineeStorage;

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
}