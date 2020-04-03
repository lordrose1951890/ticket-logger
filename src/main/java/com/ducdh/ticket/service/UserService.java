package com.ducdh.ticket.service;

import com.ducdh.ticket.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<User> getAll();

    Optional<User> getUserById(Long id);

    User save(User user);

    User update(User user);

    void delete(Long id);
}
