package com.wjx.entity;

import lombok.Data;

import java.io.Serializable;


@Data
public class Energy implements Serializable{
    private Integer id;
    private String 名称;
    private String 运营方;
    private int 数量;

    public Energy() {
    }

    public Energy(Integer id, String 名称, String 运营方, int 数量) {
        this.id = id;
        this.名称 = 名称;
        this.运营方 = 运营方;
        this.数量 = 数量;
    }
}
