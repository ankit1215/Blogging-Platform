package com.blogging.controller;

import com.blogging.entity.Blog;
import com.blogging.entity.Role;
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

    @DeleteMapping("/users/{id}")
    public String deleteUser(@PathVariable Long id){
        userRepository.deleteById(id);
        return "User Deleted";
    }

    @PutMapping("/users/{id}/role")
    public String updateRole(@PathVariable Long id, @RequestParam String role){
        User user = userRepository.findById(id).orElseThrow(()-> new RuntimeException("User not found"));
        user.setRole(Role.valueOf(role));
        userRepository.save(user);
        return "Role Updated";
    }

    @GetMapping("/blogs")
    public List<Blog> allBlogs(){
        return blogRepository.findAll();
    }

    @DeleteMapping("/blog/{id}")
    public String delete(@PathVariable Long id) {
        blogRepository.deleteById(id);
        return "Blog Deleted";
    }
}
