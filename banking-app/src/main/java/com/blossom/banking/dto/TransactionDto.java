package com.blossom.banking.dto;

import com.blossom.banking.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data //for the auto getters and setters methods
@AllArgsConstructor


public class TransactionDto {

    private Long transactionId;
    private String senderAccount;
    private String senderName;
    private String beneficiaryAccount;
    private String beneficiaryName;
    private Double amount;
    private Status transaction_status;
    private LocalDateTime date;

    public TransactionDto(Long senderId, String senderName, Long beneficiaryId, String beneficiaryName, Double amount, Status transaction_status) {
        this.senderAccount = senderAccount;
        this.senderName = senderName;
        this.beneficiaryAccount = beneficiaryAccount;
        this.beneficiaryName = beneficiaryName;
        this.amount = amount;
        this.transaction_status = transaction_status;
    }
}
