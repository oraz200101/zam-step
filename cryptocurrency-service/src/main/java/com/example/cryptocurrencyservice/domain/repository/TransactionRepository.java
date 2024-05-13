package com.example.cryptocurrencyservice.domain.repository;

import com.example.cryptocurrencyservice.domain.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    List<Transaction> getTransactionsByWalletOwnerEmail(String email);

}
