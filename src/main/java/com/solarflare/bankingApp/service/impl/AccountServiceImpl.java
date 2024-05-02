package com.solarflare.bankingApp.service.impl;

import com.solarflare.bankingApp.dto.AccountDto;
import com.solarflare.bankingApp.entity.Account;
import com.solarflare.bankingApp.mapper.AccountMapper;
import com.solarflare.bankingApp.repository.AccountRepository;
import com.solarflare.bankingApp.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    private AccountRepository accountRepository;

    @Override
    public AccountDto createAccount(AccountDto accountDto) {
        Account account = AccountMapper.mapToAccount(accountDto);
        Account savedAccount= accountRepository.save(account);
        return AccountMapper.mapToAccountDto(savedAccount);
    }

    @Override
    public AccountDto getAccountById(Long id) {
        Account account = accountRepository
                .findById(id)
                .orElseThrow(()->new RuntimeException("Account does not exist"));
        return AccountMapper.mapToAccountDto(account);
    }

    @Override
    public AccountDto depositAmount(Long id, double amount) {
        Account account = accountRepository
                .findById(id)
                .orElseThrow(()->new RuntimeException("Account does not exist"));
        double total = account.getBalance()+amount;
        account.setBalance(total);
        Account savedAccount=accountRepository.save(account);
        return AccountMapper.mapToAccountDto(savedAccount);
    }

    @Override
    public AccountDto withdrawAmount(Long id, double amount) {
        Account account = accountRepository
                .findById(id)
                .orElseThrow(()->new RuntimeException("Account does not exist"));
        if(amount > account.getBalance()){
            throw new RuntimeException("Insufficient balance");
        }
        double total = account.getBalance()-amount;
        account.setBalance(total);
        Account savedAccount=accountRepository.save(account);
        return AccountMapper.mapToAccountDto(savedAccount);
    }

    @Override
    public List<AccountDto> getAllAccounts() {
        List<Account>accounts = accountRepository.findAll();
        return accounts.stream()
                .map((account)->AccountMapper.mapToAccountDto(account))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteAccount(Long id) {
        Account account = accountRepository
                .findById(id)
                .orElseThrow(()->new RuntimeException("Account does not exist"));
        accountRepository.deleteById(id);
    }
}
