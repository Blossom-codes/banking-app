package com.blossom.banking.mapper;

import com.blossom.banking.dto.TransactionDto;
import com.blossom.banking.entity.Transaction;

public class TransactionMapper {
    public static Transaction mapToTransaction(TransactionDto transactionDto) {
        return new Transaction(
                transactionDto.getTransactionId(),
                transactionDto.getSenderId(),
                transactionDto.getSenderName(),
                transactionDto.getBeneficiaryId(),
                transactionDto.getBeneficiaryName(),
                transactionDto.getAmount(),
                transactionDto.getTransaction_status(),
                transactionDto.getDate()
        );
    }

    public static TransactionDto mapToTransactionDto(Transaction transaction) {
        return new TransactionDto(
                transaction.getTransactionId(),
                transaction.getSenderId(),
                transaction.getSenderName(),
                transaction.getBeneficiaryId(),
                transaction.getBeneficiaryName(),
                transaction.getAmount(),
                transaction.getTransaction_status(),
                transaction.getDate()
        );
    }
}
