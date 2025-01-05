package com.krowfeather.mediumelm.categories.controller;

import com.krowfeather.mediumelm.categories.service.CategoriesService;
import com.krowfeather.mediumelm.common.Result;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/api/categories")
@Tag(name = "分类管理接口", description = "用于获取商品分类信息的接口")
public class CategoriesController {

    private final CategoriesService categoriesService;

    public CategoriesController(CategoriesService categoriesService) {
        this.categoriesService = categoriesService;
    }

    /**
     * 获取所有分类
     *
     * @return 返回所有商品分类的列表
     */
    @Operation(
            summary = "获取所有商品分类",
            description = "获取系统中所有商品分类的列表，包括分类的基本信息。"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "成功获取分类列表"),
            @ApiResponse(responseCode = "500", description = "服务器内部错误")
    })
    @GetMapping("")
    public Result getCategories() {
        return Result.success(this.categoriesService.list());
    }

    /**
     * 根据ID获取单个分类信息
     *
     * @param id 分类ID
     * @return 返回指定ID的分类信息
     */
    @Operation(
            summary = "根据ID获取商品分类",
            description = "根据分类ID获取该分类的详细信息。"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "成功获取分类信息"),
            @ApiResponse(responseCode = "404", description = "未找到该分类ID")
    })
    @GetMapping("/{id}")
    public Result getCategory(@PathVariable int id) {
        return Result.success(this.categoriesService.getById(id));
    }
}
