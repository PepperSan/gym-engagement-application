package com.gym.engagement.app.storage;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gym.engagement.app.domain.Trainee;
import com.gym.engagement.app.domain.Trainer;
import com.gym.engagement.app.domain.Training;
import com.gym.engagement.app.domain.TrainingType;
import com.gym.engagement.app.storage.initdata.InitialDataDto;
import com.gym.engagement.app.storage.initdata.TraineeInitData;
import com.gym.engagement.app.storage.initdata.TrainerInitData;
import com.gym.engagement.app.storage.initdata.TrainingInitData;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.lang.NonNull;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;

@Slf4j
@Component
@RequiredArgsConstructor
public class StorageDataInitializer implements BeanPostProcessor {

    private final ObjectMapper objectMapper;
    private final ResourceLoader resourceLoader;

    @Value("${data.source.file}")
    private String dataSourceFile;

    @Override
    public Object postProcessAfterInitialization(@NonNull Object bean, @NonNull String beanName) throws BeansException {
        if (bean instanceof CommonStorage commonStorage) {
            loadInitialData(commonStorage);
        }
        return bean;
    }

    private void loadInitialData(CommonStorage commonStorage) {
        Resource resource = resourceLoader.getResource(dataSourceFile);

        try (InputStream inputStream = resource.getInputStream()) {
            InitialDataDto initialData = objectMapper.readValue(inputStream, InitialDataDto.class);

            for (TraineeInitData data : initialData.getTrainees()) {
                commonStorage.getTraineeStorage().getTrainees().put(data.getUserId(), toTrainee(data));
            }
            for (TrainerInitData data : initialData.getTrainers()) {
                commonStorage.getTrainerStorage().getTrainers().put(data.getUserId(), toTrainer(data));
            }
            for (TrainingInitData data : initialData.getTrainings()) {
                commonStorage.getTrainingStorage().getTrainings().put(data.getId(), toTraining(data));
            }

            log.info("Initial data loaded from {}: {} trainees, {} trainers, {} trainings",
                    dataSourceFile, initialData.getTrainees().size(),
                    initialData.getTrainers().size(), initialData.getTrainings().size());
        } catch (IOException e) {
            log.error("Failed to load initial data from {}", dataSourceFile, e);
        }
    }

    private Trainee toTrainee(TraineeInitData data) {
        return Trainee.builder()
                .userId(data.getUserId())
                .firstName(data.getFirstName())
                .lastName(data.getLastName())
                .username(data.getUsername())
                .password(data.getPassword())
                .isActive(data.isActive())
                .dateOfBirth(data.getDateOfBirth())
                .address(data.getAddress())
                .build();
    }

    private Trainer toTrainer(TrainerInitData data) {
        return Trainer.builder()
                .userId(data.getUserId())
                .firstName(data.getFirstName())
                .lastName(data.getLastName())
                .username(data.getUsername())
                .password(data.getPassword())
                .isActive(data.isActive())
                .specialization(new TrainingType(data.getSpecialization()))
                .build();
    }

    private Training toTraining(TrainingInitData data) {
        return Training.builder()
                .id(data.getId())
                .traineeId(data.getTraineeId())
                .trainerId(data.getTrainerId())
                .trainingName(data.getTrainingName())
                .trainingType(new TrainingType(data.getTrainingType()))
                .trainingDuration(data.getTrainingDuration())
                .trainingDate(data.getTrainingDate())
                .build();
    }
}