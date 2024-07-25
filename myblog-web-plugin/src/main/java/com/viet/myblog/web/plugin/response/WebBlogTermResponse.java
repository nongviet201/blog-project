package com.viet.myblog.web.plugin.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class WebBlogTermResponse {
    private String name;
    private String slug;
}
