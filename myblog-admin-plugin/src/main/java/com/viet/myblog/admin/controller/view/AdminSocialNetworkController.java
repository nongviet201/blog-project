package com.viet.myblog.admin.controller.view;

import com.tvd12.ezyhttp.server.core.annotation.Controller;
import com.tvd12.ezyhttp.server.core.annotation.DoGet;
import com.tvd12.ezyhttp.server.core.view.Redirect;
import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class AdminSocialNetworkController {
    @DoGet("/social-networks")
    public Redirect socialNetworks() {
        return Redirect.to(
            "/ezysupport/social-networks"
        );
    }
}
