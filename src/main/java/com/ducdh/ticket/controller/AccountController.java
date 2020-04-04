package com.ducdh.ticket.controller;

import com.ducdh.ticket.entity.Account;
import com.ducdh.ticket.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/accounts")
public class AccountController {

    private final AccountService accountService;

    @GetMapping
    public ResponseEntity<?> getAllAccounts(){
        return ResponseEntity.ok(accountService.getAll());
    }

    @GetMapping("/{username}")
    public ResponseEntity getOneAccount(@PathVariable String username){
        return ResponseEntity.ok(accountService.getAccount(username));
    }

    @PostMapping
    public Account createAccount(@RequestBody Account account) throws Exception{
        return accountService.save(account);
    }

    @PutMapping
    public Account updateAccount(@RequestBody Account account) throws Exception{
        return accountService.update(account);
    }

    @DeleteMapping("/{username}")
    public ResponseEntity deleteAccount(@PathVariable String username) throws Exception{
        accountService.delete(username);
        return ResponseEntity.ok().build();
    }
}
