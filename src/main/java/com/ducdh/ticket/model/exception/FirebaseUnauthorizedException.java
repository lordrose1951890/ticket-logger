package com.ducdh.ticket.model.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class FirebaseUnauthorizedException extends RuntimeException {

    public FirebaseUnauthorizedException(String message) {
        super(message);
    }
}
