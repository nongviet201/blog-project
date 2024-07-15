package com.viet.myblog.web.response;

import lombok.Builder;
import lombok.Getter;
import org.youngmonkeys.ezyplatform.model.MediaNameModel;

@Getter
@Builder
public class WebHighlightPostResponse {
    private final String postSlug;
    private final MediaNameModel image;
    private final String title;
    private final long publishedAt;
}
