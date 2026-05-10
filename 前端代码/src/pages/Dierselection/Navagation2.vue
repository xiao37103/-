<template>
    <div class="bjc">
        <div class="test">
            <div class="model_box">
                <div style="flex:0 1 30%;">
                    <!-- <dv-decoration-6 style="height:30px;" /> -->
                    <router-link to="/Dierye" class="btnbcack">
                    <i class="el-icon-back" ></i>
                    </router-link>
                    <dv-decoration-10 style="height:5px;margin-top:5px;" />
                </div>
                <div style="flex:0 1 40%;">
                    <div class="flexbox">
                        <dv-decoration-8 style="height:60px;flex:0 1 20%;" />
                        <dv-decoration-11
                            style="height:60px;flex:0 1 60%;color:white;"
                            class="pos"
                        >安徽省充电桩分布分析平台</dv-decoration-11>
                        <dv-decoration-8 :reverse="true" style="height:60px;flex:0 1 20%;" />
                    </div>
                </div>
                <div style="flex:0 1 30%;">
                    <!-- <dv-decoration-6 style="height:30px;" /> -->
                   

                    <dv-decoration-10
                        style="height:5px;transform:rotateY(180deg);margin-top:55px;"
                    />
                </div>
            </div>
        </div>
        <div id="main" style="width: 1200px;height:600px;" ref="main"></div>
        <!-- <router-view></router-view> -->
    </div>
</template>

<script>
import $ from "jquery";
import * as echarts from "echarts";
import { MapChart } from "echarts/charts";
echarts.use([MapChart]);
export default {
    name: "Navagation2",
    data() {
        return {
            data: [],
        };
    },
    methods: {
        initecharts() {
            var chartDom = document.getElementById("main");
            var myChart = echarts.init(chartDom);
            var optionXyMap01;
            // var uploadedDataURL =
            //     "https://geo.datav.aliyun.com/areas_v3/bound/geojson?code=340000_full";

            var t = 1; //流入流出控制0
            var r = 1; //右侧流入流出文字控制

            var geoCoordMap = {
                合肥市: [117.25, 31.83],
                滁州市: [118.32, 32.3],
                芜湖市: [118.43, 31.35],
                阜阳市: [115.38, 32.89],
                蚌埠市: [117.39, 32.92],
                淮南市: [116.99, 32.63],
                马鞍山市: [118.5, 31.71],
                安庆市: [117.05, 30.52],
                黄山市: [118.33, 29.71],
                铜陵市: [117.81, 30.94],
                宿州市: [116.98, 33.64],
                淮北市: [116.8, 33.9],
                六安市: [116.51, 31.75],
                池州市: [117.49, 30.66],
                宣城市: [118.76, 30.94],
                亳州市: [115.78, 33.84],
            };
            var d1 = {
                合肥市: 15634,
                芜湖市: 7869,
                滁州市: 2397,
                阜阳市: 5218,
                安庆市: 1534,
                马鞍山市: 1152,
                蚌埠市: 1969,
                宿州市: 3383,
                亳州市: 3140,
                六安市: 2910,
                宣城市: 1298,
                淮南市: 1322,
                淮北市: 902,
                铜陵市: 736,
                池州市: 459,
                黄山市: 594,
            };

            var d2 = {
                合肥市: 26085,
                芜湖市: 9313,
                滁州市: 3610,
                阜阳市: 7295,
                安庆市: 2473,
                马鞍山市: 1767,
                蚌埠市: 2482,
                宿州市: 4287,
                亳州市: 4910,
                六安市: 3671,
                宣城市: 1911,
                淮南市: 1650,
                淮北市: 1252,
                铜陵市: 1219,
                池州市: 685,
                黄山市: 847,
            };

            var d3 = {
                合肥市: 35412,
                芜湖市: 11281,
                滁州市: 4779,
                阜阳市: 9999,
                安庆市: 3584,
                马鞍山市: 2953,
                蚌埠市: 3290,
                宿州市: 5855,
                亳州市: 6470,
                六安市: 4918,
                宣城市: 2665,
                淮南市: 2158,
                淮北市: 1723,
                铜陵市: 1722,
                池州市: 1032,
                黄山市: 1172,
            };
            var d4 = {
                合肥市: 61760,
                芜湖市: 16527,
                滁州市: 8671,
                阜阳市: 18882,
                安庆市: 7604,
                马鞍山市: 6585,
                蚌埠市: 5674,
                宿州市: 10295,
                亳州市: 11945,
                六安市: 8966,
                宣城市: 5828,
                淮南市: 4691,
                淮北市: 3879,
                铜陵市: 3556,
                池州市: 2111,
                黄山市: 2327,
            };

            var d5 = {
                合肥市: 130823,
                芜湖市: 29674,
                滁州市: 16913,
                阜阳市: 37499,
                安庆市: 15791,
                马鞍山市: 14360,
                蚌埠市: 11281,
                宿州市: 18974,
                亳州市: 27618,
                六安市: 17070,
                宣城市: 12907,
                淮南市: 10742,
                淮北市: 7587,
                铜陵市: 7132,
                池州市: 4893,
                黄山市: 5108,
            };

            var d6 = {
                合肥市: 141275,
                芜湖市: 31559,
                滁州市: 18027,
                阜阳市: 39902,
                安庆市: 17025,
                马鞍山市: 15391,
                蚌埠市: 12169,
                宿州市: 20217,
                亳州市: 29284,
                六安市: 18247,
                宣城市: 13820,
                淮南市: 11394,
                淮北市: 8115,
                铜陵市: 7670,
                池州市: 5394,
                黄山市: 5542,
            };

            var colors = [
                "#1DE9B6",
                "#EEDD78",
                "#FFFF00",
                "#FFDB5C",
                "#37A2DA",
                "#04B9FF",
            ];
            var colorIndex = 0;
            $(function () {
                var year = ["2018", "2019", "2020", "2021", "2022", "2023"];
                var mapData = [[], [], [], [], [], []];

                /*柱子Y名称*/
                var categoryData = [];
                var barData = [];

                for (var key in geoCoordMap) {
                    mapData[0].push({
                        year: "2018",
                        name: key,
                        value: d1[key] / 10,
                        value1: d1[key] / 10,
                    });
                    mapData[1].push({
                        year: "2019",
                        name: key,
                        value: d2[key] / 10,
                        value1: d2[key] / 10,
                    });
                    mapData[2].push({
                        year: "2020",
                        name: key,
                        value: d3[key] / 10,
                        value1: d3[key] / 10,
                    });
                    mapData[3].push({
                        year: "2021",
                        name: key,
                        value: d4[key] / 10,
                        value1: d4[key] / 10,
                    });
                    mapData[4].push({
                        year: "2022",
                        name: key,
                        value: d5[key] / 10,
                        value1: d5[key] / 10,
                    });
                    mapData[5].push({
                        year: "2023",
                        name: key,
                        value: d6[key] / 10,
                        value1: d6[key] / 10,
                    });
                }

                for (var i = 0; i < mapData.length; i++) {
                    // console.log(mapData);
                    // this.data = mapData

                    // for(var i = 0;i<mapData.length;i++ ){
                    //     for(var y= 0;y<mapData[i].length;y++ ){
                    //         var arr = [];
                    //         arr.push(mapData[i][y])
                    //     }
                    // }
                    // console.log(arr);

                    // var ar1=  arr.slice(0,16)
                    // var ar2=  arr.slice(16,32)
                    // var ar3=  arr.slice(32,48)
                    // var ar4=  arr.slice(48,64)
                    // var ar5=  arr.slice(64,80)
                    // var ar6=  arr.slice(80,96)
                    // console.log(ar2);
                    mapData[i].sort(function sortNumber(a, b) {
                        return a.value - b.value;
                    });
                    barData.push([]);
                    categoryData.push([]);
                    for (var j = 0; j < mapData[i].length; j++) {
                        barData[i].push(mapData[i][j].value1 * 10);
                        categoryData[i].push(mapData[i][j].name);
                    }
                }

                $.getJSON("340000.json", function (geoJson) {
                    // echarts.registerMap(nameMap, gdMap);
                    echarts.registerMap("anhui", geoJson);
                    var convertData = function (data) {
                        var res = [];
                        for (var i = 0; i < data.length; i++) {
                            var geoCoord = geoCoordMap[data[i].name];
                            if (geoCoord) {
                                res.push({
                                    name: data[i].name,
                                    value: geoCoord.concat(data[i].value),
                                });
                            }
                        }
                        return res;
                    };

                    var convertToLineData = function (data, gps) {
                        var res = [];
                        for (var i = 0; i < data.length; i++) {
                            var dataItem = data[i];
                            var toCoord = geoCoordMap[dataItem.name];
                            var fromCoord = gps; //郑州
                            //  var toCoord = geoGps[Math.random()*3];
                            if (fromCoord && toCoord) {
                                if (t == 1) {
                                    res.push([
                                        {
                                            coord: toCoord,
                                        },
                                        {
                                            coord: fromCoord,
                                            value: dataItem.value,
                                        },
                                    ]);
                                } else {
                                    res.push([
                                        {
                                            coord: fromCoord,
                                            value: dataItem.value,
                                        },
                                        {
                                            coord: toCoord,
                                        },
                                    ]);
                                }
                            }
                        }
                        if (t === 0) {
                            t = 1;
                        } else {
                            t = 0;
                        }
                        return res;
                    };

                    optionXyMap01 = {
                        timeline: {
                            data: year,
                            axisType: "category",
                            autoPlay: true,
                            playInterval: 1500,
                            left: "10%",
                            right: "10%",
                            bottom: "3%",
                            width: "80%",
                            //  height: null,
                            label: {
                                normal: {
                                    textStyle: {
                                        color: "#ddd",
                                    },
                                },
                                emphasis: {
                                    textStyle: {
                                        color: "#fff",
                                    },
                                },
                            },
                            symbolSize: 10,
                            lineStyle: {
                                color: "#FFFFFF",
                            },
                            checkpointStyle: {
                                borderColor: "#FFFFFF",
                                borderWidth: 2,
                            },
                            controlStyle: {
                                showNextBtn: true,
                                showPrevBtn: true,
                                normal: {
                                    color: "#666",
                                    borderColor: "#666",
                                },
                                emphasis: {
                                    color: "#FFFFFF",
                                    borderColor: "#aaa",
                                },
                            },
                        },
                        baseOption: {
                            animation: true,
                            animationDuration: 1000,
                            animationEasing: "cubicInOut",
                            animationDurationUpdate: 1000,
                            animationEasingUpdate: "cubicInOut",
                            grid: {
                                
                                right: "2%",
                                top: "10%",
                                bottom: "10%",
                                width: "18%",
                            },
                            tooltip: {
                              
                                trigger: "axis", // hover触发器
                                axisPointer: {
                                    // 坐标轴指示器，坐标轴触发有效
                                    type: "shadow", // 默认为直线，可选为：'line' | 'shadow'
                                    shadowStyle: {
                                        color: "rgba(150,150,150,0.1)", //hover颜色
                                    },
                                },
                            },
                            visualMap: {
                                show:false,
                                min: 300,
                                max: 12000,
                                left: "left",
                                top: "bottom",
                                text: ["高", "低"],
                                textStyle: {
                                    color: "white",
                                },
                                calculable: true,
                                colorLightne: [0.8, 100],
                                color: ["#c05050", "#e5cf0d", "#5ab1ef"],
                                dimension: 0,
                            },
                            geo: {
                                show: true,
                                map: "anhui",
                                roam: true,
                                zoom: 1,
                                center: [117.28492, 31.861184],
                                label: {
                                    emphasis: {
                                        show: true,
                                    },
                                },
                                itemStyle: {
                                    normal: {
                                        borderColor: "rgba(147, 235, 248, 1)",
                                        borderWidth: 1,
                                        areaColor: {
                                            type: "radial",
                                            x: 0.5,
                                            y: 0.5,
                                            r: 0.8,
                                            colorStops: [
                                                {
                                                    offset: 0,
                                                    color: "rgba(3, 0, 38,9)", // 0% 处的颜色
                                                },
                                                {
                                                    offset: 1,
                                                    color: "rgba(3, 0, 38,9)", // 100% 处的颜色
                                                },
                                            ],
                                            globalCoord: false, // 缺省为 false
                                        },
                                        shadowColor: "rgba(128, 217, 248, 9)",
                                        // shadowColor: 'rgba(255, 255, 255, 1)',
                                        shadowOffsetX: -2,
                                        shadowOffsetY: 2,
                                        shadowBlur: 10,
                                    },
                                    emphasis: {
                                        areaColor: "#FCFFD9",
                                        borderWidth: 0,
                                    },
                                },
                            },
                        },
                        options: [],
                    };

                    for (var n = 0; n < year.length; n++) {
                        var statistic_name = "安徽省各市新能源汽车数量";
                        optionXyMap01.options.push({
                            backgroundColor: null,

                            title: [
                                {
                                    text:
                                        "安徽省2018年-2023年各地市新能源汽车保有量数据",
                                    //subtext: '   ',
                                    left: "15%",
                                    top: "1%",
                                    textStyle: {
                                        color: "#fff",
                                        fontSize: 25,
                                    },
                                },
                                {
                                    id: "statistic",
                                    text: statistic_name,
                                    left: "75%",
                                    top: "0%",
                                    textStyle: {
                                        color: "#fff",
                                        fontSize: 16,
                                    },
                                },
                            ],
                            xAxis: {
                                type: "value",
                                scale: false,
                                position: "top",
                                min: 0,
                                boundaryGap: true,
                              
                                splitLine: {
                                    show: true,
                                },
                                axisLine: {
                                    show: true,
                                   
                                },
                                axisTick: {
                                    show: true,
                                },
                                axisLabel: {
                                    margin: 1,
                                    rotate: 60,
                                    textStyle: {
                                        color: "#aaa",
                                    },
                                },
                                data: {
                                    textStyle: {
                                        fontStyle: "oblique",
                                    },
                                },
                            },
                            yAxis: {
                                type: "category",
                                //  name: 'TOP 20',

                                nameGap: 16,
                                axisLine: {
                                    show: true,
                                    lineStyle: {
                                        color: "#ddd",
                                    },
                                },
                                axisTick: {
                                    show: false,
                                    lineStyle: {
                                        color: "#ddd",
                                    },
                                },
                                axisLabel: {
                                    interval: 0,
                                    textStyle: {
                                        color: "#ddd",
                                    },
                                },
                                data: categoryData[n],
                            },

                            series: [
                                {
                                    //文字和标志
                                    name: "light",
                                    type: "scatter",
                                    coordinateSystem: "geo",
                                    data: convertData(mapData[n]),

                                    label: {
                                        normal: {
                                            formatter: "{b}",
                                            position: "right",
                                            show: true,
                                        },
                                        emphasis: {
                                            show: false,
                                        },
                                    },
                                    itemStyle: {
                                        normal: {
                                            color: colors[colorIndex][n],
                                        },
                                    },
                                },
                                //地图?
                                {
                                    type: "map",
                                    map: "anhui",
                                    geoIndex: 0,
                                    aspectScale: 0.6, //长宽比
                                    showLegendSymbol: false, // 存在legend时显示
                                    label: {
                                        normal: {
                                            show: false,
                                        },
                                        emphasis: {
                                            show: true,
                                            textStyle: {
                                                color: "#fff",
                                            },
                                        },
                                    },
                                    roam: true,
                                    itemStyle: {
                                        normal: {
                                            areaColor: "#031525",
                                            borderColor: "#FFFFFF",
                                        },
                                        emphasis: {
                                            areaColor: "#2B91B7",
                                        },
                                    },
                                    animation: true,
                                    data: mapData,
                                },
                                //地图点的动画效果
                                {
                                    type: "scatter",
                                    coordinateSystem: "geo",
                                    symbol: "pin",
                                    symbolSize: [50, 50],

                                    label: {
                                        normal: {
                                            show: true,
                                            textStyle: {
                                                color: "#fff",
                                                fontSize: 9,
                                            },
                                            formatter(value) {
                                                return value.data.value[2] * 10;
                                            },
                                        },
                                    },
                                    itemStyle: {
                                        normal: {
                                            color: "#D8BC37", //标志颜色
                                        },
                                    },
                                    data: convertData(
                                        mapData[n]
                                            .sort(function (a, b) {
                                                return b.value - a.value;
                                            })
                                            .slice(0, 35)
                                    ),
                                    showEffectOn: "render",
                                    rippleEffect: {
                                        brushType: "stroke",
                                    },
                                    hoverAnimation: true,
                                    zlevel: 1,
                                },
                                //地图线的动画效果

                                //柱状图
                                {
                                    zlevel: 1,
                                    type: "bar",
                                    barMaxWidth: 10,

                                    symbol: "none",
                                    itemStyle: {
                                        normal: {
                                            color: colors[n],
                                            barBorderRadius: [0, 30, 30, 0],
                                        },
                                    },
                                    data: barData[n],
                                },
                            ],
                        });
                        if (r === 0) {
                            r = 1;
                        } else {
                            r = 0;
                        }
                    }
                    myChart.setOption(optionXyMap01);
                });
            });
        },
    },
    mounted() {
        this.initecharts();
    },
};
</script>

<style scoped>
.el-icon-back{
    font-size: 60px;
    text-align: center;
    line-height: 50px;
    color: white;
}
.el-icon-back:hover{
    transition-property: color;
    transition-duration: 0.8s;
    transition-timing-function: ease-out;
    color: skyblue;
}
.btnbcack{
    display:block;
    width: 100px;
    height: 50px;
    margin-top: 0px;
}
/* .btnbcack:hover{
    display: block;
    width: 50px;
    height: 30px;
} */
#main {
    margin-left: 100px;
    margin-top: 50px;
}
.bjc {
    position: fixed;
    width: 100%;
    height: 100vh;
    /* background-color: skyblue; */
    background: url(@/assets/imgs/R.jpg);
}
.test {
    width: 100%;
}
.model_box {
    display: flex;
    justify-content: space-between;
}
.flexbox {
    display: flex;
}
.pos {
    margin-top: 10px;
}
</style>