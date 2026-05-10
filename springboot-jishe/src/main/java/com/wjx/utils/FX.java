package com.wjx.utils;

import org.apache.commons.math3.stat.regression.SimpleRegression;

import java.util.*;


public class FX {

    public static Result fx(double year) {
        HashMap<Double, Double> data = new HashMap<>();
        data.put(202202.0, 121.3);
        data.put(202203.0, 123.2);
        data.put(202204.0, 133.2);
        data.put(202205.0, 141.9);
        data.put(202206.0, 152.8);
        data.put(202207.0, 157.5);
        data.put(202208.0, 162.3);
        data.put(202209.0, 163.6);
        data.put(202210.0, 168.0);
        data.put(202211.0, 173.1);
        data.put(202212.0, 179.7);
        data.put(202301.0, 183.9);
        SimpleRegression regression = new SimpleRegression();
        for (Map.Entry<Double, Double> entry : data.entrySet()) {
            regression.addData(entry.getKey(), entry.getValue());
        }
//
        ArrayList<Double> list = getYearsList(year);
        System.out.println(list);
        for (Double d : list) {
            double predictedValue = regression.predict(d);
            data.put(d, predictedValue);
        }
        return Result.success(data);

    }
    public static ArrayList<Double> getYearsList(double year) {
        ArrayList<Double> years = new ArrayList<>();
        for (double i = year; i > 202301; i--) {
            years.add(i);
        }
        return years;
    }
}
