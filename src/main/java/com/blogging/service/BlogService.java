package com.blogging.service;

import com.blogging.dto.BlogRequest;
import com.blogging.entity.Blog;
import com.blogging.entity.PostStatus;
import com.blogging.entity.User;
import com.blogging.repository.BlogRepository;
import com.blogging.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service

public class BlogService {

    @Autowired
    private BlogRepository blogRepository;

    @Autowired
    private UserRepository userRepository;

    public Blog create(BlogRequest req, String username) {
        User user = userRepository.findByUsername(username).orElseThrow();
        Blog blog = new Blog();
        blog.setTitle(req.getTitle());
        blog.setContent(req.getContent());
        blog.setTags(req.getTags());
        blog.setStatus(req.getStatus() != null ? req.getStatus() : PostStatus.PUBLISHED);
        blog.setUser(user);
        blog.setCreatedAt(LocalDateTime.now());
        return blogRepository.save(blog);
    }

    public Blog update(Long id, BlogRequest req, String username) {
        Blog blog = blogRepository.findById(id).orElseThrow(() -> new RuntimeException("Blog not Found"));
        //only owner user can update
        if (!blog.getUser().getUsername().equals(username)) {
            throw new RuntimeException("Unauthorized");
        }
        blog.setTitle(req.getTitle());
        blog.setContent(req.getContent());
        blog.setTags(req.getTags());
        if (req.getStatus() != null) {
            blog.setStatus(req.getStatus());
        }
        return blogRepository.save(blog);
    }

    public void delete(Long id, String username) {
        Blog blog = blogRepository.findById(id).orElseThrow(() -> new RuntimeException("Blog not found"));
        if (!blog.getUser().getUsername().equals(username)) {
            throw new RuntimeException("Unauthorized");
        }
        blogRepository.deleteById(id);
    }

    public Blog getById(Long id) {
        return blogRepository.findById(id).orElseThrow(() -> new RuntimeException("Blog not found"));
    }

    public List<Blog> getBlogsByUser(String username) {
        return blogRepository.findByUserUsername(username);
    }

    public List<Blog> getAllBlogs(String search) {
        if (search != null && !search.isEmpty()) {
            return blogRepository.findByTitleContainingIgnoreCase(search);
        }
        return blogRepository.findAll();
    }
}
