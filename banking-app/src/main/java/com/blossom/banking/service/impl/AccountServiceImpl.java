package com.blossom.banking.service.impl;

import com.blossom.banking.dto.AccountDto;
import com.blossom.banking.entity.Account;
import com.blossom.banking.mapper.AccountMapper;
import com.blossom.banking.repository.AccountRepository;
import com.blossom.banking.service.AccountService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {
    private AccountRepository accountRepository;

    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public AccountDto createAccount(AccountDto accountDto) {
        Account account = AccountMapper.mapToAccount(accountDto);
//        account.setDate(LocalDateTime.now());
        Account savedAccount = accountRepository.save(account);
        return AccountMapper.mapToAccountDto(savedAccount);
    }

    @Override
    public List<Account> getAccount() {

        return accountRepository.findAll();
    }

    @Override
    public AccountDto getAccountById(Long id) {
        Account account = accountRepository.findById(id).orElseThrow(() -> new IllegalStateException("Account does not exist"));
        return AccountMapper.mapToAccountDto(account);
    }

    @Override
    public AccountDto deposit(Long id, Double amount) {

        Account account = accountRepository.findById(id).orElseThrow(() -> new IllegalStateException("Account doesn't exist"));
        Double total = account.getAccountBalance() + amount;
        account.setAccountBalance(total);
        Account saved = accountRepository.save(account);
        return AccountMapper.mapToAccountDto(saved);
    }

    @Override
    public AccountDto editAccountName(Long id, String value) {
        Account account = accountRepository.findById(id).orElseThrow(() -> new IllegalStateException("User does not exist"));
        account.setAccountHolder(value);
        Account edited = accountRepository.save(account);
        return AccountMapper.mapToAccountDto(account);
    }

    @Override
    public AccountDto withdraw(Long id, Double amount) {

        Account account = accountRepository.findById(id).orElseThrow(() -> new IllegalStateException("Account doesn't exist"));
        if (account.getAccountBalance() > amount) {
            double newBalance = account.getAccountBalance() - amount;
            account.setAccountBalance(newBalance);
        } else {
            throw new IllegalStateException("Insufficient Funds");
        }

        Account saved = accountRepository.save(account);
        return AccountMapper.mapToAccountDto(saved);
    }

    @Override
    public AccountDto transfer(Long id, Double amount, Long beneficiaryAccountNumber) {
        Account sender = accountRepository.findById(id).orElseThrow(() -> new IllegalStateException("Your Account doesn't exist"));
        Account beneficiary = accountRepository.findById(beneficiaryAccountNumber).orElseThrow(() -> new IllegalStateException("Beneficiary doesn't exist"));

        if (sender.getAccountBalance() > amount) {
            Double newBalance = sender.getAccountBalance() - amount;
            sender.setAccountBalance(newBalance);
            beneficiary.setAccountBalance(beneficiary.getAccountBalance() + amount);
        } else {
            throw new IllegalStateException("Insufficient Funds");
        }

        Account savedSender = accountRepository.save(sender);
        Account savedBeneficiary = accountRepository.save(beneficiary);
        return AccountMapper.mapToAccountDto(savedSender);

    }

    @Override
    public void deleteAccount(Long id) {
        Account account = accountRepository.findById(id).orElseThrow(() -> new IllegalStateException("Account doesn't exist"));
        accountRepository.deleteById(id);
    }

}
