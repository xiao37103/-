package com.wjx.controller;


import com.alibaba.fastjson.JSON;
import com.wjx.entity.Gdp;
import com.wjx.service.GdpService;
import com.wjx.utils.Result;
import com.wjx.utils.hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


@RestController
@CrossOrigin
@RequestMapping("/gdp")
public class GdpController {
    @Autowired
    private GdpService gdpService;
    @Value("${wjx.json}")
    private String path;
    @Value("${wjx.big}")
    private String bigPath;

    @GetMapping
    public Result get(String province) throws IOException {
        HashMap<String, Object> hashMap = hash.myHashMap(path);
        Object providers = hashMap.get(province);
        List<String> list = (List<String>) providers;
        List<List<Gdp>> arrayList = new ArrayList<>();
        for (String str : list) {
            List<Gdp> gdpList = gdpService.getAll(str);
            if (gdpList.size() > 0) {
                arrayList.add(gdpList);
            }
        }
        return Result.success(arrayList);
    }

    @GetMapping("/big")
    public Result getBig() throws IOException {
        Resource resource = new ClassPathResource(bigPath);
        byte[] bytes = new byte[resource.getInputStream().available()];
        resource.getInputStream().read(bytes);
        Object myData = JSON.parseObject(bytes, Object.class);
        // 将Java对象转换为JSON字符串并返回给前端
        return Result.success(myData);
    }


}
