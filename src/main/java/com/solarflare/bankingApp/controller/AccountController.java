package com.solarflare.bankingApp.controller;

import com.solarflare.bankingApp.dto.AccountDto;
import com.solarflare.bankingApp.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/accounts")
public class AccountController {

    @Autowired
    private AccountService accountService;

    //Add account RESTAPI
    @PostMapping
    public ResponseEntity<AccountDto>addAccount(@RequestBody AccountDto accountDto){
        return new ResponseEntity<>(accountService.createAccount(accountDto), HttpStatus.CREATED);
    }

    //Get account RESTAPI
    @GetMapping("/{id}")
    public ResponseEntity<AccountDto>getAccountById(@PathVariable Long id){
        AccountDto accountDto = accountService.getAccountById(id);
        return ResponseEntity.ok(accountDto);
    }

    //Deposit amount RESTAPI
    @PutMapping("/deposit/{id}")
    public ResponseEntity<AccountDto>deposit(@PathVariable Long id, @RequestBody Map<String,Double>request){
        Double amount=request.get("amount");
        AccountDto accountDto = accountService.depositAmount(id,amount);
        return ResponseEntity.ok(accountDto);
    }

    //Withdraw amount RESTAPI
    @PutMapping("/withdraw/{id}")
    public ResponseEntity<AccountDto>withdraw(@PathVariable Long id, @RequestBody Map<String,Double>request){
        Double amount=request.get("amount");
        AccountDto accountDto = accountService.withdrawAmount(id,amount);
        return ResponseEntity.ok(accountDto);
    }

    //Get all accounts RESTAPI
    @GetMapping("/all")
    public ResponseEntity<List<AccountDto>>getAllAccounts(){
        List<AccountDto>accounts=accountService.getAllAccounts();
        return ResponseEntity.ok(accounts);
    }

    //Delete account RESTAPI
    @DeleteMapping("/{id}")
    public ResponseEntity<String>deleteAccount(@PathVariable Long id){
        accountService.deleteAccount(id);
        return ResponseEntity.ok("Account deleted successfully.");
    }
}
