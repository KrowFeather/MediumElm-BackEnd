package com.krowfeather.mediumelm.merchant.controller;

import com.github.pagehelper.PageInfo;
import com.krowfeather.mediumelm.merchant.enitity.Merchant;
import com.krowfeather.mediumelm.merchant.service.MerchantService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/merchants")
public class MerchantController {
    private final MerchantService merchantService;

    public MerchantController(MerchantService merchantService) {
        this.merchantService = merchantService;
    }

    @GetMapping("/tag/{tagId}")
    public List<Merchant> getMerchantsByTag(@PathVariable Integer tagId) {
        return merchantService.getMerchantsByTag(tagId);
    }

    @GetMapping("")
    public PageInfo<Merchant> getMerchants(@RequestParam("pageNum") Integer pageNum, @RequestParam("pageSize") Integer pageSize) {
        return this.merchantService.getMerchantsPage(pageNum, pageSize);
    }

    @GetMapping("/{id}")
    public Merchant getMerchant(@PathVariable String id) {
        return this.merchantService.getById(id);
    }

    @PostMapping("")
    public Merchant createMerchant(@RequestBody Merchant merchant) {
        System.out.println(merchant);
        return merchantService.save(merchant) ? merchant : null;
    }

    @DeleteMapping("/{id}")
    public boolean deleteMerchant(@PathVariable String id) {
        return merchantService.removeById(id);
    }

    @PutMapping("/{id}")
    public Merchant updateMerchant(@PathVariable String id,@RequestBody Merchant merchant) {
        System.out.println(merchant);
        return merchantService.updateById(merchant)? merchant : null;
    }
}
