package com.ncv.LinkedInProject.posts_service.event;

import lombok.Data;

@Data
public class PostLiked {
    private Long ownerUserId;
    private Long postId;
    private Long likedByUserId;
}
