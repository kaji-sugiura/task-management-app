package com.example.demo.infrastructure.repository;

import com.example.demo.infrastructure.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * ユーザRepository
 */
public interface UserRepository extends JpaRepository<User, Long> {
}
