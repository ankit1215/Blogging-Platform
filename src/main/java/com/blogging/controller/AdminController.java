package com.blogging.controller;

import com.blogging.entity.User;
import com.blogging.repository.BlogRepository;
import com.blogging.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BlogRepository blogRepository;

    @GetMapping("/users")
    public List<User> users() {
        return userRepository.findAll();
    }

    @DeleteMapping("/blog/{id}")
    public String delete(@PathVariable Long id) {
        blogRepository.deleteById(id);
        return "Deleted";
    }
}
