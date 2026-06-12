package com.ncv.LinkedInProject.posts_service.event;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PostCreated {
    private Long ownerUserId;
    private Long userId;
    private Long postId;
    private String content;
}
