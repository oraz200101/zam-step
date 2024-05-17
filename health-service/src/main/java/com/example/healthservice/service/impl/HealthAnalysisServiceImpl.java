package com.example.healthservice.service.impl;

import com.example.healthservice.domain.entity.HealthAnalysis;
import com.example.healthservice.domain.repository.HealthAnalysisBigchainDbRepository;
import com.example.healthservice.domain.repository.HealthAnalysisRepository;
import com.example.healthservice.model.HealthAnalysisRequestDto;
import com.example.healthservice.model.HealthAnalysisResponseDto;
import com.example.healthservice.service.AuthService;
import com.example.healthservice.service.IHealthAnalysisServices;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class HealthAnalysisServiceImpl implements IHealthAnalysisServices {

    private final AuthService                        authService;
    private final HealthAnalysisBigchainDbRepository bigchainDbRepository;
    private final HealthAnalysisRepository           repository;

    @Override
    public List<HealthAnalysisResponseDto> getAllHealthAnalysis(String email) {
        return repository.findAllByEmail(email).stream().map(this::mapFromEntityToDto).toList();
    }

    @Override
    public HealthAnalysisResponseDto getHealthAnalysisById(String id) {
        return mapFromEntityToDto(repository.findById(id)
                                          .orElseThrow(() -> new RuntimeException("Entity with ID: " + id + " not found")));
    }

    @Override
    public void createHealthAnalysis(HealthAnalysisRequestDto requestDto) {
        double burnedCalories = 3.5 * ((requestDto.getStepsAmount() * 0.7) / 1000)
                * requestDto.getWeightInKg() * (requestDto.getTimeInMinutes() / 60);
        HealthAnalysis healthAnalysis = HealthAnalysis.builder()
                                                      .email(authService.getAuthPrincipal().getEmail())
                                                      .stepsAmount(requestDto.getStepsAmount())
                                                      .burnedCalories(burnedCalories)
                                                      .createdTime(LocalDateTime.now())
                                                      .build();
        bigchainDbRepository.saveData(healthAnalysis.toString());
        repository.save(healthAnalysis);
    }

    public HealthAnalysisResponseDto mapFromEntityToDto(HealthAnalysis healthAnalysis) {
        return HealthAnalysisResponseDto.builder()
                                        .id(healthAnalysis.getId().toString())
                                        .email(healthAnalysis.getEmail())
                                        .stepsAmount(healthAnalysis.getStepsAmount())
                                        .burnedCalories(healthAnalysis.getBurnedCalories())
                                        .createdTime(healthAnalysis.getCreatedTime())
                                        .build();
    }
}
