<template>
    <el-main>
        <h1 style="color: #909399">车辆库存</h1>
        <div class="split-sys"></div>
        <el-row>
            <el-col :span="10">
                <el-table
                        :data="tableData"
                        style="width: 100%;margin-top: 50px">
                    <el-table-column
                            prop="className"
                            label="分类"
                    >
                    </el-table-column>
                    <el-table-column
                            prop="stock"
                            label="库存"
                    >
                    </el-table-column>
                    <el-table-column
                            label="操作">
                        <template slot-scope="scope">
                            <el-button
                                    size="mini"
                                    @click="details(scope.row)">详情</el-button>
                        </template>
                    </el-table-column>
                </el-table>
                <div class="block">
                    <el-pagination
                            @size-change="handleSizeChange"
                            @current-change="stockCurrentPageHandel"
                            :current-page.sync="stockCurrentPage"
                            :page-size="7"
                            layout="prev, pager, next, jumper"
                            :total="stockTotal">
                    </el-pagination>
                </div>
            </el-col>
            <el-col :span="14">
                <div id="main" style="width: 600px;height:400px;"></div>
            </el-col>
        </el-row>
        <el-dialog title="库存详情" :visible.sync="dialogTableVisible">
            <el-table :data="detailsData">
                <el-table-column property="brand" label="品牌"></el-table-column>
                <el-table-column property="number" label="车牌"></el-table-column>
                <el-table-column property="color" label="颜色"></el-table-column>
                <el-table-column property="seating" label="座数"></el-table-column>
            </el-table>
            <div class="block">
                <el-pagination
                        @current-change="detailsCurrentPageHandel"
                        :current-page.sync="detailsCurrentPage"
                        :page-size="7"
                        layout="prev, pager, next, jumper"
                        :total="detailsTotal">
                </el-pagination>
            </div>
        </el-dialog>
    </el-main>
</template>

<script>
    define(["vue", "axios", "echarts"], function(Vue, axios, echarts) {
        return Vue.component("stock", {
            template: template,
            data: function() {
                return {
                    tableData:[],
                    detailsData:[],
                    dialogTableVisible:false,
                    stockCurrentPage:'1',
                    stockTotal:'',
                    detailsCurrentPage:'1',
                    detailsTotal:'',
                    classId:'',
                };
            },
            methods:{
                getStock:function (pageNum,pageSize) {
                    var _this = this;
                    axios.post('data/getAllStock',{"pageNum":pageNum,"pageSize":pageSize}).then(function (response) {
                        _this.tableData = response.data.dataList;
                        _this.stockTotal = response.data.total;
                        var className = [];
                        var stock = [];
                        for (var i=0;i<_this.tableData.length;i++){
                            className[i] = _this.tableData[i].className;
                            stock[i] = _this.tableData[i].stock;
                        }
                        var myChart = echarts.init(document.getElementById('main'));
                        option = {
                            color: ['#3398DB'],
                            tooltip : {
                                trigger: 'axis',
                                axisPointer : {            // 坐标轴指示器，坐标轴触发有效
                                    type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
                                }
                            },
                            grid: {
                                left: '3%',
                                right: '4%',
                                bottom: '3%',
                                containLabel: true
                            },
                            xAxis : [
                                {
                                    type : 'category',
                                    data : className,
                                    axisTick: {
                                        alignWithLabel: true
                                    }
                                }
                            ],
                            yAxis : [
                                {
                                    type : 'value'
                                }
                            ],
                            series : [
                                {
                                    name:'直接访问',
                                    type:'bar',
                                    barWidth: '60%',
                                    data:stock,
                                }
                            ]
                        };
                        myChart.setOption(option);
                    }).catch(function (error) {
                        console.log(error);
                    });
                },
                details:function (data) {
                    this.dialogTableVisible = true;
                    this.classId = data.classId;
                    this.getStockDetails(this.classId,1,7)
                },
                getStockDetails:function (classId,pageNum,pageSize) {
                    var _this = this;
                    axios.post('data/getStockDetails',{"pageNum":pageNum,"pageSize":pageSize,"classId":classId}).then(function (response) {
                        _this.detailsData = response.data.dataList;
                        _this.detailsTotal = response.data.total;
                    }).catch(function (error) {
                        console.log(error);
                    });
                },
                handleSizeChange(val) {
                    console.log(`每页 ${val} 条`);
                },
                stockCurrentPageHandel(val) {
                    this.getStock(val,7);
                    this.stockCurrentPage = val;
                },
                detailsCurrentPageHandel(val) {
                    this.getStockDetails(this.classId,val,7);
                    this.detailsCurrentPage = val;
                },
            },
            mounted:function () {
                this.getStock(1,7);
            },
            watch:{
                dialogTableVisible:function () {
                    this.detailsData = [];
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
</style>