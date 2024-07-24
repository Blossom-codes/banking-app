package com.blossom.banking.mapper;

import com.blossom.banking.dto.AccountDto;
import com.blossom.banking.entity.Account;

public class AccountMapper {
    public static Account mapToAccount(AccountDto accountDto)
    {
        return new Account(
                accountDto.getId(),
                accountDto.getAccountNumber(),
                accountDto.getAccountHolderName(),
                accountDto.getAccountHolderBalance(),
                accountDto.getDate()
        );

    }
    public static AccountDto mapToAccountDto(Account account)
    {
        return new AccountDto(
                account.getId(),
                account.getAccountNumber(),
                account.getAccountHolder(),
                account.getAccountBalance(),
                account.getDate()
        );

    }
}
