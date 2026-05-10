package com.wjx.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName(value = "gdp_1")
public class Gdp {
    private Integer id;
    private String 地址;
    private int 数量;
}
