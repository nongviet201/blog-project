package com.viet.myblog.web.theme.controller;

import com.tvd12.ezyhttp.server.core.annotation.Controller;
import com.viet.myblog.web.plugin.controller.service.WebBlogPostControllerService;
import com.viet.myblog.web.plugin.controller.view.CategoryController;

@Controller
public class CategoryWebController extends CategoryController {
    public CategoryWebController(WebBlogPostControllerService blogPostControllerService) {
        super(blogPostControllerService);
    }
}
