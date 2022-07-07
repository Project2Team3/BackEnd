package com.revature.services;

import com.revature.data.UserRepository;
import com.revature.dto.Credentials;
import com.revature.exceptions.AuthenticationException;
import com.revature.exceptions.UserNotFoundException;
import com.revature.models.User;
import com.revature.models.UserRole;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final Logger log = LoggerFactory.getLogger(this.getClass());
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User authenticate(Credentials credentials) {
        return userRepository.findUserByUsernameAndPassword(credentials.getUsername(), credentials.getPassword())
                .orElseThrow(AuthenticationException::new);
    }

    @Transactional(propagation= Propagation.REQUIRES_NEW)
    public User processRegister(User newUser) {
//        newUser.set(UserRole.BASIC_USER);
//        newUser.setRegisterDateTime(LocalDateTime.now());
        return userRepository.save(newUser);
    }

//    public User processLogin(String username, String password) {
//        return userRepository.findUserByUsernameAndPassword(username, password);
//    }

    @Transactional(readOnly = true)
    public List<User> getAllUser(){
        return userRepository.findAll();
    }


    public User updateUser(User updateUser) {
        return userRepository.save(updateUser);
    }

    public void deleteUser(int id) {
        userRepository.deleteById(id);
    }

//    @Transactional(readOnly=true)
//    public User getById(int id) {
//        if (id <= 0) {
//            log.warn("Id cannot be <= 0. Id passed was: {}", id);
//            return null;
//        } else {
//            return userRepository.findById(id)
//                    .orElseThrow(() -> new UserNotFoundException("No user found with id " + id));
//        }
//
//    }
}
