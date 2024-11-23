package com.mft.redisarchitecture;

import com.mft.redisarchitecture.entity.Book;
import com.mft.redisarchitecture.repository.BookRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RedisArchitectureApplication {

    public static void main(String[] args) {
        SpringApplication.run(RedisArchitectureApplication.class, args);
    }
//    @Autowired
//    BookRepository bookRepository;

//    @PostConstruct
//    public void init() {
//        new Thread(() -> {
//                bookSave();
//
//        }).start();
//
//    }
//
//
//    public void bookSave() {
//        for (int i = 0; i < 100000; i++) {
//            Book book = new Book();
//            book.setAuthor("Author " + i);
//            book.setName("Book " + i);
//            book.setPageCount(i * 3);
//            bookRepository.save(book);
//            if (i%5000==0) {
//                System.out.println(i+" kayıt eklendi");
//            }
//
//        }
//        System.err.println("Kayıt eklendi");
//
//    }



}
