package com.wjx.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wjx.entity.Energy;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface EnergyMapper extends BaseMapper<Energy> {
    @Select("select * from table_#{address}")
    List<Energy> getAll(Integer address);


}
