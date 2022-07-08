package com.revature.web;

import com.revature.models.User;
import com.revature.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Set;

@RestController
@CrossOrigin(origins="*", allowedHeaders="*")
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        super();
        this.userService = userService;
    }

    @GetMapping // return all users by sending a GET request to http://localhost:5000/api/users
    public ResponseEntity<Set<User>> getAll() {
        return ResponseEntity.ok(userService.findAll());
    }

    @PostMapping("/add") // http://localhost:5000/api/users/add
    public ResponseEntity<User> addUser(@Valid @RequestBody User u) {
        return  ResponseEntity.ok(userService.add(u));
    }

    @GetMapping("/{id}") // allows the client to send the request http://localhost:5000/api/users/2
    public ResponseEntity<User> findUserById(@PathVariable("id") int id) {
        return ResponseEntity.ok(userService.getById(id));
    }

    @GetMapping("/find/{username}") // allows the client to send the request http://localhost:5000/api/users/thehulk
    public ResponseEntity<User> findUserByUsername(@PathVariable("username") String username) {
        return ResponseEntity.ok(userService.getByUsername(username));
    }

    @DeleteMapping("/{id}")
    public void removeUser(@PathVariable("id") int id) {
        userService.remove(id);
    }

}