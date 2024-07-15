package com.viet.myblog.web.controller.decorator;

import com.viet.myblog.web.converter.WebBlogTermModelToResponseConverter;
import com.viet.myblog.web.response.WebBlogTermResponse;
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
