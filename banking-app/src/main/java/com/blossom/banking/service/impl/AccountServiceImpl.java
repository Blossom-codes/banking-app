package com.blossom.banking.service.impl;

import com.blossom.banking.dto.AccountDto;
import com.blossom.banking.entity.Account;
import com.blossom.banking.mapper.AccountMapper;
import com.blossom.banking.repository.AccountRepository;
import com.blossom.banking.service.AccountService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class AccountServiceImpl implements AccountService {
    private AccountRepository accountRepository;

    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public AccountDto createAccount(AccountDto accountDto) {
        Account account = AccountMapper.mapToAccount(accountDto);
        String prefix = "310";
        int min = 1000000;
        int max = 9999999;
        Random r = new Random();
        int num = r.nextInt(min, max);
        String random = Integer.toString(num);

        account.setAccountNumber(prefix + random);
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
    public AccountDto getAccountByAccountNumber(String accountNumber) {
        Account account = accountRepository.findAccountByAccountNumber(accountNumber);
        return AccountMapper.mapToAccountDto(account);
    }

    @Override
    public AccountDto deposit(String account_number, Double amount) {
        try {
            Account account = accountRepository.findAccountByAccountNumber(account_number);
            Double total = account.getAccountBalance() + amount;
            account.setAccountBalance(total);
            Account saved = accountRepository.save(account);
            return AccountMapper.mapToAccountDto(saved);
        } catch (Exception e) {
            throw new IllegalStateException("Account does not exist");
        }
    }

    @Override
    public AccountDto editAccountName(String account_number, String value) {
        try {
            Account account = accountRepository.findAccountByAccountNumber(account_number);
            account.setAccountHolder(value);
            Account edited = accountRepository.save(account);
            return AccountMapper.mapToAccountDto(edited);
        } catch (Exception e) {
            throw new IllegalStateException("Account does not exist");
        }
    }

    @Override
    public AccountDto withdraw(String account_number, Double amount) {
        try {
            Account account = accountRepository.findAccountByAccountNumber(account_number);
            if (account.getAccountBalance() > amount) {
                Double newBalance = account.getAccountBalance() - amount;
                account.setAccountBalance(newBalance);
                Account saved = accountRepository.save(account);
                return AccountMapper.mapToAccountDto(saved);
            } else {
                throw new IllegalStateException("Insufficient Funds");
            }

        } catch (Exception e) {
            throw new IllegalStateException("Account doesn't exist");
        }
    }

    @Override
    public AccountDto transfer(String senderAccountNumber, Double amount, String beneficiaryAccountNumber) {
        Account sender = accountRepository.findAccountByAccountNumber(senderAccountNumber);
        Account beneficiary = accountRepository.findAccountByAccountNumber(beneficiaryAccountNumber);
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
