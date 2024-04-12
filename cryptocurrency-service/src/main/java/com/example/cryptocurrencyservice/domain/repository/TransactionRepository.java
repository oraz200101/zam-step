package com.example.cryptocurrencyservice.domain.repository;

import com.example.cryptocurrencyservice.domain.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}
