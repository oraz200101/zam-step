package com.example.healthservice.controller;

import com.example.healthservice.model.HealthAnalysisRequestDto;
import com.example.healthservice.model.HealthAnalysisResponseDto;
import com.example.healthservice.service.AuthService;
import com.example.healthservice.service.IHealthAnalysisServices;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/health-analysis")
@RequiredArgsConstructor
public class HealthAnalysisController {

    private final AuthService             authService;
    private final IHealthAnalysisServices healthAnalysisServices;

    @GetMapping("/all")
    public ResponseEntity<List<HealthAnalysisResponseDto>> getAllHealthAnalysisByEmail() {
        return ResponseEntity.ok(healthAnalysisServices.getAllHealthAnalysis(authService.getAuthPrincipal().getEmail()));
    }

    @GetMapping
    public ResponseEntity<HealthAnalysisResponseDto> getHealthAnalysisById(@RequestParam String id) {
        return ResponseEntity.ok(healthAnalysisServices.getHealthAnalysisById(id));
    }

    @PostMapping
    public ResponseEntity<Void> createHealthAnalysis(@RequestBody HealthAnalysisRequestDto requestDto) {
        healthAnalysisServices.createHealthAnalysis(requestDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}
