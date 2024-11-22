package com.mft.redisarchitecture.dto.response;

import jakarta.persistence.Column;
import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor
@Getter
@Setter
@ToString
@NoArgsConstructor
public class BookRS {
    private Long id;

    private String author;

    private String name;

    private Integer pageCount;

    private LocalDate releaseeDate;


}
