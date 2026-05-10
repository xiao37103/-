<template>
    <div class="bjc">
        <div class="test">
            <div class="model_box">
                <div style="flex:0 1 30%;">
                    <dv-decoration-10 style="height:5px;margin-top:55px;" />
                </div>
                <div style="flex:0 1 40%;">
                    <div class="flexbox">
                        <dv-decoration-8 style="height:60px;flex:0 1 20%;" />
                        <dv-decoration-11 style="height:60px;flex:0 1 60%;color:white;" class="pos">
                            <router-link to="/Souye" style="color:white;text-decoration: none;">基于粒子群算法充电站选址优化</router-link>
                        </dv-decoration-11>
                        <dv-decoration-8 :reverse="true" style="height:60px;flex:0 1 20%;" />
                    </div>
                </div>
                <div style="flex:0 1 30%;">
                    <dv-decoration-10 style="height:5px;transform:rotateY(180deg);margin-top:55px;" />
                </div>
            </div>
        </div>
        <div class="content">
            <div class="model_box">
                <div style="flex:0 1 28%" class="leftbox">
                    <dv-decoration-7
                        style="width:300px;height:30px;position:absolute;top:13%;left:4%;color:white;">选择城市与适应度</dv-decoration-7>
                    <dv-border-box-4 :reverse="true" style="height:185px;position:aboslute;top:39%;"
                        :color="['rgba(13, 206, 235, 0.88)',]">
                        <el-select v-model="value" placeholder="请选择" style="margin-top:20px;margin-left:30px;">
                            <el-option v-for="item in cities" :key="item.value" :label="item.label" :value="item.label">
                                <span style="float: left">{{ item.label }}</span>
                                <span style="float: right; color: #8492a6; font-size: 13px">{{ item.value }}</span>
                            </el-option>
                        </el-select>
                        <el-button type="primary" plain id="search" @click="searchcode">点击搜索</el-button>
                        <el-select v-model="value1" value-key="item.value" placeholder="选择舒适度" clearable filterable
                            style="width:102px;position:absolute;top:20px;right:76px;">
                            <el-option v-for="item in fitness" :key="item.value" :label="item.label"
                                :value="item.value"></el-option>
                        </el-select>
                    </dv-border-box-4>
                    <div
                        style="background-color:rgba(1,1,1,0.1);width:95%;height:69%;position:relative;top:70%;left:4%;color:white;">
                        <dv-decoration-7
                            style="width:100%;height:8%;color:white;position:absolute;top:-17%;">部署建议</dv-decoration-7>
                        <div style="text-indent:2em;position:absolute;top:15px;">
                            在充电站选址部署的时候，在给出的选址结果上，应该结合实际的城市规划以及地形地貌等因素选址。
                            <div style="text-indent:2em;">
                                适应度越大，其选址在不考虑地形及自然环境条件下，更合理。
                            </div>
                        </div>

                    </div>

                    <dv-border-box-7 :color="['rgba(13, 206, 235, 0.88)']"
                        style="height:30%;width:700px;position: absolute;left:1%;bottom:0;">
                        <dv-decoration-7 style="width:100%;height:8%;color:white;">{{ this.value
                        }}现有充电站部署分布（绿色标点）</dv-decoration-7>
                        <dv-scroll-board :config="config" style="width:100%;height:85%;margin-top:2%;" />
                    </dv-border-box-7>
                    <dv-border-box-7 :color="['rgba(13, 206, 235, 0.88)']"
                        style="height:30%;width:720px;margin-top:5px;position:absolute;right:1%;bottom:0;">
                        <dv-decoration-7 style="width:100%;height:8%;color:white;">{{ this.value
                        }}市区新增充电站选址坐标（蓝色标点）</dv-decoration-7>
                        <dv-scroll-board :config="config1" style="width:100%;height:85%;margin-top:2%;"
                            @click="handleCarouselClick" />
                    </dv-border-box-7>
                </div>
                <div style="flex:0 1 70%">
                    <el-amap style="width:70%;height:60%;position:absolute;top:9%;left:29%;" :show-label="false"
                        :center="center" :zoom="zoom" @init="initMap" showLabel=true>
                        <el-amap-layer-labels>
                            <!-- 使用v-for指令循环生成多个标点 -->
                            <el-amap-label-marker v-for="(pos, index) in positions" :key="index"
                                :visible="labelOptions.visible" :position="pos" :text="labelOptions.text"
                                :icon="labelOptions.icon" @click="clickMarker(index)" />
                        </el-amap-layer-labels>
                        <el-amap-layer-labels>
                            <!-- 使用v-for指令循环生成多个标点 -->
                            <el-amap-label-marker v-for="(pos, index) in positions1" :key="index"
                                :visible="labelOptions1.visible" :position="pos" :text="labelOptions1.text"
                                :icon="labelOptions1.icon" @click="clickMarker(index)" />
                        </el-amap-layer-labels>
                        <el-amap-layer-labels ref="labels" :visible="visible" :collision="false" @init="init">
                            <el-amap-label-marker v-for="(marker, index) in markers" :key="index"
                                :position="marker.position" :text="marker.text" :icon="marker.icon"></el-amap-label-marker>
                        </el-amap-layer-labels>
                    </el-amap>
                </div>
            </div>
        </div>
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
            config: {
                header: ["地址", "经纬度"],
                data: [],
                align: ["center", "center"],
            },
            config1: {
                header: ["经度", "纬度", "适应度"],
                align: ["center", "center", "center"],
            },
            selectedData: [], // 缓存选中的数据,
            index: "",//获取对应的索引值
            visible: true,
            value: "",
            citycode: "",
            cities: [
                {
                    value: "Hefeishi",
                    label: "合肥市",
                    abcode: "340100",
                },
                {
                    value: "Wuhushi",
                    label: "芜湖市",
                    abcode: "340200",
                },
                {
                    value: "Huaibeishi",
                    label: "淮北市",
                    abcode: "340600",
                },
                {
                    value: "Shuzhoushi",
                    label: "宿州市",
                    abcode: "341300",
                },
                {
                    value: "Fuyangshi",
                    label: "阜阳市",
                    abcode: "341200",
                },
                {
                    value: "Bozhoushi",
                    label: "亳州市",
                    abcode: "341600",
                },
                {
                    value: "Bengbushi",
                    label: "蚌埠市",
                    abcode: "340300",
                },
                {
                    value: "Chuzhoushi",
                    label: "滁州市",
                    abcode: "341100",
                },
                {
                    value: "Huainanshi",
                    label: "淮南市",
                    abcode: "340400",
                },
                {
                    value: "Luanshi",
                    label: "六安市",
                    abcode: "341600",
                },
                {
                    value: "Maanshanshi",
                    label: "马鞍山市",
                    abcode: "340500",
                },
                {
                    value: "Tonglingshi",
                    label: "铜陵市",
                    abcode: "340700",
                },
                {
                    value: "Chizhoushi",
                    label: "池州市",
                    abcode: "341700",
                },
                {
                    value: "Huangshanshi",
                    label: "黄山市",
                    abcode: "341000",
                },
                {
                    value: "Xuanchengshi",
                    label: "宣城市",
                    abcode: "341800",
                },
                {
                    value: "Anqing",
                    label: "安庆市",
                    abcode: "340800",
                },
            ],
            fitness: [
                { value: 0.9 },
                { value: 0.8 },
                { value: 0.7 },
                { value: 0.6 },
                { value: 0.5 },
                { value: 0.4 },
                { value: 0.3 },
                { value: 0.2 },
                { value: 0.1 },
            ],
            value1: "0.6",
            result: "",
            pages: "",
            // 地图数据
            center: [117.35600518554686, 31.83289748972472],
            zoom: 10,
            labelOptions: {
                visible: true,
                text: {
                    arr: [],
                    content: "",
                    direction: "right",
                    style: {
                        fontSize: 15,
                        fillColor: "#fff",
                        strokeColor: "rgba(255,0,0,0.5)",
                        strokeWidth: 2,
                        padding: [3, 10],
                    },
                },
                icon: {
                    type: "image",
                    image:
                        "https://a.amap.com/Loca/static/loca-v2/demos/mock_data/charging_pile.png",
                    size: [48, 75],
                    anchor: "center",
                },
            },
            labelOptions1: {
                visible: true,
                text: {
                    arr: [],
                    content: "",
                    direction: "right",
                    style: {
                        fontSize: 15,
                        fillColor: "#fff",
                        strokeColor: "rgba(255,0,0,0.5)",
                        strokeWidth: 2,
                        padding: [3, 10],
                    },
                },
                icon: {
                    image:
                        "https://a.amap.com/jsapi_demos/static/images/poi-marker.png",
                    anchor: "bottom-center",
                    size: [25, 34],
                    clipOrigin: [459, 92],
                    clipSize: [50, 68],
                },
            },
            positions: [],
            positions1: [],
            markers: [],
            markersposition: []
        };
    },
    methods: {
        initMap() {
        },
        changeVisible() {
            this.labelOptions.visible = !this.labelOptions.visible;
        },
        handleCarouselClick(item) {
            this.selectedData = item.row;
            console.log(this.selectedData);
            console.log(this.positions1);
            this.markersposition[0] = this.selectedData[0];
            this.markersposition[1] = this.selectedData[1];
            const newMarker = {
                position: this.markersposition,  // 标点的位置坐标
                text: {
                    content: '选中目标地址',  // 标点的文本内容 
                    // 其他文本样式配置...
                },
                icon: {
                    image: 'https://webapi.amap.com/theme/v1.3/markers/n/mark_r.png',
                    // 其他图标样式配置...
                }
            };

            this.markers.push(newMarker);  // 将新的标点信息添加到markers数组中


        },
        init(layer) {

        },
        searchcode() {
            if (this.value == "合肥市") {
                this.citycode = 340100;
            } else if (this.value == "芜湖市") {
                this.citycode = 340200;
            } else if (this.value == "蚌埠市") {
                this.citycode = 340300;
            } else if (this.value == "淮南市") {
                this.citycode = 340400;
            } else if (this.value == "马鞍山市") {
                this.citycode = 340500;
            } else if (this.value == "淮北市") {
                this.citycode = 340600;
            } else if (this.value == "铜陵市") {
                this.citycode = 340700;
            } else if (this.value == "安庆市") {
                this.citycode = 340800;
            } else if (this.value == "黄山市") {
                this.citycode = 341000;
            } else if (this.value == "滁州市") {
                this.citycode = 341100;
            } else if (this.value == "阜阳市") {
                this.citycode = 341200;
            } else if (this.value == "宿州市") {
                this.citycode = 341300;
            } else if (this.value == "六安市") {
                this.citycode = 341500;
            } else if (this.value == "亳州市") {
                this.citycode = 341600;
            } else if (this.value == "池州市") {
                this.citycode = 341700;
            } else if (this.value == "宣城市") {
                this.citycode = 341800;
            }
            // console.log(this.citycode);
        },
    },
    mounted() {
        $(() => {
            $("#search").on("click", () => {
                $.ajax({
                    type: "GET",
                    msg: "GET请求成功",
                    url: "http://43.138.26.129:8888/gaode",
                    async: true,
                    data: {
                        address: this.value,
                    },
                    success: (res) => {
                        this.$message.success("已切换至" + this.value);
                        if (this.value == "芜湖市") {
                            this.center = [118.384108423, 31.3660197875];
                        } else if (this.value == "淮北市") {
                            this.center = [116.791447429, 33.9600233054];
                        } else if (this.value == "宿州市") {
                            this.center = [116.988692412, 33.6367723858];
                        } else if (this.value == "阜阳市") {
                            this.center = [115.820932259, 32.9012113306];
                        } else if (this.value == "亳州市") {
                            this.center = [115.787928245, 33.8712105653];
                        } else if (this.value == "蚌埠市") {
                            this.center = [117.357079866, 32.9294989067];
                        } else if (this.value == "滁州市") {
                            this.center = [118.324570351, 32.3173505954];
                        } else if (this.value == "淮南市") {
                            this.center = [117.018638863, 32.6428118237];
                        } else if (this.value == "六安市") {
                            this.center = [116.505252683, 31.7555583552];
                        } else if (this.value == "马鞍山市") {
                            this.center = [118.515881847, 31.6885281589];
                        } else if (this.value == "铜陵市") {
                            this.center = [117.819428729, 30.9409296947];
                        } else if (this.value == "池州市") {
                            this.center = [117.494476772, 30.6600192482];
                        } else if (this.value == "黄山市") {
                            this.center = [118.293569632, 29.7344348562];
                        } else if (this.value == "宣城市") {
                            this.center = [116.988692412, 33.6367723858];
                        } else if (this.value == "安庆市") {
                            this.center = [117.058738772, 30.5378978174];
                        } else if (this.value == "合肥市") {
                            this.center = [
                                117.35600518554686,
                                31.83289748972472,
                            ];
                        }
                        var searchresult = res.data;
                        var locations = searchresult.map((obj) =>
                            obj.location
                                .split(",")
                                .map((numStr) => parseFloat(numStr))
                        );
                        this.positions = locations;
                        const namesAndlocation = [];

                        searchresult.forEach((item) => {
                            namesAndlocation.push([item.name, item.location]);
                        });
                        // console.log(namesAndlocation);
                        this.config = {
                            header: ["地址", "经纬度"],
                            align: ["center", "center"],
                            data: namesAndlocation,
                            waitTime: 800,
                            // headerBGC: "#2F55AF",
                            // columnWidth: [200, 150],
                        };
                    },
                });
            });
        });
        $(() => {
            $("#search").on("click", () => {
                $.ajax({
                    type: "GET",
                    msg: "GET请求成功",
                    url: "http://43.138.26.129:8888/gaodeadd",
                    async: true,
                    data: {
                        address: this.value,
                        comfort_norm: this.value1
                    },
                    success: (res) => {
                        // console.log(res.data);
                        var searchresult = res.data;
                        const namesAndlocation = [];
                        searchresult.forEach((item) => {
                            namesAndlocation.push([
                                item.longitude,
                                item.latitude,
                                item.comfort_norm,
                            ]);
                        });
                        var addresult = [];

                        searchresult.forEach((item) => {
                            addresult.push([item.longitude, item.latitude]);
                        });
                        this.positions1 = addresult;
                        // console.log(addresult);

                        this.config1 = {
                            header: ["经度", "纬度", "适应度"],
                            align: ["center", "center", "center"],
                            data: namesAndlocation,
                            waitTime: 800,
                        };
                    },
                });
            });
        });
    },
    watch: {
        citycode(val) {
            this.citycode = val;
            this.initMap();
        },
        value(val) {
            this.value = val;
            this.initMap();
        },
        center(val) {
            this.center = val;
            this.initMap();
        },
        value1(val) {
            this.value1 = val;
            this.initMap();
        },
        selectedData(val) {
            this.selectedData = val;
            this.initMap();
        }
    },
};
</script>

<style scoped>
#search {
    position: absolute;
    width: 76%;
    top: 96px;
    left: 28px;
}

.amap-wrap {
    border-radius: 3% !important;
}

.content {
    margin-top: 10px;
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

.btnbcack {
    display: block;
    width: 100px;
    height: 50px;
    margin-top: 0px;
}

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