package com.ducdh.ticket.service;

import com.ducdh.ticket.entity.Ticket;
import com.ducdh.ticket.model.response.TicketResponse;

public interface TicketAssignService {

    TicketResponse assignTickets(Long userId);

    Ticket userAssignableCheck(Long userId);
}
