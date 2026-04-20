package com.blogging.controller;

import com.blogging.dto.BlogRequest;
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
    public Blog create(@RequestBody BlogRequest blog, Authentication auth) {
        return blogService.create(blog, auth.getName());
    }

    @GetMapping("/getAllBlogs")
    public List<Blog> getAll(@RequestParam(required = false) String search) {
        return blogService.getAllBlogs(search);
    }

    @PutMapping("/update/{id}")
    public Blog update(@PathVariable Long id, @RequestBody BlogRequest blogRequest, Authentication auth) {
        return blogService.update(id, blogRequest, auth.getName());

    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable Long id, Authentication auth) {
        blogService.delete(id, auth.getName());
        return "Deleted";
    }

    //Get my posts (for Profile section)
    @GetMapping("/myBlogs")
    public List<Blog> myBlogs(Authentication auth) {
        return blogService.getBlogsByUser(auth.getName());
    }

    //Get single blog (for Read More)
    @GetMapping("/{id}")
    public Blog getById(@PathVariable Long id) {
        return blogService.getById(id);
    }
}
