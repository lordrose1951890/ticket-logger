package com.ducdh.ticket.service.impl;

import com.ducdh.ticket.entity.Account;
import com.ducdh.ticket.repository.AccountRepository;
import com.ducdh.ticket.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AccountServiceImp implements AccountService {

    private final AccountRepository accountRepository;

    @Override
    public String login(Account account) {
        return null;
    }

    @Override
    public List<Account> getAll() {
        return accountRepository.findAll();
    }

    @Override
    public Optional<Account> getAccount(String username) {
        return accountRepository.findById(username);
    }

    @Override
    public Account save(Account account) {
        return accountRepository.save(account);
    }

    @Override
    public Account update(Account account) {
        return accountRepository.save(account);
    }

    @Override
    public void delete(String username) {
        accountRepository.deleteById(username);
    }
}
