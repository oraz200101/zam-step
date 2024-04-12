package com.example.cryptocurrencyservice.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TransactionRequestDto {
    private Integer stepsAmount;
    private WalletRequestDto walletRequestDto;
}
