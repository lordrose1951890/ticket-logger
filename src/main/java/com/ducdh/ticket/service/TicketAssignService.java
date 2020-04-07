package com.ducdh.ticket.service;

import com.ducdh.ticket.entity.Ticket;

public interface TicketAssignService {

    Ticket assignTickets(Long userId);

    Ticket userAssignableCheck(Long userId);
}
