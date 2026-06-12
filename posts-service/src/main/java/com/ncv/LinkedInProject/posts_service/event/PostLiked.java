package com.ncv.LinkedInProject.posts_service.event;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PostLiked {
    private Long postId,likedByUserId,ownerUserId;
}
