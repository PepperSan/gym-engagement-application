package com.gym.engagement.app.dao.impl;

import com.gym.engagement.app.dao.TrainerDao;
import com.gym.engagement.app.domain.Trainer;
import com.gym.engagement.app.storage.TrainerStorage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class TrainerDaoImpl implements TrainerDao {

    private final TrainerStorage trainerStorage;

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