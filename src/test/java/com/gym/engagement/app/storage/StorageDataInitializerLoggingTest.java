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

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class StorageDataInitializerLoggingTest {

    private static final String VALID_DATA_FILE = "classpath:initial-data.json";
    private static final String MISSING_DATA_FILE = "classpath:does-not-exist.json";

    private ListAppender<ILoggingEvent> listAppender;
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
        listAppender = new ListAppender<>();
        listAppender.start();
        logger.addAppender(listAppender);
    }

    @AfterEach
    void tearDown() {
        Logger logger = (Logger) LoggerFactory.getLogger(StorageDataInitializer.class);
        logger.detachAppender(listAppender);
    }

    @Test
    void shouldLogInfo_whenDataLoadedSuccessfully() {
        ReflectionTestUtils.setField(initializer, "dataSourceFile", VALID_DATA_FILE);

        initializer.postProcessAfterInitialization(commonStorage, "commonStorage");

        List<ILoggingEvent> infoLogs = listAppender.list.stream()
                .filter(event -> event.getLevel() == Level.INFO)
                .toList();
        assertThat(infoLogs).anyMatch(event ->
                event.getFormattedMessage().contains("Initial data loaded"));
    }

    @Test
    void shouldLogError_whenFileIsMissing() {
        ReflectionTestUtils.setField(initializer, "dataSourceFile", MISSING_DATA_FILE);

        initializer.postProcessAfterInitialization(commonStorage, "commonStorage");

        List<ILoggingEvent> errorLogs = listAppender.list.stream()
                .filter(event -> event.getLevel() == Level.ERROR)
                .toList();
        assertThat(errorLogs).anyMatch(event ->
                event.getFormattedMessage().contains("Failed to load initial data"));
    }
}