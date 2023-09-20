package com.example.testtask.repository;

import com.example.testtask.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<Users, Long> {
    Optional<Users> findById(Long id);
    Optional<Users> findByEmail(String email);
}
