package com.example.healthservice.service;

import com.example.healthservice.model.HealthAnalysisRequestDto;
import com.example.healthservice.model.HealthAnalysisResponseDto;

import java.util.List;

public interface IHealthAnalysisServices {

    List<HealthAnalysisResponseDto> getAllHealthAnalysis(String email);

    HealthAnalysisResponseDto getHealthAnalysisByEmail(String email);
    void createHealthAnalysis(HealthAnalysisRequestDto requestDto);

}
