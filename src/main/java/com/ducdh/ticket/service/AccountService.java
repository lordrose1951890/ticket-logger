package com.ducdh.ticket.service;

import com.ducdh.ticket.entity.Account;

import java.util.List;
import java.util.Optional;

public interface AccountService {

    String login(Account account);

    List<Account> getAll();

    Optional<Account> getAccount(String username);

    Account save(Account account);

    Account update(Account account);

    void delete(String username);
}
