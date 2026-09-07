package com.gym.engagement.app.service;

import com.gym.engagement.app.dao.impl.TraineeDao;
import com.gym.engagement.app.domain.Trainee;
import org.springframework.stereotype.Service;

@Service
public class TraineeService {

    private final TraineeDao traineeDao;

    public TraineeService(TraineeDao traineeDao) {
        this.traineeDao = traineeDao;
    }

    public void create(Trainee trainee) {
        traineeDao.save(trainee);
    }

    public Trainee selectById(Long id) {
        return traineeDao.findById(id);
    }

    public void update(Trainee trainee) {
        traineeDao.update(trainee);
    }

    public void deleteById(Long id) {
        traineeDao.deleteById(id);
    }
}