package com.example.cryptocurrencyservice.service;

import com.example.cryptocurrencyservice.domain.entity.Wallet;
import com.example.cryptocurrencyservice.model.WalletRequestDto;
import com.example.cryptocurrencyservice.model.WalletResponseDto;

import java.util.List;

public interface IWalletService {

    List<WalletResponseDto> getAllWallets();

    WalletResponseDto getWalletByUserEmail();

    Wallet createWallet(WalletRequestDto requestDto);


    WalletResponseDto patchWalletByUserEmail(String email, WalletRequestDto requestDto);

    void deleteWalletByUserEmail();
}
