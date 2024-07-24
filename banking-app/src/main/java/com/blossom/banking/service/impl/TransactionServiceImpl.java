package com.blossom.banking.service.impl;

import com.blossom.banking.dto.TransactionDto;
import com.blossom.banking.entity.Account;
import com.blossom.banking.entity.Transaction;
import com.blossom.banking.mapper.TransactionMapper;
import com.blossom.banking.repository.AccountRepository;
import com.blossom.banking.repository.TransactionRepository;
import com.blossom.banking.service.TransactionService;
import com.blossom.banking.enums.Status;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService {
    private AccountRepository accountRepository;
    private TransactionRepository transactionRepository;

    public TransactionServiceImpl(TransactionRepository transactionRepository, AccountRepository accountRepository) {
        this.transactionRepository = transactionRepository;
        this.accountRepository = accountRepository;
    }


    @Override
    public TransactionDto saveTransaction(String senderAccountNumber, String beneficiaryAccountNumber, Double amount) {
        Account sender = accountRepository.findAccountByAccountNumber(senderAccountNumber);
        Account beneficiary = accountRepository.findAccountByAccountNumber(beneficiaryAccountNumber);

        String sent_by = sender.getAccountHolder();
        String received_by = beneficiary.getAccountHolder();
        Transaction transaction = new Transaction(senderAccountNumber, sent_by, beneficiaryAccountNumber, received_by, amount, Status.SUCCESS);
        Transaction saved = transactionRepository.save(transaction);
        return TransactionMapper.mapToTransactionDto(saved);
    }

    @Override
    public List<Transaction> getAllTransaction() {
        return transactionRepository.findAll();
    }

    @Override
    public TransactionDto getTransactionById(Long id) {
        Transaction transaction = transactionRepository.findById(id).orElseThrow(() -> new IllegalStateException("Transaction with that id does not exist"));
        return TransactionMapper.mapToTransactionDto(transaction);
    }

    @Override
    public Long getTotalNumberOfTransactions() {
        return transactionRepository.count();
    }

    @Override
    public List<Transaction> getTransactionBySenderName(String sentBy) {
        return transactionRepository.getTransactionBySenderName(sentBy);
    }


}
