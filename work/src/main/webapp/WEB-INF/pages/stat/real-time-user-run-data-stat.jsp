<%--
  Created by IntelliJ IDEA.
  User: wjcaozhi
  Date: 15/7/30
  Time: 下午2:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/pages/common/nav.jsp" %>

<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
  <link rel="stylesheet" href="/static/bootstrap/bootstrap.min.css" />
  <!--<link rel="stylesheet" href="static/css/aggregationPoint.css" />-->
  <style>
    @CHARSET "UTF-8";
    @-moz-document url-prefix() {
      fieldset { display: table-cell; }
    }

    body {
      margin: 0;
      height: 100%;
      width: 100%;
      position: absolute;
    }

    #mapContainer {
      position: absolute;
      top: 0;
      left: 0;
      right: 0;
      bottom: 0;
    }

    .Console {
      position: fixed;
      top:0px;
      right: 120px;
    }
    .dataDomain {
      width: 100%;
      height: 500px;
      position:relative;
      padding: 0px;
      margin: 0px;
      background-color: #ffffff;
      display: none;
      overflow-x:auto;
    }
  </style>

  <title>点聚合显示</title>

</head>
<body>
<div id="mapContainer"></div>
<div id="Console" class="Console" style="position: absolute;">
  <%
    //控制台
  %>
  <div id="aggregation" class="btn-group btn-group-md" role="group"
       aria-label="aggregation">
    <%
      //按钮组
    %>
    <button id="kongzhiDataShow" type="button" class="btn btn-default">
      <span id="kongzhiSpan" class="glyphicon glyphicon-chevron-down"></span>
    </button>
    <button id="all" name="shaiXuan" type="button" class="btn btn-default disabled">
      综合<span id="allSpan" class="badge"></span>
    </button>
    <button id="ting" name="shaiXuan" type="button" class="btn btn-default disabled">
      听歌<span id="tingSpan" class="badge"></span>
    </button>
    <button id="pao" name="shaiXuan" type="button" class="btn btn-default disabled">
      跑步<span id="paoSpan" class="badge"></span>
    </button>
    <button id="bo" name="shaiXuan" type="button" class="btn btn-default disabled">
      直播<span id="boSpan" class="badge"></span>
    </button>
    <button id="kan" name="shaiXuan" type="button" class="btn btn-default disabled">
      直播观众<span id="kanSpan" class="badge"></span>
    </button>
    <button id="dataS" type="button" class="btn btn-default">
      暂停
    </button>
  </div>
  <div id="dataTable" class="dataDomain">
    <%
      //数据域
    %>
    <div class="table-responsive">
      <table class="table table-condensed table-hover text-center">
        <tr>
          <th class="text-center">时间</th>
          <th class="text-center">用户名</th>
          <th class="text-center">事件</th>
          <th class="text-center">经度</th>
          <th class="text-center">纬度</th>
        </tr>
        <tbody id="dataTbody">

        </tbody>

      </table>
    </div>
  </div>
</div>


<script type="text/javascript"
        src="http://webapi.amap.com/maps?v=1.3&key=0a730f3f39c856cec1fa18bd9289cf11"></script>
<!--高德地图-->
<!--<script type="text/javascript" src="../../../static/jquery.1.11.1.min.js"></script>-->
<%--<script type="text/javascript" src="static/bootstrap.min.js"></script>--%>
<script>
  /**
   * 聚合点
   */
  var dataSNumber = ajaxDate = 1,dataTime = 1000 * 60;//数据刷新和ajax请求标识，时间间隔
  var ajaxObj = null;
  var cluster;
  var markers = [];
  // 地图初始化&向地图随机加点
  var map = new AMap.Map("mapContainer", {
    resizeEnable : true,
    // 二维地图显示视口
    view : new AMap.View2D({
      center : new AMap.LngLat(116.397428, 39.90923),
      zoom : 13
    })

  });

  // 随机向地图添加500个标注点
  var mapBounds = map.getBounds();
  var sw = mapBounds.getSouthWest();
  var ne = mapBounds.getNorthEast();

  var typeList = {all:["all","全部"],ting:["ting","听歌"],pao:["pao","跑步"],bo:["bo","直播"],kan:["kan","观众"]},
          typeArray = [["all","全部"],["ting","听歌"],["pao","跑步"],["bo","直播"],["kan","观众"]];

  var renList = {
    all:new Array(),
    ting:new Array(),
    pao:new Array(),
    bo:new Array(),
    kan:new Array()
  };
  /***
   * 设置所有数据聚合点
   * @param array
   */
  function clustrt(array){
    markers.splice(0,markers.length);
    for (var i = 0; i < array.length; i++) {
      var markerPosition = new AMap.LngLat(array[i].lng, array[i].lat);

      var marker = new AMap.Marker(
              {
                // 基点位置
                position : markerPosition,
                // marker图标，直接传递地址url
                icon : "http://developer.amap.com/wp-content/uploads/2014/06/marker.png",
                  //相对于基点的位置
                  offset:{x:-8, y:-34}
              });
      markers.push(marker);
    }
     var n = 0;//array.length - 1;
      if(array.length > 0){
          map.setCenter(new AMap.LngLat(array[n].lng,array[n].lat));
      }
    addCluster(0);
  }

  function myLoad() {
    clearTimeout(ajaxObj);
    var time = "";
    if(renList.all.length > 0){
      time = renList.all[0].date;
    }
    renList.all.splice(0,renList.all.length);
    renList.ting.splice(0,renList.ting.length);
    renList.pao.splice(0,renList.pao.length);
    renList.bo.splice(0,renList.bo.length);
    renList.kan.splice(0,renList.kan.length);
    $.ajax({
      type: "POST",
      url: "/stat/real-time-user-run-data.json",
      data:"time=" + time,
      dataType:"json",
      success:function (msg){
        var maxnumber = msg.data.length;
        for (var i = 0; i < maxnumber; i++) {
//          var lng = msg.data[i].lng;
//          var lat = msg.data[i].lat;
            var lng = msg.data[i].lat;
            var lat = msg.data[i].lng;
           console.log(msg.data[i]);
            var userName = "";
            try{
                userName = msg.data[i].user.name;
            }catch (e){}
          var renObj = {
            name:userName == undefined ? "" : userName,
            type:typeArray[msg.data[i].type][0],
            lng:lng,
            lat:lat,
            date: msg.data[i].addTime
          };
          renList["all"].push(renObj);
          renList[typeArray[msg.data[i].type][0]].push(renObj);
        }
        $("#allSpan").text(renList.all.length);
        $("#tingSpan").text(renList.ting.length);
        $("#paoSpan").text(renList.pao.length);
        $("#boSpan").text(renList.bo.length);
        $("#kanSpan").text(renList.kan.length);

        clustrt(renList.all);

        ajaxObj = setTimeout(myLoad, dataTime);
      },
      error:function (XMLHttpRequest,textStatus,errorThrom){
        console.log("不明原因造成数据获取失败... ...");
      }
    });
  }



  // 添加点聚合
  function addCluster(tag) {
    if (cluster) {
      cluster.setMap(null);
    }
    if (tag == 1) {
      var sts = [ {
        url : "http://developer.amap.com/wp-content/uploads/2014/06/1.png",
        size : new AMap.Size(32, 32),
        offset : new AMap.Pixel(-16, -30)
      }, {
        url : "http://developer.amap.com/wp-content/uploads/2014/06/2.png",
        size : new AMap.Size(32, 32),
        offset : new AMap.Pixel(-16, -30)
      }, {
        url : "http://developer.amap.com/wp-content/uploads/2014/06/3.png",
        size : new AMap.Size(48, 48),
        offset : new AMap.Pixel(-24, -45),
        textColor : '#CC0066'
      } ];
      map.plugin([ "AMap.MarkerClusterer"], function() {
        cluster = new AMap.MarkerClusterer(map, markers, {
          styles : sts
        });
      });
    } else {
      map.plugin([ "AMap.MarkerClusterer"], function() {
        cluster = new AMap.MarkerClusterer(map, markers);
      });
    }
  }
  /***
   * 过滤数据
   */
  function filtrationData(){
    if(this.id == "all"){
      $("#dataTbody tr[dataType!='"+this.id+"']").slideDown("slow");
    }else if(this.id == "ting" || this.id == "pao" || this.id == "bo" || this.id == "kan"){
      $("#dataTbody tr[dataType!='"+this.id+"']").slideUp("slow");
      $("#dataTbody tr[dataType='"+this.id+"']").slideDown("slow");
    }
    clustrt(renList[this.id]);
  }
  /***
   * 设置所有table表格数据
   * @param array
   */
  function dataTable(array) {
    var trStr = '';
    for(var i = 0;i<array.length;i++){

        var currentDate = new Date(array[i].date);

      trStr += '<tr id="data_tr_'+i+'" class="info" dataType="'+typeList[array[i].type][0]+'">' +
              '<td>' +  currentDate.getHours() + ":" + currentDate.getMinutes() + '</td>' +
              '<td>' + array[i].name + '</td>' +
              '<td>' + typeList[array[i].type][1] + '</td>' +
              '<td>' + array[i].lng + '</td>' +
              '<td>' + array[i].lat + '</td>' +
              '</tr>';
    }
    $("#dataTbody").html("");
    $("#dataTbody").html(trStr);

    $("#dataTbody tr").on("click",function (){
      var dataLng = $("#"+this.id+" td").eq(3).html();
      var dataLat = $("#"+this.id+" td").eq(4).html();
      var contet = new AMap.LngLat(dataLng, dataLat);
      map.setZoomAndCenter(17, contet);
    });
  }
  /***
   * 数据刷新控制
   */
  function dataS(){
    var dataSO = $("#dataS");
    if(dataSNumber % 2 != 0){
      clearTimeout(ajaxObj);
      dataSO.text("开始");
    }else {
      myLoad();
      dataSO.text("暂停");
    }
    dataSNumber++;
  }



  $(function ($){
    myLoad();
    $("#kongzhiDataShow").click(function (){
      if($("#dataTable").css("display")=="none"){
        $("div[aria-label='aggregation'] button[name='shaiXuan']").removeClass("disabled");
        $("#kongzhiSpan").removeClass("glyphicon glyphicon-chevron-down");
        $("#kongzhiSpan").addClass("glyphicon glyphicon-chevron-up");
        dataTable(renList.all);
      }else{
        $("div[aria-label='aggregation'] button[name='shaiXuan']").addClass("disabled");
        $("#kongzhiSpan").removeClass("glyphicon glyphicon-chevron-up");
        $("#kongzhiSpan").addClass("glyphicon glyphicon-chevron-down");
      }
      $("#dataTable").slideToggle();
    });
    $("#aggregation button[name='shaiXuan']").on("click",filtrationData);
    $("#dataS").on("click",dataS);

  });
</script>
</body>
</html>





<%@ include file="/WEB-INF/pages/common/bottom.jsp" %>