package com.gym.engagement.app.service;

import com.gym.engagement.app.dao.TraineeDao;
import com.gym.engagement.app.domain.Trainee;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TraineeService {

    private final TraineeDao traineeDao;

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