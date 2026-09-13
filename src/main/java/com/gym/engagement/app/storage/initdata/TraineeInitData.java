package com.gym.engagement.app.storage.initdata;

import lombok.Data;

import java.time.LocalDate;

@Data
public class TraineeInitData {
    private Long userId;
    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private boolean isActive;
    private LocalDate dateOfBirth;
    private String address;
}
