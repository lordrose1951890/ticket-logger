package com.ducdh.ticket.service.impl;

import com.ducdh.ticket.entity.Account;
import com.ducdh.ticket.entity.Shop;
import com.ducdh.ticket.entity.User;
import com.ducdh.ticket.model.exception.ResourceNotFoundException;
import com.ducdh.ticket.model.request.AccountRequest;
import com.ducdh.ticket.repository.AccountRepository;
import com.ducdh.ticket.repository.ShopRepository;
import com.ducdh.ticket.service.AccountService;
import com.ducdh.ticket.service.ShopService;
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
    private FirebaseUserServiceImpl firebaseUserService;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private ShopRepository shopRepository;

    @Autowired
    private PasswordEncoder encoder;

    @Override
    public List<Account> getAll() {
        return accountRepository.findAll();
    }

    @Override
    public Account getAccount(String username) {
        return accountRepository.findById(username).orElseThrow(() ->
                new ResourceNotFoundException("Account not found: " + username));
    }

    @Override
    public Account getAccountByFirebaseUid(String uid) {
        return accountRepository.findByUserId(uid).orElseThrow(() ->
                new ResourceNotFoundException("Account not found: " + uid));
    }

    @Override
    public Account save(AccountRequest account) {
        if (accountRepository.existsById(account.getUsername())) {
            throw new ResourceNotFoundException("Username is taken");
        }
        String uid = firebaseUserService.createFirebaseUser(account.getEmail());
        Account newAcc = new Account();
        User tempUser = new User();
        Shop tempShop;

        newAcc.setUsername(account.getUsername());
        newAcc.setPassword(encoder.encode(account.getPassword()));
        newAcc.setEmail(account.getEmail());
        newAcc.setUserId(uid);

        if (account.getShopName() == null || account.getShopAddress() == null) {
            tempShop = shopRepository.findById(0L).get();
        } else {
            tempShop = new Shop();
            tempShop.setShopName(account.getShopName());
            tempShop.setShopAddress(account.getShopAddress());
        }

        tempUser.setName(account.getName());
        tempUser.setRole(
                (account.getRole() == null) ? "user" : account.getRole()
        );
        tempUser.setShop(tempShop);

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
