package com.example.healthservice.config;

import com.bigchaindb.builders.BigchainDbConfigBuilder;
import net.i2p.crypto.eddsa.KeyPairGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.security.KeyPair;

@Configuration
public class BigchainDbConfig {

    private static final String BIGCHAINDB_URL = "http://localhost:9984";

    @Bean
    public BigchainDbConfig bigchainDbConfig() {
        KeyPairGenerator edDsaKpg = new KeyPairGenerator();
        KeyPair keyPair = edDsaKpg.generateKeyPair();

        BigchainDbConfigBuilder.baseUrl(BIGCHAINDB_URL)
                .addToken("id", "id")
                .addToken("key", "key")
                .setup();
        return new BigchainDbConfig();
    }

    @Bean
    public KeyPair keyPair() {
        KeyPairGenerator edDsaKpg = new KeyPairGenerator();
        return edDsaKpg.generateKeyPair();
    }

}
