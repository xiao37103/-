package com.wjx.utils;

import lombok.Data;

@Data
public class Result {
    private Integer code;
    private String message;
    private String[] message1;
    private Object data;

    public Result(String message, Object data) {
        this.code = 1;
        this.message = message;
        this.data = data;
    }

    public Result() {

    }

    public static Result error(String message) {
        Result result = new Result();
        result.code = 0;
        result.message = message;
        return result;
    }

    public static Result success(Object obj) {
        Result result = new Result();
        result.code = 1;
        result.data = obj;
        return result;
    }

    public static Result success(String[] message1, Object obj) {
        Result result = new Result();
        result.code = 1;
        result.message1 = new String[]{"工信部发布的《新能源汽车产业发展规划（2021-2035）》征求意见稿显示，预计到2030年，我国新能源汽车保有量将达6420万辆。根据车桩比1:1的建设目标，给出安徽省至2030年充电桩建设规划建议。",
                "结合图表与数据分析结果显示：1.宏观上看安徽省充电桩数量分布不均匀。2.合肥市其充电桩需求量与占有量大3.少数地区如淮北市、铜陵市，其充电桩设施需求量缺口较大。"};
        ;
        result.data = obj;
        return result;
    }
}
