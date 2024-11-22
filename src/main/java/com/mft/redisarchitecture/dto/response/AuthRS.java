package com.mft.redisarchitecture.dto.response;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AuthRS  {
   private Long id;
    private Integer age;
    private LocalDate birthdate;
    private String name;
    private String password;
    private String surname;
    private String username;
}