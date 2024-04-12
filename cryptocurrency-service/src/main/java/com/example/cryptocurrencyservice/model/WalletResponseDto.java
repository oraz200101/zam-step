package com.example.cryptocurrencyservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WalletResponseDto {

    private Long id;
    private String ownerEmail;
    private BigDecimal balance;
    private String address;

}
