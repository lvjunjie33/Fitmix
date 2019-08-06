<%--
  app数据
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016-02-29
  Time: 11:03
  To change this template use File | Settings | File Templates.
  运动分享 新风格
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ include file="/WEB-INF/pages/common/head-base-path.jsp" %>
<html>
<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>

    <title>乐享动 用户运动分享</title>
    <script src="/static/mobile/mobile-util.js"></script>
    <link href="//cdn.bootcss.com/minireset.css/0.0.2/minireset.min.css" rel="stylesheet">
    <link type="text/css" rel="stylesheet" href="/static/user-run-share/css/user-run-share-new-1.css">
    <link type="text/css" rel="stylesheet" href="/static/user-run-share/css/style1.css">


</head>

<body>

<div class="m-container">
    <div class="map-wrapper">
        <div id="map" class="map">
        </div>
        <div class="map-position" onclick="positionClick()"  style="opacity: 0;">

                <i class="icon icomoon icon-position"></i>

        </div>
        <div class="map-especially" style="opacity: 0;z-index:3000">
            <img src="/static/user-run-share/imgs/christmas.png" alt="" width="100%">
            <div class="map-especially-position">
                <%--<div onclick="turtle()">--%>
                    <%--<div class="turtle">--%>
                        <%--<img src="/static/user-run-share/imgs/elderly.png" alt="" class="map-especially-img">--%>
                    <%--</div>--%>
                    <%--<div class="turtle-text">--%>
                        <%--<span class="slow">最慢配速</span>--%>
                    <%--</div>--%>
                <%--</div>--%>
                <span class="km" id="distance"></span>
                <div onclick="rabbit()" class="rabbitAll">
                    <div class="rabbit">
                        <img src="/static/user-run-share/imgs/deer.png" alt="" class="map-especially-img">
                    </div>
                    <div class="rabbit-text">
                        <span class="fast">最快配速</span>
                    </div>
                </div>
            </div>
        </div>

        <div class="map-bottom" style="opacity: 0;z-index:3000">
                <%--<div onclick="turtle()">--%>
                    <%--<div class="turtle">--%>
                        <%--<img src="/static/user-run-share/imgs/turtle.png" alt="">--%>
                    <%--</div>--%>
                    <%--<div class="turtle-text">--%>
                        <%--<span class="slow">最慢配速</span>--%>
                    <%--</div>--%>
                <%--</div>--%>
                <span class="km" id="distance"></span>
                <div onclick="rabbit()" class="rabbitAll">
                    <div class="rabbit">
                        <img src="/static/user-run-share/imgs/rabbit.png" alt="" >
                    </div>
                    <div class="rabbit-text">
                        <span class="fast">最快配速</span>
                    </div>
                </div>
        </div>
    </div>

    <div class="no-map" style="display: none">
        <div class="div-bind">

        </div>
        <div class="div-km">
            <span class="span-km">km</span>
        </div>
    </div>

    <div class="overview-wrapper data-click1"">
        <div class="text-position">
            <div class="data-text">
                运动数据<span>ACTIVITY DATA</span>
            </div>
            <div class="line"></div>
        </div>
        <span class="iconDown">
           <i class="icon iconfont icon-down"></i>
        </span>
    </div>
    <div class="data-container">
        <div class="red-left">
            <img src="/static/user-run-share/imgs/red_left.png" alt="">
        </div>


        <%-- 8个计算 --%>
        <div class="info-wrapper info-wrapper-1">
            <div class="info-item info-item-1">
                <div class="icon">
                    <i class="icon iconfont icon-shisu1"></i>
                </div>
                <div class="content">
                    <div id="matchSpeed" class="text"></div>
                    <div class="desc">配速:分钟/公里</div>
                </div>
            </div>
            <div class="interval"></div>
            <c:choose>
                <c:when test="${isIos == 'true'}">
                    <div class="info-item info-item-2" id="data-item">
                        <div class="icon">
                            <i class="icon iconfont icon-haiba"></i>
                        </div>
                        <div class="content">
                            <div id="bufu" class="text"></div>
                            <div class="desc">步幅:米</div>
                        </div>
                    </div>
                </c:when>
                <c:otherwise>
                    <div class="info-item info-item-2" id="data-item">
                        <div class="icon">
                            <i class="icon iconfont icon-bufu"></i>
                        </div>
                        <div class="content">
                            <div id="bufu" class="text"></div>
                            <div class="desc">步幅:米</div>
                        </div>
                    </div>
                </c:otherwise>
            </c:choose>


        </div>
        <div class="info-wrapper">
            <div class="info-item info-item-3">
                <div class="icon">
                    <i class="icon icomoon icon-allfoot"></i>
                </div>
                <div class="content">
                    <div id="step" class="text"></div>
                    <div class="desc">总步数</div>
                </div>
            </div>
            <div class="interval"></div>
            <div class="info-item info-item-4">
                <div class="icon">
                    <i class="icon iconfont icon-shisu"></i>
                </div>
                <div class="content">
                    <div id="speed" class="text"></div>
                    <div class="desc">时速:公里/小时</div>
                </div>
            </div>
        </div>
        <div class="info-wrapper">
            <div class="info-item info-item-5">
                <div class="icon">
                    <i class="icon icomoon icon-kaluli"></i>
                </div>
                <div class="content">
                    <div id="calorie" class="text"></div>
                    <div class="desc">消耗:大卡</div>
                </div>
            </div>
            <div class="interval"></div>
            <div class="info-item info-item-6">
                <div class="icon">
                    <i class="icon iconfont icon-zongbushu"></i>
                </div>
                <div class="content">
                    <div id="bpm" class="text"></div>
                    <div class="desc">步频:步/分钟</div>
                </div>
            </div>
        </div>
        <div class="info-wrapper">
            <div class="info-item info-item-7">
                <div class="icon">
                    <i class="icon iconfont icon-shijian"></i>
                </div>
                <div class="content">
                    <div class="text">
                        <span id="hour"></span>
                        <span class="colon">:</span>
                        <span id="minute"></span>
                        <span class="colon">:</span>
                        <span id="second"></span>
                    </div>
                    <div class="desc">总计时间</div>
                </div>
            </div>
            <div class="interval"></div>
            <div class="info-item info-item-8">
                <div class="icon">
                    <i class="icon icomoon icon-kaluli"></i>
                </div>
                <div class="content">
                    <div id="ranzhi" class="text"></div>
                    <div class="desc">燃脂:克</div>
                </div>
            </div>
        </div>
    </div>
    
    
    <div class="overview-wrapper data-click2">
        <div class="text-position">
            <div class="data-text">
                运动图表<span>ACTIVITY CHART</span>
            </div>
            <div class="line"></div>
        </div>
        <span class="iconDown">
           <i class="icon iconfont icon-down"></i>
        </span>
    </div>
    <div class="diagram-wrapper">

        <div class="title-wrapper">
            <div class="title title-bu">
                <div class="line"></div>
                <div class="text">步频曲线</div>
                <div class="line"></div>
            </div>
        </div>
        <div class="step-table">
            <div class="myCanvas_div">
                <div class="vertical"><span class="text-bu">步/分钟</span><span class="avg-step text-bu step-speed"></span></div>
                <div style="text-align: center;margin-bottom: 1rem">
                    <div id="mainBuPin" style="width:100%;height:8rem" class="mainHeight"></div>
                    <%--<canvas id="myChart1" class="myCanvas"></canvas>--%>
                </div>
                <div style="height: 200px;line-height: 200px;display: none;" class="text-center outText">
                    记步文件丢失
                </div>
                <%--<div id="frequencyTableUnit" class="text-right tableTd text-bu text-mins">Mins</div>--%>
            </div>
        </div>

        <div class="title-wrapper title-position">
            <div class="red-right">
                <img src="/static/user-run-share/imgs/red_right.png" alt="">
            </div>
            <div class="title title-su">
                <div class="line"></div>
                <div class="text">速度曲线</div>
                <div class="line"></div>
            </div>
        </div>
        <div class="speed-table">
            <div class="myCanvas_div">
                <div class="vertical"><span class="text-su" id="su-span"></span><span class="avg-speed text-su step-speed"></span></div>
                <div style="text-align: center;margin-bottom: 1rem">
                    <div id="mainSuDu" style="width:100%;height:8rem" class="mainHeight"></div>
                    <%--<canvas id="myChart" class="myCanvas"></canvas>--%>
                </div>

                <div style="height: 200px;line-height: 200px;display: none;" class="text-center outText">
                    记步文件丢失
                </div>
                <%--<div id="speedTableUnit" class="text-right tableTd text-su text-mins">Mins</div>--%>
            </div>
        </div>

        <div class="title-wrapper heart">
            <div class="title title-heart">
                <div class="line"></div>
                <div class="text">心率曲线</div>
                <div class="line"></div>
            </div>
        </div>

        <div class="heart-rate-table">
            <div class="myCanvas_div">
                <div class="vertical"><span class="text-heart">次数/分钟</span><span class="avg-heart"></span></div>
                <div style="text-align: center">
                    <div id="mainHeart" style="width:100%;height:8rem"></div>
                    <%--<canvas id="heartCanvas" class="myCanvas"></canvas>--%>
                </div>
                <div style="height: 200px;line-height: 200px;display: none;" class="text-center outText">
                    记步文件丢失
                </div>
                <%--<div id="heartRateTableUnit" class="text-right tableTd">Mins</div>--%>
            </div>
        </div>
    </div>

</div>
<div id="snowzone">

</div>
<div class="footer">
    <div class="speed-bar"></div>
    <div class="left">
        <div class="logo">
            <img src="/static/user-run-share/imgs/logo.png">
        </div>

    </div>
    <div class="top_center">
        <img src="/static/user-run-share/imgs/top_center.png" alt="">
    </div>
    <div class="right">
        <div class="download">
            <button onclick="downloadApp();">立即下载</button>
        </div>
        <%--<div class="open">--%>
        <%--<button onclick="openApp();">直接打开</button>--%>
        <%--</div>--%>
    </div>
</div>
<audio id="bgmusic" preload="preload">
    <source src="/static/user-run-share/celebrate.m4a" type="audio/mpeg">
    <source src="/static/user-run-share/celebrate.ogg" type="audio/ogg">
</audio>

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
                distance2: ${share.distance / 1000},
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
                hrMax:"${share.heartRate.hrMax}",
                elevation: (${share.elevation} + "") == "" ? null : ${share.elevation} + "",
                zipUrl: "http://yyssb.ifitmix.com/" + "${share.zipUrl}"
            }
        };

        window.share = share;
        console.log( JSON.parse(JSON.stringify(window.share)) );
    })(window);

</script>


<script type="text/html" id="markTimeTemplate">
    <div class="mark-time">
        <div class="time">
            <span>{0}</span>
        </div>
    </div>
</script>
<script type="text/html" id="markUserImgTemplate">
    <div class="avatar-wrapper">
        <div class="avatar">
        <img id="avatar" src="${share.user.avatar}">
        </div>
        <div id="userName" class="name"></div>
    </div>
</script>


<script src="/static/jquery-1.11.2.min.js"></script>
<script src="/static/util/delete.js"></script>
<script src="/static/user-run-share/js/util.js"></script>
<script src="/static/user-run-share/js/MapUtil.js"></script>
<%--地图--%>
<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=98dfV32uFI52e1iUnlpYKdbbXSBYhiQY"></script>
<script type="text/javascript" src="/static/user-run-share/js/coordtransform.js"></script>    <%--地图--%>
<script type="text/javascript" src="/static/user-run-share/js/baiduMap.js"></script>   <%--地图--%>
<script src="/static/user-run-share/js/countUp.js"></script>
<script src="/static/user-run-share/js/countInit.js"></script>
<script src="/static/user-run-share/js/echarts.min.js"></script>
<%--<script src="/static/Chart.js"></script>--%>
<%--<script src="/static/share-new/chartUtil.js"></script>--%>
<script src="/static/user-run-share/js/recordingFileXml.js"></script>
<script src="/static/chart/chart-app.js"></script>
<script src="/static/share-new/HeartRate.js"></script>
<script id="userRunShateNewEndScript">

    drawMap(share.attributes.detail.array);
    // $.get('http://zipFileParse.zifer.cn?zip_url=' + share.attributes.zipUrl,function(data) {})
    $(function () {
        //设置用户名称
//        $("#avgHR").text(window.share.attributes.avgHR);
        $("#userName").text(getUserName(window.share.attributes.userName));
        // 设置头像
        if(window.share.attributes.userImg == "") {
            $("#avatar").attr("src", "/static/user-run-share/imgs/895612581648387461.png");
        }

        var stepDetail = window.share.attributes.stepDetail["array"];

        //心率图
        var heartRateArray = window.share.attributes.stepDetail["HeartRateArray"];
        if(typeof heartRateArray != 'undefined' && heartRateArray.length > 0) {

            if(typeof window.share.attributes.maxHR != 'undefined') {
//                $("#maxHR").text("最大心率:" + Math.round(parseInt(window.share.attributes.maxHR)));
//                $("#avgHR").text("平均心率:" + Math.round(parseInt(window.share.attributes.avgHR)));
                var heartRate = heartRateInterval(window.share.attributes.currentRestHeartRate, window.share.attributes.currentAge);
                if(window.share.attributes.avgHR == '') {
                    var avgHR = 0;
                    for(var i = 0; i < window.share.attributes.stepDetail.HeartRateArray.length; i++) {
                        avgHR += window.share.attributes.stepDetail.HeartRateArray[i].heartrate;
                    }
                    window.share.attributes.avgHR = avgHR / window.share.attributes.stepDetail.HeartRateArray.length;
                }
//                var text = getHeartRateInterval(heartRate, Math.round(parseInt(window.share.attributes.avgHR)));
                $(".avg-heart").text("平均:" + Math.round(parseInt(window.share.attributes.avgHR))+"次/分钟");
            } else {
                $("#maxHR").text("最大心率:" + 0);
                $("#avgHR").text("平均心率:" + 0);
            }

            var heartLabel = [];
            var heartData = [];
            if(heartRateArray[0].time > 1000000) {
                //计算安卓横坐标
                heartLabel = resetX(heartRateArray, stepDetail);
                for(var i = 0; i < heartRateArray.length; i++) {
                    heartData.push(heartRateArray[i].heartrate);
                }
            } else {
                for(var i = 0; i < heartRateArray.length; i++) {
                    heartRateArray[i].time = heartRateArray[i].time / 60;
                    var label_ = heartRateArray[i].time;
                    var data_ = heartRateArray[i].heartrate;

                    heartLabel.push(label_);
                    heartData.push(data_);
                }

//                if(heartRateArray[heartRateArray.length - 1].time < stepDetail[stepDetail.length - 1].time) {
//                    heartLabel.push(stepDetail[stepDetail.length - 1].time / 60);
//                }

            }
            var heartLabel_ = [];
            var heartData_ = [];
            var i_ = 0;
            var targetNum = 0;
            var array_ = [];
            for(var i = 0; i<heartLabel.length; i++){
                if (heartLabel[i] < 0) {
                    continue;
                }
                if (heartLabel[i] >= targetNum) {
                    var v_ = parseInt(heartLabel[i]);
                    if (array_.length != 0 && array_[v_] != undefined) {
                        continue;
                    }
                    heartLabel_[i_] = v_;
                    heartData_[i_] = heartData[i];
                    array_[v_] = 0;
                    targetNum ++;
                    i_++;
                }

            }

            heartData = heartData_;
            heartLabel = heartLabel_;
            if (heartData== null || heartData == undefined || heartData.length < 2) {
                $('.heart').css('display', 'none');
                $('.heart-rate-table').css("display", "none");
                return;
            }
            buildHeartChar(heartData, heartLabel);
            console.log("心率");
            console.log(heartData);
            console.log(heartLabel);
        } else {
            $('.heart').css('display', 'none');
            $('.heart-rate-table').css("display", "none");
        }
        //心率图

    });

    //画心率图
    function buildHeartChar(heartData, heartLabel) {
        //初始化图标
        var myChart = echarts.init(document.getElementById('mainHeart'));
        var fonts = $('.avg-heart').css("font-size");
        var font = parseInt(fonts)*3/4;
        var pad = parseInt(fonts)/2;
        var bordWidth = parseInt(fonts)/12;
        var bordRadius = parseInt(fonts)/12*4;
        var optionHeart = {
//                tooltip: {
//                    trigger: 'axis',
////                    trigger : 'item',
//                    show:false,
//                    showDelay: 0,
//                    hideDelay: 0,
//                    transitionDuration:0,
//                    backgroundColor : 'rgba(235,42,97,0.7)',
//                    borderColor : '#eb2a61',
//                    borderRadius : bordRadius,
//                    borderWidth: bordWidth,
//                    padding: pad,    // [5, 10, 15, 20]
//                    textStyle:{
//                        fontSize:font
//                    },
//                    formatter: function (params,ticket,callback) {
//                        console.log(params);
//                        console.log(params[0]);
//                        if(params[0].name == ''){
//                            params[0].name = 0;
//                        }
////                        var res = "X:"+params[0].name+'<br/>'+"Y:"+params[0].value;
//                        var res = "";
//                        return res;
//                    }
//                },
            xAxis: {
                type: 'category',
                boundaryGap:false,
                data: heartLabel,
                splitLine:{
                    show:false,
                    lineStyle: {
                        // 使用深浅的间隔色
                        color: '#303032'
                    }
                },
                axisLine: {
                    lineStyle: {
                        color:'#303032',
                        width:3
                    }
                },//坐标轴颜色
//                    axisTick:{
////                        lineStyle:{
////                            color:'#fff'
////                        },
//                        length:5
//                    },//刻度线颜色
                axisLabel:{
                    show: true,
                    textStyle:{
                        fontSize:font,
                        color: '#d1cdd5'
                        // 让字体变大
                    }
                }
            },
            yAxis: {
                type: 'value',
                splitNumber:1,
                min: 400,
                max: 700
            },
            yAxis: {
                type: 'value',
                splitLine:{
                    show: true,
                    lineStyle: {
                        // 使用深浅的间隔色
                        color: '#303032'
                    }
                },//去除网格线
                axisLine:{
                    lineStyle:{
                        color:'#303032',
                        width:3//这里是为了突出显示加上的
                    }
                },
                axisTick:{
                    lineStyle:{
                        color:'#303032'
                    }
                },//刻度线颜色
                axisLabel:{
                    show: true,
                    textStyle:{
                        fontSize:font,
                        color: '#d1cdd5'
                        // 让字体变大
                    }
                }
            },
            grid: {
                x:"8%",
                x2:"4%",
                y:"9%",
                y2:"10%"
            },
            series: [{
                type: 'line',
                showSymbol: false,
                itemStyle: {
                    normal: {
                        color: "#eb2a61",
                        lineStyle: {
                            color: "#eb2a61"
                        }
                    }
                },
                areaStyle: {
                    normal: {
                        color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
                            offset: 0,
                            color: 'rgba(235,42,97,0.3)'
                        }, {
                            offset: 1,
                            color: 'rgba(235,42,97,0)'
                        }])
                    }
                },
                smooth:true,  //曲线变平滑的
//                    data: heartData,
                data:heartData
            }]
        };
        myChart.setOption(optionHeart);


        $(window).resize(function() {
            myChart.resize();

        });
        window.addEventListener('orientationchange', function(event){
            if ( window.orientation == 180 || window.orientation==0 ) {
                console.log("竖屏");
                window.location.reload();
            }
            if( window.orientation == 90 || window.orientation == -90 ) {
                console.log("横屏");
                window.location.reload();
            }
        });
    }


    //龟兔点击
    var flag = true;
    var flag1 = true;
    function turtle(){
        if(flag == true){
            $('.turtle').css("display","none");
            $('.turtle-text').css("display","block");
            setTimeout(function(){
                $('.turtle').css("opacity","0");
                $('.turtle-text').css("opacity","1");
            },200);
        }else{
            $('.turtle-text').css("display","none");
            $('.turtle').css("display","block");
            setTimeout(function(){
                $('.turtle-text').css("opacity","0");
                $('.turtle').css("opacity","1")
            },200);
        }
        flag = !flag;
    }
    function rabbit(){
        if(flag1 == true){
            $('.rabbit-text').css("display","block");
            $('.rabbit').css("display","none");
            setTimeout(function(){
                $('.rabbit-text').css("opacity","1");
                $('.rabbit').css("opacity","0");
            },200);

        }else{
            $('.rabbit-text').css("display","none");
            $('.rabbit').css("display","block");
            setTimeout(function(){
                $('.rabbit-text').css("opacity","0");
                $('.rabbit').css("opacity","1");
            },200);
        }
        flag1 = !flag1;
    }

        function positionClick(){
            map.centerAndZoom(new BMap.Point(center[0], center[1]), 19);
        }
        $('.data-click1').click(function(e) {
            e.stopPropagation();
            var dataClick1 = $(this).offset().top;
            scrollTo(0,dataClick1+50);
        });

        $('.data-click2').click(function(e) {
            e.stopPropagation();
            var dataClick2 = $(this).offset().top;
            scrollTo(0,dataClick2+50);
        });

        //map

        var mapHeight = parseInt($("#map").css("height").replace("px", ""));
        var height = window.innerHeight;
        var footerHeight = parseInt($(".footer").css("height").replace("px", ""));
        //            var speedInfo = parseInt($(".speed-info").css("height").replace("px", ""));
        var speendBar = parseInt($(".speed-bar").css("height").replace("px", ""));
        var offset = 20;
        var newHeight = height - footerHeight - speendBar;
        $("#map").css({
            height: newHeight + "px"
        });
        $(".map-wrapper").css({
            height: newHeight + "px"
        });
        $("body").css("overflow-x", "hidden");
//            var userMobile = userArg();
//            if(userMobile == true){
//                map.centerAndZoom(new BMap.Point(center[0], center[1]), zoom + 2);
//            }else{
//
//                map.centerAndZoom(new BMap.Point(center[0], center[1]), zoom + 4);
//
//            }




        if($(".no-map").css("display") == 'block'){
            $(".map-bottom #distance").remove();
            $('.map-especially').remove();
            $(".span-km").before("<span class='km' id='distance'></span>");
        }
        function downloadApp() {
            window.location.href = "http://a.app.qq.com/o/simple.jsp?pkgname=com.fitmix.sdk";
        }
        function openApp() {
            window.location.href = "FITMIX://";
        }
</script>
<script src="/static/user-run-share/js/jweixin-1.0.0.js"></script>
<script>

    var Media = document.getElementById('bgmusic');
    if(Media.networkState) {
        Media.src = "/static/user-run-share/celebrate.m4a";
    }
    function autoPlayAudio1() {
        wx.config({
            // 配置信息, 即使不正确也能使用 wx.ready
            debug: false,
            appId: '',
            timestamp: 1,
            nonceStr: '',
            signature: '',
            jsApiList: []
        });

        wx.ready(function() {
            Media.play();
        });
        Media.play();
    }
    $.fn.extend({
        animateCss: function (animationName) {
            var animationEnd = 'webkitAnimationEnd mozAnimationEnd MSAnimationEnd oanimationend animationend';
            this.addClass('animated ' + animationName).one(animationEnd, function() {
//                var th = $(this);
//                setTimeout(function() {
//                    th.removeClass('animated ' + animationName);
//                }, 3000)
            });
        }
    });
   $(window).load(function () {
////        //皇冠 掉落
////        $('.crown').animateCss('fadeInDown');
        // 欢呼声
        autoPlayAudio1();
//        // 背景
////        $(".overview-wrapper").css({
////            background: 'url("/static/user-run-share/imgs/fireworks.gif") no-repeat',
////            backgroundSize: 'cover'
////        });
////        setTimeout(function() {
////            $(".overview-wrapper").css({
////                background: 'url("")'
////            });
////        }, 3000);
    });


    var distance_ = parseFloat((window.share.attributes.distance / 1000).toFixed(2));
    console.log('里程');
    console.log(distance_);
    if(distance_ == 12.25 || distance_ == 0.25 || distance_ == 2.5 || distance_ == 25){
//        alert("进入");
        function snow(left,height,src){
            var div = document.createElement("div");
            var img = document.createElement("img");
            div.appendChild(img);
            img.className = "roll";
            img.src = src;
            div.style.left=left+"px";
            div.style.height=height+"px";
            div.style.width="100%";
            div.style.zIndex="1000";
            div.className="div";
            document.getElementById("snowzone").appendChild(div);
            setTimeout(function(){
                document.getElementById("snowzone").removeChild(div);
                // console.log(window.innerHeight);
            },10000);
        }

        var arr=['bow.png','candy.png','gift.png','green.png','red.png','bow.png','candy.png','gift.png','green.png','red.png','bow.png','candy.png','gift.png','green.png','red.png',
            'bow.png','candy.png','gift.png','green.png','red.png','bow.png','candy.png','gift.png','green.png','red.png','bow.png','candy.png','gift.png','green.png','red.png'];

        var counter = 0;
        function incr() {

            var src = "/static/user-run-share/imgs/"+arr[counter];
            var left = Math.random()*window.innerWidth;
//          	var top = Math.random()*window.innerHeight;
            var height = window.innerHeight;
            snow(left,height,src);
            counter += 1;
            if (counter < 30)
                setTimeout("incr()", 200);
        }
        setTimeout("incr()", 200);
    }


</script>




<script>
    var contentTime = $("#markTimeTemplate").html();
    var date = new Date(window.share.attributes.startTime);
    contentTime = contentTime.replace("{0}", date.Format('MM月dd日 hh:mm'));
    $(".div-bind").append(contentTime);
    $(".div-bind").append($("#markUserImgTemplate").html());
</script>
</body>