package com.viet.myblog.web.theme.controller;

import com.tvd12.ezyhttp.server.core.annotation.Controller;
import com.viet.myblog.web.plugin.controller.service.WebBlogPostControllerService;
import com.viet.myblog.web.plugin.controller.view.PostController;
import org.youngmonkeys.ezyplatform.web.validator.WebCommonValidator;

@Controller()
public class PostWebController extends PostController {
    public PostWebController(
        WebBlogPostControllerService blogPostControllerService,
        WebCommonValidator commonValidator
    ) {
        super(blogPostControllerService, commonValidator);
    }
}
