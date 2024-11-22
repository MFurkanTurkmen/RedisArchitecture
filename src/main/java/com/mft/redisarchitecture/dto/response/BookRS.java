package com.mft.redisarchitecture.dto.response;

import jakarta.persistence.Column;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;

@AllArgsConstructor
@Getter
@Setter
@ToString
@NoArgsConstructor
public class BookRS implements Serializable {
    private Long id;

    private String author;

    private String name;

    private Integer pageCount;

    private LocalDate releaseeDate;


}
