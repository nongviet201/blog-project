package com.blogproject.blogproject.web.controller.service;

import com.blogproject.blogproject.web.controller.decorator.WebBlogPostModelDecorator;
import com.blogproject.blogproject.web.response.WebBlogPostResponse;
import com.blogproject.blogproject.web.response.WebHighlightPostResponse;
import com.blogproject.blogproject.web.response.WebCustomPostResponse;
import com.blogproject.blogproject.web.service.WebBlogPostService;
import com.blogproject.blogproject.web.entity.enums.WebPostOrder;
import com.tvd12.ezyhttp.server.core.annotation.Service;
import lombok.AllArgsConstructor;
import org.youngmonkeys.ezyarticle.sdk.entity.PostStatus;
import org.youngmonkeys.ezyarticle.sdk.model.PostModel;
import org.youngmonkeys.ezyarticle.sdk.service.PostService;
import org.youngmonkeys.ezyarticle.web.pagination.WebPostFilterFactory;
import org.youngmonkeys.ezyarticle.web.service.WebPaginationPostService;
import org.youngmonkeys.ezyplatform.model.PaginationModel;
import org.youngmonkeys.ezyplatform.pagination.PaginationModelFetchers;

import java.util.List;

@Service
@AllArgsConstructor
public class WebBlogPostControllerService {
    private final WebBlogPostService blogPostService;
    private final WebPostFilterFactory postFilterFactory;
    private final WebPaginationPostService paginationPostService;
    private final WebBlogPostModelDecorator blogPostModelDecorator;
    private final PostService postService;

    public PaginationModel<WebBlogPostResponse> getBlogPostPagination(
        String keyword,
        String nextPageToken,
        String prevPageToken,
        boolean lastPage,
        int limit
    ) {
        PaginationModel<PostModel> pagination = PaginationModelFetchers.getPaginationModel(
            this.paginationPostService,
            postFilterFactory.newDefaultPostFilterBuilder(keyword)
                .postStatus(PostStatus.PUBLISHED.toString())
                .build(),
            nextPageToken,
            prevPageToken,
            lastPage,
            limit
        );
        return blogPostModelDecorator.decorateBlogPostPagination(
            pagination
        );
    }

    public WebBlogPostResponse getBlogPostBySlug(String slug) {
        PostModel post = postService
            .getPostBySlug(slug);
        return blogPostModelDecorator.decorateBlogPost(
            post
        );
    }

    public List<WebHighlightPostResponse> getHighlightPostsOrderByPriorityDesc() {
        List<PostModel> posts = blogPostService
            .getHighlightPostsOrderByPriorityDesc();
        return blogPostModelDecorator.decorateHighlightPost(posts);
    }

    public List<WebCustomPostResponse> getRecentPost() {
        List<PostModel> recentPosts = blogPostService
            .getPosts(WebPostOrder.RECENT);
        return blogPostModelDecorator.decorateCustomPost(recentPosts);
    }

    public List<WebCustomPostResponse> getPopularPosts() {
        List<PostModel> popularPosts = blogPostService
            .getPosts(WebPostOrder.POPULAR);
        return blogPostModelDecorator.decorateCustomPost(popularPosts);
    }

}
