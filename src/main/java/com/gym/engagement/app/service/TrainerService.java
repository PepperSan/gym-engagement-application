package com.gym.engagement.app.service;

import com.gym.engagement.app.domain.Trainer;

public interface TrainerService {

    Trainer create(Trainer trainer);

    Trainer selectById(Long id);

    void update(Trainer trainer);
}