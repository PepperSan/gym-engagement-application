package com.gym.engagement.app.dao;

import com.gym.engagement.app.domain.Trainee;

import java.util.Optional;

public interface TraineeDao {

    void save(Trainee trainee);

    Optional<Trainee> findById(Long id);

    void update(Trainee trainee);

    void deleteById(Long id);

    boolean existsByUsername(String username);
}