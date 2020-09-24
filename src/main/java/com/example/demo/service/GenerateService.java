package com.example.demo.service;

import java.util.Optional;

public interface GenerateService<T> {
    Optional<T> getById(Long id);
}
