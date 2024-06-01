package com.example.healthservice.domain.repository;

import com.example.healthservice.domain.entity.HealthAnalysis;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface HealthAnalysisRepository extends MongoRepository<HealthAnalysis, String> {
    List<HealthAnalysis> findAllByEmail(String email);
    Optional<HealthAnalysis> findByEmail(String email);

    @Query("{'email': ?0, 'currentDate': ?1}")
    Optional<HealthAnalysis> findByEmailAndCurrentDate(String email, LocalDate currentDate);

    @Query("{'email': ?0, 'currentDate': {$gte: ?1, $lte: ?2}}")
    List<HealthAnalysis> findByEmailAndCurrentWeek(String email, LocalDate startOfWeek, LocalDate endOfWeek);

    @Query("{'email': ?0, 'currentDate': {$gte: ?1, $lte: ?2}}")
    List<HealthAnalysis> findByEmailAndCurrentMonth(String email, LocalDate startOfMonth, LocalDate endOfMonth);

}
