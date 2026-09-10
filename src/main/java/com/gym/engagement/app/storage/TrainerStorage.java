package com.gym.engagement.app.storage;

import com.gym.engagement.app.domain.Trainer;
import lombok.Getter;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Getter
@Component
public class TrainerStorage implements Storage {

    private final Map<Long, Trainer> trainers = new HashMap<>();
}