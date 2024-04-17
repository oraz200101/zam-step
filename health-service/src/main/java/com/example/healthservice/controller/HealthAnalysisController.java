package com.example.healthservice.controller;

import com.example.healthservice.model.HealthAnalysisResponseDto;
import com.example.healthservice.service.IHealthAnalysisServices;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/health-analysis")
@RequiredArgsConstructor
public class HealthAnalysisController {

    private final IHealthAnalysisServices healthAnalysisServices;

    @GetMapping
    public ResponseEntity<List<HealthAnalysisResponseDto>> getAllHealthAnalysis() {
        return ResponseEntity.ok(healthAnalysisServices.getAllHealthAnalysis());
    }



}
