package com.blossom.banking.repository;

import com.blossom.banking.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {
    // let the interface extend the jpa repository and accept account as type of values and long as type of id




}
