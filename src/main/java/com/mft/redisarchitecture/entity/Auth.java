package com.mft.redisarchitecture.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "auth")
public class Auth {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "age")
    private Integer age;

    @Column(name = "birthdate")
    private LocalDate birthdate;

    @Column(name = "name", length = 100)
    private String name;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "surname", length = 100)
    private String surname;

    @Column(name = "username", nullable = false)
    private String username;

}