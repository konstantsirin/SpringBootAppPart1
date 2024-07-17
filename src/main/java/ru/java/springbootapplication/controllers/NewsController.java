package ru.java.springbootapplication.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import ru.java.springbootapplication.dto.NewsDto;
import ru.java.springbootapplication.services.NewsCRUDService;

import java.util.Collection;

@RestController
@RequestMapping("/api/news")
public class NewsController {
    private final NewsCRUDService newsCRUDService;

    public NewsController(NewsCRUDService newsCRUDService) {
        this.newsCRUDService = newsCRUDService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getNewsById(@PathVariable Long id) {
        return newsCRUDService.getById(id);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Collection<NewsDto> getAllComments() {
        return newsCRUDService.getAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public NewsDto createComment(@RequestBody NewsDto newsDto) {
        return newsCRUDService.create(newsDto);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public NewsDto updateComment(@RequestBody NewsDto newsDto) {
        return newsCRUDService.update(newsDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteComment(@PathVariable Long id) {
        return newsCRUDService.delete(id);
    }

}
