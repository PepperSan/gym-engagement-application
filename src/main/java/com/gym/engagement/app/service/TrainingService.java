package com.gym.engagement.app.service;

import com.gym.engagement.app.domain.Training;

public interface TrainingService {

    void create(Training training);

    Training selectById(Long id);
}