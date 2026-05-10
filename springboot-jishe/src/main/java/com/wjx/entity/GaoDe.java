package com.wjx.entity;

import lombok.Data;

@Data
public class GaoDe {
    private String name;
    private String type;
    private String address;
    private String location;

    public GaoDe(String name, String type, String address, String location) {
        this.name = name;
        this.type = type;
        this.address = address;
        this.location = location;
    }
}
