package com.revature.models;


import com.revature.BackEndApplication;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
@SpringBootTest(classes= BackEndApplication.class)

public class UserModelTests {

    @Test
    void testGetEmail(){
        User testUser = new User(1, "Dion", "1234", "USA", 0, "dion@dion.com");
        String expected= "dion@dion.com";
        String actual= testUser.getEmail();
        assertEquals(expected, actual);
    }

    @Test
    void testSetEmail(){
        User testUser = new User(1, "Dion", "1234", "USA", 0, "dion@dion.com");
        testUser.setEmail("dion@gmail.com");
        String expected= "dion@gmail.com";
        String actual= testUser.getEmail();
        assertEquals(expected, actual);
    }

    @Test
    void testGetPoints(){
        User testUser = new User(1, "Dion", "1234", "USA", 50, "dion@dion.com");
        int expected= 50;
        int actual= testUser.getPoints();
        assertEquals(expected, actual);
    }

    @Test
    void testSetPoints(){
        User testUser = new User(1, "Dion", "1234", "USA", 50, "dion@dion.com");
        testUser.setPoints(100);
        int expected= 100;
        int actual= testUser.getPoints();
        assertEquals(expected, actual);
    }

    @Test
    void testGetCountry(){
        User testUser = new User(1, "Dion", "1234", "USA", 50, "dion@dion.com");
        String expected= "USA";
        String actual= testUser.getCountry();
        assertEquals(expected, actual);
    }

    @Test
    void testSetCountry(){
        User testUser = new User(1, "Dion", "1234", "USA", 50, "dion@dion.com");
        testUser.setCountry("GER");
        String expected= "GER";
        String actual= testUser.getCountry();
        assertEquals(expected, actual);
    }

    @Test
    void testGetPassword(){
        User testUser = new User(1, "Dion", "1234", "USA", 50, "dion@dion.com");
        String expected= "1234";
        String actual= testUser.getPassword();
        assertEquals(expected, actual);
    }

    @Test
    void testSetPassword(){
        User testUser = new User(1, "Dion", "1234", "USA", 50, "dion@dion.com");
        testUser.setPassword("password");
        String expected= "password";
        String actual= testUser.getPassword();
        assertEquals(expected, actual);
    }

    @Test
    void testGetUsername(){
        User testUser = new User(1, "Dion", "1234", "USA", 50, "dion@dion.com");
        String expected= "Dion";
        String actual= testUser.getUsername();
        assertEquals(expected, actual);
    }

    @Test
    void testSetUsername(){
        User testUser = new User(1, "Dion", "1234", "USA", 50, "dion@dion.com");
        testUser.setUsername("Sam");
        String expected= "Sam";
        String actual= testUser.getUsername();
        assertEquals(expected, actual);
    }



    @Test
    void testGetId(){
        User testUser = new User(1, "Dion", "1234", "USA", 50, "dion@dion.com");
        int expected= 1;
        int actual= testUser.getId();
        assertEquals(expected, actual);
    }

    @Test
    void testSetId(){
        User testUser = new User(1, "Dion", "1234", "USA", 50, "dion@dion.com");
        testUser.setId(2);
        int expected= 2;
        int actual= testUser.getId();
        assertEquals(expected, actual);
    }

    @Test
    void testToString(){
        User testUser = new User(1, "Dion", "1234", "USA", 50, "dion@dion.com");
        String expected= "User(id=1, username=Dion, password=1234, country=USA, points=50, email=dion@dion.com)";
        String actual= testUser.toString();
        assertEquals(expected, actual);

    }










}
