package com.blogproject.blogproject.web.controller.decorator;

import com.blogproject.blogproject.web.converter.WebBlogPostModelToResponseConverter;
import com.blogproject.blogproject.web.response.WebBlogPostResponse;
import com.blogproject.blogproject.web.response.WebCustomPostResponse;
import com.blogproject.blogproject.web.response.WebHighlightPostResponse;
import com.blogproject.blogproject.web.service.WebBlogTermService;
import com.tvd12.ezyfox.bean.annotation.EzySingleton;
import lombok.AllArgsConstructor;
import org.youngmonkeys.ezyarticle.sdk.model.PostModel;
import org.youngmonkeys.ezyarticle.sdk.model.TermModel;
import org.youngmonkeys.ezyarticle.web.service.WebPostSlugService;
import org.youngmonkeys.ezyplatform.model.MediaNameModel;
import org.youngmonkeys.ezyplatform.model.PaginationModel;
import org.youngmonkeys.ezyplatform.web.service.WebMediaService;

import java.util.List;
import java.util.Map;
import java.util.Set;

import static com.tvd12.ezyfox.io.EzyLists.newArrayList;
import static com.tvd12.ezyfox.io.EzySets.newHashSet;

@EzySingleton
@AllArgsConstructor
public class WebBlogPostModelDecorator {
    private final WebMediaService mediaService;
    private final WebPostSlugService postSlugService;
    private final WebBlogPostModelToResponseConverter blogModelToResponseConverter;
    private final WebBlogTermService webBlogTermService;

    public List<WebHighlightPostResponse> decorateHighlightPost(
        List<PostModel> models
    ) {
        List<Long> postIds = newArrayList(
            models,
            PostModel::getId
        );
        Map<Long, String> slugByPostId = postSlugService.getLatestSlugMapByPostIds(
            postIds
        );
        Set<Long> imageIds = newHashSet(
            models,
            PostModel::getFeaturedImageId
        );
        Map<Long, MediaNameModel> imageById = mediaService
            .getMediaNameMapByIds(imageIds);
        return newArrayList(
            models,
            it -> blogModelToResponseConverter.toHighlightPostResponse(
                it,
                slugByPostId.get(it.getId()),
                imageById.get(it.getFeaturedImageId())
            )
        );
    }

    public PaginationModel<WebBlogPostResponse> decorateBlogPostPagination(
        PaginationModel<PostModel> pagination
    ) {
        List<PostModel> models = pagination.getItems();
        List<Long> postIds = newArrayList(
            models,
            PostModel::getId
        );
        Map<Long, String> slugByPostId = postSlugService.getLatestSlugMapByPostIds(
            postIds
        );
        Set<Long> imageIds = newHashSet(
            models,
            PostModel::getFeaturedImageId
        );
        Map<Long, MediaNameModel> imageById = mediaService
            .getMediaNameMapByIds(imageIds);
        return pagination.map(it ->
            blogModelToResponseConverter.toBlogPostResponse(
                it,
                slugByPostId.get(it.getId()),
                imageById.get(it.getFeaturedImageId()),
                webBlogTermService.getTermByPostId(it.getId())
            )
        );
    }


    public WebBlogPostResponse decorateBlogPost(PostModel post) {
        String slug = postSlugService
            .getLatestSlugByPostId(post.getId()
            );
        MediaNameModel imgById = mediaService
            .getMediaNameById(post.getFeaturedImageId()
            );
        List<TermModel> terms = newArrayList(
            webBlogTermService.getTermByPostId(post.getId())
        );
        return blogModelToResponseConverter.toBlogPostResponse(
            post,
            slug,
            imgById,
            terms
            );
    }

    public List<WebCustomPostResponse> decorateCustomPost(List<PostModel> models) {
        List<Long> postIds = newArrayList(
            models,
            PostModel::getId
        );
        Map<Long, String> slugByPostId = postSlugService.getLatestSlugMapByPostIds(
            postIds
        );
        Set<Long> imageIds = newHashSet(
            models,
            PostModel::getFeaturedImageId
        );
        Map<Long, MediaNameModel> imageById = mediaService
            .getMediaNameMapByIds(imageIds);
        return newArrayList(
            models,
            post -> blogModelToResponseConverter.toCustomPostResponse(
                post,
                slugByPostId.get(post.getId()),
                imageById.get(post.getFeaturedImageId())
            )
        );
    }

}
