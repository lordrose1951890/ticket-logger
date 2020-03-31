package com.ducdh.ticket.repository;

import com.ducdh.ticket.entity.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubcriptionRepository extends JpaRepository<Subscription, Long> {
}
