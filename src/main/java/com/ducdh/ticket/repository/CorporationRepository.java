package com.ducdh.ticket.repository;

import com.ducdh.ticket.entity.Corporation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CorporationRepository extends JpaRepository<Corporation, String> {
}
