package com.gym.engagement.app.storage.initdata;

import lombok.Data;

@Data
public class TrainerInitData {
    private Long userId;
    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private boolean isActive;
    private String specialization;
}
