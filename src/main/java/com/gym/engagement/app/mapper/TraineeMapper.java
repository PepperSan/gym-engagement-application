package com.gym.engagement.app.mapper;

import com.gym.engagement.app.domain.Trainee;
import com.gym.engagement.app.dto.TraineeDTO;
import org.springframework.stereotype.Component;

@Component
public class TraineeMapper {

    private TraineeMapper() {
    }

    public TraineeDTO toDto(Trainee trainee) {
        if (trainee == null) {
            return null;
        }
        return TraineeDTO.builder()
                .userId(trainee.getUserId())
                .username(trainee.getUsername())
                .firstName(trainee.getFirstName())
                .lastName(trainee.getLastName())
                .password(trainee.getPassword())
                .isActive(trainee.isActive())
                .address(trainee.getAddress())
                .dateOfBirth(trainee.getDateOfBirth())
                .build();
    }

    public Trainee toEntity(TraineeDTO dto) {
        if (dto == null) {
            return null;
        }
        return Trainee.builder()
                .userId(dto.getUserId())
                .username(dto.getUsername())
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .password(dto.getPassword())
                .isActive(dto.isActive())
                .address(dto.getAddress())
                .dateOfBirth(dto.getDateOfBirth())
                .build();
    }
}