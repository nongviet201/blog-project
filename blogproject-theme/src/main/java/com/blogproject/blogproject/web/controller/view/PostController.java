package com.blogproject.blogproject.web.controller.view;

import com.blogproject.blogproject.web.controller.service.WebBlogPostControllerService;
import com.blogproject.blogproject.web.controller.service.WebBlogTermControllerService;
import com.blogproject.blogproject.web.service.WebBlogMenuItemService;
import com.tvd12.ezyhttp.server.core.annotation.Controller;
import com.tvd12.ezyhttp.server.core.annotation.DoGet;
import com.tvd12.ezyhttp.server.core.annotation.PathVariable;
import com.tvd12.ezyhttp.server.core.view.View;
import lombok.AllArgsConstructor;
import org.youngmonkeys.ezyplatform.web.validator.WebCommonValidator;

import static org.youngmonkeys.ezyplatform.constant.CommonConstants.VIEW_VARIABLE_PAGE_TITLE;

@Controller
@AllArgsConstructor
public class PostController {
    private final WebBlogMenuItemService blogMenuItemService;
    private final WebBlogTermControllerService blogTermControllerService;
    private final WebBlogPostControllerService blogPostControllerService;
    private final WebCommonValidator commonValidator;

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
}
