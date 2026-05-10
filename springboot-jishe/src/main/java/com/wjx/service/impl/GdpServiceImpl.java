package com.wjx.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wjx.entity.Gdp;
import com.wjx.mapper.GdpMapper;
import com.wjx.service.GdpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GdpServiceImpl extends ServiceImpl<GdpMapper, Gdp> implements GdpService {
    @Autowired
    private GdpMapper gdpMapper;

    @Override
    public List<Gdp> getAll(String address) {
        return gdpMapper.getAll(address);
    }
}
