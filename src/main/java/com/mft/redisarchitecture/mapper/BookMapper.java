package com.mft.redisarchitecture.mapper;

import com.mft.redisarchitecture.dto.response.BookRS;
import com.mft.redisarchitecture.entity.Book;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface BookMapper {
    Book toEntity(BookRS bookDto);

    BookRS toDto(Book book);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Book partialUpdate(BookRS bookDto, @MappingTarget Book book);
}