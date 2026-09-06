package com.gym.engagement.app.dao;

import com.gym.engagement.app.domain.Training;

public interface TrainingDao {

    void save(Training training);

    Training findById(Long id);

    void update(Training training);

    void deleteById(Long id);
}