package com.blossom.banking.mapper;

import com.blossom.banking.dto.TransactionDto;
import com.blossom.banking.entity.Transaction;

public class TransactionMapper {
    public static Transaction mapToTransaction(TransactionDto transactionDto) {
        return new Transaction(
                transactionDto.getTransactionId(),
                transactionDto.getSenderAccount(),
                transactionDto.getSenderName(),
                transactionDto.getBeneficiaryAccount(),
                transactionDto.getBeneficiaryName(),
                transactionDto.getAmount(),
                transactionDto.getTransaction_status(),
                transactionDto.getDate()
        );
    }

    public static TransactionDto mapToTransactionDto(Transaction transaction) {
        return new TransactionDto(
                transaction.getTransactionId(),
                transaction.getSenderAccount(),
                transaction.getSenderName(),
                transaction.getBeneficiaryAccount(),
                transaction.getBeneficiaryName(),
                transaction.getAmount(),
                transaction.getTransaction_status(),
                transaction.getDate()
        );
    }
}
