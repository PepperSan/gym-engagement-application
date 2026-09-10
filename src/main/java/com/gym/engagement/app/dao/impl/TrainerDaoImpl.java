package com.gym.engagement.app.dao.impl;

import com.gym.engagement.app.dao.TrainerDao;
import com.gym.engagement.app.domain.Trainer;
import com.gym.engagement.app.storage.CommonStorage;
import com.gym.engagement.app.storage.TrainerStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

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
    }

    @Override
    public Trainer findById(Long id) {
        return trainerStorage.getTrainers().get(id);
    }

    @Override
    public void update(Trainer trainer) {
        trainerStorage.getTrainers().put(trainer.getUserId(), trainer);
    }
}