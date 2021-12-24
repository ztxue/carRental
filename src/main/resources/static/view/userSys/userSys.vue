<template>
  <el-main>
    <h1 style="color: #909399">用户管理</h1>
    <div class="split-sys"></div>
    <div class="data-table">
      <div style="margin-bottom: 15px;width: 240px">
        <el-input placeholder="user name" v-model="keywords">
          <el-button slot="append" icon="el-icon-search" @click="search(1,7)"></el-button>
        </el-input>
      </div>
      <el-table
          :data="tableData"
          stripe
          style="margin: auto">
        <el-table-column
            prop="id"
            label="ID"
        >
        </el-table-column>
        <el-table-column
            prop="userName"
            label="姓名"
        >
        </el-table-column>
        <el-table-column
            prop="phone"
            label="手机号"
        >
        </el-table-column>
        <el-table-column
            prop="jobNum"
            label="工号"
        >
        </el-table-column>
        <el-table-column
            prop="email"
            label="邮箱"
        >
        </el-table-column>
        <el-table-column
            prop="sex"
            label="性别"
        >
        </el-table-column>
      </el-table>
      <div class="block">
        <el-pagination
            @size-change="handleSizeChange"
            @current-change="handleCurrentChange"
            :current-page.sync="currentPage"
            :page-size="7"
            layout="prev, pager, next, jumper"
            :total="total">
        </el-pagination>
      </div>
    </div>
  </el-main>
</template>

<script>
define(["vue", "axios"], function (Vue, axios) {
  return Vue.component("userSys", {
    template: template,
    data: function () {
      return {
        tableData: [{
          id: '',
          userName: '',
          phone: '',
          jobNum: '',
          email: '',
          sex: '',
        }],
        total: '',
        currentPage: 1,
        keywords: '',
        searchStatus: false
      };
    },
    methods: {
      getUserList: function (pageNum, pageSize) {
        var _this = this;
        axios.post('user/getUserList', {"pageNum": pageNum, "pageSize": pageSize}).then(function (response) {
          console.log(response.data.dataList);
          _this.tableData = response.data.dataList;
          _this.total = response.data.total;
          console.log(_this.total)
        }).catch(function (error) {
          console.log(error);
        });
      },
      handleSizeChange(val) {
        console.log(`每页 ${val} 条`);
      },
      handleCurrentChange(val) {
        var _this = this;
        if (_this.searchStatus) {
          _this.search(val, 7)
        } else {
          this.getUserList(val, 7)
        }
      },
      search: function (pageNum, pageSize) {
        var _this = this;
        axios.post('user/searchUserByUserName', {
          "pageNum": pageNum,
          "pageSize": pageSize,
          "userName": _this.keywords
        }).then(function (response) {
          console.log(response.data.dataList);
          _this.tableData = response.data.dataList;
          _this.total = response.data.total;
          console.log(_this.total)
        }).catch(function (error) {
          console.log(error);
        });
      }
    },
    mounted: function () {
      this.getUserList(1, 7);
    }
  });
});
</script>

<style>
.split-sys {
  height: 3px;
  width: 100%;
  background-color: #909399;
}

.data-table {
  padding-top: 50px;
  padding-left: 20px;
}

.block {
  margin-left: 20%;
}
</style>