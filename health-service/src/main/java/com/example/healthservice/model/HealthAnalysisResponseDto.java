package com.example.healthservice.model;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class HealthAnalysisResponseDto {
    private String    id;
    private String    email;
    private Integer   stepsAmount;
    private Double    burnedCalories;
    private LocalDate created;

}
