package com.blossom.banking.service;

import com.blossom.banking.dto.TransactionDto;
import com.blossom.banking.entity.Transaction;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TransactionService {
    TransactionDto saveTransaction(Long senderId, Long beneficiary, Double amount);

    List<Transaction> getAllTransaction();

    TransactionDto getTransactionById(Long id);

    Long getTotalNumberOfTransactions();

    List<Transaction> getTransactionBySenderName(String sentBy);
}
