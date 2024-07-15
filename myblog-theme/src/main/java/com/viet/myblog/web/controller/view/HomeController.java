package com.viet.myblog.web.controller.view;

import com.tvd12.ezyhttp.server.core.annotation.PathVariable;
import com.viet.myblog.web.controller.service.WebBlogPostControllerService;
import com.viet.myblog.web.controller.service.WebBlogTermControllerService;
import com.viet.myblog.web.service.WebBlogMenuItemService;
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

    @DoGet("/post/{slug}")
    public View infoBlog(@PathVariable("slug") String slug) {
        return View.builder()
            .template("info-blog")
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
                "post",
                blogPostControllerService.getBlogPostBySlug(slug)
            )
            .addVariable(
                "recentPosts",
                blogPostControllerService.getRecentPost()
            )
            .addVariable(
                "popularPosts",
                blogPostControllerService.getPopularPosts()
            )
            .addVariable(VIEW_VARIABLE_PAGE_TITLE, "info-blog")
            .build();
    }

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
                "mainMenuItems",
                blogMenuItemService.getMaiMenuItems()
            )
            .addVariable(
                "categoryMenuItems",
                blogTermControllerService.getCategoryTerms()
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
                blogPostControllerService.getBlogPostByTermPagination(
                    keyword,
                    termName,
                    nextPageToken,
                    prevPageToken,
                    lastPage,
                    limit
                )
            )
            .addVariable(
                "termName",
                termName
            )
            .addVariable(VIEW_VARIABLE_PAGE_TITLE, "category")
            .build();
    }

    @DoGet("/contact-me")
    public View contactMe() {
        return View.builder()
            .template("contact-me")
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
            .addVariable(VIEW_VARIABLE_PAGE_TITLE, "contact-me")
            .build();
    }

}
