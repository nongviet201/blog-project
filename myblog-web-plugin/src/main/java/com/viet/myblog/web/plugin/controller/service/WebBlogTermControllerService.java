package com.viet.myblog.web.plugin.controller.service;

import com.tvd12.ezyhttp.server.core.annotation.Service;
import com.viet.myblog.web.plugin.service.WebBlogTermService;
import lombok.AllArgsConstructor;
import org.youngmonkeys.ezyarticle.sdk.entity.TermType;
import org.youngmonkeys.ezyarticle.sdk.model.TermModel;

import java.util.List;

@Service
@AllArgsConstructor
public class WebBlogTermControllerService {
    private final WebBlogTermService webBlogTermService;

    public List<TermModel> getCategoryTerms() {
        return webBlogTermService.getTermByType(TermType.CATEGORY);
    }
}
