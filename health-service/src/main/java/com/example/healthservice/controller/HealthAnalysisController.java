package com.example.healthservice.controller;

import com.example.healthservice.model.HealthAnalysisRequestDto;
import com.example.healthservice.model.HealthAnalysisResponseDto;
import com.example.healthservice.model.HealthAnalysisWeekResponseDto;
import com.example.healthservice.service.AuthService;
import com.example.healthservice.service.IHealthAnalysisServices;
import lombok.RequiredArgsConstructor;
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

    @GetMapping("/week")
    public ResponseEntity<List<HealthAnalysisWeekResponseDto>> getAllHealthAnalysisForWeek() {
        return ResponseEntity.ok(healthAnalysisServices.getHealthAnalysisForCurrentWeek(authService.getAuthPrincipal().getEmail()));
    }

    @GetMapping("/month")
    public ResponseEntity<List<HealthAnalysisWeekResponseDto>> getAllHealthAnalysisForMonth() {
        return ResponseEntity.ok(
                healthAnalysisServices.getHealthAnalysisForCurrentMonth(authService.getAuthPrincipal().getEmail()));
    }

    @GetMapping("/today")
    public ResponseEntity<HealthAnalysisResponseDto> getHealthAnalysisByCurrentDate() {
        return ResponseEntity.ok(
                healthAnalysisServices.getHealthAnalysisByEmailAndLocalDate(authService.getAuthPrincipal().getEmail()));
    }

    @PostMapping("rnd")
    public void createRnd(@RequestParam("steps") int steps,
                          @RequestParam("calorie") double calorie,
                          @RequestParam("date") String date) {
        healthAnalysisServices.creatRnd(authService.getAuthPrincipal().getEmail(), steps, calorie, date);
    }

    @PostMapping
    public ResponseEntity<HealthAnalysisResponseDto> createHealthAnalysis(@RequestBody HealthAnalysisRequestDto requestDto) {
        return ResponseEntity.ok(
                healthAnalysisServices.createHealthAnalysis(authService.getAuthPrincipal().getEmail(), requestDto));

    }

}
