package com.krowfeather.mediumelm.merchandise.controller;

import com.krowfeather.mediumelm.common.Result;
import com.krowfeather.mediumelm.merchandise.entity.Merchandise;
import com.krowfeather.mediumelm.merchandise.service.MerchandiseService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/merchandises")
public class MerchandiseController {
    private final MerchandiseService merchandiseService;

    public MerchandiseController(MerchandiseService merchandiseService) {
        this.merchandiseService = merchandiseService;
    }

    @GetMapping("/{id}")
    public Result getMerchandisesByMid(@PathVariable String id) {
        return Result.success(this.merchandiseService.getMerchandisesByMid(id));
    }

    @GetMapping("")
    public Result getMerchandises(){
        return Result.success(this.merchandiseService.list());
    }

    @PostMapping("")
    public Result addMerchandise(@RequestBody Merchandise merchandise){
        return Result.success(this.merchandiseService.save(merchandise)?merchandise:null);
    }

    @PutMapping("/{id}")
    public Result modifyMerchandise(@PathVariable String id, @RequestBody Merchandise merchandise){
        return Result.success(this.merchandiseService.updateById(merchandise)?merchandise:null);
    }

    @DeleteMapping("/{id}")
    public Result deleteMerchandise(@PathVariable String id){
        return Result.success(this.merchandiseService.removeById(id));
    }
}
