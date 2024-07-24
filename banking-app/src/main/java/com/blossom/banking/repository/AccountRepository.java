package com.blossom.banking.repository;

import com.blossom.banking.dto.AccountDto;
import com.blossom.banking.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AccountRepository extends JpaRepository<Account, Long> {
    // let the interface extend the jpa repository and accept account as type of values and long as type of id
    @Query(value = "SELECT * FROM accounts WHERE account_number = ?1",nativeQuery = true)
    Account findAccountByAccountNumber(String account_number);



}
