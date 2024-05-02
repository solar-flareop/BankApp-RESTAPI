package com.solarflare.bankingApp.service;

import com.solarflare.bankingApp.dto.AccountDto;

import java.util.List;

public interface AccountService {
    AccountDto createAccount(AccountDto accountDto);

    AccountDto getAccountById(Long id);

    AccountDto depositAmount(Long id, double amount);

    AccountDto withdrawAmount(Long id, double amount);

    List<AccountDto>getAllAccounts();

    void deleteAccount(Long id);
}
