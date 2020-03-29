package com.ducdh.ticket.service;

import com.ducdh.ticket.entity.Corporation;
import com.ducdh.ticket.repository.CorporationRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface CorporationService {

    List<Corporation> findAll();

    Optional<Corporation> findById(String id);

    Corporation save(Corporation corporation);

    Corporation update(String id, Corporation corporation);

    void deleteById(String id);

    Page<Corporation> getPageableCorporation(Pageable pageable);
}
