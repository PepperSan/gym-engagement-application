package com.gym.engagement.app.storage;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.read.ListAppender;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.ResourceLoader;
import org.springframework.test.util.ReflectionTestUtils;

import static org.assertj.core.api.Assertions.assertThat;

class StorageDataInitializerLoggingTest {

    private static final String VALID_DATA_FILE = "classpath:initial-data.json";
    private static final String MISSING_DATA_FILE = "classpath:does-not-exist.json";

    private ListAppender<ILoggingEvent> logAppender;
    private StorageDataInitializer initializer;
    private CommonStorage commonStorage;

    @BeforeEach
    void setUp() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        ResourceLoader resourceLoader = new DefaultResourceLoader();

        initializer = new StorageDataInitializer(objectMapper, resourceLoader);
        commonStorage = new CommonStorage(new TraineeStorage(), new TrainerStorage(), new TrainingStorage());

        Logger logger = (Logger) LoggerFactory.getLogger(StorageDataInitializer.class);
        logAppender = new ListAppender<>();
        logAppender.start();
        logger.addAppender(logAppender);
    }

    @AfterEach
    void tearDown() {
        Logger logger = (Logger) LoggerFactory.getLogger(StorageDataInitializer.class);
        logger.detachAppender(logAppender);
    }

    @Test
    void shouldLogInfo_whenDataLoadedSuccessfully() {
        ReflectionTestUtils.setField(initializer, "dataSourceFile", VALID_DATA_FILE);

        initializer.postProcessAfterInitialization(commonStorage, "commonStorage");

        assertThat(logAppender.list).hasSize(1);
        assertThat(logAppender.list)
                .allMatch(event -> event.getLevel() == Level.INFO)
                .extracting(ILoggingEvent::getFormattedMessage)
                .containsExactly(String.format(
                        "Initial data loaded from %s: %d trainees, %d trainers, %d trainings",
                        VALID_DATA_FILE, 2, 1, 1));
    }

    @Test
    void shouldLogError_whenFileIsMissing() {
        ReflectionTestUtils.setField(initializer, "dataSourceFile", MISSING_DATA_FILE);

        initializer.postProcessAfterInitialization(commonStorage, "commonStorage");

        assertThat(logAppender.list).hasSize(1);
        assertThat(logAppender.list)
                .allMatch(event -> event.getLevel() == Level.ERROR)
                .extracting(ILoggingEvent::getFormattedMessage)
                .containsExactly(String.format("Failed to load initial data from %s", MISSING_DATA_FILE));
    }
}