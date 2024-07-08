package com.blogproject.blogproject.web.service;

import com.blogproject.blogproject.web.repo.WebBlogPostRepository;
import com.tvd12.ezyhttp.server.core.annotation.Service;
import lombok.AllArgsConstructor;
import org.youngmonkeys.ezyarticle.sdk.entity.*;
import org.youngmonkeys.ezyarticle.sdk.model.PostModel;
import org.youngmonkeys.ezyarticle.sdk.service.PostService;
import org.youngmonkeys.ezyarticle.web.converter.WebEzyArticleEntityToModelConverter;
import org.youngmonkeys.ezyarticle.web.repo.WebPostTermRepository;
import org.youngmonkeys.ezyarticle.web.repo.WebTermRepository;
import org.youngmonkeys.ezyarticle.web.service.WebPostService;

import java.util.Collections;
import java.util.List;

import static com.blogproject.blogproject.constant.BlogprojectConstants.TERM_HIGHLIGHT;
import static com.tvd12.ezyfox.io.EzyLists.newArrayList;

@Service
@AllArgsConstructor
public class WebBlogPostService {
    private final WebBlogPostRepository blogPostRepository;
    private final WebPostTermRepository postTermRepository;
    private final WebTermRepository termRepository;
    private final WebEzyArticleEntityToModelConverter ezyArticleEntityToModelConverter;
    private final WebPostService postService;

    public List<PostModel> getHighlightPostsOrderByPriorityDesc() {
        Term term = termRepository.findByTermTypeAndSlug(
                TermType.CATEGORY.toString(),
                TERM_HIGHLIGHT
        );
        List<Long> postIds = Collections.emptyList();
        if (term != null) {
            postIds = newArrayList(
                    postTermRepository
                            .findListByField("termId", term.getId()),
                    PostTerm::getPostId
            );
        }
        if (postIds.isEmpty()) {
            return Collections.emptyList();
        }
        return newArrayList(
                blogPostRepository.findByIdInOrderByPriorityDesc(postIds),
                ezyArticleEntityToModelConverter::toModel
        );
    }

    public List<PostModel> getPublicPostsOrderByPriorityDesc() {
        return postService.getPublishedPostsByTypeSortByPriorityDesc(PostType.POST.toString());
    }
}