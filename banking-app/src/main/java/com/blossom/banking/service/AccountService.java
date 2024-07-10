package com.blossom.banking.service;

import com.blossom.banking.dto.AccountDto;
import com.blossom.banking.entity.Account;

import java.util.List;

public interface AccountService {
    AccountDto createAccount(AccountDto accountDto);
    List<Account> getAccount();
    AccountDto getAccountById(Long id);
    AccountDto deposit(Long id, Double amount);
//    AccountDto editAccount(Long id, String value);
    AccountDto withdraw(Long id, Double amount);
    AccountDto transfer(Long id, Double amount, Long beneficiaryAccountNumber);
    void deleteAccount(Long id);
}
