package com.gym.engagement.app.dao.impl;

import com.gym.engagement.app.dao.TrainingDao;
import com.gym.engagement.app.domain.Training;
import com.gym.engagement.app.storage.CommonStorage;
import com.gym.engagement.app.storage.TrainingStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class TrainingDaoImpl implements TrainingDao {

    private TrainingStorage trainingStorage;

    @Autowired
    public void setTrainingStorage(CommonStorage commonStorage) {
        this.trainingStorage = commonStorage.getTrainingStorage();
    }

    @Override
    public void save(Training training) {
        trainingStorage.getTrainings().put(training.getId(), training);
    }

    @Override
    public Training findById(Long id) {
        return trainingStorage.getTrainings().get(id);
    }
}