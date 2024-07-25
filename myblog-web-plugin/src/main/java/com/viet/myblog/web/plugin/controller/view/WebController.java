package com.viet.myblog.web.plugin.controller.view;

import com.tvd12.ezyhttp.server.core.annotation.DoGet;
import com.tvd12.ezyhttp.server.core.annotation.RequestParam;
import com.tvd12.ezyhttp.server.core.view.View;
import com.viet.myblog.web.plugin.controller.service.WebBlogPostControllerService;
import lombok.AllArgsConstructor;
import org.youngmonkeys.ezyplatform.web.validator.WebCommonValidator;

import static org.youngmonkeys.ezyplatform.constant.CommonConstants.VIEW_VARIABLE_PAGE_TITLE;

@AllArgsConstructor
public abstract class WebController {
    private final WebBlogPostControllerService blogPostControllerService;
    private final WebCommonValidator commonValidator;

    @DoGet("/")
    public View home(
        @RequestParam(value = "keyword") String keyword,
        @RequestParam(value = "nextPageToken") String nextPageToken,
        @RequestParam(value = "prevPageToken") String prevPageToken,
        @RequestParam(value = "lastPage") boolean lastPage,
        @RequestParam(value = "limit", defaultValue = "5") int limit
    ) {
        commonValidator.validatePageSize(limit);
        return View.builder()
            .template("home")
            .addVariable(
                "highlightPosts",
                blogPostControllerService.getHighlightPostsOrderByPriorityDesc()
            )
            .addVariable(
                "postPagination",
                blogPostControllerService.getBlogPostPagination(
                    keyword,
                    nextPageToken,
                    prevPageToken,
                    lastPage,
                    limit
                )
            )
            .addVariable(VIEW_VARIABLE_PAGE_TITLE, "home")
            .build();
    }
}
