package com.wjx.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wjx.entity.Gdp;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface GdpMapper extends BaseMapper<Gdp> {
    @Select("select id,地址,数量 from gdp_1 where 名称=#{address}")
    List<Gdp> getAll(String address);
}
