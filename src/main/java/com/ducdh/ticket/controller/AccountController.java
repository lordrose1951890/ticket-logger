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
    public ResponseEntity<?> allAccounts(){
        return ResponseEntity.ok(accountService.getAll());
    }

    @GetMapping("/{username}")
    public ResponseEntity oneAccount(@PathVariable String username){
        return ResponseEntity.ok(accountService.getAccount(username));
    }

    @PostMapping
    public Account accountAddition(@RequestBody Account account){
        return accountService.save(account);
    }

    @PutMapping
    public Account accountUpdatable(@RequestBody Account account){
        return accountService.update(account);
    }

    @DeleteMapping("/{username}")
    public ResponseEntity deleteAccount(@PathVariable String username){
        if (accountService.getAccount(username).isPresent()){
            accountService.delete(username);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
