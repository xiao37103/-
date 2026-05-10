<template>
    <div>
        <div class="b">
            <div class="slideinup animated text center-to-head center-to-head4">新闻资讯</div>
            <div class="message">
                <div class="bleft lcontainer" v-show="tabshow">
                    <el-carousel height="300px !important" direction="vertical" :autoplay="true">
                        <el-carousel-item v-for="item in imgList" :key="item.id">
                            <!-- <h3 class="image">{{ item.idView }}</h3> -->
                            <img :src="item.idView" class="img" />
                        </el-carousel-item>
                    </el-carousel>
                </div>
                <div class="bright rcontainer" v-show="tabshow">
                    <div class="boxt">
                        <a
                            href="http://www.gov.cn/zhengce/content/2020-11/02/content_5556716.htm"
                            target="_blank"
                            class="contentT"
                        >国务院办公厅关于印发新能源汽车产业发展规划（2021—2035年）的通知</a>
                        <div
                            class="contentli"
                        >发展新能源汽车是我国从汽车大国迈向汽车强国的必由之路，是应对气候变化、推动绿色发展的战略举措...</div>
                    </div>
                    <div class="boxt">
                        <a
                            href="http://www.sasac.gov.cn/n2588025/n2588119/c15046298/content.html"
                            target="_blank"
                            class="contentT"
                        >新基建开启充电桩建设新时代</a>
                        <div
                            class="contentli"
                        >在新基建政策的加持下，拥有巨大潜力的充电桩市场受到了众多资本的追捧，一批新技术、新模式开始加快落地和应用...</div>
                    </div>
                    <div class="boxt">
                        <router-link
                            to="/New3"
                            target="_blank"
                            class="contentT"
                        >2023年5月全国电动汽车充换电基础设施运行情况</router-link>
                        <div
                            class="contentli"
                        >在新基建政策的加持下，拥有巨大潜力的充电桩市场受到了众多资本的追捧，一批新技术、新模式开始加快落地和应用...</div>
                    </div>
                    <div class="boxt">
                        <router-link
                            to="/New4"
                            target="_blank"
                            class="contentT"
                        >2023年4月全国电动汽车充换电基础设施运行情况</router-link>
                        <div
                            class="contentli"
                        >公共充电基础设施运行情况，2023年4月比3月公共充电桩增加6.7万台，4月同比增长52.0%...</div>
                    </div>
                </div>
            </div>
        </div>
        <div class="b size">
            <div class="slideinup animated text center-to-head center-to-head4">关于我们</div>
            <el-row>
                <el-card
                    shadow="hover"
                    :body-style="{ padding: '0px' }"
                    style="width:250px;height:300px;margin-left:80px;display:inline-block;"
                >
                    <router-link
                        to="/Shujuzhanxian"
                        target="_blank"
                        class="button"
                        style="text-decoration:none;color:darkblue;margin-left:75px;"
                    >
                        <img
                            src="./imgs/show.png"
                            style="height:150px;width:100%;margin-top:-23px;"
                        />
                    </router-link>

                    <div style="padding: 14px;">
                        <span style="margin-left:75px;">数据展现</span>
                        <div style="font-size:12px;">介绍：借助于可视化展示全国已统计数据充电桩分布情况。</div>
                        <div class="bottom clearfix"></div>
                    </div>
                </el-card>
                <el-card
                    shadow="hover"
                    :body-style="{ padding: '0px' }"
                    style="width:250px;height:300px;margin-left:10%;display:inline-block;margin-top:40px;"
                >
                    <router-link
                        to="/Dierye"
                        target="_blank"
                        class="button"
                        style="text-decoration:none;color:darkblue;margin-left:75px;"
                    >
                        <img
                            src="./imgs/show1.png"
                            style="height:150px;width:100%;margin-top:-23px;"
                        />
                    </router-link>
                    <div style="padding: 14px;">
                        <span style="margin-left:75px;">数据分析</span>
                        <div style="font-size:12px;">介绍：以安徽省为例，对安徽省的充电桩分布问题进行分析展现，以及给出对应的策略建议。</div>
                        <div class="bottom clearfix"></div>
                    </div>
                </el-card>
                
                <el-card
                    shadow="hover"
                    :body-style="{ padding: '0px' }"
                    style="width:250px;height:300px;margin-left:80px;display:inline-block;margin-top:40px;"
                >
                    <router-link
                        to="/Dierselection/Analysis1"
                        target="_blank"
                        class="button"
                        style="text-decoration:none;color:darkblue;margin-left:75px;"
                    >
                        <img
                            src="./imgs/show3.png"
                            style="height:150px;width:100%;margin-top:-23px;"
                        />
                    </router-link>

                    <div style="padding: 14px;">
                        <span style="margin-left:75px;">部署分析</span>
                        <div style="font-size:12px;">介绍：基于粒子群算法给出充电站选址建议。</div>
                        <div class="bottom clearfix">
                          
                        </div>
                    </div>
                </el-card>
                <el-card
                    shadow="hover"
                    :body-style="{ padding: '0px' }"
                    style="width:250px;height:300px;margin-left:80px;display:inline-block;"
                >
                    <!-- <a
                        href="https://springdoc.cn/spring-boot/"
                        target="_blank"
                        style="text-decoration:none;color:darkblue;margin-left:75px;"
                    >
                     <img src="./imgs/show2.png" style="height:150px;width:100%;margin-top:-23px;" />
                    </a> -->
                    <router-link
                        to="/Dierselection/Analysis1"
                        target="_blank"
                        class="button"
                        style="text-decoration:none;color:darkblue;margin-left:75px;"
                    >
                        <img
                            src="./imgs/show3.png"
                            style="height:150px;width:100%;margin-top:-23px;"
                        />
                    </router-link>
                    <div style="padding: 14px;">
                        <span style="margin-left:75px;">项目主旨</span>
                        <div
                            style="font-size:12px;"
                        >介绍：从数据出发进行分析，给出安徽省充电桩的分布建议，同时通过算法分析给出充电站选址建议。</div>
                        <div class="bottom clearfix">
                            <!-- <router-link to="/Dierye" target="_blank"  class="button" style="text-decoration:none;color:darkblue;margin-left:75px;">点击跳转</router-link> -->
                        </div>
                    </div>
                </el-card>
            </el-row>
        </div>
    </div>
</template>

<script>
export default {
    name: "MiddleTop",
    data() {
        return {
            // currentDate: new Date(),
            scrollTopNum: "",
            tabshow: false,
            imgList: [
                { id: 0, idView: "./static/imgs/1.jpg" },
                { id: 1, idView: "./static/imgs/2.jpg" },
                { id: 2, idView: "./static/imgs/4.jpg" },
            ],
        };
    },
    mounted() {
        window.addEventListener("scroll", this.handleScroll, true);
    },
    methods: {
        handleScroll() {
            let top =
                document.documentElement.scrollTop ||
                document.body.scrollTop ||
                window.pageYOffset;
            this.scrollTopNum = top;
        },
        date() {},
    },
    watch: {
        scrollTopNum: function () {
            if (this.scrollTopNum > 180) {
                this.tabshow = true;
            } else {
                this.tabshow = false;
            }
        },
    },
};
</script>

<style scoped>
/* .el-card{
    box-shadow: 1px 1px 1px rgba(0, 0, 0, 0.15) !important;   
} */
.bottom:hover {
    color: #409eff;
}
.b {
    position: relative;
    width: 100%;
    background-color: rgb(246, 247, 248);
}
.size {
    height: 400px;
    margin-top: 100px;
}
img {
    display: block;
    width: 100%;
    height: 100%;
}
.text {
    width: 160px;
    font-size: 20px;
    margin-left: 45%;
    margin-top: 20px;
    font-size: 2.5rem;
    color: #323232;
}
@keyframes slideinup {
    0% {
        visibility: visible;
        transform: translateY(100%);
    }
    100% {
        transform: translateY(0);
    }
}
.slideinup {
    animation-name: slideinup;
}
.animated {
    animation-duration: 1s;
    animation-fill-mode: both;
}

/* 标题下划线 */
.center-to-head4::after {
    content: "";
    display: block;
    /*开始时候下划线的宽度为100%*/
    width: 160px;
    height: 3px;
    position: absolute;
    left: 0px;
    bottom: -1px;
    background: #66615b;
    transition: all 0.3s ease-in-out;
    /*通过transform的缩放scale来让初始时x轴为0*/
    transform: scale3d(0, 1, 1);
    /*将坐标原点移到元素的中间，以原点为中心进行缩放*/
    transform-origin: 50% 0;
}
.center-to-head4:hover::after {
    /*鼠标经过时还原到正常比例*/
    transform: scale3d(1, 1, 1);
}
/* 新闻资讯的内容*/
.message {
    position: relative;
    margin-top: 20px;
    width: 100%;
    height: 400px;
}
.bleft {
    position: absolute;
    left: 161px;
    float: left;
    width: 520px;
    height: 400px;
    margin-top: 30px;
}
.el-carousel__item h3 {
    color: #475669;
    font-size: 14px;
    opacity: 0.75;
    line-height: 200px;
    margin: 0;
}

.el-carousel__item:nth-child(2n) {
    background-color: #99a9bf;
}

.el-carousel__item:nth-child(2n + 1) {
    background-color: #d3dce6;
}
.bright {
    position: absolute;
    right: 148px;
    float: right;
    width: 520px;
    height: 360px;
}
.boxt {
    width: 530px;
    height: 90px;
    border-bottom: 1px solid #e4e5e6;
}
.contentT {
    text-decoration: none;
    color: #323232;
    font-weight: bold;
}
.contentT:hover {
    color: #66615b;
}
.contentli {
    font-size: 14px;
    color: #737373;
    margin-top: 14px;
}
.lcontainer {
    animation-name: leftbox;
    animation-duration: 1s;
    animation-timing-function: ease-in;
}
.rcontainer {
    animation-name: rightbox;
    animation-duration: 1s;
    animation-timing-function: ease-in;
}
@keyframes leftbox {
    0% {
        visibility: visible;
        transform: translateX(-100%);
    }
    100% {
        transform: translateX(0);
    }
}
@keyframes rightbox {
    0% {
        visibility: visible;
        transform: translateX(100%);
    }
    100% {
        transform: translateX(0);
    }
}
</style>