package com.revature.web;

import com.revature.models.User;
import com.revature.service.FriendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins="*", allowedHeaders="*")
@RequestMapping("/friends")
public class FriendController {

    private final FriendService friendService;

    @Autowired
    public FriendController(FriendService friendService) {
        this.friendService = friendService;
    }

    @GetMapping("")
    public ResponseEntity<List<User>> getFriends(@RequestParam("currentUserId")int currentUserId) {
        return ResponseEntity.ok(friendService.getFriends(currentUserId));
    }

    @GetMapping("add")
    public ResponseEntity<?> addUser(@RequestParam("currentUserId") int currentUserId, @RequestParam("friendId") int friendId) {
        friendService.saveFriend(currentUserId, friendId);
        return ResponseEntity.ok("Friend added successfully");
    }
}
