package com.ducdh.ticket.controller;

import com.ducdh.ticket.constant.Api;
import com.ducdh.ticket.model.request.FirebaseAuthRequest;
import com.ducdh.ticket.service.UserService;
import com.ducdh.ticket.service.impl.AccountDetailsService;
import com.ducdh.ticket.service.impl.FirebaseUserServiceImpl;
import com.ducdh.ticket.util.JwtTokenUtil;
import com.google.firebase.auth.FirebaseToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(Api.ROOT_URL + "/firebase")
public class FirebaseAuthenticationController {

    @Autowired
    private FirebaseUserServiceImpl firebaseUserService;

    @Autowired
    private AccountDetailsService accountDetailsService;

    @Autowired
    private UserService userService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @PostMapping
    ResponseEntity<?> verifyFirebaseIDToken(@RequestBody FirebaseAuthRequest authRequest) {
        FirebaseToken token = firebaseUserService.verifyIdToken(authRequest.getIdToken());
        return ResponseEntity.ok(createTokenFromFirebaseToken(token));
    }

    private String createTokenFromFirebaseToken(FirebaseToken token) {
        UserDetails user = accountDetailsService.loadUserByFirebaseUserId(token.getUid());
        return generateToken(user);
    }

    private String generateToken(UserDetails userDetails) {
        return jwtTokenUtil.generateToken(userDetails);
    }

    @GetMapping("/{uid}")
    ResponseEntity<?> getUserFromFirebaseUid(@PathVariable String uid) {
        return ResponseEntity.ok(userService.getUserByFirebaseUid(uid));
    }
}
