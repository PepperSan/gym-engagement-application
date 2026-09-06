package com.gym.engagement.app.storage;

import com.gym.engagement.app.domain.Trainee;

import java.util.Map;

public class TraineeStorage {

    private final Storage storage;

    public TraineeStorage(Storage storage) {
        this.storage = storage;
    }

    @SuppressWarnings("unchecked")
    public Map<Long, Trainee> getTrainees() {
        return (Map<Long, Trainee>) (Map<?, ?>) storage.getStorage("trainees");
    }
}