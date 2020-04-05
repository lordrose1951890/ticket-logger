package com.ducdh.ticket.service.impl;

import com.ducdh.ticket.entity.User;
import com.ducdh.ticket.model.exception.ResourceNotFoundException;
import com.ducdh.ticket.model.request.UserRequest;
import com.ducdh.ticket.repository.UserRepository;
import com.ducdh.ticket.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImp implements UserService {

    private final UserRepository userRepository;

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public User save(UserRequest userRequest) {
        User user = new User();
        user.setName(userRequest.getName());
        user.setRole(userRequest.getRole());
        return userRepository.save(user);
    }

    @Override
    public User update(User user) {
        return userRepository.findById(user.getId()).map(user1 ->
            userRepository.save(user)).orElseThrow(() -> new ResourceNotFoundException("Not found: " + user.getId()));
    }

    @Override
    public void delete(Long id) {
        userRepository.findById(id).map(user -> {
            userRepository.deleteById(id);
            return user;
        }).orElseThrow(() -> new ResourceNotFoundException("Not found: " + id));
    }
}
