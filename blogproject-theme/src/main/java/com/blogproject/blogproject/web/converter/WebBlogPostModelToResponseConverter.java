package com.blogproject.blogproject.web.converter;

import com.blogproject.blogproject.web.response.WebBlogPostResponse;
import com.blogproject.blogproject.web.response.WebHighlightPostResponse;
import com.blogproject.blogproject.web.response.WebCustomPostResponse;
import com.blogproject.blogproject.web.service.WebHtmlSummarizerService;
import com.tvd12.ezyfox.bean.annotation.EzySingleton;
import org.youngmonkeys.ezyarticle.sdk.model.PostModel;
import org.youngmonkeys.ezyarticle.sdk.model.TermModel;
import org.youngmonkeys.ezyplatform.model.MediaNameModel;

import java.util.List;

@EzySingleton
public class WebBlogPostModelToResponseConverter {

    public WebHighlightPostResponse toHighlightPostResponse(
        PostModel model,
        String slug,
        MediaNameModel image
    ) {
        return WebHighlightPostResponse.builder()
            .title(model.getTitle())
            .publishedAt(model.getPublishedAt())
            .postSlug(slug)
            .image(image)
            .build();
    }

    public WebBlogPostResponse toBlogPostResponse(
        PostModel model,
        String slug,
        MediaNameModel image,
        List<TermModel> term
    ) {
        return WebBlogPostResponse.builder()
            .title(model.getTitle())
            .description(WebHtmlSummarizerService.summarizeHtml(model.getContent(), 200))
            .content(model.getContent())
            .slug(slug)
            .image(image)
            .publishedAt(model.getPublishedAt())
            .term(term)
            .build();
    }

    public WebCustomPostResponse toCustomPostResponse(
        PostModel model,
        String slug,
        MediaNameModel img) {
        return WebCustomPostResponse.builder()
            .title(model.getTitle())
            .slug(slug)
            .publishedDate(model.getPublishedAt())
            .img(img)
            .build();
    }
}
