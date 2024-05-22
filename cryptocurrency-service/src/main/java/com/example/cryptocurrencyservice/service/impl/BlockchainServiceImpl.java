package com.example.cryptocurrencyservice.service.impl;

import com.example.cryptocurrencyservice.domain.entity.ZamToken;
import com.example.cryptocurrencyservice.exception.ElementNotFoundException;
import com.example.cryptocurrencyservice.exception.NotEnoughGasException;
import com.example.cryptocurrencyservice.service.IBlockchainService;
import java.math.BigInteger;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tx.RawTransactionManager;
import org.web3j.tx.ReadonlyTransactionManager;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.gas.DefaultGasProvider;

@Service
@RequiredArgsConstructor
public class BlockchainServiceImpl implements IBlockchainService {

  private final Web3j web3j;

  @Value("${crypto.evm.contractAddress}")
  private String contractAddress;

  @Value("${crypto.evm.privateKey}")
  private String privateKey;
  private static final BigInteger DECIMALS = new BigInteger("1000000000000000000");


  @Override
  public Map<String, String> transferTokens(String toAddress, BigInteger amount) {
    Credentials credentials = Credentials.create(privateKey);
    TransactionManager transactionManager = new RawTransactionManager(web3j, credentials);
    ZamToken contract = ZamToken.load(contractAddress, web3j, transactionManager, new DefaultGasProvider());
    try {
      TransactionReceipt transactionReceipt = contract.transfer(toAddress, amount.multiply(DECIMALS)).send();
      return Map.of("Transaction Hash", transactionReceipt.getTransactionHash());
    } catch (Exception e) {
      throw new NotEnoughGasException(e.getMessage());
    }
  }

  @Override
  public BigInteger getBalance(String ownerAddress) {
    ZamToken contract = ZamToken.load(contractAddress, web3j, new ReadonlyTransactionManager(web3j, ownerAddress), new DefaultGasProvider());
    try {
      return contract.balanceOf(ownerAddress).send().divide(DECIMALS);
    } catch (Exception e) {
      throw new ElementNotFoundException(e.getMessage());
    }
  }
}
