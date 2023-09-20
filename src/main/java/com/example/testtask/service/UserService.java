package com.example.testtask.service;

import com.example.testtask.model.Users;
import org.springframework.http.ResponseEntity;

import java.util.Map;

public interface UserService {

    ResponseEntity<?> save(Users users);
    ResponseEntity<?> findById(long id);
    ResponseEntity<?> updateUser(long id);

}
