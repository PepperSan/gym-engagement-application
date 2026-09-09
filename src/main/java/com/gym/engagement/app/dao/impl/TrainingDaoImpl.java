package com.gym.engagement.app.dao.impl;

import com.gym.engagement.app.dao.TrainingDao;
import com.gym.engagement.app.domain.Training;
import com.gym.engagement.app.storage.TrainingStorage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class TrainingDaoImpl implements TrainingDao {

    private final TrainingStorage trainingStorage;

    @Override
    public void save(Training training) {
        trainingStorage.getTrainings().put(training.getId(), training);
    }

    @Override
    public Training findById(Long id) {
        return trainingStorage.getTrainings().get(id);
    }

}