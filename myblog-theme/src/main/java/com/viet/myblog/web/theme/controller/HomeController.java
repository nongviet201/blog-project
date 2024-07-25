package com.viet.myblog.web.theme.controller;

import com.tvd12.ezyfox.annotation.EzyFeature;
import com.tvd12.ezyhttp.server.core.annotation.Controller;
import com.viet.myblog.web.plugin.controller.service.WebBlogPostControllerService;
import com.viet.myblog.web.plugin.controller.view.WebController;
import org.youngmonkeys.ezyplatform.web.validator.WebCommonValidator;

@Controller
@EzyFeature("myblog")
public class HomeController extends WebController {
    public HomeController(
        WebBlogPostControllerService blogPostControllerService,
        WebCommonValidator commonValidator
    ) {
        super(blogPostControllerService, commonValidator);
    }
}
