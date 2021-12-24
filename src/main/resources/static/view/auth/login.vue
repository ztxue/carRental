<template>
  <div id="app">
    <div class="background">

      <el-row>
        <div  class="grid-content bg-purple-light login">

          <div class="ms-title">汽车共享管理系统</div>

          <el-form :model="loginForm"  :rules="rules" ref="loginForm" class="ruleForm" v-loading="loading"
                   element-loading-text="拼命加载中...">

            <el-form-item prop="loginName">
              <el-input type="email" v-model="loginForm.email"  auto-complete="off" placeholder="邮箱"></el-input>
            </el-form-item>

            <el-form-item prop="password">
              <el-input type="password" v-model="loginForm.password"  auto-complete="off" placeholder="密码"></el-input>
            </el-form-item>

            <el-form-item>
              <el-button type="success" @click="login" class="login-bt">登录</el-button>
            </el-form-item>

            <router-link to="/register">
              <p class="login-regist"><a href="#">没有邮箱？点击注册</a></p>
            </router-link>
          </el-form>
          <el-checkbox v-model="loginForm.checked" class="el-checkbox">记住我</el-checkbox>
          <p class="forget" ><a href="#" @click="fogetPassword()">忘记密码？</a></p>
        </div>
      </el-row>
    </div>
  </div>
</template>

<script>
define(["vue", "axios"], function(Vue, axios) {
  return Vue.component("login", {
    template: template,
    data: function() {
      return {
        loading: false,
        loginForm:{
          email: '',
          password: '',
        },
        rules: {
        }
      };
    },
    methods: {
      login:function () {
        var _this = this;
        axios.post('/login', _this.loginForm).then(function (response) {
          console.log(response.data.data);
          if (response.data.data){
            sessionStorage.setItem("userId",response.data.data.id);
            sessionStorage.setItem("userName",response.data.data.userName);
            sessionStorage.setItem("password",response.data.data.password);
            sessionStorage.setItem("email",response.data.data.email);
            sessionStorage.setItem("phone",response.data.data.phone);
            sessionStorage.setItem("sex",response.data.data.sex);
            _this.$router.push({ name: '/home' });
          } else {
            _this.$message.error('用户名或密码错误');
          }
        }).catch(function (error) {
          console.log(error);
        });

      }
    }
  });
});
</script>

<style>

.background{
  position: relative;
  background-image: url("../../assets/img/car.jpg");
  background-size: 100% ;
  background-repeat: no-repeat;
  width: 100%;
  height: 100%;
}

.login{
  margin-top: 18%;
  margin-bottom: 18%;
  margin-left: 67%;
  padding: 4%;
  width: 25%;
  height: 30%;
  border-radius: 30px;
  background: rgba(255, 255, 255, 0.3);
  border: darkturquoise solid 1px;
}
.ms-title{
  width: 100%;
  line-height: 50px;
  margin-bottom: 12px;
  text-align: center;
  font-size: 20px;
  color: #fff;
  border-bottom: 1px solid #ddd;
}
.login-regist{
  font-size: 16px;

}
.login-regist a{
  color: aliceblue;
  text-decoration:none;
}
.login-regist a:hover{
  color: #00a4ff;
}
.el-checkbox{

}
.forget{
  font-size: 14px;
}
.forget a{
  color: aliceblue;
  text-decoration:none;
}
.forget a:hover{
  color: #00a4ff;
}
</style>