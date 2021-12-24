<template>
    <el-main>
        <h1 style="color: #909399">车辆信息</h1>
        <div class="split-sys"></div>
        <div class="div-main">
            <div class="el-row">
                <el-col :span="16">
                    <div style="width: 200px">
                        <el-input placeholder="brand" v-model="keywords">
                            <el-button slot="append" icon="el-icon-search" @click="search(1,7)"></el-button>
                        </el-input>
                    </div>
                </el-col>
                <el-col :span="8">
                    <div>
                        <el-button type="info" plain @click="showAddDialog">添加车辆</el-button>
                    </div>
                </el-col>
            </div>
            <el-table
                    :data="tableData"
                    stripe
                    style="width: 92%">
                <el-table-column
                        fixed
                        prop="id"
                        label="ID"
                        width="120">
                </el-table-column>
                <el-table-column
                        fixed
                        prop="brand"
                        label="品牌"
                        width="120">
                </el-table-column>
                <el-table-column
                        fixed
                        prop="color"
                        label="颜色"
                        width="120">
                </el-table-column>
                <el-table-column
                        prop="seating"
                        label="座数"
                        width="120">
                </el-table-column>
                <el-table-column
                        prop="dayPrice"
                        label="日租价格"
                        width="120">
                </el-table-column>
                <el-table-column
                        prop="monthPrice"
                        label="月租价格"
                        width="120">
                </el-table-column>
                <el-table-column
                        prop="runKm"
                        label="已跑里程"
                        width="120">
                </el-table-column>
                <el-table-column
                        prop="buyDate"
                        label="购入日期"
                        width="120">
                </el-table-column>
                <el-table-column
                        prop="status"
                        label="状态"
                        width="120">
                </el-table-column>
                <el-table-column
                        prop="statusDescribe"
                        label="状态描述"
                        width="120">
                </el-table-column>
                <el-table-column
                        prop="className"
                        label="类别"
                        width="120">
                </el-table-column>
                <el-table-column
                        prop="userName"
                        label="管理人员"
                        width="120">
                </el-table-column>
                <el-table-column
                        label="操作"
                        width="200">
                    <template slot-scope="scope">
                        <el-button type="text" size="small" @click="carStart(scope.row)">启用</el-button>
                        <el-button type="text" size="small" @click="carStop(scope.row)">禁用</el-button>
                        <el-button type="text" size="small" @click="carRemove(scope.row)">删除</el-button>
                        <el-button type="text" size="small" @click="carEdit(scope.row)">编辑</el-button>
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
            <el-dialog title="禁用编辑" :visible.sync="dialogFormVisible">
                <el-form>
                    <el-form-item label="禁用原因" :label-width="120">
                        <el-input v-model="statusDescribe" auto-complete="off"></el-input>
                    </el-form-item>
                </el-form>
                <div slot="footer" class="dialog-footer">
                    <el-button @click="dialogFormVisible = false">取 消</el-button>
                    <el-button type="primary" @click="carStopSubmit">确 定</el-button>
                </div>
            </el-dialog>

            <el-dialog title="编辑" :visible.sync="editVisible">
                <el-form :model="editForm" label-width="120px">
                    <el-form-item label="品牌">
                        <el-input v-model="editForm.brand" auto-complete="off"></el-input>
                    </el-form-item>
                    <el-form-item label="车牌号">
                        <el-input v-model="editForm.number" auto-complete="off"></el-input>
                    </el-form-item>
                    <el-form-item label="颜色">
                        <el-input v-model="editForm.color" auto-complete="off"></el-input>
                    </el-form-item>
                    <el-form-item label="座数">
                        <el-input v-model="editForm.seating" auto-complete="off"></el-input>
                    </el-form-item>
                    <el-form-item label="日租价格">
                        <el-input v-model="editForm.dayPrice" auto-complete="off"></el-input>
                    </el-form-item>
                    <el-form-item label="月租价格">
                        <el-input v-model="editForm.monthPrice" auto-complete="off"></el-input>
                    </el-form-item>
                    <el-form-item label="已跑里程">
                        <el-input v-model="editForm.runKm" auto-complete="off"></el-input>
                    </el-form-item>
                    <el-form-item label="类别">
                        <el-select v-model="editForm.classId" placeholder="请选择">
                            <el-option
                                    v-for="item in classOptions"
                                    :key="item.value"
                                    :label="item.label"
                                    :value="item.value">
                            </el-option>
                        </el-select>
                    </el-form-item>
                    <el-form-item label="管理员">
                        <el-select v-model="editForm.userId" placeholder="请选择">
                        <el-option
                                v-for="item in userOptions"
                                :key="item.value"
                                :label="item.label"
                                :value="item.value">
                        </el-option>
                    </el-select>
                    </el-form-item>
                </el-form>
                <div slot="footer" class="dialog-footer">
                    <el-button @click="editVisible = false">取 消</el-button>
                    <el-button type="primary" @click="editSubmit">确 定</el-button>
                </div>
            </el-dialog>

            <el-dialog title="新增" :visible.sync="addVisible">
                <el-form :model="addForm" label-width="120px">
                    <el-form-item label="品牌">
                        <el-input v-model="addForm.brand" auto-complete="off"></el-input>
                    </el-form-item>
                    <el-form-item label="车牌号">
                        <el-input v-model="addForm.number" auto-complete="off"></el-input>
                    </el-form-item>
                    <el-form-item label="颜色">
                        <el-input v-model="addForm.color" auto-complete="off"></el-input>
                    </el-form-item>
                    <el-form-item label="座数">
                        <el-input v-model="addForm.seating" auto-complete="off"></el-input>
                    </el-form-item>
                    <el-form-item label="日租价格">
                        <el-input v-model="addForm.dayPrice" auto-complete="off"></el-input>
                    </el-form-item>
                    <el-form-item label="月租价格">
                        <el-input v-model="addForm.monthPrice" auto-complete="off"></el-input>
                    </el-form-item>
                    <el-form-item label="已跑里程">
                        <el-input v-model="addForm.runKm" auto-complete="off"></el-input>
                    </el-form-item>
                    <el-form-item label="状态">
                        <el-select v-model="addForm.status" placeholder="是否启用">
                            <el-option label="启用" value="true"></el-option>
                            <el-option label="禁用" value="false"></el-option>
                        </el-select>
                    </el-form-item>
                    <el-form-item label="状态描述">
                        <el-input v-model="addForm.statusDescribe" auto-complete="off"></el-input>
                    </el-form-item>
                    <el-form-item label="购入日期">
                        <el-date-picker type="date" placeholder="选择日期" v-model="addForm.buyDate" style="width: 100%;"></el-date-picker>
                    </el-form-item>
                    <el-form-item label="类别">
                        <el-select v-model="addForm.classId" placeholder="请选择">
                            <el-option
                                    v-for="item in classOptions"
                                    :key="item.value"
                                    :label="item.label"
                                    :value="item.value">
                            </el-option>
                        </el-select>
                    </el-form-item>
                    <el-form-item label="管理员">
                        <el-select v-model="addForm.userId" placeholder="请选择">
                            <el-option
                                    v-for="item in userOptions"
                                    :key="item.value"
                                    :label="item.label"
                                    :value="item.value">
                            </el-option>
                        </el-select>
                    </el-form-item>
                </el-form>
                <div slot="footer" class="dialog-footer">
                    <el-button @click="addVisible = false">取 消</el-button>
                    <el-button type="primary" @click="addSubmit">确 定</el-button>
                </div>
            </el-dialog>

        </div>
    </el-main>
</template>

<script>
    define(["vue", "axios"], function(Vue, axios) {
        return Vue.component("service", {
            template: template,
            data: function() {
                return {
                    tableData:[],
                    currentPage:'1',
                    total:'',
                    keywords:'',
                    searchStatus:false,
                    statusDescribe:'',
                    stopId:'',
                    dialogFormVisible:false,
                    editForm:{
                        brand:'',
                        number:'',
                        color:'',
                        seating:'',
                        dayPrice:'',
                        monthPrice:'',
                        runKm:'',
                        classId:'',
                        userId:'',
                    },
                    addForm:{
                        brand:'',
                        number:'',
                        color:'',
                        seating:'',
                        dayPrice:'',
                        monthPrice:'',
                        runKm:'',
                        classId:'',
                        userId:'',
                        status:'',
                        statusDescribe:'',
                        buyDate:''
                    },
                    classOptions:[],
                    userOptions:[],
                    editVisible:false,
                    addVisible:false,
                };
            },
            methods:{
                getAllCarData:function (pageNum,pageSize) {
                    var _this = this;
                    axios.post('data/getAllCarData',{"pageNum":pageNum,"pageSize":pageSize}).then(function (response) {
                        var list = response.data.dataList;
                        for(var i=0;i<list.length;i++){
                            list[i].status = list[i].status?"启用":"禁用";
                        }
                        _this.tableData = list;
                        _this.total = response.data.total;
                    }).catch(function (error) {
                        console.log(error);
                    });
                },
                handleSizeChange(val) {
                    console.log(`每页 ${val} 条`);
                },
                handleCurrentChange(val) {
                    var _this = this;
                    if (_this.searchStatus){
                        _this.search(val,7)
                    }else{
                        this.getAllCarData(val,7)
                    }
                },
                search:function (pageNum,pageSize) {
                    var _this = this;
                    axios.post('data/searchCarData',{"pageNum":pageNum,"pageSize":pageSize,"brand":_this.keywords}).then(function (response) {
                        var list = response.data.dataList;
                        for(var i=0;i<list.length;i++){
                            list[i].status = list[i].status?"启用":"禁用";
                        }
                        _this.tableData = list;
                        _this.total = response.data.total;
                        _this.searchStatus = true;
                    }).catch(function (error) {
                        console.log(error);
                    });
                },
                carStart:function (row) {
                    var _this = this;
                    axios.post('data/modifyStatus',{"id":row.id,"status":"true","statusDescribe":"已启用"})
                        .then(function (response) {
                            if(response.data){
                                _this.$message({
                                    message: '启用成功',
                                    type: 'success'
                                });
                                _this.getAllCarData(1,7);
                            }else{
                                _this.$message.error('启用失败');
                            }
                    }).catch(function (error) {
                        console.log(error);
                    });
                },
                carStop:function (row) {
                    this.dialogFormVisible = true;
                    this.statusDescribe = "";
                    this.stopId = row.id;
                },
                carStopSubmit:function () {
                    var _this = this;
                    axios.post('data/modifyStatus',{"id":_this.stopId,"status":"false","statusDescribe":_this.statusDescribe})
                        .then(function (response) {
                            if(response.data){
                                _this.$message({
                                    message: '禁用成功',
                                    type: 'success'
                                });
                                _this.getAllCarData(1,7);
                                _this.dialogFormVisible = false;
                            }else{
                                _this.$message.error('禁用失败');
                                _this.dialogFormVisible = false;
                            }
                        }).catch(function (error) {
                        console.log(error);
                    });
                },
                carRemove:function (row) {
                    this.$confirm('此操作将永久删除记录, 是否继续?', '提示', {
                        confirmButtonText: '确定',
                        cancelButtonText: '取消',
                        type: 'warning'
                    }).then(() => {
                        var _this = this;
                        let param = new URLSearchParams();
                        param.append("id",row.id);
                        axios.post('data/removeCarData',param)
                            .then(function (response) {
                                if(response.data){
                                    _this.$message({
                                        type: 'success',
                                        message: '删除成功!'
                                    });
                                    _this.getAllCarData(1,7);
                                }else{
                                    _this.$message.error('删除失败');
                                }
                            }).catch(function (error) {
                            console.log(error);
                        });
                    }).catch(() => {
                        this.$message({
                            type: 'info',
                            message: '已取消删除'
                        });
                    });
                },
                carEdit:function (row) {
                    this.editVisible = true;
                    this.editForm = row;
                },
                editSubmit:function () {
                    var _this = this;
                    _this.editForm.status = _this.editForm.status === "启用"?true:false;
                    axios.post('data/modifyCarData',_this.editForm)
                        .then(function (response) {
                            if (response.data){
                                _this.$message({
                                    type: 'success',
                                    message: '编辑成功!'
                                });
                                _this.getAllCarData(1,7);
                                _this.editVisible = false;
                            }else{
                                _this.$message.error('编辑失败');
                                _this.editVisible = false;
                            }
                        }).catch(function (error) {
                        console.log(error);
                    });
                },
                getUserOptions:function () {
                    var _this = this;
                    axios.post('data/getUserOptions')
                        .then(function (response) {
                            _this.userOptions = response.data.data;
                        }).catch(function (error) {
                        console.log(error);
                    });
                },
                getClassOptions:function () {
                    var _this = this;
                    axios.post('data/getClassOptions')
                        .then(function (response) {
                            _this.classOptions = response.data.data;
                        }).catch(function (error) {
                        console.log(error);
                    });
                },
                addSubmit:function () {
                    var _this = this;
                    axios.post('data/addCarData',_this.addForm)
                        .then(function (response) {
                            if(response.data){
                                _this.$message({
                                    type: 'success',
                                    message: '添加成功!'
                                });
                                _this.getAllCarData(1,7);
                                _this.addVisible = false;
                            }else{
                                _this.$message.error('添加失败');
                                _this.addVisible = false;
                            }
                        }).catch(function (error) {
                        console.log(error);
                    });
                },
                showAddDialog:function () {
                    this.addVisible = true;
                    addForm = {
                        brand:'',
                        number:'',
                        color:'',
                        seating:'',
                        dayPrice:'',
                        monthPrice:'',
                        runKm:'',
                        classId:'',
                        userId:'',
                        status:'',
                        statusDescribe:'',
                        buyDate:''
                    }
                }
            },
            mounted:function () {
                this.getAllCarData(1,7);
                this.getUserOptions();
                this.getClassOptions();
            }
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
        width: 25%;
        text-align: center;

    }
    .car-font {
        color: #303133;
    }
    .block{
        margin-left: 20%;
    }
    .div-main{
        margin-top: 80px;
    }
</style>