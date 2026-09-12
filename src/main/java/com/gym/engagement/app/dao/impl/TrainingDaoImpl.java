package com.gym.engagement.app.dao.impl;

import com.gym.engagement.app.dao.TrainingDao;
import com.gym.engagement.app.domain.Training;
import com.gym.engagement.app.storage.CommonStorage;
import com.gym.engagement.app.storage.TrainingStorage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Slf4j
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
        log.debug("Training saved in storage: id={}", training.getId());
    }

    @Override
    public Training findById(Long id) {
        Training training = trainingStorage.getTrainings().get(id);
        log.debug("Training lookup by id={}: found={}", id, training != null);

        return training;
    }
}