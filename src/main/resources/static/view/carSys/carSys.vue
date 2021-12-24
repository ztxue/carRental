<template>
    <el-main>
        <h1 style="color: #909399">车辆管理</h1>
        <div class="split-sys"></div>
        <div class="car-menu">
            <el-row>
                <el-col :span="8">
                    <a>
                        <div @click="routeToPage('stock')">
                            <img src="../../../assets/img/box1.png" class="img-rounded">
                            <br/><span class="car-font">车辆库存</span>
                        </div>
                    </a>
                </el-col>
                <el-col :span="8">
                    <a>
                        <div @click="routeToPage('class')">
                            <img src="../../../assets/img/box5.png" class="img-rounded">
                            <br/><span class="car-font">车辆类别</span>
                        </div>
                    </a>
                </el-col>
                <el-col :span="8">
                    <a>
                        <div @click="routeToPage('service')">
                            <img src="../../../assets/img/box7.png" class="img-rounded">
                            <br/><span class="car-font">车辆信息</span>
                        </div>
                    </a>
                </el-col>
            </el-row>
        </div>
    </el-main>
</template>

<script>
    define(["vue", "axios"], function(Vue, axios) {
        return Vue.component("carSys", {
            template: template,
            methods:{
                routeToPage: function (dest) {
                    this.$router.replace({name: dest});
                }
            },
        });
    });
</script>

<style>
    .split-sys{
        height: 3px;
        width: 90%;
        background-color: #909399;
    }
    .car-menu{
        margin-top: 5%;
        margin-left: 5%;
        width: 37%;
        text-align: center;

    }
    .car-font {
        color: #303133;
    }
    a{
        cursor:pointer;
    }
</style>