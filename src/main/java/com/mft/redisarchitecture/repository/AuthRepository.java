package com.mft.redisarchitecture.repository;

import com.mft.redisarchitecture.entity.Auth;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthRepository extends JpaRepository<Auth, Long> {
}