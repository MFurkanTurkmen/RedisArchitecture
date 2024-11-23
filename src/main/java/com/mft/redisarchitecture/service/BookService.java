package com.mft.redisarchitecture.service;

import com.mft.redisarchitecture.dto.request.BookRQ;
import com.mft.redisarchitecture.dto.response.BookRS;
import com.mft.redisarchitecture.entity.Book;
import com.mft.redisarchitecture.exception.AppException;
import com.mft.redisarchitecture.mapper.IBookMapper;
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
    private final IBookMapper bookMapper;
    private final RedisCacheService redisCacheService;

    private static final String BOOK_CACHE_PREFIX = "book:";
    private static final String BOOKS_CACHE_KEY = "books:all";
    private static final long CACHE_TTL = 1; // 1 saat

    @CacheEvict(value = "books", allEntries = true)
    public BookRS createBook(BookRQ bookRQ) {
        Book book = new Book();
        book.setAuthor(bookRQ.getAuthor());
        book.setName(bookRQ.getName());
        book.setPageCount(bookRQ.getPageCount());
        book = bookRepository.save(book);

        // Cache'i güncelle
        BookRS bookRS = getBook(book.getId());
        redisCacheService.saveWithExpiration(BOOK_CACHE_PREFIX + book.getId(),
                bookRS, CACHE_TTL, TimeUnit.SECONDS);

        return bookRS;
    }

    @Cacheable(value = "books", key = "#id")
    public BookRS getBook(Long id) {
        String cacheKey = BOOK_CACHE_PREFIX + id;

        // Önce cache'den kontrol et
        Object cachedBook = redisCacheService.get(cacheKey);
        if (cachedBook != null) {
            return (BookRS) cachedBook;
        }

        // Cache'de yoksa DB'den getir
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new AppException("Book not found", 4001));

        BookRS bookRS = new BookRS();
        bookRS.setAuthor(book.getAuthor());
        bookRS.setName(book.getName());
        bookRS.setPageCount(book.getPageCount());

        // Cache'e kaydet
        redisCacheService.saveWithExpiration(cacheKey, bookRS, CACHE_TTL, TimeUnit.HOURS);

        return bookRS;
    }

    @Cacheable(value = "books", key = "'all'")
    public List<BookRS> listBook() {
        // Önce cache'den kontrol et
        Object cachedBooks = redisCacheService.get(BOOKS_CACHE_KEY);
        if (cachedBooks != null) {
            return (List<BookRS>) cachedBooks;
        }

        // Cache'de yoksa DB'den getir
        List<Book> books = bookRepository.findAll();
        List<BookRS> bookRSList = books.stream()
                .map(bookMapper::map)
                .collect(Collectors.toList());

        // Cache'e kaydet
        redisCacheService.saveWithExpiration(BOOKS_CACHE_KEY,
                bookRSList, CACHE_TTL, TimeUnit.HOURS);

        return bookRSList;
    }

    @CacheEvict(value = "books", key = "#id")
    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
        redisCacheService.delete(BOOK_CACHE_PREFIX + id);
        redisCacheService.delete(BOOKS_CACHE_KEY);
    }

    @CacheEvict(value = "books", allEntries = true)
    public void clearCache() {
        // Tüm cache'i temizle
    }
}