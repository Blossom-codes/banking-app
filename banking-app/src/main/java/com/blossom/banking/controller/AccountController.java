package com.blossom.banking.controller;

import com.blossom.banking.dto.AccountDto;
import com.blossom.banking.entity.Account;
import com.blossom.banking.service.AccountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    private AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    //    add account rest api
    @PostMapping
    public ResponseEntity<AccountDto> addAccount(@RequestBody AccountDto accountDto) {
        return new ResponseEntity<>(accountService.createAccount(accountDto), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Account>> getAccounts() {
        return ResponseEntity.ok(accountService.getAccount());
    }

//    @GetMapping(path = "/byId")
//    public ResponseEntity<AccountDto> getAccountById(@RequestBody Map<String, Long> request) {
//        Long id = request.get("id");
//        return ResponseEntity.ok(accountService.getAccountById(id));
//    }
    @GetMapping(path = "/getAccount")
    public ResponseEntity<AccountDto> getAccountById(@RequestParam(required = true) Long id) {

        return ResponseEntity.ok(accountService.getAccountById(id));
    }

    @PutMapping(path = "/{id}/deposit")
    public ResponseEntity<AccountDto> deposit(@PathVariable Long id, @RequestBody Map<String, Double> request) {
        Double amount = request.get("amount");
        return ResponseEntity.ok(accountService.deposit(id, amount));
    }

    @PutMapping(path = "/{id}/withdraw")
    public ResponseEntity<AccountDto> withdraw(@PathVariable Long id, @RequestBody Map<String, Double> request) {
        Double amount = request.get("amount");
        return ResponseEntity.ok(accountService.withdraw(id, amount));
    }
    @PutMapping(path = "/transfer")
    public ResponseEntity<AccountDto> transfer(@RequestParam Long sender,@RequestParam Long beneficiary,@RequestBody Map<String, Double> amountRequest) {
//        Long sender = request.get("sender");
//        Long beneficiary = request.get("beneficiary");
        Double amount = amountRequest.get("amount");
        return ResponseEntity.ok(accountService.transfer(sender, amount, beneficiary));
    }

    @DeleteMapping(path = "/{id}/deleteAccount")
    public ResponseEntity<String> deleteAccount(@PathVariable Long id)
    {
         accountService.deleteAccount(id);
         return ResponseEntity.ok("Account has been deleted successfully");
    }


}
