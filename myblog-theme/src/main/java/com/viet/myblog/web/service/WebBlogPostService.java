package com.viet.myblog.web.service;

import com.viet.myblog.web.enums.WebPostOrder;
import com.viet.myblog.web.repo.WebBlogPostRepository;
import com.tvd12.ezyhttp.server.core.annotation.Service;
import lombok.AllArgsConstructor;
import org.youngmonkeys.ezyarticle.sdk.entity.Post;
import org.youngmonkeys.ezyarticle.sdk.entity.PostTerm;
import org.youngmonkeys.ezyarticle.sdk.entity.Term;
import org.youngmonkeys.ezyarticle.sdk.model.PostModel;
import org.youngmonkeys.ezyarticle.sdk.service.PostService;
import org.youngmonkeys.ezyarticle.web.converter.WebEzyArticleEntityToModelConverter;
import org.youngmonkeys.ezyarticle.web.repo.WebPostTermRepository;
import org.youngmonkeys.ezyarticle.web.repo.WebTermRepository;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.viet.myblog.constant.BlogConstants.TERM_HIGHLIGHT;
import static com.viet.myblog.constant.BlogConstants.TERM_TYPE_BANNER;
import static com.tvd12.ezyfox.io.EzyLists.newArrayList;

@Service
@AllArgsConstructor
public class WebBlogPostService {
    private final WebBlogPostRepository blogPostRepository;
    private final WebPostTermRepository postTermRepository;
    private final WebTermRepository termRepository;
    private final WebEzyArticleEntityToModelConverter ezyArticleEntityToModelConverter;
    private final PostService postService;

    public List<PostModel> getHighlightPostsOrderByPriorityDesc() {
        Term term = termRepository.findByTermTypeAndSlug(
                TERM_TYPE_BANNER,
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

    public List<PostModel> getPosts(WebPostOrder criteria) {
        List<Post> posts;
        switch (criteria) {
            case RECENT:
                posts = blogPostRepository.findByStatusOrderByPublishAtDesc("PUBLISHED");
                break;
            case POPULAR:
                posts = blogPostRepository.findByStatusOrderByPriorityDesc("PUBLISHED");
                break;
            default:
                throw new IllegalArgumentException("khong ho tro kieu du lieu: " + criteria);
        }
        return Stream.concat(
                posts.stream()
                    .limit(3)
                    .map(ezyArticleEntityToModelConverter::toModel),
                Stream.of()
            )
            .collect(Collectors.toList());
    }
}