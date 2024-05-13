package com.example.healthservice.domain.repository;

import com.example.healthservice.domain.entity.HealthAnalysis;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HealthAnalysisRepository extends MongoRepository<HealthAnalysis, Long> {
    List<HealthAnalysis> findAllByEmail(String email);

}
