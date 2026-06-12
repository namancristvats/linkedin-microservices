package com.ncv.LinkedInProject.posts_service.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.ncv.LinkedInProject.posts_service.entity.PostLike;

public interface PostLikeRepository extends JpaRepository<PostLike,Long> {
    boolean existsByUserIdAndPostId(long l, Long postId);

    void deleteByUserIdAndPostId(long l, Long postId);
}
