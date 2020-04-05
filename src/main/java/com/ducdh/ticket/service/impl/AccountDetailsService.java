package com.ducdh.ticket.service.impl;

import com.ducdh.ticket.entity.Account;
import com.ducdh.ticket.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class AccountDetailsService implements UserDetailsService {

    @Autowired
    private AccountRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = repository.findById(username)
                .orElseThrow(() ->
                        new UsernameNotFoundException("Username not found: " + username));
        return new User(account.getUsername(), account.getPassword(), new ArrayList<>());
    }
}
