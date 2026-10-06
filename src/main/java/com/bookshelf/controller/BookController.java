package com.bookshelf.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/books")
public class BookController {

    private final List<Map<String, Object>> books = new ArrayList<>();
    private final AtomicLong counter = new AtomicLong();

    @GetMapping
    public List<Map<String, Object>> list() {
        return books;
    }

    @PostMapping
    public ResponseEntity<Map<String, Object>> create(@RequestBody Map<String, String> payload) {
        Map<String, Object> book = Map.of(
                "id", counter.incrementAndGet(),
                "title", payload.getOrDefault("title", "Untitled"),
                "author", payload.getOrDefault("author", "Unknown"));
        books.add(book);
        return ResponseEntity.status(HttpStatus.CREATED).body(book);
    }
}
