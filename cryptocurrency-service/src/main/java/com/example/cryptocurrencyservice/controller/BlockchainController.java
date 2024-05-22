package com.example.cryptocurrencyservice.controller;

import com.example.cryptocurrencyservice.service.IBlockchainService;
import java.math.BigInteger;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/token")
@RequiredArgsConstructor
public class BlockchainController {

  private final IBlockchainService blockchainService;

  @PostMapping("/transfer")
  public ResponseEntity<Map<String, String>> transferTokens(@RequestParam String toAddress, @RequestParam BigInteger amount) {
    return ResponseEntity.ok(blockchainService.transferTokens(toAddress, amount));
  }

  @GetMapping("/balance")
  public ResponseEntity<BigInteger> getBalance(@RequestParam String ownerAddress) {
    return ResponseEntity.ok(blockchainService.getBalance(ownerAddress));
  }

}
