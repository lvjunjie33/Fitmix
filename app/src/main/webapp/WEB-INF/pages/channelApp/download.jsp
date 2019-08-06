<%--
  Created by IntelliJ IDEA.
  User: fenxio
  Date: 2016/5/10
  Time: 11:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/pages/common/head-base-path.jsp" %>
<html>
<head>
    <title>乐享动 客户端</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable = no, minimum-scale =1.0, maximum-scale=1.0">
    <meta name="keywords" content="乐享动app,运动节奏,运动软件,运动App,运动计步器，跑步节奏,跑步音乐,跑步,减肥,健身,跑步装备,马拉松,慢跑乐享动,运动音乐">
    <meta name="description" content="乐享动客户端，深圳第一蓝筹旗下的第一款跑步软件，记录卡路里，帮助初跑者达到减肥健身的目的，轻松设定跑步目标，减肥健身马拉松一步到位，推荐各类跑步音乐和跑步装备，让跑步变得更加有趣！">
    <link href="http://www.ifitmix.com/img/other/logo-fitmix-x64.png" type="image/x-icon" rel="icon">

</head>
<body>
<link rel="stylesheet" href="/static/channelApp/css/mobile/201506/main.css">
<script type="text/javascript" src="/static/channelApp/js/mobile/201506/respond.min.js"></script>
<style>
    .slides-box{
        max-width: 550px;
        margin: 0 auto;
    }

    .slides-cont {
        position: absolute;
        top: 0%;
        left: 0%;
        margin: 0px;
        width:100%;
        height:100%;
    }

    .ff-rg{
        float: none;
        width: 100%;
        text-align:center;
    }

    .ff-rg .ff-rg-img-1{
        padding-top:5%;
        width: 40%;
    }

    .ff-rg .ff-rg-img-2{
        padding:8% 0px 5% 0px;
        width:70%;
    }


    .ff-download{
        margin: 0px auto;
        width: 100%;
        padding: 0px;
        text-align: center;
    }

    .ff-download-box{
        width: 70%;
        margin: 0 auto;
    }

    .ff-download a{
        margin: 0;
        width: 49%;
        height:auto;
        display: inline-block;
    }
    .ff-download a img {
        width: auto;
        height: 45px;
    }

    body{
        background-image:url(/static/channelApp/images/mobile/201605/BG.png);
        background-repeat: no-repeat;
        background-position: bottom right;
        background-color: rgba(34,33,31,1);
        /*min-width:1024px;*/
        overflow-x: hidden;
        overflow-y: scroll;
    }

    /*.flexslider{*/
    /*width: 100%;*/
    /*background-image:url(./images/mobile/201605/BG.png);*/
    /*background-repeat:no-repeat;*/
    /*background-position:right bottom;*/
    /*background-color: rgba(34,33,31,1);*/

    /*}*/

    .flex-control-nav{
        display: none;
        bottom:20.7%;
    }

    .flex-control-nav a{
        background: url(/static/channelApp/images/mobile/201605/choise.png) center  0 no-repeat;
        background-size: 10px 10px;
    }

    .flex-control-nav .flex-active {
        background: url(/static/channelApp/images/mobile/201605/choise2.png) center  0 no-repeat;
        background-size: 10px 10px;
    }



    .flex-control-nav li{
        margin:0px;
        width: 20px;
    }

</style>
</head>
<div class="flexslider" >
    <%--<div class="slides-box">--%>
        <ul class="slides">
            <li style="width: 100%; float: left; position: relative; opacity: 0.455553; display: block; z-index: 1; top:0; left: 0;" class="">
                <div class="slides-cont slides-01">
                    <div class="ff-rg f1-rg">
                        <img class = "ff-rg-img-1" src="/static/channelApp/images/mobile/201605/logo.png" alt="" draggable="false">
                        <br/>
                        <img class = "ff-rg-img-2" src="/static/channelApp/images/mobile/201605/pic1.png" alt="" draggable="false">
                        <div class="ff-download">
                            <div class="ff-download-box">
                                <a style="float: left;" href="javascript:void(0);" data-href="${channelApp.iosUrl}" onclick="downloadStatistics(${channelApp.channelId},'IOS',this)" title="乐享动iOS客户端点击下载">
                                    <img src="/static/channelApp/images/mobile/201605/ios.png" alt="" draggable="false">
                                </a>
                                <a style="float: right;" class="android" href="javascript:void(0);" data-href="${channelApp.androidApkUrl}" onclick="downloadStatistics(${channelApp.channelId},'ANDROID',this)" title="乐享动Android客户端点击下载">
                                    <img src="/static/channelApp/images/mobile/201605/android.png" alt="" draggable="false" >
                                </a>
                            </div>
                        </div>
                    </div>
                    <br style="clear: both;">
                </div>
            </li>

            <li style="width: 100%; float: left; position: absolute; opacity: 0.544447; display: block; z-index: 2; top:0; left: 0;" class="flex-active-slide">
                <div class="slides-cont slides-02">
                    <div class="ff-rg f1-rg">
                        <img class = "ff-rg-img-1" src="/static/channelApp/images/mobile/201605/logo.png" alt="" draggable="false" >
                        <br/>
                        <img class = "ff-rg-img-2" src="/static/channelApp/images/mobile/201605/pic2.png" alt="" draggable="false" >
                        <div class="ff-download">
                            <div class="ff-download-box">
                                <a style="float: left;" href="javascript:void(0);" data-href="${channelApp.iosUrl}" onclick="downloadStatistics(${channelApp.channelId},'IOS',this)" title="乐享动iOS客户端点击下载">
                                    <img src="/static/channelApp/images/mobile/201605/ios.png" alt="" draggable="false">
                                </a>
                                <a style="float: right;" href="javascript:void(0);" class="android" data-href="${channelApp.androidApkUrl}" onclick="downloadStatistics(${channelApp.channelId},'ANDROID',this)" title="乐享动Android客户端点击下载">
                                    <img src="/static/channelApp/images/mobile/201605/android.png" alt="" draggable="false" >
                                </a>
                            </div>
                        </div>
                    </div>
                    <br style="clear: both;">
                </div>
            </li>
        </ul>
    <%--</div>--%>
</div>

<script type="text/javascript" src="/static/channelApp/js/mobile/201506/jquery.min.js"></script>
<script type="text/javascript" src="/static/channelApp/js/mobile/201506/jquery.flexslider-min.js"></script>
<script type="text/javascript">

    window.onResize = function(){
        myResize();
    };

    $('.flexslider').flexslider({
        slideshow: true,
        directionNav: true,
        pauseOnHover: true,
        slideshowSpeed:3000,
        animationLoop:true,
        start:function(){
            $(".flex-control-nav").css({
                top:$(".ff-download").offset().top-20+"px",
                display:"block"
            });
        }
    });


    function myResize(){
        if(524 > $("body").height()){
            $(".ff-download").each(function(){
                $(this).css({
                    position: "absolute",
                    bottom: "5px"
                });
            });
        }
    }

    function downloadStatistics(channelId,type,self){
        $.ajax({
            url:"/channel-app/download-statistics.json",
            type:"post",
            dataType:"json",
            data:{
                channelId:channelId,
                type:type
            },
            success:function(){
                window.location.href=$(self).data("href");
            }
        });
    }

    $(function(){
        myResize();
        $.ajax({
            url:"/channel-app/modify-open-status.json",
            type:"post",
            data:{id:${channelAppStatistics.id}},
            dataType:"json",
            success:function(){

            }
        });

        var ua = navigator.userAgent.toLowerCase();
        if (/iphone|ipad|ipod/.test(ua)) {
            // 对浏览器的UserAgent进行正则匹配，不含有微信独有标识的则为其他浏览器
            var userAgent = navigator.userAgent; //取得浏览器的userAgent字符串
            if (userAgent.indexOf("Safari") > -1) {
                $(".ff-download").each(function(){
                    $(this).css({
                        position: "absolute",
                        bottom: "20px"
                    });
                });
            } else {
                alert("请在 Safari 浏览器中打开.");
//                $(".nohuman").append("<p style='color: red'>请在 Safari 浏览器中打开.</p>");
            }
        } else if (ua.match(/MicroMessenger/i) == "micromessenger") {
            alert("请在自带浏览器中打开.");
//            $(".nohuman").append("<p style='color: red'>请在自带浏览器中打开.</p>");
        }

    });

</script>
</body>
</html>
