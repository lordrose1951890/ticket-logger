package com.ducdh.ticket.service.impl;

import com.ducdh.ticket.entity.Corporation;
import com.ducdh.ticket.repository.CorporationRepository;
import com.ducdh.ticket.service.CorporationService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CorporationServiceImpl implements CorporationService {

    private final CorporationRepository repository;

    @Override
    public List<Corporation> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<Corporation> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public Corporation save(Corporation corporation) {
        if (corporation.getId() != null)
            if (repository.findById(corporation.getId()).isPresent())
                corporation.setId(null);
        return repository.save(corporation);
    }

    @Override
    public Corporation update(Long id, Corporation corporation) {
        corporation.setId(id);
        return repository.save(corporation);
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    @Override
    public Page<Corporation> getPageableCorporation(Pageable pageable) {
        return repository.findAll(pageable);
    }
}
