package com.gym.engagement.app.storage;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class Storage {

    private final Map<String, Map<Long, Object>> storage = new HashMap<>();

    public Map<Long, Object> getStorage(String namespace) {
        return storage.computeIfAbsent(namespace, key -> new HashMap<>());
    }
}