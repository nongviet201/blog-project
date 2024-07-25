package com.viet.myblog.web.plugin.view;

import com.tvd12.ezyfox.bean.annotation.EzySingleton;
import com.tvd12.ezyhttp.server.core.view.View;
import com.viet.myblog.web.plugin.controller.service.WebBlogPostControllerService;
import com.viet.myblog.web.plugin.controller.service.WebBlogTermControllerService;
import com.viet.myblog.web.plugin.controller.service.WebHeaderControllerService;
import com.viet.myblog.web.plugin.service.WebBlogMenuItemService;
import lombok.AllArgsConstructor;
import org.youngmonkeys.ezyplatform.web.service.WebSettingService;
import org.youngmonkeys.ezyplatform.web.view.WebViewDecorator;

import javax.servlet.http.HttpServletRequest;

import static org.youngmonkeys.ezyplatform.constant.CommonConstants.SETTING_NAME_WEB_SITE_LOGO_URL;

@EzySingleton
@AllArgsConstructor
public class WebBlogViewDecorator extends WebViewDecorator {
    private final WebBlogMenuItemService blogMenuItemService;
    private final WebBlogTermControllerService blogTermControllerService;
    private final WebBlogPostControllerService blogPostControllerService;
    private final WebHeaderControllerService headerControllerService;
    private final WebSettingService settingService;


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
        view.setVariable(
            "logoImageUrl",
            settingService.getTextValue(
                SETTING_NAME_WEB_SITE_LOGO_URL
            )
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
        view.setVariable(
            "logoFooterImageUrl",
            settingService.getTextValue(
                "ezysupport_logo_without_text_url"
            )
        );
    }
}
