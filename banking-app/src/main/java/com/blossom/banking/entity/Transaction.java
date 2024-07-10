package com.blossom.banking.entity;

import com.blossom.banking.status.Status;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    @Column(name = "sender_id")
    private Long senderId;
    @Column(name = "sent_by")
    private String senderName;
    @Column(name = "beneficiary_id")
    private Long beneficiaryId;
    @Column(name = "received_by")
    private String beneficiaryName;
    @Column(name = "amount")
    private Double amount;
    @Enumerated(EnumType.STRING)
    @Column(name = "transaction_status")
    private Status transaction_status;
    @Column(name = "transaction_date")
    private LocalDateTime date;

    public Transaction(Long senderId, String senderName, Long beneficiaryId, String beneficiaryName, Double amount, Status transaction_status, LocalDateTime date) {
        this.senderId = senderId;
        this.senderName = senderName;
        this.beneficiaryId = beneficiaryId;
        this.beneficiaryName = beneficiaryName;
        this.amount = amount;
        this.transaction_status = transaction_status;
        this.date = date;
    }
}

