package com.mft.redisarchitecture.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AuthRQ {
    private String password;
    private String name;
    private String username;
}