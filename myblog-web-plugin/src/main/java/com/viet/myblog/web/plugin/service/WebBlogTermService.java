package com.viet.myblog.web.plugin.service;

import com.viet.myblog.web.plugin.repo.WebBlogTermRepository;
import com.tvd12.ezyhttp.server.core.annotation.Service;
import lombok.AllArgsConstructor;
import org.youngmonkeys.ezyarticle.sdk.entity.TermType;
import org.youngmonkeys.ezyarticle.sdk.model.TermModel;
import org.youngmonkeys.ezyarticle.sdk.service.TermService;
import org.youngmonkeys.ezyarticle.web.converter.WebEzyArticleEntityToModelConverter;

import java.util.List;

import static com.tvd12.ezyfox.io.EzyLists.newArrayList;

@Service
@AllArgsConstructor
public class WebBlogTermService {
    private final WebBlogTermRepository webBlogTermRepository;
    private final WebEzyArticleEntityToModelConverter ezyArticleEntityToModelConverter;
    private final TermService termService;

    public List<TermModel> getTermByType(TermType termType) {
        return newArrayList(
            webBlogTermRepository.findByType(
                termType.toString()
            ),
            ezyArticleEntityToModelConverter::toModel
        );
    }

    public List<TermModel> getTermByPostId(long postId) {
        return newArrayList(
            termService.getTermsByPostId(postId)
        );
    }
}
