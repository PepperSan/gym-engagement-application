package com.gym.engagement.app.dao;

import com.gym.engagement.app.domain.Training;

import java.util.Optional;

public interface TrainingDao {

    void save(Training training);

    Optional<Training> findById(Long id);
}