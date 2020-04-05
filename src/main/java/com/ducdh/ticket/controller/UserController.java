package com.ducdh.ticket.controller;

import com.ducdh.ticket.entity.User;
import com.ducdh.ticket.model.request.UserRequest;
import com.ducdh.ticket.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/users")
public class UserController {

    private final UserService userService;

    @GetMapping
    public ResponseEntity<?> getAllUser(){
        return ResponseEntity.ok(userService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity findOneUser(@PathVariable Long id){
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @PostMapping
    public User createUser(@RequestBody UserRequest user) throws Exception{
        return userService.save(user);
    }

    @PutMapping
    public User updateUser(@RequestBody User user) throws Exception{
        return userService.update(user);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteUser(@PathVariable Long id) throws Exception{
        userService.delete(id);
        return ResponseEntity.ok().build();
    }
}
