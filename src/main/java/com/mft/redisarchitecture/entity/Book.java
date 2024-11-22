package com.mft.redisarchitecture.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "book")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "author", nullable = false)
    private String author;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "page_count")
    private Integer pageCount;

    @Column(name = "release_date")
    private LocalDate releaseDate;

}