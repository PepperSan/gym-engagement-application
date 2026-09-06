package com.gym.engagement.app.storage;

import com.gym.engagement.app.domain.Trainer;

import java.util.Map;

public class TrainerStorage {

    private final Storage storage;

    public TrainerStorage(Storage storage) {
        this.storage = storage;
    }

    @SuppressWarnings("unchecked")
    public Map<Long, Trainer> getTrainers() {
        return (Map<Long, Trainer>) (Map<?, ?>) storage.getStorage("trainers");
    }
}