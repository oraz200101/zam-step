package com.example.cryptocurrencyservice.service;

import java.math.BigInteger;
import java.util.Map;

public interface IBlockchainService {

  Map<String, String> transferTokens(String toAddress, BigInteger amount);
  BigInteger getBalance(String ownerAddress);

}
