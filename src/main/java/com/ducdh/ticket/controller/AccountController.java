package com.ducdh.ticket.controller;

import com.ducdh.ticket.entity.Account;
import com.ducdh.ticket.model.request.AccountRequest;
import com.ducdh.ticket.model.request.JwtRequest;
import com.ducdh.ticket.model.response.JwtResponse;
import com.ducdh.ticket.service.AccountService;
import com.ducdh.ticket.service.impl.AccountDetailsService;
import com.ducdh.ticket.util.JwtTokenUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@CrossOrigin
@RequestMapping("api/v1/accounts")
public class AccountController {

    @Autowired
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
    public Account createAccount(@RequestBody AccountRequest account) throws Exception{
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
