package com.example.cryptocurrencyservice.controller;

import com.example.cryptocurrencyservice.model.TransactionRequestDto;
import com.example.cryptocurrencyservice.model.TransactionResponseDto;
import com.example.cryptocurrencyservice.service.ITransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/transactions")
public class TransactionController {

    private final ITransactionService transactionService;

    @GetMapping
    public ResponseEntity<List<TransactionResponseDto>> getAllTransactionsByOwnerEmail() {
        List<TransactionResponseDto> transactions = transactionService.getAllTransactionsByOwnerEmail();
        if (transactions.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(transactions);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TransactionResponseDto> getTransactionById(@PathVariable Long id) {
        return ResponseEntity.ok(transactionService.getTransactionById(id));
    }

    @PostMapping
    public ResponseEntity<Void> createTransaction(@RequestBody TransactionRequestDto requestDto) {
        transactionService.createTransaction(requestDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }



}
