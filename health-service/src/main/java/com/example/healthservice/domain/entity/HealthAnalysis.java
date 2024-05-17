package com.example.healthservice.domain.entity;


import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Document
public class HealthAnalysis {

    @Id
    private ObjectId      id;
    private String        email;
    private Integer       stepsAmount;
    private Double        burnedCalories;
    private LocalDateTime createdTime;

}
