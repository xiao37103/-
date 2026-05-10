package com.wjx.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.wjx.entity.Yuce;
import com.wjx.service.YuceService;
import com.wjx.utils.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.*;


@RestController
@Slf4j
@CrossOrigin
@RequestMapping("/yuce")
public class YuceController {
    @Autowired
    private YuceService yuceService;
    @Autowired
    private EnergyController energyController;
    @Autowired
    private GdpController gdpController;
    private HashMap<String, Object> getData(String address) throws IOException {
        LambdaQueryWrapper<Yuce> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(StringUtils.isNotEmpty(address), Yuce::getCity, address.substring(0, 2));
        Yuce yuce = yuceService.getOne(queryWrapper);
        Result result = gdpController.get("安徽省");
        int n = 1;
        Yuce yu = new Yuce(address,
                yuce.getYear_2018() / n, yuce.getYear_2019() / n, yuce.getYear_2020() / n,
                yuce.getYear_2021() / n, yuce.getYear_2022() / n, yuce.getYear_2023() / n,
                yuce.getYear_2024() / n, yuce.getYear_2025() / n, yuce.getYear_2026() / n,
                yuce.getYear_2027() / n, yuce.getYear_2028() / n, yuce.getYear_2029() / n,
                yuce.getYear_2030() / n);
        yu.setYear_2018(yuce.getYear_2018() / n);
        yu.setYear_2019(yuce.getYear_2019() / n);
        yu.setYear_2020(yuce.getYear_2020() / n);
        yu.setYear_2021(yuce.getYear_2021() / n);
        yu.setYear_2022(yuce.getYear_2022() / n);
        yu.setYear_2023(yuce.getYear_2023() / n);
        yu.setYear_2024(yuce.getYear_2024() / n);
        yu.setYear_2025(yuce.getYear_2025() / n);
        yu.setYear_2026(yuce.getYear_2026() / n);
        yu.setYear_2027(yuce.getYear_2027() / n);
        yu.setYear_2028(yuce.getYear_2028() / n);
        yu.setYear_2029(yuce.getYear_2029() / n);
        yu.setYear_2030(yuce.getYear_2030() / n);
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put(address + "汽车", yuce);
        hashMap.put(address + "预测充电桩", yu);
        hashMap.put("已知充电桩", result.getData());
        return hashMap;
    }

    @GetMapping("/one")
    public Result getOne(String address) throws IOException {
        HashMap<String, Object> data = getData(address);
        return Result.success(null, data);

    }

    @GetMapping("/all")
    public Result get() throws IOException {
        Result result = gdpController.get("安徽省");
        List<Yuce> list = yuceService.list();
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("预测充电桩数量", list);
        hashMap.put("已知充电桩", result.getData());
        return Result.success(hashMap);
    }
}
