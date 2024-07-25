package com.viet.myblog.web.plugin.response;

import lombok.Builder;
import lombok.Getter;
import org.youngmonkeys.ezyplatform.model.MediaNameModel;

@Getter
@Builder
public class WebCustomPostResponse {
    private final String title;
    private final String slug;
    private final long publishedDate;
    private final MediaNameModel img;
}
