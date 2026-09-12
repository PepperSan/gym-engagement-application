package com.gym.engagement.app.service.impl;

import com.gym.engagement.app.dao.TrainingDao;
import com.gym.engagement.app.domain.Training;
import com.gym.engagement.app.service.TrainingService;
import com.gym.engagement.app.service.exception.CoreServiceException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class TrainingServiceImpl implements TrainingService {

    private final TrainingDao trainingDao;

    @Override
    public void create(Training training) {
        trainingDao.save(training);
        log.info("Training created: id={}, name={}", training.getId(), training.getTrainingName());
    }

    @Override
    public Training selectById(Long id) {
        log.info("Selecting training by id={}", id);

        return trainingDao.findById(id)
                .orElseThrow(() -> {
                    log.warn("Training not found for id={}", id);
                    return new CoreServiceException(String.format("Training with id %s not found", id));
                });
    }
}