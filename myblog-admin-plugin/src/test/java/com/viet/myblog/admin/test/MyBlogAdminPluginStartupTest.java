package com.viet.myblog.admin.test;

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
    "org.youngmonkeys.ezyarticle",
    "org.youngmonkeys.ezysupport",
    "org.youngmonkeys.ezymail"
})
public class MyBlogAdminPluginStartupTest {

    public static void main(String[] args) throws Exception {
        EzyHttpApplicationBootstrap.start(MyBlogAdminPluginStartupTest.class);
    }
}
