package com.example.authserver.repository;

import com.example.authserver.models.entities.AuthEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthRepository extends JpaRepository<AuthEntity, Long> {

    @Query("select a from AuthEntity a where a.email = ?1")
    Optional<AuthEntity> findByEmail(String email);
}
