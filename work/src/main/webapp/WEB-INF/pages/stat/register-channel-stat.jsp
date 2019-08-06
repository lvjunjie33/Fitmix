<%--
  Created by IntelliJ IDEA.
  User: sin
  Date: 2015/8/25
  Time: 9:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/pages/common/nav.jsp" %>
<!-- ECharts单文件引入 -->
<script src="http://echarts.baidu.com/build/dist/echarts.js"></script>

<div class="uk-form">
    <%--<input type="text" data-uk-datepicker="{format:'YYYY-MM-DD'}">--%>
    <input type="button" class="uk-button" data-uk-datepicker="{format:'YYYY-MM-DD'}" value="${currentDate}" beginTime/>
    <input type="button" class="uk-button" data-uk-datepicker="{format:'YYYY-MM-DD'}" value="${currentDate}" endTime/>
    <button class="uk-button" stat-start>统计</button>
</div>
<div style="position: relative;width: 2000px">
    <div id="register-main" style="height: 500px;"></div>
    <div id="register-tip" style="text-align: center;display: none;position: absolute;z-index: 11;top:0px;left:49%;">无数据</div>
</div>


<table class="uk-table uk-table-striped uk-text-nowrap uk-table-striped uk-table uk-table-hover">
    <thead>
        <tr>
            <th>序号</th>
            <th>用户编号</th>
            <th>渠道号</th>
            <th>注册类型</th>
        </tr>
    </thead>
    <tbody>

    </tbody>

</table>

<%@ include file="/WEB-INF/pages/common/bottom.jsp" %>

<script type="text/javascript">

    $(function(){
        $("[stat-start]").click(function(){
            var beginTime = $("[beginTime]").val();
            var endTime = $("[endTime]").val();
            initEchartsData(beginTime, endTime);
        });
    });

</script>

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
              magicType : {show: true, type: ['line', 'bar']},
              restore : {show: true},
              saveAsImage : {show: true}
          }
      },
      calculable : true,
      tooltip : {
          trigger: 'axis'
      },
      legend: {
        data:[]
      },
      xAxis : [
          {
              type : 'category',
              boundaryGap : false,
              data : []
          }
      ],
      yAxis : [
          {
              type : 'value',
              data:[],
              axisLabel : {
                  formatter: '{value}'
              }
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
              text: '正在努力的读取数据中...'    //loading话术
          });
          initEchartsData(); // 初始化数据
      }
  );

  function initEchartsData(beginTime, endTime) {
      option.series = []; // 设置默认值
      option.xAxis[0].data = []; // 设置默认值
      option.legend.data = [];

      $.post("/stat/register-channel-stat-data.json", {"beginTime":beginTime, "endTime":endTime}, function(result){
          // ajax callback
          myChart.hideLoading();
          // 设置表格数据
          setTableData(result["registerType"]);

          option.xAxis[0].data = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14 ,15 ,16 ,17, 18, 19, 20, 21, 22, 23, 24];
          option.legend.data = result["echarts"]["legendData"];

          $.each(option.legend.data, function(i, legendData){
              var seriesData = [];
              $.each(option.xAxis[0].data, function(j, xAxisData){
                  var isBool = false;
                  $.each(result["registerChannel"], function(indexJ, registerChannel){
                      if (xAxisData == registerChannel["hour"] && legendData == registerChannel["channelName"]) {
                          seriesData.push(registerChannel["userRegisterTypeStatId"].length);
                          return isBool = true;
                      }
                  })
                  if (!isBool) {
                      seriesData.push(0);
                  }
              })
              option.series.push({
                  "name":legendData,
                  "type":"line",
                  "data":seriesData,
                  markPoint : {
                      data : [
                          {type : 'max', name: '最大值'}
                      ]
                  }
              })
              console.info(seriesData);
          })
          if (beginTime) {
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


    function setTableData(data) {
        console.info(data, "zz");
        var html = "";
        $.each(data, function(index, da){
            html += "<tr><td>{0}</td><td>{1}</td><td>{2}</td><td>{3}</td></tr>".format(index, da.uid, dictionaryName(da.channel ? da.channel : ""), registerTypeName(da.registerType));
        })
        $("tbody").html(html);
    }

  function dictionaryName(data) {
      var channelDictionary = ${channelDictionary};
      for (var i = 0; i < channelDictionary.length; i++) {
          var dic = channelDictionary[i];
          if (data == "appStore") { // 处理 中文 渠道
              return "appStore";
          }
          else if ((data == dic.value) || (/\s+/.test(data) && parseInt(data) == dic.value)) {  // app 上传渠道 001 or 1 ，字典 1 处理不兼容
              return dic.name;
          }
      }
      return "<label style='color: #ff567f;'>error({0})</label>".format(data);
  }

    function registerTypeName(data) {
        var result = "";
        switch (data) {
            case 1:
                result = "app";
                break
            case 2:
                result = "QQ";
                break
            case 3:
                result = "微信";
                break
            case 4:
                result = "微博";
                break
        }
        return result;
    }
</script>