package com.example.healthservice.model;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Data
@Builder
public class HealthAnalysisRequestDto {
    private Long id;
    private String email;
    private int stepsAmount;
    private Double weightInKg;
    private Double heightInCm;
    private Double timeInMinutes;
}
