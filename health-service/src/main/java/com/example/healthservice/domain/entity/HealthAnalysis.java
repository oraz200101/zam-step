package com.example.healthservice.domain.entity;


import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDateTime;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Document
public class HealthAnalysis {

    @Id
    private Long id;
    private String email;
    private Integer stepsAmount;
    private Double burnedCalories;
    private LocalDateTime createdTime;

}
