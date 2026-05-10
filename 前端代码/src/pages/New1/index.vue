<template>
    <div>
        <div class="bj"></div>
        <h5 class="title">2022年12月全国电动汽车充换电基础设施运行情况</h5>
        <div class="line">
            <div class="date">发布时间:2023-01-12 15:47</div>
            <div class="souce">来源:中国充电联盟</div>
        </div>
        <el-divider></el-divider>
        <div class="pra">
            <p><span class="bloder">公共充电基础设施运行情况</span>，2022年12月比11月公共充电桩增加6.6万台，12月同比增长56.7%。截至2022年12月，联盟内成员单位总计上报公共充电桩179.7万台，其中直流充电桩76.1万台、交流充电桩103.6万台。从2022年1月~12月，月均新增公共充电桩约5.4万台。</p>
            <p><span class="bloder">公共充电基础设施省、区、市运行情况</span>，广东、江苏、浙江、上海、北京、湖北、山东、安徽、河南、福建TOP10地区建设的公共充电桩占比达71.3%。全国充电电量主要集中在广东、江苏、四川、浙江、河北、福建、上海、陕西、湖南、北京等省份，电量流向以公交车和乘用车为主，环卫物流车、出租车等其他类型车辆占比较小。2022年12月全国充电总电量约21.4亿度，较上月增加1.5亿度，同比增长82.8%，环比增长7.6%。2022年1~12月全国充电总电量为213.2亿度。</p>
            <p><span class="bloder">公共充电基础设施运营商运行情况</span>，截止到2022年12月，全国充电运营企业所运营充电桩数量TOP15，分别为：特来电运营36.3万台、星星充电运营34.3万台、云快充运营25.9万台、国家电网运营19.6万台、小桔充电运营9.4万台、蔚景云运营7.3万台、深圳车电网运营6.9万台、南方电网运营6.1万台、万城万充运营4.8万台、汇充电运营4.6万台、依威能源运营4.2万台、万马爱充运营2.6万台、上汽安悦运营2.4万台、中国普天运营2.3万台、蔚蓝快充运营1.9万台。这15家运营商占总量的93.8%，其余的运营商占总量的6.2%。</p>
            <p><span class="bloder">充电基础设施整体运行情况</span>，2022年1～12月，充电基础设施增量为259.3万台，其中公共充电桩增量同比上涨91.6%，随车配建私人充电桩增量持续上升，同比上升225.5%。截止2022年12月，全国充电基础设施累计数量为521.0万台，同比增加99.1%。2022年公共充电站增量为3.7万座，保有量为11.1万座。</p>
            <p><span class="bloder">充电基础设施与电动汽车对比情况</span>充电基础设施与电动汽车对比情况，2022年1～12月，充电基础设施增量为259.3万台，新能源汽车销量688.7万辆，充电基础设施与新能源汽车继续爆发式增长。桩车增量比为1：2.7 ，充电基础设施建设能够基本满足新能源汽车的快速发展。</p>
            <img src="./imgs/微信图片_20230207171016.png" alt="12月份" style="width:600px;height:400px;display:block;margin:0 auto;">
        </div>
    </div>
</template>

<script>
export default {};
</script>

<style scoped>
.title {
    display: block;
    margin: 0 auto;
    font-size: 30px;
    margin-left: 425px;
}
/* .line{
    width: 800px;
} */
.bj {
    width: 100%;
    height: 400px;
    background: url(./imgs/1-2012221T114.jpg);
}
.date {
    float: left;
    width: 153px;
    height: 15px;
    font-size: 0.75rem;
    color: #999999;
    margin-left: 606px;
}
.souce {
    float: right;
    width: 150px;
    height: 15px;
    font-size: 0.75rem;
    color: #999999;
    margin-right: 420px;
}
/* 段落样式 */
.pra {
    width: 800px;
    margin: 0 auto;
}
p {
    text-indent: 2em;
}
.bloder{
    font-weight: bolder;
}
</style>