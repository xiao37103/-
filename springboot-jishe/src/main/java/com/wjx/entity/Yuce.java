package com.wjx.entity;

import lombok.Data;

@Data
public class Yuce {
    private  String city;
    //    private String city;
    private Integer year_2018;
    private Integer year_2019;
    private Integer year_2020;
    private Integer year_2021;
    private Integer year_2022;
    private Integer year_2023;
    private Integer year_2024;
    private Integer year_2025;
    private Integer year_2026;
    private Integer year_2027;
    private Integer year_2028;
    private Integer year_2029;
    private Integer year_2030;

    public Yuce(Integer year_2018, Integer year_2019, Integer year_2020, Integer year_2021, Integer year_2022, Integer year_2023, Integer year_2024, Integer year_2025, Integer year_2026, Integer year_2027, Integer year_2028, Integer year_2029, Integer year_2030) {
        this.year_2018 = year_2018;
        this.year_2019 = year_2019;
        this.year_2020 = year_2020;
        this.year_2021 = year_2021;
        this.year_2022 = year_2022;
        this.year_2023 = year_2023;
        this.year_2024 = year_2024;
        this.year_2025 = year_2025;
        this.year_2026 = year_2026;
        this.year_2027 = year_2027;
        this.year_2028 = year_2028;
        this.year_2029 = year_2029;
        this.year_2030 = year_2030;
    }

    public Yuce(String city, Integer year_2018, Integer year_2019, Integer year_2020, Integer year_2021, Integer year_2022, Integer year_2023, Integer year_2024, Integer year_2025, Integer year_2026, Integer year_2027, Integer year_2028, Integer year_2029, Integer year_2030) {
        this.city = city;
        this.year_2018 = year_2018;
        this.year_2019 = year_2019;
        this.year_2020 = year_2020;
        this.year_2021 = year_2021;
        this.year_2022 = year_2022;
        this.year_2023 = year_2023;
        this.year_2024 = year_2024;
        this.year_2025 = year_2025;
        this.year_2026 = year_2026;
        this.year_2027 = year_2027;
        this.year_2028 = year_2028;
        this.year_2029 = year_2029;
        this.year_2030 = year_2030;
    }
}
