<template>
    <el-main>
        <h1 style="color: #909399">车辆类别</h1>
        <div class="split-sys"></div>
        <div class="body">
            <el-row>
                <el-col :span="10">
                    <el-tooltip effect="dark" content="点击进行修改" placement="top-start" >
                    <el-card class="box-card">
                        <div slot="header" class="clearfix">
                            <span>当前类别 </span>
                        </div>
                            <el-tag type="warning" class="tag" @close="handleClose(item)"
                                    @click.native ="modifyTag(item)" closable  v-for="item in items">
                                {{item.className}}
                            </el-tag>
                    </el-card>
                    </el-tooltip>
                </el-col>
                <el-col :span="14">
                    <el-card class="box-card">
                        <div slot="header" class="clearfix">
                            <span>添加类别 </span>
                        </div>
                        <el-form :model="classForm" status-icon label-width="100px" class="demo-ruleForm">
                            <el-form-item label="类别名称" prop="className">
                                <el-input v-model="classForm.className" auto-complete="off"></el-input>
                            </el-form-item>
                            <el-form-item>
                                <el-button type="primary" @click="addClass">添加</el-button>
                            </el-form-item>
                        </el-form>
                    </el-card>
                </el-col>
            </el-row>
        </div>
        <el-dialog title="修改类别" :visible.sync="dialogFormVisible">
            <el-form :model="modifyForm">
                <el-form-item label="类别名称" :label-width="200">
                    <el-input v-model="modifyForm.className" auto-complete="off"></el-input>
                </el-form-item>
            </el-form>
            <div slot="footer" class="dialog-footer">
                <el-button @click="dialogFormVisible = false">取 消</el-button>
                <el-button type="primary" @click="submitModifyForm">确 定</el-button>
            </div>
        </el-dialog>
    </el-main>
</template>

<script>
    define(["vue", "axios"], function(Vue, axios) {
        return Vue.component("class", {
            template: template,
            data: function() {
                return {
                    items:[],
                    classForm:{
                        className:''
                    },
                    modifyForm:{
                        id:'',
                        className:''
                    },
                    dialogFormVisible:false,
                    dialogVisible:false,
                    classId:''
                };
            },
            methods:{
                addClass:function () {
                    var _this = this;
                    let param = new URLSearchParams();
                    param.append("className",_this.classForm.className)
                    axios.post('class/addClass',param).then(function (response) {
                        console.log(response.data);
                        if(response.data){
                            _this.getClass();
                            _this.classForm.className = "";
                        }else{
                            _this.$message.error('添加失败');
                        }
                    }).catch(function (error) {
                        console.log(error);
                    });
                },
                getClass:function () {
                    var _this = this;
                    axios.post('class/getAllClass').then(function (response) {
                        console.log(response.data);
                        _this.items = response.data.data;
                    }).catch(function (error) {
                        console.log(error);
                    });
                },
                modifyTag:function (tag) {
                    console.log(tag)
                    $(this.modifyForm).attr("className",tag.className);
                    $(this.modifyForm).attr("id",tag.id);
                    console.log(this.modifyForm)
                    this.dialogFormVisible = true;
                },
                submitModifyForm:function () {
                    var _this = this;
                    axios.post('class/modifyClass',_this.modifyForm).then(function (response) {
                        if (response.data){
                            _this.getClass();
                            _this.dialogFormVisible = false;
                        }else{
                            _this.$message.error('修改失败');
                        }
                    }).catch(function (error) {
                        console.log(error);
                    });
                },
                handleClose:function (item) {
                    this.$confirm('此操作将删除该类别以及该类别下的所有车辆信息, 是否继续?', '提示', {
                        confirmButtonText: '确定',
                        cancelButtonText: '取消',
                        type: 'warning'
                    }).then(() => {
                        this.$message({
                            type: 'success',
                            message: '删除成功!'
                        });
                        var _this = this;
                        let param = new URLSearchParams();
                        param.append("classId",item.id);
                        axios.post('class/removeClass',param).then(function (response) {
                            console.log(response.data);
                            if(response.data){
                                _this.getClass();
                                _this.$message({
                                    message: '删除成功',
                                    type: 'success'
                                });
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
            },
            mounted:function () {
                this.getClass();
            },
            watch:{
                dialogFormVisible:function () {
                    if (!this.dialogFormVisible){
                        this.modifyForm = {
                            id:'',
                            className:''
                        };
                    }
                }
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
    .text {
        font-size: 14px;
    }

    .item {
        margin-bottom: 18px;
    }

    .clearfix:before,
    .clearfix:after {
        display: table;
        content: "";
    }
    .clearfix:after {
        clear: both
    }

    .box-card {
        width: 480px;
    }
    .tag{
        margin-top: 10px;
        margin-left: 10px;
    }
    .body{
        margin-top: 50px;
    }
</style>