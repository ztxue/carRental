<template>
  <el-main>
    <h1 style="color: #909399">支付管理</h1>
    <div class="split-sys"></div>
    <!--二维码-->
    <div style="margin-left: 100px">
      <div
          style="margin: 50px 50px 0 100px;padding: 35px;width: 400px;height: 400px;background-color: #ece6e6;float: left">
        <img src="../assets/img/Alipay.jpg">
        <h3 style="margin: 20px 0px 0px 130px">支付宝</h3></div>
      <div
          style="margin: 50px 50px 0 100px;padding: 35px;width: 400px;height: 400px;background-color: #ece6e6;float: left">
        <img src="../assets/img/Wechat.jpg">
        <h3 style="margin: 20px 0px 0px 143px">微信</h3></div>
    </div>
  </el-main>
</template>


<script>
define(["vue", "axios", "echarts"], function (Vue, axios, echarts) {
  return Vue.component("finance", {
    template: template,
    data: function () {
      return {
        selectDate: [],
      };
    },
  });
});
</script>

<style>
.split-sys {
  height: 3px;
  width: 90%;
  background-color: #909399;
}
</style>