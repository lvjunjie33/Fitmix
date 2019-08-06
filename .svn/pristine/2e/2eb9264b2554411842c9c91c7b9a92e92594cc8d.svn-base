<%--
  Created by IntelliJ IDEA.
  User: sin
  Date: 2015/6/9 0009
  Time: 11:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/pages/common/nav.jsp" %>
<!-- ECharts单文件引入 -->
<script src="http://echarts.baidu.com/build/dist/echarts.js"></script>
<style>
    .tm-article-subtitle{
        padding-left: 6px;
        border-left: 3px solid #1FA2D6;
        line-height: 16px;
        font-family: "Microsoft Yahei","微软雅黑","Proxima Nova", "Helvetica Neue", Helvetica, Arial, sans-serif;
        margin-top: 25px;
        margin: 0 0 15px;
        font-weight: 300;
        color: #222;
        text-transform: none;
        display: block;
        font-size: 1em;
        -webkit-margin-before: 1em;
        -webkit-margin-after: 1em;
        -webkit-margin-start: 0px;
        -webkit-margin-end: 0px;
    }
</style>

<div class="uk-container-center uk-body">

    <div class="uk-panel">
        <h3 class="tm-article-subtitle">近日概况 - 用户活跃/用户留存</h3>
        <table class="uk-table uk-table-hover">
            <thead>
            <tr>
                <th>时间</th>
                <th>一次性用户</th>
                <th>日活跃用户</th>
                <th>周活跃用户</th>
                <th>月活跃用户</th>
                <th>次日留存率</th>
                <th>周留存率</th>
                <th>月留存率</th>
            </tr>
            </thead>
            <tbody>
                <c:forEach var="userActiveRetained" items="${userActiveRetainedList}">
                    <tr>
                        <td>${userActiveRetained.toDay}</td>
                        <td>${userActiveRetained.oneTimeUser}</td>
                        <td>${userActiveRetained.dayActiveUser}</td>
                        <td>${userActiveRetained.weekActiveUser}</td>
                        <td>${userActiveRetained.monthActiveUser}</td>
                        <td>${userActiveRetained.dayRetained}</td>
                        <td>${userActiveRetained.weekRetained}</td>
                        <td>${userActiveRetained.monthRetained}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
    <br/>

    <div class="uk-panel">
        <h3 class="tm-article-subtitle">近日概况 - 新用户/流失用户</h3>
        <table class="uk-table uk-table-hover">
            <thead>
            <tr>
                <th>时间</th>
                <th>用户数量</th>
                <th>新曾用户</th>
                <th>活跃用户</th>
                <th>新用户(%)</th>
                <th>启动次数</th>
                <th>人均启动</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="userGrowthLoss" items="${userGrowthLossList}">
                <tr>
                    <td>${userGrowthLoss.toDay}</td>
                    <td>${userGrowthLoss.userCount}</td>
                    <td>${userGrowthLoss.newAddUserCount}</td>
                    <td>${userGrowthLoss.userActiveCount}</td>
                    <td>${userGrowthLoss.newUserScale}</td>
                    <td>${userGrowthLoss.loginTimes}</td>
                    <td><fmt:formatNumber value="${userGrowthLoss.eachUserLoginTimes}" pattern="##.##"/></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
    <br/>



    <div class="uk-panel retained-panel">
        <h3 class="tm-article-subtitle">用户留存</h3>

        <%--<div class="uk-button-group " data-uk-button-radio>--%>
        <%--<button class="uk-button uk-active" data="1">近三天</button>--%>
        <%--<button class="uk-button" data="2">近三周</button>--%>
        <%--<button class="uk-button" data="3">近三月</button>--%>
        <%--</div>--%>

        <!-- 为ECharts准备一个具备大小（宽高）的Dom -->
        <div id="UserActiveRetainedCharts" style="height:400px;"></div>
    </div>
    <br/>



    <div class="uk-panel loss-panel">
        <h3 class="tm-article-subtitle">时段分析</h3>

        <div class="uk-button-group button-todata" data-uk-button-radio>
            <button class="uk-button uk-active" data="2">活跃用户</button>
            <button class="uk-button" data="1">新增用户</button>
            <button class="uk-button" data="3">启动次数</button>
        </div>

        <div class="uk-button-group button-today" data-uk-button-radio>
            <button class="uk-button uk-active" data="1">今天</button>
            <button class="uk-button" data="2">一周</button>
            <button class="uk-button" data="3">一月</button>
        </div>

        <!-- 为ECharts准备一个具备大小（宽高）的Dom -->
        <div id="UserGrowthLossStat" style="height:400px;"></div>
    </div>
    <br/>


</div>

<script type="text/javascript">

    (function(){
        // 路径配置
        require.config({
            paths: {
            echarts: 'http://echarts.baidu.com/build/dist'
            }
        });

//        var retainedBool = 0;
//        $(".retained-panel button").click(function(){
//            if (!retainedBool) {
//                retainedBool++;
//                return;
//            }
//            retainedEcartInit();
//        });



        function retainedEcartInit () {
            $.post("/stat/user-active-retained.json", {"today" : /*$(".retained-panel .uk-active").attr("data")*/1}, function(result){
                console.info(result);
                option.legend.data = result.echarts.legendData;
                $.each(result.echarts.seriesData, function(i, data){
                    console.info(data);
                    option.series.push({
                        name: result.echarts.legendData[i],                            // 系列名称
                        type: 'line',                           // 图表类型，折线图line、散点图scatter、柱状图bar、饼图pie、雷达图radar
                        data: data,
                        markPoint : {
                            data : [
                                {type : 'max', name: '最大值'},
                                {type : 'min', name: '最小值'}
                            ]
                        },
                        markLine : {
                            data : [
                                {type : 'average', name: '平均值'}
                            ]
                        }
                    });
                });
                echart.setOption(option);
            });
        }

        var option;
        function retainedOption () {
            var xAxisData = ["一次性用户", "日活跃用户", "周活跃用户", "月活跃用户", "次日留存率", "周留存率", "月留存率"];
             option = {
                legend: {
                    data:[]
                },
                tooltip : {
                    trigger: 'axis'
                },
                toolbox: {
                    show : true,
                    feature : {
                        mark : {show: true},
                        dataView : {show: true, readOnly: false},
                        magicType : {show: true, type: ['line', 'bar']},
                        restore : {show: true},
                        saveAsImage : {show: true}
                    }
                },
                calculable : true,
                xAxis : [
                    {
                        type : 'category',
                        boundaryGap : false,
                        data : xAxisData
                    }
                ],
                yAxis : [
                    {
                        type : 'value',
                        axisLabel : {
                            formatter: '{value}'
                        }
                    }
                ],
                series : []
            };
        }

        var echart;
        function retainedChart () {
            require(
                [
                    'echarts',
                    'echarts/chart/line',   // 按需加载所需图表，如需动态类型切换功能，别忘了同时加载相应图表
                    'echarts/chart/bar'
                ],
                function (ec) {
                    echart = ec.init(document.getElementById('UserActiveRetainedCharts'));
                }
            );
        }

        retainedOption();
        retainedChart();

        window.setTimeout(function(){
            retainedEcartInit();
        }, 200)
    })()
</script>


<script type="text/javascript">

    (function(){
        // 路径配置
        require.config({
            paths: {
                echarts: 'http://echarts.baidu.com/build/dist'
            }
        });

        UserGrowthLossStatInit();
        function  UserGrowthLossStatInit() {
            var xAxisData = [0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14 ,15 ,16 ,17, 18, 19, 20, 21, 22, 23];
            var userGrowthLossStatOption = {
                legend: {
                    data:[]
                },
                tooltip : {
                    trigger: 'axis'
                },
                toolbox: {
                    show : true,
                    feature : {
                        mark : {show: true},
                        dataView : {show: true, readOnly: false},
                        magicType : {show: true, type: ['line', 'bar']},
                        restore : {show: true},
                        saveAsImage : {show: true}
                    }
                },
                calculable : true,
                xAxis : [
                    {
                        type : 'category',
                        boundaryGap : false,
                        data : xAxisData
                    }
                ],
                yAxis : [
                    {
                        type : 'value',
                        axisLabel : {
                            formatter: '{value}'
                        }
                    }
                ],
                series : []
            };

            var userGrowthLossStatChart;
            if (userGrowthLossStatChart) {
                // 图表清空-------------------
                userGrowthLossStatChart.clear();
                // 图表释放-------------------
                userGrowthLossStatChart.dispose();
            }

            require(
                    [
                        'echarts',
                        'echarts/chart/line',   // 按需加载所需图表，如需动态类型切换功能，别忘了同时加载相应图表
                        'echarts/chart/bar'
                    ],
                    function (ec) {
                        userGrowthLossStatChart = ec.init(document.getElementById('UserGrowthLossStat'));
                    }
            );


            window.setTimeout(function(){
                $.post("/stat/user-growth-loss-stat.json", getActiveData(), function(result){
                    userGrowthLossStatOption.legend.data = result.echarts.legendData;
                    userGrowthLossStatOption.series = [];
                    userGrowthLossStatOption.series.push({
                        name: result.echarts.legendData[0],
                        type: 'line',
                        data :result.echarts.seriesData[0],
                        markPoint : {
                            data : [
                                {type : 'max', name: '最大值'},
                                {type : 'min', name: '最小值'}
                            ]
                        },
                        markLine : {
                            data : [
                                {type : 'average', name: '平均值'}
                            ]
                        }
                    });
                    userGrowthLossStatOption.series.push({
                        name: result.echarts.legendData[1],
                        type: 'line',
                        data :result.echarts.seriesData[1],
                        markPoint : {
                            data : [
                                {type : 'max', name: '最大值'},
                                {type : 'min', name: '最小值'}
                            ]
                        },
                        markLine : {
                            data : [
                                {type : 'average', name: '平均值'}
                            ]
                        }
                    });
                    // ajax callback
//                userGrowthLossStatChart.hideLoading();
                    console.info(userGrowthLossStatOption);
                    userGrowthLossStatChart.setOption(userGrowthLossStatOption);
                });
            }, 200);
        }



        var todata = 0;
        var today = 0;
        $(".loss-panel .button-todata button, .button-today .uk-button").click(function(){
            if (!todata) {
                todata++;
                return;
            }
            UserGrowthLossStatInit();
        });

        $(".loss-panel.button-today .uk-button").click(function(){
            if (!today) {
                today++;
                return;
            }
            UserGrowthLossStatInit();
        });



        function getActiveData () {
            var json = {};
            json["today"] = $(".button-today .uk-active").attr("data");
            json["data"] = $(".button-todata .uk-active").attr("data");
            return json;
        }
    })()

</script>

<%@ include file="/WEB-INF/pages/common/bottom.jsp" %>