<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016-02-29
  Time: 11:03
  To change this template use File | Settings | File Templates.
  运动分享 新风格
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ include file="/WEB-INF/pages/common/head-base-path.jsp" %>
<html>
<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta name="viewport" content="initial-scale=1.0, user-scalable=no"/>

    <title>乐享动 用户运动分享</title>

    <link rel="stylesheet" href="/static/bootstrap/dist/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="http://cache.amap.com/lbs/static/main1119.css"/>
    <%-- 高德地图css --%>


    <style type="text/css">
        * {/*适用ios终端的禁止手指长按*/
            -webkit-touch-callout: none;
            -webkit-user-select: none;
            -moz-user-select: none;
            -ms-user-select: none;
            -o-user-select: none;
            user-select: none;
        }
        #map {
            width: 100%;
            height: 263px;
            overflow: hidden;
            margin: 0;
            font-family: "微软雅黑";
        }

        .anchorBL {
            /*隐藏百度logo*/
            display: none;
        }

        .extremeSpeed {
            height: auto;
            position: relative;
            /*top: -23px;*/
        }

        .extremeSpeed .speed,
        .extremeSpeed .speed [class *="col-"] {
            min-height: 4px;
            font-size: 12px;
            color: #FFFFFF;
            line-height: 23px;
        }

        .yunButtomBorder {
            height: 4px;
            max-height: 4px;
            /*设置渐变*/
            background: -webkit-linear-gradient(left, rgba(255, 0, 0, 1), rgba(0, 255, 0, 1));
            /* Safari 5.1 - 6 */
            background: -o-linear-gradient(right, rgba(255, 0, 0, 1), rgba(0, 255, 0, 1));
            /* Opera 11.1 - 12*/
            background: -moz-linear-gradient(right, rgba(255, 0, 0, 1), rgba(0, 255, 0, 1));
            /* Firefox 3.6 - 15*/
            background: linear-gradient(to right, rgba(255, 0, 0, 1), rgba(0, 255, 0, 1));
            /* 标准的语法  */
        }

        .userText {
            height: 55px;
            background-color: #1C1B21;
            max-height: 55px;
            padding: 10px 0;
        }

        .userText [class *="col-"] {
            color: #FFFFFF;
        }

        .userImg {
            width: 44px;
            height: 44px;
            max-height: 44px;
            margin-top: 5.5px;
        }

        .userText div .list-unstyled {
            height: 44px;
            max-height: 44px;
            margin-top: 5px;
            margin-bottom: 15px;
            margin-left: -35%;
        }

        .userText div .list-unstyled li {
            /*height: 50%;
            line-height: 27.5px;*/
        }

        #userName,
        #addTime {
            height: 28px;
        }
        #addTime {
            line-height: 28px;
        }

        .jumbotron {
            position: relative;
            top: -15px;
            background-color: #1C1B21;
            color: #FFFFFF;
            padding-top: 0px;
        }

        .h1 {
            font-size: 90px;
            margin-top: 18px;
        }

        .glText {
            font-size: 11px;
        }

        body,
        .yunParameter,
        .navbar-fixed-bottom,
        .myCanvas,
        .myCanvas_div {
            background-color: #1C1B21;
        }

        body {
            /*margin-bottom: -90px;*/
        }

        .navbar-fixed-bottom {
            background-color: #1C1B21;
            color: #FFFFFF;
            border: 0px;
        }

        .yunParameter {
            position: relative;
            top: -60px;
        }

        .logo {
            width: 25px;
            position: absolute;
            z-index: 1000;
            top: 10px;
            right: 15px;
        }

        .myCanvas {
            width: 100%;
        }

        .myCanvas_div {
            height: auto;
        }

        .chart_speed_color {
            color: #8e65e3;
        }

        .chart_cadence_color {
            color: #6167C3;
        }

        .textColor_61da66 {
            color: #61da66;
        }

        .textColor_ecb267 {
            color: #ecb267;
        }

        .textColor_6d87f8 {
            color: #6d87f8;
        }

        .textColor_9c71e6 {
            color: #9c71e6;
        }

        .tableTitle {
            font-size: 16px;
        }

        .tableTd {
            font-size: 11px;
        }

        .navbar-fixed-bottom {
            height: 55px;
        }

        .navbar-fixed-bottom .container,
        .navbar-fixed-bottom .container .row,
        .navbar-fixed-bottom .container .row [class*='col-'] {
            height: inherit;
            line-height: 55px;
        }

        a {
            color: #FFFFFF;
        }

        a:hover,
        a:link,
        a:visited {
            color: #FFFFFF;
        }

        .outText {
            color: #FFFFFF;
        }

        .btn {
            margin-top: 10px;
            background-color: #f4ac1f;
            color: #FFFFFF;
            border: 0px;
        }

        .yunParameterImgParent{
            text-align: center;
        }

        .yunParameterImg {
            max-width: 24px;
            width: 24px;
            display: inline;
        }

        .color71E6CF {
            color: #71E6CF;
        }

        #userName {
            word-break: keep-all; /* 不换行 */
            white-space: nowrap; /* 不换行 */
            overflow: hidden; /* 内容超出宽度时隐藏超出部分的内容 */
            text-overflow: ellipsis; /* 当对象内文本溢出时显示省略标记(...) ；需与overflow:hidden;一起使用。*/
        }

        #step,#calorie,#yunTime,#runTime{
            padding-left: 0px;
            padding-right: 0px;
        }

        .yun-time-cls{
            padding-left: 0px;
            padding-right: 0px;
            width: 30%;
        }
        .run-time-cls{
            padding-left: 0px;
            padding-right: 0px;
            width: 20%;
        }

    </style>

    <script type="text/javascript"
            src="http://api.map.baidu.com/library/AreaRestriction/1.2/src/AreaRestriction_min.js"></script>
    <script async src="http://c.cnzz.com/core.php"></script>

    <%--  百度统计  --%>
    <script type="text/javascript">
        var _hmt = _hmt || [];
        (function () {
            var hm = document.createElement("script");
            hm.src = "//hm.baidu.com/hm.js?b5bf26e306096c5c177896b7d17f6d74";
            var s = document.getElementsByTagName("script")[0];
            s.parentNode.insertBefore(hm, s);
        })();
    </script>

</head>

<body style="padding-bottom: 100px;">

<div id="map"></div>

<div id="userRunShareNewBig" class="container extremeSpeed">
    <%--<div class="row speed" style="background-image: url('static/user-run-share/imgs/396359921908202185.png')">
        <div class="col-xs-6 col-sm-6 col-md-6 col-lg-6" style="text-align: left;">
            最慢:<span id="min">0.00</span>
        </div>
        <div class="col-xs-6 col-sm-6 col-md-6 col-lg-6" style="text-align: right;">
            最快:<span id="max">0.00</span>
        </div>
    </div>--%>
    <%--<div class="row yunButtomBorder"></div>--%>
    <div class="row userText">
        <div class="col-xs-3 col-sm-3 col-md-3 col-lg-3">
            <img id="userImg" class="img-responsive img-rounded userImg" src=""/>
        </div>
        <div class="col-xs-5 col-sm-5 col-md-5 col-lg-5">
            <ul class="list-unstyled">
                <li id="userName" style="font-size: 24px"><%--${share.user.name}--%></li>
                <li>ID:${share.uid}</li>
            </ul>
        </div>
        <div class="col-xs-4 col-sm-4 col-md-4 col-lg-4 text-right">
            <ul class="list-unstyled color71E6CF">
                <li id="addTime"></li>
                <li id="addTimeH"></li>
            </ul>
        </div>
    </div>
</div>
<div class="jumbotron text-center">
    <h1 id="distance" class="h1"><fmt:formatNumber value="${share.distance / 1000}" pattern="#,###,###.##"/></h1>
    <p class="glText">公里</p>
</div>
<div class="container yunParameter">
    <div class="row">
        <div class="col-xs-3 col-sm-3 col-md-3 col-lg-3 yunParameterImgParent">
            <img class="img-responsive img-rounded center-block yunParameterImg"
                 src="/static/user-run-share/imgs/icon_bushu.png"/>
        </div>
        <div class="col-xs-3 col-sm-3 col-md-3 col-lg-3 yunParameterImgParent">
            <img class="img-responsive img-rounded center-block yunParameterImg"
                 src="/static/user-run-share/imgs/icon_kaluli.png"/>
        </div>
        <div class="col-xs-3 col-sm-3 col-md-3 col-lg-3 yunParameterImgParent yun-time-cls">
            <img class="img-responsive img-rounded center-block yunParameterImg"
                 src="/static/user-run-share/imgs/icon_shijian.png"/>
        </div>
        <div class="col-xs-3 col-sm-3 col-md-3 col-lg-3 yunParameterImgParent run-time-cls">
            <img class="img-responsive img-rounded center-block yunParameterImg"
                 src="/static/user-run-share/imgs/icon_peisu_l.png"/>
        </div>
    </div>
    <div class="row text-center" style="font-size: 21px">
        <div id="step" class="col-xs-3 col-sm-3 col-md-3 col-lg-3 textColor_61da66"></div>
        <div id="calorie" class="col-xs-3 col-sm-3 col-md-3 col-lg-3 textColor_ecb267"></div>
        <div id="yunTime" class="col-xs-3 col-sm-3 col-md-3 col-lg-3 textColor_6d87f8 yun-time-cls"></div>
        <div id="runTime" class="col-xs-3 col-sm-3 col-md-3 col-lg-3 textColor_9c71e6 run-time-cls"></div>
    </div>
    <div class="row text-center" style="font-size: 11px">
        <div class="col-xs-3 col-sm-3 col-md-3 col-lg-3 textColor_61da66">
            步
        </div>
        <div class="col-xs-3 col-sm-3 col-md-3 col-lg-3 textColor_ecb267">
            卡路里
        </div>
        <div class="col-xs-3 col-sm-3 col-md-3 col-lg-3 textColor_6d87f8 yun-time-cls">
            时间
        </div>
        <div class="col-xs-3 col-sm-3 col-md-3 col-lg-3 textColor_9c71e6 run-time-cls">
            配速
        </div>
    </div>
</div>

<div class="container-fluid" style="/*margin-bottom: 70px;position: relative;*/top: -70px;height: 900px;">
    <div class="container chart_speed_color">
        <div class="row">
            <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 text-center tableTitle">
                速度图表
            </div>
            <div class="col-xs-6 col-sm-6 col-md-6 col-lg-6 tableTd">
                公里/小时
            </div>
            <div class="col-xs-6 col-sm-6 col-md-6 col-lg-6 text-right tableTd">
                平均:<span id="speed">0</span>公里/小时
            </div>
        </div>
        <div class="myCanvas_div">
            <canvas id="myChart" class="myCanvas"></canvas>
            <div style="height: 200px;line-height: 200px;display: none;" class="text-center outText">
                记步文件丢失
            </div>
            <div id="speedTableUnit" class="text-right tableTd">时间:分</div>
        </div>

    </div>

    <div class="container chart_cadence_color" style="margin-bottom: 20px;">
        <div class="row">
            <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 text-center tableTitle">
                步频图表
            </div>
            <div class="col-xs-6 col-sm-6 col-md-6 col-lg-6 tableTd">
                步/分钟
            </div>
            <div class="col-xs-6 col-sm-6 col-md-6 col-lg-6 text-right tableTd">
                平均:<span id="detail">0</span>步/分钟
            </div>
        </div>
        <div class="myCanvas_div">
            <canvas id="myChart1" class="myCanvas"></canvas>
            <div style="height: 200px;line-height: 200px;display: none;" class="text-center outText">
                记步文件丢失
            </div>
            <div id="frequencyTableUnit" class="text-right tableTd">时间:分</div>
        </div>
    </div>


    <div class="container chart_cadence_color heartRate" style="margin-bottom: 70px;">
        <div class="row">
            <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 text-center tableTitle">
                心率图表
            </div>
            <div id="maxHR" class="col-xs-6 col-sm-6 col-md-6 col-lg-6 tableTd">
                最大心率:
            </div>
            <div id="avgHR" class="col-xs-6 col-sm-6 col-md-6 col-lg-6 text-right tableTd">
                平均心率:
            </div>
        </div>
        <div class="myCanvas_div">
            <canvas id="heartCanvas" height="230"></canvas>
            <div style="height: 200px;line-height: 200px;display: none;" class="text-center outText">
                记步文件丢失
            </div>
            <div id="heartRateTableUnit" class="text-right tableTd">时间:分</div>
        </div>
    </div>
</div>

<img class="img-responsive img-rounded logo" src="/static/user-run-share/imgs/logo.png"/>

<nav id="download" class="navbar navbar-default navbar-fixed-bottom">
    <a href="http://a.app.qq.com/o/simple.jsp?pkgname=com.fitmix.sdk">
        <div class="container">
            <div class="row">
                <div class="col-xs-3 col-sm-3 col-md-3 col-lg-3">
                    <img class="img-responsive img-rounded userImg" src="/static/user-run-share/imgs/logo.png"/>
                </div>
                <div class="col-xs-5 col-sm-5 col-md-5 col-lg-5" style="left: -10%;">
                    乐享动APP
                </div>
                <div class="col-xs-4 col-sm-4 col-md-4 col-lg-4 text-right">
                    <button type="button" class="btn btn-default">下载</button>
                </div>
            </div>
        </div>
    </a>
</nav>


<script>
    (function (window) {
        var share = function () {
                } || {};
        share = {
            /**
             *  跑步模式 ： 室外
             */
            MODAL_TYPE_INDOOR: 1,
            /**
             * 跑步模式 ： 室内.
             */
            MODAL_TYPE_OUTDOOR: 2,
            /**
             * 一些属性
             * */
            attributes: {
                //用户分享记录类型
                model: ${share.model},
                //获取轨迹文件
                detail: ${share.detail},
                startTime: ${share.startTime},
                endTime: ${share.endTime},
                runTime: ${share.runTime},
                distance: ${share.distance},
                //获取记步文件
                stepDetail: ${share.stepDetail},
                step: ${share.step},
                userImg: "${share.user.avatar}",
                userName: "${share.user.name}",
                step:"${share.step}",
                calorie:"${share.calorie}",
                currentAge:"${share.heartRate.currentAge}",
                currentRestHeartRate:"${share.heartRate.currentRestHeartRate}",
                maxHR: "${share.heartRate.maxHeartRate}",
                avgHR: "${share.heartRate.heartRateAvg}",
                hrMax:"${share.heartRate.hrMax}"
            }
        };

        window.share = share;
    })(window);
</script>
<script src="/static/jquery-1.11.2.min.js"></script>
<script src="/static/util/delete.js"></script>
<%--<script src="static/BUMap/BUMap.js"></script>--%>
<script src="http://webapi.amap.com/maps?v=1.3&key=0e9cc8786d487525f8759b2e3bd9d476&callback=init"></script>
<script src="/static/GDMap/GBMap.js"></script>
<script src="/static/user-run-share/js/util.js"></script>
<script src="/static/Chart.js"></script>
<script src="/static/share-new/chartUtil.js"></script>
<script src="/static/user-run-share/js/recordingFileXml.js"></script>
<script src="/static/chart/chart.js"></script>
<script src="/static/share-new/HeartRate.js"></script>
<script id="userRunShateNewEndScript">
    $(function () {
        $("#distance").text((window.share.attributes.distance / 1000).toFixed(2));
        var deleteBigId = "map";

        //设置用户名称
        $("#userName").text(getUserName(window.share.attributes.userName));


        var scrollTopNumber = $(window).scrollTop();//原始滚动条位置
        var gun = 0;
        var scrollTypeTimeOut = null;

        function scrollType() {
            if ($(window).scrollTop() == scrollTopNumber) {//停止滑动显示底部在家条
                //$("#download").show();
                if ($("#download").css("opacity") != "1") {
                    $("#download").animate({
                        opacity: "1",
                    }, 600, function () {
                    });
                }

                scrollTopNumber = $("#download").scrollTop();//原始滚动条位置
                clearInterval(scrollTypeTimeOut);
                scrollTypeTimeOut = null;

            }
        }

        $(window).on("scroll", function () {
            //$("#download").hide();
            gun = $(window).scrollTop();
            if (null == scrollTypeTimeOut) {
                //scrollTypeTimeOut = setInterval(scrollType,1000);
            } else {
                if ($("#download").css("opacity") == "1") {
                    $("#download").animate({
                        opacity: "0.2",
                    }, 600);
                }
            }
        });

        $("#userImg").attr("src", window.share.attributes.userImg == "" ? "/static/user-run-share/imgs/895612581648387461.png" : window.share.attributes.userImg);

        if (window.share.attributes.model == window.share.MODAL_TYPE_OUTDOOR) {
            $("#map,.logo").remove();
            deleteBigId = "userRunShareNewBig";
        }
        /* else {
         loadJSScript();
         }*/

        var startTime = new Date(window.share.attributes.startTime);

        $("#addTime").text(startTime.Format(DataPrototype.FORMATSTR_2));//开始时间 xxxx/xx/xx
        $("#addTimeH").text(startTime.Format(DataPrototype.FORMATSTR_3));//开始小时:分

        $("#yunTime").text(pedometer.getTimeDisparity(window.share.attributes.runTime));//总时长
        $("#runTime").text(putRunTime(pedometer.runTime, window.share.attributes.distance / 1000));//配速
        $("#step").text(window.share.attributes.step);
        $("#calorie").text(window.share.attributes.calorie);
        var stepDetail = window.share.attributes.stepDetail["array"];

        if (stepDetail.length > 0) {

            //调试ios android 图表数据 getStepAve
            createTable(stepDetail);

        } else {
            $(".myCanvas").remove();
            $(".outText").show();
        }

        //心率图
        var heartRateArray = window.share.attributes.stepDetail["HeartRateArray"];
        if(typeof heartRateArray != 'undefined' && heartRateArray.length > 0) {
            if(typeof window.share.attributes.maxHR != 'undefined') {
                $("#maxHR").text("最大心率:" + Math.round(parseInt(window.share.attributes.maxHR)));
                $("#avgHR").text("平均心率:" + Math.round(parseInt(window.share.attributes.avgHR)));
            } else {
                $("#maxHR").text("最大心率:" + 0);
                $("#avgHR").text("平均心率:" + 0);
            }

            if(heartRateArray[0].time > 1000000) {
                //计算安卓横坐标
                resetX(heartRateArray);
            }

            var width = $(".myCanvas_div").width();
            var heartCanvas = document.getElementById("heartCanvas");
            heartCanvas.width = width;
            var ctx = heartCanvas.getContext("2d");
            //缩放
            retinaScale(ctx);
            var heartRate = heartRateInterval(window.share.attributes.currentRestHeartRate, window.share.attributes.currentAge);
            heartRateInit(ctx, width, heartRate);
            drawHeartRateLineY(ctx, width, heartRateArray, heartRate);
            drawHeartRateLineX(ctx, width, heartRateArray);
        } else {
            $(".heartRate").hide();
        }

        //屏蔽广告
        deleteAdvertising(deleteBigId,"userRunShateNewEndScript");
    });


</script>
</body>