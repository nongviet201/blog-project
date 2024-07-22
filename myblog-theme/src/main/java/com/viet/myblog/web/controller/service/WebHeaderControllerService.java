package com.viet.myblog.web.controller.service;

import com.tvd12.ezyhttp.server.core.annotation.Service;
import lombok.AllArgsConstructor;
import org.youngmonkeys.ezysupport.model.SocialNetworkModel;
import org.youngmonkeys.ezysupport.service.SocialNetworkService;

import java.util.List;

@Service
@AllArgsConstructor
public class WebHeaderControllerService {
    private SocialNetworkService socialNetworkService;

    public List<SocialNetworkModel> getAllActivatedSocialNetworks() {
        return socialNetworkService.getActivatedSocialNetworks();
    }
}
