package com.gym.engagement.app.storage;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.ResourceLoader;
import org.springframework.test.util.ReflectionTestUtils;

import static org.assertj.core.api.Assertions.assertThat;

class StorageDataInitializerTest {

    private static final String VALID_DATA_FILE = "classpath:initial-data.json";
    private static final String MISSING_DATA_FILE = "classpath:does-not-exist.json";

    private final ObjectMapper objectMapper = buildObjectMapper();
    private final ResourceLoader resourceLoader = new DefaultResourceLoader();

    private TraineeStorage traineeStorage;
    private TrainerStorage trainerStorage;
    private TrainingStorage trainingStorage;
    private CommonStorage commonStorage;
    private StorageDataInitializer initializer;

    @BeforeEach
    void setUp() {
        traineeStorage = new TraineeStorage();
        trainerStorage = new TrainerStorage();
        trainingStorage = new TrainingStorage();
        commonStorage = new CommonStorage(traineeStorage, trainerStorage, trainingStorage);
        initializer = new StorageDataInitializer(objectMapper, resourceLoader);
    }

    @Test
    void postProcessAfterInitialization_shouldPopulateStorage_whenFileIsValid() {
        ReflectionTestUtils.setField(initializer, "dataSourceFile", VALID_DATA_FILE);

        initializer.postProcessAfterInitialization(commonStorage, "commonStorage");

        assertThat(traineeStorage.getTrainees()).hasSize(2);
        assertThat(trainerStorage.getTrainers()).hasSize(1);
        assertThat(trainingStorage.getTrainings()).hasSize(1);
        assertThat(traineeStorage.getTrainees().get(1L).getUsername()).isEqualTo("Alex.Kovalenko");
    }

    @Test
    void postProcessAfterInitialization_shouldIgnoreOtherBeans() {
        Object otherBean = new Object();

        Object actual = initializer.postProcessAfterInitialization(otherBean, "someOtherBean");

        assertThat(actual).isSameAs(otherBean);
        assertThat(traineeStorage.getTrainees()).isEmpty();
    }

    @Test
    void postProcessAfterInitialization_shouldNotThrow_whenFileIsMissing() {
        ReflectionTestUtils.setField(initializer, "dataSourceFile", MISSING_DATA_FILE);

        initializer.postProcessAfterInitialization(commonStorage, "commonStorage");

        assertThat(traineeStorage.getTrainees()).isEmpty();
    }

    private static ObjectMapper buildObjectMapper() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        return mapper;
    }
}