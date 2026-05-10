package com.wjx.service.impl;

import com.wjx.entity.Gao;
import com.wjx.entity.GaoDe;
import com.wjx.mapper.GaoDeMapper;
import com.wjx.service.GaoDeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GaoDeServiceImpl implements GaoDeService {
    @Autowired
    private GaoDeMapper gaoDeMapper;

    @Override
    public List<GaoDe> select(String address) {
        return gaoDeMapper.select(address);
    }

    @Override
    public List<Gao> select1(String address, String comfort_norm) {
        return gaoDeMapper.select1(address, comfort_norm);
    }
}
