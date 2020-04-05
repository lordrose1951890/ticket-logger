package com.ducdh.ticket.service;

import com.ducdh.ticket.entity.Ticket;
import com.ducdh.ticket.model.request.TicketRequest;

import java.util.List;

public interface TicketService {

    List<Ticket> getAll();

    Ticket getOne(String id);

    List<Ticket> getAllByUserId(Long userId);

    Ticket createTicket(TicketRequest request);

    Ticket updateTicket(String ticketId, TicketRequest request);

    void deleteTicket(String ticketId);
}
