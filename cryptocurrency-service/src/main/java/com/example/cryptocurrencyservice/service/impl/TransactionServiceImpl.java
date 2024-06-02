package com.example.cryptocurrencyservice.service.impl;

import com.example.cryptocurrencyservice.domain.entity.Transaction;
import com.example.cryptocurrencyservice.domain.entity.Wallet;
import com.example.cryptocurrencyservice.domain.repository.TransactionRepository;
import com.example.cryptocurrencyservice.domain.repository.WalletRepository;
import com.example.cryptocurrencyservice.exception.ElementNotFoundException;
import com.example.cryptocurrencyservice.exception.InputDataIsNullException;
import com.example.cryptocurrencyservice.model.TransactionRequestDto;
import com.example.cryptocurrencyservice.model.TransactionResponseDto;
import com.example.cryptocurrencyservice.service.AuthService;
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

    private final        TransactionRepository transactionRepository;
    private final        WalletRepository      walletRepository;
    private final        WalletServiceImpl     walletService;
    private final        AuthService           authService;
    private static final String                TRANSACTION_NOT_FOUND = "Transaction not found";


    @Override
    public List<TransactionResponseDto> getAllTransactionsByOwnerEmail() {
        return transactionRepository.getTransactionsByWalletOwnerEmail(authService.getAuthPrincipal().getEmail()).stream()
                                    .map(this::mapFromEntityToDto)
                                    .toList();
    }

    @Override
    public TransactionResponseDto getTransactionById(Long id) {
        return mapFromEntityToDto(transactionRepository.findById(id)
                                                       .orElseThrow(() -> new ElementNotFoundException(TRANSACTION_NOT_FOUND)));
    }

    @Override
    @SneakyThrows
    @Transactional
    public void createTransaction(TransactionRequestDto requestDto) {
        String email = authService.getAuthPrincipal().getEmail();

        if (!walletRepository.existsByOwnerEmail(email)) {
            walletRepository.save(walletService.mapFromDtoToEntity(email));
        }


        Wallet wallet = walletRepository.findWalletByOwnerEmail(authService.getAuthPrincipal().getEmail())
                                        .orElseThrow(() -> new ElementNotFoundException("wallet was not found"));

        Transaction transaction = Transaction.builder()
                                             .tokenAmount(new BigDecimal(requestDto.getStepsAmount()).divide(BigDecimal.TEN))
                                             .stepsAmount(requestDto.getStepsAmount())
                                             .wallet(wallet)
                                             .build();

        BigDecimal balance = wallet.getBalance();
        if (balance == null){
            balance = BigDecimal.valueOf(0L);
        }

        wallet.setBalance(balance.add(transaction.getTokenAmount()));
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
