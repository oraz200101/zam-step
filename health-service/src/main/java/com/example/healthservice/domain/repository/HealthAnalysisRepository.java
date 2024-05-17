package com.example.healthservice.domain.repository;

import com.example.healthservice.domain.entity.HealthAnalysis;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface HealthAnalysisRepository extends MongoRepository<HealthAnalysis, String> {
    List<HealthAnalysis> findAllByEmail(String email);
    Optional<HealthAnalysis> findByEmail(String email);
}
