package com.ducdh.ticket.repository;

import com.ducdh.ticket.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TicketRepository extends JpaRepository<Ticket, String> {

    List<Ticket> findByUserId(Long userId);

    List<Ticket> findByStatus(String status);
}
