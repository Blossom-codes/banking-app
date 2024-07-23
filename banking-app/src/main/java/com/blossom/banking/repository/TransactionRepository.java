package com.blossom.banking.repository;

import com.blossom.banking.dto.TransactionDto;
import com.blossom.banking.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction,Long> {
    @Query(value = "SELECT * FROM Transactions WHERE sent_by=?1", nativeQuery = true)
    List<Transaction> getTransactionBySenderName(String sent_by);


}

