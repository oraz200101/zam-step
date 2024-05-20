package com.example.cryptocurrencyservice.controller;

import com.example.cryptocurrencyservice.model.WalletRequestDto;
import com.example.cryptocurrencyservice.model.WalletResponseDto;
import com.example.cryptocurrencyservice.service.IWalletService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/wallets")
@RequiredArgsConstructor
public class WalletController {

    private final IWalletService walletService;

    @GetMapping("/all")
    public ResponseEntity<List<WalletResponseDto>> getAllWallets() {
        return ResponseEntity.ok(walletService.getAllWallets());
    }

    @GetMapping
    public ResponseEntity<WalletResponseDto> getWalletByUserEmail() {
        return ResponseEntity.ok(walletService.getWalletByUserEmail());
    }

    @PostMapping
    public ResponseEntity<Void> createWallet(@RequestBody WalletRequestDto requestDto) {
        walletService.createWallet(requestDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PatchMapping("/{email}")
    public ResponseEntity<WalletResponseDto> patchWalletByUserEmail(@PathVariable String email,
                                                                    @RequestBody WalletRequestDto requestDto) {
        return ResponseEntity.ok(walletService.patchWalletByUserEmail(email, requestDto));
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteWalletByUserEmail() {
        walletService.deleteWalletByUserEmail();
        return ResponseEntity.noContent().build();
    }

}
