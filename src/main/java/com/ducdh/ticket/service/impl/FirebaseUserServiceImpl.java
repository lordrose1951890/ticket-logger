package com.ducdh.ticket.service.impl;

import com.ducdh.ticket.model.exception.FirebaseUnauthorizedException;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;
import com.google.firebase.auth.UserRecord;
import com.google.firebase.auth.UserRecord.CreateRequest;
import org.springframework.stereotype.Service;

@Service
public class FirebaseUserServiceImpl {

    public FirebaseToken verifyIdToken(String idToken) {
        FirebaseAuth auth = FirebaseAuth.getInstance();
        try {
            return auth.verifyIdToken(idToken);
        } catch (FirebaseAuthException e) {
            System.out.println(e.getErrorCode() + " - " + e.getMessage());
            throw new FirebaseUnauthorizedException("Unauthorized");
        }
    }

    public String createFirebaseUser(String email) {
        FirebaseAuth auth = FirebaseAuth.getInstance();
        CreateRequest request = new CreateRequest()
                .setEmail(email)
                .setEmailVerified(true);
        UserRecord userRecord;
        try {
            userRecord = auth.createUser(request);
        } catch (FirebaseAuthException e) {
            System.out.println(e.getErrorCode() + " - " + e.getMessage());
            return "";
        }
        return userRecord.getUid();
    }
}
