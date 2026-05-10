<template>
    <!-- 背景轮播图 -->
    <div>
        <div>
            <el-carousel
                indicator-position="outside"
                style="z-index:1;"
                v-if="$route.path=='/Souye'"
            >
                <el-carousel-item v-for="item in imgList " :key="item.id">
                    <img :src="item.idView" class="image" />
                </el-carousel-item>
            </el-carousel>
        </div>
        <!--  导航栏 -->
        <div class="tou slideindone animated" style="z-index:3">
            <div class="logo">
                <div
                    style="font-size:30px;position: absolute;
                    left: 7px;
                    top: -34px;
                    font-family:STHupo;
                    font-weight:normal;"
                >正好充电</div>
                <div
                    style="font-size:23px;position: absolute;left:-38px;top:4px;font-family:STXingkai;"
                >充电桩部署分析系统</div>
            </div>
            <ul class="biao">
                <li>
                    <router-link class="undenglu center-to-head center-to-head1" to="/Souye">首页</router-link>
                </li>
                <li>
                    <router-link
                        class="undenglu center-to-head center-to-head2"
                        to="/Shujuzhanxian"
                        target="_blank"
                    >数据展现</router-link>
                </li>
                <li>
                    <router-link
                        class="undenglu center-to-head center-to-head3"
                        to="/Dierye"
                        target="_blank"
                    >数据分析</router-link>
                </li>
                <li>
                    <!-- <div class="undenglu">
                     {{dateFormat(date)}}
                    </div>-->
                    <router-link
                        class="undenglu center-to-head center-to-head3"
                        to="/Dierselection/Analysis1"
                    >部署分析</router-link>
                </li>
                <li>
                    <router-link class="denglu" to="/Login">{{message}}</router-link>
                </li>
            </ul>
        </div>
    </div>
</template>

<script scoped>
export default {
    name: "HeaderRow",
    data() {
        return {
            date: new Date(),
            imgList: [
                { id: 0, idView: "./static/imgs/8.jpg" },
                { id: 1, idView: "./static/imgs/7.jpg" },
                { id: 2, idView: "./static/imgs/3.jpg" },
            ],
            // message:''
        };
    },
    computed: {
        message() {
            // this.message=this.$store.state.message;
            return this.$store.state.message;
        },
    },
    methods: {
        dateFormat(time) {
            var date = new Date(time);
            var year = date.getFullYear();
            /* 在日期格式中，月份是从0开始的，因此要加0
             * 使用三元表达式在小于10的前面加0，以达到格式统一  如 09:11:05
             * */
            var month =
                date.getMonth() + 1 < 10
                    ? "0" + (date.getMonth() + 1)
                    : date.getMonth() + 1;
            var day =
                date.getDate() < 10 ? "0" + date.getDate() : date.getDate();
            var hours =
                date.getHours() < 10 ? "0" + date.getHours() : date.getHours();
            var minutes =
                date.getMinutes() < 10
                    ? "0" + date.getMinutes()
                    : date.getMinutes();
            var seconds =
                date.getSeconds() < 10
                    ? "0" + date.getSeconds()
                    : date.getSeconds();
            // 拼接
            return (
                year +
                "-" +
                month +
                "-" +
                day +
                " " +
                hours +
                ":" +
                minutes +
                ":" +
                seconds
            );
        },
    },
    mounted() {
        //显示当前日期时间
        let _this = this; // 声明一个变量指向Vue实例this，保证作用域一致
        this.timer = setInterval(() => {
            _this.date = new Date(); // 修改数据date
        }, 1000);
    },
    beforeDestroy() {
        if (this.timer) {
            clearInterval(this.timer); // 在Vue实例销毁前，清除我们的定时器
        }
    },
};

window.addEventListener("scroll", function () {
    let tou = document.querySelector(".tou");

    /*  tou.classList.toggle("bian",window.scrollY>0); */
    if (window.scrollY > 0) {
        tou.classList.add("bian");
    } else {
        tou.classList.remove("bian");
    }
});
</script>

<style >
* {
    margin: 0;
    padding: 0;
    box-sizing: border-box;
}
img {
    width: 100%;
    height: 100%;
}
body {
    height: 200vh;
}
/*时间的样式 */
/* 轮播图的样式 */
.el-carousel__container {
    position: relative;
    height: 600px !important;
}
/* .el-carousel__item:nth-child(2n) {
    background: url(./imgs/1-2012221T114.jpg);
} */

/* .el-carousel__item:nth-child(2n + 1) {
    background: url(./imgs/1-2012221T114.jpg);
} */
/*导航栏的样式 */
.tou {
    position: fixed;
    top: 0;
    left: 0;
    padding: 25px 100px;
    width: 100%;
    display: flex;
    justify-content: space-between;
    align-items: center;
    transition: 0.5s;
}
/* 左侧logo */

.logo {
    position: relative;
    font-size: 35px;
    font-weight: 900;
    letter-spacing: 1px;
    color: aliceblue;
    left: 10%;
    width: 400px;
}

.logo::before {
    content: "";
    position: absolute;
    left: -60px;
    top: -3px;
    width: 50px;
    height: 50px;
    /* background-image: url(./imgs/logo.png); */
    background-size: 100%;
}
/* 导航栏右侧 */

.biao {
    position: relative;
    display: flex;
    justify-content: space-evenly;
    align-content: center;
    list-style: none;
    margin-right: 10px;
    width: 880px;
}

.biao li {
    height: 40px;
    position: relative;
}

.biao li .undenglu {
    position: relative;
    display: block;
    margin: 0 10px;
    font-size: 20px;
    font-weight: bold;
    color: aliceblue;
    text-decoration: none;
    height: 40px;
    line-height: 40px;
}

.biao .undenglu:hover {
    transition-property: color;
    transition-duration: 0.8s;
    transition-timing-function: ease-out;
    color: #66615b;
    /* border-bottom: 3px solid #66615b; */
}
/* 字体下划线动画 */
.biao li .center-to-heada,
.biao li .center-to-head:link,
.biao li .center-to-head:visited,
.biao li .center-to-head:focus {
    text-decoration: none;
}
.biao li .center-to-head {
    position: relative;
}

.biao li .center-to-head1::after {
    content: "";
    display: block;
    /*开始时候下划线的宽度为100%*/
    width: 100px;
    height: 3px;
    position: absolute;
    left: -30px;
    bottom: -1px;
    background: #66615b;
    transition: all 0.3s ease-in-out;
    /*通过transform的缩放scale来让初始时x轴为0*/
    transform: scale3d(0, 1, 1);
    /*将坐标原点移到元素的中间，以原点为中心进行缩放*/
    transform-origin: 50% 0;
}
.biao li .center-to-head1:hover::after {
    /*鼠标经过时还原到正常比例*/
    transform: scale3d(1, 1, 1);
}
.biao li .center-to-head2::after {
    content: "";
    display: block;
    /*开始时候下划线的宽度为100%*/
    width: 100px;
    height: 3px;
    position: absolute;
    left: -10px;
    bottom: -1px;
    background: #66615b;
    transition: all 0.3s ease-in-out;
    /*通过transform的缩放scale来让初始时x轴为0*/
    transform: scale3d(0, 1, 1);
    /*将坐标原点移到元素的中间，以原点为中心进行缩放*/
    transform-origin: 50% 0;
}
.biao li .center-to-head2:hover::after {
    /*鼠标经过时还原到正常比例*/
    transform: scale3d(1, 1, 1);
}
.biao li .center-to-head3::after {
    content: "";
    display: block;
    /*开始时候下划线的宽度为100%*/
    width: 100px;
    height: 3px;
    position: absolute;
    left: -10px;
    bottom: -1px;
    background: #66615b;
    transition: all 0.3s ease-in-out;
    /*通过transform的缩放scale来让初始时x轴为0*/
    transform: scale3d(0, 1, 1);
    /*将坐标原点移到元素的中间，以原点为中心进行缩放*/
    transform-origin: 50% 0;
}
.biao li .center-to-head3:hover::after {
    /*鼠标经过时还原到正常比例*/
    transform: scale3d(1, 1, 1);
}
/* 登录按钮变化 */

.biao li .denglu {
    display: block;
    width: 100px;
    height: 40px;
    font-size: 18px;
    border-radius: 20px;
    margin-top: 0px;
    border: none;
    font-weight: bold;
    color: #66615b;
    text-decoration: none;
    text-align: center;
    line-height: 40px;
    background-color: aliceblue;
}

.biao li .denglu:hover {
    transition-property: color, background-color;
    transition-duration: 0.8s;
    transition-timing-function: ease-out;
    background-color: #66615b;
    color: aliceblue;
}
/* 改变之后的样式 */

.bian {
    padding: 15px 100px;
    background-color: aliceblue;
}

/* 改变之后的字体 */
.bian .biao .denglu {
    transition-property: color, background-color;
    transition-duration: 0.8s;
    transition-timing-function: ease-out;
    background-color: aliceblue;
    color: #66615b;
    border: 1px solid #66615b;
}

.bian .biao .denglu:hover {
    background-color: #66615b;
    color: aliceblue;
}

.bian .logo,
.tou.bian .undenglu {
    color: #66615b;
}
/*  背景图样式 */
/* .bjimg {
            position: fixed;
            top: 0;
            left: 0;
            width: 100%;
            height: 100%;
            min-width: 1000px;
            z-index: -10;
            zoom: 1;
            background-color: #fff;
            background-image: url(./1-2012221T114.jpg);
            background-repeat: no-repeat;
            background-size: cover;
            -webkit-background-size: cover;
            -o-background-size: cover;
            background-position: center 0;
        } */

/* .bjimg {
    width: 100%;
    height: 400px;
    background: url(D:\计算机设计大赛制作\jsapp\src\assets\1-2012221T114.jpg)
        no-repeat;
} */

@keyframes slideindone {
    0% {
        visibility: visible;
        transform: translateY(-100%);
    }
    100% {
        transform: translateY(0);
    }
}

.slideindone {
    animation-name: slideindone;
}

.animated {
    animation-duration: 1s;
    animation-fill-mode: both;
}
</style>