package com.viet.myblog.web.test;

import com.tvd12.ezyhttp.server.boot.EzyHttpApplicationBootstrap;
import com.tvd12.ezyhttp.server.core.annotation.ComponentsScan;
import com.tvd12.ezyhttp.server.core.annotation.PropertiesSources;

@PropertiesSources({
    "config.properties",
    "setup.properties"
})
@ComponentsScan({
    "org.youngmonkeys.ezyplatform",
    "com.viet.myblog",
    "org.youngmonkeys.ezyarticle"
})
public class BlogThemeStartupTest {

    public static void main(String[] args) throws Exception {
        EzyHttpApplicationBootstrap.start(BlogThemeStartupTest.class);
    }
}
