package com.ducdh.ticket.service.impl;

import com.ducdh.ticket.entity.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class RedisTicketService {

    private static final String TICKET = "Ticket";

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    private HashOperations<String, String, Ticket> hashOperations;

    @PostConstruct
    private void init() {
        hashOperations = redisTemplate.opsForHash();
    }

    public void save(Ticket ticket) {
        hashOperations.put(TICKET, ticket.getId(), ticket);
    }

    public Ticket find(String id) {
        return hashOperations.get(TICKET, id);
    }

    public void delete(String id) {
        hashOperations.delete(TICKET, id);
    }
}
