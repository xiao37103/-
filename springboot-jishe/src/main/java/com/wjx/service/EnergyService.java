package com.wjx.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wjx.entity.Energy;
import org.springframework.stereotype.Service;

import java.util.List;

public interface EnergyService extends IService<Energy> {
    List<Energy> getAll(Integer address);
}
