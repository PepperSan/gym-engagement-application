package com.gym.engagement.app.dao;

import com.gym.engagement.app.domain.Trainee;
import com.gym.engagement.app.storage.Storage;

public class TraineeDaoImpl implements TraineeDao {

    private final Storage storage;

    public TraineeDaoImpl(Storage storage) {
        this.storage = storage;
    }

    @Override
    public void save(Trainee trainee) {
        storage.getTrainees().put(trainee.getUserId(), trainee);
    }

    @Override
    public Trainee findById(Long id) {
        return storage.getTrainees().get(id);
    }

    @Override
    public void update(Trainee trainee) {
        storage.getTrainees().put(trainee.getUserId(), trainee);
    }

    @Override
    public void deleteById(Long id) {
        storage.getTrainees().remove(id);
    }
}