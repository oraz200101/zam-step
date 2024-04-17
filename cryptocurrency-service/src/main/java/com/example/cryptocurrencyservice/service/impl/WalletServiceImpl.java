package com.example.cryptocurrencyservice.service.impl;

import com.example.cryptocurrencyservice.domain.entity.Wallet;
import com.example.cryptocurrencyservice.domain.repository.WalletRepository;
import com.example.cryptocurrencyservice.exception.ElementAlreadyExistException;
import com.example.cryptocurrencyservice.exception.ElementNotFoundException;
import com.example.cryptocurrencyservice.model.WalletRequestDto;
import com.example.cryptocurrencyservice.model.WalletResponseDto;
import com.example.cryptocurrencyservice.service.IWalletService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.stereotype.Service;

import java.beans.PropertyDescriptor;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class WalletServiceImpl implements IWalletService {

    private final WalletRepository walletRepository;
    private static final String WALLET_NOT_FOUND = "Wallet not found in database";


    @Override
    public List<WalletResponseDto> getAllWallets() {
        return walletRepository.findAll().stream()
                .map(this::mapFromEntityToDto)
                .toList();
    }

    @Override
    public WalletResponseDto getWalletByUserEmail(String email) {
        return mapFromEntityToDto(walletRepository.findWalletByOwnerEmail(email)
                .orElseThrow(()-> new ElementNotFoundException(WALLET_NOT_FOUND)));
    }

    @Override
    public void createWallet(WalletRequestDto requestDto) {
        if(walletRepository.existsByOwnerEmail(requestDto.getOwnerEmail())) {
            throw new ElementAlreadyExistException("User with this email already exists!");
        }
        Wallet wallet = mapFromDtoToEntity(requestDto);
        wallet.setBalance(new BigDecimal("0.0"));
        walletRepository.save(wallet);
    }

    @Override
    @Transactional
    public WalletResponseDto patchWalletByUserEmail(String email, WalletRequestDto requestDto) {
        Wallet wallet = walletRepository.findWalletByOwnerEmail(email)
                .orElseThrow(() -> new ElementNotFoundException(WALLET_NOT_FOUND));
        System.out.println(wallet);
        BeanUtils.copyProperties(requestDto, wallet, getNullPropertyNames(requestDto));
        System.out.println(wallet);
        walletRepository.save(wallet);
        return mapFromEntityToDto(wallet);
    }


    @Override
    @Transactional
    public void deleteWalletByUserEmail(String email) {
        if(!walletRepository.existsByOwnerEmail(email)) {
            throw new ElementNotFoundException(WALLET_NOT_FOUND);
        }
        walletRepository.deleteWalletByOwnerEmail(email);
    }

    public Wallet mapFromDtoToEntity(WalletRequestDto dto) {
        return Wallet.builder()
                .ownerEmail(dto.getOwnerEmail())
                .address(dto.getAddress())
                .build();
    }

    public WalletResponseDto mapFromEntityToDto(Wallet wallet) {
        return WalletResponseDto.builder()
                .id(wallet.getWalletId())
                .ownerEmail(wallet.getOwnerEmail())
                .balance(wallet.getBalance())
                .address(wallet.getAddress())
                .build();
    }

    private String[] getNullPropertyNames(Object source) {
        final BeanWrapper src = new BeanWrapperImpl(source);
        Set<String> nullPropertyNames = new HashSet<>();
        for (PropertyDescriptor propertyDescriptor : src.getPropertyDescriptors()) {
            if (src.getPropertyValue(propertyDescriptor.getName()) == null) {
                nullPropertyNames.add(propertyDescriptor.getName());
            }
        }
        return nullPropertyNames.toArray(new String[0]);
    }


}
