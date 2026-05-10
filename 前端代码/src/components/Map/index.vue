<template>
    <div class="amap-page-container">
        <el-amap
            ref="map"
            :zoom="zoom"
            :center="center"
            :pitch="pitch"
            view-mode="3D"
            @init="initMap"
            :show-label="false"
            class="amap-demo"
            mapStyle="amap://styles/darkblue"
        >
            <!-- 头部的容器 -->
            <div class="test">
                <div class="model_box">
                    <div style="flex:0 1 30%;">
                        <dv-decoration-10 style="height:5px; margin-top:45px;" />
                    </div>
                    <div style="flex:0 1 40%;">
                        <div class="flexbox">
                            <dv-decoration-8 style="height:50px;flex:0 1 30%;" />
                            <dv-decoration-11
                                style="height:80px;flex:0 1 40%;color:white;font-size:20px;"
                                class="tc box_middle"
                            >
                                <router-link
                                    to="/Souye"
                                    style="text-decoration:none;color:white;"
                                >数据展现</router-link>
                            </dv-decoration-11>
                            <dv-decoration-8 :reverse="true" style="height:50px;flex:0 1 30%;" />
                        </div>
                    </div>
                    <div style="flex:0 1 30%;">
                        <dv-decoration-10
                            style="height:5px;transform:rotateY(180deg);margin-top:45px;"
                        />
                    </div>
                </div>
            </div>
            <!-- 数字的容器 -->
            <div class="num">
                <dv-border-box-7
                    class="numbox"
                    style="width:350px;height:60px;padding-top:5px;"
                    :color="['MidnightBlue']"
                >
                    <div class="left" style="height:50px;float:left;">{{provincename}}</div>
                    <dv-digital-flop :config="confignum" style="height:50px;float:right;" />
                </dv-border-box-7>
                <dv-border-box-7
                    class="numbox"
                    style="width:350px;height:60px;padding-top:5px;"
                    :color="['MidnightBlue']"
                >
                    <div class="left" style="height:50px;float:left;">{{cityname}}</div>
                    <dv-digital-flop :config="confignum1" style="height:50px;float:right;" />
                </dv-border-box-7>
                <dv-border-box-7
                    class="numbox"
                    style="width:350px;height:60px;padding-top:5px;"
                    :color="['MidnightBlue']"
                >
                    <div class="left" style="height:50px;float:left;">{{areaname}}</div>
                    <dv-digital-flop :config="confignum2" style="height:50px;float:right;" />
                </dv-border-box-7>
            </div>
            <div class="search">
                <div class="searchfa">
                    <el-cascader
                        size="mini"
                        filterable
                        clearable
                        :options="options"
                        v-model="selectedoptions"
                        @change="handlechange"
                        style="width:250px;"
                    ></el-cascader>
                    <el-button type="primary" size="default" @click="submit" id="search">搜索</el-button>
                </div>
            </div>
            <div class="bottomdatashow">
                <!-- 底部容器 -->
                <div class="model_box">
                    <div style="flex:0 1 40%;">
                        <dv-scroll-board
                            :config="config"
                            style="width:500x;height:200px;margin-top:0px;"
                            id="carouselTable"
                        />
                    </div>
                </div>
            </div>
            <div class="rightbox">
                <dv-decoration-7
                    style="width:270px;height:30px;margin-left:50px;color:white;margin-top:0px;"
                >{{this.provincename+"各市区充电桩排行"}}</dv-decoration-7>
                <div style="flex:0 1 50%">
                    <dv-scroll-ranking-board :config="config1" style="width:400px;height:250px" />
                </div>
            </div>
            <div class="echartsline">
                <dv-decoration-7
                    style="width:350px;height:30px;margin-left:20px;color:white;"
                >{{this.provincename+this.cityname+this.areaname+"充电商统计"}}</dv-decoration-7>
                <div id="main" style="width: 100%;height:300px;" ref="main"></div>
            </div>
            <div class="lilmap">
                <div id="lilmap" style="width:500px;height:500px;" ref="lilmap"></div>
            </div>
            <el-amap-loca
                ref="loca"
                :amb-light="ambLight"
                :dir-light="dirLight"
                :point-light="pointLight"
            >
                <el-amap-loca-prism
                    :visible="visible"
                    :source-url="sourceUrl"
                    :layer-style="layerStyle"
                ></el-amap-loca-prism>
            </el-amap-loca>
        </el-amap>
    </div>
</template>

<style scoped>
.amap-logo {
    display: none;
    opacity: 0 !important;
}
.amap-copyright {
    opacity: 0;
}
::-webkit-scrollbar {
    width: 0px;
    height: 0px !important;
    display: none;
}
.amap-page-container {
    position: relative;
    top: 0;
}
/* .amap-logo {
    display: none !important;
    opacity: 0 !important;
} */
.amap-copyright {
    opacity: 0;
}
.test {
    position: absolute;
    left: 0;
    top: 0;
    width: 100%;
    height: 100px;
}
.search {
    position: absolute;
    left: 0;
    top: 15%;
}
.searchfa {
    height: 150px;
    margin-left: 10px;
    position: relative;
}
#search {
    position: absolute;
    top: 0;
    width: 99px;
    height: 30px;
    background-color: rgb(10, 68, 119);
    border: none;

    text-align: center;
}
.left {
    width: 200px;
    text-align: center;
    line-height: 50px;
    font-size: 25px;
    color: rgb(58, 220, 191);
    text-overflow: ellipsis;
    /* overflow: hidden; */
}
.lilmap {
    position: relative !important;
    left: 68% !important;
    bottom: 94% !important;
    width: 500px;
    height: 500px;
}
.num {
    position: absolute;
    top: 31%;
    left: 10px;
}
.numbox {
    /* display: inline-block; */
    width: 300px;
}
.bottomdatashow {
    position: absolute;
    bottom: 0;
    width: 107%;
    height: 200px;
    right: -35%;
}
.rightbox {
    position: absolute;
    right: 0;
    bottom: 20px;
    width: 400px;
    background-color: rgba(0, 0, 0, 0.1);
}
.echartsline {
    position: absolute;
    left: 0;
    bottom: 0;
    width: 390px;
    height: 300px;
    background-color: rgba(0, 0, 0, 0.1);
}
.model_box {
    display: flex;
    justify-content: space-between;
}
.flexbox {
    display: flex;
}
.tc {
    text-align: center;
}
.box_middle {
    align-self: center;
}
</style>


  <script>
// import axios from 'axios'  // 安装axios后引入
// Vue.prototype.$axios = axios  // 将axios挂载到原型上方便使用
// import { mapState, mapMutations } from 'vuex';
import { MapChart } from "echarts/charts";
echarts.use([MapChart]);
import * as echarts from "echarts";
import $ from "jquery";
import store from "@/store";
import { regionData, CodeToText, TextToCode } from "element-china-area-data";
var topConf = {
    上海市: "https://a.amap.com/Loca/static/loca-v2/demos/images/top-one.png",
    北京市: "https://a.amap.com/Loca/static/loca-v2/demos/images/top-two.png",
    广州市: "https://a.amap.com/Loca/static/loca-v2/demos/images/top-three.png",
};
export default {
    name: "Map",
    data() {
        return {
            form: {
                province: "",
                city: "",
                district: "",
            },
            district: null,
            options: regionData,
            selectedoptions: [],
            result: "",
            allresult: "",
            echartstitle: "",
            arr: [],
            // 运营方
            arry: [],
            // 底部的容器数据
            config: {
                header: ["名称", "运营商", "数量"],
                align: ["center", "center", "center"],
                data: [],
                waitTime: 1500,
                headerBGC: "#2F55AF",
                columnWidth: [300, 150, 150],
            },
            config1: {
                data: [],
            },
            // 有关数字容器
            provincename: "",
            cityname: "",
            areaname: "",
            sum: "",
            sumprovince: "",
            sumcity: "",
            confignum: {
                number: [100],
                content: "{nt}",
                textAlign: "right",
            },
            confignum1: {
                number: [100],
                content: "{nt}",
                textAlign: "right",
            },
            confignum2: {
                number: [100],
                content: "{nt}",
                textAlign: "right",
            },
            // provincedata
            lildata: [],
            mapjson: {},
            // 下面是有关地图的实例上面是各个组件的实例
            map: null,
            pl: null,
            zoom: 5,
            pitch: 55,
            center: [110.551439, 28.994065],
            visible: true,
            ambLight: {
                intensity: 0.7,
                color: "#7b7bff",
            },
            dirLight: {
                intensity: 0.8,
                color: "#fff",
                target: [0, 0, 0],
                position: [0, -1, 1],
            },
            pointLight: {
                color: "rgb(240,88,25)",
                position: [112.028276, 31.58538, 200000],
                intensity: 3,
                // 距离表示从光源到光照强度为 0 的位置，0 就是光不会消失。
                distance: 500000,
            },
            sourceUrl: "",
            layerStyle: {
                unit: "meter",
                sideNumber: 32,
                topColor: (index, f) => {
                    var n = f.properties["GDP"];
                    return n > 7000 ? "#E97091" : "#2852F1";
                },
                sideTopColor: (index, f) => {
                    var n = f.properties["GDP"];
                    return n > 7000 ? "#E97091" : "#2852F1";
                },
                sideBottomColor: "#002bb9",
                radius: 15000,
                height: (index, f) => {
                    var props = f.properties;
                    var height = Math.max(
                        100,
                        Math.sqrt(props["GDP"]) * 9000 - 50000
                    );
                    var conf = topConf[props["名称"]];
                    // top3 的数据，增加文字表达
                    if (conf) {
                        this.map.add(
                            new AMap.Marker({
                                anchor: "bottom-center",
                                position: [
                                    f.coordinates[0],
                                    f.coordinates[1],
                                    height,
                                ],
                                content:
                                    '<div style="margin-bottom: 10px; float: left; font-size: 14px;height: 57px; width: 180px; color:#fff; background: no-repeat url(' +
                                    conf +
                                    '); background-size: 100%;"><p style="margin: 7px 0 0 35px; height: 20px; line-height:20px;">' +
                                    props["名称"] +
                                    "人口 " +
                                    props["人口"] +
                                    "</p>" +
                                    '<p style="margin: 4px 0 0 35px; height: 20px; line-height:20px; color: #00a9ff; font-size: 13px;">' +
                                    props["GDP"] +
                                    " 个" +
                                    "</p></div>",
                            })
                        );
                    }
                    return height;
                    // return 60000 + n * 100;
                },
                // rotation: 360,
                altitude: 0,
            },
        };
    },
 

    created() {
        store.dispatch("getInfo").then(() => {
            this.form = {
                id: this.user.id,
                nickName: this.user.nickName,
                gender: this.user.gender,
                phone: this.user.phone,
                province: this.user.province,
                city: this.user.city,
                country: this.user.country,
            };
            if (this.form.country !== "") {
                this.selectedOptions = [
                    TextToCode[this.form.province].code,
                    TextToCode[this.form.province][this.form.city].code,
                    TextToCode[this.form.province][this.form.city][
                        this.form.country
                    ].code,
                ];
            } else {
                this.selectedOptions = [
                    TextToCode[this.form.province].code,
                    TextToCode[this.form.province][this.form.city].code,
                ];
            }
        });
    },
    methods: {
        submit() {
            if (this.selectedoptions[0] === "130000") {
                this.$axios.get("/130000.json").then((res) => {
                    // 获取public下的test.json文件数据
                    // console.log(res.data);
                    this.mapjson = res.data;
                });
            } else if (this.selectedoptions[0] === "140000") {
                this.$axios.get("/140000.json").then((res) => {
                    // 获取public下的test.json文件数据
                    // console.log(res.data);
                    this.mapjson = res.data;
                });
            } else if (this.selectedoptions[0] === "110000") {
                this.$axios.get("/110000.json").then((res) => {
                    // 获取public下的test.json文件数据
                    // console.log(res.data);
                    this.mapjson = res.data;
                });
            } else if (this.selectedoptions[0] === "210000") {
                this.$axios.get("/210000.json").then((res) => {
                    // 获取public下的test.json文件数据
                    // console.log(res.data);
                    this.mapjson = res.data;
                });
            } else if (this.selectedoptions[0] === "220000") {
                this.$axios.get("/220000.json").then((res) => {
                    // 获取public下的test.json文件数据
                    // console.log(res.data);
                    this.mapjson = res.data;
                });
            } else if (this.selectedoptions[0] === "230000") {
                this.$axios.get("/230000.json").then((res) => {
                    // 获取public下的test.json文件数据
                    // console.log(res.data);
                    this.mapjson = res.data;
                });
            } else if (this.selectedoptions[0] === "310000") {
                this.$axios.get("/310000.json").then((res) => {
                    // 获取public下的test.json文件数据
                    // console.log(res.data);
                    this.mapjson = res.data;
                });
            } else if (this.selectedoptions[0] === "120000") {
                this.$axios.get("/120000.json").then((res) => {
                    // 获取public下的test.json文件数据
                    // console.log(res.data);
                    this.mapjson = res.data;
                });
            } else if (this.selectedoptions[0] === "500000") {
                this.$axios.get("/500000.json").then((res) => {
                    // 获取public下的test.json文件数据
                    // console.log(res.data);
                    this.mapjson = res.data;
                });
            } else if (this.selectedoptions[0] === "320000") {
                this.$axios.get("/320000.json").then((res) => {
                    // 获取public下的test.json文件数据
                    // console.log(res.data);
                    this.mapjson = res.data;
                });
            } else if (this.selectedoptions[0] === "330000") {
                this.$axios.get("/330000.json").then((res) => {
                    // 获取public下的test.json文件数据
                    // console.log(res.data);
                    this.mapjson = res.data;
                });
            } else if (this.selectedoptions[0] === "340000") {
                this.$axios.get("/340000.json").then((res) => {
                    // 获取public下的test.json文件数据
                    // console.log(res.data);
                    this.mapjson = res.data;
                });
            } else if (this.selectedoptions[0] === "350000") {
                this.$axios.get("/350000.json").then((res) => {
                    // 获取public下的test.json文件数据
                    // console.log(res.data);
                    this.mapjson = res.data;
                });
            } else if (this.selectedoptions[0] === "360000") {
                this.$axios.get("/360000.json").then((res) => {
                    // 获取public下的test.json文件数据
                    // console.log(res.data);
                    this.mapjson = res.data;
                });
            } else if (this.selectedoptions[0] === "370000") {
                this.$axios.get("/370000.json").then((res) => {
                    // 获取public下的test.json文件数据
                    // console.log(res.data);
                    this.mapjson = res.data;
                });
            } else if (this.selectedoptions[0] === "410000") {
                this.$axios.get("/410000.json").then((res) => {
                    // 获取public下的test.json文件数据
                    // console.log(res.data);
                    this.mapjson = res.data;
                });
            } else if (this.selectedoptions[0] === "420000") {
                this.$axios.get("/420000.json").then((res) => {
                    // 获取public下的test.json文件数据
                    // console.log(res.data);
                    this.mapjson = res.data;
                });
            } else if (this.selectedoptions[0] === "430000") {
                this.$axios.get("/430000.json").then((res) => {
                    // 获取public下的test.json文件数据
                    // console.log(res.data);
                    this.mapjson = res.data;
                });
            } else if (this.selectedoptions[0] === "440000") {
                this.$axios.get("/440000.json").then((res) => {
                    // 获取public下的test.json文件数据
                    // console.log(res.data);
                    this.mapjson = res.data;
                });
            } else if (this.selectedoptions[0] === "460000") {
                this.$axios.get("/460000.json").then((res) => {
                    // 获取public下的test.json文件数据
                    // console.log(res.data);
                    this.mapjson = res.data;
                });
            } else if (this.selectedoptions[0] === "510000") {
                this.$axios.get("/510000.json").then((res) => {
                    // 获取public下的test.json文件数据
                    // console.log(res.data);
                    this.mapjson = res.data;
                });
            } else if (this.selectedoptions[0] === "520000") {
                this.$axios.get("/520000.json").then((res) => {
                    // 获取public下的test.json文件数据
                    // console.log(res.data);
                    this.mapjson = res.data;
                });
            } else if (this.selectedoptions[0] === "530000") {
                this.$axios.get("/530000.json").then((res) => {
                    // 获取public下的test.json文件数据
                    // console.log(res.data);
                    this.mapjson = res.data;
                });
            } else if (this.selectedoptions[0] === "610000") {
                this.$axios.get("/610000.json").then((res) => {
                    // 获取public下的test.json文件数据
                    // console.log(res.data);
                    this.mapjson = res.data;
                });
            } else if (this.selectedoptions[0] === "620000") {
                this.$axios.get("/620000.json").then((res) => {
                    // 获取public下的test.json文件数据
                    // console.log(res.data);
                    this.mapjson = res.data;
                });
            } else if (this.selectedoptions[0] === "630000") {
                this.$axios.get("/630000.json").then((res) => {
                    // 获取public下的test.json文件数据
                    // console.log(res.data);
                    this.mapjson = res.data;
                });
            } else if (this.selectedoptions[0] === "650000") {
                this.$axios.get("/650000.json").then((res) => {
                    // 获取public下的test.json文件数据
                    // console.log(res.data);
                    this.mapjson = res.data;
                });
            } else if (this.selectedoptions[0] === "710000") {
                this.$axios.get("/710000.json").then((res) => {
                    // 获取public下的test.json文件数据
                    // console.log(res.data);
                    this.mapjson = res.data;
                });
            }
        },
        handlechange(value) {
            var arr = [];
            arr[0] = CodeToText[value[0]];
            arr[1] = CodeToText[value[1]];
            arr[2] = CodeToText[value[2]];
            var arr1 = [];
            arr1[0] = arr[1];
            arr1[1] = arr[2];
            this.arr = arr1;
            var result = arr1.join("");
            this.result = result;
            var arr2 = [];
            arr2[0] = arr[0];
            arr2[1] = arr[1];
            arr2[2] = arr[2];
            this.allresult = arr2;
            this.provincename = this.allresult[0];
            this.cityname = this.allresult[1];
            this.areaname = this.allresult[2];
            // console.log(this.allresult);
            this.echartstitle =
                this.allresult[0] + this.allresult[1] + this.allresult[2];
            // console.log(this.echartstitle);
        },
        // 下面是有关地图初始化的方法
        toggleVisible() {
            // this.visible = !this.visible;
        },
        initMap(_map) {
            this.map = _map;
            // console.log(this.$refs.map.$$getInstance());
            this.$nextTick(() => {
                var loca = new Loca.Container({
                    map: this.map,
                });
                loca.ambLight = {
                    intensity: 0.7,
                    color: "#7b7bff",
                };
                loca.dirLight = {
                    intensity: 0.8,
                    color: "#fff",
                    target: [0, 0, 0],
                    position: [0, -1, 1],
                };
                loca.pointLight = {
                    color: "rgb(240,88,25)",
                    position: [112.028276, 31.58538, 2000000],
                    intensity: 3,
                    // 距离表示从光源到光照强度为 0 的位置，0 就是光不会消失。
                    distance: 5000000,
                };

                this.pl = new Loca.PrismLayer({
                    zIndex: 10,
                    opacity: 1,
                    visible: false,
                    hasSide: true,
                });
                var geo = new Loca.GeoJSONSource({
                    // url:
                    //     "https://a.amap.com/Loca/static/loca-v2/demos/mock_data/gdp.json",
                    data: {
                        type: "FeatureCollection",
                        features: [
                            {
                                type: "Feature",
                                geometry: {
                                    type: "Point",
                                    coordinates: [106.551556, 29.563009],
                                },
                                properties: {
                                    id: 1,
                                    名称: "重庆市",
                                    地址: "重庆市",
                                    adcode_n: -1,
                                    adcode_p: -1,
                                    adcode_c: -1,
                                    adcode_d: -1,
                                    point_status: 0,
                                    创建时间: "2021-01-27 14:45:12",
                                    修改时间: "2021-01-27 14:45:12",
                                    人口: 2884.62,
                                    GDP: 8956,
                                    人均GDP: 27367,
                                    人均折美元: 4043,
                                },
                            },
                            {
                                type: "Feature",
                                geometry: {
                                    type: "Point",
                                    coordinates: [121.473701, 31.230416],
                                },
                                properties: {
                                    id: 2,
                                    名称: "上海市",
                                    地址: "上海市",
                                    adcode_n: 100000,
                                    adcode_p: 310000,
                                    adcode_c: 310000,
                                    adcode_d: 310101,
                                    point_status: 0,
                                    创建时间: "2021-01-27 14:45:12",
                                    修改时间: "2021-01-27 14:45:12",
                                    人口: 2301.91,
                                    GDP: 12909,
                                    人均GDP: 73297,
                                    人均折美元: 10828,
                                },
                            },
                            {
                                type: "Feature",
                                geometry: {
                                    type: "Point",
                                    coordinates: [116.407526, 39.90403],
                                },
                                properties: {
                                    id: 3,
                                    名称: "北京市",
                                    地址: "北京市",
                                    adcode_n: 100000,
                                    adcode_p: 110000,
                                    adcode_c: 110000,
                                    adcode_d: 110101,
                                    point_status: 0,
                                    创建时间: "2021-01-27 14:45:12",
                                    修改时间: "2021-01-27 14:45:12",
                                    人口: 1961.24,
                                    GDP: 11563,
                                    人均GDP: 70251,
                                    人均折美元: 10378,
                                },
                            },
                            {
                                type: "Feature",
                                geometry: {
                                    type: "Point",
                                    coordinates: [104.066541, 30.572269],
                                },
                                properties: {
                                    id: 4,
                                    名称: "成都市",
                                    地址: "成都市",
                                    adcode_n: -1,
                                    adcode_p: -1,
                                    adcode_c: -1,
                                    adcode_d: -1,
                                    point_status: 0,
                                    创建时间: "2021-01-27 14:45:12",
                                    修改时间: "2021-01-27 14:45:12",
                                    人口: 1404.76,
                                    GDP: 6778,
                                    人均GDP: 39518,
                                    人均折美元: 5838,
                                },
                            },
                            {
                                type: "Feature",
                                geometry: {
                                    type: "Point",
                                    coordinates: [117.200983, 39.084158],
                                },
                                properties: {
                                    id: 5,
                                    名称: "天津市",
                                    地址: "天津市",
                                    adcode_n: -1,
                                    adcode_p: -1,
                                    adcode_c: -1,
                                    adcode_d: -1,
                                    point_status: 0,
                                    创建时间: "2021-01-27 14:45:12",
                                    修改时间: "2021-01-27 14:45:12",
                                    人口: 1293.82,
                                    GDP: 7356,
                                    人均GDP: 70402,
                                    人均折美元: 10400,
                                },
                            },
                            {
                                type: "Feature",
                                geometry: {
                                    type: "Point",
                                    coordinates: [113.264434, 23.129162],
                                },
                                properties: {
                                    id: 6,
                                    名称: "广州市",
                                    地址: "广州市",
                                    adcode_n: -1,
                                    adcode_p: -1,
                                    adcode_c: -1,
                                    adcode_d: -1,
                                    point_status: 0,
                                    创建时间: "2021-01-27 14:45:12",
                                    修改时间: "2021-01-27 14:45:12",
                                    人口: 1270.08,
                                    GDP: 11319,
                                    人均GDP: 83495,
                                    人均折美元: 12334,
                                },
                            },
                            {
                                type: "Feature",
                                geometry: {
                                    type: "Point",
                                    coordinates: [115.464806, 38.873891],
                                },
                                properties: {
                                    id: 7,
                                    名称: "保定市",
                                    地址: "保定市",
                                    adcode_n: -1,
                                    adcode_p: -1,
                                    adcode_c: -1,
                                    adcode_d: -1,
                                    point_status: 0,
                                    创建时间: "2021-01-27 14:45:12",
                                    修改时间: "2021-01-27 14:45:12",
                                    人口: 1119.44,
                                    GDP: 1736,
                                    人均GDP: 18315,
                                    人均折美元: 2706,
                                },
                            },
                            {
                                type: "Feature",
                                geometry: {
                                    type: "Point",
                                    coordinates: [126.534967, 45.803775],
                                },
                                properties: {
                                    id: 8,
                                    名称: "哈尔滨市",
                                    地址: "哈尔滨市",
                                    adcode_n: -1,
                                    adcode_p: -1,
                                    adcode_c: -1,
                                    adcode_d: -1,
                                    point_status: 0,
                                    创建时间: "2021-01-27 14:45:12",
                                    修改时间: "2021-01-27 14:45:12",
                                    人口: 1063.6,
                                    GDP: 113,
                                    人均GDP: 34467,
                                    人均折美元: 5092,
                                },
                            },
                            {
                                type: "Feature",
                                geometry: {
                                    type: "Point",
                                    coordinates: [120.585315, 31.298886],
                                },
                                properties: {
                                    id: 9,
                                    名称: "苏州市",
                                    地址: "苏州市",
                                    adcode_n: -1,
                                    adcode_p: -1,
                                    adcode_c: -1,
                                    adcode_d: -1,
                                    point_status: 0,
                                    创建时间: "2021-01-27 14:45:12",
                                    修改时间: "2021-01-27 14:45:12",
                                    人口: 1046.6,
                                    GDP: 4375,
                                    人均GDP: 85993,
                                    人均折美元: 12703,
                                },
                            },
                            {
                                type: "Feature",
                                geometry: {
                                    type: "Point",
                                    coordinates: [114.057868, 22.543099],
                                },
                                properties: {
                                    id: 10,
                                    名称: "深圳市",
                                    地址: "深圳市",
                                    adcode_n: -1,
                                    adcode_p: -1,
                                    adcode_c: -1,
                                    adcode_d: -1,
                                    point_status: 0,
                                    创建时间: "2021-01-27 14:45:12",
                                    修改时间: "2021-01-27 14:45:12",
                                    人口: 1035.79,
                                    GDP: 7789,
                                    人均GDP: 91822,
                                    人均折美元: 13564,
                                },
                            },
                            {
                                type: "Feature",
                                geometry: {
                                    type: "Point",
                                    coordinates: [112.528321, 32.990833],
                                },
                                properties: {
                                    id: 11,
                                    名称: "南阳市",
                                    地址: "南阳市",
                                    adcode_n: -1,
                                    adcode_p: -1,
                                    adcode_c: -1,
                                    adcode_d: -1,
                                    point_status: 0,
                                    创建时间: "2021-01-27 14:45:12",
                                    修改时间: "2021-01-27 14:45:12",
                                    人口: 1026.3,
                                    GDP: 98,
                                    人均GDP: 19057,
                                    人均折美元: 2815,
                                },
                            },
                            {
                                type: "Feature",
                                geometry: {
                                    type: "Point",
                                    coordinates: [114.514859, 38.042306],
                                },
                                properties: {
                                    id: 12,
                                    名称: "石家庄市",
                                    地址: "石家庄市",
                                    adcode_n: -1,
                                    adcode_p: -1,
                                    adcode_c: -1,
                                    adcode_d: -1,
                                    point_status: 0,
                                    创建时间: "2021-01-27 14:45:12",
                                    修改时间: "2021-01-27 14:45:12",
                                    人口: 1016.38,
                                    GDP: 2226,
                                    人均GDP: 33462,
                                    人均折美元: 4943,
                                },
                            },
                            {
                                type: "Feature",
                                geometry: {
                                    type: "Point",
                                    coordinates: [118.356448, 35.104672],
                                },
                                properties: {
                                    id: 13,
                                    名称: "临沂市",
                                    地址: "临沂市",
                                    adcode_n: -1,
                                    adcode_p: -1,
                                    adcode_c: -1,
                                    adcode_d: -1,
                                    point_status: 0,
                                    创建时间: "2021-01-27 14:45:12",
                                    修改时间: "2021-01-27 14:45:12",
                                    人口: 1003.94,
                                    GDP: 2258,
                                    人均GDP: 23906,
                                    人均折美元: 3531,
                                },
                            },
                            {
                                type: "Feature",
                                geometry: {
                                    type: "Point",
                                    coordinates: [114.305392, 30.593098],
                                },
                                properties: {
                                    id: 14,
                                    名称: "武汉市",
                                    地址: "武汉市",
                                    adcode_n: -1,
                                    adcode_p: -1,
                                    adcode_c: -1,
                                    adcode_d: -1,
                                    point_status: 0,
                                    创建时间: "2021-01-27 14:45:12",
                                    修改时间: "2021-01-27 14:45:12",
                                    人口: 978.54,
                                    GDP: 10716,
                                    人均GDP: 56367,
                                    人均折美元: 8327,
                                },
                            },
                            {
                                type: "Feature",
                                geometry: {
                                    type: "Point",
                                    coordinates: [114.538961, 36.625657],
                                },
                                properties: {
                                    id: 15,
                                    名称: "邯郸市",
                                    地址: "邯郸市",
                                    adcode_n: -1,
                                    adcode_p: -1,
                                    adcode_c: -1,
                                    adcode_d: -1,
                                    point_status: 0,
                                    创建时间: "2021-01-27 14:45:12",
                                    修改时间: "2021-01-27 14:45:12",
                                    人口: 917.47,
                                    GDP: 543,
                                    人均GDP: 25529,
                                    人均折美元: 3771,
                                },
                            },
                            {
                                type: "Feature",
                                geometry: {
                                    type: "Point",
                                    coordinates: [120.699366, 27.994267],
                                },
                                properties: {
                                    id: 16,
                                    名称: "温州市",
                                    地址: "温州市",
                                    adcode_n: -1,
                                    adcode_p: -1,
                                    adcode_c: -1,
                                    adcode_d: -1,
                                    point_status: 0,
                                    创建时间: "2021-01-27 14:45:12",
                                    修改时间: "2021-01-27 14:45:12",
                                    人口: 912.21,
                                    GDP: 2491,
                                    人均GDP: 32071,
                                    人均折美元: 4738,
                                },
                            },
                            {
                                type: "Feature",
                                geometry: {
                                    type: "Point",
                                    coordinates: [119.161755, 36.706774],
                                },
                                properties: {
                                    id: 17,
                                    名称: "潍坊市",
                                    地址: "潍坊市",
                                    adcode_n: -1,
                                    adcode_p: -1,
                                    adcode_c: -1,
                                    adcode_d: -1,
                                    point_status: 0,
                                    创建时间: "2021-01-27 14:45:12",
                                    修改时间: "2021-01-27 14:45:12",
                                    人口: 908.62,
                                    GDP: 2560,
                                    人均GDP: 34018,
                                    人均折美元: 5025,
                                },
                            },
                            {
                                type: "Feature",
                                geometry: {
                                    type: "Point",
                                    coordinates: [114.696951, 33.626149],
                                },
                                properties: {
                                    id: 18,
                                    名称: "周口市",
                                    地址: "周口市",
                                    adcode_n: -1,
                                    adcode_p: -1,
                                    adcode_c: -1,
                                    adcode_d: -1,
                                    point_status: 0,
                                    创建时间: "2021-01-27 14:45:12",
                                    修改时间: "2021-01-27 14:45:12",
                                    人口: 895.32,
                                    GDP: 159,
                                    人均GDP: 13715,
                                    人均折美元: 2026,
                                },
                            },
                            {
                                type: "Feature",
                                geometry: {
                                    type: "Point",
                                    coordinates: [120.382639, 36.067082],
                                },
                                properties: {
                                    id: 19,
                                    名称: "青岛市",
                                    地址: "青岛市",
                                    adcode_n: -1,
                                    adcode_p: -1,
                                    adcode_c: -1,
                                    adcode_d: -1,
                                    point_status: 0,
                                    创建时间: "2021-01-27 14:45:12",
                                    修改时间: "2021-01-27 14:45:12",
                                    人口: 871.51,
                                    GDP: 5498,
                                    人均GDP: 65016,
                                    人均折美元: 9604,
                                },
                            },
                            {
                                type: "Feature",
                                geometry: {
                                    type: "Point",
                                    coordinates: [120.15507, 30.274084],
                                },
                                properties: {
                                    id: 20,
                                    名称: "杭州市",
                                    地址: "杭州市",
                                    adcode_n: -1,
                                    adcode_p: -1,
                                    adcode_c: -1,
                                    adcode_d: -1,
                                    point_status: 0,
                                    创建时间: "2021-01-27 14:45:12",
                                    修改时间: "2021-01-27 14:45:12",
                                    人口: 870.04,
                                    GDP: 8187,
                                    人均GDP: 68340,
                                    人均折美元: 10095,
                                },
                            },
                            {
                                type: "Feature",
                                geometry: {
                                    type: "Point",
                                    coordinates: [113.625368, 34.746599],
                                },
                                properties: {
                                    id: 21,
                                    名称: "郑州市",
                                    地址: "郑州市",
                                    adcode_n: -1,
                                    adcode_p: -1,
                                    adcode_c: -1,
                                    adcode_d: -1,
                                    point_status: 0,
                                    创建时间: "2021-01-27 14:45:12",
                                    修改时间: "2021-01-27 14:45:12",
                                    人口: 862.65,
                                    GDP: 9044,
                                    人均GDP: 46369,
                                    人均折美元: 6850,
                                },
                            },
                            {
                                type: "Feature",
                                geometry: {
                                    type: "Point",
                                    coordinates: [117.284124, 34.205768],
                                },
                                properties: {
                                    id: 22,
                                    名称: "徐州市",
                                    地址: "徐州市",
                                    adcode_n: -1,
                                    adcode_p: -1,
                                    adcode_c: -1,
                                    adcode_d: -1,
                                    point_status: 0,
                                    创建时间: "2021-01-27 14:45:12",
                                    修改时间: "2021-01-27 14:45:12",
                                    人口: 858.05,
                                    GDP: 1175,
                                    人均GDP: 33412,
                                    人均折美元: 4936,
                                },
                            },
                            {
                                type: "Feature",
                                geometry: {
                                    type: "Point",
                                    coordinates: [108.940174, 34.341568],
                                },
                                properties: {
                                    id: 23,
                                    名称: "西安市",
                                    地址: "西安市",
                                    adcode_n: -1,
                                    adcode_p: -1,
                                    adcode_c: -1,
                                    adcode_d: -1,
                                    point_status: 0,
                                    创建时间: "2021-01-27 14:45:12",
                                    修改时间: "2021-01-27 14:45:12",
                                    人口: 846.78,
                                    GDP: 7043,
                                    人均GDP: 38280,
                                    人均折美元: 5655,
                                },
                            },
                            {
                                type: "Feature",
                                geometry: {
                                    type: "Point",
                                    coordinates: [114.935029, 25.831829],
                                },
                                properties: {
                                    id: 24,
                                    名称: "赣州市",
                                    地址: "赣州市",
                                    adcode_n: -1,
                                    adcode_p: -1,
                                    adcode_c: -1,
                                    adcode_d: -1,
                                    point_status: 0,
                                    创建时间: "2021-01-27 14:45:12",
                                    修改时间: "2021-01-27 14:45:12",
                                    人口: 836.84,
                                    GDP: 1649,
                                    人均GDP: 13377,
                                    人均折美元: 1976,
                                },
                            },
                            {
                                type: "Feature",
                                geometry: {
                                    type: "Point",
                                    coordinates: [115.480656, 35.23375],
                                },
                                properties: {
                                    id: 25,
                                    名称: "菏泽市",
                                    地址: "菏泽市",
                                    adcode_n: -1,
                                    adcode_p: -1,
                                    adcode_c: -1,
                                    adcode_d: -1,
                                    point_status: 0,
                                    创建时间: "2021-01-27 14:45:12",
                                    修改时间: "2021-01-27 14:45:12",
                                    人口: 828.78,
                                    GDP: 438,
                                    人均GDP: 13895,
                                    人均折美元: 2053,
                                },
                            },
                            {
                                type: "Feature",
                                geometry: {
                                    type: "Point",
                                    coordinates: [113.751765, 23.020536],
                                },
                                properties: {
                                    id: 26,
                                    名称: "东莞市",
                                    地址: "东莞市",
                                    adcode_n: -1,
                                    adcode_p: -1,
                                    adcode_c: -1,
                                    adcode_d: -1,
                                    point_status: 0,
                                    创建时间: "2021-01-27 14:45:12",
                                    修改时间: "2021-01-27 14:45:12",
                                    人口: 822.02,
                                    GDP: 4246,
                                    人均GDP: 51656,
                                    人均折美元: 7631,
                                },
                            },
                            {
                                type: "Feature",
                                geometry: {
                                    type: "Point",
                                    coordinates: [118.675675, 24.874132],
                                },
                                properties: {
                                    id: 27,
                                    名称: "泉州市",
                                    地址: "泉州市",
                                    adcode_n: -1,
                                    adcode_p: -1,
                                    adcode_c: -1,
                                    adcode_d: -1,
                                    point_status: 0,
                                    创建时间: "2021-01-27 14:45:12",
                                    修改时间: "2021-01-27 14:45:12",
                                    人口: 812.85,
                                    GDP: 4153,
                                    人均GDP: 43855,
                                    人均折美元: 6478,
                                },
                            },
                            {
                                type: "Feature",
                                geometry: {
                                    type: "Point",
                                    coordinates: [123.431474, 41.805698],
                                },
                                properties: {
                                    id: 28,
                                    名称: "沈阳市",
                                    地址: "沈阳市",
                                    adcode_n: -1,
                                    adcode_p: -1,
                                    adcode_c: -1,
                                    adcode_d: -1,
                                    point_status: 0,
                                    创建时间: "2021-01-27 14:45:12",
                                    修改时间: "2021-01-27 14:45:12",
                                    人口: 810.62,
                                    GDP: 651,
                                    人均GDP: 61891,
                                    人均折美元: 9143,
                                },
                            },
                            {
                                type: "Feature",
                                geometry: {
                                    type: "Point",
                                    coordinates: [116.587098, 35.414921],
                                },
                                properties: {
                                    id: 29,
                                    名称: "济宁市",
                                    地址: "济宁市",
                                    adcode_n: -1,
                                    adcode_p: -1,
                                    adcode_c: -1,
                                    adcode_d: -1,
                                    point_status: 0,
                                    创建时间: "2021-01-27 14:45:12",
                                    修改时间: "2021-01-27 14:45:12",
                                    人口: 808.19,
                                    GDP: 1261,
                                    人均GDP: 31463,
                                    人均折美元: 4648,
                                },
                            },
                            {
                                type: "Feature",
                                geometry: {
                                    type: "Point",
                                    coordinates: [118.796877, 32.060255],
                                },
                                properties: {
                                    id: 30,
                                    名称: "南京市",
                                    地址: "南京市",
                                    adcode_n: -1,
                                    adcode_p: -1,
                                    adcode_c: -1,
                                    adcode_d: -1,
                                    point_status: 0,
                                    创建时间: "2021-01-27 14:45:12",
                                    修改时间: "2021-01-27 14:45:12",
                                    人口: 800.47,
                                    GDP: 7019,
                                    人均GDP: 62593,
                                    人均折美元: 9246,
                                },
                            },
                            {
                                type: "Feature",
                                geometry: {
                                    type: "Point",
                                    coordinates: [125.323544, 43.817071],
                                },
                                properties: {
                                    id: 31,
                                    名称: "长春市",
                                    地址: "长春市",
                                    adcode_n: -1,
                                    adcode_p: -1,
                                    adcode_c: -1,
                                    adcode_d: -1,
                                    point_status: 0,
                                    创建时间: "2021-01-27 14:45:12",
                                    修改时间: "2021-01-27 14:45:12",
                                    人口: 767.71,
                                    GDP: 1197,
                                    人均GDP: 43893,
                                    人均折美元: 6484,
                                },
                            },
                            {
                                type: "Feature",
                                geometry: {
                                    type: "Point",
                                    coordinates: [121.550357, 29.874556],
                                },
                                properties: {
                                    id: 32,
                                    名称: "宁波市",
                                    地址: "宁波市",
                                    adcode_n: -1,
                                    adcode_p: -1,
                                    adcode_c: -1,
                                    adcode_d: -1,
                                    point_status: 0,
                                    创建时间: "2021-01-27 14:45:12",
                                    修改时间: "2021-01-27 14:45:12",
                                    人口: 760.57,
                                    GDP: 3017,
                                    人均GDP: 67394,
                                    人均折美元: 9956,
                                },
                            },
                            {
                                type: "Feature",
                                geometry: {
                                    type: "Point",
                                    coordinates: [115.814204, 32.890124],
                                },
                                properties: {
                                    id: 33,
                                    名称: "阜阳市",
                                    地址: "阜阳市",
                                    adcode_n: -1,
                                    adcode_p: -1,
                                    adcode_c: -1,
                                    adcode_d: -1,
                                    point_status: 0,
                                    创建时间: "2021-01-27 14:45:12",
                                    修改时间: "2021-01-27 14:45:12",
                                    人口: 760,
                                    GDP: 345,
                                    人均GDP: 9474,
                                    人均折美元: 1399,
                                },
                            },
                            {
                                type: "Feature",
                                geometry: {
                                    type: "Point",
                                    coordinates: [118.180193, 39.630867],
                                },
                                properties: {
                                    id: 34,
                                    名称: "唐山市",
                                    地址: "唐山市",
                                    adcode_n: -1,
                                    adcode_p: -1,
                                    adcode_c: -1,
                                    adcode_d: -1,
                                    point_status: 0,
                                    创建时间: "2021-01-27 14:45:12",
                                    修改时间: "2021-01-27 14:45:12",
                                    人口: 757.73,
                                    GDP: 1453,
                                    人均GDP: 58980,
                                    人均折美元: 8713,
                                },
                            },
                            {
                                type: "Feature",
                                geometry: {
                                    type: "Point",
                                    coordinates: [115.65637, 34.414172],
                                },
                                properties: {
                                    id: 35,
                                    名称: "商丘市",
                                    地址: "商丘市",
                                    adcode_n: -1,
                                    adcode_p: -1,
                                    adcode_c: -1,
                                    adcode_d: -1,
                                    point_status: 0,
                                    创建时间: "2021-01-27 14:45:12",
                                    修改时间: "2021-01-27 14:45:12",
                                    人口: 736.25,
                                    GDP: 159,
                                    人均GDP: 15484,
                                    人均折美元: 2287,
                                },
                            },
                            {
                                type: "Feature",
                                geometry: {
                                    type: "Point",
                                    coordinates: [120.894291, 31.980171],
                                },
                                properties: {
                                    id: 36,
                                    名称: "南通市",
                                    地址: "南通市",
                                    adcode_n: -1,
                                    adcode_p: -1,
                                    adcode_c: -1,
                                    adcode_d: -1,
                                    point_status: 0,
                                    创建时间: "2021-01-27 14:45:12",
                                    修改时间: "2021-01-27 14:45:12",
                                    人口: 728.28,
                                    GDP: 1645,
                                    人均GDP: 46931,
                                    人均折美元: 6933,
                                },
                            },
                            {
                                type: "Feature",
                                geometry: {
                                    type: "Point",
                                    coordinates: [120.163561, 33.347382],
                                },
                                properties: {
                                    id: 37,
                                    名称: "盐城市",
                                    地址: "盐城市",
                                    adcode_n: -1,
                                    adcode_p: -1,
                                    adcode_c: -1,
                                    adcode_d: -1,
                                    point_status: 0,
                                    创建时间: "2021-01-27 14:45:12",
                                    修改时间: "2021-01-27 14:45:12",
                                    人口: 726.02,
                                    GDP: 1832,
                                    人均GDP: 31215,
                                    人均折美元: 4611,
                                },
                            },
                            {
                                type: "Feature",
                                geometry: {
                                    type: "Point",
                                    coordinates: [114.022298, 33.011529],
                                },
                                properties: {
                                    id: 38,
                                    名称: "驻马店市",
                                    地址: "驻马店市",
                                    adcode_n: -1,
                                    adcode_p: -1,
                                    adcode_c: -1,
                                    adcode_d: -1,
                                    point_status: 0,
                                    创建时间: "2021-01-27 14:45:12",
                                    修改时间: "2021-01-27 14:45:12",
                                    人口: 723.07,
                                    GDP: 103,
                                    人均GDP: 14597,
                                    人均折美元: 2156,
                                },
                            },
                            {
                                type: "Feature",
                                geometry: {
                                    type: "Point",
                                    coordinates: [113.121416, 23.021548],
                                },
                                properties: {
                                    id: 39,
                                    名称: "佛山市",
                                    地址: "佛山市",
                                    adcode_n: -1,
                                    adcode_p: -1,
                                    adcode_c: -1,
                                    adcode_d: -1,
                                    point_status: 0,
                                    创建时间: "2021-01-27 14:45:12",
                                    修改时间: "2021-01-27 14:45:12",
                                    人口: 719.43,
                                    GDP: 2021,
                                    人均GDP: 78555,
                                    人均折美元: 11604,
                                },
                            },
                            {
                                type: "Feature",
                                geometry: {
                                    type: "Point",
                                    coordinates: [112.571997, 26.89323],
                                },
                                properties: {
                                    id: 40,
                                    名称: "衡阳市",
                                    地址: "衡阳市",
                                    adcode_n: -1,
                                    adcode_p: -1,
                                    adcode_c: -1,
                                    adcode_d: -1,
                                    point_status: 0,
                                    创建时间: "2021-01-27 14:45:12",
                                    修改时间: "2021-01-27 14:45:12",
                                    人口: 714.15,
                                    GDP: 505,
                                    人均GDP: 19889,
                                    人均折美元: 2938,
                                },
                            },
                            {
                                type: "Feature",
                                geometry: {
                                    type: "Point",
                                    coordinates: [116.838834, 38.304477],
                                },
                                properties: {
                                    id: 41,
                                    名称: "沧州市",
                                    地址: "沧州市",
                                    adcode_n: -1,
                                    adcode_p: -1,
                                    adcode_c: -1,
                                    adcode_d: -1,
                                    point_status: 0,
                                    创建时间: "2021-01-27 14:45:12",
                                    修改时间: "2021-01-27 14:45:12",
                                    人口: 713.41,
                                    GDP: 714,
                                    人均GDP: 30880,
                                    人均折美元: 4562,
                                },
                            },
                            {
                                type: "Feature",
                                geometry: {
                                    type: "Point",
                                    coordinates: [119.296494, 26.074507],
                                },
                                properties: {
                                    id: 42,
                                    名称: "福州市",
                                    地址: "福州市",
                                    adcode_n: -1,
                                    adcode_p: -1,
                                    adcode_c: -1,
                                    adcode_d: -1,
                                    point_status: 0,
                                    创建时间: "2021-01-27 14:45:12",
                                    修改时间: "2021-01-27 14:45:12",
                                    人口: 711.54,
                                    GDP: 3771,
                                    人均GDP: 43121,
                                    人均折美元: 6370,
                                },
                            },
                            {
                                type: "Feature",
                                geometry: {
                                    type: "Point",
                                    coordinates: [114.504844, 37.070589],
                                },
                                properties: {
                                    id: 43,
                                    名称: "邢台市",
                                    地址: "邢台市",
                                    adcode_n: -1,
                                    adcode_p: -1,
                                    adcode_c: -1,
                                    adcode_d: -1,
                                    point_status: 0,
                                    创建时间: "2021-01-27 14:45:12",
                                    修改时间: "2021-01-27 14:45:12",
                                    人口: 710.41,
                                    GDP: 528,
                                    人均GDP: 17041,
                                    人均折美元: 2517,
                                },
                            },
                            {
                                type: "Feature",
                                geometry: {
                                    type: "Point",
                                    coordinates: [111.467791, 27.238892],
                                },
                                properties: {
                                    id: 44,
                                    名称: "邵阳市",
                                    地址: "邵阳市",
                                    adcode_n: -1,
                                    adcode_p: -1,
                                    adcode_c: -1,
                                    adcode_d: -1,
                                    point_status: 0,
                                    创建时间: "2021-01-27 14:45:12",
                                    修改时间: "2021-01-27 14:45:12",
                                    人口: 707.18,
                                    GDP: 516,
                                    人均GDP: 10327,
                                    人均折美元: 1526,
                                },
                            },
                            {
                                type: "Feature",
                                geometry: {
                                    type: "Point",
                                    coordinates: [112.938814, 28.228209],
                                },
                                properties: {
                                    id: 45,
                                    名称: "长沙市",
                                    地址: "长沙市",
                                    adcode_n: -1,
                                    adcode_p: -1,
                                    adcode_c: -1,
                                    adcode_d: -1,
                                    point_status: 0,
                                    创建时间: "2021-01-27 14:45:12",
                                    修改时间: "2021-01-27 14:45:12",
                                    人口: 704.41,
                                    GDP: 7591,
                                    人均GDP: 64551,
                                    人均折美元: 9536,
                                },
                            },
                            {
                                type: "Feature",
                                geometry: {
                                    type: "Point",
                                    coordinates: [110.359377, 21.270707],
                                },
                                properties: {
                                    id: 46,
                                    名称: "湛江市",
                                    地址: "湛江市",
                                    adcode_n: -1,
                                    adcode_p: -1,
                                    adcode_c: -1,
                                    adcode_d: -1,
                                    point_status: 0,
                                    创建时间: "2021-01-27 14:45:12",
                                    修改时间: "2021-01-27 14:45:12",
                                    人口: 699.33,
                                    GDP: 223,
                                    人均GDP: 20059,
                                    人均折美元: 2963,
                                },
                            },
                            {
                                type: "Feature",
                                geometry: {
                                    type: "Point",
                                    coordinates: [121.447935, 37.463822],
                                },
                                properties: {
                                    id: 47,
                                    名称: "烟台市",
                                    地址: "烟台市",
                                    adcode_n: -1,
                                    adcode_p: -1,
                                    adcode_c: -1,
                                    adcode_d: -1,
                                    point_status: 0,
                                    创建时间: "2021-01-27 14:45:12",
                                    修改时间: "2021-01-27 14:45:12",
                                    人口: 696.82,
                                    GDP: 1390,
                                    人均GDP: 62548,
                                    人均折美元: 9240,
                                },
                            },
                            {
                                type: "Feature",
                                geometry: {
                                    type: "Point",
                                    coordinates: [117.119999, 36.651216],
                                },
                                properties: {
                                    id: 48,
                                    名称: "济南市",
                                    地址: "济南市",
                                    adcode_n: -1,
                                    adcode_p: -1,
                                    adcode_c: -1,
                                    adcode_d: -1,
                                    point_status: 0,
                                    创建时间: "2021-01-27 14:45:12",
                                    修改时间: "2021-01-27 14:45:12",
                                    人口: 681.4,
                                    GDP: 2545,
                                    人均GDP: 57394,
                                    人均折美元: 8478,
                                },
                            },
                            {
                                type: "Feature",
                                geometry: {
                                    type: "Point",
                                    coordinates: [121.614682, 38.914003],
                                },
                                properties: {
                                    id: 49,
                                    名称: "大连市",
                                    地址: "大连市",
                                    adcode_n: -1,
                                    adcode_p: -1,
                                    adcode_c: -1,
                                    adcode_d: -1,
                                    point_status: 0,
                                    创建时间: "2021-01-27 14:45:12",
                                    修改时间: "2021-01-27 14:45:12",
                                    人口: 669.04,
                                    GDP: 1413,
                                    人均GDP: 77097,
                                    人均折美元: 11389,
                                },
                            },
                            {
                                type: "Feature",
                                geometry: {
                                    type: "Point",
                                    coordinates: [108.366543, 22.817002],
                                },
                                properties: {
                                    id: 50,
                                    名称: "南宁市",
                                    地址: "南宁市",
                                    adcode_n: -1,
                                    adcode_p: -1,
                                    adcode_c: -1,
                                    adcode_d: -1,
                                    point_status: 0,
                                    创建时间: "2021-01-27 14:45:12",
                                    修改时间: "2021-01-27 14:45:12",
                                    人口: 666.16,
                                    GDP: 781,
                                    人均GDP: 27027,
                                    人均折美元: 3992,
                                },
                            },
                            {
                                type: "Feature",
                                geometry: {
                                    type: "Point",
                                    coordinates: [117.943433, 28.454863],
                                },
                                properties: {
                                    id: 51,
                                    名称: "上饶市",
                                    地址: "上饶市",
                                    adcode_n: -1,
                                    adcode_p: -1,
                                    adcode_c: -1,
                                    adcode_d: -1,
                                    point_status: 0,
                                    创建时间: "2021-01-27 14:45:12",
                                    修改时间: "2021-01-27 14:45:12",
                                    人口: 657.97,
                                    GDP: 901,
                                    人均GDP: 13694,
                                    人均折美元: 2023,
                                },
                            },
                            {
                                type: "Feature",
                                geometry: {
                                    type: "Point",
                                    coordinates: [112.45404, 34.619682],
                                },
                                properties: {
                                    id: 52,
                                    名称: "洛阳市",
                                    地址: "洛阳市",
                                    adcode_n: -1,
                                    adcode_p: -1,
                                    adcode_c: -1,
                                    adcode_d: -1,
                                    point_status: 0,
                                    创建时间: "2021-01-27 14:45:12",
                                    修改时间: "2021-01-27 14:45:12",
                                    人口: 654.95,
                                    GDP: 348,
                                    人均GDP: 35441,
                                    人均折美元: 5235,
                                },
                            },
                            {
                                type: "Feature",
                                geometry: {
                                    type: "Point",
                                    coordinates: [105.285011, 27.301693],
                                },
                                properties: {
                                    id: 53,
                                    名称: "毕节地区",
                                    地址: "毕节地区",
                                    adcode_n: -1,
                                    adcode_p: -1,
                                    adcode_c: -1,
                                    adcode_d: -1,
                                    point_status: 0,
                                    创建时间: "2021-01-27 14:45:12",
                                    修改时间: "2021-01-27 14:45:12",
                                    人口: 653.64,
                                    GDP: 300,
                                    人均GDP: 9192,
                                    人均折美元: 1358,
                                },
                            },
                            {
                                type: "Feature",
                                geometry: {
                                    type: "Point",
                                    coordinates: [102.832891, 24.880095],
                                },
                                properties: {
                                    id: 54,
                                    名称: "昆明市",
                                    地址: "昆明市",
                                    adcode_n: -1,
                                    adcode_p: -1,
                                    adcode_c: -1,
                                    adcode_d: -1,
                                    point_status: 0,
                                    创建时间: "2021-01-27 14:45:12",
                                    修改时间: "2021-01-27 14:45:12",
                                    人口: 643.2,
                                    GDP: 4745,
                                    人均GDP: 32966,
                                    人均折美元: 4870,
                                },
                            },
                            {
                                type: "Feature",
                                geometry: {
                                    type: "Point",
                                    coordinates: [120.31191, 31.491169],
                                },
                                properties: {
                                    id: 55,
                                    名称: "无锡市",
                                    地址: "无锡市",
                                    adcode_n: -1,
                                    adcode_p: -1,
                                    adcode_c: -1,
                                    adcode_d: -1,
                                    point_status: 0,
                                    创建时间: "2021-01-27 14:45:12",
                                    修改时间: "2021-01-27 14:45:12",
                                    人口: 637.26,
                                    GDP: 1043,
                                    人均GDP: 90355,
                                    人均折美元: 13347,
                                },
                            },
                            {
                                type: "Feature",
                                geometry: {
                                    type: "Point",
                                    coordinates: [106.110698, 30.837793],
                                },
                                properties: {
                                    id: 56,
                                    名称: "南充市",
                                    地址: "南充市",
                                    adcode_n: -1,
                                    adcode_p: -1,
                                    adcode_c: -1,
                                    adcode_d: -1,
                                    point_status: 0,
                                    创建时间: "2021-01-27 14:45:12",
                                    修改时间: "2021-01-27 14:45:12",
                                    人口: 627.86,
                                    GDP: 196,
                                    人均GDP: 13185,
                                    人均折美元: 1948,
                                },
                            },
                            {
                                type: "Feature",
                                geometry: {
                                    type: "Point",
                                    coordinates: [114.872316, 30.453905],
                                },
                                properties: {
                                    id: 57,
                                    名称: "黄冈市",
                                    地址: "黄冈市",
                                    adcode_n: -1,
                                    adcode_p: -1,
                                    adcode_c: -1,
                                    adcode_d: -1,
                                    point_status: 0,
                                    创建时间: "2021-01-27 14:45:12",
                                    修改时间: "2021-01-27 14:45:12",
                                    人口: 616.21,
                                    GDP: 521,
                                    人均GDP: 13994,
                                    人均折美元: 2067,
                                },
                            },
                            {
                                type: "Feature",
                                geometry: {
                                    type: "Point",
                                    coordinates: [106.927389, 27.725654],
                                },
                                properties: {
                                    id: 58,
                                    名称: "遵义市",
                                    地址: "遵义市",
                                    adcode_n: -1,
                                    adcode_p: -1,
                                    adcode_c: -1,
                                    adcode_d: -1,
                                    point_status: 0,
                                    创建时间: "2021-01-27 14:45:12",
                                    修改时间: "2021-01-27 14:45:12",
                                    人口: 612.7,
                                    GDP: 300,
                                    人均GDP: 14832,
                                    人均折美元: 2191,
                                },
                            },
                            {
                                type: "Feature",
                                geometry: {
                                    type: "Point",
                                    coordinates: [114.091023, 32.146983],
                                },
                                properties: {
                                    id: 59,
                                    名称: "信阳市",
                                    地址: "信阳市",
                                    adcode_n: -1,
                                    adcode_p: -1,
                                    adcode_c: -1,
                                    adcode_d: -1,
                                    point_status: 0,
                                    创建时间: "2021-01-27 14:45:12",
                                    修改时间: "2021-01-27 14:45:12",
                                    人口: 610.87,
                                    GDP: 103,
                                    人均GDP: 17880,
                                    人均折美元: 2641,
                                },
                            },
                            {
                                type: "Feature",
                                geometry: {
                                    type: "Point",
                                    coordinates: [121.420757, 28.656386],
                                },
                                properties: {
                                    id: 60,
                                    名称: "台州市",
                                    地址: "台州市",
                                    adcode_n: -1,
                                    adcode_p: -1,
                                    adcode_c: -1,
                                    adcode_d: -1,
                                    point_status: 0,
                                    创建时间: "2021-01-27 14:45:12",
                                    修改时间: "2021-01-27 14:45:12",
                                    人口: 596.88,
                                    GDP: 716,
                                    人均GDP: 40462,
                                    人均折美元: 5977,
                                },
                            },
                            {
                                type: "Feature",
                                geometry: {
                                    type: "Point",
                                    coordinates: [116.372831, 23.549993],
                                },
                                properties: {
                                    id: 61,
                                    名称: "揭阳市",
                                    地址: "揭阳市",
                                    adcode_n: -1,
                                    adcode_p: -1,
                                    adcode_c: -1,
                                    adcode_d: -1,
                                    point_status: 0,
                                    创建时间: "2021-01-27 14:45:12",
                                    修改时间: "2021-01-27 14:45:12",
                                    人口: 587.7,
                                    GDP: 58,
                                    人均GDP: 17168,
                                    人均折美元: 2536,
                                },
                            },
                            {
                                type: "Feature",
                                geometry: {
                                    type: "Point",
                                    coordinates: [103.796167, 25.489999],
                                },
                                properties: {
                                    id: 62,
                                    名称: "曲靖市",
                                    地址: "曲靖市",
                                    adcode_n: -1,
                                    adcode_p: -1,
                                    adcode_c: -1,
                                    adcode_d: -1,
                                    point_status: 0,
                                    创建时间: "2021-01-27 14:45:12",
                                    修改时间: "2021-01-27 14:45:12",
                                    人口: 585.5,
                                    GDP: 266,
                                    人均GDP: 17173,
                                    人均折美元: 2537,
                                },
                            },
                            {
                                type: "Feature",
                                geometry: {
                                    type: "Point",
                                    coordinates: [110.925456, 21.662999],
                                },
                                properties: {
                                    id: 63,
                                    名称: "茂名市",
                                    地址: "茂名市",
                                    adcode_n: -1,
                                    adcode_p: -1,
                                    adcode_c: -1,
                                    adcode_d: -1,
                                    point_status: 0,
                                    创建时间: "2021-01-27 14:45:12",
                                    修改时间: "2021-01-27 14:45:12",
                                    人口: 581.78,
                                    GDP: 253,
                                    人均GDP: 25647,
                                    人均折美元: 3789,
                                },
                            },
                            {
                                type: "Feature",
                                geometry: {
                                    type: "Point",
                                    coordinates: [115.985371, 36.456703],
                                },
                                properties: {
                                    id: 64,
                                    名称: "聊城市",
                                    地址: "聊城市",
                                    adcode_n: -1,
                                    adcode_p: -1,
                                    adcode_c: -1,
                                    adcode_d: -1,
                                    point_status: 0,
                                    创建时间: "2021-01-27 14:45:12",
                                    修改时间: "2021-01-27 14:45:12",
                                    人口: 578.99,
                                    GDP: 351,
                                    人均GDP: 27747,
                                    人均折美元: 4099,
                                },
                            },
                            {
                                type: "Feature",
                                geometry: {
                                    type: "Point",
                                    coordinates: [111.698497, 29.031673],
                                },
                                properties: {
                                    id: 65,
                                    名称: "常德市",
                                    地址: "常德市",
                                    adcode_n: -1,
                                    adcode_p: -1,
                                    adcode_c: -1,
                                    adcode_d: -1,
                                    point_status: 0,
                                    创建时间: "2021-01-27 14:45:12",
                                    修改时间: "2021-01-27 14:45:12",
                                    人口: 571.72,
                                    GDP: 469,
                                    人均GDP: 26090,
                                    人均折美元: 3854,
                                },
                            },
                            {
                                type: "Feature",
                                geometry: {
                                    type: "Point",
                                    coordinates: [113.9268, 35.303004],
                                },
                                properties: {
                                    id: 66,
                                    名称: "新乡市",
                                    地址: "新乡市",
                                    adcode_n: -1,
                                    adcode_p: -1,
                                    adcode_c: -1,
                                    adcode_d: -1,
                                    point_status: 0,
                                    创建时间: "2021-01-27 14:45:12",
                                    修改时间: "2021-01-27 14:45:12",
                                    人口: 570.78,
                                    GDP: 527,
                                    人均GDP: 20699,
                                    人均折美元: 3058,
                                },
                            },
                            {
                                type: "Feature",
                                geometry: {
                                    type: "Point",
                                    coordinates: [117.227239, 31.820586],
                                },
                                properties: {
                                    id: 67,
                                    名称: "合肥市",
                                    地址: "合肥市",
                                    adcode_n: -1,
                                    adcode_p: -1,
                                    adcode_c: -1,
                                    adcode_d: -1,
                                    point_status: 0,
                                    创建时间: "2021-01-27 14:45:12",
                                    修改时间: "2021-01-27 14:45:12",
                                    人口: 570.2,
                                    GDP: 2272,
                                    人均GDP: 47396,
                                    人均折美元: 7001,
                                },
                            },
                            {
                                type: "Feature",
                                geometry: {
                                    type: "Point",
                                    coordinates: [112.239741, 30.335165],
                                },
                                properties: {
                                    id: 68,
                                    名称: "荆州市",
                                    地址: "荆州市",
                                    adcode_n: -1,
                                    adcode_p: -1,
                                    adcode_c: -1,
                                    adcode_d: -1,
                                    point_status: 0,
                                    创建时间: "2021-01-27 14:45:12",
                                    修改时间: "2021-01-27 14:45:12",
                                    人口: 569.17,
                                    GDP: 164,
                                    人均GDP: 14707,
                                    人均折美元: 2173,
                                },
                            },
                            {
                                type: "Feature",
                                geometry: {
                                    type: "Point",
                                    coordinates: [116.521854, 31.733699],
                                },
                                properties: {
                                    id: 69,
                                    名称: "六安市",
                                    地址: "六安市",
                                    adcode_n: -1,
                                    adcode_p: -1,
                                    adcode_c: -1,
                                    adcode_d: -1,
                                    point_status: 0,
                                    创建时间: "2021-01-27 14:45:12",
                                    修改时间: "2021-01-27 14:45:12",
                                    人口: 561.2,
                                    GDP: 351,
                                    人均GDP: 12047,
                                    人均折美元: 1780,
                                },
                            },
                            {
                                type: "Feature",
                                geometry: {
                                    type: "Point",
                                    coordinates: [116.357464, 37.434092],
                                },
                                properties: {
                                    id: 70,
                                    名称: "德州市",
                                    地址: "德州市",
                                    adcode_n: -1,
                                    adcode_p: -1,
                                    adcode_c: -1,
                                    adcode_d: -1,
                                    point_status: 0,
                                    创建时间: "2021-01-27 14:45:12",
                                    修改时间: "2021-01-27 14:45:12",
                                    人口: 556.82,
                                    GDP: 486,
                                    人均GDP: 29773,
                                    人均折美元: 4398,
                                },
                            },
                            {
                                type: "Feature",
                                geometry: {
                                    type: "Point",
                                    coordinates: [112.122414, 32.008986],
                                },
                                properties: {
                                    id: 71,
                                    名称: "襄阳市",
                                    地址: "襄阳市",
                                    adcode_n: -1,
                                    adcode_p: -1,
                                    adcode_c: -1,
                                    adcode_d: -1,
                                    point_status: 0,
                                    创建时间: "2021-01-27 14:45:12",
                                    修改时间: "2021-01-27 14:45:12",
                                    人口: 550.03,
                                    GDP: 952,
                                    人均GDP: 27968,
                                    人均折美元: 4131,
                                },
                            },
                            {
                                type: "Feature",
                                geometry: {
                                    type: "Point",
                                    coordinates: [117.087614, 36.200252],
                                },
                                properties: {
                                    id: 72,
                                    名称: "泰安市",
                                    地址: "泰安市",
                                    adcode_n: -1,
                                    adcode_p: -1,
                                    adcode_c: -1,
                                    adcode_d: -1,
                                    point_status: 0,
                                    创建时间: "2021-01-27 14:45:12",
                                    修改时间: "2021-01-27 14:45:12",
                                    人口: 549.42,
                                    GDP: 752,
                                    人均GDP: 37343,
                                    人均折美元: 5516,
                                },
                            },
                            {
                                type: "Feature",
                                geometry: {
                                    type: "Point",
                                    coordinates: [110.164756, 22.636379],
                                },
                                properties: {
                                    id: 73,
                                    名称: "玉林市",
                                    地址: "玉林市",
                                    adcode_n: -1,
                                    adcode_p: -1,
                                    adcode_c: -1,
                                    adcode_d: -1,
                                    point_status: 0,
                                    创建时间: "2021-01-27 14:45:12",
                                    修改时间: "2021-01-27 14:45:12",
                                    人口: 548.74,
                                    GDP: 187,
                                    人均GDP: 15232,
                                    人均折美元: 2250,
                                },
                            },
                            {
                                type: "Feature",
                                geometry: {
                                    type: "Point",
                                    coordinates: [113.128958, 29.357104],
                                },
                                properties: {
                                    id: 74,
                                    名称: "岳阳市",
                                    地址: "岳阳市",
                                    adcode_n: -1,
                                    adcode_p: -1,
                                    adcode_c: -1,
                                    adcode_d: -1,
                                    point_status: 0,
                                    创建时间: "2021-01-27 14:45:12",
                                    修改时间: "2021-01-27 14:45:12",
                                    人口: 547.79,
                                    GDP: 1539.36,
                                    人均GDP: 441,
                                    人均折美元: 4151,
                                },
                            },
                            {
                                type: "Feature",
                                geometry: {
                                    type: "Point",
                                    coordinates: [107.468023, 31.209571],
                                },
                                properties: {
                                    id: 75,
                                    名称: "达州市",
                                    地址: "达州市",
                                    adcode_n: -1,
                                    adcode_p: -1,
                                    adcode_c: -1,
                                    adcode_d: -1,
                                    point_status: 0,
                                    创建时间: "2021-01-27 14:45:12",
                                    修改时间: "2021-01-27 14:45:12",
                                    人口: 546.81,
                                    GDP: 82,
                                    人均GDP: 14981,
                                    人均折美元: 2213,
                                },
                            },
                            {
                                type: "Feature",
                                geometry: {
                                    type: "Point",
                                    coordinates: [114.416778, 27.815619],
                                },
                                properties: {
                                    id: 76,
                                    名称: "宜春市",
                                    地址: "宜春市",
                                    adcode_n: -1,
                                    adcode_p: -1,
                                    adcode_c: -1,
                                    adcode_d: -1,
                                    point_status: 0,
                                    创建时间: "2021-01-27 14:45:12",
                                    修改时间: "2021-01-27 14:45:12",
                                    人口: 541.96,
                                    GDP: 300,
                                    人均GDP: 16053,
                                    人均折美元: 2371,
                                },
                            },
                            {
                                type: "Feature",
                                geometry: {
                                    type: "Point",
                                    coordinates: [126.968887, 46.653845],
                                },
                                properties: {
                                    id: 77,
                                    名称: "绥化市",
                                    地址: "绥化市",
                                    adcode_n: -1,
                                    adcode_p: -1,
                                    adcode_c: -1,
                                    adcode_d: -1,
                                    point_status: 0,
                                    创建时间: "2021-01-27 14:45:12",
                                    修改时间: "2021-01-27 14:45:12",
                                    人口: 541.64,
                                    GDP: 107,
                                    人均GDP: 13540,
                                    人均折美元: 2000,
                                },
                            },
                            {
                                type: "Feature",
                                geometry: {
                                    type: "Point",
                                    coordinates: [116.681972, 23.354091],
                                },
                                properties: {
                                    id: 78,
                                    名称: "汕头市",
                                    地址: "汕头市",
                                    adcode_n: -1,
                                    adcode_p: -1,
                                    adcode_c: -1,
                                    adcode_d: -1,
                                    point_status: 0,
                                    创建时间: "2021-01-27 14:45:12",
                                    修改时间: "2021-01-27 14:45:12",
                                    人口: 539.1,
                                    GDP: 107,
                                    人均GDP: 22319,
                                    人均折美元: 3297,
                                },
                            },
                            {
                                type: "Feature",
                                geometry: {
                                    type: "Point",
                                    coordinates: [123.918186, 47.354348],
                                },
                                properties: {
                                    id: 79,
                                    名称: "齐齐哈尔市",
                                    地址: "齐齐哈尔市",
                                    adcode_n: -1,
                                    adcode_p: -1,
                                    adcode_c: -1,
                                    adcode_d: -1,
                                    point_status: 0,
                                    创建时间: "2021-01-27 14:45:12",
                                    修改时间: "2021-01-27 14:45:12",
                                    人口: 536.7,
                                    GDP: 11,
                                    人均GDP: 15886,
                                    人均折美元: 2347,
                                },
                            },
                            {
                                type: "Feature",
                                geometry: {
                                    type: "Point",
                                    coordinates: [119.647444, 29.079059],
                                },
                                properties: {
                                    id: 80,
                                    名称: "金华市",
                                    地址: "金华市",
                                    adcode_n: -1,
                                    adcode_p: -1,
                                    adcode_c: -1,
                                    adcode_d: -1,
                                    point_status: 0,
                                    创建时间: "2021-01-27 14:45:12",
                                    修改时间: "2021-01-27 14:45:12",
                                    人口: 536.16,
                                    GDP: 2026,
                                    人均GDP: 39069,
                                    人均折美元: 5771,
                                },
                            },
                            {
                                type: "Feature",
                                geometry: {
                                    type: "Point",
                                    coordinates: [116.964356, 33.646373],
                                },
                                properties: {
                                    id: 81,
                                    名称: "宿州市",
                                    地址: "宿州市",
                                    adcode_n: -1,
                                    adcode_p: -1,
                                    adcode_c: -1,
                                    adcode_d: -1,
                                    point_status: 0,
                                    创建时间: "2021-01-27 14:45:12",
                                    修改时间: "2021-01-27 14:45:12",
                                    人口: 535.3,
                                    GDP: 369,
                                    人均GDP: 12148,
                                    人均折美元: 1795,
                                },
                            },
                            {
                                type: "Feature",
                                geometry: {
                                    type: "Point",
                                    coordinates: [117.063754, 30.543494],
                                },
                                properties: {
                                    id: 82,
                                    名称: "安庆市",
                                    地址: "安庆市",
                                    adcode_n: -1,
                                    adcode_p: -1,
                                    adcode_c: -1,
                                    adcode_d: -1,
                                    point_status: 0,
                                    创建时间: "2021-01-27 14:45:12",
                                    修改时间: "2021-01-27 14:45:12",
                                    人口: 531.1,
                                    GDP: 999,
                                    人均GDP: 18605,
                                    人均折美元: 2748,
                                },
                            },
                            {
                                type: "Feature",
                                geometry: {
                                    type: "Point",
                                    coordinates: [109.509786, 34.499995],
                                },
                                properties: {
                                    id: 83,
                                    名称: "渭南市",
                                    地址: "渭南市",
                                    adcode_n: -1,
                                    adcode_p: -1,
                                    adcode_c: -1,
                                    adcode_d: -1,
                                    point_status: 0,
                                    创建时间: "2021-01-27 14:45:12",
                                    修改时间: "2021-01-27 14:45:12",
                                    人口: 528.61,
                                    GDP: 200,
                                    人均GDP: 15161,
                                    人均折美元: 2240,
                                },
                            },
                            {
                                type: "Feature",
                                geometry: {
                                    type: "Point",
                                    coordinates: [103.717465, 27.338257],
                                },
                                properties: {
                                    id: 84,
                                    名称: "昭通市",
                                    地址: "昭通市",
                                    adcode_n: -1,
                                    adcode_p: -1,
                                    adcode_c: -1,
                                    adcode_d: -1,
                                    point_status: 0,
                                    创建时间: "2021-01-27 14:45:12",
                                    修改时间: "2021-01-27 14:45:12",
                                    人口: 521.3,
                                    GDP: 4,
                                    人均GDP: 7282,
                                    人均折美元: 1076,
                                },
                            },
                            {
                                type: "Feature",
                                geometry: {
                                    type: "Point",
                                    coordinates: [111.613445, 26.420394],
                                },
                                properties: {
                                    id: 85,
                                    名称: "永州市",
                                    地址: "永州市",
                                    adcode_n: -1,
                                    adcode_p: -1,
                                    adcode_c: -1,
                                    adcode_d: -1,
                                    point_status: 0,
                                    创建时间: "2021-01-27 14:45:12",
                                    修改时间: "2021-01-27 14:45:12",
                                    人口: 518.02,
                                    GDP: 272,
                                    人均GDP: 14806,
                                    人均折美元: 2187,
                                },
                            },
                            {
                                type: "Feature",
                                geometry: {
                                    type: "Point",
                                    coordinates: [114.392392, 36.097577],
                                },
                                properties: {
                                    id: 86,
                                    名称: "安阳市",
                                    地址: "安阳市",
                                    adcode_n: -1,
                                    adcode_p: -1,
                                    adcode_c: -1,
                                    adcode_d: -1,
                                    point_status: 0,
                                    创建时间: "2021-01-27 14:45:12",
                                    修改时间: "2021-01-27 14:45:12",
                                    人口: 517.28,
                                    GDP: 402,
                                    人均GDP: 25350,
                                    人均折美元: 3745,
                                },
                            },
                            {
                                type: "Feature",
                                geometry: {
                                    type: "Point",
                                    coordinates: [111.007528, 35.026412],
                                },
                                properties: {
                                    id: 87,
                                    名称: "运城市",
                                    地址: "运城市",
                                    adcode_n: -1,
                                    adcode_p: -1,
                                    adcode_c: -1,
                                    adcode_d: -1,
                                    point_status: 0,
                                    创建时间: "2021-01-27 14:45:12",
                                    修改时间: "2021-01-27 14:45:12",
                                    人口: 513.48,
                                    GDP: 519,
                                    人均GDP: 16106,
                                    人均折美元: 2379,
                                },
                            },
                            {
                                type: "Feature",
                                geometry: {
                                    type: "Point",
                                    coordinates: [115.858197, 28.682892],
                                },
                                properties: {
                                    id: 88,
                                    名称: "南昌市",
                                    地址: "南昌市",
                                    adcode_n: -1,
                                    adcode_p: -1,
                                    adcode_c: -1,
                                    adcode_d: -1,
                                    point_status: 0,
                                    创建时间: "2021-01-27 14:45:12",
                                    修改时间: "2021-01-27 14:45:12",
                                    人口: 504.26,
                                    GDP: 1519,
                                    人均GDP: 43770,
                                    人均折美元: 6466,
                                },
                            },
                            {
                                type: "Feature",
                                geometry: {
                                    type: "Point",
                                    coordinates: [120.580232, 30.029752],
                                },
                                properties: {
                                    id: 89,
                                    名称: "绍兴市",
                                    地址: "绍兴市",
                                    adcode_n: -1,
                                    adcode_p: -1,
                                    adcode_c: -1,
                                    adcode_d: -1,
                                    point_status: 0,
                                    创建时间: "2021-01-27 14:45:12",
                                    修改时间: "2021-01-27 14:45:12",
                                    人口: 491.22,
                                    GDP: 692,
                                    人均GDP: 56650,
                                    人均折美元: 8368,
                                },
                            },
                            {
                                type: "Feature",
                                geometry: {
                                    type: "Point",
                                    coordinates: [113.192661, 33.766169],
                                },
                                properties: {
                                    id: 90,
                                    名称: "平顶山市",
                                    地址: "平顶山市",
                                    adcode_n: -1,
                                    adcode_p: -1,
                                    adcode_c: -1,
                                    adcode_d: -1,
                                    point_status: 0,
                                    创建时间: "2021-01-27 14:45:12",
                                    修改时间: "2021-01-27 14:45:12",
                                    人口: 490.44,
                                    GDP: 155,
                                    人均GDP: 26754,
                                    人均折美元: 3952,
                                },
                            },
                            {
                                type: "Feature",
                                geometry: {
                                    type: "Point",
                                    coordinates: [108.708991, 34.329605],
                                },
                                properties: {
                                    id: 91,
                                    名称: "咸阳市",
                                    地址: "咸阳市",
                                    adcode_n: -1,
                                    adcode_p: -1,
                                    adcode_c: -1,
                                    adcode_d: -1,
                                    point_status: 0,
                                    创建时间: "2021-01-27 14:45:12",
                                    修改时间: "2021-01-27 14:45:12",
                                    人口: 489.48,
                                    GDP: 1333,
                                    人均GDP: 22446,
                                    人均折美元: 3316,
                                },
                            },
                            {
                                type: "Feature",
                                geometry: {
                                    type: "Point",
                                    coordinates: [115.778676, 33.844582],
                                },
                                properties: {
                                    id: 92,
                                    名称: "亳州市",
                                    地址: "亳州市",
                                    adcode_n: -1,
                                    adcode_p: -1,
                                    adcode_c: -1,
                                    adcode_d: -1,
                                    point_status: 0,
                                    创建时间: "2021-01-27 14:45:12",
                                    修改时间: "2021-01-27 14:45:12",
                                    人口: 485.1,
                                    GDP: 222,
                                    人均GDP: 10571,
                                    人均折美元: 1562,
                                },
                            },
                            {
                                type: "Feature",
                                geometry: {
                                    type: "Point",
                                    coordinates: [113.916902, 30.924568],
                                },
                                properties: {
                                    id: 93,
                                    名称: "孝感市",
                                    地址: "孝感市",
                                    adcode_n: -1,
                                    adcode_p: -1,
                                    adcode_c: -1,
                                    adcode_d: -1,
                                    point_status: 0,
                                    创建时间: "2021-01-27 14:45:12",
                                    修改时间: "2021-01-27 14:45:12",
                                    人口: 481.45,
                                    GDP: 285,
                                    人均GDP: 16630,
                                    人均折美元: 2457,
                                },
                            },
                            {
                                type: "Feature",
                                geometry: {
                                    type: "Point",
                                    coordinates: [114.992509, 27.113443],
                                },
                                properties: {
                                    id: 94,
                                    名称: "吉安市",
                                    地址: "吉安市",
                                    adcode_n: -1,
                                    adcode_p: -1,
                                    adcode_c: -1,
                                    adcode_d: -1,
                                    point_status: 0,
                                    创建时间: "2021-01-27 14:45:12",
                                    修改时间: "2021-01-27 14:45:12",
                                    人口: 481.03,
                                    GDP: 200,
                                    人均GDP: 14979,
                                    人均折美元: 2213,
                                },
                            },
                            {
                                type: "Feature",
                                geometry: {
                                    type: "Point",
                                    coordinates: [117.647481, 24.512948],
                                },
                                properties: {
                                    id: 95,
                                    名称: "漳州市",
                                    地址: "漳州市",
                                    adcode_n: -1,
                                    adcode_p: -1,
                                    adcode_c: -1,
                                    adcode_d: -1,
                                    point_status: 0,
                                    创建时间: "2021-01-27 14:45:12",
                                    修改时间: "2021-01-27 14:45:12",
                                    人口: 481,
                                    GDP: 822,
                                    人均GDP: 29121,
                                    人均折美元: 4302,
                                },
                            },
                            {
                                type: "Feature",
                                geometry: {
                                    type: "Point",
                                    coordinates: [119.015285, 33.610353],
                                },
                                properties: {
                                    id: 96,
                                    名称: "淮安市",
                                    地址: "淮安市",
                                    adcode_n: -1,
                                    adcode_p: -1,
                                    adcode_c: -1,
                                    adcode_d: -1,
                                    point_status: 0,
                                    创建时间: "2021-01-27 14:45:12",
                                    修改时间: "2021-01-27 14:45:12",
                                    人口: 479.99,
                                    GDP: 527,
                                    人均GDP: 28023,
                                    人均折美元: 4140,
                                },
                            },
                            {
                                type: "Feature",
                                geometry: {
                                    type: "Point",
                                    coordinates: [110.290194, 25.273566],
                                },
                                properties: {
                                    id: 97,
                                    名称: "桂林市",
                                    地址: "桂林市",
                                    adcode_n: -1,
                                    adcode_p: -1,
                                    adcode_c: -1,
                                    adcode_d: -1,
                                    point_status: 0,
                                    创建时间: "2021-01-27 14:45:12",
                                    修改时间: "2021-01-27 14:45:12",
                                    人口: 474.8,
                                    GDP: 93,
                                    人均GDP: 23349,
                                    人均折美元: 3449,
                                },
                            },
                            {
                                type: "Feature",
                                geometry: {
                                    type: "Point",
                                    coordinates: [109.998488, 27.554978],
                                },
                                properties: {
                                    id: 98,
                                    名称: "怀化市",
                                    地址: "怀化市",
                                    adcode_n: -1,
                                    adcode_p: -1,
                                    adcode_c: -1,
                                    adcode_d: -1,
                                    point_status: 0,
                                    创建时间: "2021-01-27 14:45:12",
                                    修改时间: "2021-01-27 14:45:12",
                                    人口: 474.19,
                                    GDP: 520,
                                    人均GDP: 14233,
                                    人均折美元: 2103,
                                },
                            },
                            {
                                type: "Feature",
                                geometry: {
                                    type: "Point",
                                    coordinates: [116.00193, 29.705077],
                                },
                                properties: {
                                    id: 99,
                                    名称: "九江市",
                                    地址: "九江市",
                                    adcode_n: -1,
                                    adcode_p: -1,
                                    adcode_c: -1,
                                    adcode_d: -1,
                                    point_status: 0,
                                    创建时间: "2021-01-27 14:45:12",
                                    修改时间: "2021-01-27 14:45:12",
                                    人口: 472.88,
                                    GDP: 300,
                                    人均GDP: 21825,
                                    人均折美元: 3224,
                                },
                            },
                            {
                                type: "Feature",
                                geometry: {
                                    type: "Point",
                                    coordinates: [118.275198, 33.963232],
                                },
                                properties: {
                                    id: 100,
                                    名称: "宿迁市",
                                    地址: "宿迁市",
                                    adcode_n: -1,
                                    adcode_p: -1,
                                    adcode_c: -1,
                                    adcode_d: -1,
                                    point_status: 0,
                                    创建时间: "2021-01-27 14:45:12",
                                    修改时间: "2021-01-27 14:45:12",
                                    人口: 471.56,
                                    GDP: 1372,
                                    人均GDP: 21529,
                                    人均折美元: 3180,
                                },
                            },
                            {
                                type: "Feature",
                                geometry: {
                                    type: "Point",
                                    coordinates: [114.307581, 34.797239],
                                },
                                properties: {
                                    id: 101,
                                    名称: "开封市",
                                    地址: "开封市",
                                    adcode_n: -1,
                                    adcode_p: -1,
                                    adcode_c: -1,
                                    adcode_d: -1,
                                    point_status: 0,
                                    创建时间: "2021-01-27 14:45:12",
                                    修改时间: "2021-01-27 14:45:12",
                                    人口: 467.62,
                                    GDP: 295,
                                    人均GDP: 19893,
                                    人均折美元: 2939,
                                },
                            },
                            {
                                type: "Feature",
                                geometry: {
                                    type: "Point",
                                    coordinates: [119.923116, 32.455778],
                                },
                                properties: {
                                    id: 102,
                                    名称: "泰州市",
                                    地址: "泰州市",
                                    adcode_n: -1,
                                    adcode_p: -1,
                                    adcode_c: -1,
                                    adcode_d: -1,
                                    point_status: 0,
                                    创建时间: "2021-01-27 14:45:12",
                                    修改时间: "2021-01-27 14:45:12",
                                    人口: 461.86,
                                    GDP: 740,
                                    人均GDP: 43359,
                                    人均折美元: 6405,
                                },
                            },
                            {
                                type: "Feature",
                                geometry: {
                                    type: "Point",
                                    coordinates: [104.679114, 31.46745],
                                },
                                properties: {
                                    id: 103,
                                    名称: "绵阳市",
                                    地址: "绵阳市",
                                    adcode_n: -1,
                                    adcode_p: -1,
                                    adcode_c: -1,
                                    adcode_d: -1,
                                    point_status: 0,
                                    创建时间: "2021-01-27 14:45:12",
                                    修改时间: "2021-01-27 14:45:12",
                                    人口: 461.39,
                                    GDP: 423,
                                    人均GDP: 20811,
                                    人均折美元: 3074,
                                },
                            },
                            {
                                type: "Feature",
                                geometry: {
                                    type: "Point",
                                    coordinates: [114.416196, 23.111847],
                                },
                                properties: {
                                    id: 104,
                                    名称: "惠州市",
                                    地址: "惠州市",
                                    adcode_n: -1,
                                    adcode_p: -1,
                                    adcode_c: -1,
                                    adcode_d: -1,
                                    point_status: 0,
                                    创建时间: "2021-01-27 14:45:12",
                                    修改时间: "2021-01-27 14:45:12",
                                    人口: 459.7,
                                    GDP: 100,
                                    人均GDP: 37631,
                                    人均折美元: 5559,
                                },
                            },
                            {
                                type: "Feature",
                                geometry: {
                                    type: "Point",
                                    coordinates: [119.973987, 31.810689],
                                },
                                properties: {
                                    id: 105,
                                    名称: "常州市",
                                    地址: "常州市",
                                    adcode_n: -1,
                                    adcode_p: -1,
                                    adcode_c: -1,
                                    adcode_d: -1,
                                    point_status: 0,
                                    创建时间: "2021-01-27 14:45:12",
                                    修改时间: "2021-01-27 14:45:12",
                                    人口: 459.2,
                                    GDP: 2237,
                                    人均GDP: 64824,
                                    人均折美元: 9576,
                                },
                            },
                            {
                                type: "Feature",
                                geometry: {
                                    type: "Point",
                                    coordinates: [113.014717, 25.770509],
                                },
                                properties: {
                                    id: 106,
                                    名称: "郴州市",
                                    地址: "郴州市",
                                    adcode_n: -1,
                                    adcode_p: -1,
                                    adcode_c: -1,
                                    adcode_d: -1,
                                    point_status: 0,
                                    创建时间: "2021-01-27 14:45:12",
                                    修改时间: "2021-01-27 14:45:12",
                                    人口: 458.18,
                                    GDP: 405,
                                    人均GDP: 23611,
                                    人均折美元: 3488,
                                },
                            },
                            {
                                type: "Feature",
                                geometry: {
                                    type: "Point",
                                    coordinates: [102.267335, 27.88161],
                                },
                                properties: {
                                    id: 107,
                                    名称: "凉山州",
                                    地址: "凉山州",
                                    adcode_n: -1,
                                    adcode_p: -1,
                                    adcode_c: -1,
                                    adcode_d: -1,
                                    point_status: 0,
                                    创建时间: "2021-01-27 14:45:12",
                                    修改时间: "2021-01-27 14:45:12",
                                    人口: 453.28,
                                    GDP: 300,
                                    人均GDP: 17300,
                                    人均折美元: 2556,
                                },
                            },
                            {
                                type: "Feature",
                                geometry: {
                                    type: "Point",
                                    coordinates: [118.054927, 36.813487],
                                },
                                properties: {
                                    id: 108,
                                    名称: "淄博市",
                                    地址: "淄博市",
                                    adcode_n: -1,
                                    adcode_p: -1,
                                    adcode_c: -1,
                                    adcode_d: -1,
                                    point_status: 0,
                                    创建时间: "2021-01-27 14:45:12",
                                    修改时间: "2021-01-27 14:45:12",
                                    人口: 453.06,
                                    GDP: 1005,
                                    人均GDP: 63275,
                                    人均折美元: 9347,
                                },
                            },
                            {
                                type: "Feature",
                                geometry: {
                                    type: "Point",
                                    coordinates: [120.755486, 30.746129],
                                },
                                properties: {
                                    id: 109,
                                    名称: "嘉兴市",
                                    地址: "嘉兴市",
                                    adcode_n: -1,
                                    adcode_p: -1,
                                    adcode_c: -1,
                                    adcode_d: -1,
                                    point_status: 0,
                                    创建时间: "2021-01-27 14:45:12",
                                    修改时间: "2021-01-27 14:45:12",
                                    人口: 450.17,
                                    GDP: 1096,
                                    人均GDP: 51003,
                                    人均折美元: 7534,
                                },
                            },
                            {
                                type: "Feature",
                                geometry: {
                                    type: "Point",
                                    coordinates: [103.374799, 23.36313],
                                },
                                properties: {
                                    id: 110,
                                    名称: "红河州",
                                    地址: "红河州",
                                    adcode_n: -1,
                                    adcode_p: -1,
                                    adcode_c: -1,
                                    adcode_d: -1,
                                    point_status: 0,
                                    创建时间: "2021-01-27 14:45:12",
                                    修改时间: "2021-01-27 14:45:12",
                                    人口: 450.1,
                                    GDP: 100,
                                    人均GDP: 14451,
                                    人均折美元: 2135,
                                },
                            },
                            {
                                type: "Feature",
                                geometry: {
                                    type: "Point",
                                    coordinates: [104.643215, 28.751768],
                                },
                                properties: {
                                    id: 111,
                                    名称: "宜宾市",
                                    地址: "宜宾市",
                                    adcode_n: -1,
                                    adcode_p: -1,
                                    adcode_c: -1,
                                    adcode_d: -1,
                                    point_status: 0,
                                    创建时间: "2021-01-27 14:45:12",
                                    修改时间: "2021-01-27 14:45:12",
                                    人口: 447.2,
                                    GDP: 172,
                                    人均GDP: 19473,
                                    人均折美元: 2877,
                                },
                            },
                            {
                                type: "Feature",
                                geometry: {
                                    type: "Point",
                                    coordinates: [119.412966, 32.39421],
                                },
                                properties: {
                                    id: 112,
                                    名称: "扬州市",
                                    地址: "扬州市",
                                    adcode_n: -1,
                                    adcode_p: -1,
                                    adcode_c: -1,
                                    adcode_d: -1,
                                    point_status: 0,
                                    创建时间: "2021-01-27 14:45:12",
                                    修改时间: "2021-01-27 14:45:12",
                                    人口: 445.98,
                                    GDP: 1276,
                                    人均GDP: 49509,
                                    人均折美元: 7314,
                                },
                            },
                            {
                                type: "Feature",
                                geometry: {
                                    type: "Point",
                                    coordinates: [113.081901, 22.578738],
                                },
                                properties: {
                                    id: 113,
                                    名称: "江门市",
                                    地址: "江门市",
                                    adcode_n: -1,
                                    adcode_p: -1,
                                    adcode_c: -1,
                                    adcode_d: -1,
                                    point_status: 0,
                                    创建时间: "2021-01-27 14:45:12",
                                    修改时间: "2021-01-27 14:45:12",
                                    人口: 444.89,
                                    GDP: 275,
                                    人均GDP: 34849,
                                    人均折美元: 5148,
                                },
                            },
                            {
                                type: "Feature",
                                geometry: {
                                    type: "Point",
                                    coordinates: [126.549576, 43.837883],
                                },
                                properties: {
                                    id: 114,
                                    名称: "吉林市",
                                    地址: "吉林市",
                                    adcode_n: -1,
                                    adcode_p: -1,
                                    adcode_c: -1,
                                    adcode_d: -1,
                                    point_status: 0,
                                    创建时间: "2021-01-27 14:45:12",
                                    修改时间: "2021-01-27 14:45:12",
                                    人口: 441.47,
                                    GDP: 24,
                                    人均GDP: 40787,
                                    人均折美元: 6025,
                                },
                            },
                            {
                                type: "Feature",
                                geometry: {
                                    type: "Point",
                                    coordinates: [119.221611, 34.596653],
                                },
                                properties: {
                                    id: 115,
                                    名称: "连云港市",
                                    地址: "连云港市",
                                    adcode_n: -1,
                                    adcode_p: -1,
                                    adcode_c: -1,
                                    adcode_d: -1,
                                    point_status: 0,
                                    创建时间: "2021-01-27 14:45:12",
                                    修改时间: "2021-01-27 14:45:12",
                                    人口: 439.39,
                                    GDP: 860,
                                    人均GDP: 26191,
                                    人均折美元: 3869,
                                },
                            },
                            {
                                type: "Feature",
                                geometry: {
                                    type: "Point",
                                    coordinates: [116.683752, 39.538047],
                                },
                                properties: {
                                    id: 116,
                                    名称: "廊坊市",
                                    地址: "廊坊市",
                                    adcode_n: -1,
                                    adcode_p: -1,
                                    adcode_c: -1,
                                    adcode_d: -1,
                                    point_status: 0,
                                    创建时间: "2021-01-27 14:45:12",
                                    修改时间: "2021-01-27 14:45:12",
                                    人口: 435.88,
                                    GDP: 1207,
                                    人均GDP: 30538,
                                    人均折美元: 4511,
                                },
                            },
                            {
                                type: "Feature",
                                geometry: {
                                    type: "Point",
                                    coordinates: [114.887543, 40.824418],
                                },
                                properties: {
                                    id: 117,
                                    名称: "张家口市",
                                    地址: "张家口市",
                                    adcode_n: -1,
                                    adcode_p: -1,
                                    adcode_c: -1,
                                    adcode_d: -1,
                                    point_status: 0,
                                    创建时间: "2021-01-27 14:45:12",
                                    修改时间: "2021-01-27 14:45:12",
                                    人口: 434.55,
                                    GDP: 820,
                                    人均GDP: 22233,
                                    人均折美元: 3284,
                                },
                            },
                            {
                                type: "Feature",
                                geometry: {
                                    type: "Point",
                                    coordinates: [118.886856, 42.257817],
                                },
                                properties: {
                                    id: 118,
                                    名称: "赤峰市",
                                    地址: "赤峰市",
                                    adcode_n: -1,
                                    adcode_p: -1,
                                    adcode_c: -1,
                                    adcode_d: -1,
                                    point_status: 0,
                                    创建时间: "2021-01-27 14:45:12",
                                    修改时间: "2021-01-27 14:45:12",
                                    人口: 434.12,
                                    GDP: 6,
                                    人均GDP: 24882,
                                    人均折美元: 3676,
                                },
                            },
                            {
                                type: "Feature",
                                geometry: {
                                    type: "Point",
                                    coordinates: [115.670177, 37.73892],
                                },
                                properties: {
                                    id: 119,
                                    名称: "衡水市",
                                    地址: "衡水市",
                                    adcode_n: -1,
                                    adcode_p: -1,
                                    adcode_c: -1,
                                    adcode_d: -1,
                                    point_status: 0,
                                    创建时间: "2021-01-27 14:45:12",
                                    修改时间: "2021-01-27 14:45:12",
                                    人口: 434.08,
                                    GDP: 497,
                                    人均GDP: 18004,
                                    人均折美元: 2660,
                                },
                            },
                            {
                                type: "Feature",
                                geometry: {
                                    type: "Point",
                                    coordinates: [106.630153, 26.647661],
                                },
                                properties: {
                                    id: 120,
                                    名称: "贵阳市",
                                    地址: "贵阳市",
                                    adcode_n: -1,
                                    adcode_p: -1,
                                    adcode_c: -1,
                                    adcode_d: -1,
                                    point_status: 0,
                                    创建时间: "2021-01-27 14:45:12",
                                    修改时间: "2021-01-27 14:45:12",
                                    人口: 432.46,
                                    GDP: 1405,
                                    人均GDP: 25941,
                                    人均折美元: 3832,
                                },
                            },
                            {
                                type: "Feature",
                                geometry: {
                                    type: "Point",
                                    coordinates: [111.518976, 36.088005],
                                },
                                properties: {
                                    id: 121,
                                    名称: "临汾市",
                                    地址: "临汾市",
                                    adcode_n: -1,
                                    adcode_p: -1,
                                    adcode_c: -1,
                                    adcode_d: -1,
                                    point_status: 0,
                                    创建时间: "2021-01-27 14:45:12",
                                    修改时间: "2021-01-27 14:45:12",
                                    人口: 431.66,
                                    GDP: 412,
                                    人均GDP: 20667,
                                    人均折美元: 3053,
                                },
                            },
                            {
                                type: "Feature",
                                geometry: {
                                    type: "Point",
                                    coordinates: [112.35518, 28.55386],
                                },
                                properties: {
                                    id: 122,
                                    名称: "益阳市",
                                    地址: "益阳市",
                                    adcode_n: -1,
                                    adcode_p: -1,
                                    adcode_c: -1,
                                    adcode_d: -1,
                                    point_status: 0,
                                    创建时间: "2021-01-27 14:45:12",
                                    修改时间: "2021-01-27 14:45:12",
                                    人口: 431.31,
                                    GDP: 412,
                                    人均GDP: 16514,
                                    人均折美元: 2439,
                                },
                            },
                            {
                                type: "Feature",
                                geometry: {
                                    type: "Point",
                                    coordinates: [113.85264, 34.035506],
                                },
                                properties: {
                                    id: 123,
                                    名称: "许昌市",
                                    地址: "许昌市",
                                    adcode_n: -1,
                                    adcode_p: -1,
                                    adcode_c: -1,
                                    adcode_d: -1,
                                    point_status: 0,
                                    创建时间: "2021-01-27 14:45:12",
                                    修改时间: "2021-01-27 14:45:12",
                                    人口: 430.72,
                                    GDP: 185,
                                    人均GDP: 30479,
                                    人均折美元: 4502,
                                },
                            },
                            {
                                type: "Feature",
                                geometry: {
                                    type: "Point",
                                    coordinates: [116.122238, 24.288615],
                                },
                                properties: {
                                    id: 124,
                                    名称: "梅州市",
                                    地址: "梅州市",
                                    adcode_n: -1,
                                    adcode_p: -1,
                                    adcode_c: -1,
                                    adcode_d: -1,
                                    point_status: 0,
                                    创建时间: "2021-01-27 14:45:12",
                                    修改时间: "2021-01-27 14:45:12",
                                    人口: 424.01,
                                    GDP: 29,
                                    人均GDP: 14442,
                                    人均折美元: 2133,
                                },
                            },
                            {
                                type: "Feature",
                                geometry: {
                                    type: "Point",
                                    coordinates: [105.442258, 28.871811],
                                },
                                properties: {
                                    id: 125,
                                    名称: "泸州市",
                                    地址: "泸州市",
                                    adcode_n: -1,
                                    adcode_p: -1,
                                    adcode_c: -1,
                                    adcode_d: -1,
                                    point_status: 0,
                                    创建时间: "2021-01-27 14:45:12",
                                    修改时间: "2021-01-27 14:45:12",
                                    人口: 421.84,
                                    GDP: 75,
                                    人均GDP: 16945,
                                    人均折美元: 2503,
                                },
                            },
                            {
                                type: "Feature",
                                geometry: {
                                    type: "Point",
                                    coordinates: [112.548879, 37.87059],
                                },
                                properties: {
                                    id: 126,
                                    名称: "太原市",
                                    地址: "太原市",
                                    adcode_n: -1,
                                    adcode_p: -1,
                                    adcode_c: -1,
                                    adcode_d: -1,
                                    point_status: 0,
                                    创建时间: "2021-01-27 14:45:12",
                                    修改时间: "2021-01-27 14:45:12",
                                    人口: 420.16,
                                    GDP: 4391,
                                    人均GDP: 42318,
                                    人均折美元: 6251,
                                },
                            },
                            {
                                type: "Feature",
                                geometry: {
                                    type: "Point",
                                    coordinates: [109.598926, 23.11153],
                                },
                                properties: {
                                    id: 127,
                                    名称: "贵港市",
                                    地址: "贵港市",
                                    adcode_n: -1,
                                    adcode_p: -1,
                                    adcode_c: -1,
                                    adcode_d: -1,
                                    point_status: 0,
                                    创建时间: "2021-01-27 14:45:12",
                                    修改时间: "2021-01-27 14:45:12",
                                    人口: 411.88,
                                    GDP: 60,
                                    人均GDP: 12814,
                                    人均折美元: 1893,
                                },
                            },
                            {
                                type: "Feature",
                                geometry: {
                                    type: "Point",
                                    coordinates: [111.286471, 30.691967],
                                },
                                properties: {
                                    id: 128,
                                    名称: "宜昌市",
                                    地址: "宜昌市",
                                    adcode_n: -1,
                                    adcode_p: -1,
                                    adcode_c: -1,
                                    adcode_d: -1,
                                    point_status: 0,
                                    创建时间: "2021-01-27 14:45:12",
                                    修改时间: "2021-01-27 14:45:12",
                                    人口: 405.97,
                                    GDP: 574,
                                    人均GDP: 38114,
                                    人均折美元: 5630,
                                },
                            },
                            {
                                type: "Feature",
                                geometry: {
                                    type: "Point",
                                    coordinates: [75.989755, 39.4704],
                                },
                                properties: {
                                    id: 129,
                                    名称: "喀什地区",
                                    地址: "喀什地区",
                                    adcode_n: -1,
                                    adcode_p: -1,
                                    adcode_c: -1,
                                    adcode_d: -1,
                                    point_status: 0,
                                    创建时间: "2021-01-27 14:45:12",
                                    修改时间: "2021-01-27 14:45:12",
                                    人口: 397.94,
                                    GDP: 50,
                                    人均GDP: 9424,
                                    人均折美元: 1392,
                                },
                            },
                            {
                                type: "Feature",
                                geometry: {
                                    type: "Point",
                                    coordinates: [118.317106, 32.301556],
                                },
                                properties: {
                                    id: 130,
                                    名称: "滁州市",
                                    地址: "滁州市",
                                    adcode_n: -1,
                                    adcode_p: -1,
                                    adcode_c: -1,
                                    adcode_d: -1,
                                    point_status: 0,
                                    创建时间: "2021-01-27 14:45:12",
                                    修改时间: "2021-01-27 14:45:12",
                                    人口: 393.8,
                                    GDP: 261,
                                    人均GDP: 17665,
                                    人均折美元: 2610,
                                },
                            },
                            {
                                type: "Feature",
                                geometry: {
                                    type: "Point",
                                    coordinates: [112.465091, 23.047191],
                                },
                                properties: {
                                    id: 131,
                                    名称: "肇庆市",
                                    地址: "肇庆市",
                                    adcode_n: -1,
                                    adcode_p: -1,
                                    adcode_c: -1,
                                    adcode_d: -1,
                                    point_status: 0,
                                    创建时间: "2021-01-27 14:45:12",
                                    修改时间: "2021-01-27 14:45:12",
                                    人口: 391.81,
                                    GDP: 192,
                                    人均GDP: 27205,
                                    人均折美元: 4019,
                                },
                            },
                            {
                                type: "Feature",
                                geometry: {
                                    type: "Point",
                                    coordinates: [116.358181, 27.949217],
                                },
                                properties: {
                                    id: 132,
                                    名称: "抚州市",
                                    地址: "抚州市",
                                    adcode_n: -1,
                                    adcode_p: -1,
                                    adcode_c: -1,
                                    adcode_d: -1,
                                    point_status: 0,
                                    创建时间: "2021-01-27 14:45:12",
                                    修改时间: "2021-01-27 14:45:12",
                                    人口: 391.23,
                                    GDP: 300,
                                    人均GDP: 16103,
                                    人均折美元: 2379,
                                },
                            },
                            {
                                type: "Feature",
                                geometry: {
                                    type: "Point",
                                    coordinates: [117.8618, 31.598628],
                                },
                                properties: {
                                    id: 133,
                                    名称: "巢湖市",
                                    地址: "巢湖市",
                                    adcode_n: -1,
                                    adcode_p: -1,
                                    adcode_c: -1,
                                    adcode_d: -1,
                                    point_status: 0,
                                    创建时间: "2021-01-27 14:45:12",
                                    修改时间: "2021-01-27 14:45:12",
                                    人口: 387.3,
                                    GDP: 100,
                                    人均GDP: 16137,
                                    人均折美元: 2384,
                                },
                            },
                            {
                                type: "Feature",
                                geometry: {
                                    type: "Point",
                                    coordinates: [113.134002, 27.82755],
                                },
                                properties: {
                                    id: 134,
                                    名称: "株洲市",
                                    地址: "株洲市",
                                    adcode_n: -1,
                                    adcode_p: -1,
                                    adcode_c: -1,
                                    adcode_d: -1,
                                    point_status: 0,
                                    创建时间: "2021-01-27 14:45:12",
                                    修改时间: "2021-01-27 14:45:12",
                                    人口: 385.56,
                                    GDP: 554,
                                    人均GDP: 33064,
                                    人均折美元: 4884,
                                },
                            },
                            {
                                type: "Feature",
                                geometry: {
                                    type: "Point",
                                    coordinates: [111.993497, 27.700062],
                                },
                                properties: {
                                    id: 135,
                                    名称: "娄底市",
                                    地址: "娄底市",
                                    adcode_n: -1,
                                    adcode_p: -1,
                                    adcode_c: -1,
                                    adcode_d: -1,
                                    point_status: 0,
                                    创建时间: "2021-01-27 14:45:12",
                                    修改时间: "2021-01-27 14:45:12",
                                    人口: 378.56,
                                    GDP: 559,
                                    人均GDP: 17982,
                                    人均折美元: 2656,
                                },
                            },
                            {
                                type: "Feature",
                                geometry: {
                                    type: "Point",
                                    coordinates: [109.415953, 24.325502],
                                },
                                properties: {
                                    id: 136,
                                    名称: "柳州市",
                                    地址: "柳州市",
                                    adcode_n: -1,
                                    adcode_p: -1,
                                    adcode_c: -1,
                                    adcode_d: -1,
                                    point_status: 0,
                                    创建时间: "2021-01-27 14:45:12",
                                    修改时间: "2021-01-27 14:45:12",
                                    人口: 375.87,
                                    GDP: 296,
                                    人均GDP: 33530,
                                    人均折美元: 4953,
                                },
                            },
                            {
                                type: "Feature",
                                geometry: {
                                    type: "Point",
                                    coordinates: [112.944049, 27.829738],
                                },
                                properties: {
                                    id: 137,
                                    名称: "湘潭市",
                                    地址: "湘潭市",
                                    adcode_n: -1,
                                    adcode_p: -1,
                                    adcode_c: -1,
                                    adcode_d: -1,
                                    point_status: 0,
                                    创建时间: "2021-01-27 14:45:12",
                                    修改时间: "2021-01-27 14:45:12",
                                    人口: 374.86,
                                    GDP: 486,
                                    人均GDP: 23849,
                                    人均折美元: 3523,
                                },
                            },
                            {
                                type: "Feature",
                                geometry: {
                                    type: "Point",
                                    coordinates: [117.970703, 37.38199],
                                },
                                properties: {
                                    id: 138,
                                    名称: "滨州市",
                                    地址: "滨州市",
                                    adcode_n: -1,
                                    adcode_p: -1,
                                    adcode_c: -1,
                                    adcode_d: -1,
                                    point_status: 0,
                                    创建时间: "2021-01-27 14:45:12",
                                    修改时间: "2021-01-27 14:45:12",
                                    人口: 374.85,
                                    GDP: 247,
                                    人均GDP: 41390,
                                    人均折美元: 6114,
                                },
                            },
                            {
                                type: "Feature",
                                geometry: {
                                    type: "Point",
                                    coordinates: [117.323725, 34.810487],
                                },
                                properties: {
                                    id: 139,
                                    名称: "枣庄市",
                                    地址: "枣庄市",
                                    adcode_n: -1,
                                    adcode_p: -1,
                                    adcode_c: -1,
                                    adcode_d: -1,
                                    point_status: 0,
                                    创建时间: "2021-01-27 14:45:12",
                                    修改时间: "2021-01-27 14:45:12",
                                    人口: 372.93,
                                    GDP: 382,
                                    人均GDP: 36523,
                                    人均折美元: 5395,
                                },
                            },
                            {
                                type: "Feature",
                                geometry: {
                                    type: "Point",
                                    coordinates: [111.144319, 37.518314],
                                },
                                properties: {
                                    id: 140,
                                    名称: "吕梁市",
                                    地址: "吕梁市",
                                    adcode_n: -1,
                                    adcode_p: -1,
                                    adcode_c: -1,
                                    adcode_d: -1,
                                    point_status: 0,
                                    创建时间: "2021-01-27 14:45:12",
                                    修改时间: "2021-01-27 14:45:12",
                                    人口: 372.71,
                                    GDP: 200,
                                    人均GDP: 22685,
                                    人均折美元: 3351,
                                },
                            },
                            {
                                type: "Feature",
                                geometry: {
                                    type: "Point",
                                    coordinates: [107.237974, 34.361979],
                                },
                                properties: {
                                    id: 141,
                                    名称: "宝鸡市",
                                    地址: "宝鸡市",
                                    adcode_n: -1,
                                    adcode_p: -1,
                                    adcode_c: -1,
                                    adcode_d: -1,
                                    point_status: 0,
                                    创建时间: "2021-01-27 14:45:12",
                                    修改时间: "2021-01-27 14:45:12",
                                    人口: 371.67,
                                    GDP: 938,
                                    人均GDP: 26262,
                                    人均折美元: 3879,
                                },
                            },
                            {
                                type: "Feature",
                                geometry: {
                                    type: "Point",
                                    coordinates: [105.058433, 29.580228],
                                },
                                properties: {
                                    id: 142,
                                    名称: "内江市",
                                    地址: "内江市",
                                    adcode_n: -1,
                                    adcode_p: -1,
                                    adcode_c: -1,
                                    adcode_d: -1,
                                    point_status: 0,
                                    创建时间: "2021-01-27 14:45:12",
                                    修改时间: "2021-01-27 14:45:12",
                                    人口: 370.28,
                                    GDP: 96,
                                    人均GDP: 18642,
                                    人均折美元: 2754,
                                },
                            },
                            {
                                type: "Feature",
                                geometry: {
                                    type: "Point",
                                    coordinates: [113.056031, 23.681763],
                                },
                                properties: {
                                    id: 143,
                                    名称: "清远市",
                                    地址: "清远市",
                                    adcode_n: -1,
                                    adcode_p: -1,
                                    adcode_c: -1,
                                    adcode_d: -1,
                                    point_status: 0,
                                    创建时间: "2021-01-27 14:45:12",
                                    修改时间: "2021-01-27 14:45:12",
                                    人口: 369.84,
                                    GDP: 307,
                                    人均GDP: 30081,
                                    人均折美元: 4444,
                                },
                            },
                            {
                                type: "Feature",
                                geometry: {
                                    type: "Point",
                                    coordinates: [104.627636, 30.128901],
                                },
                                properties: {
                                    id: 144,
                                    名称: "资阳市",
                                    地址: "资阳市",
                                    adcode_n: -1,
                                    adcode_p: -1,
                                    adcode_c: -1,
                                    adcode_d: -1,
                                    point_status: 0,
                                    创建时间: "2021-01-27 14:45:12",
                                    修改时间: "2021-01-27 14:45:12",
                                    人口: 366.51,
                                    GDP: 97,
                                    人均GDP: 17951,
                                    人均折美元: 2652,
                                },
                            },
                            {
                                type: "Feature",
                                geometry: {
                                    type: "Point",
                                    coordinates: [122.994329, 41.108647],
                                },
                                properties: {
                                    id: 145,
                                    名称: "鞍山市",
                                    地址: "鞍山市",
                                    adcode_n: -1,
                                    adcode_p: -1,
                                    adcode_c: -1,
                                    adcode_d: -1,
                                    point_status: 0,
                                    创建时间: "2021-01-27 14:45:12",
                                    修改时间: "2021-01-27 14:45:12",
                                    人口: 364.59,
                                    GDP: 198,
                                    人均GDP: 60342,
                                    人均折美元: 8914,
                                },
                            },
                            {
                                type: "Feature",
                                geometry: {
                                    type: "Point",
                                    coordinates: [103.834303, 36.061089],
                                },
                                properties: {
                                    id: 146,
                                    名称: "兰州市",
                                    地址: "兰州市",
                                    adcode_n: -1,
                                    adcode_p: -1,
                                    adcode_c: -1,
                                    adcode_d: -1,
                                    point_status: 0,
                                    创建时间: "2021-01-27 14:45:12",
                                    修改时间: "2021-01-27 14:45:12",
                                    人口: 361.62,
                                    GDP: 1575,
                                    人均GDP: 30430,
                                    人均折美元: 4495,
                                },
                            },
                            {
                                type: "Feature",
                                geometry: {
                                    type: "Point",
                                    coordinates: [104.397894, 31.126855],
                                },
                                properties: {
                                    id: 147,
                                    名称: "德阳市",
                                    地址: "德阳市",
                                    adcode_n: -1,
                                    adcode_p: -1,
                                    adcode_c: -1,
                                    adcode_d: -1,
                                    point_status: 0,
                                    创建时间: "2021-01-27 14:45:12",
                                    修改时间: "2021-01-27 14:45:12",
                                    人口: 361.58,
                                    GDP: 345,
                                    人均GDP: 25480,
                                    人均折美元: 3764,
                                },
                            },
                            {
                                type: "Feature",
                                geometry: {
                                    type: "Point",
                                    coordinates: [115.029215, 35.761829],
                                },
                                properties: {
                                    id: 148,
                                    名称: "濮阳市",
                                    地址: "濮阳市",
                                    adcode_n: -1,
                                    adcode_p: -1,
                                    adcode_c: -1,
                                    adcode_d: -1,
                                    point_status: 0,
                                    创建时间: "2021-01-27 14:45:12",
                                    修改时间: "2021-01-27 14:45:12",
                                    人口: 359.85,
                                    GDP: 302,
                                    人均GDP: 21532,
                                    人均折美元: 3181,
                                },
                            },
                            {
                                type: "Feature",
                                geometry: {
                                    type: "Point",
                                    coordinates: [113.241823, 35.215892],
                                },
                                properties: {
                                    id: 149,
                                    名称: "焦作市",
                                    地址: "焦作市",
                                    adcode_n: -1,
                                    adcode_p: -1,
                                    adcode_c: -1,
                                    adcode_d: -1,
                                    point_status: 0,
                                    创建时间: "2021-01-27 14:45:12",
                                    修改时间: "2021-01-27 14:45:12",
                                    人口: 353.99,
                                    GDP: 314,
                                    人均GDP: 35245,
                                    人均折美元: 5206,
                                },
                            },
                            {
                                type: "Feature",
                                geometry: {
                                    type: "Point",
                                    coordinates: [118.089425, 24.479833],
                                },
                                properties: {
                                    id: 150,
                                    名称: "厦门市",
                                    地址: "厦门市",
                                    adcode_n: -1,
                                    adcode_p: -1,
                                    adcode_c: -1,
                                    adcode_d: -1,
                                    point_status: 0,
                                    创建时间: "2021-01-27 14:45:12",
                                    修改时间: "2021-01-27 14:45:12",
                                    人口: 353.13,
                                    GDP: 5085,
                                    人均GDP: 58157,
                                    人均折美元: 8591,
                                },
                            },
                            {
                                type: "Feature",
                                geometry: {
                                    type: "Point",
                                    coordinates: [104.216248, 23.400733],
                                },
                                properties: {
                                    id: 151,
                                    名称: "文山州",
                                    地址: "文山州",
                                    adcode_n: -1,
                                    adcode_p: -1,
                                    adcode_c: -1,
                                    adcode_d: -1,
                                    point_status: 0,
                                    创建时间: "2021-01-27 14:45:12",
                                    修改时间: "2021-01-27 14:45:12",
                                    人口: 351.8,
                                    GDP: 358,
                                    人均GDP: 9377,
                                    人均折美元: 1385,
                                },
                            },
                            {
                                type: "Feature",
                                geometry: {
                                    type: "Point",
                                    coordinates: [107.982859, 26.583442],
                                },
                                properties: {
                                    id: 152,
                                    名称: "黔东南州",
                                    地址: "黔东南州",
                                    adcode_n: -1,
                                    adcode_p: -1,
                                    adcode_c: -1,
                                    adcode_d: -1,
                                    point_status: 0,
                                    创建时间: "2021-01-27 14:45:12",
                                    修改时间: "2021-01-27 14:45:12",
                                    人口: 348.06,
                                    GDP: 100,
                                    人均GDP: 8980,
                                    人均折美元: 1327,
                                },
                            },
                            {
                                type: "Feature",
                                geometry: {
                                    type: "Point",
                                    coordinates: [117.96241, 40.954071],
                                },
                                properties: {
                                    id: 153,
                                    名称: "承德市",
                                    地址: "承德市",
                                    adcode_n: -1,
                                    adcode_p: -1,
                                    adcode_c: -1,
                                    adcode_d: -1,
                                    point_status: 0,
                                    创建时间: "2021-01-27 14:45:12",
                                    修改时间: "2021-01-27 14:45:12",
                                    人口: 347.32,
                                    GDP: 358,
                                    人均GDP: 25351,
                                    人均折美元: 3745,
                                },
                            },
                            {
                                type: "Feature",
                                geometry: {
                                    type: "Point",
                                    coordinates: [106.618201, 23.902333],
                                },
                                properties: {
                                    id: 154,
                                    名称: "百色市",
                                    地址: "百色市",
                                    adcode_n: -1,
                                    adcode_p: -1,
                                    adcode_c: -1,
                                    adcode_d: -1,
                                    point_status: 0,
                                    创建时间: "2021-01-27 14:45:12",
                                    修改时间: "2021-01-27 14:45:12",
                                    人口: 346.68,
                                    GDP: 8,
                                    人均GDP: 16254,
                                    人均折美元: 2401,
                                },
                            },
                            {
                                type: "Feature",
                                geometry: {
                                    type: "Point",
                                    coordinates: [100.267638, 25.606486],
                                },
                                properties: {
                                    id: 155,
                                    名称: "大理州",
                                    地址: "大理州",
                                    adcode_n: -1,
                                    adcode_p: -1,
                                    adcode_c: -1,
                                    adcode_d: -1,
                                    point_status: 0,
                                    创建时间: "2021-01-27 14:45:12",
                                    修改时间: "2021-01-27 14:45:12",
                                    人口: 345.6,
                                    GDP: 300,
                                    人均GDP: 13741,
                                    人均折美元: 2030,
                                },
                            },
                            {
                                type: "Feature",
                                geometry: {
                                    type: "Point",
                                    coordinates: [107.023323, 33.06748],
                                },
                                properties: {
                                    id: 156,
                                    名称: "汉中市",
                                    地址: "汉中市",
                                    adcode_n: -1,
                                    adcode_p: -1,
                                    adcode_c: -1,
                                    adcode_d: -1,
                                    point_status: 0,
                                    创建时间: "2021-01-27 14:45:12",
                                    修改时间: "2021-01-27 14:45:12",
                                    人口: 341.62,
                                    GDP: 483,
                                    人均GDP: 14920,
                                    人均折美元: 2204,
                                },
                            },
                            {
                                type: "Feature",
                                geometry: {
                                    type: "Point",
                                    coordinates: [124.350398, 43.166419],
                                },
                                properties: {
                                    id: 157,
                                    名称: "四平市",
                                    地址: "四平市",
                                    adcode_n: -1,
                                    adcode_p: -1,
                                    adcode_c: -1,
                                    adcode_d: -1,
                                    point_status: 0,
                                    创建时间: "2021-01-27 14:45:12",
                                    修改时间: "2021-01-27 14:45:12",
                                    人口: 338.63,
                                    GDP: 58,
                                    人均GDP: 23303,
                                    人均折美元: 3442,
                                },
                            },
                            {
                                type: "Feature",
                                geometry: {
                                    type: "Point",
                                    coordinates: [108.085261, 24.692931],
                                },
                                properties: {
                                    id: 158,
                                    名称: "河池市",
                                    地址: "河池市",
                                    adcode_n: -1,
                                    adcode_p: -1,
                                    adcode_c: -1,
                                    adcode_d: -1,
                                    point_status: 0,
                                    创建时间: "2021-01-27 14:45:12",
                                    修改时间: "2021-01-27 14:45:12",
                                    人口: 336.92,
                                    GDP: 13,
                                    人均GDP: 13861,
                                    人均折美元: 2048,
                                },
                            },
                            {
                                type: "Feature",
                                geometry: {
                                    type: "Point",
                                    coordinates: [109.734589, 38.28539],
                                },
                                properties: {
                                    id: 159,
                                    名称: "榆林市",
                                    地址: "榆林市",
                                    adcode_n: -1,
                                    adcode_p: -1,
                                    adcode_c: -1,
                                    adcode_d: -1,
                                    point_status: 0,
                                    创建时间: "2021-01-27 14:45:12",
                                    修改时间: "2021-01-27 14:45:12",
                                    人口: 335.14,
                                    GDP: 65,
                                    人均GDP: 52415,
                                    人均折美元: 7743,
                                },
                            },
                            {
                                type: "Feature",
                                geometry: {
                                    type: "Point",
                                    coordinates: [110.79799, 32.629397],
                                },
                                properties: {
                                    id: 160,
                                    名称: "十堰市",
                                    地址: "十堰市",
                                    adcode_n: -1,
                                    adcode_p: -1,
                                    adcode_c: -1,
                                    adcode_d: -1,
                                    point_status: 0,
                                    创建时间: "2021-01-27 14:45:12",
                                    修改时间: "2021-01-27 14:45:12",
                                    人口: 334.08,
                                    GDP: 538,
                                    人均GDP: 22054,
                                    人均折美元: 3258,
                                },
                            },
                            {
                                type: "Feature",
                                geometry: {
                                    type: "Point",
                                    coordinates: [113.116255, 36.195386],
                                },
                                properties: {
                                    id: 161,
                                    名称: "长治市",
                                    地址: "长治市",
                                    adcode_n: -1,
                                    adcode_p: -1,
                                    adcode_c: -1,
                                    adcode_d: -1,
                                    point_status: 0,
                                    创建时间: "2021-01-27 14:45:12",
                                    修改时间: "2021-01-27 14:45:12",
                                    人口: 333.46,
                                    GDP: 294,
                                    人均GDP: 27596,
                                    人均折美元: 4076,
                                },
                            },
                            {
                                type: "Feature",
                                geometry: {
                                    type: "Point",
                                    coordinates: [113.300129, 40.076762],
                                },
                                properties: {
                                    id: 162,
                                    名称: "大同市",
                                    地址: "大同市",
                                    adcode_n: -1,
                                    adcode_p: -1,
                                    adcode_c: -1,
                                    adcode_d: -1,
                                    point_status: 0,
                                    创建时间: "2021-01-27 14:45:12",
                                    修改时间: "2021-01-27 14:45:12",
                                    人口: 331.81,
                                    GDP: 294,
                                    人均GDP: 20925,
                                    人均折美元: 3091,
                                },
                            },
                            {
                                type: "Feature",
                                geometry: {
                                    type: "Point",
                                    coordinates: [109.488172, 30.272156],
                                },
                                properties: {
                                    id: 163,
                                    名称: "恩施州",
                                    地址: "恩施州",
                                    adcode_n: -1,
                                    adcode_p: -1,
                                    adcode_c: -1,
                                    adcode_d: -1,
                                    point_status: 0,
                                    创建时间: "2021-01-27 14:45:12",
                                    修改时间: "2021-01-27 14:45:12",
                                    人口: 329.03,
                                    GDP: 100,
                                    人均GDP: 10672,
                                    人均折美元: 1576,
                                },
                            },
                            {
                                type: "Feature",
                                geometry: {
                                    type: "Point",
                                    coordinates: [106.747477, 31.867903],
                                },
                                properties: {
                                    id: 164,
                                    名称: "巴中市",
                                    地址: "巴中市",
                                    adcode_n: -1,
                                    adcode_p: -1,
                                    adcode_c: -1,
                                    adcode_d: -1,
                                    point_status: 0,
                                    创建时间: "2021-01-27 14:45:12",
                                    修改时间: "2021-01-27 14:45:12",
                                    人口: 328.38,
                                    GDP: 5,
                                    人均GDP: 8554,
                                    人均折美元: 1264,
                                },
                            },
                            {
                                type: "Feature",
                                geometry: {
                                    type: "Point",
                                    coordinates: [105.724947, 34.580863],
                                },
                                properties: {
                                    id: 165,
                                    名称: "天水市",
                                    地址: "天水市",
                                    adcode_n: -1,
                                    adcode_p: -1,
                                    adcode_c: -1,
                                    adcode_d: -1,
                                    point_status: 0,
                                    创建时间: "2021-01-27 14:45:12",
                                    修改时间: "2021-01-27 14:45:12",
                                    人口: 326.25,
                                    GDP: 84,
                                    人均GDP: 9202,
                                    人均折美元: 1359,
                                },
                            },
                            {
                                type: "Feature",
                                geometry: {
                                    type: "Point",
                                    coordinates: [105.592898, 30.532847],
                                },
                                properties: {
                                    id: 166,
                                    名称: "遂宁市",
                                    地址: "遂宁市",
                                    adcode_n: -1,
                                    adcode_p: -1,
                                    adcode_c: -1,
                                    adcode_d: -1,
                                    point_status: 0,
                                    创建时间: "2021-01-27 14:45:12",
                                    修改时间: "2021-01-27 14:45:12",
                                    人口: 325.26,
                                    GDP: 101,
                                    人均GDP: 15226,
                                    人均折美元: 2249,
                                },
                            },
                            {
                                type: "Feature",
                                geometry: {
                                    type: "Point",
                                    coordinates: [112.752694, 37.687024],
                                },
                                properties: {
                                    id: 167,
                                    名称: "晋中市",
                                    地址: "晋中市",
                                    adcode_n: -1,
                                    adcode_p: -1,
                                    adcode_c: -1,
                                    adcode_d: -1,
                                    point_status: 0,
                                    创建时间: "2021-01-27 14:45:12",
                                    修改时间: "2021-01-27 14:45:12",
                                    人口: 324.94,
                                    GDP: 217,
                                    人均GDP: 23506,
                                    人均折美元: 3472,
                                },
                            },
                            {
                                type: "Feature",
                                geometry: {
                                    type: "Point",
                                    coordinates: [103.765568, 29.552106],
                                },
                                properties: {
                                    id: 168,
                                    名称: "乐山市",
                                    地址: "乐山市",
                                    adcode_n: -1,
                                    adcode_p: -1,
                                    adcode_c: -1,
                                    adcode_d: -1,
                                    point_status: 0,
                                    创建时间: "2021-01-27 14:45:12",
                                    修改时间: "2021-01-27 14:45:12",
                                    人口: 323.58,
                                    GDP: 16,
                                    人均GDP: 22991,
                                    人均折美元: 3396,
                                },
                            },
                            {
                                type: "Feature",
                                geometry: {
                                    type: "Point",
                                    coordinates: [107.522097, 26.254092],
                                },
                                properties: {
                                    id: 169,
                                    名称: "黔南州",
                                    地址: "黔南州",
                                    adcode_n: -1,
                                    adcode_p: -1,
                                    adcode_c: -1,
                                    adcode_d: -1,
                                    point_status: 0,
                                    创建时间: "2021-01-27 14:45:12",
                                    修改时间: "2021-01-27 14:45:12",
                                    人口: 323.12,
                                    GDP: 130,
                                    人均GDP: 11018,
                                    人均折美元: 1628,
                                },
                            },
                            {
                                type: "Feature",
                                geometry: {
                                    type: "Point",
                                    coordinates: [106.633212, 30.455961],
                                },
                                properties: {
                                    id: 170,
                                    名称: "广安市",
                                    地址: "广安市",
                                    adcode_n: -1,
                                    adcode_p: -1,
                                    adcode_c: -1,
                                    adcode_d: -1,
                                    point_status: 0,
                                    创建时间: "2021-01-27 14:45:12",
                                    修改时间: "2021-01-27 14:45:12",
                                    人口: 320.55,
                                    GDP: 16,
                                    人均GDP: 16759,
                                    人均折美元: 2476,
                                },
                            },
                            {
                                type: "Feature",
                                geometry: {
                                    type: "Point",
                                    coordinates: [117.389719, 32.916287],
                                },
                                properties: {
                                    id: 171,
                                    名称: "蚌埠市",
                                    地址: "蚌埠市",
                                    adcode_n: -1,
                                    adcode_p: -1,
                                    adcode_c: -1,
                                    adcode_d: -1,
                                    point_status: 0,
                                    创建时间: "2021-01-27 14:45:12",
                                    修改时间: "2021-01-27 14:45:12",
                                    人口: 316.4,
                                    GDP: 130,
                                    人均GDP: 20130,
                                    人均折美元: 2974,
                                },
                            },
                            {
                                type: "Feature",
                                geometry: {
                                    type: "Point",
                                    coordinates: [122.243444, 43.65289],
                                },
                                properties: {
                                    id: 172,
                                    名称: "通辽市",
                                    地址: "通辽市",
                                    adcode_n: -1,
                                    adcode_p: -1,
                                    adcode_c: -1,
                                    adcode_d: -1,
                                    point_status: 0,
                                    创建时间: "2021-01-27 14:45:12",
                                    修改时间: "2021-01-27 14:45:12",
                                    人口: 313.92,
                                    GDP: 18,
                                    人均GDP: 37470,
                                    人均折美元: 5535,
                                },
                            },
                            {
                                type: "Feature",
                                geometry: {
                                    type: "Point",
                                    coordinates: [121.127003, 41.095119],
                                },
                                properties: {
                                    id: 173,
                                    名称: "锦州市",
                                    地址: "锦州市",
                                    adcode_n: -1,
                                    adcode_p: -1,
                                    adcode_c: -1,
                                    adcode_d: -1,
                                    point_status: 0,
                                    创建时间: "2021-01-27 14:45:12",
                                    修改时间: "2021-01-27 14:45:12",
                                    人口: 312.65,
                                    GDP: 1157,
                                    人均GDP: 29298,
                                    人均折美元: 4328,
                                },
                            },
                            {
                                type: "Feature",
                                geometry: {
                                    type: "Point",
                                    coordinates: [113.392782, 22.517645],
                                },
                                properties: {
                                    id: 174,
                                    名称: "中山市",
                                    地址: "中山市",
                                    adcode_n: -1,
                                    adcode_p: -1,
                                    adcode_c: -1,
                                    adcode_d: -1,
                                    point_status: 0,
                                    创建时间: "2021-01-27 14:45:12",
                                    修改时间: "2021-01-27 14:45:12",
                                    人口: 312.09,
                                    GDP: 300,
                                    人均GDP: 58519,
                                    人均折美元: 8645,
                                },
                            },
                            {
                                type: "Feature",
                                geometry: {
                                    type: "Point",
                                    coordinates: [119.425836, 32.187849],
                                },
                                properties: {
                                    id: 175,
                                    名称: "镇江市",
                                    地址: "镇江市",
                                    adcode_n: -1,
                                    adcode_p: -1,
                                    adcode_c: -1,
                                    adcode_d: -1,
                                    point_status: 0,
                                    创建时间: "2021-01-27 14:45:12",
                                    修改时间: "2021-01-27 14:45:12",
                                    人口: 311.34,
                                    GDP: 300,
                                    人均GDP: 62858,
                                    人均折美元: 9285,
                                },
                            },
                            {
                                type: "Feature",
                                geometry: {
                                    type: "Point",
                                    coordinates: [87.616848, 43.825592],
                                },
                                properties: {
                                    id: 176,
                                    名称: "乌鲁木齐市",
                                    地址: "乌鲁木齐市",
                                    adcode_n: -1,
                                    adcode_p: -1,
                                    adcode_c: -1,
                                    adcode_d: -1,
                                    point_status: 0,
                                    创建时间: "2021-01-27 14:45:12",
                                    修改时间: "2021-01-27 14:45:12",
                                    人口: 311.03,
                                    GDP: 74,
                                    人均GDP: 42151,
                                    人均折美元: 6227,
                                },
                            },
                            {
                                type: "Feature",
                                geometry: {
                                    type: "Point",
                                    coordinates: [109.191551, 27.718346],
                                },
                                properties: {
                                    id: 177,
                                    名称: "铜仁地区",
                                    地址: "铜仁地区",
                                    adcode_n: -1,
                                    adcode_p: -1,
                                    adcode_c: -1,
                                    adcode_d: -1,
                                    point_status: 0,
                                    创建时间: "2021-01-27 14:45:12",
                                    修改时间: "2021-01-27 14:45:12",
                                    人口: 309.24,
                                    GDP: 100,
                                    人均GDP: 9601,
                                    人均折美元: 1418,
                                },
                            },
                            {
                                type: "Feature",
                                geometry: {
                                    type: "Point",
                                    coordinates: [108.654146, 21.979933],
                                },
                                properties: {
                                    id: 178,
                                    名称: "钦州市",
                                    地址: "钦州市",
                                    adcode_n: -1,
                                    adcode_p: -1,
                                    adcode_c: -1,
                                    adcode_d: -1,
                                    point_status: 0,
                                    创建时间: "2021-01-27 14:45:12",
                                    修改时间: "2021-01-27 14:45:12",
                                    人口: 307.97,
                                    GDP: 6,
                                    人均GDP: 16371,
                                    人均折美元: 2418,
                                },
                            },
                            {
                                type: "Feature",
                                geometry: {
                                    type: "Point",
                                    coordinates: [112.734174, 38.416663],
                                },
                                properties: {
                                    id: 179,
                                    名称: "忻州市",
                                    地址: "忻州市",
                                    adcode_n: -1,
                                    adcode_p: -1,
                                    adcode_c: -1,
                                    adcode_d: -1,
                                    point_status: 0,
                                    创建时间: "2021-01-27 14:45:12",
                                    修改时间: "2021-01-27 14:45:12",
                                    人口: 306.75,
                                    GDP: 50,
                                    人均GDP: 14194,
                                    人均折美元: 2097,
                                },
                            },
                            {
                                type: "Feature",
                                geometry: {
                                    type: "Point",
                                    coordinates: [120.450372, 41.573734],
                                },
                                properties: {
                                    id: 180,
                                    名称: "朝阳市",
                                    地址: "朝阳市",
                                    adcode_n: -1,
                                    adcode_p: -1,
                                    adcode_c: -1,
                                    adcode_d: -1,
                                    point_status: 0,
                                    创建时间: "2021-01-27 14:45:12",
                                    修改时间: "2021-01-27 14:45:12",
                                    人口: 304.46,
                                    GDP: 50,
                                    人均GDP: 21494,
                                    人均折美元: 3175,
                                },
                            },
                            {
                                type: "Feature",
                                geometry: {
                                    type: "Point",
                                    coordinates: [119.600492, 39.935385],
                                },
                                properties: {
                                    id: 181,
                                    名称: "秦皇岛市",
                                    地址: "秦皇岛市",
                                    adcode_n: -1,
                                    adcode_p: -1,
                                    adcode_c: -1,
                                    adcode_d: -1,
                                    point_status: 0,
                                    创建时间: "2021-01-27 14:45:12",
                                    修改时间: "2021-01-27 14:45:12",
                                    人口: 298.76,
                                    GDP: 659,
                                    人均GDP: 31145,
                                    人均折美元: 4601,
                                },
                            },
                            {
                                type: "Feature",
                                geometry: {
                                    type: "Point",
                                    coordinates: [114.700447, 23.743538],
                                },
                                properties: {
                                    id: 182,
                                    名称: "河源市",
                                    地址: "河源市",
                                    adcode_n: -1,
                                    adcode_p: -1,
                                    adcode_c: -1,
                                    adcode_d: -1,
                                    point_status: 0,
                                    创建时间: "2021-01-27 14:45:12",
                                    修改时间: "2021-01-27 14:45:12",
                                    人口: 295.3,
                                    GDP: 200,
                                    人均GDP: 16159,
                                    人均折美元: 2387,
                                },
                            },
                            {
                                type: "Feature",
                                geometry: {
                                    type: "Point",
                                    coordinates: [103.848538, 30.075439],
                                },
                                properties: {
                                    id: 183,
                                    名称: "眉山市",
                                    地址: "眉山市",
                                    adcode_n: -1,
                                    adcode_p: -1,
                                    adcode_c: -1,
                                    adcode_d: -1,
                                    point_status: 0,
                                    创建时间: "2021-01-27 14:45:12",
                                    修改时间: "2021-01-27 14:45:12",
                                    人口: 295.05,
                                    GDP: 338,
                                    人均GDP: 18717,
                                    人均折美元: 2765,
                                },
                            },
                            {
                                type: "Feature",
                                geometry: {
                                    type: "Point",
                                    coordinates: [115.375278, 22.786211],
                                },
                                properties: {
                                    id: 184,
                                    名称: "汕尾市",
                                    地址: "汕尾市",
                                    adcode_n: -1,
                                    adcode_p: -1,
                                    adcode_c: -1,
                                    adcode_d: -1,
                                    point_status: 0,
                                    创建时间: "2021-01-27 14:45:12",
                                    修改时间: "2021-01-27 14:45:12",
                                    人口: 293.57,
                                    GDP: 51,
                                    人均GDP: 16009,
                                    人均折美元: 2365,
                                },
                            },
                            {
                                type: "Feature",
                                geometry: {
                                    type: "Point",
                                    coordinates: [125.103784, 46.589309],
                                },
                                properties: {
                                    id: 185,
                                    名称: "大庆市",
                                    地址: "大庆市",
                                    adcode_n: -1,
                                    adcode_p: -1,
                                    adcode_c: -1,
                                    adcode_d: -1,
                                    point_status: 0,
                                    创建时间: "2021-01-27 14:45:12",
                                    修改时间: "2021-01-27 14:45:12",
                                    人口: 290.45,
                                    GDP: 600,
                                    人均GDP: 99847,
                                    人均折美元: 14750,
                                },
                            },
                            {
                                type: "Feature",
                                geometry: {
                                    type: "Point",
                                    coordinates: [120.086823, 30.894348],
                                },
                                properties: {
                                    id: 186,
                                    名称: "湖州市",
                                    地址: "湖州市",
                                    adcode_n: -1,
                                    adcode_p: -1,
                                    adcode_c: -1,
                                    adcode_d: -1,
                                    point_status: 0,
                                    创建时间: "2021-01-27 14:45:12",
                                    修改时间: "2021-01-27 14:45:12",
                                    人口: 289.35,
                                    GDP: 844,
                                    人均GDP: 44982,
                                    人均折美元: 6645,
                                },
                            },
                            {
                                type: "Feature",
                                geometry: {
                                    type: "Point",
                                    coordinates: [111.279115, 23.476962],
                                },
                                properties: {
                                    id: 187,
                                    名称: "梧州市",
                                    地址: "梧州市",
                                    adcode_n: -1,
                                    adcode_p: -1,
                                    adcode_c: -1,
                                    adcode_d: -1,
                                    point_status: 0,
                                    创建时间: "2021-01-27 14:45:12",
                                    修改时间: "2021-01-27 14:45:12",
                                    人口: 288.22,
                                    GDP: 34,
                                    人均GDP: 19905,
                                    人均折美元: 2940,
                                },
                            },
                            {
                                type: "Feature",
                                geometry: {
                                    type: "Point",
                                    coordinates: [124.825117, 45.141789],
                                },
                                properties: {
                                    id: 188,
                                    名称: "松原市",
                                    地址: "松原市",
                                    adcode_n: -1,
                                    adcode_p: -1,
                                    adcode_c: -1,
                                    adcode_d: -1,
                                    point_status: 0,
                                    创建时间: "2021-01-27 14:45:12",
                                    修改时间: "2021-01-27 14:45:12",
                                    人口: 288.11,
                                    GDP: 9,
                                    人均GDP: 38277,
                                    人均折美元: 5654,
                                },
                            },
                            {
                                type: "Feature",
                                geometry: {
                                    type: "Point",
                                    coordinates: [112.199265, 31.035423],
                                },
                                properties: {
                                    id: 189,
                                    名称: "荆门市",
                                    地址: "荆门市",
                                    adcode_n: -1,
                                    adcode_p: -1,
                                    adcode_c: -1,
                                    adcode_d: -1,
                                    point_status: 0,
                                    创建时间: "2021-01-27 14:45:12",
                                    修改时间: "2021-01-27 14:45:12",
                                    人口: 287.37,
                                    GDP: 196,
                                    人均GDP: 25405,
                                    人均折美元: 3753,
                                },
                            },
                            {
                                type: "Feature",
                                geometry: {
                                    type: "Point",
                                    coordinates: [111.74918, 40.842585],
                                },
                                properties: {
                                    id: 190,
                                    名称: "呼和浩特市",
                                    地址: "呼和浩特市",
                                    adcode_n: -1,
                                    adcode_p: -1,
                                    adcode_c: -1,
                                    adcode_d: -1,
                                    point_status: 0,
                                    创建时间: "2021-01-27 14:45:12",
                                    修改时间: "2021-01-27 14:45:12",
                                    人口: 286.66,
                                    GDP: 226,
                                    人均GDP: 65084,
                                    人均折美元: 9614,
                                },
                            },
                            {
                                type: "Feature",
                                geometry: {
                                    type: "Point",
                                    coordinates: [104.830359, 26.592666],
                                },
                                properties: {
                                    id: 191,
                                    名称: "六盘水市",
                                    地址: "六盘水市",
                                    adcode_n: -1,
                                    adcode_p: -1,
                                    adcode_c: -1,
                                    adcode_d: -1,
                                    point_status: 0,
                                    创建时间: "2021-01-27 14:45:12",
                                    修改时间: "2021-01-27 14:45:12",
                                    人口: 285.12,
                                    GDP: 200,
                                    人均GDP: 17559,
                                    人均折美元: 2594,
                                },
                            },
                            {
                                type: "Feature",
                                geometry: {
                                    type: "Point",
                                    coordinates: [113.597522, 24.810403],
                                },
                                properties: {
                                    id: 192,
                                    名称: "韶关市",
                                    地址: "韶关市",
                                    adcode_n: -1,
                                    adcode_p: -1,
                                    adcode_c: -1,
                                    adcode_d: -1,
                                    point_status: 0,
                                    创建时间: "2021-01-27 14:45:12",
                                    修改时间: "2021-01-27 14:45:12",
                                    人口: 282.66,
                                    GDP: 29,
                                    人均GDP: 24167,
                                    人均折美元: 3570,
                                },
                            },
                            {
                                type: "Feature",
                                geometry: {
                                    type: "Point",
                                    coordinates: [119.547932, 26.665617],
                                },
                                properties: {
                                    id: 193,
                                    名称: "宁德市",
                                    地址: "宁德市",
                                    adcode_n: -1,
                                    adcode_p: -1,
                                    adcode_c: -1,
                                    adcode_d: -1,
                                    point_status: 0,
                                    创建时间: "2021-01-27 14:45:12",
                                    修改时间: "2021-01-27 14:45:12",
                                    人口: 282.2,
                                    GDP: 514,
                                    人均GDP: 26097,
                                    人均折美元: 3855,
                                },
                            },
                            {
                                type: "Feature",
                                geometry: {
                                    type: "Point",
                                    coordinates: [104.906396, 25.087825],
                                },
                                properties: {
                                    id: 194,
                                    名称: "黔西南州",
                                    地址: "黔西南州",
                                    adcode_n: -1,
                                    adcode_p: -1,
                                    adcode_c: -1,
                                    adcode_d: -1,
                                    point_status: 0,
                                    创建时间: "2021-01-27 14:45:12",
                                    修改时间: "2021-01-27 14:45:12",
                                    人口: 280.59,
                                    GDP: 100,
                                    人均GDP: 11566,
                                    人均折美元: 1709,
                                },
                            },
                            {
                                type: "Feature",
                                geometry: {
                                    type: "Point",
                                    coordinates: [122.120419, 37.513068],
                                },
                                properties: {
                                    id: 195,
                                    名称: "威海市",
                                    地址: "威海市",
                                    adcode_n: -1,
                                    adcode_p: -1,
                                    adcode_c: -1,
                                    adcode_d: -1,
                                    point_status: 0,
                                    创建时间: "2021-01-27 14:45:12",
                                    修改时间: "2021-01-27 14:45:12",
                                    人口: 280.48,
                                    GDP: 410,
                                    人均GDP: 69335,
                                    人均折美元: 10242,
                                },
                            },
                            {
                                type: "Feature",
                                geometry: {
                                    type: "Point",
                                    coordinates: [119.526888, 35.416377],
                                },
                                properties: {
                                    id: 196,
                                    名称: "日照市",
                                    地址: "日照市",
                                    adcode_n: -1,
                                    adcode_p: -1,
                                    adcode_c: -1,
                                    adcode_d: -1,
                                    point_status: 0,
                                    创建时间: "2021-01-27 14:45:12",
                                    修改时间: "2021-01-27 14:45:12",
                                    人口: 280.11,
                                    GDP: 429,
                                    人均GDP: 36596,
                                    人均折美元: 5406,
                                },
                            },
                            {
                                type: "Feature",
                                geometry: {
                                    type: "Point",
                                    coordinates: [129.633168, 44.551653],
                                },
                                properties: {
                                    id: 197,
                                    名称: "牡丹江市",
                                    地址: "牡丹江市",
                                    adcode_n: -1,
                                    adcode_p: -1,
                                    adcode_c: -1,
                                    adcode_d: -1,
                                    point_status: 0,
                                    创建时间: "2021-01-27 14:45:12",
                                    修改时间: "2021-01-27 14:45:12",
                                    人口: 279.87,
                                    GDP: 7,
                                    人均GDP: 27906,
                                    人均折美元: 4122,
                                },
                            },
                            {
                                type: "Feature",
                                geometry: {
                                    type: "Point",
                                    coordinates: [119.007777, 25.454084],
                                },
                                properties: {
                                    id: 198,
                                    名称: "莆田市",
                                    地址: "莆田市",
                                    adcode_n: -1,
                                    adcode_p: -1,
                                    adcode_c: -1,
                                    adcode_d: -1,
                                    point_status: 0,
                                    创建时间: "2021-01-27 14:45:12",
                                    修改时间: "2021-01-27 14:45:12",
                                    人口: 277.85,
                                    GDP: 341,
                                    人均GDP: 29404,
                                    人均折美元: 4344,
                                },
                            },
                            {
                                type: "Feature",
                                geometry: {
                                    type: "Point",
                                    coordinates: [123.726166, 42.223769],
                                },
                                properties: {
                                    id: 199,
                                    名称: "铁岭市",
                                    地址: "铁岭市",
                                    adcode_n: -1,
                                    adcode_p: -1,
                                    adcode_c: -1,
                                    adcode_d: -1,
                                    point_status: 0,
                                    创建时间: "2021-01-27 14:45:12",
                                    修改时间: "2021-01-27 14:45:12",
                                    人口: 271.77,
                                    GDP: 62,
                                    人均GDP: 26570,
                                    人均折美元: 3925,
                                },
                            },
                            {
                                type: "Feature",
                                geometry: {
                                    type: "Point",
                                    coordinates: [104.626282, 35.580662],
                                },
                                properties: {
                                    id: 200,
                                    名称: "定西市",
                                    地址: "定西市",
                                    adcode_n: -1,
                                    adcode_p: -1,
                                    adcode_c: -1,
                                    adcode_d: -1,
                                    point_status: 0,
                                    创建时间: "2021-01-27 14:45:12",
                                    修改时间: "2021-01-27 14:45:12",
                                    人口: 269.86,
                                    GDP: 61,
                                    人均GDP: 5781,
                                    人均折美元: 854,
                                },
                            },
                            {
                                type: "Feature",
                                geometry: {
                                    type: "Point",
                                    coordinates: [101.528068, 25.045532],
                                },
                                properties: {
                                    id: 201,
                                    名称: "楚雄州",
                                    地址: "楚雄州",
                                    adcode_n: -1,
                                    adcode_p: -1,
                                    adcode_c: -1,
                                    adcode_d: -1,
                                    point_status: 0,
                                    创建时间: "2021-01-27 14:45:12",
                                    修改时间: "2021-01-27 14:45:12",
                                    人口: 268.4,
                                    GDP: 200,
                                    人均GDP: 15069,
                                    人均折美元: 2226,
                                },
                            },
                            {
                                type: "Feature",
                                geometry: {
                                    type: "Point",
                                    coordinates: [104.778442, 29.33903],
                                },
                                properties: {
                                    id: 202,
                                    名称: "自贡市",
                                    地址: "自贡市",
                                    adcode_n: -1,
                                    adcode_p: -1,
                                    adcode_c: -1,
                                    adcode_d: -1,
                                    point_status: 0,
                                    创建时间: "2021-01-27 14:45:12",
                                    修改时间: "2021-01-27 14:45:12",
                                    人口: 267.89,
                                    GDP: 26,
                                    人均GDP: 24179,
                                    人均折美元: 3572,
                                },
                            },
                            {
                                type: "Feature",
                                geometry: {
                                    type: "Point",
                                    coordinates: [116.622603, 23.65695],
                                },
                                properties: {
                                    id: 203,
                                    名称: "潮州市",
                                    地址: "潮州市",
                                    adcode_n: -1,
                                    adcode_p: -1,
                                    adcode_c: -1,
                                    adcode_d: -1,
                                    point_status: 0,
                                    创建时间: "2021-01-27 14:45:12",
                                    修改时间: "2021-01-27 14:45:12",
                                    人口: 266.98,
                                    GDP: 4,
                                    人均GDP: 20945,
                                    人均折美元: 3094,
                                },
                            },
                            {
                                type: "Feature",
                                geometry: {
                                    type: "Point",
                                    coordinates: [109.840347, 40.657449],
                                },
                                properties: {
                                    id: 204,
                                    名称: "包头市",
                                    地址: "包头市",
                                    adcode_n: -1,
                                    adcode_p: -1,
                                    adcode_c: -1,
                                    adcode_d: -1,
                                    point_status: 0,
                                    创建时间: "2021-01-27 14:45:12",
                                    修改时间: "2021-01-27 14:45:12",
                                    人口: 265.04,
                                    GDP: 171,
                                    人均GDP: 92848,
                                    人均折美元: 13716,
                                },
                            },
                            {
                                type: "Feature",
                                geometry: {
                                    type: "Point",
                                    coordinates: [118.081325, 27.382829],
                                },
                                properties: {
                                    id: 205,
                                    名称: "南平市",
                                    地址: "南平市",
                                    adcode_n: -1,
                                    adcode_p: -1,
                                    adcode_c: -1,
                                    adcode_d: -1,
                                    point_status: 0,
                                    创建时间: "2021-01-27 14:45:12",
                                    修改时间: "2021-01-27 14:45:12",
                                    人口: 264.55,
                                    GDP: 427,
                                    人均GDP: 27546,
                                    人均折美元: 4069,
                                },
                            },
                            {
                                type: "Feature",
                                geometry: {
                                    type: "Point",
                                    coordinates: [109.029022, 32.684714],
                                },
                                properties: {
                                    id: 206,
                                    名称: "安康市",
                                    地址: "安康市",
                                    adcode_n: -1,
                                    adcode_p: -1,
                                    adcode_c: -1,
                                    adcode_d: -1,
                                    point_status: 0,
                                    创建时间: "2021-01-27 14:45:12",
                                    修改时间: "2021-01-27 14:45:12",
                                    人口: 262.99,
                                    GDP: 238,
                                    人均GDP: 12436,
                                    人均折美元: 1837,
                                },
                            },
                            {
                                type: "Feature",
                                geometry: {
                                    type: "Point",
                                    coordinates: [120.836932, 40.711052],
                                },
                                properties: {
                                    id: 207,
                                    名称: "葫芦岛市",
                                    地址: "葫芦岛市",
                                    adcode_n: -1,
                                    adcode_p: -1,
                                    adcode_c: -1,
                                    adcode_d: -1,
                                    point_status: 0,
                                    创建时间: "2021-01-27 14:45:12",
                                    修改时间: "2021-01-27 14:45:12",
                                    人口: 262.35,
                                    GDP: 79,
                                    人均GDP: 20255,
                                    人均折美元: 2992,
                                },
                            },
                            {
                                type: "Feature",
                                geometry: {
                                    type: "Point",
                                    coordinates: [104.921841, 33.400684],
                                },
                                properties: {
                                    id: 208,
                                    名称: "陇南市",
                                    地址: "陇南市",
                                    adcode_n: -1,
                                    adcode_p: -1,
                                    adcode_c: -1,
                                    adcode_d: -1,
                                    point_status: 0,
                                    创建时间: "2021-01-27 14:45:12",
                                    修改时间: "2021-01-27 14:45:12",
                                    人口: 256.77,
                                    GDP: 12,
                                    人均GDP: 6597,
                                    人均折美元: 975,
                                },
                            },
                            {
                                type: "Feature",
                                geometry: {
                                    type: "Point",
                                    coordinates: [117.017536, 25.075123],
                                },
                                properties: {
                                    id: 209,
                                    名称: "龙岩市",
                                    地址: "龙岩市",
                                    adcode_n: -1,
                                    adcode_p: -1,
                                    adcode_c: -1,
                                    adcode_d: -1,
                                    point_status: 0,
                                    创建时间: "2021-01-27 14:45:12",
                                    修改时间: "2021-01-27 14:45:12",
                                    人口: 255.95,
                                    GDP: 390,
                                    人均GDP: 38737,
                                    人均折美元: 5722,
                                },
                            },
                            {
                                type: "Feature",
                                geometry: {
                                    type: "Point",
                                    coordinates: [130.318917, 46.799922],
                                },
                                properties: {
                                    id: 210,
                                    名称: "佳木斯市",
                                    地址: "佳木斯市",
                                    adcode_n: -1,
                                    adcode_p: -1,
                                    adcode_c: -1,
                                    adcode_d: -1,
                                    point_status: 0,
                                    创建时间: "2021-01-27 14:45:12",
                                    修改时间: "2021-01-27 14:45:12",
                                    人口: 255.21,
                                    GDP: 12,
                                    人均GDP: 20082,
                                    人均折美元: 2966,
                                },
                            },
                            {
                                type: "Feature",
                                geometry: {
                                    type: "Point",
                                    coordinates: [119.765744, 49.211574],
                                },
                                properties: {
                                    id: 211,
                                    名称: "呼伦贝尔市",
                                    地址: "呼伦贝尔市",
                                    adcode_n: -1,
                                    adcode_p: -1,
                                    adcode_c: -1,
                                    adcode_d: -1,
                                    point_status: 0,
                                    创建时间: "2021-01-27 14:45:12",
                                    修改时间: "2021-01-27 14:45:12",
                                    人口: 254.93,
                                    GDP: 4,
                                    人均GDP: 36560,
                                    人均折美元: 5401,
                                },
                            },
                            {
                                type: "Feature",
                                geometry: {
                                    type: "Point",
                                    coordinates: [109.739172, 28.311947],
                                },
                                properties: {
                                    id: 212,
                                    名称: "湘西州",
                                    地址: "湘西州",
                                    adcode_n: -1,
                                    adcode_p: -1,
                                    adcode_c: -1,
                                    adcode_d: -1,
                                    point_status: 0,
                                    创建时间: "2021-01-27 14:45:12",
                                    修改时间: "2021-01-27 14:45:12",
                                    人口: 254.78,
                                    GDP: 200,
                                    人均GDP: 11910,
                                    人均折美元: 1759,
                                },
                            },
                            {
                                type: "Feature",
                                geometry: {
                                    type: "Point",
                                    coordinates: [114.016539, 33.581412],
                                },
                                properties: {
                                    id: 213,
                                    名称: "漯河市",
                                    地址: "漯河市",
                                    adcode_n: -1,
                                    adcode_p: -1,
                                    adcode_c: -1,
                                    adcode_d: -1,
                                    point_status: 0,
                                    创建时间: "2021-01-27 14:45:12",
                                    修改时间: "2021-01-27 14:45:12",
                                    人口: 254.41,
                                    GDP: 119,
                                    人均GDP: 26807,
                                    人均折美元: 3960,
                                },
                            },
                            {
                                type: "Feature",
                                geometry: {
                                    type: "Point",
                                    coordinates: [100.966512, 22.825065],
                                },
                                properties: {
                                    id: 214,
                                    名称: "普洱市",
                                    地址: "普洱市",
                                    adcode_n: -1,
                                    adcode_p: -1,
                                    adcode_c: -1,
                                    adcode_d: -1,
                                    point_status: 0,
                                    创建时间: "2021-01-27 14:45:12",
                                    修改时间: "2021-01-27 14:45:12",
                                    人口: 254.3,
                                    GDP: 26,
                                    人均GDP: 9725,
                                    人均折美元: 1437,
                                },
                            },
                            {
                                type: "Feature",
                                geometry: {
                                    type: "Point",
                                    coordinates: [118.758816, 30.940718],
                                },
                                properties: {
                                    id: 215,
                                    名称: "宣城市",
                                    地址: "宣城市",
                                    adcode_n: -1,
                                    adcode_p: -1,
                                    adcode_c: -1,
                                    adcode_d: -1,
                                    point_status: 0,
                                    创建时间: "2021-01-27 14:45:12",
                                    修改时间: "2021-01-27 14:45:12",
                                    人口: 253.3,
                                    GDP: 193,
                                    人均GDP: 20754,
                                    人均折美元: 3066,
                                },
                            },
                            {
                                type: "Feature",
                                geometry: {
                                    type: "Point",
                                    coordinates: [99.161761, 25.112046],
                                },
                                properties: {
                                    id: 216,
                                    名称: "保山市",
                                    地址: "保山市",
                                    adcode_n: -1,
                                    adcode_p: -1,
                                    adcode_c: -1,
                                    adcode_d: -1,
                                    point_status: 0,
                                    创建时间: "2021-01-27 14:45:12",
                                    修改时间: "2021-01-27 14:45:12",
                                    人口: 250.6,
                                    GDP: 38,
                                    人均GDP: 10411,
                                    人均折美元: 1538,
                                },
                            },
                            {
                                type: "Feature",
                                geometry: {
                                    type: "Point",
                                    coordinates: [117.638678, 26.263406],
                                },
                                properties: {
                                    id: 217,
                                    名称: "三明市",
                                    地址: "三明市",
                                    adcode_n: -1,
                                    adcode_p: -1,
                                    adcode_c: -1,
                                    adcode_d: -1,
                                    point_status: 0,
                                    创建时间: "2021-01-27 14:45:12",
                                    修改时间: "2021-01-27 14:45:12",
                                    人口: 250.34,
                                    GDP: 445,
                                    人均GDP: 38856,
                                    人均折美元: 5740,
                                },
                            },
                            {
                                type: "Feature",
                                geometry: {
                                    type: "Point",
                                    coordinates: [105.843357, 32.435435],
                                },
                                properties: {
                                    id: 218,
                                    名称: "广元市",
                                    地址: "广元市",
                                    adcode_n: -1,
                                    adcode_p: -1,
                                    adcode_c: -1,
                                    adcode_d: -1,
                                    point_status: 0,
                                    创建时间: "2021-01-27 14:45:12",
                                    修改时间: "2021-01-27 14:45:12",
                                    人口: 248.41,
                                    GDP: 334,
                                    人均GDP: 12957,
                                    人均折美元: 1914,
                                },
                            },
                            {
                                type: "Feature",
                                geometry: {
                                    type: "Point",
                                    coordinates: [81.324136, 43.916823],
                                },
                                properties: {
                                    id: 219,
                                    名称: "伊犁州",
                                    地址: "伊犁州",
                                    adcode_n: -1,
                                    adcode_p: -1,
                                    adcode_c: -1,
                                    adcode_d: -1,
                                    point_status: 0,
                                    创建时间: "2021-01-27 14:45:12",
                                    修改时间: "2021-01-27 14:45:12",
                                    人口: 248.26,
                                    GDP: 200,
                                    人均GDP: 35904,
                                    人均折美元: 5304,
                                },
                            },
                            {
                                type: "Feature",
                                geometry: {
                                    type: "Point",
                                    coordinates: [114.322492, 29.841443],
                                },
                                properties: {
                                    id: 220,
                                    名称: "咸宁市",
                                    地址: "咸宁市",
                                    adcode_n: -1,
                                    adcode_p: -1,
                                    adcode_c: -1,
                                    adcode_d: -1,
                                    point_status: 0,
                                    创建时间: "2021-01-27 14:45:12",
                                    修改时间: "2021-01-27 14:45:12",
                                    人口: 246.26,
                                    GDP: 219,
                                    人均GDP: 21129,
                                    人均折美元: 3121,
                                },
                            },
                            {
                                type: "Feature",
                                geometry: {
                                    type: "Point",
                                    coordinates: [124.354706, 40.000499],
                                },
                                properties: {
                                    id: 221,
                                    名称: "丹东市",
                                    地址: "丹东市",
                                    adcode_n: -1,
                                    adcode_p: -1,
                                    adcode_c: -1,
                                    adcode_d: -1,
                                    point_status: 0,
                                    创建时间: "2021-01-27 14:45:12",
                                    修改时间: "2021-01-27 14:45:12",
                                    人口: 244.47,
                                    GDP: 13,
                                    人均GDP: 29816,
                                    人均折美元: 4404,
                                },
                            },
                            {
                                type: "Feature",
                                geometry: {
                                    type: "Point",
                                    coordinates: [100.079583, 23.877573],
                                },
                                properties: {
                                    id: 222,
                                    名称: "临沧市",
                                    地址: "临沧市",
                                    adcode_n: -1,
                                    adcode_p: -1,
                                    adcode_c: -1,
                                    adcode_d: -1,
                                    point_status: 0,
                                    创建时间: "2021-01-27 14:45:12",
                                    修改时间: "2021-01-27 14:45:12",
                                    人口: 243,
                                    GDP: 20,
                                    人均GDP: 8984,
                                    人均折美元: 1327,
                                },
                            },
                            {
                                type: "Feature",
                                geometry: {
                                    type: "Point",
                                    coordinates: [115.03852, 30.199652],
                                },
                                properties: {
                                    id: 223,
                                    名称: "黄石市",
                                    地址: "黄石市",
                                    adcode_n: -1,
                                    adcode_p: -1,
                                    adcode_c: -1,
                                    adcode_d: -1,
                                    point_status: 0,
                                    创建时间: "2021-01-27 14:45:12",
                                    修改时间: "2021-01-27 14:45:12",
                                    人口: 242.93,
                                    GDP: 149,
                                    人均GDP: 28280,
                                    人均折美元: 4177,
                                },
                            },
                            {
                                type: "Feature",
                                geometry: {
                                    type: "Point",
                                    coordinates: [122.235417, 40.667012],
                                },
                                properties: {
                                    id: 224,
                                    名称: "营口市",
                                    地址: "营口市",
                                    adcode_n: -1,
                                    adcode_p: -1,
                                    adcode_c: -1,
                                    adcode_d: -1,
                                    point_status: 0,
                                    创建时间: "2021-01-27 14:45:12",
                                    修改时间: "2021-01-27 14:45:12",
                                    人口: 242.85,
                                    GDP: 71,
                                    人均GDP: 41276,
                                    人均折美元: 6097,
                                },
                            },
                            {
                                type: "Feature",
                                geometry: {
                                    type: "Point",
                                    coordinates: [111.982232, 21.857958],
                                },
                                properties: {
                                    id: 225,
                                    名称: "阳江市",
                                    地址: "阳江市",
                                    adcode_n: -1,
                                    adcode_p: -1,
                                    adcode_c: -1,
                                    adcode_d: -1,
                                    point_status: 0,
                                    创建时间: "2021-01-27 14:45:12",
                                    修改时间: "2021-01-27 14:45:12",
                                    人口: 242.18,
                                    GDP: 67,
                                    人均GDP: 26501,
                                    人均折美元: 3915,
                                },
                            },
                            {
                                type: "Feature",
                                geometry: {
                                    type: "Point",
                                    coordinates: [80.260605, 41.168779],
                                },
                                properties: {
                                    id: 226,
                                    名称: "阿克苏地区",
                                    地址: "阿克苏地区",
                                    adcode_n: -1,
                                    adcode_p: -1,
                                    adcode_c: -1,
                                    adcode_d: -1,
                                    point_status: 0,
                                    创建时间: "2021-01-27 14:45:12",
                                    修改时间: "2021-01-27 14:45:12",
                                    人口: 237.09,
                                    GDP: 50,
                                    人均GDP: 25573,
                                    人均折美元: 3778,
                                },
                            },
                            {
                                type: "Feature",
                                geometry: {
                                    type: "Point",
                                    coordinates: [112.044491, 22.915094],
                                },
                                properties: {
                                    id: 227,
                                    名称: "云浮市",
                                    地址: "云浮市",
                                    adcode_n: -1,
                                    adcode_p: -1,
                                    adcode_c: -1,
                                    adcode_d: -1,
                                    point_status: 0,
                                    创建时间: "2021-01-27 14:45:12",
                                    修改时间: "2021-01-27 14:45:12",
                                    人口: 236.01,
                                    GDP: 40,
                                    人均GDP: 16705,
                                    人均折美元: 2468,
                                },
                            },
                            {
                                type: "Feature",
                                geometry: {
                                    type: "Point",
                                    coordinates: [109.940477, 33.870422],
                                },
                                properties: {
                                    id: 228,
                                    名称: "商洛市",
                                    地址: "商洛市",
                                    adcode_n: -1,
                                    adcode_p: -1,
                                    adcode_c: -1,
                                    adcode_d: -1,
                                    point_status: 0,
                                    创建时间: "2021-01-27 14:45:12",
                                    修改时间: "2021-01-27 14:45:12",
                                    人口: 234.17,
                                    GDP: 101,
                                    人均GDP: 12209,
                                    人均折美元: 1804,
                                },
                            },
                            {
                                type: "Feature",
                                geometry: {
                                    type: "Point",
                                    coordinates: [116.999932, 32.625478],
                                },
                                properties: {
                                    id: 229,
                                    名称: "淮南市",
                                    地址: "淮南市",
                                    adcode_n: -1,
                                    adcode_p: -1,
                                    adcode_c: -1,
                                    adcode_d: -1,
                                    point_status: 0,
                                    创建时间: "2021-01-27 14:45:12",
                                    修改时间: "2021-01-27 14:45:12",
                                    人口: 233.4,
                                    GDP: 117,
                                    人均GDP: 25857,
                                    人均折美元: 3820,
                                },
                            },
                            {
                                type: "Feature",
                                geometry: {
                                    type: "Point",
                                    coordinates: [125.939697, 41.728401],
                                },
                                properties: {
                                    id: 230,
                                    名称: "通化市",
                                    地址: "通化市",
                                    adcode_n: -1,
                                    adcode_p: -1,
                                    adcode_c: -1,
                                    adcode_d: -1,
                                    point_status: 0,
                                    创建时间: "2021-01-27 14:45:12",
                                    修改时间: "2021-01-27 14:45:12",
                                    人口: 232.52,
                                    GDP: 300,
                                    人均GDP: 26969,
                                    人均折美元: 3984,
                                },
                            },
                            {
                                type: "Feature",
                                geometry: {
                                    type: "Point",
                                    coordinates: [102.546543, 24.352036],
                                },
                                properties: {
                                    id: 231,
                                    名称: "玉溪市",
                                    地址: "玉溪市",
                                    adcode_n: -1,
                                    adcode_p: -1,
                                    adcode_c: -1,
                                    adcode_d: -1,
                                    point_status: 0,
                                    创建时间: "2021-01-27 14:45:12",
                                    修改时间: "2021-01-27 14:45:12",
                                    人口: 230.4,
                                    GDP: 64,
                                    人均GDP: 31966,
                                    人均折美元: 4722,
                                },
                            },
                            {
                                type: "Feature",
                                geometry: {
                                    type: "Point",
                                    coordinates: [105.947593, 26.253072],
                                },
                                properties: {
                                    id: 232,
                                    名称: "安顺市",
                                    地址: "安顺市",
                                    adcode_n: -1,
                                    adcode_p: -1,
                                    adcode_c: -1,
                                    adcode_d: -1,
                                    point_status: 0,
                                    创建时间: "2021-01-27 14:45:12",
                                    修改时间: "2021-01-27 14:45:12",
                                    人口: 229.73,
                                    GDP: 100,
                                    人均GDP: 10139,
                                    人均折美元: 1498,
                                },
                            },
                            {
                                type: "Feature",
                                geometry: {
                                    type: "Point",
                                    coordinates: [112.851831, 35.490701],
                                },
                                properties: {
                                    id: 233,
                                    名称: "晋城市",
                                    地址: "晋城市",
                                    adcode_n: -1,
                                    adcode_p: -1,
                                    adcode_c: -1,
                                    adcode_d: -1,
                                    point_status: 0,
                                    创建时间: "2021-01-27 14:45:12",
                                    修改时间: "2021-01-27 14:45:12",
                                    人口: 227.92,
                                    GDP: 200,
                                    人均GDP: 32051,
                                    人均折美元: 4735,
                                },
                            },
                            {
                                type: "Feature",
                                geometry: {
                                    type: "Point",
                                    coordinates: [129.508946, 42.891253],
                                },
                                properties: {
                                    id: 234,
                                    名称: "延边州",
                                    地址: "延边州",
                                    adcode_n: -1,
                                    adcode_p: -1,
                                    adcode_c: -1,
                                    adcode_d: -1,
                                    point_status: 0,
                                    创建时间: "2021-01-27 14:45:12",
                                    修改时间: "2021-01-27 14:45:12",
                                    人口: 227.16,
                                    GDP: 545,
                                    人均GDP: 23992,
                                    人均折美元: 3544,
                                },
                            },
                            {
                                type: "Feature",
                                geometry: {
                                    type: "Point",
                                    coordinates: [118.432941, 31.352859],
                                },
                                properties: {
                                    id: 235,
                                    名称: "芜湖市",
                                    地址: "芜湖市",
                                    adcode_n: -1,
                                    adcode_p: -1,
                                    adcode_c: -1,
                                    adcode_d: -1,
                                    point_status: 0,
                                    创建时间: "2021-01-27 14:45:12",
                                    修改时间: "2021-01-27 14:45:12",
                                    人口: 226.3,
                                    GDP: 517,
                                    人均GDP: 48989,
                                    人均折美元: 7237,
                                },
                            },
                            {
                                type: "Feature",
                                geometry: {
                                    type: "Point",
                                    coordinates: [111.200135, 34.772493],
                                },
                                properties: {
                                    id: 236,
                                    名称: "三门峡市",
                                    地址: "三门峡市",
                                    adcode_n: -1,
                                    adcode_p: -1,
                                    adcode_c: -1,
                                    adcode_d: -1,
                                    point_status: 0,
                                    创建时间: "2021-01-27 14:45:12",
                                    修改时间: "2021-01-27 14:45:12",
                                    人口: 223.39,
                                    GDP: 82,
                                    人均GDP: 39142,
                                    人均折美元: 5782,
                                },
                            },
                            {
                                type: "Feature",
                                geometry: {
                                    type: "Point",
                                    coordinates: [107.643631, 35.709077],
                                },
                                properties: {
                                    id: 237,
                                    名称: "庆阳市",
                                    地址: "庆阳市",
                                    adcode_n: -1,
                                    adcode_p: -1,
                                    adcode_c: -1,
                                    adcode_d: -1,
                                    point_status: 0,
                                    创建时间: "2021-01-27 14:45:12",
                                    修改时间: "2021-01-27 14:45:12",
                                    人口: 221.12,
                                    GDP: 61,
                                    人均GDP: 16173,
                                    人均折美元: 2389,
                                },
                            },
                            {
                                type: "Feature",
                                geometry: {
                                    type: "Point",
                                    coordinates: [101.778228, 36.617144],
                                },
                                properties: {
                                    id: 238,
                                    名称: "西宁市",
                                    地址: "西宁市",
                                    adcode_n: -1,
                                    adcode_p: -1,
                                    adcode_c: -1,
                                    adcode_d: -1,
                                    point_status: 0,
                                    创建时间: "2021-01-27 14:45:12",
                                    修改时间: "2021-01-27 14:45:12",
                                    人口: 220.87,
                                    GDP: 22,
                                    人均GDP: 28446,
                                    人均折美元: 4202,
                                },
                            },
                            {
                                type: "Feature",
                                geometry: {
                                    type: "Point",
                                    coordinates: [109.489727, 36.585455],
                                },
                                properties: {
                                    id: 239,
                                    名称: "延安市",
                                    地址: "延安市",
                                    adcode_n: -1,
                                    adcode_p: -1,
                                    adcode_c: -1,
                                    adcode_d: -1,
                                    point_status: 0,
                                    创建时间: "2021-01-27 14:45:12",
                                    修改时间: "2021-01-27 14:45:12",
                                    人口: 218.7,
                                    GDP: 139,
                                    人均GDP: 40485,
                                    人均折美元: 5981,
                                },
                            },
                            {
                                type: "Feature",
                                geometry: {
                                    type: "Point",
                                    coordinates: [113.382458, 31.690215],
                                },
                                properties: {
                                    id: 240,
                                    名称: "随州市",
                                    地址: "随州市",
                                    adcode_n: -1,
                                    adcode_p: -1,
                                    adcode_c: -1,
                                    adcode_d: -1,
                                    point_status: 0,
                                    创建时间: "2021-01-27 14:45:12",
                                    修改时间: "2021-01-27 14:45:12",
                                    人口: 216.22,
                                    GDP: 164,
                                    人均GDP: 18576,
                                    人均折美元: 2744,
                                },
                            },
                            {
                                type: "Feature",
                                geometry: {
                                    type: "Point",
                                    coordinates: [113.132585, 40.994785],
                                },
                                properties: {
                                    id: 241,
                                    名称: "乌兰察布市",
                                    地址: "乌兰察布市",
                                    adcode_n: -1,
                                    adcode_p: -1,
                                    adcode_c: -1,
                                    adcode_d: -1,
                                    point_status: 0,
                                    创建时间: "2021-01-27 14:45:12",
                                    修改时间: "2021-01-27 14:45:12",
                                    人口: 214.36,
                                    GDP: 32,
                                    人均GDP: 25658,
                                    人均折美元: 3790,
                                },
                            },
                            {
                                type: "Feature",
                                geometry: {
                                    type: "Point",
                                    coordinates: [123.957208, 41.880872],
                                },
                                properties: {
                                    id: 242,
                                    名称: "抚顺市",
                                    地址: "抚顺市",
                                    adcode_n: -1,
                                    adcode_p: -1,
                                    adcode_c: -1,
                                    adcode_d: -1,
                                    point_status: 0,
                                    创建时间: "2021-01-27 14:45:12",
                                    修改时间: "2021-01-27 14:45:12",
                                    人口: 213.81,
                                    GDP: 41,
                                    人均GDP: 41635,
                                    人均折美元: 6150,
                                },
                            },
                            {
                                type: "Feature",
                                geometry: {
                                    type: "Point",
                                    coordinates: [118.859457, 28.970079],
                                },
                                properties: {
                                    id: 243,
                                    名称: "衢州市",
                                    地址: "衢州市",
                                    adcode_n: -1,
                                    adcode_p: -1,
                                    adcode_c: -1,
                                    adcode_d: -1,
                                    point_status: 0,
                                    创建时间: "2021-01-27 14:45:12",
                                    修改时间: "2021-01-27 14:45:12",
                                    人口: 212.27,
                                    GDP: 591,
                                    人均GDP: 35463,
                                    人均折美元: 5239,
                                },
                            },
                            {
                                type: "Feature",
                                geometry: {
                                    type: "Point",
                                    coordinates: [119.922796, 28.46763],
                                },
                                properties: {
                                    id: 244,
                                    名称: "丽水市",
                                    地址: "丽水市",
                                    adcode_n: -1,
                                    adcode_p: -1,
                                    adcode_c: -1,
                                    adcode_d: -1,
                                    point_status: 0,
                                    创建时间: "2021-01-27 14:45:12",
                                    修改时间: "2021-01-27 14:45:12",
                                    人口: 211.7,
                                    GDP: 256,
                                    人均GDP: 30422,
                                    人均折美元: 4494,
                                },
                            },
                            {
                                type: "Feature",
                                geometry: {
                                    type: "Point",
                                    coordinates: [116.798265, 33.955844],
                                },
                                properties: {
                                    id: 245,
                                    名称: "淮北市",
                                    地址: "淮北市",
                                    adcode_n: -1,
                                    adcode_p: -1,
                                    adcode_c: -1,
                                    adcode_d: -1,
                                    point_status: 0,
                                    创建时间: "2021-01-27 14:45:12",
                                    修改时间: "2021-01-27 14:45:12",
                                    人口: 211.4,
                                    GDP: 47,
                                    人均GDP: 21835,
                                    人均折美元: 3226,
                                },
                            },
                            {
                                type: "Feature",
                                geometry: {
                                    type: "Point",
                                    coordinates: [109.221465, 23.750306],
                                },
                                properties: {
                                    id: 246,
                                    名称: "来宾市",
                                    地址: "来宾市",
                                    adcode_n: -1,
                                    adcode_p: -1,
                                    adcode_c: -1,
                                    adcode_d: -1,
                                    point_status: 0,
                                    创建时间: "2021-01-27 14:45:12",
                                    修改时间: "2021-01-27 14:45:12",
                                    人口: 209.97,
                                    GDP: 39,
                                    人均GDP: 18347,
                                    人均折美元: 2710,
                                },
                            },
                            {
                                type: "Feature",
                                geometry: {
                                    type: "Point",
                                    coordinates: [106.66524, 35.543051],
                                },
                                properties: {
                                    id: 247,
                                    名称: "平凉市",
                                    地址: "平凉市",
                                    adcode_n: -1,
                                    adcode_p: -1,
                                    adcode_c: -1,
                                    adcode_d: -1,
                                    point_status: 0,
                                    创建时间: "2021-01-27 14:45:12",
                                    修改时间: "2021-01-27 14:45:12",
                                    人口: 206.8,
                                    GDP: 77,
                                    人均GDP: 11213,
                                    人均折美元: 1656,
                                },
                            },
                            {
                                type: "Feature",
                                geometry: {
                                    type: "Point",
                                    coordinates: [110.198293, 20.044001],
                                },
                                properties: {
                                    id: 248,
                                    名称: "海口市",
                                    地址: "海口市",
                                    adcode_n: -1,
                                    adcode_p: -1,
                                    adcode_c: -1,
                                    adcode_d: -1,
                                    point_status: 0,
                                    创建时间: "2021-01-27 14:45:12",
                                    修改时间: "2021-01-27 14:45:12",
                                    人口: 204.62,
                                    GDP: 1386,
                                    人均GDP: 28861,
                                    人均折美元: 4263,
                                },
                            },
                            {
                                type: "Feature",
                                geometry: {
                                    type: "Point",
                                    coordinates: [118.674767, 37.434751],
                                },
                                properties: {
                                    id: 249,
                                    名称: "东营市",
                                    地址: "东营市",
                                    adcode_n: -1,
                                    adcode_p: -1,
                                    adcode_c: -1,
                                    adcode_d: -1,
                                    point_status: 0,
                                    创建时间: "2021-01-27 14:45:12",
                                    修改时间: "2021-01-27 14:45:12",
                                    人口: 203.53,
                                    GDP: 599,
                                    人均GDP: 115950,
                                    人均折美元: 17128,
                                },
                            },
                            {
                                type: "Feature",
                                geometry: {
                                    type: "Point",
                                    coordinates: [122.839024, 45.619641],
                                },
                                properties: {
                                    id: 250,
                                    名称: "白城市",
                                    地址: "白城市",
                                    adcode_n: -1,
                                    adcode_p: -1,
                                    adcode_c: -1,
                                    adcode_d: -1,
                                    point_status: 0,
                                    创建时间: "2021-01-27 14:45:12",
                                    修改时间: "2021-01-27 14:45:12",
                                    人口: 203.31,
                                    GDP: 31,
                                    人均GDP: 21716,
                                    人均折美元: 3208,
                                },
                            },
                            {
                                type: "Feature",
                                geometry: {
                                    type: "Point",
                                    coordinates: [79.922211, 37.114157],
                                },
                                properties: {
                                    id: 251,
                                    名称: "和田地区",
                                    地址: "和田地区",
                                    adcode_n: -1,
                                    adcode_p: -1,
                                    adcode_c: -1,
                                    adcode_d: -1,
                                    point_status: 0,
                                    创建时间: "2021-01-27 14:45:12",
                                    修改时间: "2021-01-27 14:45:12",
                                    人口: 201.44,
                                    GDP: 32,
                                    人均GDP: 4994,
                                    人均折美元: 738,
                                },
                            },
                            {
                                type: "Feature",
                                geometry: {
                                    type: "Point",
                                    coordinates: [107.364711, 22.376532],
                                },
                                properties: {
                                    id: 252,
                                    名称: "崇左市",
                                    地址: "崇左市",
                                    adcode_n: -1,
                                    adcode_p: -1,
                                    adcode_c: -1,
                                    adcode_d: -1,
                                    point_status: 0,
                                    创建时间: "2021-01-27 14:45:12",
                                    修改时间: "2021-01-27 14:45:12",
                                    人口: 199.43,
                                    GDP: 31,
                                    人均GDP: 19516,
                                    人均折美元: 2883,
                                },
                            },
                            {
                                type: "Feature",
                                geometry: {
                                    type: "Point",
                                    coordinates: [106.230909, 38.487193],
                                },
                                properties: {
                                    id: 253,
                                    名称: "银川市",
                                    地址: "银川市",
                                    adcode_n: -1,
                                    adcode_p: -1,
                                    adcode_c: -1,
                                    adcode_d: -1,
                                    point_status: 0,
                                    创建时间: "2021-01-27 14:45:12",
                                    修改时间: "2021-01-27 14:45:12",
                                    人口: 199.31,
                                    GDP: 617,
                                    人均GDP: 38295,
                                    人均折美元: 5657,
                                },
                            },
                            {
                                type: "Feature",
                                geometry: {
                                    type: "Point",
                                    coordinates: [111.566694, 24.403582],
                                },
                                properties: {
                                    id: 254,
                                    名称: "贺州市",
                                    地址: "贺州市",
                                    adcode_n: -1,
                                    adcode_p: -1,
                                    adcode_c: -1,
                                    adcode_d: -1,
                                    point_status: 0,
                                    创建时间: "2021-01-27 14:45:12",
                                    修改时间: "2021-01-27 14:45:12",
                                    人口: 195.41,
                                    GDP: 50,
                                    人均GDP: 19446,
                                    人均折美元: 2873,
                                },
                            },
                            {
                                type: "Feature",
                                geometry: {
                                    type: "Point",
                                    coordinates: [103.210538, 35.601182],
                                },
                                properties: {
                                    id: 255,
                                    名称: "临夏州",
                                    地址: "临夏州",
                                    adcode_n: -1,
                                    adcode_p: -1,
                                    adcode_c: -1,
                                    adcode_d: -1,
                                    point_status: 0,
                                    创建时间: "2021-01-27 14:45:12",
                                    修改时间: "2021-01-27 14:45:12",
                                    人口: 194.67,
                                    GDP: 142,
                                    人均GDP: 5465,
                                    人均折美元: 807,
                                },
                            },
                            {
                                type: "Feature",
                                geometry: {
                                    type: "Point",
                                    coordinates: [109.781327, 39.608266],
                                },
                                properties: {
                                    id: 256,
                                    名称: "鄂尔多斯市",
                                    地址: "鄂尔多斯市",
                                    adcode_n: -1,
                                    adcode_p: -1,
                                    adcode_c: -1,
                                    adcode_d: -1,
                                    point_status: 0,
                                    创建时间: "2021-01-27 14:45:12",
                                    修改时间: "2021-01-27 14:45:12",
                                    人口: 194.07,
                                    GDP: 136,
                                    人均GDP: 136202,
                                    人均折美元: 20120,
                                },
                            },
                            {
                                type: "Feature",
                                geometry: {
                                    type: "Point",
                                    coordinates: [130.969333, 45.295075],
                                },
                                properties: {
                                    id: 257,
                                    名称: "鸡西市",
                                    地址: "鸡西市",
                                    adcode_n: -1,
                                    adcode_p: -1,
                                    adcode_c: -1,
                                    adcode_d: -1,
                                    point_status: 0,
                                    创建时间: "2021-01-27 14:45:12",
                                    修改时间: "2021-01-27 14:45:12",
                                    人口: 186.22,
                                    GDP: 18,
                                    人均GDP: 22528,
                                    人均折美元: 3328,
                                },
                            },
                            {
                                type: "Feature",
                                geometry: {
                                    type: "Point",
                                    coordinates: [123.236944, 41.267244],
                                },
                                properties: {
                                    id: 258,
                                    名称: "辽阳市",
                                    地址: "辽阳市",
                                    adcode_n: -1,
                                    adcode_p: -1,
                                    adcode_c: -1,
                                    adcode_d: -1,
                                    point_status: 0,
                                    创建时间: "2021-01-27 14:45:12",
                                    修改时间: "2021-01-27 14:45:12",
                                    人口: 185.88,
                                    GDP: 136,
                                    人均GDP: 39558,
                                    人均折美元: 5844,
                                },
                            },
                            {
                                type: "Feature",
                                geometry: {
                                    type: "Point",
                                    coordinates: [113.854556, 27.622768],
                                },
                                properties: {
                                    id: 259,
                                    名称: "萍乡市",
                                    地址: "萍乡市",
                                    adcode_n: -1,
                                    adcode_p: -1,
                                    adcode_c: -1,
                                    adcode_d: -1,
                                    point_status: 0,
                                    创建时间: "2021-01-27 14:45:12",
                                    修改时间: "2021-01-27 14:45:12",
                                    人口: 185.45,
                                    GDP: 139,
                                    人均GDP: 28061,
                                    人均折美元: 4145,
                                },
                            },
                            {
                                type: "Feature",
                                geometry: {
                                    type: "Point",
                                    coordinates: [121.670323, 42.021619],
                                },
                                properties: {
                                    id: 260,
                                    名称: "阜新市",
                                    地址: "阜新市",
                                    adcode_n: -1,
                                    adcode_p: -1,
                                    adcode_c: -1,
                                    adcode_d: -1,
                                    point_status: 0,
                                    创建时间: "2021-01-27 14:45:12",
                                    修改时间: "2021-01-27 14:45:12",
                                    人口: 181.93,
                                    GDP: 18,
                                    人均GDP: 19848,
                                    人均折美元: 2932,
                                },
                            },
                            {
                                type: "Feature",
                                geometry: {
                                    type: "Point",
                                    coordinates: [102.638011, 37.928264],
                                },
                                properties: {
                                    id: 261,
                                    名称: "武威市",
                                    地址: "武威市",
                                    adcode_n: -1,
                                    adcode_p: -1,
                                    adcode_c: -1,
                                    adcode_d: -1,
                                    point_status: 0,
                                    创建时间: "2021-01-27 14:45:12",
                                    修改时间: "2021-01-27 14:45:12",
                                    人口: 181.51,
                                    GDP: 56,
                                    人均GDP: 12604,
                                    人均折美元: 1862,
                                },
                            },
                            {
                                type: "Feature",
                                geometry: {
                                    type: "Point",
                                    coordinates: [112.432825, 39.331595],
                                },
                                properties: {
                                    id: 262,
                                    名称: "朔州市",
                                    地址: "朔州市",
                                    adcode_n: -1,
                                    adcode_p: -1,
                                    adcode_c: -1,
                                    adcode_d: -1,
                                    point_status: 0,
                                    创建时间: "2021-01-27 14:45:12",
                                    修改时间: "2021-01-27 14:45:12",
                                    人口: 171.49,
                                    GDP: 32,
                                    人均GDP: 39076,
                                    人均折美元: 5772,
                                },
                            },
                            {
                                type: "Feature",
                                geometry: {
                                    type: "Point",
                                    coordinates: [123.766485, 41.294175],
                                },
                                properties: {
                                    id: 263,
                                    名称: "本溪市",
                                    地址: "本溪市",
                                    adcode_n: -1,
                                    adcode_p: -1,
                                    adcode_c: -1,
                                    adcode_d: -1,
                                    point_status: 0,
                                    创建时间: "2021-01-27 14:45:12",
                                    修改时间: "2021-01-27 14:45:12",
                                    人口: 170.95,
                                    GDP: 3232,
                                    人均GDP: 50329,
                                    人均折美元: 7435,
                                },
                            },
                            {
                                type: "Feature",
                                geometry: {
                                    type: "Point",
                                    coordinates: [104.138559, 36.544756],
                                },
                                properties: {
                                    id: 264,
                                    名称: "白银市",
                                    地址: "白银市",
                                    adcode_n: -1,
                                    adcode_p: -1,
                                    adcode_c: -1,
                                    adcode_d: -1,
                                    point_status: 0,
                                    创建时间: "2021-01-27 14:45:12",
                                    修改时间: "2021-01-27 14:45:12",
                                    人口: 170.88,
                                    GDP: 311.18,
                                    人均GDP: 18211,
                                    人均折美元: 2690,
                                },
                            },
                            {
                                type: "Feature",
                                geometry: {
                                    type: "Point",
                                    coordinates: [127.52856, 50.245329],
                                },
                                properties: {
                                    id: 265,
                                    名称: "黑河市",
                                    地址: "黑河市",
                                    adcode_n: -1,
                                    adcode_p: -1,
                                    adcode_c: -1,
                                    adcode_d: -1,
                                    point_status: 0,
                                    创建时间: "2021-01-27 14:45:12",
                                    修改时间: "2021-01-27 14:45:12",
                                    人口: 167.39,
                                    GDP: 1,
                                    人均GDP: 15616,
                                    人均折美元: 2307,
                                },
                            },
                            {
                                type: "Feature",
                                geometry: {
                                    type: "Point",
                                    coordinates: [107.387657, 40.743213],
                                },
                                properties: {
                                    id: 266,
                                    名称: "巴彦淖尔市",
                                    地址: "巴彦淖尔市",
                                    adcode_n: -1,
                                    adcode_p: -1,
                                    adcode_c: -1,
                                    adcode_d: -1,
                                    point_status: 0,
                                    创建时间: "2021-01-27 14:45:12",
                                    修改时间: "2021-01-27 14:45:12",
                                    人口: 166.99,
                                    GDP: 9,
                                    人均GDP: 36445,
                                    人均折美元: 5384,
                                },
                            },
                            {
                                type: "Feature",
                                geometry: {
                                    type: "Point",
                                    coordinates: [122.067042, 46.077561],
                                },
                                properties: {
                                    id: 267,
                                    名称: "兴安盟",
                                    地址: "兴安盟",
                                    adcode_n: -1,
                                    adcode_p: -1,
                                    adcode_c: -1,
                                    adcode_d: -1,
                                    point_status: 0,
                                    创建时间: "2021-01-27 14:45:12",
                                    修改时间: "2021-01-27 14:45:12",
                                    人口: 161.33,
                                    GDP: 30,
                                    人均GDP: 16203,
                                    人均折美元: 2393,
                                },
                            },
                            {
                                type: "Feature",
                                geometry: {
                                    type: "Point",
                                    coordinates: [117.178419, 29.268835],
                                },
                                properties: {
                                    id: 268,
                                    名称: "景德镇市",
                                    地址: "景德镇市",
                                    adcode_n: -1,
                                    adcode_p: -1,
                                    adcode_c: -1,
                                    adcode_d: -1,
                                    point_status: 0,
                                    创建时间: "2021-01-27 14:45:12",
                                    修改时间: "2021-01-27 14:45:12",
                                    人口: 158.75,
                                    GDP: 96,
                                    人均GDP: 29071,
                                    人均折美元: 4294,
                                },
                            },
                            {
                                type: "Feature",
                                geometry: {
                                    type: "Point",
                                    coordinates: [114.297272, 35.747225],
                                },
                                properties: {
                                    id: 269,
                                    名称: "鹤壁市",
                                    地址: "鹤壁市",
                                    adcode_n: -1,
                                    adcode_p: -1,
                                    adcode_c: -1,
                                    adcode_d: -1,
                                    point_status: 0,
                                    创建时间: "2021-01-27 14:45:12",
                                    修改时间: "2021-01-27 14:45:12",
                                    人口: 156.91,
                                    GDP: 55,
                                    人均GDP: 27260,
                                    人均折美元: 4027,
                                },
                            },
                            {
                                type: "Feature",
                                geometry: {
                                    type: "Point",
                                    coordinates: [113.576726, 22.270715],
                                },
                                properties: {
                                    id: 270,
                                    名称: "珠海市",
                                    地址: "珠海市",
                                    adcode_n: -1,
                                    adcode_p: -1,
                                    adcode_c: -1,
                                    adcode_d: -1,
                                    point_status: 0,
                                    创建时间: "2021-01-27 14:45:12",
                                    修改时间: "2021-01-27 14:45:12",
                                    人口: 156.02,
                                    GDP: 380,
                                    人均GDP: 77077,
                                    人均折美元: 11386,
                                },
                            },
                            {
                                type: "Feature",
                                geometry: {
                                    type: "Point",
                                    coordinates: [109.119927, 21.481254],
                                },
                                properties: {
                                    id: 271,
                                    名称: "北海市",
                                    地址: "北海市",
                                    adcode_n: -1,
                                    adcode_p: -1,
                                    adcode_c: -1,
                                    adcode_d: -1,
                                    point_status: 0,
                                    创建时间: "2021-01-27 14:45:12",
                                    修改时间: "2021-01-27 14:45:12",
                                    人口: 153.93,
                                    GDP: 35,
                                    人均GDP: 25830,
                                    人均折美元: 3816,
                                },
                            },
                            {
                                type: "Feature",
                                geometry: {
                                    type: "Point",
                                    coordinates: [103.013261, 29.980537],
                                },
                                properties: {
                                    id: 272,
                                    名称: "雅安市",
                                    地址: "雅安市",
                                    adcode_n: -1,
                                    adcode_p: -1,
                                    adcode_c: -1,
                                    adcode_d: -1,
                                    point_status: 0,
                                    创建时间: "2021-01-27 14:45:12",
                                    修改时间: "2021-01-27 14:45:12",
                                    人口: 150.73,
                                    GDP: 159,
                                    人均GDP: 19011,
                                    人均折美元: 2808,
                                },
                            },
                            {
                                type: "Feature",
                                geometry: {
                                    type: "Point",
                                    coordinates: [110.479191, 29.117096],
                                },
                                properties: {
                                    id: 273,
                                    名称: "张家界市",
                                    地址: "张家界市",
                                    adcode_n: -1,
                                    adcode_p: -1,
                                    adcode_c: -1,
                                    adcode_d: -1,
                                    point_status: 0,
                                    创建时间: "2021-01-27 14:45:12",
                                    修改时间: "2021-01-27 14:45:12",
                                    人口: 147.65,
                                    GDP: 154,
                                    人均GDP: 16422,
                                    人均折美元: 2426,
                                },
                            },
                            {
                                type: "Feature",
                                geometry: {
                                    type: "Point",
                                    coordinates: [131.159133, 46.646508],
                                },
                                properties: {
                                    id: 274,
                                    名称: "双鸭山市",
                                    地址: "双鸭山市",
                                    adcode_n: -1,
                                    adcode_p: -1,
                                    adcode_c: -1,
                                    adcode_d: -1,
                                    point_status: 0,
                                    创建时间: "2021-01-27 14:45:12",
                                    修改时间: "2021-01-27 14:45:12",
                                    人口: 146.26,
                                    GDP: 1,
                                    人均GDP: 25755,
                                    人均折美元: 3805,
                                },
                            },
                            {
                                type: "Feature",
                                geometry: {
                                    type: "Point",
                                    coordinates: [0, 0],
                                },
                                properties: {
                                    id: 275,
                                    名称: "昌吉州",
                                    地址: "昌吉回族自治州",
                                    adcode_n: -1,
                                    adcode_p: -1,
                                    adcode_c: -1,
                                    adcode_d: -1,
                                    point_status: 2,
                                    创建时间: "2021-01-27 14:45:12",
                                    修改时间: "2021-01-27 14:51:43",
                                    人口: 142.86,
                                    GDP: 60,
                                    人均GDP: 38989,
                                    人均折美元: 5760,
                                },
                            },
                            {
                                type: "Feature",
                                geometry: {
                                    type: "Point",
                                    coordinates: [117.491568, 30.6648],
                                },
                                properties: {
                                    id: 276,
                                    名称: "池州市",
                                    地址: "池州市",
                                    adcode_n: -1,
                                    adcode_p: -1,
                                    adcode_c: -1,
                                    adcode_d: -1,
                                    point_status: 0,
                                    创建时间: "2021-01-27 14:45:12",
                                    修改时间: "2021-01-27 14:45:12",
                                    人口: 140.3,
                                    GDP: 145,
                                    人均GDP: 21440,
                                    人均折美元: 3167,
                                },
                            },
                            {
                                type: "Feature",
                                geometry: {
                                    type: "Point",
                                    coordinates: [121.198479, 31.89315],
                                },
                                properties: {
                                    id: 277,
                                    名称: "海东地区",
                                    地址: "海东地区",
                                    adcode_n: -1,
                                    adcode_p: -1,
                                    adcode_c: -1,
                                    adcode_d: -1,
                                    point_status: 0,
                                    创建时间: "2021-01-27 14:45:12",
                                    修改时间: "2021-01-27 14:45:12",
                                    人口: 139.68,
                                    GDP: 30,
                                    人均GDP: 12407,
                                    人均折美元: 1833,
                                },
                            },
                            {
                                type: "Feature",
                                geometry: {
                                    type: "Point",
                                    coordinates: [122.070714, 41.119997],
                                },
                                properties: {
                                    id: 278,
                                    名称: "盘锦市",
                                    地址: "盘锦市",
                                    adcode_n: -1,
                                    adcode_p: -1,
                                    adcode_c: -1,
                                    adcode_d: -1,
                                    point_status: 0,
                                    创建时间: "2021-01-27 14:45:12",
                                    修改时间: "2021-01-27 14:45:12",
                                    人口: 139.25,
                                    GDP: 24,
                                    人均GDP: 66535,
                                    人均折美元: 9829,
                                },
                            },
                            {
                                type: "Feature",
                                geometry: {
                                    type: "Point",
                                    coordinates: [113.580519, 37.856971],
                                },
                                properties: {
                                    id: 279,
                                    名称: "阳泉市",
                                    地址: "阳泉市",
                                    adcode_n: -1,
                                    adcode_p: -1,
                                    adcode_c: -1,
                                    adcode_d: -1,
                                    point_status: 0,
                                    创建时间: "2021-01-27 14:45:12",
                                    修改时间: "2021-01-27 14:45:12",
                                    人口: 136.85,
                                    GDP: 270,
                                    人均GDP: 31377,
                                    人均折美元: 4635,
                                },
                            },
                            {
                                type: "Feature",
                                geometry: {
                                    type: "Point",
                                    coordinates: [118.506759, 31.670452],
                                },
                                properties: {
                                    id: 280,
                                    名称: "马鞍山市",
                                    地址: "马鞍山市",
                                    adcode_n: -1,
                                    adcode_p: -1,
                                    adcode_c: -1,
                                    adcode_d: -1,
                                    point_status: 0,
                                    创建时间: "2021-01-27 14:45:12",
                                    修改时间: "2021-01-27 14:45:12",
                                    人口: 136.6,
                                    GDP: 150,
                                    人均GDP: 59371,
                                    人均折美元: 8770,
                                },
                            },
                            {
                                type: "Feature",
                                geometry: {
                                    type: "Point",
                                    coordinates: [118.337481, 29.714655],
                                },
                                properties: {
                                    id: 281,
                                    名称: "黄山市",
                                    地址: "黄山市",
                                    adcode_n: -1,
                                    adcode_p: -1,
                                    adcode_c: -1,
                                    adcode_d: -1,
                                    point_status: 0,
                                    创建时间: "2021-01-27 14:45:12",
                                    修改时间: "2021-01-27 14:45:12",
                                    人口: 135.9,
                                    GDP: 158,
                                    人均GDP: 22759,
                                    人均折美元: 3362,
                                },
                            },
                            {
                                type: "Feature",
                                geometry: {
                                    type: "Point",
                                    coordinates: [117.676723, 36.213813],
                                },
                                properties: {
                                    id: 282,
                                    名称: "莱芜市",
                                    地址: "莱芜市",
                                    adcode_n: -1,
                                    adcode_p: -1,
                                    adcode_c: -1,
                                    adcode_d: -1,
                                    point_status: 0,
                                    创建时间: "2021-01-27 14:45:12",
                                    修改时间: "2021-01-27 14:45:12",
                                    人口: 129.85,
                                    GDP: 12,
                                    人均GDP: 42074,
                                    人均折美元: 6215,
                                },
                            },
                            {
                                type: "Feature",
                                geometry: {
                                    type: "Point",
                                    coordinates: [126.423587, 41.939994],
                                },
                                properties: {
                                    id: 283,
                                    名称: "白山市",
                                    地址: "白山市",
                                    adcode_n: -1,
                                    adcode_p: -1,
                                    adcode_c: -1,
                                    adcode_d: -1,
                                    point_status: 0,
                                    创建时间: "2021-01-27 14:45:12",
                                    修改时间: "2021-01-27 14:45:12",
                                    人口: 129.66,
                                    GDP: 8,
                                    人均GDP: 33409,
                                    人均折美元: 4935,
                                },
                            },
                            {
                                type: "Feature",
                                geometry: {
                                    type: "Point",
                                    coordinates: [86.145298, 41.764115],
                                },
                                properties: {
                                    id: 284,
                                    名称: "巴音郭楞州",
                                    地址: "巴音郭楞州",
                                    adcode_n: -1,
                                    adcode_p: -1,
                                    adcode_c: -1,
                                    adcode_d: -1,
                                    point_status: 0,
                                    创建时间: "2021-01-27 14:45:12",
                                    修改时间: "2021-01-27 14:45:12",
                                    人口: 127.85,
                                    GDP: 100,
                                    人均GDP: 49903,
                                    人均折美元: 7372,
                                },
                            },
                            {
                                type: "Feature",
                                geometry: {
                                    type: "Point",
                                    coordinates: [106.198393, 37.99746],
                                },
                                properties: {
                                    id: 285,
                                    名称: "吴忠市",
                                    地址: "吴忠市",
                                    adcode_n: -1,
                                    adcode_p: -1,
                                    adcode_c: -1,
                                    adcode_d: -1,
                                    point_status: 0,
                                    创建时间: "2021-01-27 14:45:12",
                                    修改时间: "2021-01-27 14:45:12",
                                    人口: 127.38,
                                    GDP: 167,
                                    人均GDP: 16486,
                                    人均折美元: 2435,
                                },
                            },
                            {
                                type: "Feature",
                                geometry: {
                                    type: "Point",
                                    coordinates: [100.22775, 26.855047],
                                },
                                properties: {
                                    id: 286,
                                    名称: "丽江市",
                                    地址: "丽江市",
                                    adcode_n: -1,
                                    adcode_p: -1,
                                    adcode_c: -1,
                                    adcode_d: -1,
                                    point_status: 0,
                                    创建时间: "2021-01-27 14:45:12",
                                    修改时间: "2021-01-27 14:45:12",
                                    人口: 124.5,
                                    GDP: 167,
                                    人均GDP: 11533,
                                    人均折美元: 1704,
                                },
                            },
                            {
                                type: "Feature",
                                geometry: {
                                    type: "Point",
                                    coordinates: [106.24261, 36.015855],
                                },
                                properties: {
                                    id: 287,
                                    名称: "固原市",
                                    地址: "固原市",
                                    adcode_n: -1,
                                    adcode_p: -1,
                                    adcode_c: -1,
                                    adcode_d: -1,
                                    point_status: 0,
                                    创建时间: "2021-01-27 14:45:12",
                                    修改时间: "2021-01-27 14:45:12",
                                    人口: 122.82,
                                    GDP: 51,
                                    人均GDP: 8470,
                                    人均折美元: 1251,
                                },
                            },
                            {
                                type: "Feature",
                                geometry: {
                                    type: "Point",
                                    coordinates: [82.980316, 46.745364],
                                },
                                properties: {
                                    id: 288,
                                    名称: "塔城地区",
                                    地址: "塔城地区",
                                    adcode_n: -1,
                                    adcode_p: -1,
                                    adcode_c: -1,
                                    adcode_d: -1,
                                    point_status: 0,
                                    创建时间: "2021-01-27 14:45:12",
                                    修改时间: "2021-01-27 14:45:12",
                                    人口: 121.92,
                                    GDP: 80,
                                    人均GDP: 24622,
                                    人均折美元: 3637,
                                },
                            },
                            {
                                type: "Feature",
                                geometry: {
                                    type: "Point",
                                    coordinates: [101.718637, 26.582347],
                                },
                                properties: {
                                    id: 289,
                                    名称: "攀枝花市",
                                    地址: "攀枝花市",
                                    adcode_n: -1,
                                    adcode_p: -1,
                                    adcode_c: -1,
                                    adcode_d: -1,
                                    point_status: 0,
                                    创建时间: "2021-01-27 14:45:12",
                                    修改时间: "2021-01-27 14:45:12",
                                    人口: 121.41,
                                    GDP: 21,
                                    人均GDP: 43158,
                                    人均折美元: 6375,
                                },
                            },
                            {
                                type: "Feature",
                                geometry: {
                                    type: "Point",
                                    coordinates: [98.584895, 24.433353],
                                },
                                properties: {
                                    id: 290,
                                    名称: "德宏州",
                                    地址: "德宏州",
                                    adcode_n: -1,
                                    adcode_p: -1,
                                    adcode_c: -1,
                                    adcode_d: -1,
                                    point_status: 0,
                                    创建时间: "2021-01-27 14:45:12",
                                    修改时间: "2021-01-27 14:45:12",
                                    人口: 121.1,
                                    GDP: 60,
                                    人均GDP: 11613,
                                    人均折美元: 1715,
                                },
                            },
                            {
                                type: "Feature",
                                geometry: {
                                    type: "Point",
                                    coordinates: [100.449818, 38.925875],
                                },
                                properties: {
                                    id: 291,
                                    名称: "张掖市",
                                    地址: "张掖市",
                                    adcode_n: -1,
                                    adcode_p: -1,
                                    adcode_c: -1,
                                    adcode_d: -1,
                                    point_status: 0,
                                    创建时间: "2021-01-27 14:45:12",
                                    修改时间: "2021-01-27 14:45:12",
                                    人口: 119.95,
                                    GDP: 19,
                                    人均GDP: 17731,
                                    人均折美元: 2619,
                                },
                            },
                            {
                                type: "Feature",
                                geometry: {
                                    type: "Point",
                                    coordinates: [125.143532, 42.887918],
                                },
                                properties: {
                                    id: 292,
                                    名称: "辽源市",
                                    地址: "辽源市",
                                    adcode_n: -1,
                                    adcode_p: -1,
                                    adcode_c: -1,
                                    adcode_d: -1,
                                    point_status: 0,
                                    创建时间: "2021-01-27 14:45:12",
                                    修改时间: "2021-01-27 14:45:12",
                                    人口: 117.66,
                                    GDP: 12,
                                    人均GDP: 34853,
                                    人均折美元: 5149,
                                },
                            },
                            {
                                type: "Feature",
                                geometry: {
                                    type: "Point",
                                    coordinates: [128.841147, 47.727536],
                                },
                                properties: {
                                    id: 293,
                                    名称: "伊春市",
                                    地址: "伊春市",
                                    adcode_n: -1,
                                    adcode_p: -1,
                                    adcode_c: -1,
                                    adcode_d: -1,
                                    point_status: 0,
                                    创建时间: "2021-01-27 14:45:12",
                                    修改时间: "2021-01-27 14:45:12",
                                    人口: 114.81,
                                    GDP: 60,
                                    人均GDP: 17629,
                                    人均折美元: 2604,
                                },
                            },
                            {
                                type: "Feature",
                                geometry: {
                                    type: "Point",
                                    coordinates: [114.917346, 27.817808],
                                },
                                properties: {
                                    id: 294,
                                    名称: "新余市",
                                    地址: "新余市",
                                    adcode_n: -1,
                                    adcode_p: -1,
                                    adcode_c: -1,
                                    adcode_d: -1,
                                    point_status: 0,
                                    创建时间: "2021-01-27 14:45:12",
                                    修改时间: "2021-01-27 14:45:12",
                                    人口: 113.89,
                                    GDP: 300,
                                    人均GDP: 55425,
                                    人均折美元: 8187,
                                },
                            },
                            {
                                type: "Feature",
                                geometry: {
                                    type: "Point",
                                    coordinates: [100.797777, 22.007351],
                                },
                                properties: {
                                    id: 295,
                                    名称: "西双版纳州",
                                    地址: "西双版纳州",
                                    adcode_n: -1,
                                    adcode_p: -1,
                                    adcode_c: -1,
                                    adcode_d: -1,
                                    point_status: 2,
                                    创建时间: "2021-01-27 14:45:12",
                                    修改时间: "2021-01-27 14:50:49",
                                    人口: 113.4,
                                    GDP: 160.99,
                                    人均GDP: 14196,
                                    人均折美元: 2097,
                                },
                            },
                            {
                                type: "Feature",
                                geometry: {
                                    type: "Point",
                                    coordinates: [117.069202, 28.260189],
                                },
                                properties: {
                                    id: 296,
                                    名称: "鹰潭市",
                                    地址: "鹰潭市",
                                    adcode_n: -1,
                                    adcode_p: -1,
                                    adcode_c: -1,
                                    adcode_d: -1,
                                    point_status: 0,
                                    创建时间: "2021-01-27 14:45:12",
                                    修改时间: "2021-01-27 14:45:12",
                                    人口: 112.49,
                                    GDP: 60,
                                    人均GDP: 30465,
                                    人均折美元: 4500,
                                },
                            },
                            {
                                type: "Feature",
                                geometry: {
                                    type: "Point",
                                    coordinates: [122.207215, 29.985295],
                                },
                                properties: {
                                    id: 297,
                                    名称: "舟山市",
                                    地址: "舟山市",
                                    adcode_n: -1,
                                    adcode_p: -1,
                                    adcode_c: -1,
                                    adcode_d: -1,
                                    point_status: 0,
                                    创建时间: "2021-01-27 14:45:12",
                                    修改时间: "2021-01-27 14:45:12",
                                    人口: 112.13,
                                    GDP: 834,
                                    人均GDP: 56492,
                                    人均折美元: 8345,
                                },
                            },
                            {
                                type: "Feature",
                                geometry: {
                                    type: "Point",
                                    coordinates: [98.494483, 39.73241],
                                },
                                properties: {
                                    id: 298,
                                    名称: "酒泉市",
                                    地址: "酒泉市",
                                    adcode_n: -1,
                                    adcode_p: -1,
                                    adcode_c: -1,
                                    adcode_d: -1,
                                    point_status: 0,
                                    创建时间: "2021-01-27 14:45:12",
                                    修改时间: "2021-01-27 14:45:12",
                                    人口: 109.59,
                                    GDP: 22,
                                    人均GDP: 36957,
                                    人均折美元: 5459,
                                },
                            },
                            {
                                type: "Feature",
                                geometry: {
                                    type: "Point",
                                    coordinates: [101.96231, 30.04952],
                                },
                                properties: {
                                    id: 299,
                                    名称: "甘孜州",
                                    地址: "甘孜州",
                                    adcode_n: -1,
                                    adcode_p: -1,
                                    adcode_c: -1,
                                    adcode_d: -1,
                                    point_status: 0,
                                    创建时间: "2021-01-27 14:45:12",
                                    修改时间: "2021-01-27 14:45:12",
                                    人口: 109.19,
                                    GDP: 30,
                                    人均GDP: 11249,
                                    人均折美元: 1662,
                                },
                            },
                            {
                                type: "Feature",
                                geometry: {
                                    type: "Point",
                                    coordinates: [105.196902, 37.499972],
                                },
                                properties: {
                                    id: 300,
                                    名称: "中卫市",
                                    地址: "中卫市",
                                    adcode_n: -1,
                                    adcode_p: -1,
                                    adcode_c: -1,
                                    adcode_d: -1,
                                    point_status: 0,
                                    创建时间: "2021-01-27 14:45:12",
                                    修改时间: "2021-01-27 14:45:12",
                                    人口: 108.08,
                                    GDP: 52,
                                    人均GDP: 15657,
                                    人均折美元: 2313,
                                },
                            },
                            {
                                type: "Feature",
                                geometry: {
                                    type: "Point",
                                    coordinates: [130.297964, 47.349916],
                                },
                                properties: {
                                    id: 301,
                                    名称: "鹤岗市",
                                    地址: "鹤岗市",
                                    adcode_n: -1,
                                    adcode_p: -1,
                                    adcode_c: -1,
                                    adcode_d: -1,
                                    point_status: 0,
                                    创建时间: "2021-01-27 14:45:12",
                                    修改时间: "2021-01-27 14:45:12",
                                    人口: 105.87,
                                    GDP: 1,
                                    人均GDP: 23709,
                                    人均折美元: 3502,
                                },
                            },
                            {
                                type: "Feature",
                                geometry: {
                                    type: "Point",
                                    coordinates: [114.894843, 30.39194],
                                },
                                properties: {
                                    id: 302,
                                    名称: "鄂州市",
                                    地址: "鄂州市",
                                    adcode_n: -1,
                                    adcode_p: -1,
                                    adcode_c: -1,
                                    adcode_d: -1,
                                    point_status: 0,
                                    创建时间: "2021-01-27 14:45:12",
                                    修改时间: "2021-01-27 14:45:12",
                                    人口: 104.87,
                                    GDP: 155,
                                    人均GDP: 37694,
                                    人均折美元: 5568,
                                },
                            },
                            {
                                type: "Feature",
                                geometry: {
                                    type: "Point",
                                    coordinates: [116.048222, 43.933454],
                                },
                                properties: {
                                    id: 303,
                                    名称: "锡林郭勒盟",
                                    地址: "锡林郭勒盟",
                                    adcode_n: -1,
                                    adcode_p: -1,
                                    adcode_c: -1,
                                    adcode_d: -1,
                                    point_status: 0,
                                    创建时间: "2021-01-27 14:45:12",
                                    修改时间: "2021-01-27 14:45:12",
                                    人口: 102.8,
                                    GDP: 90,
                                    人均GDP: 57513,
                                    人均折美元: 8496,
                                },
                            },
                            {
                                type: "Feature",
                                geometry: {
                                    type: "Point",
                                    coordinates: [131.003138, 45.771726],
                                },
                                properties: {
                                    id: 304,
                                    名称: "七台河市",
                                    地址: "七台河市",
                                    adcode_n: -1,
                                    adcode_p: -1,
                                    adcode_c: -1,
                                    adcode_d: -1,
                                    point_status: 0,
                                    创建时间: "2021-01-27 14:45:12",
                                    修改时间: "2021-01-27 14:45:12",
                                    人口: 92.04,
                                    GDP: 92,
                                    人均GDP: 32811,
                                    人均折美元: 4847,
                                },
                            },
                            {
                                type: "Feature",
                                geometry: {
                                    type: "Point",
                                    coordinates: [102.224653, 31.899413],
                                },
                                properties: {
                                    id: 305,
                                    名称: "阿坝州",
                                    地址: "阿坝州",
                                    adcode_n: -1,
                                    adcode_p: -1,
                                    adcode_c: -1,
                                    adcode_d: -1,
                                    point_status: 0,
                                    创建时间: "2021-01-27 14:45:12",
                                    修改时间: "2021-01-27 14:45:12",
                                    人口: 89.87,
                                    GDP: 60,
                                    人均GDP: 14772,
                                    人均折美元: 2182,
                                },
                            },
                            {
                                type: "Feature",
                                geometry: {
                                    type: "Point",
                                    coordinates: [108.353846, 21.68686],
                                },
                                properties: {
                                    id: 306,
                                    名称: "防城港市",
                                    地址: "防城港市",
                                    adcode_n: -1,
                                    adcode_p: -1,
                                    adcode_c: -1,
                                    adcode_d: -1,
                                    point_status: 0,
                                    创建时间: "2021-01-27 14:45:12",
                                    修改时间: "2021-01-27 14:45:12",
                                    人口: 86.69,
                                    GDP: 17,
                                    人均GDP: 36860,
                                    人均折美元: 5445,
                                },
                            },
                            {
                                type: "Feature",
                                geometry: {
                                    type: "Point",
                                    coordinates: [108.945233, 34.896756],
                                },
                                properties: {
                                    id: 307,
                                    名称: "铜川市",
                                    地址: "铜川市",
                                    adcode_n: -1,
                                    adcode_p: -1,
                                    adcode_c: -1,
                                    adcode_d: -1,
                                    point_status: 0,
                                    创建时间: "2021-01-27 14:45:12",
                                    修改时间: "2021-01-27 14:45:12",
                                    人口: 83.44,
                                    GDP: 101,
                                    人均GDP: 22498,
                                    人均折美元: 3323,
                                },
                            },
                            {
                                type: "Feature",
                                geometry: {
                                    type: "Point",
                                    coordinates: [106.383303, 38.983236],
                                },
                                properties: {
                                    id: 308,
                                    名称: "石嘴山市",
                                    地址: "石嘴山市",
                                    adcode_n: -1,
                                    adcode_p: -1,
                                    adcode_c: -1,
                                    adcode_d: -1,
                                    point_status: 0,
                                    创建时间: "2021-01-27 14:45:12",
                                    修改时间: "2021-01-27 14:45:12",
                                    人口: 72.55,
                                    GDP: 89,
                                    人均GDP: 41086,
                                    人均折美元: 6069,
                                },
                            },
                            {
                                type: "Feature",
                                geometry: {
                                    type: "Point",
                                    coordinates: [117.812079, 30.945429],
                                },
                                properties: {
                                    id: 309,
                                    名称: "铜陵市",
                                    地址: "铜陵市",
                                    adcode_n: -1,
                                    adcode_p: -1,
                                    adcode_c: -1,
                                    adcode_d: -1,
                                    point_status: 0,
                                    创建时间: "2021-01-27 14:45:12",
                                    修改时间: "2021-01-27 14:45:12",
                                    人口: 72.4,
                                    GDP: 4,
                                    人均GDP: 64448,
                                    人均折美元: 9520,
                                },
                            },
                            {
                                type: "Feature",
                                geometry: {
                                    type: "Point",
                                    coordinates: [88.880583, 29.266869],
                                },
                                properties: {
                                    id: 310,
                                    名称: "日喀则地区",
                                    地址: "日喀则",
                                    adcode_n: -1,
                                    adcode_p: -1,
                                    adcode_c: -1,
                                    adcode_d: -1,
                                    point_status: 2,
                                    创建时间: "2021-01-27 14:45:12",
                                    修改时间: "2021-01-27 14:49:56",
                                    人口: 70.33,
                                    GDP: 21,
                                    人均GDP: 12271,
                                    人均折美元: 1813,
                                },
                            },
                            {
                                type: "Feature",
                                geometry: {
                                    type: "Point",
                                    coordinates: [102.911027, 34.983385],
                                },
                                properties: {
                                    id: 311,
                                    名称: "甘南州",
                                    地址: "甘南州",
                                    adcode_n: -1,
                                    adcode_p: -1,
                                    adcode_c: -1,
                                    adcode_d: -1,
                                    point_status: 0,
                                    创建时间: "2021-01-27 14:45:12",
                                    修改时间: "2021-01-27 14:45:12",
                                    人口: 68.91,
                                    GDP: 26,
                                    人均GDP: 9844,
                                    人均折美元: 1454,
                                },
                            },
                            {
                                type: "Feature",
                                geometry: {
                                    type: "Point",
                                    coordinates: [109.511909, 18.252847],
                                },
                                properties: {
                                    id: 312,
                                    名称: "三亚市",
                                    地址: "三亚市",
                                    adcode_n: -1,
                                    adcode_p: -1,
                                    adcode_c: -1,
                                    adcode_d: -1,
                                    point_status: 0,
                                    创建时间: "2021-01-27 14:45:12",
                                    修改时间: "2021-01-27 14:45:12",
                                    人口: 68.54,
                                    GDP: 948,
                                    人均GDP: 33672,
                                    人均折美元: 4974,
                                },
                            },
                            {
                                type: "Feature",
                                geometry: {
                                    type: "Point",
                                    coordinates: [112.602344, 35.06903],
                                },
                                properties: {
                                    id: 313,
                                    名称: "济源市",
                                    地址: "济源市",
                                    adcode_n: -1,
                                    adcode_p: -1,
                                    adcode_c: -1,
                                    adcode_d: -1,
                                    point_status: 0,
                                    创建时间: "2021-01-27 14:45:12",
                                    修改时间: "2021-01-27 14:45:12",
                                    人口: 67.57,
                                    GDP: 80,
                                    人均GDP: 50821,
                                    人均折美元: 7507,
                                },
                            },
                            {
                                type: "Feature",
                                geometry: {
                                    type: "Point",
                                    coordinates: [97.17202, 31.140969],
                                },
                                properties: {
                                    id: 314,
                                    名称: "昌都地区",
                                    地址: "西藏自治区昌都市",
                                    adcode_n: -1,
                                    adcode_p: -1,
                                    adcode_c: -1,
                                    adcode_d: -1,
                                    point_status: 2,
                                    创建时间: "2021-01-27 14:45:12",
                                    修改时间: "2021-01-27 14:49:42",
                                    人口: 65.75,
                                    GDP: 16,
                                    人均GDP: 10266,
                                    人均折美元: 1517,
                                },
                            },
                            {
                                type: "Feature",
                                geometry: {
                                    type: "Point",
                                    coordinates: [89.189651, 42.951382],
                                },
                                properties: {
                                    id: 315,
                                    名称: "吐鲁番地区",
                                    地址: "吐鲁番地区",
                                    adcode_n: -1,
                                    adcode_p: -1,
                                    adcode_c: -1,
                                    adcode_d: -1,
                                    point_status: 0,
                                    创建时间: "2021-01-27 14:45:12",
                                    修改时间: "2021-01-27 14:45:12",
                                    人口: 62.27,
                                    GDP: 36,
                                    人均GDP: 29759,
                                    人均折美元: 4396,
                                },
                            },
                            {
                                type: "Feature",
                                geometry: {
                                    type: "Point",
                                    coordinates: [88.141253, 47.844924],
                                },
                                properties: {
                                    id: 316,
                                    名称: "阿勒泰地区",
                                    地址: "阿勒泰地区",
                                    adcode_n: -1,
                                    adcode_p: -1,
                                    adcode_c: -1,
                                    adcode_d: -1,
                                    point_status: 0,
                                    创建时间: "2021-01-27 14:45:12",
                                    修改时间: "2021-01-27 14:45:12",
                                    人口: 60.33,
                                    GDP: 18,
                                    人均GDP: 22354,
                                    人均折美元: 3302,
                                },
                            },
                            {
                                type: "Feature",
                                geometry: {
                                    type: "Point",
                                    coordinates: [93.513161, 42.833248],
                                },
                                properties: {
                                    id: 317,
                                    名称: "哈密地区",
                                    地址: "哈密地区",
                                    adcode_n: -1,
                                    adcode_p: -1,
                                    adcode_c: -1,
                                    adcode_d: -1,
                                    point_status: 0,
                                    创建时间: "2021-01-27 14:45:12",
                                    修改时间: "2021-01-27 14:45:12",
                                    人口: 57.24,
                                    GDP: 46,
                                    人均GDP: 28994,
                                    人均折美元: 4283,
                                },
                            },
                            {
                                type: "Feature",
                                geometry: {
                                    type: "Point",
                                    coordinates: [91.140856, 29.645554],
                                },
                                properties: {
                                    id: 318,
                                    名称: "拉萨市",
                                    地址: "拉萨市",
                                    adcode_n: -1,
                                    adcode_p: -1,
                                    adcode_c: -1,
                                    adcode_d: -1,
                                    point_status: 0,
                                    创建时间: "2021-01-27 14:45:12",
                                    修改时间: "2021-01-27 14:45:12",
                                    人口: 55.94,
                                    GDP: 84,
                                    人均GDP: 31981,
                                    人均折美元: 4724,
                                },
                            },
                            {
                                type: "Feature",
                                geometry: {
                                    type: "Point",
                                    coordinates: [98.853097, 25.852547],
                                },
                                properties: {
                                    id: 319,
                                    名称: "怒江州",
                                    地址: "怒江州",
                                    adcode_n: -1,
                                    adcode_p: -1,
                                    adcode_c: -1,
                                    adcode_d: -1,
                                    point_status: 0,
                                    创建时间: "2021-01-27 14:45:12",
                                    修改时间: "2021-01-27 14:45:12",
                                    人口: 53.4,
                                    GDP: 12,
                                    人均GDP: 9993,
                                    人均折美元: 1476,
                                },
                            },
                            {
                                type: "Feature",
                                geometry: {
                                    type: "Point",
                                    coordinates: [106.794249, 39.655388],
                                },
                                properties: {
                                    id: 320,
                                    名称: "乌海市",
                                    地址: "乌海市",
                                    adcode_n: -1,
                                    adcode_p: -1,
                                    adcode_c: -1,
                                    adcode_d: -1,
                                    point_status: 0,
                                    创建时间: "2021-01-27 14:45:12",
                                    修改时间: "2021-01-27 14:45:12",
                                    人口: 53.29,
                                    GDP: 4,
                                    人均GDP: 73394,
                                    人均折美元: 10842,
                                },
                            },
                            {
                                type: "Feature",
                                geometry: {
                                    type: "Point",
                                    coordinates: [76.167819, 39.714526],
                                },
                                properties: {
                                    id: 321,
                                    名称: "克孜勒苏州",
                                    地址: "克孜勒苏州",
                                    adcode_n: -1,
                                    adcode_p: -1,
                                    adcode_c: -1,
                                    adcode_d: -1,
                                    point_status: 0,
                                    创建时间: "2021-01-27 14:45:12",
                                    修改时间: "2021-01-27 14:45:12",
                                    人口: 52.56,
                                    GDP: 6,
                                    人均GDP: 7230,
                                    人均折美元: 1068,
                                },
                            },
                            {
                                type: "Feature",
                                geometry: {
                                    type: "Point",
                                    coordinates: [124.71108, 52.335206],
                                },
                                properties: {
                                    id: 322,
                                    名称: "大兴安岭地区",
                                    地址: "大兴安岭地区",
                                    adcode_n: -1,
                                    adcode_p: -1,
                                    adcode_c: -1,
                                    adcode_d: -1,
                                    point_status: 0,
                                    创建时间: "2021-01-27 14:45:12",
                                    修改时间: "2021-01-27 14:45:12",
                                    人口: 51.16,
                                    GDP: 68,
                                    人均GDP: 19192,
                                    人均折美元: 2835,
                                },
                            },
                            {
                                type: "Feature",
                                geometry: {
                                    type: "Point",
                                    coordinates: [97.369751, 37.377139],
                                },
                                properties: {
                                    id: 323,
                                    名称: "海西州",
                                    地址: "海西州",
                                    adcode_n: -1,
                                    adcode_p: -1,
                                    adcode_c: -1,
                                    adcode_d: -1,
                                    point_status: 0,
                                    创建时间: "2021-01-27 14:45:12",
                                    修改时间: "2021-01-27 14:45:12",
                                    人口: 48.93,
                                    GDP: 57,
                                    人均GDP: 74691,
                                    人均折美元: 11033,
                                },
                            },
                            {
                                type: "Feature",
                                geometry: {
                                    type: "Point",
                                    coordinates: [102.188043, 38.520089],
                                },
                                properties: {
                                    id: 324,
                                    名称: "金昌市",
                                    地址: "金昌市",
                                    adcode_n: -1,
                                    adcode_p: -1,
                                    adcode_c: -1,
                                    adcode_d: -1,
                                    point_status: 0,
                                    创建时间: "2021-01-27 14:45:12",
                                    修改时间: "2021-01-27 14:45:12",
                                    人口: 46.41,
                                    GDP: 16,
                                    人均GDP: 45364,
                                    人均折美元: 6701,
                                },
                            },
                            {
                                type: "Feature",
                                geometry: {
                                    type: "Point",
                                    coordinates: [92.051239, 31.476202],
                                },
                                properties: {
                                    id: 325,
                                    名称: "那曲地区",
                                    地址: "那曲地区",
                                    adcode_n: -1,
                                    adcode_p: -1,
                                    adcode_c: -1,
                                    adcode_d: -1,
                                    point_status: 0,
                                    创建时间: "2021-01-27 14:45:12",
                                    修改时间: "2021-01-27 14:45:12",
                                    人口: 46.24,
                                    GDP: 32,
                                    人均GDP: 11062,
                                    人均折美元: 1634,
                                },
                            },
                            {
                                type: "Feature",
                                geometry: {
                                    type: "Point",
                                    coordinates: [82.066159, 44.905588],
                                },
                                properties: {
                                    id: 326,
                                    名称: "博尔塔拉州",
                                    地址: "博尔塔拉州",
                                    adcode_n: -1,
                                    adcode_p: -1,
                                    adcode_c: -1,
                                    adcode_d: -1,
                                    point_status: 0,
                                    创建时间: "2021-01-27 14:45:12",
                                    修改时间: "2021-01-27 14:45:12",
                                    人口: 44.37,
                                    GDP: 96,
                                    人均GDP: 28910,
                                    人均折美元: 4271,
                                },
                            },
                            {
                                type: "Feature",
                                geometry: {
                                    type: "Point",
                                    coordinates: [110.349228, 20.017377],
                                },
                                properties: {
                                    id: 327,
                                    名称: "海南州",
                                    地址: "海南省 海南州",
                                    adcode_n: -1,
                                    adcode_p: -1,
                                    adcode_c: -1,
                                    adcode_d: -1,
                                    point_status: 2,
                                    创建时间: "2021-01-27 14:45:12",
                                    修改时间: "2021-01-27 14:48:40",
                                    人口: 44.17,
                                    GDP: 96,
                                    人均GDP: 15823,
                                    人均折美元: 2337,
                                },
                            },
                            {
                                type: "Feature",
                                geometry: {
                                    type: "Point",
                                    coordinates: [99.702234, 27.818882],
                                },
                                properties: {
                                    id: 328,
                                    名称: "迪庆州",
                                    地址: "迪庆州",
                                    adcode_n: -1,
                                    adcode_p: -1,
                                    adcode_c: -1,
                                    adcode_d: -1,
                                    point_status: 0,
                                    创建时间: "2021-01-27 14:45:12",
                                    修改时间: "2021-01-27 14:45:12",
                                    人口: 40,
                                    GDP: 12,
                                    人均GDP: 19275,
                                    人均折美元: 2847,
                                },
                            },
                            {
                                type: "Feature",
                                geometry: {
                                    type: "Point",
                                    coordinates: [84.889207, 45.579888],
                                },
                                properties: {
                                    id: 329,
                                    名称: "克拉玛依市",
                                    地址: "克拉玛依市",
                                    adcode_n: -1,
                                    adcode_p: -1,
                                    adcode_c: -1,
                                    adcode_d: -1,
                                    point_status: 0,
                                    创建时间: "2021-01-27 14:45:12",
                                    修改时间: "2021-01-27 14:45:12",
                                    人口: 39.1,
                                    GDP: 44,
                                    人均GDP: 181633,
                                    人均折美元: 26831,
                                },
                            },
                            {
                                type: "Feature",
                                geometry: {
                                    type: "Point",
                                    coordinates: [97.091934, 33.011674],
                                },
                                properties: {
                                    id: 330,
                                    名称: "玉树州",
                                    地址: "玉树州",
                                    adcode_n: -1,
                                    adcode_p: -1,
                                    adcode_c: -1,
                                    adcode_d: -1,
                                    point_status: 0,
                                    创建时间: "2021-01-27 14:45:12",
                                    修改时间: "2021-01-27 14:45:12",
                                    人口: 37.84,
                                    GDP: 24,
                                    人均GDP: 8418,
                                    人均折美元: 1244,
                                },
                            },
                            {
                                type: "Feature",
                                geometry: {
                                    type: "Point",
                                    coordinates: [91.766525, 29.236023],
                                },
                                properties: {
                                    id: 331,
                                    名称: "山南地区",
                                    地址: "山南地区",
                                    adcode_n: -1,
                                    adcode_p: -1,
                                    adcode_c: -1,
                                    adcode_d: -1,
                                    point_status: 0,
                                    创建时间: "2021-01-27 14:45:12",
                                    修改时间: "2021-01-27 14:45:12",
                                    人口: 32.9,
                                    GDP: 12,
                                    人均GDP: 16423,
                                    人均折美元: 2426,
                                },
                            },
                            {
                                type: "Feature",
                                geometry: {
                                    type: "Point",
                                    coordinates: [100.900997, 36.954413],
                                },
                                properties: {
                                    id: 332,
                                    名称: "海北州",
                                    地址: "海北州",
                                    adcode_n: -1,
                                    adcode_p: -1,
                                    adcode_c: -1,
                                    adcode_d: -1,
                                    point_status: 0,
                                    创建时间: "2021-01-27 14:45:12",
                                    修改时间: "2021-01-27 14:45:12",
                                    人口: 27.33,
                                    GDP: 12,
                                    人均GDP: 19952,
                                    人均折美元: 2947,
                                },
                            },
                            {
                                type: "Feature",
                                geometry: {
                                    type: "Point",
                                    coordinates: [102.015248, 35.519548],
                                },
                                properties: {
                                    id: 333,
                                    名称: "黄南州",
                                    地址: "黄南州",
                                    adcode_n: -1,
                                    adcode_p: -1,
                                    adcode_c: -1,
                                    adcode_d: -1,
                                    point_status: 0,
                                    创建时间: "2021-01-27 14:45:12",
                                    修改时间: "2021-01-27 14:45:12",
                                    人口: 25.67,
                                    GDP: 32,
                                    人均GDP: 17015,
                                    人均折美元: 2513,
                                },
                            },
                            {
                                type: "Feature",
                                geometry: {
                                    type: "Point",
                                    coordinates: [98.289152, 39.77313],
                                },
                                properties: {
                                    id: 334,
                                    名称: "嘉峪关市",
                                    地址: "嘉峪关市",
                                    adcode_n: -1,
                                    adcode_p: -1,
                                    adcode_c: -1,
                                    adcode_d: -1,
                                    point_status: 0,
                                    创建时间: "2021-01-27 14:45:12",
                                    修改时间: "2021-01-27 14:45:12",
                                    人口: 23.19,
                                    GDP: 96,
                                    人均GDP: 79322,
                                    人均折美元: 11718,
                                },
                            },
                            {
                                type: "Feature",
                                geometry: {
                                    type: "Point",
                                    coordinates: [105.728969, 38.851892],
                                },
                                properties: {
                                    id: 335,
                                    名称: "阿拉善盟",
                                    地址: "阿拉善盟",
                                    adcode_n: -1,
                                    adcode_p: -1,
                                    adcode_c: -1,
                                    adcode_d: -1,
                                    point_status: 0,
                                    创建时间: "2021-01-27 14:45:12",
                                    修改时间: "2021-01-27 14:45:12",
                                    人口: 23.13,
                                    GDP: 265,
                                    人均GDP: 132229,
                                    人均折美元: 19533,
                                },
                            },
                            {
                                type: "Feature",
                                geometry: {
                                    type: "Point",
                                    coordinates: [108.940174, 34.341568],
                                },
                                properties: {
                                    id: 336,
                                    名称: "杨凌示范区",
                                    地址: "西安杨凌",
                                    adcode_n: -1,
                                    adcode_p: -1,
                                    adcode_c: -1,
                                    adcode_d: -1,
                                    point_status: 2,
                                    创建时间: "2021-01-27 14:45:12",
                                    修改时间: "2021-01-27 14:48:20",
                                    人口: 20.12,
                                    GDP: 120,
                                    人均GDP: 23509,
                                    人均折美元: 3473,
                                },
                            },
                            {
                                type: "Feature",
                                geometry: {
                                    type: "Point",
                                    coordinates: [94.361558, 29.648943],
                                },
                                properties: {
                                    id: 337,
                                    名称: "林芝地区",
                                    地址: "林芝地区",
                                    adcode_n: -1,
                                    adcode_p: -1,
                                    adcode_c: -1,
                                    adcode_d: -1,
                                    point_status: 0,
                                    创建时间: "2021-01-27 14:45:12",
                                    修改时间: "2021-01-27 14:45:12",
                                    人口: 19.51,
                                    GDP: 15,
                                    人均GDP: 27216,
                                    人均折美元: 4020,
                                },
                            },
                            {
                                type: "Feature",
                                geometry: {
                                    type: "Point",
                                    coordinates: [100.244808, 34.471431],
                                },
                                properties: {
                                    id: 338,
                                    名称: "果洛州",
                                    地址: "果洛州",
                                    adcode_n: -1,
                                    adcode_p: -1,
                                    adcode_c: -1,
                                    adcode_d: -1,
                                    point_status: 0,
                                    创建时间: "2021-01-27 14:45:12",
                                    修改时间: "2021-01-27 14:45:12",
                                    人口: 18.17,
                                    GDP: 26,
                                    人均GDP: 11245,
                                    人均折美元: 1661,
                                },
                            },
                            {
                                type: "Feature",
                                geometry: {
                                    type: "Point",
                                    coordinates: [80.105804, 32.501111],
                                },
                                properties: {
                                    id: 339,
                                    名称: "阿里地区",
                                    地址: "阿里地区",
                                    adcode_n: -1,
                                    adcode_p: -1,
                                    adcode_c: -1,
                                    adcode_d: -1,
                                    point_status: 0,
                                    创建时间: "2021-01-27 14:45:12",
                                    修改时间: "2021-01-27 14:45:12",
                                    人口: 9.55,
                                    GDP: 12,
                                    人均GDP: 19379,
                                    人均折美元: 2863,
                                },
                            },
                        ],
                    },
                });
                this.pl.setSource(geo);
                this.pl.setStyle({
                    unit: "meter",
                    sideNumber: 4,
                    topColor: (index, f) => {
                        var n = f.properties["GDP"];
                        return n > 7000 ? "#E97091" : "#2852F1";
                    },
                    sideTopColor: (index, f) => {
                        var n = f.properties["GDP"];
                        return n > 7000 ? "#E97091" : "#2852F1";
                    },
                    sideBottomColor: "#002bb9",
                    radius: 15000,
                    height: (index, f) => {
                        var props = f.properties;
                        var height = Math.max(
                            100,
                            Math.sqrt(props["GDP"]) * 9000 - 50000
                        );
                        var conf = topConf[props["名称"]];
                        // top3 的数据，增加文字表达
                        if (conf) {
                            this.map.add(
                                new AMap.Marker({
                                    anchor: "bottom-center",
                                    position: [
                                        f.coordinates[0],
                                        f.coordinates[1],
                                        height,
                                    ],
                                    content:
                                        '<div style="margin-bottom: 10px; float: left; font-size: 14px;height: 57px; width: 180px; color:#fff; background: no-repeat url(' +
                                        conf +
                                        '); background-size: 100%;"><p style="margin: 7px 0 0 75px; height: 20px; line-height:20px;">' +
                                        props["名称"] +
                                        "</p>" +
                                        '<p style="margin: 4px 0 0 75px; height: 20px; line-height:20px; color: #00a9ff; font-size: 13px;">' +
                                        props["GDP"] +
                                        " 个" +
                                        "</p></div>",
                                })
                            );
                        }
                        return height;
                    },
                    rotation: 360,
                    altitude: 0,
                });
                this.$refs.loca.$$getInstance().add(this.pl);
                // console.log(this.$refs.loca.$$getInstance());
                var clickInfo = new AMap.Marker({
                    anchor: "bottom-center",
                    position: [116.396923, 39.918203, 0],
                });
                clickInfo.setMap(this.map);
                clickInfo.hide();
                // console.log(clickInfo)
                this.map.on("complete", () => {
                    // console.log(this.pl, "464");
                    setTimeout(() => {
                        this.pl.show(500);
                        var di = this.pl.addAnimate({
                            key: "height",
                            value: [0, 1],
                            duration: 500,
                            easing: "Linear",
                            transform: 2000,
                            random: true,
                            delay: 8000,
                        });
                        // console.log(di);
                        this.pl.addAnimate({
                            key: "rotation",
                            value: [0, 1],
                            duration: 500,
                            easing: "Linear",
                            transform: 2000,
                            random: true,
                            delay: 8000,
                        });
                    }, 800);
                });
                this.$refs.loca.$$getInstance().animate.start();
                this.map.on("mousemove", (e) => {
                    //   console.log(this.$refs.loca.$$getInstance());
                    var feat = this.pl.queryFeature(e.pixel.toArray());
                    // console.log(feat);
                    if (feat) {
                        clickInfo.show();
                        var props = feat.properties;
                        var height = Math.max(
                            100,
                            Math.sqrt(props["GDP"]) * 9000 - 50000
                        );
                        clickInfo.setPosition([
                            feat.coordinates[0],
                            feat.coordinates[1],
                            height,
                        ]);
                        clickInfo.setContent(
                            '<div style="text-align: center; height: 20px; width: 150px; color:#fff; font-size: 14px;">' +
                                feat.properties["名称"] +
                                ": " +
                                feat.properties["GDP"] +
                                " 个</div>"
                        );
                    } else {
                        clickInfo.hide();
                    }
                });
            });
        },
        // this.echartstitle标题
        // this.arry数据

        innitecharts() {
            var chartDom = document.getElementById("main");
            var myChart = echarts.init(chartDom);
            var option;

            option = {
                tooltip: {
                    trigger: "item",
                },
                legend: {
                    top: "-1%",
                    left: "center",
                    textStyle: {
                        color: "#fff", // 将文本字体颜色设为白色
                    },
                },
                series: [
                    {
                        name: "Access From",
                        type: "pie",
                        radius: ["40%", "70%"],
                        avoidLabelOverlap: false,
                        itemStyle: {
                            borderRadius: 10,
                            borderColor: null,
                            borderWidth: 2,
                        },
                        label: {
                            show: false,
                            position: "center",
                        },
                        emphasis: {
                            label: {
                                show: true,
                                fontSize: 40,
                                fontWeight: "bold",
                            },
                        },
                        labelLine: {
                            show: false,
                        },
                        data: this.arry,
                    },
                ],
            };

            option && myChart.setOption(option);
        },
        innitecharts1() {
            var chartDom = document.getElementById("lilmap");
            var myChart = echarts.init(chartDom);
            var option;
            var mapJson = this.mapjson;
            var mapData = [
                {
                    value: 5000,
                    name: "朝阳区",
                },
                {
                    value: 200,
                    name: "福洪镇",
                },
                {
                    value: 300,
                    name: "姚渡镇",
                },
                {
                    value: 400,
                    name: "弥牟镇",
                },
                {
                    value: 500,
                    name: "大同街道",
                },
                {
                    value: 600,
                    name: "大弯街道",
                },

                {
                    name: "合肥市",
                    value: 5000,
                },
            ]; //虚拟数据
            echarts.registerMap("area", mapJson);
            var outdata = []; //地图区域挂载数据
            var maxData = parseInt(mapData[0].value); //热力最大值
            var minData = parseInt(mapData[0].value); //热力最小值
            mapData.forEach(function (item, index) {
                var num = parseInt(item.value);
                num >= maxData && (maxData = num);
                num <= minData && (minData = num);
                outdata.push({
                    name: item.name,
                    value: item.value,
                    index: index,
                });
                // console.log(outdata);
            });
            var option = {
                tooltip: {
                    show: true,
                    formatter: function (params) {
                        return params.name + "<br>" + params.value;
                    },
                },
                grid: {
                    left: "1%",
                    right: "1%",
                    top: "1%",
                    bottom: "1%",
                    show: false,
                    // backgroundColor:'#000'
                },
                // 地图的阴影底图
                geo: {
                    map: "area",
                    left: 0,
                    right: 0,
                    bottom: 0,
                    top: 0,
                    aspectScale: 0.9,
                    layoutCenter: ["50%", "52.5%"], //地图位置
                    layoutSize: "80%",
                    itemStyle: {
                        normal: {
                            shadowColor: "rgba(0,228,242,0.5)",
                            shadowBlur: 5,
                            color: "#082A52",
                        },
                        emphasis: {
                            areaColor: "#082A52",
                        },
                    },
                    z: 2,
                },
                //就这东西能根据地图数据value值实现地图不同颜色
                visualMap: {
                    left: "10%",
                    bottom: "10%",
                    min: minData,
                    max: maxData,
                    right: "90%",
                    text: ["高", "低"], // 文本，默认为数值文本
                    calculable: true,
                    show: false,
                    inRange: {
                        //颜色数组
                        color: [
                            "rgba(1,60,128,1)",
                            "rgba(3,120,204,1)",
                            "rgba(1,128,236,1)",
                            "rgba(3,115,240,1)",
                            "rgba(1,190,239,1)",
                            "rgba(213,141,56,1)",
                            "rgba(212,177,66,1)",
                        ],
                    },
                },
                series: [
                    {
                        type: "map",
                        map: "area",
                        aspectScale: 0.9,
                        layoutCenter: ["50%", "50%"], //地图位置
                        layoutSize: "80%",
                        label: {
                            normal: {
                                show: true,
                                fontFamily: "SourceHanSansCN",
                                fontSize: "14",
                                color: "#FEFEFE",
                            },
                            emphasis: {
                                show: true,
                                fontFamily: "SourceHanSansCN",
                                fontSize: "14",
                                color: "#FEFEFE",
                            },
                        },
                        // tooltip:{} tooltip可结合formatter、div、css实现样式
                        itemStyle: {
                            normal: {
                                borderColor: "rgba(0,0,0, 0.6)",
                                borderWidth: 1,
                                areaColor: {
                                    type: "linear-gradient",
                                    x: 0,
                                    y: 300,
                                    x2: 0,
                                    y2: 0,
                                    colorStops: [
                                        {
                                            offset: 0,
                                            color: "#0a56BC", // 0% 处的颜色
                                        },
                                        {
                                            offset: 1,
                                            color: "#12A6F2",
                                        },
                                    ],
                                    global: true, // 缺省为 false
                                },
                            },
                            emphasis: {
                                shadowColor: "rgba(0, 0, 0, 1)",
                                shadowBlur: 10,
                                shadowOffsetX: 5,
                                shadowOffsetY: 5,
                                areaColor: {
                                    type: "linear-gradient",
                                    x: 0,
                                    y: 0,
                                    x2: 0,
                                    y2: 1,
                                    colorStops: [
                                        {
                                            offset: 0,
                                            color: "#F5B615", // 0% 处的颜色
                                        },
                                        {
                                            offset: 1,
                                            color: "#E27B0d",
                                        },
                                    ],
                                },
                            },
                        },
                        zlevel: 1,
                        data: this.lildata,
                    },
                ],
            };
            option && myChart.setOption(option);
        },
    },
    mounted() {
        // console.log(this.sourceUrl);
        this.innitecharts();
        this.innitecharts1();
        $(() => {
            $("#search").on("click", () => {
                $.ajax({
                    type: "GET",
                    msg: "GET请求成功",
                    url: "http://43.138.26.129:8888/energy/big",
                    async: true,
                    data: {
                        address: this.result,
                    },
                    success: (res) => {
                        // console.log(res.data);
                        this.$message.success("已切换至" + this.allresult);
                       
                        var add = 0;
                        for (var i = 0; i < res.data.length; i++) {
                            for (var j = 0; j < res.data[i].length; j++) {
                                add = add + res.data[i][j].数量;
                            }
                        }
                        this.sum = add;
                        this.confignum2.number[0] = this.sum;
                        this.confignum2 = { ...this.confignum2 };
                        const arr1 = [];
                        const data = res.data;
                        for (var i = 0; i < res.data.length; i++) {
                            for (var j = 0; j < res.data[i].length; j++) {
                                arr1.push([
                                    data[i][j].名称,
                                    data[i][j].运营方,
                                    data[i][j].数量,
                                ]);
                            }
                        }
                        //如果想把值整合在一起，可以var一个数组，然后将值push进数组里
                        this.config = {
                            header: ["名称", "充电商", "数量"],
                            align: ["center", "center", "center"],
                            data: arr1,
                            waitTime: 1500,
                            headerBGC: "#2F55AF",
                            columnWidth: [300, 150, 150],
                        };
                        const arr2 = [];
                        // echarts饼状图数据处理
                        for (var i = 0; i < res.data.length; i++) {
                            for (var j = 0; j < res.data[i].length; j++) {
                                arr2.push(res.data[i][j].运营方);
                            }
                        }
                        // var newarr2 = [...new Set(arr2)];
                        // 对应区的充电商的所有种类
                        // this.arry = newarr2;
                        var arrecharts = arr2;
                        var ary = {};
                        for (let i = 0; i < arrecharts.length; i++) {
                            if (arrecharts[i] in ary) {
                                ary[arrecharts[i]] = ary[arrecharts[i]] + 1;
                            } else {
                                ary[arrecharts[i]] = 1;
                            }
                        }
                        // 对应区的所有对应充电商的数量
                        var dawd = Object.keys(ary);
                        this.arry.length = dawd.length;
                        // var echartsdata = [{value:,name:}]
                        var valueArr = [];
                        var nameArr = [];
                        // for(var i= 0;i<ary.length;i++){

                        // }
                        for (let o in ary) {
                            valueArr.push(o);
                            nameArr.push(ary[o]);
                        }
                        var arrylast = [];
                        for (var i = 0; i < dawd.length; i++) {
                            arrylast.push({
                                value: nameArr[i],
                                name: valueArr[i],
                            });
                        }
                        this.arry = arrylast;
                        // }
                    },
                });
            });
        });

        $(() => {
            $("#search").on("click", () => {
                $.ajax({
                    type: "GET",
                    msg: "GET请求成功",
                    url: "http://43.138.26.129:8888/gdp",
                    async: true,
                    data: {
                        province: this.provincename,
                    },
                    success: (res) => {
                        if (this.allresult[0] == "北京市") {
                            var citynum = 11563;
                        } else if (this.allresult[0] == "天津市") {
                            var citynum = 7356;
                        } else if (this.allresult[0] == "上海市") {
                            var citynum = 12909;
                        } else if (this.allresult[0] == "重庆市") {
                            var citynum = 8956;
                        }
                        var provincearr = [];
                        var cityname = [];
                        // console.log(res.data);
                        for (var i = 0; i < res.data.length; i++) {
                            var obj = res.data[i][0];
                            // console.log(obj);
                            var pdata = Object.values(obj);
                            provincearr.push(pdata[2]);
                            cityname.push(pdata[1]);
                            if (this.allresult[1] == Object.values(obj)[1]) {
                                // console.log(Object.values(obj)[0]);
                                var citynum = Object.values(obj)[2];
                                // console.log(citynum);
                            }
                        }
                        this.sumcity = citynum;
                        // console.log(this.sumcity);
                        this.confignum1.number[0] = this.sumcity;
                        this.confignum1 = { ...this.confignum1 };

                        // console.log(cityname);
                        // console.log(provincearr);
                        var arrlun = [];

                        for (var i = 0; i < cityname.length; i++) {
                            arrlun.push({
                                name: cityname[i],
                                value: provincearr[i],
                            });
                        }
                        this.config1 = {
                            data: arrlun,
                        };
                        this.lildata = arrlun;
                        var sumpdata = 0;
                        for (var i = 0; i < provincearr.length; i++) {
                            sumpdata = sumpdata + provincearr[i];
                        }
                        // this.provincename=sumpdata;
                        this.sumprovince = sumpdata;
                        this.confignum.number[0] = this.sumprovince;
                        this.confignum = { ...this.confignum };
                        // console.log(sumpdata);
                    },
                });
            });
        });
    },
    watch: {
        echartstitle(val) {
            this.echartstitle = val;
            this.innitecharts();
        },
        arry(val) {
            this.arry = val;
            this.innitecharts();
            // console.log(this.arry);
        },
        lildata(val) {
            this.lildata = val;
            this.innitecharts1();
        },
        mapjson(val) {
            this.mapjson = val;
            this.innitecharts1();
        },
    },
};
window.onload = function () {
    document.getElementsByClassName("amap-logo")[0].remove();
    document.getElementsByClassName("amap-copyright")[0].remove();
};
</script>