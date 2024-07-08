package com.blogproject.blogproject.web.controller.decorator;

import com.blogproject.blogproject.web.converter.WebBlogModelToResponseConverter;
import com.blogproject.blogproject.web.response.WebBlogPostResponse;
import com.blogproject.blogproject.web.response.WebHighlightPostResponse;
import com.tvd12.ezyfox.bean.annotation.EzySingleton;
import lombok.AllArgsConstructor;
import org.youngmonkeys.ezyarticle.sdk.model.PostModel;
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
    private final WebBlogModelToResponseConverter blogModelToResponseConverter;

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
                imageById.get(it.getFeaturedImageId())
            )
        );
    }
}
