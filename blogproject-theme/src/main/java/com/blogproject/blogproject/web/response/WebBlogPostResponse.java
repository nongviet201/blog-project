package com.blogproject.blogproject.web.response;

import lombok.Builder;
import lombok.Getter;
import org.youngmonkeys.ezyplatform.model.MediaNameModel;

@Getter
@Builder
public class WebBlogPostResponse {
    private final String title;
    private final String description;
    private final String slug;
    private final MediaNameModel image;
    private final long publishedAt;
}
