package com.krowfeather.mediumelm.menu.controller;

import com.krowfeather.mediumelm.menu.entity.Menu;
import com.krowfeather.mediumelm.menu.service.MenuService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/menus")
public class MenuController {
    private final MenuService menuService;
    public MenuController(MenuService menuService) {
        this.menuService = menuService;
    }
    @GetMapping("/{id}")
    public List<Menu> getMenuByMid(@PathVariable String id) {
        return this.menuService.getMenuByMid(id);
    }

    @PostMapping("")
    public Menu addMenu(@RequestBody Menu menu) {
        return this.menuService.save(menu) ? menu : null;
    }

    @DeleteMapping("/{id}")
    public boolean deleteMenu(@PathVariable String id) {
        return this.menuService.removeById(id);
    }
}
