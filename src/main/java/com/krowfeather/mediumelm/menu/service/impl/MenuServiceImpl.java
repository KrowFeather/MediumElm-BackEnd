package com.krowfeather.mediumelm.menu.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.krowfeather.mediumelm.menu.entity.Menu;
import com.krowfeather.mediumelm.menu.mapper.MenuMapper;
import com.krowfeather.mediumelm.menu.service.MenuService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements MenuService {
    private final MenuMapper menuMapper;

    public MenuServiceImpl(MenuMapper menuMapper) {
        this.menuMapper = menuMapper;
    }

    @Override
    public List<Menu> getMenuByMid(String mid) {
        return this.menuMapper.getMenuByMid(mid);
    }
}
