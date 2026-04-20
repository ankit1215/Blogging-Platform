package com.blogging.controller;

import com.blogging.entity.User;
import com.blogging.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/profile")
    public User getProfile(Authentication auth){
        return userRepository.findByUsername(auth.getName()).orElseThrow(()-> new RuntimeException("User not found"));
    }
}
