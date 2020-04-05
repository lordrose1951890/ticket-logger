package com.ducdh.ticket.service;

import com.ducdh.ticket.entity.User;
import com.ducdh.ticket.model.request.UserRequest;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<User> getAll();

    Optional<User> getUserById(Long id);

    User save(UserRequest user);

    User update(User user);

    void delete(Long id);
}
