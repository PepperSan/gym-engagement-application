package com.gym.engagement.app.dao;

import com.gym.engagement.app.domain.Trainer;

public interface TrainerDao {

    void save(Trainer trainer);

    Trainer findById(Long id);

    void update(Trainer trainer);

}