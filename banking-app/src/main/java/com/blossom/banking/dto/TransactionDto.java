package com.blossom.banking.dto;

import com.blossom.banking.status.Status;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data //for the auto getters and setters methods
@AllArgsConstructor


public class TransactionDto {

    private Long transactionId;
    private Long senderId;
    private String senderName;
    private Long beneficiaryId;
    private String beneficiaryName;
    private Double amount;
    private Status transaction_status;
    private LocalDateTime date;

    public TransactionDto(Long senderId, String senderName, Long beneficiaryId, String beneficiaryName, Double amount, Status transaction_status, LocalDateTime date) {
        this.senderId = senderId;
        this.senderName = senderName;
        this.beneficiaryId = beneficiaryId;
        this.beneficiaryName = beneficiaryName;
        this.amount = amount;
        this.transaction_status = transaction_status;
        this.date = date;
    }
}
