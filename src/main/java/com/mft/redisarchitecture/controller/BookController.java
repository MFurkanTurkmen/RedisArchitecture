package com.mft.redisarchitecture.controller;

import com.mft.redisarchitecture.dto.request.BookRQ;
import com.mft.redisarchitecture.dto.response.BookRS;
import com.mft.redisarchitecture.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/Book")
@RequiredArgsConstructor
@RestController
public class BookController {

    private final BookService bookService;

    @PostMapping("/create")
    public ResponseEntity<BookRS> createBook(@RequestBody BookRQ dto) {

        return ResponseEntity.ok(bookService.createBook(dto));
    }

    @GetMapping("/get")
    public ResponseEntity<BookRS> getBook(@RequestParam Long id) {
        return ResponseEntity.ok(bookService.getBook(id));
    }

    @GetMapping("/list")
    public ResponseEntity<List<BookRS>> listBook() {
        return ResponseEntity.ok(bookService.listBook());
    }



}
