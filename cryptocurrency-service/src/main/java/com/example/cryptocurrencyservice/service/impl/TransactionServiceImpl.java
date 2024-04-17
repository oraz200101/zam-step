package com.example.cryptocurrencyservice.service.impl;

import com.example.cryptocurrencyservice.domain.entity.Transaction;
import com.example.cryptocurrencyservice.domain.entity.Wallet;
import com.example.cryptocurrencyservice.domain.repository.TransactionRepository;
import com.example.cryptocurrencyservice.domain.repository.WalletRepository;
import com.example.cryptocurrencyservice.exception.ElementNotFoundException;
import com.example.cryptocurrencyservice.exception.InputDataIsNullException;
import com.example.cryptocurrencyservice.model.TransactionRequestDto;
import com.example.cryptocurrencyservice.model.TransactionResponseDto;
import com.example.cryptocurrencyservice.service.ITransactionService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements ITransactionService {

    private final TransactionRepository transactionRepository;
    private final WalletRepository walletRepository;
    private final WalletServiceImpl walletService;
    private static final String TRANSACTION_NOT_FOUND = "Transaction not found";


    @Override
    public List<TransactionResponseDto> getAllTransactions() {
        return transactionRepository.findAll().stream()
                .map(this::mapFromEntityToDto)
                .toList();
    }

    @Override
    public TransactionResponseDto getTransactionById(Long id) {
        return mapFromEntityToDto(transactionRepository.findById(id)
                .orElseThrow(()-> new ElementNotFoundException(TRANSACTION_NOT_FOUND)));
    }

    @Override
    @SneakyThrows
    @Transactional
    public void createTransaction(TransactionRequestDto requestDto) {
        if (requestDto.getWalletRequestDto() == null) {
            throw new InputDataIsNullException("Wallet data is null. Write owner email");
        } else {
            if(!walletRepository.existsByOwnerEmail(requestDto.getWalletRequestDto().getOwnerEmail())) {
                walletRepository.save(walletService.mapFromDtoToEntity(requestDto.getWalletRequestDto()));
            }
        }

        Wallet wallet = walletRepository
                .findWalletByOwnerEmail(requestDto.getWalletRequestDto().getOwnerEmail()).get();

        Transaction transaction = Transaction.builder()
                .tokenAmount(new BigDecimal(requestDto.getStepsAmount()).divide(BigDecimal.TEN))
                .stepsAmount(requestDto.getStepsAmount())
                .wallet(wallet)
                .build();
        wallet.setBalance(wallet.getBalance().add(transaction.getTokenAmount()));
        transactionRepository.save(transaction);

    }


    public TransactionResponseDto mapFromEntityToDto(Transaction transaction) {
        return TransactionResponseDto.builder()
                .id(transaction.getTransactionId())
                .tokenAmount(transaction.getTokenAmount())
                .stepsAmount(transaction.getStepsAmount())
                .createdTime(transaction.getCreatedTime())
                .walletResponseDto(walletService.mapFromEntityToDto(transaction.getWallet()))
                .build();
    }
}
