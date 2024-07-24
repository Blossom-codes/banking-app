package com.blossom.banking.controller;

import com.blossom.banking.dto.AccountDto;
import com.blossom.banking.dto.TransactionDto;
import com.blossom.banking.entity.Account;
import com.blossom.banking.entity.Transaction;
import com.blossom.banking.service.AccountService;
import com.blossom.banking.service.TransactionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    private final AccountService accountService;
    private final TransactionService transactionService;

    public AccountController(AccountService accountService,TransactionService transactionService) {
        this.accountService = accountService;
        this.transactionService = transactionService;
    }

    //    add account rest api
    @PostMapping(path = "/register")
    public ResponseEntity<AccountDto> addAccount(@RequestBody AccountDto accountDto) {
        return new ResponseEntity<>(accountService.createAccount(accountDto), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Account>> getAccounts() {
        return ResponseEntity.ok(accountService.getAccount());
    }
    @GetMapping(path = "/transactions")
    public ResponseEntity<List<Transaction>> getAllTransactions() {
        return ResponseEntity.ok(transactionService.getAllTransaction());
    }
    @GetMapping(path = "/user")
    public ResponseEntity<AccountDto> getAccountByAccountNumber(@RequestBody Map<String, String> request) {
        String account_number = request.get("account_number");
        return ResponseEntity.ok(accountService.getAccountByAccountNumber(account_number));
    }

    @GetMapping(path = "/transaction")
    public ResponseEntity<TransactionDto> getTransactionById(@RequestParam Long id) {
        return ResponseEntity.ok(transactionService.getTransactionById(id));
    }

    //    @GetMapping(path = "/byId")
//    public ResponseEntity<AccountDto> getAccountById(@RequestBody Map<String, Long> request) {
//        Long id = request.get("id");
//        return ResponseEntity.ok(accountService.getAccountById(id));
//    }
    @GetMapping(path = "/getAccount")
    public ResponseEntity<AccountDto> getAccountById(@RequestParam Long id) {

        return ResponseEntity.ok(accountService.getAccountById(id));
    }
    @GetMapping(path = "/transactionCount")
    public  ResponseEntity<Long> getTransactionCount()
    {
        return ResponseEntity.ok(transactionService.getTotalNumberOfTransactions());
    }
    @GetMapping(path = "/transactions/sent_by")
    public  ResponseEntity<List<Transaction>> getTransactionBySenderName(@RequestParam String name)
    {
        return ResponseEntity.ok(transactionService.getTransactionBySenderName(name));
    }
    @PutMapping(path = "/deposit")
    public ResponseEntity<AccountDto> deposit(@RequestParam String account_number, @RequestBody Map<String, Double> request) {
        Double amount = request.get("amount");
        return ResponseEntity.ok(accountService.deposit(account_number, amount));
    }

    @PutMapping(path = "/withdraw")
    public ResponseEntity<AccountDto> withdraw(@RequestParam String account_number, @RequestBody Map<String, Double> request) {
        Double amount = request.get("amount");
        return ResponseEntity.ok(accountService.withdraw(account_number, amount));
    }

    @PutMapping(path = "/transfer")
    public ResponseEntity<TransactionDto> transfer(@RequestParam String sender, @RequestParam String beneficiary, @RequestBody Map<String, Double> amountRequest) {
        Double amount = amountRequest.get("amount");
        ResponseEntity.ok(accountService.transfer(sender, amount, beneficiary));
        return ResponseEntity.ok(transactionService.saveTransaction(sender, beneficiary, amount));

    }
    @PutMapping("/edit")
    public  ResponseEntity<AccountDto> edit(@RequestBody Map<String, String> request)
    {
       String name = request.get("full_name");
       String account_number = request.get("account_number");
        return ResponseEntity.ok(accountService.editAccountName(account_number,name));
    }

    @DeleteMapping(path = "/delete")
    public ResponseEntity<String> deleteAccount(@RequestParam Long id) {
        accountService.deleteAccount(id);
        return ResponseEntity.ok("Account has been deleted successfully");
    }


}
