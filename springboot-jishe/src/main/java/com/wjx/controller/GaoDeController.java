package com.wjx.controller;

import com.wjx.entity.Gao;
import com.wjx.entity.GaoDe;
import com.wjx.service.GaoDeService;
import com.wjx.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
public class GaoDeController {
    @Autowired
    private GaoDeService gaoDeService;

    @GetMapping("/gaode")
    public Result get(String address) {
        List<GaoDe> data = gaoDeService.select(address);
        return Result.success(data);
    }

    @GetMapping("/gaodeadd")
    public Result getAdd(String address, String comfort_norm) {
        List<Gao> data = gaoDeService.select1(address,comfort_norm);
        return Result.success(data);
    }

}
