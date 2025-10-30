package com.nick.notetaker.controller;

import com.nick.notetaker.model.User;
import com.nick.notetaker.repository.UserRepository;
import com.nick.notetaker.service.UserService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;


@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;


    @PostMapping
    private ResponseEntity<User> createUser(@RequestBody User user){
        return ResponseEntity.ok(userService.createUser(user));
    }

    @GetMapping("/{id}")
    private ResponseEntity<User> getUser(@PathVariable UUID id){
        return ResponseEntity.ok(userService.getUser(id));
    }

}
