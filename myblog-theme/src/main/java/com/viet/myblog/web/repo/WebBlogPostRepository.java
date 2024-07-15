package com.viet.myblog.web.repo;

import com.tvd12.ezydata.database.EzyDatabaseRepository;
import com.tvd12.ezyfox.database.annotation.EzyQuery;
import com.tvd12.ezyfox.database.annotation.EzyRepository;
import org.youngmonkeys.ezyarticle.sdk.entity.Post;

import java.util.List;

@EzyRepository
public interface WebBlogPostRepository extends EzyDatabaseRepository<Long, Post> {

    @EzyQuery(
        "SELECT e FROM Post e WHERE e.id IN ?0 ORDER BY e.priority DESC"
    )
    List<Post> findByIdInOrderByPriorityDesc(List<Long> ids);

    @EzyQuery(
        "SELECT e FROM Post e WHERE e.status = ?0 ORDER BY e.publishedAt DESC"
    )
    List<Post> findByStatusOrderByPublishAtDesc(String status);

    @EzyQuery(
        "SELECT e FROM Post e WHERE e.status = ?0 ORDER BY e.priority DESC"
    )
    List<Post> findByStatusOrderByPriorityDesc(String status);
}
