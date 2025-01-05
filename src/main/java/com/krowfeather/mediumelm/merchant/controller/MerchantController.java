package com.krowfeather.mediumelm.merchant.controller;

import com.krowfeather.mediumelm.common.Result;
import com.krowfeather.mediumelm.merchant.enitity.Merchant;
import com.krowfeather.mediumelm.merchant.service.MerchantService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/merchants")
public class MerchantController {
    private final MerchantService merchantService;

    public MerchantController(MerchantService merchantService) {
        this.merchantService = merchantService;
    }

    @GetMapping("/tag/{tagId}")
    public Result getMerchantsByTag(@PathVariable Integer tagId) {
        return Result.success(merchantService.getMerchantsByTag(tagId));
    }

    @GetMapping("")
    public Result getMerchants(@RequestParam("pageNum") Integer pageNum, @RequestParam("pageSize") Integer pageSize) {
        return Result.success(this.merchantService.getMerchantsPage(pageNum, pageSize));
    }

    @GetMapping("/{id}")
    public Result getMerchant(@PathVariable String id) {
        return Result.success(this.merchantService.getByid(id));
    }

    @PostMapping("")
    public Result createMerchant(@RequestBody Merchant merchant) {
        System.out.println(merchant);
        return Result.success(merchantService.save(merchant) ? merchant : null);
    }

    @DeleteMapping("/{id}")
    public Result deleteMerchant(@PathVariable String id) {
        return Result.success(merchantService.removeById(id));
    }

    @PutMapping("/{id}")
    public Result updateMerchant(@PathVariable String id, @RequestBody Merchant merchant) {
        System.out.println(merchant);
        return Result.success(merchantService.updateById(merchant) ? merchant : null);
    }

    @GetMapping("/soldout/{id}")
    public Result getSoldout(@PathVariable String id) {
        return Result.success(merchantService.getSoldout(id));
    }
}
