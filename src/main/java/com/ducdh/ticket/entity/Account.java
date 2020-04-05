package com.ducdh.ticket.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "tbl_account")
@Data
public class Account {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private String username;

    private String password;
}
