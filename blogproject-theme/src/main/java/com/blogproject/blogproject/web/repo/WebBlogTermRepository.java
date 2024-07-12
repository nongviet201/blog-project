package com.blogproject.blogproject.web.repo;

import com.tvd12.ezydata.database.EzyDatabaseRepository;
import com.tvd12.ezyfox.database.annotation.EzyQuery;
import com.tvd12.ezyfox.database.annotation.EzyRepository;
import org.youngmonkeys.ezyarticle.sdk.entity.Term;

import java.util.List;

@EzyRepository
public interface WebBlogTermRepository extends EzyDatabaseRepository<Long, Term> {
    @EzyQuery("SELECT e FROM Term e WHERE e.termType = ?0")
    List<Term> findByType(String var1);
}
