package com.blogproject.blogproject.web.converter;

import com.blogproject.blogproject.web.response.WebBlogTermResponse;
import com.tvd12.ezyfox.bean.annotation.EzySingleton;
import org.youngmonkeys.ezyarticle.sdk.model.TermModel;

import java.util.List;

@EzySingleton
public class WebBlogTermModelToResponseConverter {
    public WebBlogTermResponse toTermResponse (
        TermModel termModel
    ) {
        return WebBlogTermResponse
            .builder()
            .name(termModel.getName())
            .slug(termModel.getSlug())
            .build();
    }
}
