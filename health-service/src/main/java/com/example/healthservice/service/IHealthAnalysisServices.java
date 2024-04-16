package com.example.healthservice.service;

import com.example.healthservice.model.HealthAnalysisRequestDto;
import com.example.healthservice.model.HealthAnalysisResponseDto;

import java.util.List;

public interface IHealthAnalysisServices {

    List<HealthAnalysisResponseDto> getAllHealthAnalysis();

    HealthAnalysisResponseDto getHealthAnalysisById(Long id);
    void createHealthAnalysis(HealthAnalysisRequestDto requestDto);

}
