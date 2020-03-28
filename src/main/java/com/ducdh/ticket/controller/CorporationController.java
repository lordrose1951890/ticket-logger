package com.ducdh.ticket.controller;

import com.ducdh.ticket.entity.Corporation;
import com.ducdh.ticket.model.BaseRequest;
import com.ducdh.ticket.service.CorporationService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Min;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/corporations")
@RequiredArgsConstructor
public class CorporationController {

    private final CorporationService service;

    @GetMapping
    public Page<Corporation> findAllWithPaging(
            @RequestParam(name = "pageNum", required = false, defaultValue = "0") Integer page,
            @RequestParam(name = "pageSize", required = false, defaultValue = "100") Integer size,
            @RequestParam(name = "sort", required = false, defaultValue = "ASC") String sort,
            @RequestParam(name = "sortBy", required = false, defaultValue = "id") String sortBy) {

        Sort sortAble = Sort.by(sortBy).ascending();
        if (sort.equalsIgnoreCase("DESC")) {
            sortAble = Sort.by(sortBy).descending();
        }

        Pageable pageable = PageRequest.of(Math.max(0, page), Math.max(0, size), sortAble);

        return service.getPageableCorporation(pageable);
    }

    @GetMapping("/{id}")
    public ResponseEntity findOne(@PathVariable Long id) {
        return ResponseEntity.of(service.findById(id));
    }

    @PostMapping
    public Corporation create(@RequestBody Corporation corporation) {
        return service.save(corporation);
    }

    @PutMapping("/{id}")
    public Corporation update(@PathVariable Long id, @RequestBody Corporation corporation) {
        return service.update(id, corporation);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        if (!service.findById(id).isPresent())
            return ResponseEntity.notFound().build();
        service.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
