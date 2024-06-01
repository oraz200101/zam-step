package com.example.cryptocurrencyservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WalletRequestDto {

    private String ownerEmail;
    private String address;

    public static WalletRequestDto of(String ownerEmail) {
        WalletRequestDto walletRequestDto = new WalletRequestDto();
        walletRequestDto.ownerEmail = ownerEmail;

        return walletRequestDto;
    }

}
