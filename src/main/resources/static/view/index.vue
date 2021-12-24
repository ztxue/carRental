<template>
  <el-container>
    <el-header style="background-color: #F2F6FC;position: fixed;width: 100%;z-index: 100">
      <div style="margin-left: 25%;color: #777474;font-size: 35px"><b>★汽车共享管理系统-</b>
        <i style="font-size: 28px;color: #777474">CarSharingManagementSystem</i>★</div>
      <div class="user-status">
        <img src="../assets/img/head2.png" alt="..." class="img-circle"/>
        <p class="user-name">{{ userName }}</p>
      </div>
    </el-header>
    <el-container>
      <el-aside style="width: 150px;background-color: #DCDFE6;height: 9999px;position: fixed;top: 60px;z-index: 20">
        <ul class="menu-ul">
          <li class="menu-btn">
            <router-link to="/index/home"><span
                class="glyphicon glyphicon-home"></span>
              <p>首页</p>
            </router-link>
          </li>
          <li class="menu-btn">
            <router-link to="/index/userSys"><span
                class="glyphicon glyphicon-user"></span>
              <p>用户管理</p>
            </router-link>
          </li>
          <li class="menu-btn">
            <router-link to="/index/carSys"><span
                class="glyphicon glyphicon-leaf"></span>
              <p>车辆管理</p>
            </router-link>
          </li>
          <li class="menu-btn">
            <router-link to="/index/customer"><span
                class="glyphicon glyphicon-phone-alt"></span>
              <p>客户管理</p>
            </router-link>
          </li>
          <li class="menu-btn">
            <router-link to="/index/record"><span
                class="glyphicon glyphicon-th-large"></span>
              <p>订单管理</p>
            </router-link>
          </li>
          <li class="menu-btn">
            <router-link to="/index/finance"><span
                class="glyphicon glyphicon-jpy"></span>
              <p>支付管理</p>
            </router-link>
          </li>
        </ul>
      </el-aside>
      <router-view style="position: relative;left: 150px;top:60px;width:calc(100% - 150px);z-index: 10"></router-view>
    </el-container>
  </el-container>
</template>

<script>
define(["vue", "axios"], function (Vue, axios) {
  return Vue.component("index", {
    template: template,
    data: function data() {
      return {
        selectedLink: 'home',
        userName: sessionStorage.getItem("userName"),
      };
    },
  });
});
</script>
<style>
.menu-ul {
  margin-left: 2px;
  padding-left: 0;
  width: 150px;
  text-align: center;
}

.glyphicon {
  font-size: 24px;
  color: #67C23A;
}

.menu-btn {
  padding-top: 40px;
}

.img-circle {
  height: 40px;
  width: 40px;
}

.user-name {
  color: #67C23A;
}

.user-status {
  position: absolute;
  right: 100px;
  top: 3px;
}

.menu-btn p {
  color: #67C23A;
}
</style>