package com.ducdh.ticket.service.impl;

import com.ducdh.ticket.entity.Ticket;
import com.ducdh.ticket.model.exception.ResourceNotFoundException;
import com.ducdh.ticket.repository.TicketRepository;
import com.ducdh.ticket.repository.UserRepository;
import com.ducdh.ticket.service.TicketAssignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

@Service
public class TicketAssignServiceImpl implements TicketAssignService {

    private Comparator<Ticket> ticketComparator =
            Comparator.comparingInt(Ticket::getPriority).reversed();

    @Autowired
    private TicketRepository ticketRepository;

    @Override
    public Ticket assignTickets(Long userId) {
        List<Ticket> assignable = findAllAssignable();
        if (assignable.isEmpty()) {
            throw new ResourceNotFoundException("Now available ticket");
        }
        Queue<Ticket> ticketQueue = new PriorityQueue<>(ticketComparator);
        ticketQueue.addAll(assignable);
        Ticket temp = ticketQueue.remove();
        temp.setAssignTo(userId);
        temp.setStatus("working");
        return ticketRepository.save(temp);
    }

    @Override
    public Ticket userAssignableCheck(Long userId) {
        List<Ticket> assignedTickets = ticketRepository.findByAssignToEquals(userId);
        for (Ticket ticket : assignedTickets) {
            if ("working".equalsIgnoreCase(ticket.getStatus())) {
                return ticket;
            }
        }
        throw new ResourceNotFoundException("No currently working ticket");
    }

    private List<Ticket> findAllAssignable() {
        return ticketRepository.findByAssignToIsNullAndStatusEquals("new");
    }
}
