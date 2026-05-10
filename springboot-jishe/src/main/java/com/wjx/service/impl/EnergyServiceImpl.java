package com.wjx.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wjx.entity.Energy;
import com.wjx.mapper.EnergyMapper;
import com.wjx.service.EnergyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EnergyServiceImpl extends ServiceImpl<EnergyMapper, Energy> implements EnergyService {
    @Autowired
    private EnergyMapper energyMapper;

    public List<Energy> getAll(Integer address) {
        return energyMapper.getAll(address);
    }
}
