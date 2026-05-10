package com.wjx.controller;


import com.wjx.entity.Energy;
import com.wjx.service.EnergyService;
import com.wjx.utils.FX;
import com.wjx.utils.Result;
import com.wjx.utils.hash;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.io.*;
import java.util.*;


@RestController
@Slf4j
@CrossOrigin
@RequestMapping("/energy")
public class EnergyController {
    @Autowired
    private EnergyService energyService;
    @Value("${wjx.data}")
    private String path;


    public List<List<Energy>> getAllEnergyData(String address) throws IOException {
        HashMap<String, Object> hashMap = hash.myHashMap(path);
        List<List<Energy>> result = new ArrayList<>();
        for (Map.Entry<String, Object> entry : hashMap.entrySet()) {
            if (address.equals(entry.getKey())) {
                List<Energy> data = energyService.getAll(Integer.valueOf((String) entry.getValue()));
                if (!data.isEmpty()) {
                    result.add(data);
                    return result;
                }
            } else if (address.endsWith("市市辖区") && entry.getKey().contains(address.substring(0, 3))) {
                List<Energy> data = energyService.getAll(Integer.valueOf((String) entry.getValue()));
                if (!data.isEmpty()) {
                    result.add(data);
                }
            }
        }
        return result;
    }


    @GetMapping("/page")
    public Result getInfo(String address) throws IOException {
        List<List<Energy>> list = getAllEnergyData(address);
        if (!list.isEmpty()) {
            return Result.success(list);
        } else {
            return Result.error(address + "地区的充电桩没有收录");
        }
    }

    @GetMapping("/big")
    public Result getBig(String address) throws IOException {
        List<List<Energy>> list = getAllEnergyData(address);
        if (!list.isEmpty()) {
            return Result.success(list);
        } else {
            return Result.error(address + "地区新能源充电桩未收录");
        }

    }


    @GetMapping("/year")
    public Result getFx(double year) {
        return FX.fx(year);
    }
}


