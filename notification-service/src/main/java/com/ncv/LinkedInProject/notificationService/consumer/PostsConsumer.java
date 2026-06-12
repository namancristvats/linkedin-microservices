package com.ncv.LinkedInProject.notificationService.consumer;

import com.ncv.LinkedInProject.notificationService.entity.Notification;
import com.ncv.LinkedInProject.posts_service.event.PostCreated;
import com.ncv.LinkedInProject.posts_service.event.PostLiked;
import com.ncv.LinkedInProject.notificationService.service.NotificationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class PostsConsumer {
    private final NotificationService notificationService;

    @KafkaListener(topics="post_created_topic")
    public void handlePostCreated(PostCreated postCreated){
        log.info("handle Post Created: {}",postCreated);
        String message=String.format("Your connection with id: %d has created this post: %s",
                postCreated.getOwnerUserId(),postCreated.getContent());
        Notification notification=Notification.builder()
                .message(message)
                .userId(postCreated.getUserId())
                .build();
        notificationService.addNotification(notification);
    }

    @KafkaListener(topics="post_liked_topic")
    public void handlePostLiked(PostLiked postLiked){
        log.info("handle Post Liked:{}",postLiked);
        String message=String.format("User with id: %d has liked your post with id: %d",
                postLiked.getLikedByUserId(),postLiked.getPostId());
        Notification notification=Notification.builder()
                .userId(postLiked.getOwnerUserId())
                .message(message).build();
        notificationService.addNotification(notification);
    }
}
