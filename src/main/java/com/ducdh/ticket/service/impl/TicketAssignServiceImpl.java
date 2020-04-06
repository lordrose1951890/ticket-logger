package com.ducdh.ticket.service.impl;

import com.ducdh.ticket.entity.Ticket;
import com.ducdh.ticket.repository.TicketRepository;
import com.ducdh.ticket.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketAssignServiceImpl {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TicketRepository ticketRepository;

    public void assignTickets() {
        List<Ticket> assignable = findAllAssignable();

    }

    private List<Ticket> findAllAssignable() {
        return ticketRepository.findByStatus("new");
    }
}
