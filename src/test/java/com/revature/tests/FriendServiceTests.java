package com.revature.tests;

import com.revature.data.FriendRepository;
import com.revature.data.UserRepository;
import com.revature.models.Friend;
import com.revature.models.User;
import com.revature.service.FriendService;
import com.revature.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import com.revature.BackEndApplication;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@SpringBootTest(classes=BackEndApplication.class)
public class FriendServiceTests {
    @Mock // Add this
    private FriendRepository friendRepository;
    @Mock
    private UserRepository userRepository;

    private FriendService friendService;

    private UserService userService;

    @BeforeEach
    void setUp() {
        friendService = new FriendService(userRepository, friendRepository);
    }

    @Test
    void userCanAddFriend(){
        User testUser1 = new User(1, "Test1", "1234", "USA", 0, "test1@mail.com");
        User testUser2 = new User(2, "Test2", "1234", "USA", 0, "test2@mail.com");
        when(userRepository.findById(testUser1.getId())).thenReturn(Optional.of(testUser1));
        when(userRepository.findById(testUser2.getId())).thenReturn(Optional.of(testUser2));
        when(friendRepository.existsByFirstUserAndSecondUser(testUser1, testUser2)).thenReturn(false);
        int actualId = friendService.saveFriend(testUser1.getId(), testUser2.getId());
        assertEquals(2, actualId);
    }

    @Test
    void userAddingFriendWithIllegalIdReturnsZero(){
        User testUser1 = new User(1, "Test1", "1234", "USA", 0, "test1@mail.com");
        int actualId = friendService.saveFriend(testUser1.getId(), testUser1.getId());
        assertEquals(0, actualId);
    }

    @Test
    void userWithNegativeIdReturnsZero(){
        User testUser1 = new User(-1, "Test1", "1234", "USA", 0, "test1@mail.com");
        int actualId = friendService.saveFriend(testUser1.getId(), testUser1.getId());
        assertEquals(0, actualId);
    }

    @Test
    void userExistsByFirstUserAndSecondUser(){
        User testUser1 = new User(1, "Test1", "1234", "USA", 0, "test1@mail.com");
        User testUser2 = new User(2, "Test2", "1234", "USA", 0, "test2@mail.com");
        when(userRepository.findById(testUser1.getId())).thenReturn(Optional.of(testUser1));
        when(userRepository.findById(testUser2.getId())).thenReturn(Optional.of(testUser2));
        when(friendRepository.existsByFirstUserAndSecondUser(testUser1, testUser2)).thenReturn(true);
        int actualId = friendService.saveFriend(testUser1.getId(), testUser2.getId());
        assertEquals(0, actualId);
    }

    @Test
    void userCanAddFriendWithLesserId(){
        User testUser1 = new User(2, "Test1", "1234", "USA", 0, "test1@mail.com");
        User testUser2 = new User(1, "Test2", "1234", "USA", 0, "test2@mail.com");
        when(userRepository.findById(testUser1.getId())).thenReturn(Optional.of(testUser1));
        when(userRepository.findById(testUser2.getId())).thenReturn(Optional.of(testUser2));
        when(friendRepository.existsByFirstUserAndSecondUser(testUser2, testUser1)).thenReturn(false);
        int actualId = friendService.saveFriend(testUser1.getId(), testUser2.getId());
        assertEquals(1, actualId);
    }

    @Test
    void getFriendTest() {
        User testUser = new User(1, "test1","test1","USA",0,"test1@email.com");
        User testUser2 = new User(2, "test2","test2","USA",0,"test2@email.com");

        Friend friend = new Friend();
        friend.setFirstUser(testUser);
        friend.setSecondUser(testUser2);

        List<Friend> userOneFriends = new ArrayList<>();
        userOneFriends.add(friend);
        List<Friend> userTwoFriends = new ArrayList<>();
        userTwoFriends.add(friend);

        when(friendRepository.findByFirstUser(testUser)).thenReturn(userOneFriends);
        when(friendRepository.findBySecondUser(testUser)).thenReturn(userTwoFriends);
        when(userRepository.findById(friend.getSecondUser().getId())).thenReturn(Optional.of(testUser2));
        when(userRepository.findById(friend.getFirstUser().getId())).thenReturn(Optional.of(testUser));

        List<User> actual = friendService.getFriends(testUser.getId());
        List<User> expected = new ArrayList<>();
        expected.add(testUser);
        expected.add(testUser2);
        assertEquals(expected.size(), actual.size());
    }
}