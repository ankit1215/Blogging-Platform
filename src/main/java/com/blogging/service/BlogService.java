package com.blogging.service;

import com.blogging.entity.Blog;
import com.blogging.entity.User;
import com.blogging.repository.BlogRepository;
import com.blogging.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BlogService {
    private final BlogRepository blogRepository;
    private final UserRepository userRepository;

    public Blog create(Blog blog, String username) {

        User user = userRepository.findByUsername(username)
                .orElseThrow();

        blog.setUser(user);
        blog.setCreatedAt(LocalDateTime.now());

        return blogRepository.save(blog);
    }

    public List<Blog> getAllBlogs(String search) {

        if (search != null && !search.isEmpty()) {
            return blogRepository.findByTitleContainingIgnoreCase(search);
        }

        return blogRepository.findAll();
    }
}
