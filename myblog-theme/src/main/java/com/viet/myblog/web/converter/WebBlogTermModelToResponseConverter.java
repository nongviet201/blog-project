package com.viet.myblog.web.converter;

import com.viet.myblog.web.response.WebBlogTermResponse;
import com.tvd12.ezyfox.bean.annotation.EzySingleton;
import org.youngmonkeys.ezyarticle.sdk.model.TermModel;

@EzySingleton
public class WebBlogTermModelToResponseConverter {
    public WebBlogTermResponse toTermResponse(
        TermModel termModel
    ) {
        return WebBlogTermResponse
            .builder()
            .name(termModel.getName())
            .slug(termModel.getSlug())
            .build();
    }
}
