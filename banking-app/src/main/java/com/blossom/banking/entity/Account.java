package com.blossom.banking.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.repository.Query;

import java.sql.Date;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "accounts")
@Entity
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "account_number")
    private Long accountNumber;
    @Column(name = "account_holder_name")
    private String accountHolder;
    @Column(name = "account_holder_balance")
    private Double accountBalance;
    @Column(name = "reg_date")
    private LocalDateTime date;
}
