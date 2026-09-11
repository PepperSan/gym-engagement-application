package com.gym.engagement.app.service.impl;

import com.gym.engagement.app.dao.TrainingDao;
import com.gym.engagement.app.domain.Training;
import com.gym.engagement.app.service.TrainingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TrainingServiceImpl implements TrainingService {

    private final TrainingDao trainingDao;

    @Override
    public void create(Training training) {
        trainingDao.save(training);
    }

    @Override
    public Training selectById(Long id) {
        return trainingDao.findById(id);
    }
}