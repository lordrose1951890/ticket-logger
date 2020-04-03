package com.ducdh.ticket.repository;

import com.ducdh.ticket.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
