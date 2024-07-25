package com.viet.myblog.web.plugin.converter;

import com.viet.myblog.web.plugin.response.WebBlogTermResponse;
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
