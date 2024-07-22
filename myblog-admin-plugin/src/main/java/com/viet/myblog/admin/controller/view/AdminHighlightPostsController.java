package com.viet.myblog.admin.controller.view;

import com.tvd12.ezyhttp.server.core.annotation.Controller;
import com.tvd12.ezyhttp.server.core.annotation.DoGet;
import com.tvd12.ezyhttp.server.core.view.Redirect;
import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class AdminHighlightPostsController {
    @DoGet("/highlight-posts")
    public Redirect highlightPostsGet() {
        return Redirect.to(
                "/ezyarticle/posts?postType=POST&termType=BANNER&term=highlight"
        );
    }
}
