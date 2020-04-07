package com.ducdh.ticket.repository;

import com.ducdh.ticket.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, String> {

    List<Ticket> findByUserId(Long userId);

    List<Ticket> findByAssignToEquals(Long userId);

    List<Ticket> findByAssignToIsNullAndStatusEquals(String status);
}
