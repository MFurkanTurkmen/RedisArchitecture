package com.mft.redisarchitecture.repository;

import com.mft.redisarchitecture.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {
    @Query(value = "SELECT * FROM book b LIMIT :adet", nativeQuery = true)
    List<Book> findBooks(@Param("adet") int adet);
}