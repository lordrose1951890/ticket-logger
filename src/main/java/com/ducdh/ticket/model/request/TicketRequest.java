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

    public TicketRequest(String id, String description, String type, String fileUri) {
        this.id = id;
        this.description = description;
        this.type = type;
        this.fileUri = fileUri;
    }

    public static TicketRequest of(Ticket ticket) {
        return new TicketRequest(ticket.getId(), ticket.getDescription(),
                ticket.getType(), ticket.getFileUri());
    }
}
