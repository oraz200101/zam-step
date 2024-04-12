package com.example.cryptocurrencyservice.service;

import com.example.cryptocurrencyservice.model.TransactionRequestDto;
import com.example.cryptocurrencyservice.model.TransactionResponseDto;

import java.util.List;

public interface ITransactionService {

    List<TransactionResponseDto> getAllTransactions();
    TransactionResponseDto getTransactionById(Long id);
    void createTransaction(TransactionRequestDto requestDto);

}
