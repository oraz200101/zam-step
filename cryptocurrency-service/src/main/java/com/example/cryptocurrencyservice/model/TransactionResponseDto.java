package com.example.cryptocurrencyservice.model;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
public class TransactionResponseDto {
    private Long              id;
    private BigDecimal        tokenAmount;
    private Integer           stepsAmount;
    private LocalDateTime     createdTime;
    private WalletResponseDto walletResponseDto;
}
