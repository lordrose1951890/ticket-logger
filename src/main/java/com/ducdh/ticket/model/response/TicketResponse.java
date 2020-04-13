package com.ducdh.ticket.model.response;

import com.ducdh.ticket.entity.Ticket;
import com.ducdh.ticket.model.request.TicketRequest;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
public class TicketResponse {
    private Long userId;

    private String id;

    private String description;

    private String type;

    private String fileUri;

    private String status;

    private Long assignTo;

    public TicketResponse(String id, String description, String type, String fileUri,
                         String status, Long assignTo, Long userId) {
        this.id = id;
        this.description = description;
        this.type = type;
        this.fileUri = fileUri;
        this.status = status;
        this.assignTo = assignTo;
        this.userId = userId;
    }

    public static TicketResponse of(Ticket ticket) {
        return new TicketResponse(ticket.getId(), ticket.getDescription(),
                ticket.getType(), ticket.getFileUri(), ticket.getStatus(),
                ticket.getAssignTo(), ticket.getUser().getId());
    }
}
