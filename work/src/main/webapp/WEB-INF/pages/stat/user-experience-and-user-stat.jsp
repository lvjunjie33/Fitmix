<%--
  Created by IntelliJ IDEA.
  User: sin
  Date: 2015/9/10
  Time: 0:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/pages/common/nav.jsp" %>
<!-- ECharts单文件引入 -->
<script src="http://echarts.baidu.com/build/dist/echarts.js"></script>

<div class="uk-button-group today-type" data-uk-button-radio>
  <button class="uk-button uk-active" data="1">日</button>
  <button class="uk-button" data="2">周</button>
  <button class="uk-button" data="3">月</button>
</div>

<div style="position: relative;">
  <div id="register-main" style="height: 500px;"></div>
  <div id="register-tip" style="text-align: center;display: none;position: absolute;z-index: 11;top:0px;left:49%;">无数据</div>
</div>

<br>

<div class="uk-button-group today-type-append" data-uk-button-radio>
    <button class="uk-button uk-active" data="1">日</button>
    <button class="uk-button" data="2">周</button>
    <button class="uk-button" data="3">月</button>
</div>

<div style="position: relative;">
    <div id="user-main-append" style="height: 500px;"></div>
</div>


<script type="text/javascript">

    // 路径配置
    require.config({
        paths: {
            echarts: 'http://echarts.baidu.com/build/dist'
        }
    });

    var option = {
        toolbox: {
            show : true,
            feature : {
                mark : {show: true},
                dataView : {show: true, readOnly: false},
                magicType : {show: true, type: ['line', 'bar', 'pie']},
                restore : {show: true},
                saveAsImage : {show: true}
            }
        },
        legend: {
            data:[]
        },
        xAxis : [
            {
                type : 'category',
                data : []
            }
        ],
        yAxis : [
            {
                type : 'value'
            }
        ],
        series : []
    };
    // 使用
    var myChart;
    require(
            [
                'echarts',
                'echarts/chart/line',   // 按需加载所需图表，如需动态类型切换功能，别忘了同时加载相应图表
                'echarts/chart/bar'
            ],
            function (ec) {
                // 为echarts对象加载数据
                myChart = ec.init(document.getElementById('register-main'));
                // 过渡---------------------
                myChart.showLoading({
                    text: '正在努力的读取数据中...',    //loading话术
                });
                initEchartsData(); // 初始化数据
            }
    );

    function initEchartsData(type) {
        option.series = []; // 设置默认值
        option.xAxis[0].data = []; // 设置默认值
        option.legend.data = [];

        $.post("/stat/user-experience-and-user-data.json", {"type":type}, function(result){
            // ajax callback
            myChart.hideLoading();

            option.xAxis[0].data = result["echarts"]["xAxisData"];
            option.legend.data = result["echarts"]["legendData"];

            var seriesData = result["echarts"]["seriesData"];
            for (var i = 0; i < option.legend.data.length; i++) {
                console.info("data:" , seriesData[i]);
                option.series.push({
                    "name":option.legend.data[i],
                    "type":"bar",
                    "data":seriesData[i],
                    "markPoint" : {
                        data : [
                            {type : 'max', name: '最大值'},
                            {type : 'min', name: '最小值'}
                        ]
                    }
                })
            }

            if (type) {
                myChart.clear();
            }
            if (option.legend.data.length == 0) {
                $("#register-tip").css("display", "block");
            }
            else {
                $("#register-tip").css("display", "none");
            }

            myChart.setOption(option);
        })
    }
</script>
<script type="text/javascript">

    // 路径配置
    require.config({
        paths: {
            echarts: 'http://echarts.baidu.com/build/dist'
        }
    });

    var optionAppend = {
        toolbox: {
            show : true,
            feature : {
                mark : {show: true},
                dataView : {show: true, readOnly: false},
                magicType : {show: true, type: ['line', 'bar', 'pie']},
                restore : {show: true},
                saveAsImage : {show: true}
            }
        },
        legend: {
            data:[]
        },
        xAxis : [
            {
                type : 'category',
                data : []
            }
        ],
        yAxis : [
            {
                type : 'value'
            }
        ],
        series : []
    };
    // 使用
    var myChartAppend;
    require(
            [
                'echarts',
                'echarts/chart/line',   // 按需加载所需图表，如需动态类型切换功能，别忘了同时加载相应图表
                'echarts/chart/bar'
            ],
            function (ec) {
                // 为echarts对象加载数据
                myChartAppend = ec.init(document.getElementById('user-main-append'));
                // 过渡---------------------
                myChartAppend.showLoading({
                    text: '正在努力的读取数据中...',    //loading话术
                });
                initEchartsAppendData(); // 初始化数据
            }
    );

    function initEchartsAppendData(type) {
        optionAppend.series = []; // 设置默认值
        optionAppend.xAxis[0].data = []; // 设置默认值
        optionAppend.legend.data = [];

        $.post("/stat/user-experience-and-user-append-data.json", {"type":type}, function(result){
            // ajax callback
            myChartAppend.hideLoading();

            optionAppend.xAxis[0].data = result["echarts"]["xAxisData"];
            optionAppend.legend.data = result["echarts"]["legendData"];

            var seriesData = result["echarts"]["seriesData"];
            for (var i = 0; i < optionAppend.legend.data.length; i++) {
                console.info("data:" , seriesData[i]);
                optionAppend.series.push({
                    "name":optionAppend.legend.data[i],
                    "type":"bar",
                    "data":seriesData[i],
                    "markPoint" : {
                        data : [
                            {type : 'max', name: '最大值'},
                            {type : 'min', name: '最小值'}
                        ]
                    }
                })
            }

            if (type) {
                myChartAppend.clear();
            }
            myChartAppend.setOption(optionAppend);
        })
    }

</script>
<script>
  $(function(){

    $(".today-type button").click(function(){
        initEchartsData($(this).attr("data"));
    });

      $(".today-type-append button").click(function(){
          initEchartsAppendData($(this).attr("data"));
      });
  });
</script>

<%@ include file="/WEB-INF/pages/common/bottom.jsp" %>

