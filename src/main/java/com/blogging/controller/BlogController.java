package com.blogging.controller;

import com.blogging.entity.Blog;
import com.blogging.service.BlogService;
import org.springframework.security.core.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/blogs")
public class BlogController {

    @Autowired
    private BlogService blogService;

    @PostMapping("/create")
    public Blog create(@RequestBody Blog blog, Authentication auth){
        return blogService.create(blog, auth.getName());
    }

    @GetMapping("/getAllBlogs")
    public List<Blog> getAll(@RequestParam(required = false) String search) {
        return blogService.getAllBlogs(search);
    }
}
