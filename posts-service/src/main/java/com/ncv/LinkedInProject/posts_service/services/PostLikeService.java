package com.ncv.LinkedInProject.posts_service.services;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import com.ncv.LinkedInProject.posts_service.auth.AuthContextHolder;
import com.ncv.LinkedInProject.posts_service.entity.Post;
import com.ncv.LinkedInProject.posts_service.entity.PostLike;
import com.ncv.LinkedInProject.posts_service.event.PostLiked;
import com.ncv.LinkedInProject.posts_service.exceptions.BadRequestException;
import com.ncv.LinkedInProject.posts_service.exceptions.ResourceNotFoundException;
import com.ncv.LinkedInProject.posts_service.repositories.PostLikeRepository;
import com.ncv.LinkedInProject.posts_service.repositories.PostRepository;

@Service
@RequiredArgsConstructor
public class PostLikeService {
    private final PostLikeRepository postLikeRepository;
    private final PostRepository postRepository;
    private final KafkaTemplate<Long,PostLiked> postLikedKafkaTemplate;

    @Transactional
    public void likePost(Long postId){
        Long userId = AuthContextHolder.getCurrentUserId();
        Post post=postRepository.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post with given id not exist"+postId));
        boolean hasAlreadyExist= postLikeRepository.existsByUserIdAndPostId(userId,postId);
        if(hasAlreadyExist){
             throw new BadRequestException("You cannot Like the post again!!!");
        }
        PostLike postLike=new PostLike();
        postLike.setPostId(postId);
        postLike.setUserId(userId);
        postLikeRepository.save(postLike);
        //        TODO: send notification to the owner of the post
        PostLiked postLiked=PostLiked.builder()
                .postId(postId)
                .likedByUserId(userId)
                .ownerUserId(post.getUserId()).build();
        postLikedKafkaTemplate.send("post_liked_topic",postLiked);

    }

    @Transactional
    public void unlikepost(Long postId){
        postRepository.findById(postId).orElseThrow(()
                -> new ResourceNotFoundException("Post not found with ID: "+postId));

        boolean hasAlreadyLiked = postLikeRepository.existsByUserIdAndPostId(1L, postId);
        if(!hasAlreadyLiked) throw new BadRequestException("You cannot unlike the post that you have not liked yet");

        postLikeRepository.deleteByUserIdAndPostId(1L, postId);
    }
}
