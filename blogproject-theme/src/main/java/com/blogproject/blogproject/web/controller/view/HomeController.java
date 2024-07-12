package com.blogproject.blogproject.web.controller.view;

import com.blogproject.blogproject.web.controller.service.WebBlogPostControllerService;
import com.blogproject.blogproject.web.controller.service.WebBlogTermControllerService;
import com.blogproject.blogproject.web.service.WebBlogMenuItemService;
import com.tvd12.ezyhttp.server.core.annotation.Controller;
import com.tvd12.ezyhttp.server.core.annotation.DoGet;
import com.tvd12.ezyhttp.server.core.annotation.RequestParam;
import com.tvd12.ezyhttp.server.core.view.View;
import lombok.AllArgsConstructor;
import org.youngmonkeys.ezyplatform.web.validator.WebCommonValidator;

import static org.youngmonkeys.ezyplatform.constant.CommonConstants.VIEW_VARIABLE_PAGE_TITLE;

@Controller
@AllArgsConstructor
public class HomeController {

    private final WebBlogMenuItemService blogMenuItemService;
    private final WebBlogTermControllerService blogTermControllerService;
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
                blogTermControllerService.getCategoryTerms()
            )
            .addVariable(
                "highlightPosts",
                blogPostControllerService.getHighlightPostsOrderByPriorityDesc()
            )
            .addVariable(
                "recentPosts",
                blogPostControllerService.getRecentPost()
            )
            .addVariable(
                "popularPosts",
                blogPostControllerService.getPopularPosts()
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
