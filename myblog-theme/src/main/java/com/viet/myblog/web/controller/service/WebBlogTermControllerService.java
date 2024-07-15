package com.viet.myblog.web.controller.service;

import com.viet.myblog.web.service.WebBlogTermService;
import com.tvd12.ezyhttp.server.core.annotation.Service;
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
