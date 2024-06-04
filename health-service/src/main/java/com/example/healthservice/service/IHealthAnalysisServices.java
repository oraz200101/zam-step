package com.example.healthservice.service;

import com.example.healthservice.model.HealthAnalysisRequestDto;
import com.example.healthservice.model.HealthAnalysisResponseDto;
import com.example.healthservice.model.HealthAnalysisWeekResponseDto;

import java.math.BigDecimal;
import java.util.List;

public interface IHealthAnalysisServices {

    List<HealthAnalysisResponseDto> getAllHealthAnalysis(String email);

    HealthAnalysisResponseDto getHealthAnalysisById(String id);

    HealthAnalysisResponseDto getHealthAnalysisByEmailAndLocalDate(String email);

    void creatRnd(String email, int stepsCount, double calorie, String date);

    HealthAnalysisResponseDto createHealthAnalysis(String email, HealthAnalysisRequestDto requestDto);

    List<HealthAnalysisWeekResponseDto> getHealthAnalysisForCurrentWeek(String email);

    List<HealthAnalysisWeekResponseDto> getHealthAnalysisForCurrentMonth(String email);
}
