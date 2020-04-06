package com.ducdh.ticket.service;

import com.ducdh.ticket.entity.Account;
import com.ducdh.ticket.model.request.AccountRequest;

import java.util.List;
import java.util.Optional;

public interface AccountService {

    List<Account> getAll();

    Account getAccount(String username);

    Account save(AccountRequest account);

    Account update(Account account);

    void delete(String username);
}
