<template>
  <el-main>
    <h1 style="color: #909399">订单管理</h1>
    <div class="split-sys"></div>
    <div style="margin-top: 50px;margin-left: 20px;">
      <el-row>
        <el-col :span="8">
          <div style="margin-bottom: 15px;width: 240px">
            <el-date-picker
                v-model="selectDate"
                type="daterange"
                range-separator="至"
                start-placeholder="开始日期"
                end-placeholder="结束日期"
                value-format="yyyy-MM-dd"
                format="yyyy年MM月dd日">
            </el-date-picker>

          </div>
        </el-col>
        <el-col :span="4">
          <el-button type="info" @click="getRecordByTimeAndStatus(1,7)">查询</el-button>
        </el-col>
        <el-col :span="6">
          <div>
            <el-select v-model="selectStatus" placeholder="分类查看">
              <el-option label="已完成" value="1"></el-option>
              <el-option label="未完成" value="2"></el-option>
              <el-option label="查看全部" value="3"></el-option>
            </el-select>
          </div>
        </el-col>
        <el-col :span="6">
          <div>
            <el-button type="info" @click="dialogFormVisible = true">添加订单</el-button>
          </div>
        </el-col>
      </el-row>
      <el-table
          :data="tableData"
          stripe
          style="width: 90%">
        <el-table-column type="expand">
          <template slot-scope="props">
            <el-form label-position="left" inline class="demo-table-expand">
              <el-form-item label="订单ID">
                <span>{{ props.row.id }}</span>
              </el-form-item>
              <el-form-item label="押金">
                <span>{{ props.row.deposit }}</span>
              </el-form-item>
              <el-form-item label="预约时长">
                <span>{{ props.row.timeLong }}</span>
              </el-form-item>
              <el-form-item label="创建日期">
                <span>{{ props.row.createTime }}</span>
              </el-form-item>
              <el-form-item label="状态">
                <span>{{ props.row.status }}</span>
              </el-form-item>
              <el-form-item label="姓名">
                <span>{{ props.row.customer.customerName }}</span>
              </el-form-item>
              <el-form-item label="联系地址">
                <span>{{ props.row.customer.address }}</span>
              </el-form-item>
              <el-form-item label="租赁次数">
                <span>{{ props.row.customer.leaseNum }}</span>
              </el-form-item>
              <el-form-item label="联系电话">
                <span>{{ props.row.customer.phone }}</span>
              </el-form-item>
              <el-form-item label="身份证号">
                <span>{{ props.row.customer.number }}</span>
              </el-form-item>
              <el-form-item label="汽车品牌">
                <span>{{ props.row.carData.brand }}</span>
              </el-form-item>
              <el-form-item label="颜色">
                <span>{{ props.row.carData.color }}</span>
              </el-form-item>
              <el-form-item label="座数">
                <span>{{ props.row.carData.seating }}</span>
              </el-form-item>
              <el-form-item label="车牌号">
                <span>{{ props.row.carData.number }}</span>
              </el-form-item>
              <el-form-item label="日租价格">
                <span>{{ props.row.carData.dayPrice }}</span>
              </el-form-item>
              <el-form-item label="月租价格">
                <span>{{ props.row.carData.monthPrice }}</span>
              </el-form-item>
              <el-form-item label="超公里价格">
                <span>{{ props.row.carData.superPrice }}</span>
              </el-form-item>
              <el-form-item label="管理员">
                <span>{{ props.row.carUser.userName }}</span>
              </el-form-item>
            </el-form>
          </template>
        </el-table-column>
        <el-table-column
            prop="id"
            label="订单id"
        >
        </el-table-column>
        <el-table-column
            prop="createTime"
            label="创建日期"
        >
        </el-table-column>
        <el-table-column
            prop="customer.customerName"
            label="客户姓名"
        >
        </el-table-column>
        <el-table-column
            prop="customer.phone"
            label="手机号">
        </el-table-column>
        <el-table-column
            prop="carData.brand"
            label="品牌">
        </el-table-column>
        <el-table-column
            prop="timeLong"
            label="预约时长">
        </el-table-column>
        <el-table-column
            prop="deposit"
            label="押金">
        </el-table-column>
        <el-table-column
            prop="status"
            label="状态">
        </el-table-column>
        <el-table-column
            label="操作"
        >
          <template slot-scope="scope">
            <el-button type="text" size="small" @click="endRecord(scope.row)">订单结算</el-button>
            <el-button type="text" size="small" @click="removeCustomer(scope.row)">删除</el-button>
          </template>
          ]]
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
    <el-dialog title="新建订单" :visible.sync="dialogFormVisible" @close="clearData">
      <el-form :model="form">
        <el-form-item label="客户" label-width="180">
          <el-select
              v-model="form.customerId"
              filterable
              remote
              reserve-keyword
              placeholder="请输入客户姓名"
              :remote-method="remoteMethod"
              :loading="loading">
            <el-option
                v-for="item in customerOptions"
                :key="item.value"
                :label="item.label"
                :value="item.value">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="车辆" label-width="120">
          <el-cascader
              :options="options"
              :show-all-levels="false"
              @change="carHandleChange"
              v-model="form.carData"
          ></el-cascader>
          <el-card class="box-card" v-if="details">
            <div class="text item">
              {{ '颜色: ' + carData.color }}
            </div>
            <div class="text item">
              {{ '座数: ' + carData.seating }}
            </div>
            <div class="text item">
              {{ '日租金: ' + carData.dayPrice + '￥' }}
            </div>
            <div class="text item">
              {{ '月租金: ' + carData.monthPrice + '￥' }}
            </div>
          </el-card>
        </el-form-item>
        <el-form-item label="预约时长" label-width="120">
          <el-input placeholder="请输入一个数字" v-model="form.timeLong" class="input-with-select" style="width: 50%">
            <div slot="suffix" style="text-align: center;height: 100%;padding: 10px">
              <span v-if="form.unit == '天'">天</span>
              <span v-if="form.unit == '月'">月</span>
            </div>
            <el-select v-model="form.unit" slot="prepend" placeholder="租赁方式">
              <el-option label="日租" value="天"></el-option>
              <el-option label="月租" value="月"></el-option>
            </el-select>
          </el-input>
        </el-form-item>
        <el-form-item label="预付押金" label-width="120">
          <el-input v-model="form.deposit" placeholder="预付押金" style="width: 50%"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="addRecord">确 定</el-button>
      </div>
    </el-dialog>
    <el-dialog
        title="订单结算"
        :visible.sync="dialogVisible"
        width="30%"
    >
      <el-form :model="balanceForm">
        <div class="text item">
          {{ "预约时长: " + balanceForm.timeLong }}
        </div>
        <div class="text item">
          {{ "实际时长:" + balanceForm.currentTimeLong }}
        </div>
        <div class="text item">
          {{ "预付金额: " + balanceForm.deposit }}
        </div>
        <div class="text item">
          {{ "应付金额: " + balanceForm.money }}
        </div>
        <div class="text item">
          {{ "应补金额: " + balanceForm.addMoney }}
        </div>
        <div class="text item">
          {{ "应退金额: " + balanceForm.reduceMoney }}
        </div>
      </el-form>
      <span slot="footer" class="dialog-footer">
                <el-button @click="dialogVisible = false">取 消</el-button>
                <el-button type="primary" @click="settlement">结 算</el-button>
            </span>
    </el-dialog>
  </el-main>
</template>

<script>
define(["vue", "axios"], function (Vue, axios) {
  return Vue.component("record", {
    template: template,
    data: function () {
      return {
        selectDate: [],
        selectStatus: '',
        total: '',
        currentPage: 1,
        tableData: [
          {
            carData: {},
            customer: {},
          }
        ],
        options: [],
        customerOptions: [],
        dialogFormVisible: false,
        form: {
          customerId: '',
          carData: [],
          timeLong: '',
          unit: '',
          deposit: ''
        },
        loading: false,
        carData: {
          seating: '',
          color: '',
          dayPrice: '',
          monthPrice: ''
        },
        details: false,
        dialogVisible: false,
        balanceForm: {
          timeLong: '',
          currentTimeLong: '',
          deposit: '',
          addMoney: 0,
          reduceMoney: 0,
          money: 0,
          id: '',
          carId: '',
          customerId: ''
        },
        sysDate: ''
      };
    },
    methods: {
      handleSizeChange(val) {
        console.log(`每页 ${val} 条`);
      },
      handleCurrentChange(val) {
        this.getRecordByTimeAndStatus(val, 7);
      },
      getRecordByTimeAndStatus: function (pageNum, pageSize) {
        var _this = this;
        var value = null;
        if (_this.selectStatus == 1) {
          value = true;
        }
        if (_this.selectStatus == 2) {
          value = false;
        }
        axios.post('record/getRecordByTime',
            {
              "pageNum": pageNum, "pageSize": pageSize,
              "startTime": _this.selectDate[0], "endTime": _this.selectDate[1], "status": value
            }).then(function (response) {
          console.log("dataList",response.data.dataList);
          var list = response.data.dataList;
          for (var i = 0; i < list.length; i++) {
            list[i].status = list[i].status ? "已完成" : "未完成";
          }
          _this.tableData = list;
          _this.total = response.data.total;
          console.log(_this.total)
        }).catch(function (error) {
          console.log(error);
        });
      },
      removeCustomer(data) {
        var _this = this;
        this.$confirm('此操作将永久删除此订单信息, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          let param = new URLSearchParams();
          param.append("id", data.id)
          axios.post('record/removeRecordById', param).then(function (response) {
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

      },
      getOptions: function () {
        var _this = this;
        axios.post('record/getOptions').then(function (response) {
          _this.options = response.data.data;
          console.log(response.data.data)
        }).catch(function (error) {
          console.log(error);
        });
      },
      remoteMethod: function (keywords) {
        var _this = this;
        let param = new URLSearchParams();
        param.append("customerName", keywords);
        axios.post('record/getCustomerOptions', param).then(function (response) {
          _this.customerOptions = response.data.data;
        }).catch(function (error) {
          console.log(error);
        });
      },
      addRecord: function () {
        console.log(this.form)
        var _this = this;
        var carRecord = {
          customerId: _this.form.customerId,
          userId: sessionStorage.getItem("userId"),
          carId: _this.form.carData[1],
          timeLong: _this.form.timeLong + _this.form.unit,
          deposit: _this.form.deposit,
          status: 'false'
        }
        axios.post('record/addCarRecord', carRecord).then(function (response) {
          console.log(response.data.data);
          if (response.data.data) {
            _this.$message({
              message: '创建成功',
              type: 'success'
            });
            _this.getRecordByTimeAndStatus(1, 7);
            _this.dialogFormVisible = false;
          }
        }).catch(function (error) {
          console.log(error);
        });
      },
      carHandleChange: function (val) {
        var _this = this;
        let param = new URLSearchParams();
        param.append("id", val[1]);
        axios.post('record/getCurrentCarData', param).then(function (response) {
          _this.carData = response.data.data;
          _this.details = true;
        }).catch(function (error) {
          console.log(error);
        });
      },
      clearData: function () {
        this.carData = {
          seating: '',
          color: '',
          dayPrice: '',
          monthPrice: ''
        };
        this.details = false;
        this.form = {
          customerId: '',
          carData: [],
          timeLong: '',
          unit: '',
          deposit: ''
        };
      },
      getCurrentDate: function () {
        var _this = this;
        axios.post('record/getCurrentDate').then(function (response) {
          _this.sysDate = response.data.data;
        }).catch(function (error) {
          console.log(error);
        });
      },
      getDate: function (period) {
        var yearLevelValue = 365 * 24 * 60 * 60 * 1000;
        var monthLevelValue = 30 * 24 * 60 * 60 * 1000;
        var dayLevelValue = 24 * 60 * 60 * 1000;

        function getDifference(period) {
          /*******计算出时间差中的年、月、日、天、时、分、秒*******/
          var year = parseInt(getYear(period));
          var month = parseInt(getMonth(period - year * yearLevelValue));
          var day = parseInt(getDay(period - year * yearLevelValue - month * monthLevelValue));
          var result = "";
          if (year != 0) result = result + year + "年";
          if (month != 0) result = result + month + "月";
          if (day != 0) result = result + day + "天";

          function getYear(period) {
            return parseInt(period) / yearLevelValue;
          }

          function getMonth(period) {
            return parseInt(period) / monthLevelValue;
          }

          function getDay(period) {
            return parseInt(period) / dayLevelValue;
          }

          return result;
        }

        return getDifference(period);
      },
      //结算时长
      endRecord: function (data) {
        console.log(data);
        //创建时间
        var str = data.createTime;
        str = str.replace(/-/g, '/');
        console.log("createTime-str", str);
        var date = new Date(str);
        var str2 = date.getTime();
        console.log("createTime-str2毫秒", str2);
        //当下时间
        var sys = this.sysDate;
        console.log("sysTime-str", sys);
        var date2 = new Date(sys);
        var sys2 = date2.getTime();
        console.log("sysTime-str2毫秒", sys2);
        //当下时间-创建时间，进制转换
        var resultDay = (sys2 - str2) / (1000 * 3600 * 24)+1;
        //默认+1天
        this.balanceForm.currentTimeLong = resultDay;
        this.balanceForm.timeLong = data.timeLong;
        this.balanceForm.deposit = data.deposit;
        //结算金额
        var money = resultDay * data.carData.dayPrice;
        this.balanceForm.money = money;
        var aMoney = money - data.deposit;
        var rMoney = data.deposit - money;
        if (aMoney > 0) {
          this.balanceForm.addMoney = aMoney;
        }else {
          this.balanceForm.addMoney = 0;
        }
        if (rMoney > 0) {
          this.balanceForm.reduceMoney = rMoney;
        }else{
          this.balanceForm.reduceMoney = 0;
        }
        this.balanceForm.id = data.id;
        this.balanceForm.carId = data.carData.id;
        this.balanceForm.customerId = data.customer.id;
        this.dialogVisible = true;
      },
      settlement: function () {
        var _this = this;
        var recordRequest = {
          id: _this.balanceForm.id,
          carId: _this.balanceForm.carId,
          customerId: _this.balanceForm.customerId,
          income: _this.balanceForm.addMoney,
          expend: _this.balanceForm.reduceMoney
        }
        axios.post('record/settlement', recordRequest).then(function (response) {
          console.log("data",response.data.data);
          if (response.data.data) {
            _this.$message({
              message: '结算成功',
              type: 'success'
            });
          }
        }).catch(function (error) {
          console.log(error);
        });
      }
    },
    mounted: function () {
      this.getRecordByTimeAndStatus(1, 7);
      this.getOptions();
      this.getCurrentDate();
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

.block {
  margin-left: 20%;
}

.demo-table-expand {
  font-size: 0;
}

.demo-table-expand label {
  width: 90px;
  color: #99a9bf;
}

.demo-table-expand .el-form-item {
  margin-right: 0;
  margin-bottom: 0;
  width: 50%;
}

.input-with-select .el-select .el-input {
  width: 130px;
}

.input-with-select .el-input-group__prepend {
  background-color: #fff;
}

.text {
  font-size: 14px;
}

.item {
  padding: 2px 0;
}

.box-card {
  width: 480px;
}
</style>