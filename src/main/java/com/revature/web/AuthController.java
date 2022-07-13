package com.revature.web;

import com.revature.dto.Credentials;
import com.revature.models.User;
import com.revature.service.UserService;
import com.revature.util.JwtTokenManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@RestController
@CrossOrigin(origins="*", allowedHeaders="*")
@RequestMapping("/login")
public class AuthController {
	
	private final UserService userService;
	private final JwtTokenManager tokenManager;
	
	@Autowired
	public AuthController(UserService userService, JwtTokenManager tokenManager) {
		super();
		this.userService = userService;
		this.tokenManager = tokenManager;
	}

	@PostMapping
	public User login(@RequestBody Credentials credentials, HttpServletResponse response) {
		
		User user = userService.authenticate(credentials);
		
		if (user != null) {
			String token = tokenManager.issueToken(user);
			
			response.addHeader("user-token", token);
			response.addHeader("Access-Control-Expose-Headers", "user-token");
			response.setStatus(200);
			
			return user;
		} else {
			response.setStatus(401);
			return null;
		}
	}	
}