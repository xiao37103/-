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
            <el-amap-loca
                ref="loca"
                :amb-light="ambLight"
                :dir-light="dirLight"
                :point-light="pointLight"
            >
                <el-amap-loca-prism
                class="456"
                    :visible="visible"
                    :source-url="sourceUrl"
                    :layer-style="layerStyle"
                ></el-amap-loca-prism>
            </el-amap-loca>
        </el-amap>
        <!-- <div class="toolbar">
            <button type="button" name="button" @click="toggleVisible">{{visible ? '隐藏标记' : '显示标记'}}</button>
        </div>-->
    </div>
</template>

  <style>
/* .amap-demo {
    height: 300px;
}
.amap-page-container {
    height: 500px;
} */
 .amap-logo{
    display: none!important;
  }
  .amap-copyright{
   visibility:hidden!important;
  }

</style>

  <script>
var topConf = {
    上海市: "https://a.amap.com/Loca/static/loca-v2/demos/images/top-one.png",
    北京市: "https://a.amap.com/Loca/static/loca-v2/demos/images/top-two.png",
    广州市: "https://a.amap.com/Loca/static/loca-v2/demos/images/top-three.png",
};
module.exports = {
    name: "Map",
    data() {
        return {
            map: null,
            pl: null,
            zoom: 4,
            pitch: 55,
            center: [103.594884, 36.964587],
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

            sourceUrl:
                "https://a.amap.com/Loca/static/loca-v2/demos/mock_data/gdp.json",
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
                                    " 元" +
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
    methods: {
        toggleVisible() {
            // this.visible = !this.visible;
        },
        initMap(_map) {
            this.map = _map;
            console.log(this.$refs.map.$$getInstance());
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
                    url:
                        "https://a.amap.com/Loca/static/loca-v2/demos/mock_data/gdp.json",
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
                                        '); background-size: 100%;"><p style="margin: 7px 0 0 35px; height: 20px; line-height:20px;">' +
                                        props["名称"] +
                                        "人口 " +
                                        props["人口"] +
                                        "</p>" +
                                        '<p style="margin: 4px 0 0 35px; height: 20px; line-height:20px; color: #00a9ff; font-size: 13px;">' +
                                        props["GDP"] +
                                        " 元" +
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
                this.map.on("complete",  ()=> {
                    console.log(this.pl,"464");
                    setTimeout( () =>{
                        this.pl.show(500);
                      var di=  this.pl.addAnimate({
                            key: "height",
                            value: [0, 1],
                            duration: 500,
                            easing: "Linear",
                            transform: 2000, 
                            random: true,
                            delay: 8000,
                        });
                        console.log(di);
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
                    console.log(feat);
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
                                " 元</div>"
                        );
                    } else {
                        clickInfo.hide();
                    }
                });
            });
           
        },
        
    },
    mounted() {
        
    },
};
</script>