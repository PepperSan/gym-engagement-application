package com.gym.engagement.app.dao.impl;

import com.gym.engagement.app.domain.Trainee;

public interface TraineeDao {

    void save(Trainee trainee);

    Trainee findById(Long id);

    void update(Trainee trainee);

    void deleteById(Long id);
}