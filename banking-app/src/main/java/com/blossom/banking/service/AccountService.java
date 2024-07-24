package com.blossom.banking.service;

import com.blossom.banking.dto.AccountDto;
import com.blossom.banking.entity.Account;

import java.util.List;

public interface AccountService {
    AccountDto createAccount(AccountDto accountDto);
    List<Account> getAccount();
    AccountDto getAccountByAccountNumber(String accountNumber);
    AccountDto getAccountById(Long id);
    AccountDto deposit(String account_number, Double amount);
   AccountDto editAccountName(String account_number, String value);
    AccountDto withdraw(String account_number, Double amount);
    AccountDto transfer(String senderAccountNumber, Double amount, String beneficiaryAccountNumber);
    void deleteAccount(Long id);
}
