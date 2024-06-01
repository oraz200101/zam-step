package com.example.healthservice.service.impl;

import com.example.healthservice.domain.entity.HealthAnalysis;
import com.example.healthservice.domain.repository.HealthAnalysisBigchainDbRepository;
import com.example.healthservice.domain.repository.HealthAnalysisRepository;
import com.example.healthservice.model.HealthAnalysisRequestDto;
import com.example.healthservice.model.HealthAnalysisResponseDto;
import com.example.healthservice.model.HealthAnalysisWeekResponseDto;
import com.example.healthservice.service.AuthService;
import com.example.healthservice.service.IHealthAnalysisServices;
import com.example.healthservice.util.DateUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
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
    public HealthAnalysisResponseDto getHealthAnalysisByEmailAndLocalDate(String email) {
        HealthAnalysis healthAnalysis = repository.findByEmailAndCurrentDate(email, LocalDate.now()).orElse(null);

        if (healthAnalysis == null) {
            return createHealthAnalysis(email, HealthAnalysisRequestDto.of());
        } else {
            return mapFromEntityToDto(healthAnalysis);
        }
    }

    @Override
    public void creatRnd(String email, int stepsCount, double calorie, String date) {
        HealthAnalysis newHealth = HealthAnalysis.builder()
                                                 .email(email)
                                                 .stepsAmount(stepsCount)
                                                 .burnedCalories(calorie)
                                                 .currentDate(DateUtil.parseToLocalDateTime(date))
                                                 .build();
        repository.save(newHealth);
    }

    @Override
    public HealthAnalysisResponseDto createHealthAnalysis(String email, HealthAnalysisRequestDto requestDto) {
        HealthAnalysis healthAnalysis = repository
                .findByEmailAndCurrentDate(email, LocalDate.now()).orElse(null);

        double burnedCalories = 3.5 * ((requestDto.getStepsAmount() * 0.7) / 1000)
                * requestDto.getWeightInKg() * (requestDto.getTimeInMinutes() / 60);

        if (healthAnalysis == null) {
            HealthAnalysis newHealth = HealthAnalysis.builder()
                                                     .email(authService.getAuthPrincipal().getEmail())
                                                     .stepsAmount(requestDto.getStepsAmount())
                                                     .burnedCalories(burnedCalories)
                                                     .currentDate(LocalDate.now())
                                                     .build();
            repository.save(newHealth);

            return mapFromEntityToDto(newHealth);
        } else {

            healthAnalysis.setStepsAmount(healthAnalysis.getStepsAmount() + requestDto.getStepsAmount());
            healthAnalysis.setBurnedCalories(healthAnalysis.getBurnedCalories() + burnedCalories);

            repository.save(healthAnalysis);

            return mapFromEntityToDto(healthAnalysis);
        }
    }

    @Override
    public List<HealthAnalysisWeekResponseDto> getHealthAnalysisForCurrentWeek(String email) {
        LocalDate today = LocalDate.now();
        LocalDate startOfPeriod = today.minusDays(6); // Last 7 days including today

        List<HealthAnalysis> healthAnalyses = repository.findByEmailAndCurrentMonth(email, startOfPeriod, today);

        List<HealthAnalysisWeekResponseDto> healthAnalysisWeekResponseDtos = new ArrayList<>(7);

        // Initialize the list with placeholder DTOs for each of the last 7 days
        for (int i = 0; i < 7; i++) {
            LocalDate date = startOfPeriod.plusDays(i);
            healthAnalysisWeekResponseDtos.add(HealthAnalysisWeekResponseDto.ofWeek(date));
        }

        // Map existing health analysis data to the corresponding days
        for (HealthAnalysis healthAnalysis : healthAnalyses) {
            LocalDate analysisDate = healthAnalysis.getCurrentDate();
            int index = (int) java.time.temporal.ChronoUnit.DAYS.between(startOfPeriod, analysisDate);
            healthAnalysisWeekResponseDtos.set(index, HealthAnalysisWeekResponseDto.ofWeek(healthAnalysis));
        }

        return healthAnalysisWeekResponseDtos;
    }

    @Override
    public List<HealthAnalysisWeekResponseDto> getHealthAnalysisForCurrentMonth(String email) {
        LocalDate today = LocalDate.now();
        LocalDate startOfPeriod = today.minusDays(30); // Last 31 days including today

        List<HealthAnalysis> healthAnalyses = repository.findByEmailAndCurrentMonth(email, startOfPeriod, today);

        List<HealthAnalysisWeekResponseDto> healthAnalysisWeekResponseDtos = new ArrayList<>(31);

        // Initialize the list with placeholder DTOs for each of the last 31 days
        for (int i = 0; i < 31; i++) {
            LocalDate date = startOfPeriod.plusDays(i);
            healthAnalysisWeekResponseDtos.add(HealthAnalysisWeekResponseDto.ofMonth(date.getDayOfMonth()));
        }

        // Map existing health analysis data to the corresponding days
        for (HealthAnalysis healthAnalysis : healthAnalyses) {
            LocalDate analysisDate = healthAnalysis.getCurrentDate();
            int index = (int) java.time.temporal.ChronoUnit.DAYS.between(startOfPeriod, analysisDate);
            healthAnalysisWeekResponseDtos.set(index, HealthAnalysisWeekResponseDto.ofMonth(healthAnalysis));
        }

        return healthAnalysisWeekResponseDtos;
    }


    public HealthAnalysisResponseDto mapFromEntityToDto(HealthAnalysis healthAnalysis) {
        return HealthAnalysisResponseDto.builder()
                                        .id(healthAnalysis.getId().toString())
                                        .email(healthAnalysis.getEmail())
                                        .stepsAmount(healthAnalysis.getStepsAmount())
                                        .burnedCalories(healthAnalysis.getBurnedCalories())
                                        .created(healthAnalysis.getCurrentDate())
                                        .build();
    }
}
