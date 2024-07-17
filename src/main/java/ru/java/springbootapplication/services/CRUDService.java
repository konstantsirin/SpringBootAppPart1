package ru.java.springbootapplication.services;

import org.springframework.http.ResponseEntity;

import java.util.Collection;

public interface CRUDService<T> {
    ResponseEntity<?> getById(Long id);
    Collection<T> getAll();
    T create(T item);
    T update(T item);
    ResponseEntity<?> delete(Long id);
}
