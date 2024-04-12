package com.example.cryptocurrencyservice.domain.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "transactions", schema = "cryptocurrency_schema")
public class Transaction {

    @Id
    @Column(name = "transaction_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long transactionId;

    @Column(name = "token_amount")
    private BigDecimal tokenAmount;

    @Column(name = "steps_amount")
    private Integer stepsAmount;

    @Column(name = "created_time")
    private LocalDateTime createdTime;

    @ManyToOne(cascade = {CascadeType.MERGE,CascadeType.PERSIST})
    @JoinColumn(name = "wallet_id")
    private Wallet wallet;

    @PrePersist
    public void toCreate() {
        this.setCreatedTime(LocalDateTime.now());
    }

}
