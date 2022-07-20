package com.revature.service;

import com.revature.data.FriendRepository;
import com.revature.data.UserRepository;
import com.revature.models.Friend;
import com.revature.models.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class FriendService {
    private final UserRepository userRepository;
    private final FriendRepository friendRepository;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    public FriendService(UserRepository userRepository, FriendRepository friendRepository) {
        this.userRepository = userRepository;
        this.friendRepository = friendRepository;
    }
    public Integer saveFriend(int currentUserId, int friendId){

        Friend friend = new Friend();
        if (currentUserId <= 0 || friendId <= 0) {
            logger.error("ID must be greater than 0");
            return 0;
        } else if (currentUserId == friendId) {
            logger.error("You cannot befriend yourself");
            return 0;
        }
        Optional<User> currentUserOptional = userRepository.findById(currentUserId);
        Optional<User> friendUserOptional = userRepository.findById(friendId);
        User firstUser = new User();
        User secondUser = new User();
        if (currentUserOptional.isPresent() && friendUserOptional.isPresent()) {
            if (currentUserId > friendId) {
                firstUser = friendUserOptional.get();
                secondUser = currentUserOptional.get();
            } else {
                firstUser = currentUserOptional.get();
                secondUser = friendUserOptional.get();
            }
        }


        if( !(friendRepository.existsByFirstUserAndSecondUser(firstUser,secondUser)) ){
            friend.setFirstUser(firstUser);
            friend.setSecondUser(secondUser);
            friendRepository.save(friend);
            logger.info("Friend Added Successfully");
            return friendId;
        }
        return 0;
    }

    public List<User> getFriends(int currentUserId){

        Optional<User> currentUserOptional = userRepository.findById(currentUserId);
        User currentUser = new User();
        if (currentUserOptional.isPresent()) {
            currentUser = currentUserOptional.get();
        }

        List<Friend> friendsByFirstUser = friendRepository.findByFirstUser(currentUser);
        List<Friend> friendsBySecondUser = friendRepository.findBySecondUser(currentUser);
        List<User> friendUsers = new ArrayList<>();

        for (Friend friend : friendsByFirstUser) {
            Optional<User> secondUser = userRepository.findById(friend.getSecondUser().getId());
            secondUser.ifPresent(friendUsers::add);
        }
        for (Friend friend : friendsBySecondUser) {
            Optional<User> firstUser = userRepository.findById(friend.getFirstUser().getId());
            firstUser.ifPresent(friendUsers::add);
        }
        logger.info("All friends retrieved");
        return friendUsers;

    }
}
