package com.gym.engagement.app.storage;

import com.gym.engagement.app.domain.Training;
import lombok.Getter;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Getter
@Component
public class TrainingStorage implements Storage {

    private final Map<Long, Training> trainings = new HashMap<>();
}