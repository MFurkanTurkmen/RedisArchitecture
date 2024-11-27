package com.mft.redisarchitecture.service;

import com.mft.redisarchitecture.dto.request.BookRQ;
import com.mft.redisarchitecture.dto.response.BookRS;
import com.mft.redisarchitecture.entity.Book;
import com.mft.redisarchitecture.exception.AppException;
import com.mft.redisarchitecture.mapper.BookMapper;
import com.mft.redisarchitecture.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepository bookRepository;
    private final BookMapper bookMapper;

    @Cacheable(value = "books", key = "#id")
    public BookRS getBook(Long id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new AppException("Book not found", 4001));
        return bookMapper.toDto(book);
    }

    @CacheEvict(value = "books", allEntries = true)
    public BookRS createBook(BookRQ bookRQ) {
        Book book = new Book();
        book.setAuthor(bookRQ.getAuthor());
        book.setName(bookRQ.getName());
        book.setPageCount(bookRQ.getPageCount());
        return bookMapper.toDto(bookRepository.save(book));
    }

    @Cacheable(value = "books", key = "'list_' + #adet")
    public List<BookRS> listBook(int adet) {
        return bookRepository.findBooks(adet).stream()
                .map(bookMapper::toDto)
                .collect(Collectors.toList());
    }
}