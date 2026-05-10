<template>
    <div>
     <!-- <el-scrollbar style="height:100%;"> -->
           <div v-if="$route.meta.keepAlive">
            <HeaderRow></HeaderRow>
        </div>
        <router-view></router-view>
     <!-- </el-scrollbar> -->
    </div>
</template>

<script>
import HeaderRow from "./components/Header/index";
export default {
    name: "App",
    data() {
        return {
            sizeHeight:0
        };
    },
    components: {
        HeaderRow,
    },
};
</script>

<style >
::-webkit-scrollbar {
width: 0px;
height: 0px;
}
</style>