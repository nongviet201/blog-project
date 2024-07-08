package com.blogproject.blogproject.web.controller.view;

import com.blogproject.blogproject.web.controller.service.WebBlogPostControllerService;
import com.blogproject.blogproject.web.service.WebBlogMenuItemService;
import com.tvd12.ezyhttp.server.core.annotation.Controller;
import com.tvd12.ezyhttp.server.core.annotation.DoGet;
import com.tvd12.ezyhttp.server.core.annotation.PathVariable;
import com.tvd12.ezyhttp.server.core.annotation.RequestParam;
import com.tvd12.ezyhttp.server.core.view.View;
import lombok.AllArgsConstructor;
import org.youngmonkeys.ezyplatform.web.validator.WebCommonValidator;

import static org.youngmonkeys.ezyplatform.constant.CommonConstants.VIEW_VARIABLE_PAGE_TITLE;

@Controller
@AllArgsConstructor
public class HomeController {

    private final WebBlogMenuItemService blogMenuItemService;
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
                "mainMenuItems",
                blogMenuItemService.getMaiMenuItems()
            )
            .addVariable(
                "categoryMenuItems",
                blogMenuItemService.getCategoriesMenuItems()
            )
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

    @DoGet("/info-blog")
    public View infoBlog(@PathVariable("id") long id, @PathVariable("slug") String slug) {
        return View.builder()
            .template("info-blog")
            .addVariable(
                "mainMenuItems",
                blogMenuItemService.getMaiMenuItems()
            )
            .addVariable(
                "highlightPosts",
                blogPostControllerService.getHighlightPostsOrderByPriorityDesc()
            )
            .addVariable(VIEW_VARIABLE_PAGE_TITLE, "info-blog")
            .build();
    }
}
