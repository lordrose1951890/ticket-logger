package com.ducdh.ticket.repository;

import com.ducdh.ticket.entity.Corporation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CorporationRepository extends JpaRepository<Corporation, String> {
}
