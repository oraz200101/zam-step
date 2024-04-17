package com.example.cryptocurrencyservice.domain.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "wallets", schema = "cryptocurrency_schema")
public class Wallet {

    @Id
    @Column(name = "wallet_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long walletId;

    @Column(name = "owner_email", unique = true, nullable = false)
    private String ownerEmail;

    @Column(name = "balance")
    private BigDecimal balance;

    @Column(name = "address")
    private String address;

}
