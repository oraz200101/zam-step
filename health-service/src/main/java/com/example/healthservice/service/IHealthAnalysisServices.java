package com.example.healthservice.service;

import com.example.healthservice.model.HealthAnalysisRequestDto;
import com.example.healthservice.model.HealthAnalysisResponseDto;

import java.util.List;

public interface IHealthAnalysisServices {

    List<HealthAnalysisResponseDto> getAllHealthAnalysis(String email);

    HealthAnalysisResponseDto getHealthAnalysisById(String id);
    void createHealthAnalysis(HealthAnalysisRequestDto requestDto);

}
