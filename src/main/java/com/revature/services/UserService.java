package com.revature.services;

import com.revature.data.UserRepository;
import com.revature.models.User;
import com.revature.models.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional(propagation= Propagation.REQUIRES_NEW)
    public User processRegister(User newUser) {
        newUser.setRole(UserRole.BASIC_USER);
        newUser.setRegisterDateTime(LocalDateTime.now());
        return userRepository.save(newUser);
    }

    public User processLogin(String username, String password) {
        return userRepository.findUserByUsernameAndPassword(username, password);
    }

    @Transactional(readOnly = true)
    public List<User> getAllUser(){
        return userRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<User> getById(int id) {
        return userRepository.findById(id);
    }

    public User updateUser(User updateUser) {
        return userRepository.save(updateUser);
    }

    public void deleteUser(int id) {
        userRepository.deleteById(id);
    }
}
