package com.example.healthservice.model;

import com.example.healthservice.domain.entity.HealthAnalysis;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.Locale;

@Getter
@Setter
public class HealthAnalysisWeekResponseDto {
    private String name;
    private int    value;

    public static HealthAnalysisWeekResponseDto ofWeek(HealthAnalysis healthAnalysis) {
        HealthAnalysisWeekResponseDto ret = new HealthAnalysisWeekResponseDto();

        ret.name  = healthAnalysis.getCurrentDate().getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.US);
        ret.value = healthAnalysis.getStepsAmount();

        return ret;
    }

    public static HealthAnalysisWeekResponseDto ofMonth(HealthAnalysis healthAnalysis) {
        HealthAnalysisWeekResponseDto ret = new HealthAnalysisWeekResponseDto();

        ret.name  = String.valueOf(healthAnalysis.getCurrentDate().getDayOfMonth());
        ret.value = healthAnalysis.getStepsAmount();

        return ret;
    }

    public static HealthAnalysisWeekResponseDto ofMonth(int dayOfMonth) {
        HealthAnalysisWeekResponseDto ret = new HealthAnalysisWeekResponseDto();

        ret.name  = String.valueOf(dayOfMonth);
        ret.value = 0;

        return ret;
    }

    public static HealthAnalysisWeekResponseDto ofWeek(LocalDate localDate) {
        HealthAnalysisWeekResponseDto ret = new HealthAnalysisWeekResponseDto();

        ret.name  = localDate.getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.US);
        ret.value = 0;

        return ret;
    }
}
