package com.wjx.mapper;

import com.wjx.entity.Gao;
import com.wjx.entity.GaoDe;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface GaoDeMapper {
    @Select("SELECT * FROM ${address}")
    List<GaoDe> select(@Param("address") String address);

    @Select("SELECT * FROM ${address}充电桩 where comfort_norm>#{comfort_norm} ")
    List<Gao> select1(String address ,String comfort_norm);
}
