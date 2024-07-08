package com.blogproject.blogproject.web.response;

import lombok.Builder;
import lombok.Getter;
import org.youngmonkeys.ezyplatform.model.MediaNameModel;

@Getter
@Builder
public class WebHighlightPostResponse {
    private final String postSlug;
    private final MediaNameModel image;
}
