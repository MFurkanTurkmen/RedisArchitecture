package com.mft.redisarchitecture.service;

import com.mft.redisarchitecture.dto.request.BookRQ;
import com.mft.redisarchitecture.dto.response.BookRS;
import com.mft.redisarchitecture.entity.Book;
import com.mft.redisarchitecture.exception.AppException;
import com.mft.redisarchitecture.mapper.IBookMapper;
import com.mft.redisarchitecture.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepository bookRepository;
    private final IBookMapper bookMapper;



    public BookRS createBook(BookRQ bookRQ) {

        Book book = new Book();
        book.setAuthor(bookRQ.getAuthor());
        book.setName(bookRQ.getName());
        book.setPageCount(bookRQ.getPageCount());
        book=bookRepository.save(book);


        return getBook(book.getId());

    }


    public BookRS getBook(Long id) {
        Book book = bookRepository.findById(id).orElseThrow(() -> new AppException("Book not found",4001));

        BookRS bookRS= new BookRS();
        bookRS.setAuthor(book.getAuthor());
        bookRS.setName(book.getName());
        bookRS.setPageCount(book.getPageCount());
        return bookRS;

    }

    //list book
    public List<BookRS> listBook() {
        List<Book> books = bookRepository.findAll();
        return books.stream().map(bookMapper::map).collect(Collectors.toList());
    }

}
