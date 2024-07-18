package com.viet.myblog.web.service;

import com.tvd12.ezyhttp.server.core.annotation.Service;
import lombok.AllArgsConstructor;
import org.youngmonkeys.ezyarticle.sdk.entity.Menu;
import org.youngmonkeys.ezyarticle.sdk.model.MenuItemModel;
import org.youngmonkeys.ezyarticle.web.repo.WebMenuRepository;
import org.youngmonkeys.ezyarticle.web.service.WebMenuItemService;

import java.util.List;

@Service
@AllArgsConstructor
public class WebBlogMenuItemService {

    private final WebMenuItemService menuItemService;
    private final WebMenuRepository menuRepository;

    public List<MenuItemModel> getMaiMenuItems() {
        Menu menu = menuRepository.findByField("name", "main");
        return menuItemService.getMenuItemsByMenuId(menu.getId());
    }

    public List<MenuItemModel> getCategoriesMenuItems() {
        Menu menu = menuRepository.findByField("name", "category");
        return menuItemService.getMenuItemsByMenuId(menu.getId());
    }
}
