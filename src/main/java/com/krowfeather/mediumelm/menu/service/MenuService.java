package com.krowfeather.mediumelm.menu.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.krowfeather.mediumelm.menu.entity.Menu;

import java.util.List;

public interface MenuService extends IService<Menu> {
    List<Menu> getMenuByMid(String id);
}
