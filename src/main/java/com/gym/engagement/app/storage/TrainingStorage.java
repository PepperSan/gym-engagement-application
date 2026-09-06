package com.gym.engagement.app.storage;

import com.gym.engagement.app.domain.Training;

import java.util.Map;

public class TrainingStorage {

    private final Storage storage;

    public TrainingStorage(Storage storage) {
        this.storage = storage;
    }

    @SuppressWarnings("unchecked")
    public Map<Long, Training> getTrainings() {
        return (Map<Long, Training>) (Map<?, ?>) storage.getStorage("trainings");
    }
}