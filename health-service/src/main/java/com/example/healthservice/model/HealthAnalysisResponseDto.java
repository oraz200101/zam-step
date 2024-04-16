package com.example.healthservice.model;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class HealthAnalysisResponseDto {

    private String email;
    private Integer stepsAmount;
    private Double burnedCalories;
    private LocalDateTime createdTime;

}
