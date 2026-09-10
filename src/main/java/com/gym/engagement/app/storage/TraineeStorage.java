package com.gym.engagement.app.storage;

import com.gym.engagement.app.domain.Trainee;
import lombok.Getter;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Getter
@Component
public class TraineeStorage implements Storage {

    private final Map<Long, Trainee> trainees = new HashMap<>();
}