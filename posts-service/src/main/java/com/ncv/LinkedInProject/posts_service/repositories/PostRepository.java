package com.ncv.LinkedInProject.posts_service.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.ncv.LinkedInProject.posts_service.entity.Post;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post,Long> {
    List<Post> findByUserId(Long userId);
}
