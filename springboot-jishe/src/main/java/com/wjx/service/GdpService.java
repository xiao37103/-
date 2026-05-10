package com.wjx.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wjx.entity.Gdp;

import java.util.List;

public interface GdpService extends IService<Gdp> {
    List<Gdp> getAll(String address);
}
