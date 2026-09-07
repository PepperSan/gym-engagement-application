package com.gym.engagement.app.service;

import com.gym.engagement.app.dao.impl.TrainingDao;
import com.gym.engagement.app.domain.Training;
import org.springframework.stereotype.Service;

@Service
public class TrainingService {

    private final TrainingDao trainingDao;

    public TrainingService(TrainingDao trainingDao) {
        this.trainingDao = trainingDao;
    }

    public void create(Training training) {
        trainingDao.save(training);
    }

    public Training selectById(Long id) {
        return trainingDao.findById(id);
    }
}
