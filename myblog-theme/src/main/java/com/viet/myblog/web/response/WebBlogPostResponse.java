package com.viet.myblog.web.response;

import lombok.Builder;
import lombok.Getter;
import org.youngmonkeys.ezyarticle.sdk.model.TermModel;
import org.youngmonkeys.ezyplatform.model.MediaNameModel;

import java.util.List;

@Getter
@Builder
public class WebBlogPostResponse {
    private final String title;
    private final String description;
    private final String content;
    private final String slug;
    private final MediaNameModel image;
    private final long publishedAt;
    private final List<TermModel> term;
}
