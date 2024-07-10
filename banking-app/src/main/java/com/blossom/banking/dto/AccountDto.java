package com.blossom.banking.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data //for the auto getters and setters methods
@AllArgsConstructor
public class AccountDto {
    private Long accountNumber;
    private String accountHolderName;
    private Double accountHolderBalance;
    private LocalDateTime date;
}
