package com.gym.engagement.app.dto;

import com.gym.engagement.app.domain.TrainingType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TrainerDTO {
    private Long userId;
    private String username;
    private String firstName;
    private String lastName;
    private String password;
    private boolean isActive;
    private TrainingType specialization;
}