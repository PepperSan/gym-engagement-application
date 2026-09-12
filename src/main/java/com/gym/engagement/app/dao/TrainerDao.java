package com.gym.engagement.app.dao;

import com.gym.engagement.app.domain.Trainer;

import java.util.Optional;

public interface TrainerDao {

    void save(Trainer trainer);

    Optional<Trainer> findById(Long id);

    void update(Trainer trainer);

    boolean existsByUsername(String username);
}