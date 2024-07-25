package com.viet.myblog.web.plugin.controller.view;

import com.tvd12.ezyhttp.server.core.annotation.DoGet;
import com.tvd12.ezyhttp.server.core.annotation.PathVariable;
import com.tvd12.ezyhttp.server.core.annotation.RequestParam;
import com.tvd12.ezyhttp.server.core.view.View;
import com.viet.myblog.web.plugin.controller.service.WebBlogPostControllerService;
import lombok.AllArgsConstructor;

import static org.youngmonkeys.ezyplatform.constant.CommonConstants.VIEW_VARIABLE_PAGE_TITLE;

@AllArgsConstructor
public abstract class CategoryController {
    private final WebBlogPostControllerService blogPostControllerService;

    @DoGet("/category/{termName}")
    public View category(
        @PathVariable("termName") String termName,
        @RequestParam(value = "keyword") String keyword,
        @RequestParam(value = "nextPageToken") String nextPageToken,
        @RequestParam(value = "prevPageToken") String prevPageToken,
        @RequestParam(value = "lastPage") boolean lastPage,
        @RequestParam(value = "limit", defaultValue = "6") int limit
    ) {
        return View.builder()
            .template("category")
            .addVariable(
                "postPagination",
                blogPostControllerService.getBlogPostByTermPagination(
                    keyword,
                    termName,
                    nextPageToken,
                    prevPageToken,
                    lastPage,
                    limit
                ))
            .addVariable(
                "termName",
                termName)
            .addVariable(
                VIEW_VARIABLE_PAGE_TITLE,
                "category"
            )
            .build();
    }
}
