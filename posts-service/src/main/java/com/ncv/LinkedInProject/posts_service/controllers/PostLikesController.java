package com.ncv.LinkedInProject.posts_service.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.ncv.LinkedInProject.posts_service.services.PostLikeService;

@RequestMapping("/likes")
@RequiredArgsConstructor
@RestController
public class PostLikesController {

    private final PostLikeService postLikeService;

    @PostMapping("/{postId}")
    public ResponseEntity<Void> likePost(@PathVariable Long postId) {
        postLikeService.likePost(postId);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{postId}")
    public ResponseEntity<Void> unlikePost(@PathVariable Long postId) {
        postLikeService.unlikepost(postId);
        return ResponseEntity.noContent().build();
    }
}

