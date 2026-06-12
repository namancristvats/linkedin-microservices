package com.ncv.LinkedInProject.posts_service.services;

import com.ncv.LinkedInProject.posts_service.client.UploaderServiceClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import com.ncv.LinkedInProject.posts_service.Dtos.PersonDto;
import com.ncv.LinkedInProject.posts_service.Dtos.PostCreateRequestDto;
import com.ncv.LinkedInProject.posts_service.Dtos.PostDto;
import com.ncv.LinkedInProject.posts_service.auth.AuthContextHolder;
import com.ncv.LinkedInProject.posts_service.client.ConnectionServiceClient;
import com.ncv.LinkedInProject.posts_service.entity.Post;
import com.ncv.LinkedInProject.posts_service.event.PostCreated;
import com.ncv.LinkedInProject.posts_service.exceptions.ResourceNotFoundException;
import com.ncv.LinkedInProject.posts_service.repositories.PostRepository;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class PostService {
    private final PostRepository postRepository;
    private final ModelMapper modelMapper;
    private final ConnectionServiceClient connectionServiceClient;
    private final UploaderServiceClient uploaderServiceClient;
    private final KafkaTemplate<Long,PostCreated> postCreatedKafkaTemplate;

    public PostDto createPosts(PostCreateRequestDto postCreateRequestDto, MultipartFile file) {
        Long userId = AuthContextHolder.getCurrentUserId();
        log.info("Creating post for user with id: {}",userId);
        ResponseEntity<String> imageUrl= uploaderServiceClient.uploadFile(file);

        Post post=modelMapper.map(postCreateRequestDto,Post.class);
        post.setUserId(userId);
        post.setImageUrl(imageUrl.getBody());
        post=postRepository.save(post);
//        TODO Get first degree connection of currentUser
        List<PersonDto> persons=connectionServiceClient.getFirstDegreeConnection(userId);
        for(PersonDto person:persons){
            log.info("First Degree Connection of"+userId+"is: {}",person);
            PostCreated postCreated=PostCreated.builder()
                    .postId(post.getId())
                    .ownerUserId(post.getUserId())
                    .content(post.getContent())
                    .userId(person.getUserId())
                    .build();
            postCreatedKafkaTemplate.send("post_created_topic",postCreated);
        }
        return modelMapper.map(post,PostDto.class);
    }
    public PostDto getPostById(Long postId){
        log.info("Getting the post with ID: {}", postId);
        Post post=postRepository.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post Not Found With Given Id"+postId));
        return modelMapper.map(post, PostDto.class);
    }
    public List<PostDto> getAllPostOfUser(Long userId){
        log.info("Getting all the posts of a user with ID: {}", userId);
        List<Post> postList = postRepository.findByUserId(userId);
        return postList.stream()
                .map(post->modelMapper.map(post,PostDto.class)).collect(Collectors.toList());
    }
}
