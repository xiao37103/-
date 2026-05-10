<template>
    <div class="bgc1">
        <!-- 头部 -->
        <div class="test">
            <div class="model_box">
                <div style="flex:0 1 30%;">
                    <router-link to="/Dierye" class="btnbcack1">
                        <i class="el-icon-back"></i>
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
                    <dv-decoration-10
                        style="height:5px;transform:rotateY(180deg);margin-top:55px;"
                    />
                </div>
            </div>
        </div>
        <!-- 搜索 -->
        <el-select
            v-model="value"
            placeholder="请选择"
            style="margin-top:20px;margin-left:180px;width:200px;"
        >
            <el-option
                v-for="item in cities"
                :key="item.value"
                :label="item.label"
                :value="item.label"
            >
                <span style="float: left">{{ item.label }}</span>
                <span style="float: right; color: #8492a6; font-size: 13px">{{ item.value }}</span>
            </el-option>
        </el-select>
        <el-button type="primary" size="default" @click="change">切换</el-button>

        <!-- <div v-show="itsvalue">
            <dv-decoration-4 style="width:5px;height:40%;position:absolute;left:24%;top:10%;" />
            <dv-border-box-6 style="width:50%;height:40%;position:absolute;left:25%;top:10%;">
            </dv-border-box-6>
            <dv-decoration-4 style="width:5px;height:40%;position:absolute;right:24%;top:10%;" />
            <dv-decoration-4 style="width:5px;height:40%;position:absolute;left:24%;top:55%;" />
            <dv-border-box-6 style="width:50%;height:40%;position:absolute;left:25%;top:55%;"></dv-border-box-6>
            <dv-decoration-4 style="width:5px;height:40%;position:absolute;right:24%;top:55%;" />
        </div>

       
            <div  v-show="seconboxvalue">
                <dv-decoration-4 style="width:5px;height:40%;position:absolute;left:24%;top:10%;" />
                <dv-border-box-2 style="width:;500px;height:500px;">
                    <img src="./imgs/三维散点图.png"  style="width:500px;height:500px;" />
                </dv-border-box-2>
                <dv-decoration-4 style="width:5px;height:40%;position:absolute;right:24%;top:10%;" />
        </div>-->
        <!-- <dv-decoration-4 style="width:5px;height:40%;position:absolute;left:24%;top:15%;" /> -->

        <Navagation4 v-show="itsvalue" style="margin-left:5%;margin-top:5%;"></Navagation4>
        <Navagation v-show="seconboxvalue" style="margin-left:5%;margin-top:5%;"></Navagation>
        <!-- <dv-decoration-4 style="width:5px;height:40%;position:absolute;right:24%;top:15%;" /> -->

        <router-view></router-view>
        <dv-decoration-7
            style="width:200px;height:30px;position:absolute;left:63%;top:30%;color:white;"
        >相关性分析结果</dv-decoration-7>
        <dv-border-box-9 style="width:40%;height:300px;position:absolute;left:50%;top:35%;">
            <div style="color:white;font-size:15px;text-indent:2em;margin-top:20px;width:95%;margin-left:20px;"> 1. GDP与新能源汽车占比正相关：在图中，我们可以看到 GDP 越高，新能源汽车占比也越高，这说明了经济发展与新能源汽车普及程度之间存在着正相关关系。</div>
            <div style="color:white;font-size:15px;text-indent:2em;margin-top:20px;width:95%;margin-left:20px;">
                 2. 人口与新能源汽车占比之间没有明显关系：尽管新能源汽车占比在不地区之间存在巨大的差异，但我们可以看到，人口数量并没有成为影响新能源汽车普及程度的主要因素，而主要的影响因素是国家或地区自身的经济和政策环境。
            </div>
            <div style="color:white;font-size:15px;text-indent:2em;margin-top:20px;width:95%;margin-left:20px;">
                3. 在某些地区，新能源汽车普及程度较高：通过观察图中分布较密集的区域，我们可以发现，在一些地区，新能源汽车普及程度已经相当高，这些地区有可能是对新能源汽车投资、政策支持等方面做得比较好的地区。
            </div>
             <div style="color:white;font-size:15px;text-indent:2em;margin-top:20px;width:95%;margin-left:20px;">
                4. 解决安徽省充电桩分布问题：可以借助相关性分析结果，对充电桩分布进行调控。
            </div>
        </dv-border-box-9>
    </div>
</template>

<script>
// import * as echarts from "echarts";
import Navagation4 from "@/pages/Dierselection/Navagation4.vue";
import Navagation from "@/pages/Dierselection/Navagation.vue";
export default {
    data() {
        return {
            itsvalue: true,
            seconboxvalue: false,
            cities: [
                {
                    label: "盒图",
                },
                {
                    label: "三维散点图",
                },
            ],
            value: "",
        };
    },
    components: {
        Navagation4,
        Navagation,
    },
    mounted() {
        // this.initecharts();
    },
    methods: {
        change() {
            if (this.value == "盒图") {
                this.itsvalue = true;
                this.seconboxvalue = false;
            } else if (this.value == "三维散点图") {
                this.itsvalue = false;
                this.seconboxvalue = true;
            }
        },
    },
};
</script>

<style scoped>
/* .btnback1{
      display:block;
    width: 100px;
    height: 50px;
    margin-top: 0px;
} */
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
.bgc1 {
    position: fixed;
    left: 0;
    top: 0;
    width: 100%;
    height: 100vh;
    background: url(@/assets/imgs/R.jpg);
}
.pos {
    margin-top: 10px;
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
</style>