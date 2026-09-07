package com.gym.engagement.app.dao.impl;

import com.gym.engagement.app.domain.Training;

public interface TrainingDao {

    void save(Training training);

    Training findById(Long id);

}