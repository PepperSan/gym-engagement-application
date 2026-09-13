package com.gym.engagement.app;

import com.gym.engagement.app.config.SpringConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@Slf4j
public class GymApplication {

    public static void main(String[] args) {
        try (AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class)) {
            log.info("Gym Engagement Application started successfully with {} beans registered",
                    context.getBeanDefinitionCount());
        }
    }
}