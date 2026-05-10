<template>
    <div>
        <div class="header">
            <div class="lefttitle">数据分析后台</div>
            <div class="rightuser">
                <label for>
                    <el-avatar icon="el-icon-user-solid"></el-avatar>
                    <a class="btn">退出登录</a>
                </label>
            </div>
        </div>
    </div>
</template>

<script>
export default {
    data() {
        return {
            name: "managerHeader",
        };
    },
};
</script>

<style scoped>
body {
    padding: 0;
    margin: 0;
}

.header {
    position: fixed;
    right: 0;
    width: 85%;
    height: 50px;
    background-color: rgba(13, 34, 64, 0.993);
    overflow-x: hidden;
    margin-left: 15%;
    border-bottom: 1px solid rgb(143, 158, 171);

}
.lefttitle {
    position: absolute;
    left: 525px;
    line-height: 50px;
    color: aliceblue;
    font-size: 18px;
}
.rightuser {
    position: absolute;
    right: 50px;
    line-height: 50px;
}
.el-icon-user-solid {
    font-size: 23px;
    margin-top: 3px;
}
.el-avatar{
    height: 38px;
    width: 38px;
    margin-top: 6px;
}

</style>