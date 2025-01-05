package com.krowfeather.mediumelm.merchant.controller;

import com.krowfeather.mediumelm.common.Result;
import com.krowfeather.mediumelm.merchant.enitity.Merchant;
import com.krowfeather.mediumelm.merchant.service.MerchantService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/merchants")
@Tag(name = "商户管理接口", description = "用于管理商户信息的接口")
public class MerchantController {

    private final MerchantService merchantService;

    public MerchantController(MerchantService merchantService) {
        this.merchantService = merchantService;
    }

    /**
     * 根据标签获取商户信息
     *
     * @param tagId 标签ID
     * @return 返回该标签下的所有商户信息
     */
    @Operation(
            summary = "根据标签ID获取商户信息",
            description = "根据商户的标签ID获取所有属于该标签的商户。"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "成功获取商户信息"),
            @ApiResponse(responseCode = "404", description = "未找到商户信息")
    })
    @GetMapping("/tag/{tagId}")
    public Result getMerchantsByTag(@PathVariable Integer tagId) {
        return Result.success(merchantService.getMerchantsByTag(tagId));
    }

    /**
     * 获取商户分页信息
     *
     * @param pageNum 当前页码
     * @param pageSize 每页显示数量
     * @return 返回商户分页列表
     */
    @Operation(
            summary = "获取商户分页列表",
            description = "获取商户的分页信息，支持按页查询商户列表。"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "成功获取商户分页信息"),
            @ApiResponse(responseCode = "400", description = "请求参数无效")
    })
    @GetMapping("")
    public Result getMerchants(@RequestParam("pageNum") Integer pageNum, @RequestParam("pageSize") Integer pageSize) {
        return Result.success(this.merchantService.getMerchantsPage(pageNum, pageSize));
    }

    /**
     * 根据商户ID获取商户信息
     *
     * @param id 商户ID
     * @return 返回商户的详细信息
     */
    @Operation(
            summary = "根据商户ID获取商户",
            description = "根据商户ID获取指定商户的详细信息。"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "成功获取商户信息"),
            @ApiResponse(responseCode = "404", description = "未找到该商户")
    })
    @GetMapping("/{id}")
    public Result getMerchant(@PathVariable String id) {
        return Result.success(this.merchantService.getByid(id));
    }

    /**
     * 创建新的商户
     *
     * @param merchant 商户信息
     * @return 返回创建后的商户信息
     */
    @Operation(
            summary = "创建新的商户",
            description = "根据传入的商户信息创建新的商户。"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "成功创建商户"),
            @ApiResponse(responseCode = "400", description = "请求数据无效")
    })
    @PostMapping("")
    public Result createMerchant(@RequestBody Merchant merchant) {
        System.out.println(merchant);
        return Result.success(merchantService.save(merchant) ? merchant : null);
    }

    /**
     * 删除指定商户
     *
     * @param id 商户ID
     * @return 返回删除操作结果
     */
    @Operation(
            summary = "删除指定商户",
            description = "根据商户ID删除指定的商户。"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "成功删除商户"),
            @ApiResponse(responseCode = "404", description = "未找到该商户ID")
    })
    @DeleteMapping("/{id}")
    public Result deleteMerchant(@PathVariable String id) {
        return Result.success(merchantService.removeById(id));
    }

    /**
     * 更新商户信息
     *
     * @param id 商户ID
     * @param merchant 更新后的商户信息
     * @return 返回更新后的商户信息
     */
    @Operation(
            summary = "更新商户信息",
            description = "根据商户ID更新指定商户的信息。"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "成功更新商户信息"),
            @ApiResponse(responseCode = "404", description = "未找到该商户ID"),
            @ApiResponse(responseCode = "400", description = "请求数据无效")
    })
    @PutMapping("/{id}")
    public Result updateMerchant(@PathVariable String id, @RequestBody Merchant merchant) {
        System.out.println(merchant);
        return Result.success(merchantService.updateById(merchant) ? merchant : null);
    }

    /**
     * 获取已售完的商户信息
     *
     * @param id 商户ID
     * @return 返回商户已售完的信息
     */
    @Operation(
            summary = "获取已售完商户信息",
            description = "获取指定商户已售完的商品信息。"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "成功获取已售完信息"),
            @ApiResponse(responseCode = "404", description = "未找到商户或商品信息")
    })
    @GetMapping("/soldout/{id}")
    public Result getSoldout(@PathVariable String id) {
        return Result.success(merchantService.getSoldout(id));
    }
}
