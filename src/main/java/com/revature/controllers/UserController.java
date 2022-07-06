package com.revature.controllers;


import com.revature.models.User;
import com.revature.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

// all info related to users
@RestController
@RequestMapping("/users")
public class UserController {

    // DB -> DAO -> Service -> Controller... Such a Pain

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUser();
    }

    @GetMapping("/{id}")
    public User findUserById(@PathVariable("id") int id) {
        Optional<User> user = userService.getById(id);
        if (user.isPresent()) {
            return ResponseEntity.ok(user.get()).getBody();
        }
        return new ResponseEntity<User>(HttpStatus.NO_CONTENT).getBody();
    }

    @PostMapping
    public User registerUser(User newUser) {
        return userService.processRegister(newUser);
    }

    @PutMapping
    public User updateUser(@Valid @RequestBody User updatedUser){
        return userService.updateUser(updatedUser);
    }
}
