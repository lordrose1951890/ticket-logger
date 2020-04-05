package com.ducdh.ticket.service.impl;

import com.ducdh.ticket.entity.Account;
import com.ducdh.ticket.entity.User;
import com.ducdh.ticket.model.exception.ResourceNotFoundException;
import com.ducdh.ticket.model.request.AccountRequest;
import com.ducdh.ticket.repository.AccountRepository;
import com.ducdh.ticket.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private PasswordEncoder encoder;

    @Override
    public List<Account> getAll() {
        return accountRepository.findAll();
    }

    @Override
    public Optional<Account> getAccount(String username) {
        return accountRepository.findById(username);
    }

    @Override
    public Account save(AccountRequest account) {
        if (accountRepository.existsById(account.getUsername())) {
            throw new ResourceNotFoundException("Username is taken");
        }
        Account newAcc = new Account();
        User tempUser = new User();
        newAcc.setUsername(account.getUsername());
        newAcc.setPassword(encoder.encode(account.getPassword()));
        tempUser.setName(account.getName());
        newAcc.setUser(tempUser);

        return accountRepository.save(newAcc);
    }

    @Override
    public Account update(Account account) {
        return accountRepository.findById(account.getUsername())
                .map(acc -> {
                    account.setUser(acc.getUser());
                    return accountRepository.save(account);
                })
                .orElseThrow(() ->
                        new ResourceNotFoundException("Not found: " +
                                account.getUsername()));
    }

    @Override
    public void delete(String username) {
        accountRepository.findById(username).map(account -> {
            accountRepository.deleteById(username);
            return account;
        }).orElseThrow(() -> new ResourceNotFoundException("Not found: " + username));
    }
}
