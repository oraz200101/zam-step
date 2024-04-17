package com.example.healthservice.service.impl;

import com.example.healthservice.model.HealthAnalysisResponseDto;
import com.example.healthservice.service.IHealthAnalysisServices;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HealthAnalysisImpl implements IHealthAnalysisServices {

    @Override
    public List<HealthAnalysisResponseDto> getAllHealthAnalysis() {
        return null;
    }
}
