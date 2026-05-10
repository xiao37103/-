<template>
    <div>
        <!-- <dv-full-screen-container class="container"> -->
            <div class="model_box" >
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
            </div>
        <!-- </dv-full-screen-container> -->
    </div>
</template>

<script>
export default {
    name:"Datashowheader",
    data() {
        return {
            
        }
    },
};
</script>

<style scoped>
.container {
    /* position: relative; */
    height: 10vh;
    width: 100%;
}
.model_box {
    background-color: rgba(83, 138, 211,1);
    display: flex;
    justify-content: space-between;
    /* height: 60%; */
    /* margin: 5px; */
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