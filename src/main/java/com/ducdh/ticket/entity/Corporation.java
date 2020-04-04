package com.ducdh.ticket.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_corporation")
@Data
public class Corporation {

    @Id()
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    private String name;

    private String address;

    private String description;

}
