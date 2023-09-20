package com.example.testtask.controller;

import com.example.testtask.model.Users;
import com.example.testtask.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@AllArgsConstructor
@RestController
@RequestMapping("/user")
public class Controller {

    UserService userService;

    @GetMapping("/{userId}")
    public ResponseEntity<?> getUser(@PathVariable String userId) {
        return userService.findById(Integer.parseInt(userId));
    }


    @PostMapping
    public ResponseEntity<?> addUser(@RequestBody Users users) {
        return userService.save(users);

    }

    @PutMapping("/{userId}")
    public ResponseEntity<?> updateUserStatus(@PathVariable String userId) {
        return userService.updateUser(Integer.parseInt(userId));
    }


}
