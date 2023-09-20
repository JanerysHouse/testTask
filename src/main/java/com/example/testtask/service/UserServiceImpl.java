package com.example.testtask.service;

import com.example.testtask.exception.UserExistingEmailException;
import com.example.testtask.exception.UserNotFoundException;
import com.example.testtask.model.Users;
import com.example.testtask.repository.UserRepository;
import com.example.testtask.response.ResponseHandler;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;


@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    UserRepository userRepository;

    @Override
    public ResponseEntity<?> save(Users users) {
        if(users.getEmail().equals(userRepository.findByEmail(users.getEmail()).get().getEmail()))
            throw new UserExistingEmailException("This email already in use");

        var id = userRepository.save(users).getId();

        return ResponseHandler.responseBuilder("user was saved", HttpStatus.OK, id);
    }

    @Override
    public ResponseEntity<?> findById(long id) {
        if (userRepository.findById(id).isPresent())
            throw new UserNotFoundException("User does not exist");

        return ResponseHandler
                .responseBuilder("searched user", HttpStatus.OK, userRepository.findById(id).get());
    }

    @Override
    public ResponseEntity<?> updateUser(long id) {

        if (userRepository.findById(id).isPresent())
            throw new UserNotFoundException("User does not exist");

        Users user = userRepository.findById(id).get();
        Map<String,String> updateResponse = new HashMap<>();
        updateResponse.put("id", "" + user.getId());
        updateResponse.put("previous status", user.getStatus());
        if (user.getStatus().equalsIgnoreCase("Offline")) {
            user.setStatus("Online");
            updateResponse.put("current status", user.getStatus());
        } else {
            user.setStatus("Offline");
            updateResponse.put("current status", user.getStatus());
        }
        userRepository.save(user);

        return  ResponseHandler
                .responseBuilder("user was updated", HttpStatus.OK, userRepository.findById(id).get());
    }
}
