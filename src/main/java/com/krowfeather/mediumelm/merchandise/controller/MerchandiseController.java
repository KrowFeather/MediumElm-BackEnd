package com.krowfeather.mediumelm.merchandise.controller;

import com.krowfeather.mediumelm.common.Result;
import com.krowfeather.mediumelm.merchandise.entity.Merchandise;
import com.krowfeather.mediumelm.merchandise.service.MerchandiseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/merchandises")
@Tag(name = "商品管理接口", description = "用于管理商品信息的接口")
public class MerchandiseController {

    private final MerchandiseService merchandiseService;

    public MerchandiseController(MerchandiseService merchandiseService) {
        this.merchandiseService = merchandiseService;
    }

    /**
     * 根据商品ID获取商品信息
     *
     * @param id 商品ID
     * @return 返回指定ID的商品信息
     */
    @Operation(
            summary = "根据商品ID获取商品",
            description = "根据商品ID获取该商品的详细信息。"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "成功获取商品信息"),
            @ApiResponse(responseCode = "404", description = "未找到该商品ID")
    })
    @GetMapping("/{id}")
    public Result getMerchandisesByMid(@PathVariable String id) {
        return Result.success(this.merchandiseService.getMerchandisesByMid(id));
    }

    /**
     * 获取所有商品信息
     *
     * @return 返回所有商品信息
     */
    @Operation(
            summary = "获取所有商品",
            description = "获取系统中的所有商品信息。"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "成功获取商品信息"),
            @ApiResponse(responseCode = "404", description = "未找到任何商品")
    })
    @GetMapping("")
    public Result getMerchandises() {
        return Result.success(this.merchandiseService.list());
    }

    /**
     * 添加新商品
     *
     * @param merchandise 商品信息
     * @return 返回添加后的商品信息
     */
    @Operation(
            summary = "添加新商品",
            description = "将新的商品信息保存到系统中。"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "成功添加商品"),
            @ApiResponse(responseCode = "400", description = "请求数据无效")
    })
    @PostMapping("")
    public Result addMerchandise(@RequestBody Merchandise merchandise) {
        return Result.success(this.merchandiseService.save(merchandise) ? merchandise : null);
    }

    /**
     * 修改商品信息
     *
     * @param id 商品ID
     * @param merchandise 更新后的商品信息
     * @return 返回修改后的商品信息
     */
    @Operation(
            summary = "修改商品信息",
            description = "根据商品ID更新该商品的详细信息。"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "成功修改商品信息"),
            @ApiResponse(responseCode = "404", description = "未找到该商品ID"),
            @ApiResponse(responseCode = "400", description = "请求数据无效")
    })
    @PutMapping("/{id}")
    public Result modifyMerchandise(@PathVariable String id, @RequestBody Merchandise merchandise) {
        return Result.success(this.merchandiseService.updateById(merchandise) ? merchandise : null);
    }

    /**
     * 删除商品
     *
     * @param id 商品ID
     * @return 返回删除操作的结果
     */
    @Operation(
            summary = "删除商品",
            description = "根据商品ID删除指定的商品信息。"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "成功删除商品"),
            @ApiResponse(responseCode = "404", description = "未找到该商品ID")
    })
    @DeleteMapping("/{id}")
    public Result deleteMerchandise(@PathVariable String id) {
        return Result.success(this.merchandiseService.removeById(id));
    }
}
