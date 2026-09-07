package com.gym.engagement.app.storage;

import com.gym.engagement.app.domain.Trainer;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class TrainerStorage {

    private final Map<Long, Trainer> trainers = new HashMap<>();

    public Map<Long, Trainer> getTrainers() {
        return trainers;
    }
}