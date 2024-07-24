package com.blossom.banking.entity;

import com.blossom.banking.enums.Status;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "transactions")
@Entity
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "transaction_id")
    private Long transactionId;
    //    @JoinColumn(table = "accounts", foreignKey = senderId)
    @Column(name = "sender_account")
    private String senderAccount;
    @Column(name = "sent_by")
    private String senderName;
    @Column(name = "beneficiary_account")
    private String beneficiaryAccount;
    @Column(name = "received_by")
    private String beneficiaryName;
    @Column(name = "amount")
    private Double amount;
    @Enumerated(EnumType.STRING)
    @Column(name = "transaction_status")
    private Status transaction_status;
    @Column(name = "transaction_date")
    @CreationTimestamp
    private LocalDateTime date;

    public Transaction(String senderAccount, String senderName, String beneficiaryAccount, String beneficiaryName, Double amount, Status transaction_status) {
        this.senderAccount = senderAccount;
        this.senderName = senderName;
        this.beneficiaryAccount = beneficiaryAccount;
        this.beneficiaryName = beneficiaryName;
        this.amount = amount;
        this.transaction_status = transaction_status;
    }
}

