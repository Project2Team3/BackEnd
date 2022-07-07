package com.revature.controllers;

import com.revature.dto.Credentials;
import com.revature.models.User;
import com.revature.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

// Login Authentication I think
@RestController
@RequestMapping("/login")
public class AuthController {
    private final UserService userService;

    public AuthController(UserService userService){
        this.userService = userService;
    }
    // Post to avoid showing the password
    @PostMapping
    public ResponseEntity<User> login(@Valid @RequestBody Credentials credentials){
        User user = userService.authenticate(credentials);
        if (user == null ){
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        } else {
            return ResponseEntity.ok(user);
        }
    }

}
