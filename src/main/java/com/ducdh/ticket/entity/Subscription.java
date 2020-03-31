package com.ducdh.ticket.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "subscription")
@Data
public class Subscription {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String type;
}
