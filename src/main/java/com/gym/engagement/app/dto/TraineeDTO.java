package com.gym.engagement.app.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TraineeDTO {
    private Long userId;
    private String username;
    private String firstName;
    private String lastName;
    private String password;
    private boolean isActive;
    private String address;
    private LocalDate dateOfBirth;
}