package com.gym.engagement.app.mapper;

import com.gym.engagement.app.domain.Trainer;
import com.gym.engagement.app.dto.TrainerDTO;
import org.springframework.stereotype.Component;

@Component
public class TrainerMapper {

    public TrainerDTO toDto(Trainer trainer) {
        if (trainer == null) {
            return null;
        }
        return TrainerDTO.builder()
                .userId(trainer.getUserId())
                .username(trainer.getUsername())
                .firstName(trainer.getFirstName())
                .lastName(trainer.getLastName())
                .password(trainer.getPassword())
                .isActive(trainer.isActive())
                .specialization(trainer.getSpecialization())
                .build();
    }

    public Trainer toEntity(TrainerDTO dto) {
        if (dto == null) {
            return null;
        }
        return Trainer.builder()
                .userId(dto.getUserId())
                .username(dto.getUsername())
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .password(dto.getPassword())
                .isActive(dto.isActive())
                .specialization(dto.getSpecialization())
                .build();
    }
}