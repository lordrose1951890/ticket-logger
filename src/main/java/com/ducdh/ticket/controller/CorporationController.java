package com.ducdh.ticket.controller;

import com.ducdh.ticket.entity.Corporation;
import com.ducdh.ticket.service.CorporationService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
    public ResponseEntity findOne(@PathVariable String id) {
        return ResponseEntity.of(service.findById(id));
    }

    @PostMapping
    public Corporation create(@RequestBody Corporation corporation) {
        return service.save(corporation);
    }

    @PutMapping("/{id}")
    public Corporation update(@PathVariable String id, @RequestBody Corporation corporation) {
        return service.update(id, corporation);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable String id) {
        if (!service.findById(id).isPresent())
            return ResponseEntity.notFound().build();
        service.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
