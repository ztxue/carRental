<template>
  <el-main>
    <h1 style="color: #909399">客户管理</h1>
    <div class="split-sys"></div>
    <div class="data-table">
      <div style="margin-bottom: 15px;width: 100%">
        <div class="el-row">
          <el-col :span="16">
            <div style="width: 200px">
              <el-input placeholder="customer name" v-model="keywords">
                <el-button slot="append" icon="el-icon-search" @click="search(1,7)"></el-button>
              </el-input>
            </div>
          </el-col>
          <el-col :span="8">
            <div>
              <el-button type="info" plain @click="setSaveType('add')">添加客户</el-button>
            </div>
          </el-col>
        </div>
        <el-dialog title="客户信息" :visible.sync="dialogFormVisible">
          <el-form :model="formData">
            <el-form-item label="姓名" :label-width="formLabelWidth">
              <el-input v-model="formData.customerName" auto-complete="off"></el-input>
            </el-form-item>
            <el-form-item label="性别" :label-width="formLabelWidth">
              <el-select v-model="formData.sex" placeholder="请选择">
                <el-option label="男" value="男"></el-option>
                <el-option label="女" value="女"></el-option>
              </el-select>
            </el-form-item>
            <el-form-item label="年龄" :label-width="formLabelWidth">
              <el-input v-model="formData.age" auto-complete="off"></el-input>
            </el-form-item>
            <el-form-item label="手机号" :label-width="formLabelWidth">
              <el-input v-model="formData.phone" auto-complete="off"></el-input>
            </el-form-item>
            <el-form-item label="身份证号" :label-width="formLabelWidth">
              <el-input v-model="formData.number" auto-complete="off"></el-input>
            </el-form-item>
            <el-form-item label="联系地址" :label-width="formLabelWidth">
              <el-input v-model="formData.address" auto-complete="off"></el-input>
            </el-form-item>
          </el-form>
          <div slot="footer" class="dialog-footer">
            <el-button @click="dialogFormVisible = false">取 消</el-button>
            <el-button type="primary" @click="saveCustomer">保 存</el-button>
          </div>
        </el-dialog>
      </div>
      <el-table
          :data="tableData"
          stripe
          style="margin: auto">
        <el-table-column
            prop="id"
            label="ID"
            width="100">
        </el-table-column>
        <el-table-column
            prop="createTime"
            label="注册时间"
            width="120"
        >
        </el-table-column>
        <el-table-column
            prop="customerName"
            label="姓名"
            width="100"
        >
        </el-table-column>
        <el-table-column
            prop="sex"
            label="性别"
            width="100"
        >
        </el-table-column>
        <el-table-column
            prop="age"
            label="年龄"
            width="100"
        >
        </el-table-column>
        <el-table-column
            prop="phone"
            label="手机号"
            width="120"
        >
        </el-table-column>
        <el-table-column
            prop="number"
            label="身份证号"
            width="180">
        </el-table-column>
        <el-table-column
            prop="address"
            label="联系地址"
            width="300">
        </el-table-column>
        <el-table-column
            prop="leaseNum"
            label="租赁次数"
            width="100"
        >
        </el-table-column>
        <el-table-column
            label="操作"
        >
          <template slot-scope="scope">
            <el-button @click="editRow(scope.row,'edit')" type="text" size="small">编辑</el-button>
            <el-button type="text" size="small" @click="removeCustomer(scope.row)">删除</el-button>
          </template>
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
  return Vue.component("customer", {
    template: template,
    data: function () {
      return {
        tableData: [],
        total: '',
        currentPage: 1,
        keywords: '',
        searchStatus: false,
        formLabelWidth: '120px',
        dialogFormVisible: false,
        formData: {
          id: '',
          customerName: '',
          phone: '',
          number: '',
          leaseNum: '',
          sex: '',
          age: '',
          address: '',
          createTime: ''
        },
        saveType: '',
      };
    },
    methods: {
      getAllCustomer: function (pageNum, pageSize) {
        var _this = this;
        axios.post('customer/getAllCustomer', {"pageNum": pageNum, "pageSize": pageSize}).then(function (response) {
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
          this.getAllCustomer(val, 7)
        }
      },
      search: function (pageNum, pageSize) {
        var _this = this;
        axios.post('customer/searchCustomer', {
          "pageNum": pageNum,
          "pageSize": pageSize,
          "keywords": _this.keywords
        }).then(function (response) {
          console.log(response.data.dataList);
          _this.tableData = response.data.dataList;
          _this.total = response.data.total;
          _this.searchStatus = true;
        }).catch(function (error) {
          console.log(error);
        });
      },
      saveCustomer: function () {
        var _this = this;
        if (_this.saveType === "add") {
          _this.addCustomer();
        }
        if (_this.saveType === "edit") {
          _this.editCustomer();
        }
        _this.dialogFormVisible = false;
        _this.getAllCustomer(1, 7);
      },
      addCustomer: function () {
        var _this = this;
        axios.post('customer/addCustomer', _this.formData).then(function (response) {
          console.log(response)
        }).catch(function (error) {
          console.log(error);
        });
      },
      editCustomer: function () {
        var _this = this;
        axios.post('customer/modifyCustomer', _this.formData).then(function (response) {
          if (response.data.data) {
            _this.$message({
              type: 'success',
              message: '修改成功!'
            });
          } else {
            _this.$message.error('修改失败');
          }
        }).catch(function (error) {
          console.log(error);
        });
      },
      setSaveType: function (type) {
        this.saveType = type;
        this.dialogFormVisible = true;
      },
      editRow: function (data, type) {
        var _this = this;
        _this.setSaveType(type);
        _this.formData = data;
      },
      removeCustomer(data) {
        var _this = this;
        this.$confirm('此操作将永久删除客户, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          let param = new URLSearchParams();
          param.append("id", data.id)
          axios.post('customer/removeCustomer', param).then(function (response) {
            if (response.data.data) {
              _this.$message({
                type: 'success',
                message: '删除成功!'
              });
            } else {
              _this.$message.error('删除失败');
            }
            _this.getAllCustomer(1, 7);
          }).catch(function (error) {
            console.log(error);
          });
        }).catch(() => {
          _this.$message({
            type: 'info',
            message: '已取消删除'
          });
        });

      }
    },
    mounted: function () {
      this.getAllCustomer(1, 7);
    },
    watch: {
      dialogFormVisible: function () {
        var _this = this;
        if (!_this.dialogFormVisible) {
          _this.formData = {
            id: '',
            customerName: '',
            phone: '',
            number: '',
            leaseNum: '',
            sex: '',
            age: '',
            address: '',
            createTime: ''
          }
        }
        _this.getAllCustomer(1, 7);
      }
    }
  });
});
</script>

<style>
.split-sys {
  height: 3px;
  width: 90%;
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