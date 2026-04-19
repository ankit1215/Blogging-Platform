package com.blogging.repository;

import com.blogging.entity.Blog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BlogRepository extends JpaRepository<Blog,Long> {

    List<Blog> findByTitleContainingIgnoreCase(String keyword);

    List<Blog> findByUserUsername(String username);
}
