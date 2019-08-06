<%--
  手表数据
  Created by IntelliJ IDEA.
  User: edward
  Date: 2018/3/2
  Time: 17:10
  To change this template use File | Settings | File Templates.
--%>

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

<c:choose>
    <%-- 户外跑 ### --%>
    <c:when test="${share.watch.sportType eq 0}">
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
                        <div class="km" id="distance"></div>
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
                    <div class="km" id="distance"></div>
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

            <div class="overview-wrapper data-click1">
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
                    <img src="/static/user-run-share/imgs/red_left.png" onclick="return false;" alt="">
                </div>
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
                                    <div id="elevation" class="text"></div>
                                    <div class="desc">累积爬升:米</div>
                                </div>
                            </div>
                        </c:when>
                        <c:otherwise>
                            <div class="info-item info-item-2" id="data-item">
                                <div class="icon">
                                    <i class="icon iconfont icon-bufu"></i>
                                </div>
                                <div class="content">
                                    <div id="elevation" class="text"></div>
                                    <div class="desc">累积爬升:米</div>
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
                            <i class="icon icomoon icon-heart"></i>
                        </div>
                        <div class="content">
                            <div id="avgSportHR" class="text"></div>
                            <div class="desc">心率</div>
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
                        <div style="height: 200px;line-height: 200px;display: none;" class="text-center outText buPinText">
                            记步文件丢失
                        </div>
                            <%--<div id="frequencyTableUnit" class="text-right tableTd text-bu text-mins">Mins</div>--%>
                    </div>
                </div>

                <div class="title-wrapper title-position">
                    <div class="red-right">
                        <img src="/static/user-run-share/imgs/red_right.png" onclick="return false;" alt="">
                    </div>
                    <div class="title title-su">
                        <div class="line"></div>
                        <div class="text">速度曲线</div>
                        <div class="line"></div>
                    </div>
                </div>
                <div class="speed-table">
                    <div class="myCanvas_div">
                        <div class="vertical"><span class="text-su" id = "su-span">米/分钟</span><span class="avg-speed text-su step-speed"></span></div>
                        <div style="text-align: center;margin-bottom: 1rem">
                            <div id="mainSuDu" style="width:100%;height:8rem" class="mainHeight"></div>
                                <%--<canvas id="myChart" class="myCanvas"></canvas>--%>
                        </div>

                        <div style="height: 200px;line-height: 200px;display: none;" class="text-center outText suDuText">
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
                        <div style="height: 200px;line-height: 200px;display: none;" class="text-center outText heartText">
                            记步文件丢失
                        </div>
                            <%--<div id="heartRateTableUnit" class="text-right tableTd">Mins</div>--%>
                    </div>
                </div>
            </div>

        </div>
    </c:when>


    <%-- 室内跑 ### --%>
    <c:when test="${share.watch.sportType eq 1}">
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
                        <div class="km" id="distance"></div>
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
                    <div class="km" id="distance"></div>
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

            <div class="overview-wrapper data-click1">
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
                    <img src="/static/user-run-share/imgs/red_left.png" onclick="return false;" alt="">
                </div>
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
                                    <div id="elevation" class="text"></div>
                                    <div class="desc">累积爬升:米</div>
                                </div>
                            </div>
                        </c:when>
                        <c:otherwise>
                            <div class="info-item info-item-2" id="data-item">
                                <div class="icon">
                                    <i class="icon iconfont icon-bufu"></i>
                                </div>
                                <div class="content">
                                    <div id="elevation" class="text"></div>
                                    <div class="desc">累积爬升:米</div>
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
                            <i class="icon icomoon icon-heart"></i>
                        </div>
                        <div class="content">
                            <div id="avgSportHR" class="text"></div>
                            <div class="desc">心率</div>
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
                        <div style="height: 200px;line-height: 200px;display: none;" class="text-center outText bupinText">
                            记步文件丢失
                        </div>
                            <%--<div id="frequencyTableUnit" class="text-right tableTd text-bu text-mins">Mins</div>--%>
                    </div>
                </div>

                <div class="title-wrapper title-position">
                    <div class="red-right">
                        <img src="/static/user-run-share/imgs/red_right.png" onclick="return false;" alt="">
                    </div>
                    <div class="title title-su">
                        <div class="line"></div>
                        <div class="text">速度曲线</div>
                        <div class="line"></div>
                    </div>
                </div>
                <div class="speed-table">
                    <div class="myCanvas_div">
                        <div class="vertical"><span class="text-su" id = "su-span">米/分钟</span><span class="avg-speed text-su step-speed"></span></div>
                        <div style="text-align: center;margin-bottom: 1rem">
                            <div id="mainSuDu" style="width:100%;height:8rem" class="mainHeight"></div>
                                <%--<canvas id="myChart" class="myCanvas"></canvas>--%>
                        </div>

                        <div style="height: 200px;line-height: 200px;display: none;" class="text-center outText suDuText">
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
                        <div style="height: 200px;line-height: 200px;display: none;" class="text-center outText heartText">
                            记步文件丢失
                        </div>
                            <%--<div id="heartRateTableUnit" class="text-right tableTd">Mins</div>--%>
                    </div>
                </div>
            </div>

        </div>
    </c:when>

    <%-- 徒步 ### --%>
    <c:when test="${share.watch.sportType eq 2}">
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
                        <div class="km" id="distance"></div>
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
                    <div class="km" id="distance"></div>
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

            <div class="overview-wrapper data-click1">
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
                    <img src="/static/user-run-share/imgs/red_left.png" onclick="return false;" alt="">
                </div>
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
                                    <div id="elevation" class="text"></div>
                                    <div class="desc">累积爬升:米</div>
                                </div>
                            </div>
                        </c:when>
                        <c:otherwise>
                            <div class="info-item info-item-2" id="data-item">
                                <div class="icon">
                                    <i class="icon iconfont icon-bufu"></i>
                                </div>
                                <div class="content">
                                    <div id="elevation" class="text"></div>
                                    <div class="desc">累积爬升:米</div>
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
                            <i class="icon icomoon icon-heart"></i>
                        </div>
                        <div class="content">
                            <div id="avgSportHR" class="text"></div>
                            <div class="desc">心率</div>
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
                        <div style="height: 200px;line-height: 200px;display: none;" class="text-center outText buPinText">
                            记步文件丢失
                        </div>
                            <%--<div id="frequencyTableUnit" class="text-right tableTd text-bu text-mins">Mins</div>--%>
                    </div>
                </div>

                <div class="title-wrapper title-position">
                    <div class="red-right">
                        <img src="/static/user-run-share/imgs/red_right.png" onclick="return false;" alt="">
                    </div>
                    <div class="title title-su">
                        <div class="line"></div>
                        <div class="text">速度曲线</div>
                        <div class="line"></div>
                    </div>
                </div>
                <div class="speed-table">
                    <div class="myCanvas_div">
                        <div class="vertical"><span class="text-su" id = "su-span">米/分钟</span><span class="avg-speed text-su step-speed"></span></div>
                        <div style="text-align: center;margin-bottom: 1rem">
                            <div id="mainSuDu" style="width:100%;height:8rem" class="mainHeight"></div>
                                <%--<canvas id="myChart" class="myCanvas"></canvas>--%>
                        </div>

                        <div style="height: 200px;line-height: 200px;display: none;" class="text-center outText suDuText">
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
                        <div style="height: 200px;line-height: 200px;display: none;" class="text-center outText heartText">
                            记步文件丢失
                        </div>
                            <%--<div id="heartRateTableUnit" class="text-right tableTd">Mins</div>--%>
                    </div>
                </div>
            </div>

        </div>
    </c:when>

    <%-- 登山 ### --%>
    <c:when test="${share.watch.sportType eq 8}">
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
                        <div class="km" id="distance"></div>
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
                    <div class="km" id="distance"></div>
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

            <div class="overview-wrapper data-click1">
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
                    <img src="/static/user-run-share/imgs/red_left.png" onclick="return false;" alt="">
                </div>
                <div class="info-wrapper info-wrapper-1">
                    <div class="info-item info-item-1">
                        <div class="icon">
                            <i class="icon iconfont icon-haiba"></i>
                        </div>
                        <div class="content">
                            <div id="peakAltitude" class="text"></div>
                            <div class="desc">最高海拔：米</div>
                        </div>
                    </div>
                    <div class="interval"></div>
                    <div class="info-item info-item-2">
                        <div class="icon">
                            <i class="icon icomoon icon-kaluli" style="font-size:1.35rem;"></i>
                        </div>
                        <div class="content">
                            <div id="calorie" class="text"></div>
                            <div class="desc">消耗：大卡</div>
                        </div>
                    </div>
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
                            <img src="/static/user-run-share/imgs/upall.png" alt="" class="icon-img">
                        </div>
                        <div class="content">
                            <div id="totalUp" class="text"></div>
                            <div class="desc">累计爬升：米</div>
                        </div>
                    </div>
                    <div class="interval"></div>
                    <div class="info-item info-item-6">
                        <div class="icon">
                            <img src="/static/user-run-share/imgs/downall.png" alt="" class="icon-img">
                        </div>
                        <div class="content">
                            <div id="totalDown" class="text"></div>
                            <div class="desc">累计下降：米</div>
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
                            <i class="icon icomoon icon-heart"></i>
                        </div>
                        <div class="content">
                            <div id="avgSportHR" class="text"></div>
                            <div class="desc">心率</div>
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
                    <%-- 登山速度 --%>
                <div class="title-wrapper title-position">
                    <div class="red-right">
                        <img src="/static/user-run-share/imgs/red_right.png" onclick="return false;" alt="">
                    </div>
                    <div class="title title-su">
                        <div class="line"></div>
                        <div class="text">速度曲线</div>
                        <div class="line"></div>
                    </div>
                </div>
                <div class="speed-table">
                    <div class="myCanvas_div">
                        <div class="vertical"><span class="text-su" id ="su-span">米/分钟</span><span class="avg-speed text-su step-speed"></span></div>
                        <div style="text-align: center;margin-bottom: 1rem">
                            <div id="mainSuDu" style="width:100%;height:8rem" class="mainHeight"></div>
                                <%--<canvas id="myChart" class="myCanvas"></canvas>--%>
                        </div>

                        <div style="height: 200px;line-height: 200px;display: none;" class="text-center outText suDuText">
                            记步文件丢失
                        </div>
                            <%--<div id="speedTableUnit" class="text-right tableTd text-su text-mins">Mins</div>--%>
                    </div>
                </div>

                    <%-- 登山心率 --%>
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
                        <div style="height: 200px;line-height: 200px;display: none;" class="text-center outText heartText">
                            记步文件丢失
                        </div>
                            <%--<div id="heartRateTableUnit" class="text-right tableTd">Mins</div>--%>
                    </div>
                </div>

                    <%-- 登山海拔 --%>
                <div class="title-wrapper">
                    <div class="title title-altitude">
                        <div class="line"></div>
                        <div class="text">海拔曲线</div>
                        <div class="line"></div>
                    </div>
                </div>
                <div class="altitude-table">
                    <div class="myCanvas_div">
                        <div class="vertical"><span class="text-altitude">高度（米）/分钟</span><span class="avg-altitude"></span></div>
                        <div style="text-align: center">
                            <div id="mainAltitude" style="width:100%;height:8rem"></div>
                                <%--<canvas id="heartCanvas" class="myCanvas"></canvas>--%>
                        </div>
                        <div style="height: 200px;line-height: 200px;display: none;" class="text-center outText altitudeText">
                            记步文件丢失
                        </div>
                            <%--<div id="heartRateTableUnit" class="text-right tableTd">Mins</div>--%>
                    </div>
                </div>
            </div>

        </div>
    </c:when>

    <%-- （公路）骑行 ### --%>
    <c:when test="${share.watch.sportType eq 4}">
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
                            <%--<span class="slow">最慢时速</span>--%>
                            <%--</div>--%>
                            <%--</div>--%>
                        <div class="km" id="distance"></div>
                        <div onclick="rabbit()" class="rabbitAll">
                            <div class="rabbit">
                                <img src="/static/user-run-share/imgs/deer.png" alt="" class="map-especially-img">
                            </div>
                            <div class="rabbit-text">
                                <span class="fast">最快时速</span>
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
                        <%--<span class="slow">最慢时速</span>--%>
                        <%--</div>--%>
                        <%--</div>--%>
                    <div class="km" id="distance"></div>
                    <div onclick="rabbit()" class="rabbitAll">
                        <div class="rabbit">
                            <img src="/static/user-run-share/imgs/rabbit.png" alt="" >
                        </div>
                        <div class="rabbit-text">
                            <span class="fast">最快时速</span>
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

            <div class="overview-wrapper data-click1">
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
                    <img src="/static/user-run-share/imgs/red_left.png" onclick="return false;" alt="">
                </div>
                <div class="info-wrapper info-wrapper-1">
                    <div class="info-item info-item-1">
                        <div class="icon">
                            <i class="icon iconfont icon-shisu"></i>
                        </div>
                        <div class="content">
                            <div id="speed" class="text"></div>
                            <div class="desc">平均时速：公里/小时</div>
                        </div>
                    </div>
                    <div class="interval"></div>
                    <div class="info-item info-item-2">
                        <div class="icon">
                            <img src="/static/user-run-share/imgs/bestshisu.png" alt="" style="width: 1.3rem;margin-top: 0.2rem;">
                        </div>
                        <div class="content">
                            <div id="maxPace" class="text"></div>
                            <div class="desc">最高时速：公里/小时</div>
                        </div>
                    </div>
                </div>
                <div class="info-wrapper">
                    <div class="info-item info-item-3">
                        <div class="icon">
                            <img src="/static/user-run-share/imgs/upall.png" alt="" class="icon-img">
                        </div>
                        <div class="content">
                            <div id="totalUp" class="text"></div>
                            <div class="desc">累计爬升：米</div>
                        </div>
                    </div>
                    <div class="interval"></div>
                    <div class="info-item info-item-4">
                        <div class="icon">
                            <img src="/static/user-run-share/imgs/downall.png" alt="" class="icon-img">
                        </div>
                        <div class="content">
                            <div id="totalDown" class="text"></div>
                            <div class="desc">累计下降：米</div>
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
                            <img src="/static/user-run-share/imgs/ranzhi.png" alt="" style="width: 1.2rem;margin-left: 0.2rem;">
                        </div>
                        <div class="content">
                            <div id="fatBurst" class="text"></div>
                            <div class="desc">燃脂：克</div>
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
                            <i class="icon icomoon icon-heart"></i>
                        </div>
                        <div class="content">
                            <div id="avgSportHR" class="text"></div>
                            <div class="desc">心率</div>
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
                <%--骑行图表（速度，心率，海拔）--%>
            <div class="diagram-wrapper">

                    <%-- 骑行速度 --%>
                <div class="title-wrapper title-position">
                    <div class="red-right">
                        <img src="/static/user-run-share/imgs/red_right.png" onclick="return false;" alt="">
                    </div>
                    <div class="title title-su">
                        <div class="line"></div>
                        <div class="text">速度曲线</div>
                        <div class="line"></div>
                    </div>
                </div>
                <div class="speed-table">
                    <div class="myCanvas_div">
                        <div class="vertical"><span class="text-su" id = "su-span">米/分钟</span><span class="avg-speed text-su step-speed"></span></div>
                        <div style="text-align: center;margin-bottom: 1rem">
                            <div id="mainSuDu" style="width:100%;height:8rem" class="mainHeight"></div>
                                <%--<canvas id="myChart" class="myCanvas"></canvas>--%>
                        </div>

                        <div style="height: 200px;line-height: 200px;display: none;" class="text-center outText suDuText">
                            记步文件丢失
                        </div>
                            <%--<div id="speedTableUnit" class="text-right tableTd text-su text-mins">Mins</div>--%>
                    </div>
                </div>

                    <%-- 骑行心率 --%>
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
                        <div style="height: 200px;line-height: 200px;display: none;" class="text-center outText heartText">
                            记步文件丢失
                        </div>
                            <%--<div id="heartRateTableUnit" class="text-right tableTd">Mins</div>--%>
                    </div>
                </div>

                    <%-- 骑行海拔 --%>
                <div class="title-wrapper heart">
                    <div class="title title-altitude">
                        <div class="line"></div>
                        <div class="text">海拔曲线</div>
                        <div class="line"></div>
                    </div>
                </div>

                <div class="altitude-table">
                    <div class="myCanvas_div">
                        <div class="vertical"><span class="text-altitude">高度（米）/分钟</span><span class="avg-altitude"></span></div>
                        <div style="text-align: center">
                            <div id="mainAltitude" style="width:100%;height:8rem"></div>
                                <%--<canvas id="heartCanvas" class="myCanvas"></canvas>--%>
                        </div>
                        <div style="height: 200px;line-height: 200px;display: none;" class="text-center outText altitudeText">
                            记步文件丢失
                        </div>
                            <%--<div id="heartRateTableUnit" class="text-right tableTd">Mins</div>--%>
                    </div>
                </div>
            </div>
        </div>
    </c:when>
    <%-- 山地车骑行 ### --%>
    <c:when test="${share.watch.sportType eq 15}">
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
                            <%--<span class="slow">最慢时速</span>--%>
                            <%--</div>--%>
                            <%--</div>--%>
                        <div class="km" id="distance"></div>
                        <div onclick="rabbit()" class="rabbitAll">
                            <div class="rabbit">
                                <img src="/static/user-run-share/imgs/deer.png" alt="" class="map-especially-img">
                            </div>
                            <div class="rabbit-text">
                                <span class="fast">最快时速</span>
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
                        <%--<span class="slow">最慢时速</span>--%>
                        <%--</div>--%>
                        <%--</div>--%>
                    <div class="km" id="distance"></div>
                    <div onclick="rabbit()" class="rabbitAll">
                        <div class="rabbit">
                            <img src="/static/user-run-share/imgs/rabbit.png" alt="" >
                        </div>
                        <div class="rabbit-text">
                            <span class="fast">最快时速</span>
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

            <div class="overview-wrapper data-click1">

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
                    <img src="/static/user-run-share/imgs/red_left.png" onclick="return false;" alt="">
                </div>
                <div class="info-wrapper info-wrapper-1">
                    <div class="info-item info-item-1">
                        <div class="icon">
                            <i class="icon iconfont icon-shisu"></i>
                        </div>
                        <div class="content">
                            <div id="speed" class="text"></div>
                            <div class="desc">平均时速：公里/小时</div>
                        </div>
                    </div>
                    <div class="interval"></div>
                    <div class="info-item info-item-2">
                        <div class="icon">
                            <img src="/static/user-run-share/imgs/bestshisu.png" alt="" style="width: 1.3rem;margin-top: 0.2rem;">
                        </div>
                        <div class="content">
                            <div id="maxPace" class="text"></div>
                            <div class="desc">最高时速：公里/小时</div>
                        </div>
                    </div>
                </div>
                <div class="info-wrapper">
                    <div class="info-item info-item-3">
                        <div class="icon">
                            <img src="/static/user-run-share/imgs/upall.png" alt="" class="icon-img">
                        </div>
                        <div class="content">
                            <div id="totalUp" class="text"></div>
                            <div class="desc">累计爬升：米</div>
                        </div>
                    </div>
                    <div class="interval"></div>
                    <div class="info-item info-item-4">
                        <div class="icon">
                            <img src="/static/user-run-share/imgs/downall.png" alt="" class="icon-img">
                        </div>
                        <div class="content">
                            <div id="totalDown" class="text"></div>
                            <div class="desc">累计下降：米</div>
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
                            <img src="/static/user-run-share/imgs/ranzhi.png" alt="" style="width: 1.2rem;margin-left: 0.2rem;">
                        </div>
                        <div class="content">
                            <div id="fatBurst" class="text"></div>
                            <div class="desc">燃脂：克</div>
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
                            <i class="icon icomoon icon-heart"></i>
                        </div>
                        <div class="content">
                            <div id="avgSportHR" class="text"></div>
                            <div class="desc">心率</div>
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

                <div class="title-wrapper title-position">
                    <div class="red-right">
                        <img src="/static/user-run-share/imgs/red_right.png" onclick="return false;" alt="">
                    </div>
                    <div class="title title-su">
                        <div class="line"></div>
                        <div class="text">速度曲线</div>
                        <div class="line"></div>
                    </div>
                </div>
                <div class="speed-table">
                    <div class="myCanvas_div">
                        <div class="vertical"><span class="text-su" id = "su-span">米/分钟</span><span class="avg-speed text-su step-speed"></span></div>
                        <div style="text-align: center;margin-bottom: 1rem">
                            <div id="mainSuDu" style="width:100%;height:8rem" class="mainHeight"></div>
                                <%--<canvas id="myChart" class="myCanvas"></canvas>--%>
                        </div>

                        <div style="height: 200px;line-height: 200px;display: none;" class="text-center outText suDuText">
                            记步文件丢失
                        </div>
                            <%--<div id="speedTableUnit" class="text-right tableTd text-su text-mins">Mins</div>--%>
                    </div>
                </div>

                    <%-- 山地车骑行心率 --%>
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
                        <div style="height: 200px;line-height: 200px;display: none;" class="text-center outText heartText">
                            记步文件丢失
                        </div>
                            <%--<div id="heartRateTableUnit" class="text-right tableTd">Mins</div>--%>
                    </div>
                </div>

                    <%-- 山地车骑行海拔 --%>
                <div class="title-wrapper heart">
                    <div class="title title-altitude">
                        <div class="line"></div>
                        <div class="text">海拔曲线</div>
                        <div class="line"></div>
                    </div>
                </div>
                <div class="altitude-table">
                    <div class="myCanvas_div">
                        <div class="vertical"><span class="text-altitude">高度（米）/分钟</span><span class="avg-altitude"></span></div>
                        <div style="text-align: center">
                            <div id="mainAltitude" style="width:100%;height:8rem"></div>
                                <%--<canvas id="heartCanvas" class="myCanvas"></canvas>--%>
                        </div>
                        <div style="height: 200px;line-height: 200px;display: none;" class="text-center outText altitudeText">
                            记步文件丢失
                        </div>
                            <%--<div id="heartRateTableUnit" class="text-right tableTd">Mins</div>--%>
                    </div>
                </div>
            </div>
        </div>
    </c:when>
</c:choose>

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
    // 定义数据来源，全局share
    var share = {
        /**
         *  跑步模式 ： 室外
         */
        MODAL_TYPE_INDOOR: 1,
        /**
         * 跑步模式 ： 室内.
         */
        MODAL_TYPE_OUTDOOR: 2,
        //手表
        RUN_TYPE_WATCH:3,
        /**
         * 一些属性
         * */
        attributes: {
            //运动类型: 手表
            type: ${share.type},
            //用户分享记录类型
            model: ${share.watch.sportType},
            //获取轨迹文件
            detail: ${share.detail},
            startTime: ${share.startTime},
            endTime: ${share.endTime},
            runTime: ${share.watch.sportTime},
            distance: ${share.watch.totalDistance},
            distance2: ${share.watch.totalDistance / 1000},
            //获取记步文件
            stepDetail: ${share.stepDetail},
            step: ${share.watch.totalSteps},
            userImg: "${share.user.avatar}",
            userName: "${share.user.name}",
            calorie:"${share.watch.totalCalorie}",
            peakAltitude:"${share.watch.peakAltitude}",
            totalDown:"${share.watch.totalDown}",
            totalUp:"${share.watch.totalUp}",
            maxPace:"${share.watch.maxPace}", // 一公里用了多少秒
            fatBurst:"${share.watch.fatBurst}",
            avgSportHR: "${share.watch.avgSportHR}",
            maxSportHR: "${share.watch.maxSportHR}",
            watchZipFile: "${share.watchZipFile}"
        }
    }
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

<%--<script>--%>
<%--//    /* TODO 别删除这玩意 */--%>
<%--if (window.share != undefined--%>
<%--&& window.share.attributes != undefined--%>
<%--&& window.share.attributes.distance2 != undefined) {--%>
<%--window.share.attributes.distance2 = window.share.attributes.distance2.toFixed(2);--%>
<%--}--%>
<%--</script>--%>
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
<script src="/static/chart/chart.js"></script>

<script id="userRunShateNewEndScript">
    var fonts = $('.step-speed').css("font-size");
    var font = parseInt(fonts)*3/4; // 横纵坐标的字体大小值
    $.get('http://ifitmix.com/api/zipFileParse?zip_url=' + share.attributes.watchZipFile,function(data) {
        var array = window.share.attributes.detail["array"].length?window.share.attributes.detail["array"]:data.json?data.json.array:[];
        drawMap(array);
        var sportType = share.attributes.model;
        var xLabel;
        if(data.speedChart) {
            xLabel = getXLabel(data.speedChart.Time,data.speedChart.array.length);// 所有曲线都共用同一个横坐标（运动时间一样）
            drawMainSuDu(xLabel,data.speedChart);
            data.hrChart && drawMainHeart(xLabel,data.hrChart);
        }else {
            // 记录文件丢失(基本上速度文件丢失（或者根本没动），其他文件也一样)
            $('.suDuText').show();
//            return;
        }
        if(data.hrChart) {
            console.log('阿三打扫打扫打扫打扫')
            xLabel = getXLabel(data.hrChart.Time,data.hrChart.array.length);// 所有曲线都共用同一个横坐标（运动时间一样）
            drawMainHeart(xLabel,data.hrChart);
        }else {
            console.log('asdasxzcxzcxzxczxzcxzcxzcxzc');
            // 记录文件丢失(基本上速度文件丢失（或者根本没动），其他文件也一样)
            $('.heartText').show();
//            return;
        }
        switch(sportType) {
            // 室外
            case 0:
                data.bpmChart && drawMainBuPin(xLabel,data.bpmChart);
                break;
            // 室内
            case 1:
                data.bpmChart && drawMainBuPin(xLabel,data.bpmChart);
                break;
            // 徒步
            case 2:
                data.bpmChart && drawMainBuPin(xLabel,data.bpmChart);
                break;
            // 公路骑行
            case 4:
                data.altitudeChart && drawMainAltitude(xLabel,data.altitudeChart);
                break;
            // 登山
            case 8:
                data.altitudeChart && drawMainAltitude(xLabel,data.altitudeChart);
                break;
            // 山地车骑行
            case 15:
                data.altitudeChart && drawMainAltitude(xLabel,data.altitudeChart);
                break;
        }
    });

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
//        if($('.rabbit-text').css("display") == "block" && $('.turtle-text').css("display") == "block"){
//            $(".map-bottom .turtle-text").css("padding-top","0.05rem");
//            $(".map-bottom .rabbit-text").css("padding-top","0.05rem");
//        }
//        if($('.rabbit-text').css("display") == "block" && $('.turtle-text').css("display") == "none"){
//            $(".map-bottom .rabbit-text").css("padding-top","0.5rem");
//            $(".map-bottom .turtle-text").css("padding-top","0.5rem");
//        }
//        if($('.turtle-text').css("display") == "block" && $('.rabbit-text').css("display") == "none"){
//            $(".map-bottom .rabbit-text").css("padding-top","0.5rem");
//            $(".map-bottom .turtle-text").css("padding-top","0.5rem");
//        }
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

//        if($('.rabbit-text').css("display") == "block" && $('.turtle-text').css("display") == "block"){
//            $(".map-bottom .turtle-text").css("padding-top","0.05rem");
//            $(".map-bottom .rabbit-text").css("padding-top","0.05rem");
//        }
//        if($('.rabbit-text').css("display") == "block" && $('.turtle-text').css("display") == "none"){
//            $(".map-bottom .turtle-text").css("padding-top","0.5rem");
//            $(".map-bottom .rabbit-text").css("padding-top","0.5rem");
//        }
//        if($('.turtle-text').css("display") == "block" && $('.rabbit-text').css("display") == "none"){
//            $(".map-bottom .turtle-text").css("padding-top","0.5rem");
//            $(".map-bottom .rabbit-text").css("padding-top","0.5rem");
//        }
        flag1 = !flag1;
    }
    $(".turtle-text .slow").css("margin","0.02rem");
    $(".rabbit-text .fast").css("margin","0.02rem");



    function positionClick(){
        map.centerAndZoom(new BMap.Point(center[0], center[1]), viewPort.zoom);
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
    })

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

    // 当没有地图的时候
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
    setTimeout(function() {
        console.log(getUserName(window.share.attributes.userName));
        $("#userName").text(getUserName(window.share.attributes.userName));
    },1000)
    // 设置头像
    if(window.share.attributes.userImg == "") {
        $("#avatar").attr("src", "/static/user-run-share/imgs/895612581648387461.png");
    }
</script>
</body>

