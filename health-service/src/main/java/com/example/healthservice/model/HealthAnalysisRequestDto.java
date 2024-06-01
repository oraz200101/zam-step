package com.example.healthservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HealthAnalysisRequestDto {
    private int    stepsAmount;
    private Double weightInKg;
    private Double heightInCm;
    private Double timeInMinutes;

    public static HealthAnalysisRequestDto of() {
        HealthAnalysisRequestDto healthAnalysisRequestDto = new HealthAnalysisRequestDto();
        healthAnalysisRequestDto.stepsAmount   = 0;
        healthAnalysisRequestDto.weightInKg    = 0.0;
        healthAnalysisRequestDto.heightInCm    = 0.0;
        healthAnalysisRequestDto.timeInMinutes = 0.0;

        return healthAnalysisRequestDto;
    }
}
