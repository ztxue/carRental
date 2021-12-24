<template>
    <el-main>
        <h1 style="color: #909399">welcome</h1>
        <div class="split-sys"></div>
        <el-row>
            <el-col :span="12"><div id="bar" style="width: 600px;height:400px;"></div><p style="width: 600px;text-align: center;">客户个年龄阶段统计</p></el-col>
            <el-col :span="12"><div id="pie" style="width: 600px;height:400px;"></div><p style="width: 600px;text-align: center;">客户性别统计及比例</p></el-col>
        </el-row>
    </el-main>
</template>

<script>
    define(["vue", "axios", "echarts"], function (Vue, axios,echarts) {
        return Vue.component("home", {
            template: template,
            data: function data() {
            },
            methods:{
              initBarData:function () {
                  var _this = this;
                  axios.post('customer/getAgeCount').then(function (response) {
                      console.log(response.data);
                      var myChart = echarts.init(document.getElementById('bar'));

                      // 指定图表的配置项和数据
                      var option = {
                          xAxis: {
                              type: 'category',
                              data: ['20及以下', '21-25', '26-30', '31-35', '36-40', '41-45', '45-50', '51及以上']
                          },
                          yAxis: {
                              type: 'value'
                          },
                          series: [{
                              data: response.data.data,
                              type: 'bar'
                          }]
                      };

                      // 使用刚指定的配置项和数据显示图表。
                      myChart.setOption(option);
                  }).catch(function (error) {
                      console.log(error);
                  });
              },
                initPieData:function () {
                    var _this = this;
                    axios.post('customer/getSexCount').then(function (response) {
                        console.log(response.data);
                        var myChart = echarts.init(document.getElementById('pie'));

                        // 指定图表的配置项和数据
                        option = {
                            tooltip: {
                                trigger: 'item',
                                formatter: "{a} <br/>{b}: {c} ({d}%)"
                            },
                            legend: {
                                orient: 'vertical',
                                x: 'left',
                                data:['男','女']
                            },
                            series: [
                                {
                                    name:'男女比例',
                                    type:'pie',
                                    radius: ['50%', '70%'],
                                    avoidLabelOverlap: false,
                                    label: {
                                        normal: {
                                            show: false,
                                            position: 'center'
                                        },
                                        emphasis: {
                                            show: true,
                                            textStyle: {
                                                fontSize: '30',
                                                fontWeight: 'bold'
                                            }
                                        }
                                    },
                                    labelLine: {
                                        normal: {
                                            show: false
                                        }
                                    },
                                    data:[
                                        {value:response.data.data[0].count, name:'男'},
                                        {value:response.data.data[1].count, name:'女'}
                                    ]
                                }
                            ]
                        };
                        myChart.setOption(option);
                    }).catch(function (error) {
                        console.log(error);
                    });
                }
            },
            mounted:function () {
                this.initBarData();
                this.initPieData();
            }
        });
    });
</script>

<style>
    .split-sys{
        height: 3px;
        width: 100%;
        background-color: #909399;
    }
</style>