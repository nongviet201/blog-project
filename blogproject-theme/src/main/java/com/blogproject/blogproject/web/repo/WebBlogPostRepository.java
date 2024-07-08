package com.blogproject.blogproject.web.repo;

import com.tvd12.ezydata.database.EzyDatabaseRepository;
import com.tvd12.ezyfox.database.annotation.EzyQuery;
import com.tvd12.ezyfox.database.annotation.EzyRepository;
import javafx.print.PageRange;
import lombok.AllArgsConstructor;
import org.apache.tika.renderer.PageRangeRequest;
import org.youngmonkeys.ezyarticle.sdk.entity.Post;
import org.youngmonkeys.ezyarticle.sdk.entity.PostStatus;

import java.awt.print.Pageable;
import java.util.List;

@EzyRepository
public interface WebBlogPostRepository extends EzyDatabaseRepository<Long, Post> {

    @EzyQuery(
        "SELECT e FROM Post e WHERE e.id IN ?0 ORDER BY e.priority DESC"
    )
    List<Post> findByIdInOrderByPriorityDesc(List<Long> ids);

    @EzyQuery(
            "SELECT e FROM Post e WHERE e.status ORDER BY e.priority DESC"
    )
    List<Post> findByStatusOrderByPriorityDesc(String status);
}
