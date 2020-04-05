package com.ducdh.ticket.model.request;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserRequest implements Serializable {
    private String name;

    private String role;
}
