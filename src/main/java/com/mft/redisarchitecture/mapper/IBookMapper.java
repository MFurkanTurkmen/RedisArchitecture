package com.mft.redisarchitecture.mapper;

import com.mft.redisarchitecture.dto.request.BookRQ;
import com.mft.redisarchitecture.dto.response.BookRS;
import com.mft.redisarchitecture.entity.Book;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface IBookMapper {

    Book map(BookRQ bookRQ);

    BookRS map(Book book);
}
