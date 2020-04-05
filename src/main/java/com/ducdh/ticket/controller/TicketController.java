package com.ducdh.ticket.controller;

import com.ducdh.ticket.constant.Api;
import com.ducdh.ticket.entity.Ticket;
import com.ducdh.ticket.model.request.TicketRequest;
import com.ducdh.ticket.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping(Api.ROOT_URL + "/tickets")
public class TicketController {

    @Autowired
    private TicketService ticketService;

    @GetMapping
    ResponseEntity<?> getAll() {
        return ResponseEntity.ok(ticketService.getAll());
    }

    @GetMapping("/{ticketId}")
    ResponseEntity<?> getOne(@PathVariable String ticketId) {
        return ResponseEntity.ok(ticketService.getOne(ticketId));
    }

    @GetMapping("/users/{userId}")
    ResponseEntity<?> getAllByUserId(@PathVariable Long userId) {
        return ResponseEntity.ok(ticketService.getAllByUserId(userId));
    }

    @PostMapping
    ResponseEntity<?> createTicket(@RequestBody TicketRequest request) {
        return ResponseEntity.ok(ticketService.createTicket(request));
    }

    @PutMapping("/{ticketId}")
    ResponseEntity<?> updateTicket(@PathVariable String ticketId,
                                 @RequestBody TicketRequest request) {
        return ResponseEntity.ok(ticketService.updateTicket(ticketId, request));
    }

    @DeleteMapping("/{ticketId}")
    ResponseEntity<?> deleteTicket(@PathVariable String ticketId) {
        ticketService.deleteTicket(ticketId);
        return ResponseEntity.ok().build();
    }
}
