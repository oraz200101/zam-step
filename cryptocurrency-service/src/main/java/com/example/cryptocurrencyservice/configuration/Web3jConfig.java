package com.example.cryptocurrencyservice.configuration;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;

@Configuration
public class Web3jConfig {

  @Value("${crypto.evm.url}")
  private String NODE_URL;

  @Bean
  public Web3j getConfigWeb3j() {
    return Web3j.build(new HttpService(NODE_URL));
  }

}
