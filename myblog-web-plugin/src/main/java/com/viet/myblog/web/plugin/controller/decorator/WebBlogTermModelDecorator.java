package com.viet.myblog.web.plugin.controller.decorator;

import com.tvd12.ezyfox.bean.annotation.EzySingleton;
import com.tvd12.ezyfox.io.EzyLists;
import com.viet.myblog.web.plugin.converter.WebBlogTermModelToResponseConverter;
import com.viet.myblog.web.plugin.response.WebBlogTermResponse;
import lombok.AllArgsConstructor;
import org.youngmonkeys.ezyarticle.sdk.model.TermModel;

import java.util.List;


@EzySingleton
@AllArgsConstructor
public class WebBlogTermModelDecorator {
    private final WebBlogTermModelToResponseConverter webBlogTermModelToResponseConverter;

    public List<WebBlogTermResponse> decorate(
        List<TermModel> models
    ) {
        return EzyLists.newArrayList(
            models,
            webBlogTermModelToResponseConverter::toTermResponse
        );
    }
}
