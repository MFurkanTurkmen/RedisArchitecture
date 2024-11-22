package com.mft.redisarchitecture.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@Getter
@ToString
@NoArgsConstructor
public class BookRQ {
    private String name;
    private String author;
    private int pageCount;
}
