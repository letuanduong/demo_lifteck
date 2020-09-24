package com.example.demo.service;

import com.example.demo.model.Account;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;
import java.util.Optional;

public interface IAccountService extends UserDetailsService {
    List<Account> findAll();
    Optional<Account> findById(Long id);
    Account findByUsername(String username);
    void remove(Long id);
    void save(Account account);
}
