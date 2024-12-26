package com.krowfeather.mediumelm.merchandise.controller;

import com.krowfeather.mediumelm.merchandise.entity.Merchandise;
import com.krowfeather.mediumelm.merchandise.service.MerchandiseService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/merchandises")
public class MerchandiseController {
    private final MerchandiseService merchandiseService;

    public MerchandiseController(MerchandiseService merchandiseService) {
        this.merchandiseService = merchandiseService;
    }

    @GetMapping("/{id}")
    public List<Merchandise> getMerchandisesByMid(@PathVariable String id) {
        return this.merchandiseService.getMerchandisesByMid(id);
    }

    @GetMapping("")
    public List<Merchandise> getMerchandises(){
        return this.merchandiseService.list();
    }

    @PostMapping("")
    public Merchandise addMerchandise(@RequestBody Merchandise merchandise){
        return this.merchandiseService.save(merchandise)?merchandise:null;
    }

    @PutMapping("/{id}")
    public Merchandise modifyMerchandise(@PathVariable String id, @RequestBody Merchandise merchandise){
        return this.merchandiseService.updateById(merchandise)?merchandise:null;
    }

    @DeleteMapping("/{id}")
    public boolean deleteMerchandise(@PathVariable String id){
        return this.merchandiseService.removeById(id);
    }
}
