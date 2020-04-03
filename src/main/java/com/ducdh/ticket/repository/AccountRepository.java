package com.ducdh.ticket.repository;

import com.ducdh.ticket.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, String> {

}
