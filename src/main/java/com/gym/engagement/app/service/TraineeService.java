package com.gym.engagement.app.service;

import com.gym.engagement.app.domain.Trainee;

public interface TraineeService {

    Trainee create(Trainee trainee);

    Trainee selectById(Long id);

    void update(Trainee trainee);

    void deleteById(Long id);
}