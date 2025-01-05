package com.krowfeather.mediumelm.menu.controller;

import com.krowfeather.mediumelm.common.Result;
import com.krowfeather.mediumelm.menu.entity.Menu;
import com.krowfeather.mediumelm.menu.service.MenuService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/menus")
public class MenuController {
    private final MenuService menuService;
    public MenuController(MenuService menuService) {
        this.menuService = menuService;
    }
    @GetMapping("/{id}")
    public Result getMenuByMid(@PathVariable String id) {
        return Result.success(this.menuService.getMenuByMid(id));
    }

    @PostMapping("")
    public Result addMenu(@RequestBody Menu menu) {
        return Result.success(this.menuService.save(menu) ? menu : null);
    }

    @DeleteMapping("/{id}")
    public Result deleteMenu(@PathVariable String id) {
        return Result.success(this.menuService.removeById(id));
    }
}
