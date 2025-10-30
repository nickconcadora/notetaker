package com.nick.notetaker.service;

import com.nick.notetaker.model.User;
import com.nick.notetaker.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;



    public User createUser(User user) {
        if(userRepository.existsByEmail(user.getEmail().toLowerCase())) {
            throw new RuntimeException("Email already in use");
        }
        User createdUser = new User();
        createdUser.setFirstName(user.getFirstName());
        createdUser.setLastName(user.getLastName());
        createdUser.setEmail(user.getEmail());
        createdUser.setPassword(user.getPassword());

        userRepository.saveAndFlush(createdUser);

        return createdUser;
    }

    public User getUser(UUID id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User " + id + " not found"));
    }

}
