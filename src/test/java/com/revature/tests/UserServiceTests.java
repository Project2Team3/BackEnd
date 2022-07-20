package com.revature.tests;

import com.revature.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import com.revature.BackEndApplication;
import com.revature.data.UserRepository;
import com.revature.models.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@SpringBootTest(classes=BackEndApplication.class)
public class UserServiceTests {
    @Mock // Add this
    private UserRepository userRepository;

    private UserService userService;

    @BeforeEach
    void setUp() {
        userService = new UserService(userRepository);
    }

    @Test
    void findAllUsers() {
        User testUser = new User(1, "Dion", "1234", "USA", 0, "dion@dion.com");
        List<User> uArray = new ArrayList<>();
        uArray.add(testUser);
        when(userRepository.findAll()).thenReturn(uArray);
        List<User> actual = new ArrayList<>(userService.findAll());
        assertEquals(actual.size(), uArray.size());
    }

    @Test
    void testGetByIdNull_ifIDLessThanZero() {
        User actual = userService.getById(-1);
        assertNull(actual);
    }

    @Test
    void addUser_returnsUser() {
        User testUser = new User(1, "Dion", "1234", "USA", 0, "dion@dion.com");
        when(userRepository.save(testUser)).thenReturn(testUser);
        User actual = userService.add(testUser);
        assertEquals(actual, testUser);
    }

    @Test
    void getByIdReturnsUser() {
        User testUser = new User(1, "Dion", "1234", "USA", 0, "dion@dion.com");
        when(userRepository.findById(1)).thenReturn(Optional.of(testUser));
        User actual = userService.getById(1);
        assertEquals(actual, testUser);
    }
}
