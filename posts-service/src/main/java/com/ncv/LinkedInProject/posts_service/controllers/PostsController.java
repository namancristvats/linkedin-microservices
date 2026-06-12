package com.ncv.LinkedInProject.posts_service.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.ncv.LinkedInProject.posts_service.Dtos.PostCreateRequestDto;
import com.ncv.LinkedInProject.posts_service.Dtos.PostDto;
import com.ncv.LinkedInProject.posts_service.auth.AuthContextHolder;
import com.ncv.LinkedInProject.posts_service.services.PostService;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/core")
@Slf4j
public class PostsController {

    private final PostService postService;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<PostDto> createPosts(@RequestPart("post") PostCreateRequestDto postCreateRequestDto,
                                               @RequestPart("file") MultipartFile file) {
        PostDto postDto=postService.createPosts(postCreateRequestDto,file);
        return new ResponseEntity<>(postDto, HttpStatus.CREATED);
    }

    @GetMapping("/{postId}")
    public ResponseEntity<PostDto> getPostById(@PathVariable Long postId){
        Long userId= AuthContextHolder.getCurrentUserId();
        log.info("Current User Id is {}",userId);
        PostDto postDto=postService.getPostById(postId);
        return new ResponseEntity<>(postDto,HttpStatus.OK);
    }

    @GetMapping("/user/{userId}/allPosts")
    public ResponseEntity<List<PostDto>> getAllPostByUser(@PathVariable Long userId){
        List<PostDto> posts=postService.getAllPostOfUser(userId);
        return new ResponseEntity<>(posts,HttpStatus.OK);
    }
}
