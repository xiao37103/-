<template>
    <div class="bjc">
        <!-- 头部 -->
        <div class="test">
            <div class="model_box">
                <div style="flex:0 1 30%;">
                    <router-link to="/Dierselection/Navagation2" class="btnbcack1">
                        <dv-decoration-11 style="width:250px;height:60px;color:white;">新能源汽车保有量数据</dv-decoration-11>
                    </router-link>
                    <dv-decoration-10 style="height:5px;margin-top:5px;" />
                </div>
                <div style="flex:0 1 40%;">
                    <div class="flexbox">
                        <dv-decoration-8 style="height:60px;flex:0 1 20%;" />
                        <dv-decoration-11
                            style="height:80px;flex:0 1 60%;color:white;font-size:20px;"
                            class="pos"
                        >
                        <router-link
                                    to="/Souye"
                                    style="text-decoration:none;color:white;"
                                >安徽省充电桩分布分析平台</router-link>
                        </dv-decoration-11>
                        <dv-decoration-8 :reverse="true" style="height:60px;flex:0 1 20%;" />
                    </div>
                </div>
                <div style="flex:0 1 30%;">
                    <router-link to="/Dierselection/Analysis" class="btnbcack">
                        <dv-decoration-11 style="width:250px;height:60px;color:white;">相关性分析</dv-decoration-11>
                    </router-link>
                    <dv-decoration-10 style="height:5px;transform:rotateY(180deg);margin-top:5px;" />
                </div>
            </div>
        </div>
        <!-- 第一层 -->
        <div class="content">
            <div class="model_box">
                <div style="flex:0 1 28%" class="leftbox">
                    <dv-border-box-4
                        :reverse="true"
                        style="height:185px;"
                        :color="['rgba(13, 206, 235, 0.88)', ]"
                    >
                        <el-select
                            v-model="value"
                            placeholder="请选择"
                            style="margin-top:20px;margin-left:30px;"
                        >
                            <el-option
                                v-for="item in cities"
                                :key="item.value"
                                :label="item.label"
                                :value="item.label"
                            >
                                <span style="float: left">{{ item.label }}</span>
                                <span
                                    style="float: right; color: #8492a6; font-size: 13px"
                                >{{ item.value }}</span>
                            </el-option>
                        </el-select>
                        <el-button type="primary" plain id="btnsearch">点击搜索</el-button>
                        <!-- <el-button plain @click="btn">朴素按钮</el-button> -->
                    </dv-border-box-4>
                    <dv-border-box-7 :color="['rgba(13, 206, 235, 0.88)']" style="height:250px;">
                        <dv-decoration-7
                            style="width:300px;height:30px;color:white;margin-left:45px;"
                        >{{this.value}}指数平滑法充电桩预测结果</dv-decoration-7>
                        <div id="left1" style="width:400px;height:250px;"></div>
                    </dv-border-box-7>
                    <dv-border-box-7
                        :color="['rgba(13, 206, 235, 0.88)']"
                        style="height:250px;margin-top:5px;bottom:;"
                    >
                        <dv-decoration-7
                            style="width:350px;height:30px;color:white;margin-left:30px;font-size:10px;"
                        >{{this.value}}2030年国家标准下充电桩建成比例</dv-decoration-7>
                        <div id="left2" style="width:400px;height:200px;"></div>
                    </dv-border-box-7>
                </div>
                <div style="flex:0 1 70%">
                    <div style="display:flex">
                        <div style="flex:0 1 50%">
                            <div
                                style="height:180px;width:100%;margin-left:-8px;margin-bottom:5px;background-color:rgba(1,1,1,0.1);"
                            >
                                <dv-decoration-7
                                    style="width:300px;height:30px;color:white;margin-left:100px;"
                                >安徽省各市现有量与2030年标准量</dv-decoration-7>
                                <div
                                    id="middletop"
                                    style="width:450px;height:180px;margin-top:-35px;"
                                ></div>
                            </div>
                            <dv-border-box-7 style="height:505px;margin-left:-8px;">
                                <dv-decoration-7
                                    style="width:300px;height:30px;color:white;"
                                    class="rltitle"
                                >现有充电桩的分布（除合肥市以外）</dv-decoration-7>
                                <div id="main" style="width:460px;height:460px;"></div>
                            </dv-border-box-7>
                        </div>
                        <div style="flex:0 1 50%">
                            <div
                                style="height:180px;width:100%;margin-bottom:5px;background-color:rgba(1,1,1,0.1);"
                            >
                                <dv-decoration-7
                                    style="width:300px;height:30px;color:white;"
                                    class="rltitle2"
                                >预测对比结果分析</dv-decoration-7>
                                <div
                                    style="width:100%;height:70px;border-bottom:1px solid black;font-size:14px;color:white;text-indent:2em;"
                                >{{this.message1}}</div>
                                <div
                                    style="width:100%;height:20px;font-size:14px;color:white;text-indent:2em;"
                                >{{this.firstsentence0}}</div>
                                <div
                                    style="width:100%;height:20px;font-size:14px;color:white;text-indent:2em;"
                                >{{this.firstsentence1}}</div>
                                <div
                                    style="width:100%;height:20px;font-size:14px;color:white;text-indent:2em;"
                                >{{this.firstsentence2}}</div>
                            </div>
                            <dv-border-box-7 style="height:505px">
                                <dv-decoration-7
                                    style="width:450px;height:30px;color:white;"
                                    class="rltitle1"
                                >"多车一桩"政策下充电桩的理想分布（除合肥市以外）</dv-decoration-7>
                                <div id="main1" style="width:460px;height:460px;"></div>
                            </dv-border-box-7>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
import "echarts-liquidfill";
import $ from "jquery";
import * as echarts from "echarts";
import { MapChart } from "echarts/charts";
echarts.use([MapChart]);
import "echarts-gl";
export default {
    name: "Dierye",
    data() {
        return {
            cities: [
                 {
                    value: "Hefeishi",
                    label: "合肥市",
                },
                 {
                    value: "Wuhushi",
                    label: "芜湖市",
                },
                {
                    value: "Huaibeishi",
                    label: "淮北市",
                },
                {
                    value: "Shuzhoushi",
                    label: "宿州市",
                },
                {
                    value: "Fuyangshi",
                    label: "阜阳市",
                },
                {
                    value: "Bozhoushi",
                    label: "亳州市",
                },
                {
                    value: "Bengbushi",
                    label: "蚌埠市",
                },
                {
                    value: "Chuzhoushi",
                    label: "滁州市",
                },
                {
                    value: "Huainanshi",
                    label: "淮南市",
                },
                {
                    value: "Luanshi",
                    label: "六安市",
                },
                {
                    value: "Maanshanshi",
                    label: "马鞍山市",
                },
                {
                    value: "Tonglingshi",
                    label: "铜陵市",
                },
                {
                    value: "Chizhoushi",
                    label: "池州市",
                },
                {
                    value: "Huangshanshi",
                    label: "黄山市",
                },
                {
                    value: "Xuanchengshi",
                    label: "宣城市",
                },
            ],
            value: "",
            left1arr: [],
            shantunow: [],
            zhuzhuang: [],
            cityarr: [],
            yuce: [],
            persent: [],
            message1: "",
            firstsentence0: "",
            firstsentence1: "",
            firstsentence2: "",
        };
    },
    component: {},
    methods: {
        // 右边的图表
        initecharts() {
            var chartDom = document.getElementById("main");
            var myChart = echarts.init(chartDom);
            var option;
            $.getJSON("340000.json", function (geoJson) {
                // 注册地图
                echarts.registerMap("GD", geoJson);
                // 初始化 ECharts 实例
                // 配置地图和数据 ...
                let data = [
                    // ["117.283042", "31.86119", 22700], // '合肥'
                    ["118.376451", "31.326319", 5170], // '芜湖'
                    ["117.363228", "32.939667", 1300], // '蚌埠'
                    ["117.018329", "32.647574", 1170], // '淮南'
                    ["118.507906", "31.689362", 1500], // '马鞍山'
                    ["116.794664", "33.971707", 470], // '淮北'
                    ["117.816576", "30.929935", 40], // '铜陵'
                    ["117.043551", "30.50883", 9990], // '安庆'
                    ["118.317325", "29.709239", 1580], // '黄山'
                    ["118.316264", "32.303627", 2610], // '滁州'
                    ["115.819729", "32.896969", 3450], // '阜阳'
                    ["116.984084", "33.633891", 3690], // '宿州'
                    ["116.621138", "40.432762", 20], // '巢湖'
                    ["116.507676", "31.752889", 3510], // '六安'
                    ["115.782939", "33.869338", 2220], // '亳州'
                    ["117.489157", "30.656037", 1450], // '池州'
                    ["118.757995", "30.945667", 1930], // '宣城'
                ];
                let areaData = [];
                data.map((item) => {
                    // 扩大热力图效果
                    areaData.push(...new Array(3).fill(item));
                });
                let mapMax = Math.max(...data.map((item) => item[2]));
                let mapMin = Math.min(...data.map((item) => item[2]));

                option = {
                    backgroundColor: null,
                    tooltip: {
                        show: false,
                        trigger: "item",
                        axisPointer: {
                            type: "shadow",
                        },
                    },

                    visualMap: {
                        bottom: 20,
                        left: 10,
                        show: true,
                        color: ["#ff4601", "#fffc00", "#87cffa"],
                        min: mapMin,
                        max: mapMax,
                        calculable: true,
                        textStyle: {
                            color: "#fff",
                            fontSize: 12,
                        },
                    },

                    grid: {
                        height: "100%",
                        width: "100%",
                    },

                    geo: {
                        map: "GD",
                        label: {
                            show: true,
                        },
                        top: "center",
                        left: "60",
                        roam: true,
                        width: "90%",
                        height: "95%",
                        zoom: 0.9,
                        label: {
                            normal: {
                                show: true,
                                color: "#fff",
                            },
                            emphasis: {
                                color: "#fff",
                            },
                        },
                        itemStyle: {
                            normal: {
                                areaColor: "black",
                                borderColor: "#eee",
                                shadowColor: "#76b1ff",
                                shadowBlur: 10,
                                borderWidth: 1,
                            },
                            emphasis: {
                                // 鼠标移入颜色
                                areaColor: "#409EFF",
                                borderWidth: 0,
                            },
                        },
                    },

                    series: [
                        {
                            mapType: "GD",
                            top: "center",
                            left: "center",
                            width: "65%",
                            height: "95%",

                            name: "AQI",
                            type: "heatmap",
                            coordinateSystem: "geo",
                            blurSize: 40,
                            data: areaData,
                            // 鼠标移入
                            emphasis: {
                                show: false,
                                itemStyle: {
                                    areaColor: "rgb(255,255,0,1)",
                                },
                            },
                        },
                    ],
                };
                myChart.setOption(option);
                window.addEventListener("resize", function () {
                    myChart.resize();
                });
            });
        },
        initecharts1() {
            var chartDom = document.getElementById("main1");
            var myChart = echarts.init(chartDom);
            var option;
            $.getJSON("340000.json", function (geoJson) {
                // 注册地图
                echarts.registerMap("GD", geoJson);
                // 初始化 ECharts 实例
                // 配置地图和数据 ...
                let data = [
                    // ["117.283042", "31.86119", 233694], // '合肥'
                    ["118.376451", "31.326319", 44756], // '芜湖'
                    ["117.363228", "32.939667", 18388], // '蚌埠'
                    ["117.018329", "32.647574", 16557], // '淮南'
                    ["118.507906", "31.689362", 22611], // '马鞍山'
                    ["116.794664", "33.971707", 11815], // '淮北'
                    ["117.816576", "30.929935", 11636], // '铜陵'
                    ["117.043551", "30.50883", 25661], // '安庆'
                    ["118.317325", "29.709239", 8580], // '黄山'
                    ["118.316264", "32.303627", 25823], // '滁州'
                    ["115.819729", "32.896969", 56723], // '阜阳'
                    ["116.984084", "33.633891", 28916], // '宿州'
                    ["116.621138", "40.432762", 501], // '巢湖'
                    ["116.507676", "31.752889", 26484], // '六安'
                    ["115.782939", "33.869338", 40946], // '亳州'
                    ["117.489157", "30.656037", 8905], // '池州'
                    ["118.757995", "30.945667", 20211], // '宣城'
                ];
                let areaData = [];
                data.map((item) => {
                    // 扩大热力图效果
                    areaData.push(...new Array(3).fill(item));
                });
                let mapMax = Math.max(...data.map((item) => item[2]));
                let mapMin = Math.min(...data.map((item) => item[2]));

                option = {
                    backgroundColor: null,
                    tooltip: {
                        show: false,
                        trigger: "item",
                        axisPointer: {
                            type: "shadow",
                        },
                    },

                    visualMap: {
                        bottom: 20,
                        left: 10,
                        show: true,
                        color: ["#ff4601", "#fffc00", "#87cffa"],
                        min: mapMin,
                        max: mapMax,
                        calculable: true,
                        textStyle: {
                            color: "#fff",
                            fontSize: 12,
                        },
                    },

                    grid: {
                        height: "100%",
                        width: "100%",
                    },

                    geo: {
                        map: "GD",
                        label: {
                            show: true,
                        },
                        top: "center",
                        left: "60",
                        roam: true,
                        width: "90%",
                        height: "95%",
                        zoom: 0.9,
                        label: {
                            normal: {
                                show: true,
                                color: "#fff",
                            },
                            emphasis: {
                                color: "#fff",
                            },
                        },
                        itemStyle: {
                            normal: {
                                areaColor: "black",
                                borderColor: "#eee",
                                shadowColor: "#76b1ff",
                                shadowBlur: 10,
                                borderWidth: 1,
                            },
                            emphasis: {
                                // 鼠标移入颜色
                                areaColor: "#409EFF",
                                borderWidth: 0,
                            },
                        },
                    },

                    series: [
                        {
                            mapType: "GD",
                            top: "center",
                            left: "center",
                            width: "65%",
                            height: "95%",

                            name: "AQI",
                            type: "heatmap",
                            coordinateSystem: "geo",
                            blurSize: 40,
                            data: areaData,
                            // 鼠标移入
                            emphasis: {
                                show: false,
                                itemStyle: {
                                    areaColor: "rgb(255,255,0,1)",
                                },
                            },
                        },
                    ],
                };
                myChart.setOption(option);
                window.addEventListener("resize", function () {
                    myChart.resize();
                });
            });
        },
        // 左边的图表
        initecharts2() {
            var chartDom = document.getElementById("left1");
            var myChart = echarts.init(chartDom);
            var option;

            option = {
                title: {
                    // text: "  4564 ",
                    subtext: "万(个)",
                    subtextStyle:{
                            color:'#ccc'
                        }
                },
                tooltip: {
                    trigger: "axis",
                    axisPointer: {
                        type: "cross",
                    },
                },
                toolbox: {
                    show: false,
                    feature: {
                        saveAsImage: {},
                    },
                },
                xAxis: {
                    type: "category",
                    boundaryGap: false,
                    // prettier-ignore
                    data: ['2018', '2019', '2020', '2021', '2022', '2023', '2024', '2025', '2026', '2027', '2028', '2029', '2030', ],
                    axisLabel:{
                        show:true,
                        textStyle:{
                            color:'white'
                        }
                    }
                },
                yAxis: {
                    type: "value",
                    axisLine: {
                        show: true,
                    },
                    axisLabel: {
                        formatter: "{value} W",
                        textStyle:{
                            color:'white'
                        }
                    },
                    axisPointer: {
                        snap: true,
                    },
                },
                visualMap: {
                    show: false,
                    dimension: 0,
                    pieces: [
                        {
                            gt: 0,
                            lte: 5,
                            color: "green",
                        },
                        {
                            gt: 5,
                            color: "red",
                        },
                    ],
                },
                series: [
                    {
                        name: "num",
                        type: "line",

                        smooth: true,
                        // prettier-ignore
                        data: this.left1arr,
                        markArea: {
                            itemStyle: {
                                color: "rgba(255, 173, 177, 0.4)",
                            },
                            data: [
                                [
                                    {
                                        name: "预测趋势",
                                        xAxis: "2023",
                                    },
                                    {
                                        xAxis: "2030",
                                    },
                                ],
                            ],
                        },
                    },
                ],
            };

            option && myChart.setOption(option);
        },
        initecharts3() {
            var chartDom = document.getElementById("left2");
            var myChart = echarts.init(chartDom);
            var option;
            // 水球图，需引入第三方插件

            const series = [
                {
                    type: "liquidFill",
                    shape: "circle",
                    radius: "80%",
                    center: ["50%", "50%"],
                    data: [0.6, 0.5, 0.4],
                    // 球体配置
                    outline: {
                        borderDistance: 0,
                        itemStyle: {
                            borderWidth: 2,
                            borderColor: "#3dfff6",
                            shadowBlur: 20,
                            shadowColor: "#12786f",
                        },
                    },
                    color: [
                        "rgba(50, 255, 238, .6)",
                        "rgba(154, 255, 247, .6)",
                    ],
                    backgroundStyle: {
                        color: "transparent",
                    },

                    label: {
                        show: true,
                        textStyle: {
                            color: "#12786f",
                            insideColor: "#12786f",
                            fontSize: 28,
                        },
                    },
                    label: {
                        show: true,
                        textStyle: {
                            color: "#12786f",
                            insideColor: "#12786f",
                            fontSize: 40,
                        },

                        formatter: (params) => {
                            return `${((this.persent / 100) * 100).toFixed(
                                1
                            )}%\n {a|百分比}`;
                        },
                        rich: {
                            a: {
                                fontSize: 24,
                            },
                        },
                    },
                },
            ];

            option = { series, backgroundColor: null };

            option && myChart.setOption(option);
        },
        initecharts4() {
            var chartDom = document.getElementById("middletop");
            var myChart = echarts.init(chartDom);
            var option;
            let barData = [
                {
                    name: "现存量",
                    data: this.zhuzhuang,
                    color: [
                        "rgba(0,250,251,1)",
                        "rgba(1,185,255,0.5)",
                        "transparent",
                    ],
                },
                {
                    name: "标准量",
                    data: [
                        233694,
                        44756,
                        18388,
                        16557,
                        22611,
                        11815,
                        11636,
                        25661,
                        8580,
                        25823,
                        56723,
                        28916,
                        8200,
                        26484,
                        40946,
                        8905,
                        20211,
                    ],
                    color: [
                        "rgba(230,194,17,1)",
                        "rgba(129,67,16,0.5)",
                        "transparent",
                    ],
                },
            ];
            let seriesData = [];
            for (let i = 0; i < barData.length; i++) {
                seriesData.push({
                    name: barData[i].name,
                    type: "bar",
                    barWidth: 5,
                    barGap: "100%",
                    itemStyle: {
                        normal: {
                            barBorderRadius: 50, //圆角
                            color: new echarts.graphic.LinearGradient(
                                0,
                                0,
                                0,
                                1,
                                [
                                    {
                                        offset: 0,
                                        color: barData[i].color[0],
                                    },
                                    {
                                        offset: 0.5,
                                        color: barData[i].color[1],
                                    },
                                    {
                                        offset: 1,
                                        color: "transparent",
                                    },
                                ]
                            ),
                        },
                    },
                    data: barData[i].data,
                });
            }

            option = {
                backgroundColor: null,
                title: {
                    text: "个",
                    textStyle: {
                        fontSize: 12,
                        fontWeight: "normal",
                        color: "#c7dfe9", //标题颜色
                    },
                    top: "15%",
                    left: "5%",
                },
                tooltip: {
                    trigger: "axis",
                },
                grid: {
                    left: "5px",
                    right: "5px",
                    bottom: "5px",
                    top: "50px",
                    height: "80%",
                    containLabel: true,
                },
                calculable: true,
                xAxis: [
                    {
                        type: "category",
                        axisLabel: {
                            interval: 0, // 解决x轴名称过长问题
                            rotate: 60,
                            textStyle: {
                                color: "#c8d8e3",
                            },
                        },
                        axisLine: {
                            lineStyle: {
                                //y轴网格线设置
                                color: "#032e42",
                                width: 1,
                            },
                        },
                        data: this.cityarr,
                    },
                ],
                yAxis: [
                    {
                        type: "value",

                        axisLine: {
                            show: true,
                            lineStyle: {
                                //y轴网格线设置
                                color: "#032e42",
                                width: 1,
                            },
                        },
                        splitLine: {
                            //保留网格线
                            show: false,
                        },
                        axisLabel: {
                            show: true,
                            textStyle: {
                                color: "#c7dfe9", //字体颜色
                            },
                        },
                    },
                ],
                legend: {
                    icon: "roundRect",
                    orient: "horizontal",
                    itemWidth: 20,
                    itemHeight: 15,
                    textStyle: {
                        fontSize: 12, //字体大小
                        color: "#b9c8d4", //字体颜色
                    },
                    top: "20%", //距离右侧
                    right: "3%",
                },
                series: seriesData,
            };
            option && myChart.setOption(option);
        },
        btn() {
            // this.$router.replace('/Dierselection/Navagation2')
        },
    },
    mounted() {
        this.initecharts();
        this.initecharts1();
        this.initecharts2();
        this.initecharts3();
        this.initecharts4();
        $(() => {
            $("#btnsearch").on("click", () => {
                $.ajax({
                    type: "GET",
                    msg: "GET请求成功",
                    url: "http://43.138.26.129:8888/yuce/one",
                    data: {
                        address: this.value + "市辖区",
                    },
                    success: (res) => {
                        // console.log(res);
                        this.$message.success("已切换至" + this.value);
                        var resdata = res.data;
                        // console.log(resdata);

                        var message = res.message1;
                        this.message1 = message[0];
                        this.message2 = message[1];
                        // var sentences = this.message2.split(/\d+\./).filter(Boolean);
                        var sentences = this.message2
                            .split(/\d+\./)
                            .filter(Boolean)
                            .splice(1);
                        // var newsentences = sentences.splice(1)
                        // console.log(newsentences);
                        var datalist = [];
                        for (var i = 0; i < sentences.length; i++) {
                            var sentence = sentences[i].trim();
                            datalist.push(
                                `${i + 1}. ${sentence.padEnd(
                                    sentence.length + i * 2,
                                    " "
                                )}`
                            );
                        }
                        this.firstsentence0 = datalist[0];
                        this.firstsentence1 = datalist[1];
                        this.firstsentence2 = datalist[2];
                        // console.log(this.message1);
                        var valuename = this.value + "市辖区预测充电桩";

                        var yuce = resdata[valuename];
                        // console.log(yuce);
                        let newObj = {};
                        for (let key in yuce) {
                            if (key !== "city") {
                                newObj[key] = yuce[key];
                            }
                        }
                        // console.log(newObj);
                        // var arr= yuce.splice(0,1)
                        // console.log(yuce);
                        var arryuce = Object.values(newObj);
                        // console.log(arryuce);
                        this.yuce = arryuce[12];
                        var newarryuce = [];
                        for (var i = 0; i < arryuce.length; i++) {
                            newarryuce.push(arryuce[i] / 10000);
                        }

                        // console.log(arryuce);
                        let newArr = newarryuce.sort(function (a, b) {
                            return a - b;
                        });
                        this.left1arr = newArr;
                        // console.log(newArr);
                        var cityarr = resdata["已知充电桩"];
                        // console.log(cityarr);
                        var newcitydata = [];
                        for (var i = 0; i < cityarr.length; i++) {
                            newcitydata.push(cityarr[i][0]);
                        }
                        // console.log(newcitydata);
                        var dataarr = [];
                        var cityarr = [];
                        for (var i = 0; i < newcitydata.length; i++) {
                            dataarr.push(newcitydata[i].数量);
                            cityarr.push(newcitydata[i].地址);
                        }
                        this.cityarr = cityarr;
                        // console.log(cityarr);
                        // console.log(dataarr);
                        var dataarr1 = [];
                        for (i = 0; i < dataarr.length; i++) {
                            dataarr1[i] = dataarr[i] * 10;
                        }
                        this.zhuzhuang = dataarr1;
                        // console.log(dataarr1);

                        var arrall = [];
                        for (var i = 0; i < cityarr.length; i++) {
                            arrall.push({
                                name: cityarr[i],
                                value: dataarr1[i],
                            });
                        }
                        // console.log(arrall);
                        for (var i = 0; i < arrall.length; i++) {
                            if (this.value == arrall[i].name) {
                                this.shantunow = arrall[i].value;
                                // console.log(arrall[i]);
                            }
                        }
                        // console.log(this.yuce);
                        // console.log(this.shantunow);
                        var persent = (
                            (this.shantunow / this.yuce) *
                            100
                        ).toFixed(1);
                        this.persent = persent;
                        // console.log(persent);
                    },
                });
            });
        });
    },
    watch: {
        left1arr(val) {
            this.left1arr = val;
            this.initecharts2();
        },

        cityarr(val) {
            this.cityarr = val;
            this.initecharts4();
        },
        zhuzhuang(val) {
            this.zhuzhuang = val;
            this.initecharts4();
        },
        persent(val) {
            this.persent = val;
            this.initecharts3();
        },
    },
};
</script>

<style scoped>
.dv-border-box-7{
    bottom: -6%;
}
.el-icon-right {
    font-size: 60px;
    text-align: center;
    line-height: 50px;
    color: white;
}
.el-icon-right:hover {
    transition-property: color;
    transition-duration: 0.8s;
    transition-timing-function: ease-out;
    color: skyblue;
}
.btnbcack {
    display: block;
    width: 100px;
    height: 50px;
    margin-top: 0px;
    margin-left: 26%;
}
.btnbcack1 {
    display: block;
    text-decoration: none;
    width: 100px;
    height: 50px;
    margin-top: 0px;
    /* margin-left: 350px; */
    margin-left: 20%;
}
.el-icon-back {
    font-size: 60px;
    text-align: center;
    line-height: 50px;
    color: white;
}
.el-icon-back:hover {
    transition-property: color;
    transition-duration: 0.8s;
    transition-timing-function: ease-out;
    color: skyblue;
}
#left1 {
    /* padding-left: 50px; */
    margin-top: -20px;
}
/* .leftbox {
    height: 100%;
} */
.rltitle {
    margin-left: 90px;
}
.rltitle1 {
    margin-left: 25px;
}
.rltitle2 {
    margin-left: 100px;
}
.content {
    margin-top: 10px;
}
.pos {
    margin-top: 0px;
}
.flexbox {
    display: flex;
}
.test {
    width: 100%;
}
.model_box {
    display: flex;
    justify-content: space-between;
}
#routerbtn {
    font-size: 18px;
    color: rgb(9, 21, 42);
    font-weight: bold;
}
#routerbtn:hover {
    color: aliceblue;
}
.router {
    position: absolute;
    left: 250px;
    top: 100px;
}
body {
    padding: 0;
    margin: 0;
}
.bjc {
    position: fixed;
    left: 0;
    top: 0;
    width: 100%;
    height: 100vh;
    background: url(@/assets/imgs/R.jpg) ;
}
.el-menu-item {
    color: rgb(53, 116, 167);
}
.el-menu-item:hover {
    background-color: rgb(8, 94, 173);
}
.el-menu-vertical-demo {
    position: fixed;
    width: 15%;
    height: 100vh;
    background-color: #09152a;
}
.title1 {
    font-size: 18px;
    margin-left: 32px;
    color: rgb(182, 192, 200);
}

.top {
    margin-top: 50px;
}
.Title {
    font-size: 25px;
    font-weight: bloder;
    color: aliceblue;
}
.el-menu {
    border: none;
}
</style>