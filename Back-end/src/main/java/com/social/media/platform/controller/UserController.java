package com.social.media.platform.controller;

import com.social.media.platform.dto.UserDTO;
import com.social.media.platform.entity.User;
import com.social.media.platform.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/login")
    public ResponseEntity<UserDTO> login(@RequestBody User user) {
        UserDTO response = new UserDTO();

        User existingUser = userRepository.findByEmail(user.getEmail());

        if (existingUser != null && existingUser.getPassword().equals(user.getPassword())) {
            response.setUser(existingUser);
            response.setMessage("User logged successfully!");
            response.setError(false);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        } else {
            response.setUser(null);
            response.setMessage("Invalid email or password!");
            response.setError(true);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }
    }

    @PostMapping("/register")
    public ResponseEntity<UserDTO> register(@RequestBody User user) {
        UserDTO response = new UserDTO();

        User existingUser = userRepository.findByEmail(user.getEmail());
        if (existingUser != null) {
            response.setUser(null);
            response.setMessage("This email address has been registered already!");
            response.setError(true);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        }
        User registerdUser = userRepository.save(user);
        response.setUser(registerdUser);
        response.setMessage("User registered successfully!");
        response.setError(false);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
