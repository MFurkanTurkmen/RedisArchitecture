package com.mft.redisarchitecture.mapper;

import lombok.Value;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * DTO for {@link com.mft.redisarchitecture.entity.Book}
 */
@Value
public class BookDto implements Serializable {
    Long id;
    String author;
    String name;
    Integer pageCount;
    LocalDate releaseDate;
}