package com.ducdh.ticket.service.impl;

import com.ducdh.ticket.entity.Ticket;
import com.ducdh.ticket.model.exception.ResourceNotFoundException;
import com.ducdh.ticket.model.request.TicketRequest;
import com.ducdh.ticket.repository.TicketRepository;
import com.ducdh.ticket.repository.UserRepository;
import com.ducdh.ticket.service.TicketService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class TicketServiceImpl implements TicketService {

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RedisTicketService redisTicketService;

    @Override
    public List<Ticket> getAll() {
        log.info("Going to the DB");
        return ticketRepository.findAll();
    }

    @Override
    public Ticket getOne(String id) {
        Ticket result = redisTicketService.find(id);
        if (result != null) {
            log.info("Not going to the DB - Caching by Redis");
            return result;
        }
        return ticketRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Ticket not found" + id));
    }

    @Override
    public List<Ticket> getAllByUserId(Long userId) {
        return ticketRepository.findByUserId(userId);
    }

    @Override
    public Ticket createTicket(TicketRequest request) {
        Ticket newTicket = new Ticket();

        newTicket.setDescription(request.getDescription());
        newTicket.setType(request.getType());
        newTicket.setFileUri(request.getFileUri());
        newTicket.setStatus(request.getStatus());
        newTicket.setUser(userRepository.findById(request.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found: " +
                        request.getUserId())));

        newTicket = ticketRepository.save(newTicket);
        redisTicketService.save(newTicket);
        return newTicket;
    }

    @Override
    public Ticket updateTicket(String ticketId, TicketRequest request) {
        return ticketRepository.findById(ticketId).map(ticket -> {
            ticket.setDescription(request.getDescription());
            ticket.setType(request.getDescription());
            ticket.setFileUri(request.getDescription());
            ticket.setStatus(request.getStatus());
            ticket.setAssignTo(request.getAssignTo());
            redisTicketService.save(ticket);
            return ticketRepository.save(ticket);
        }).orElseThrow(() -> new ResourceNotFoundException("Ticket not found: " +
                ticketId));
    }

    @Override
    public void deleteTicket(String ticketId) {
        Ticket currTicket = ticketRepository.findById(ticketId)
                .orElseThrow(() ->  new ResourceNotFoundException("Ticket not found: " +
                        ticketId));

        redisTicketService.delete(ticketId);
        ticketRepository.delete(currTicket);
    }
}
