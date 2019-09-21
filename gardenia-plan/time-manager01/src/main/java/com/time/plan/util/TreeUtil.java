package com.time.plan.util;

import com.time.plan.bean.MenuTreeDto;
import com.time.plan.bean.MiniMenuTreeDto;
import com.time.plan.common.TreeDto;
import com.time.plan.common.ZTreeDto;
import com.time.plan.model.SysMenu;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * tree树转化工具类
 */
public class TreeUtil {

    public static List<TreeDto> convert(List<SysMenu> menuList) {
        if (menuList != null && menuList.size() > 0) {
            return menuList.stream().map(menu -> TreeDto.builder().id(menu.getId()).parentId(menu.getParentId()).title(menu.getMenuName()).build()).collect(Collectors.toList());
        } else {
            return new ArrayList<>();
        }
    }


    public static List<ZTreeDto> convertZtree(List<SysMenu> menuList) {
        if (menuList != null && menuList.size() > 0) {
            return menuList.stream().map(menu -> ZTreeDto.builder().id(menu.getId()).parentId(menu.getParentId()).name(menu.getMenuName()).build()).collect(Collectors.toList());
        } else {
            return new ArrayList<>();
        }
    }

    public static List<MenuTreeDto> convertMenuTree(List<SysMenu> menuList) {
        if (menuList != null && menuList.size() > 0) {
            return menuList.stream().map(menu -> MenuTreeDto.builder().id(menu.getId()).meunTitle(menu.getMenuName()).meunUrl(menu.getRouteUrl()).build()).collect(Collectors.toList());
        } else {
            return new ArrayList<>();
        }
    }

    public static List<MiniMenuTreeDto> convertMiniMenuTreeDto(List<SysMenu> menuList) {
        if (menuList != null && menuList.size() > 0) {
            return menuList.stream().map(menu -> MiniMenuTreeDto.builder().menuId(menu.getId()).title(menu.getMenuName()).href(menu.getRouteUrl()).icon(menu.getRouteUrl()).target(menu.getTarget()).build()).collect(Collectors.toList());
        } else {
            return new ArrayList<>();
        }
    }
}
