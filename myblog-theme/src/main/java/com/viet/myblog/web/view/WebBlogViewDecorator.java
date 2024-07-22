package com.viet.myblog.web.view;

import com.tvd12.ezyfox.bean.annotation.EzySingleton;
import com.tvd12.ezyhttp.server.core.view.View;
import com.viet.myblog.web.controller.service.WebBlogPostControllerService;
import com.viet.myblog.web.controller.service.WebBlogTermControllerService;
import com.viet.myblog.web.controller.service.WebHeaderControllerService;
import com.viet.myblog.web.service.WebBlogMenuItemService;
import lombok.AllArgsConstructor;
import org.youngmonkeys.ezyplatform.web.view.WebViewDecorator;

import javax.servlet.http.HttpServletRequest;

@EzySingleton
@AllArgsConstructor
public class WebBlogViewDecorator extends WebViewDecorator {
    private final WebBlogMenuItemService blogMenuItemService;
    private final WebBlogTermControllerService blogTermControllerService;
    private final WebBlogPostControllerService blogPostControllerService;
    private final WebHeaderControllerService headerControllerService;

    @Override
    public void decorate(HttpServletRequest request, View view) {
        super.decorate(request, view);
        decorateFooter(view);
        decorateHeader(view);
    }

    public void decorateHeader(View view) {
        view.setVariable(
            "mainMenuItems",
            blogMenuItemService.getMaiMenuItems()
        );
        view.setVariable(
            "categoryMenuItems",
            blogTermControllerService.getCategoryTerms()
        );
        view.setVariable(
            "socialNetworks",
            headerControllerService.getAllActivatedSocialNetworks()
        );
    }

    public void decorateFooter(View view) {
        view.setVariable(
            "popularPosts",
            blogPostControllerService.getPopularPosts()
        );
        view.setVariable(
            "recentPosts",
            blogPostControllerService.getRecentPost()
        );
    }
}
