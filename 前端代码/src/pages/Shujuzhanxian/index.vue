<template >
    <div class="shuju">
        <!-- 全屏容器 -->
        <!-- <dv-full-screen-container class="container bg">
            <div class="model_box" style="z-index:50;">
                <div style="flex:0 1 30%;">
                    <dv-decoration-10 style="height:5px;" />
                </div>
                <div style="flex:0 1 40%;">
                    <div class="flexbox">
                        <dv-decoration-8 style="height:50px;flex:0 1 30%;" />
                        <dv-decoration-11
                            style="height:60px;flex:0 1 40%;color:white;"
                            class="tc box_middle"
                        >数据中心</dv-decoration-11>
                        <dv-decoration-8 :reverse="true" style="height:50px;flex:0 1 30%;" />
                    </div>
                </div>
                <div style="flex:0 1 30%;">
                    <dv-decoration-10 style="height:5px;transform:rotateY(180deg);" />
                </div>
        </div>-->

        <Map style="width:100%;height:100vh;"></Map>

        <!-- </dv-full-screen-container> -->
        <!-- 数字翻牌器 -->
        <!-- <dv-border-box-7
                        style="width:100%;height:150px;padding-top:10px;"
                        :color="['MidnightBlue']"
                    >
                        <dv-digital-flop :config="confignum" style="width:200px;height:50px;" />
        </dv-border-box-7>-->
        <!-- 搜索 -->
        <!-- <div class="searchfa">
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
        </div>-->
        <!-- <div style="width:100%;height:100vh;">
            <Map></Map>
        </div>-->
        <!-- 底部容器
            <div class="model_box">
                <div style="flex:0 1 50%">
                    <dv-scroll-board
                        :config="config"
                        style="width:745x;height:285px;margin-top:2px;"
                        id="carouselTable"
                    />
                </div>
                <div style="flex:0 1 50%">
                    <dv-scroll-ranking-board :config="config1" style="width:100%;height:285px" />
                </div>
        </div>-->
    </div>
</template>

<script>
import Map from "@/components/Map/index";
// import Datashowheader from "@/components/Datashowheader/index";
// import Map1 from '@/components/Map1/index'
// import CountTo from "vue-count-to";
// import dataV from "@jiaminghi/data-view";
// import provinceAndcity from "../../pages/province.json";
// import { lazyAMapApiLoaderInstance } from "vue-amap";
// import $ from "jquery";
// import { mapGetters } from "vuex";
// import store from "@/store";
// import { regionData, CodeToText, TextToCode } from "element-china-area-data";
// import AMapLoader from "@amap/amap-jsapi-loader";
export default {
    name: "Shujuzhanxian",
    components: {
        Map,
    },
    data() {
        return {
            // config: {
            //     header: ["名称", "运营商", "数量"],
            //     align: ["center", "center", "center"],
            //     data: [],
            //     waitTime: 1500,
            //     headerBGC: "#191970",
            // },
            // config1: {
            //     data: [
            //         {
            //             name: "周口",
            //             value: 55,
            //         },
            //         {
            //             name: "南阳",
            //             value: 120,
            //         },
            //         {
            //             name: "西峡",
            //             value: 78,
            //         },
            //         {
            //             name: "驻马店",
            //             value: 66,
            //         },
            //         {
            //             name: "新乡",
            //             value: 80,
            //         },
            //         {
            //             name: "信阳",
            //             value: 45,
            //         },
            //         {
            //             name: "漯河",
            //             value: 29,
            //         },
            //     ],
            // },
            // confignum: {
            //     number: [100],
            //     content: "{nt}个",
            // },
            // district: null,
            // options: regionData,
            // selectedoptions: [],
            // result: "",
            // allresult: "",
            // 三级联动的文字地址
            // arr: [],
            // 市区的充电桩数量以及市辖区
            // sum: "",
            // form: {
            //     province: "",
            //     city: "",
            //     district: "",
            // },
        };
    },
    computed: {
        // ...mapGetters(["user", "baseApi"]),
    },
  
    // created() {
    //     store.dispatch("getInfo").then(() => {
    //         this.form = {
    //             id: this.user.id,
    //             nickName: this.user.nickName,
    //             gender: this.user.gender,
    //             phone: this.user.phone,
    //             province: this.user.province,
    //             city: this.user.city,
    //             country: this.user.country,
    //         };
    //         if (this.form.country !== "") {
    //             this.selectedOptions = [
    //                 TextToCode[this.form.province].code,
    //                 TextToCode[this.form.province][this.form.city].code,
    //                 TextToCode[this.form.province][this.form.city][
    //                     this.form.country
    //                 ].code,
    //             ];
    //         } else {
    //             this.selectedOptions = [
    //                 TextToCode[this.form.province].code,
    //                 TextToCode[this.form.province][this.form.city].code,
    //             ];
    //         }
    //     });
    // },
    // methods: {
    //     submit() {
    //         console.log(this.selectedoptions);
    //     },
    //     handlechange(value) {
    //         var arr = [];
    //         arr[0] = CodeToText[value[0]];
    //         arr[1] = CodeToText[value[1]];
    //         arr[2] = CodeToText[value[2]];
    //         console.log(arr);
    //         var arr1 = [];
    //         arr1[0] = arr[1];
    //         arr1[1] = arr[2];
    //         this.arr = arr1;
    //         console.log(this.arr);
    //         var result = arr1.join("");
    //         this.result = result;
    //         console.log(result);
    //         var arr2 = [];
    //         arr2[0] = arr[0];
    //         arr2[1] = arr[1];
    //         arr2[2] = arr[2];
    //         this.allresult = arr2;
    //     },
    //     click() {},

    //     power(n) {
    //         this.confignum.number[0] = this.sum;
    //         this.confignum = { ...this.confignum }; //对象解构，更新props
    //     },
    // },
    // mounted() {
    //     setInterval(() => {
    //         this.power(300);
    //     }, 4000);
    //     //挂载阶段调用，DOM初始化完成进行地图初始化
    //     console.log(this.result);
    //     $(() => {
    //         $("#search").on("click", () => {
    //             $.ajax({
    //                 type: "GET",
    //                 msg: "GET请求成功",
    //                 url: "http://127.0.0.1/energy/big",
    //                 data: {
    //                     address: this.result,
    //                 },
    //                 success: (res) => {
    //                     console.log(res.data);
    //                     this.$message.success("已切换至" + this.allresult);
    //                     if (res.data) var add = 0;
    //                     for (var i = 0; i < res.data.length; i++) {
    //                         for (var j = 0; j < res.data[i].length; j++) {
    //                             add = add + res.data[i][j].数量;
    //                         }
    //                     }
    //                     this.sum = add;
    //                     const arr1 = [];
    //                     const data = res.data;
    //                     for (var i = 0; i < res.data.length; i++) {
    //                         for (var j = 0; j < res.data[i].length; j++) {
    //                             arr1.push([
    //                                 data[i][j].名称,
    //                                 data[i][j].运营方,
    //                                 data[i][j].数量,
    //                             ]);
    //                         }
    //                     }
    //                     console.log(arr1);
    //                     console.log(typeof result);
    //                     // 如果想把值整合在一起，可以var一个数组，然后将值push进数组里
    //                     this.config = {
    //                         header: ["名称", "充电商", "数量"],
    //                         align: ["center", "center", "center"],
    //                         data: arr1,
    //                         waitTime: 1500,
    //                         headerBGC: "#2F55AF",
    //                     };
    //                 },
    //             });
    //         });
    //     });

    //     // 调用高德API对关键字的搜索每天只有100次调用次数
    //     // $(() => {
    //     //     $("#search").on("click", () => {
    //     //         $.ajax({
    //     //             type: "GET",
    //     //             msg: "GET请求成功",
    //     //             url:
    //     //                 "https://restapi.amap.com/v5/place/text?key=236c12905eccbfec33a50ed02e383138&keywords=北京市西城区国家大剧院公共充电站",
    //     //             success: (res) => {
    //     //                 console.log(res);
    //     //             },
    //     //         });
    //     //     });
    //     // });
    // },
};
</script>



<style scoped>
::-webkit-scrollbar {
    width: 0px;
    height: 0px !important;
    display: none;
}

.shuju {

    width: 100%;
    height: 100vh;
    position: fixed;
}
body {
    margin: 0;
    padding: 0;
    /* position: relative; */
}
/* *{
    margin: 0;
    padding: 0;
} */

/* .bg { */
/* background-color: #000001; */
/* } */
/* background: url(@/assets/imgs/viewbigscreen.png) no-repeat center center; */
/* background-color: rgba(0, 0, 1);
    background-size: cover; */

/* .model_box {
    display: flex;
    justify-content: space-between;
    margin: 5px;
} */

html {
    overflow-y: hidden !important;
}
/* .el-scrollbar__wrap {overflow-x: hidden !important;} */

/* .flexbox {
    display: flex;
}
.tc {
    text-align: center;
}
.box_middle {
    align-self: center;
} */
/* 搜索框 */
/* .searchfa {
    height: 150px;
    margin-left: 10px;
    padding-top: 50px;
    padding-left: 20px;
}
#search {
    width: 50px;
    height: 30px;
    background-color: rgb(10, 68, 119);
    border: none;
}
#container {
    width: 747px;
    height: 480px;
    margin-left: 8px;
    margin-top: 17px;
} */
</style>

