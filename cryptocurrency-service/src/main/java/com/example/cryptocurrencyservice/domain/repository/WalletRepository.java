package com.example.cryptocurrencyservice.domain.repository;

import com.example.cryptocurrencyservice.domain.entity.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WalletRepository extends JpaRepository<Wallet, Long> {

    Optional<Wallet> findWalletByOwnerEmail(String email);
    boolean existsByOwnerEmail(String email);
    void deleteWalletByOwnerEmail(String email);

}
