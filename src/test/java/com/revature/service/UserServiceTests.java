package com.revature.service;

import com.revature.dto.Credentials;
import com.revature.exceptions.UserNotFoundException;
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
		User testUser = new User(1, "Dion", "1234", "USA", 0, "dion@dion.com");
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
	void getByUsernameReturnsUser (){

		User testUser = new User(1, "Dion", "1234", "USA", 0, "dion@dion.com");
		when(userRepository.findByUsername("Dion")).thenReturn(Optional.of(testUser));
		User actual = userService.getByUsername("Dion");
		assertEquals(actual, testUser);
	}

	@Test
	void getByIdReturnsUser (){

		User testUser = new User(1, "Dion", "1234", "USA", 0, "dion@dion.com");
		when(userRepository.findById(1)).thenReturn(Optional.of(testUser));
		User actual = userService.getById(1);
		assertEquals(actual, testUser);
	}

	@Test
	void testThrowsNoUserFoundException (){


		when(userRepository.findByUsername("xxx")).thenThrow(new UserNotFoundException("No user found with username: xxx"));

		assertThrows(UserNotFoundException.class, () -> {userRepository.findByUsername("xxx");});
	}

//	@Test
//	void testAuthenticateReturnsUser(){
//
//		User testUser = new User(1, "Dion", "1234", "USA", 0, "dion@dion.com");
//
//		when(userRepository.findUserByUsernameAndPassword("Dion",  "1234")).thenReturn(Optional.of(testUser))
//		User actual = userService.authenticate();
//		assertEquals(actual, testUser);
//	}

	@Test
	void test_authenticate() {
		User testUser = new User(1, "Dion", "1234", "USA", 0, "dion@dion.com");
		Credentials credentials = new Credentials(testUser.getUsername(), testUser.getPassword());
		when(userRepository.findUserByUsernameAndPassword(credentials.getUsername(), credentials.getPassword())).thenReturn(Optional.of(testUser));
		User actualUser = userService.authenticate(credentials);
		assertEquals(testUser, actualUser);
	}

	@Test
	public void testRemoveUser() {
		int Id=1;

	// perform the call
	userService.remove(1);

	// verify the mocks
	verify(userRepository, times(1)).deleteById(eq(Id));
}
//	@Test
//	public void whenIdIsNull_thenExceptionIsThrown() {
//
//
//		assertThrows(UserNotFoundException.class, () -> Optional
//				.ofNullable(userRepository.getReferenceById(null))
//				.orElseThrow(new UserNotFoundException("No user found with id " +id)));
//	}

//	@Test
//	void testAuthenticationException (){
//
//
//		when(userRepository.findByUsername("xxx")).thenThrow(new UserNotFoundException("No user found with username: xxx"));
//
//		assertThrows(UserNotFoundException.class, () -> {userRepository.findByUsername("xxx");});
//	}












}