package com.krowfeather.mediumelm.categories.controller;


import com.krowfeather.mediumelm.categories.service.CategoriesService;
import com.krowfeather.mediumelm.common.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
@RestController
@RequestMapping("/api/categories")
public class CategoriesController {
    private final CategoriesService categoriesService;

    public CategoriesController(CategoriesService categoriesService) {
        this.categoriesService = categoriesService;
    }

    @GetMapping("")
    public Result getCategories() {
        return Result.success(this.categoriesService.list());
    }

    @GetMapping("/{id}")
    public Result getCategory(@PathVariable int id) {
        return Result.success(this.categoriesService.getById(id));
    }
}
