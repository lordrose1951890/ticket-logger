package com.ducdh.ticket.controller;

import com.ducdh.ticket.constant.Api;
import com.ducdh.ticket.service.TicketAssignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping(Api.ROOT_URL + "/ticket-assignments")
public class TicketAssignController {

    @Autowired
    private TicketAssignService ticketAssignService;

    @GetMapping("/{userId}")
    ResponseEntity<?> getCurrentTicket(@PathVariable Long userId) {
        return ResponseEntity.ok(ticketAssignService.userAssignableCheck(userId));
    }

    @PostMapping("/{userId}")
    ResponseEntity<?> assignTicket(@PathVariable Long userId) {
        return ResponseEntity.ok(ticketAssignService.assignTickets(userId));
    }
}
