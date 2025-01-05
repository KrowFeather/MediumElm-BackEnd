package com.krowfeather.mediumelm.menu.controller;

import com.krowfeather.mediumelm.common.Result;
import com.krowfeather.mediumelm.menu.entity.Menu;
import com.krowfeather.mediumelm.menu.service.MenuService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/menus")
@Tag(name = "菜单管理接口", description = "用于管理菜单信息的接口")
public class MenuController {

    private final MenuService menuService;

    public MenuController(MenuService menuService) {
        this.menuService = menuService;
    }

    /**
     * 根据菜单ID获取菜单信息
     *
     * @param id 菜单ID
     * @return 返回指定ID的菜单信息
     */
    @Operation(
            summary = "根据菜单ID获取菜单",
            description = "根据菜单ID获取该菜单的详细信息。"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "成功获取菜单信息"),
            @ApiResponse(responseCode = "404", description = "未找到该菜单ID")
    })
    @GetMapping("/{id}")
    public Result getMenuByMid(@PathVariable String id) {
        return Result.success(this.menuService.getMenuByMid(id));
    }

    /**
     * 添加新菜单
     *
     * @param menu 菜单信息
     * @return 返回添加后的菜单信息
     */
    @Operation(
            summary = "添加新菜单",
            description = "将新的菜单信息保存到系统中。"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "成功添加菜单"),
            @ApiResponse(responseCode = "400", description = "请求数据无效")
    })
    @PostMapping("")
    public Result addMenu(@RequestBody Menu menu) {
        return Result.success(this.menuService.save(menu) ? menu : null);
    }

    /**
     * 删除菜单
     *
     * @param id 菜单ID
     * @return 返回删除操作的结果
     */
    @Operation(
            summary = "删除菜单",
            description = "根据菜单ID删除指定的菜单信息。"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "成功删除菜单"),
            @ApiResponse(responseCode = "404", description = "未找到该菜单ID")
    })
    @DeleteMapping("/{id}")
    public Result deleteMenu(@PathVariable String id) {
        return Result.success(this.menuService.removeById(id));
    }
}
