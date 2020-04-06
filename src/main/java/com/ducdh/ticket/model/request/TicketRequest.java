package com.ducdh.ticket.model.request;

import com.ducdh.ticket.entity.Ticket;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
public class TicketRequest {

    private Long userId;

    @JsonIgnore
    private String id;

    private String description;

    private String type;

    private String fileUri;

    private String status;

    public TicketRequest(String id, String description, String type, String fileUri, String status) {
        this.id = id;
        this.description = description;
        this.type = type;
        this.fileUri = fileUri;
        this.status = status;
    }

    public static TicketRequest of(Ticket ticket) {
        return new TicketRequest(ticket.getId(), ticket.getDescription(),
                ticket.getType(), ticket.getFileUri(), ticket.getStatus());
    }
}
