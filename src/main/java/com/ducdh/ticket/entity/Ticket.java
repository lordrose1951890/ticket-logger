package com.ducdh.ticket.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_ticket")
@Data
public class Ticket {

    @Id
    @GeneratedValue(generator = "ticket-generator")
    @GenericGenerator(name = "ticket-generator",
            strategy = "com.ducdh.ticket.entity.identifier.TicketIdGenerator")
    private String id;

    private String description;

    private String type;

    private String fileUri;

    private String status;

    private Long assignTo;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private User user;

    public int getPriority() {
        int priority;
        switch (type) {
            case "Computer error":
                priority = 2;
                break;
            case "Printer error":
                priority = 3;
                break;
            case "Pipeline error":
                priority = 4;
                break;
            case "Scanner error":
                priority = 5;
                break;
            case "Camera error":
                priority = 6;
                break;
            case "Network error":
                priority = 7;
                break;
            case "Electric error":
                priority = 8;
                break;
            default:
                priority = 1;
                break;
        }
        return priority;
    }
}
