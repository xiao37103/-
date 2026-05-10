package com.wjx.service;

import com.wjx.entity.Gao;
import com.wjx.entity.GaoDe;

import java.util.List;

public interface GaoDeService {
    List<GaoDe> select(String address);

    List<Gao> select1(String address, String comfort_norm);
}
