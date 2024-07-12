package com.blogproject.blogproject.web.controller.decorator;

import com.blogproject.blogproject.web.converter.WebBlogTermModelToResponseConverter;
import com.blogproject.blogproject.web.response.WebBlogTermResponse;
import com.tvd12.ezyfox.bean.annotation.EzySingleton;
import lombok.AllArgsConstructor;
import org.youngmonkeys.ezyarticle.sdk.model.TermModel;

import java.util.List;
import static com.tvd12.ezyfox.io.EzyLists.newArrayList;


@EzySingleton
@AllArgsConstructor
public class WebBlogTermModelDecorator {
    private final WebBlogTermModelToResponseConverter webBlogTermModelToResponseConverter;

    public List<WebBlogTermResponse> decorate(
        List<TermModel> models
    ) {
        return newArrayList(
            models,
            webBlogTermModelToResponseConverter::toTermResponse
        );
    }
}
