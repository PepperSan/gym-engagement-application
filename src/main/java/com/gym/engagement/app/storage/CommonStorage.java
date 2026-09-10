package com.gym.engagement.app.storage;

import org.springframework.stereotype.Component;

import java.util.EnumMap;
import java.util.Map;

import static com.gym.engagement.app.storage.EntityType.TRAINEE;
import static com.gym.engagement.app.storage.EntityType.TRAINER;
import static com.gym.engagement.app.storage.EntityType.TRAINING;

@Component
public class CommonStorage {

    private final Map<EntityType, Storage> storages = new EnumMap<>(EntityType.class);

    public CommonStorage(TraineeStorage traineeStorage,
                         TrainerStorage trainerStorage,
                         TrainingStorage trainingStorage) {
        storages.put(TRAINEE, traineeStorage);
        storages.put(TRAINER, trainerStorage);
        storages.put(TRAINING, trainingStorage);
    }

    public TraineeStorage getTraineeStorage() {
        return (TraineeStorage) storages.get(TRAINEE);
    }

    public TrainerStorage getTrainerStorage() {
        return (TrainerStorage) storages.get(TRAINER);
    }

    public TrainingStorage getTrainingStorage() {
        return (TrainingStorage) storages.get(TRAINING);
    }
}