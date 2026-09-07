package com.gym.engagement.app.service;

import com.gym.engagement.app.dao.impl.TrainerDao;
import com.gym.engagement.app.domain.Trainer;
import org.springframework.stereotype.Service;

@Service
public class TrainerService {

    private final TrainerDao trainerDao;

    public TrainerService(TrainerDao trainerDao) {
        this.trainerDao = trainerDao;
    }

    public void create(Trainer trainer) {
        trainerDao.save(trainer);
    }

    public Trainer selectById(Long id) {
        return trainerDao.findById(id);
    }

    public void update(Trainer trainer) {
        trainerDao.update(trainer);
    }

}