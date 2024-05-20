package com.example.healthservice.config;

import com.bigchaindb.builders.BigchainDbConfigBuilder;
import com.bigchaindb.constants.BigchainDbApi;
import com.bigchaindb.model.BigChainDBGlobals;
import jakarta.annotation.PostConstruct;
import net.i2p.crypto.eddsa.KeyPairGenerator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.security.KeyPair;

@Configuration
public class BigchainDbConfig {

    @Value("${bigchaindb.URI}")
    private static String bigchain_URI;

    @PostConstruct
    public static void setupConnection() {
        // Установка конфигурации BigchainDB
        BigchainDbConfigBuilder
                .baseUrl(bigchain_URI) // URL-адрес узла BigchainDB
                .setup(); // Установка конфигурации

        // Установка API-адреса BigchainDB (необязательно, но может быть полезно для настройки пользовательского API)
        //BigChainDBGlobals.setWebsocketUrl(BIGCHAINDB_URL + BigchainDbApi.TRANSACTIONS_SOCKET);
    }

    @Bean
    public KeyPair keyPair() {
        KeyPairGenerator edDsaKpg = new KeyPairGenerator();
        return edDsaKpg.generateKeyPair();
    }

}
