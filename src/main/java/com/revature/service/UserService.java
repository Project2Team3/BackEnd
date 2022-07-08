package com.revature.service;

import com.revature.data.UserRepository;
import com.revature.dto.Credentials;
import com.revature.exceptions.AuthenticationException;
import com.revature.exceptions.UserNotFoundException;
import com.revature.models.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        super();
        this.userRepository = userRepository;
    }

    public User authenticate(Credentials credentials) {
        return userRepository.findUserByUsernameAndPassword(credentials.getUsername(), credentials.getPassword())
                .orElseThrow(AuthenticationException::new);
    }

    @Transactional(readOnly = true)
    public Set<User> findAll() {
        return new HashSet<>(userRepository.findAll());
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public User add(User u) {
        return userRepository.save(u);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void remove(int id) {
        userRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public User getByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException("No user found with username " + username));
    }

    @Transactional(readOnly = true)
    public User getById(int id) {
        if (id <= 0) {
            logger.warn("Id cannot be <= 0. Id passed was: {}", id);
            return null;
        } else {
            return userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("No user found with id " + id));
        }

    }
}