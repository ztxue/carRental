<template>
  <div id="app">
    <div class="background">

      <el-row>
        <div class="grid-content bg-purple-light register">

          <el-form :model="form" label-width="30%">
            <el-form-item label="用户名:">
              <el-input v-model="form.userName"></el-input>
            </el-form-item>
            <el-form-item label="密 码:">
              <el-input type="password" v-model="form.password"></el-input>
            </el-form-item>
            <el-form-item label="邮 箱:">
              <el-input v-model="form.email"></el-input>
            </el-form-item>
            <el-form-item label="工 号:">
              <el-input v-model="form.jobNum"></el-input>
            </el-form-item>
            <el-form-item label="手机号:">
              <el-input v-model="form.phone"></el-input>
            </el-form-item>
            <el-form-item label="性 别:">
              <el-select v-model="form.sex" placeholder="性别">
                <el-option label="男" value="男"></el-option>
                <el-option label="女" value="女"></el-option>
              </el-select>
            </el-form-item>
            <el-form-item>
              <el-button type="primary" @click="onSubmit">注 册</el-button>
            </el-form-item>
          </el-form>
        </div>
      </el-row>
    </div>

  </div>
</template>

<script>
define(["vue", "axios"], function (Vue, axios) {
  return Vue.component("register", {
    template: template,
    data: function () {
      return {
        form: {
          userName: "",
          password: "",
          email: "",
          phone: "",
          sex: "",
          jobNum: ""
        }
      };
    },
    methods: {
      onSubmit: function () {
        var _this = this;
        axios.post('register/addUser', _this.form).then(function (response) {
          console.log(response.data.data);
          if (response.data.data) {
            _this.$message({
              message: '注册成功',
              type: 'success'
            });
            _this.$router.push({name: 'login'});
          } else {
            _this.$message.error('注册失败');
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
#app {
  /*width: 50%;*/
  /*margin-left: 25%;*/
  /*margin-top: 10%;*/
}

.background {
  background-size: 100%;
  background-repeat: no-repeat;
  width: 100%;
  height: 100%;
  background-image: url("../../assets/img/car.jpg");
}

.register {
  margin: 6% 10% 7% 38%;

  padding: 3%;
  width: 25%;
  height: 40%;
  border-radius: 30px;
  background: rgba(255, 255, 255, 0.3);
  border: darkturquoise solid 1px;
}
</style>