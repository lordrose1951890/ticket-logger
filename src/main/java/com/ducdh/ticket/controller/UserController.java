package com.ducdh.ticket.controller;

import com.ducdh.ticket.entity.User;
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

    @GetMapping("/{username}")
    public ResponseEntity findUser(@PathVariable String username){
        return ResponseEntity.ok(userService.getUserByUsername(username));
    }

    @PostMapping
    public User createUser(@RequestBody User user) throws Exception{
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
