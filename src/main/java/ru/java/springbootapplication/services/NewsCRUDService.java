package ru.java.springbootapplication.services;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.java.springbootapplication.dto.NewsDto;

import java.time.Instant;
import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class NewsCRUDService implements CRUDService<NewsDto> {

    private final ConcurrentHashMap<Long, NewsDto> storage = new ConcurrentHashMap<>();
    private final AtomicLong count = new AtomicLong(0);

    @Override
    public ResponseEntity<?> getById(Long id) {
        System.out.println("Get by ID: " + id);
        if (storage.containsKey(id)) {
            NewsDto newsDto = storage.get(id);
            return ResponseEntity.ok(newsDto);
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorMessage("Новость с ID " + id + " не найдена"));
    }

    @Override
    public Collection<NewsDto> getAll() {
        System.out.println("Get all");
        return storage.values();
    }
    @Override
    public NewsDto create(NewsDto item) {
        System.out.println("Create");
        Long nextId = count.incrementAndGet();
        item.setId(nextId);
        Instant now = Instant.now();
        item.setDate(now);
        storage.put(nextId, item);
        return item;
    }

    @Override
    public NewsDto update(NewsDto item) {
        Long id = item.getId();
        if (!storage.containsKey(id)) {
            return null;
        }
        System.out.println("Update by id " + id);
        item.setId(id);
        Instant now = Instant.now();
        item.setDate(now);
        storage.put(id, item);
        return item;
    }

    @Override
    public ResponseEntity<?> delete(Long id) {
        System.out.println("Delete id " + id);

        if (storage.containsKey(id)) {
            storage.remove(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorMessage("Новость с ID " + id + " не найдена"));


    }

    @Getter
    static class ErrorMessage {
        private final String message;

        public ErrorMessage(String message) {
            this.message = message;
        }
    }
}
