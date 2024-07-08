package com.blogproject.blogproject.web.converter;

import com.blogproject.blogproject.web.response.WebBlogPostResponse;
import com.blogproject.blogproject.web.response.WebHighlightPostResponse;
import com.blogproject.blogproject.web.service.WebHtmlSummarizerService;
import com.tvd12.ezyfox.bean.annotation.EzySingleton;
import org.youngmonkeys.ezyarticle.sdk.model.PostModel;
import org.youngmonkeys.ezyplatform.model.MediaNameModel;

@EzySingleton
public class WebBlogModelToResponseConverter {
    WebHtmlSummarizerService htmlSummarizerService;

    public WebHighlightPostResponse toHighlightPostResponse(
        String slug,
        MediaNameModel image
    ) {
        return WebHighlightPostResponse.builder()
            .postSlug(slug)
            .image(image)
            .build();
    }

    public WebBlogPostResponse toBlogPostResponse(
        PostModel model,
        String slug,
        MediaNameModel image
    ) {
        return WebBlogPostResponse.builder()
            .title(model.getTitle())
            .description(htmlSummarizerService.summarizeHtml(model.getContent(), 200))
            .slug(slug)
            .image(image)
            .publishedAt(model.getPublishedAt())
            .build();
    }
}
