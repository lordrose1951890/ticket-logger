package com.ducdh.ticket.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "tbl_account")
@Data
public class Account implements Serializable {

    @Id
    private String username;

    private String password;

    @Column(name = "uid")
    private String userId;

    private String email;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "userId", referencedColumnName = "id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private User user;
}
