<%--
  Created by IntelliJ IDEA.
  User: edward
  Date: 2016/5/26
  Time: 16:10
  To change this template use File | Settings | File Templates.
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
    <meta name="viewport" content="initial-scale=1.0, user-scalable=no"/>

    <title>${activityName}</title>

    <link rel="stylesheet" href="/static/bootstrap/dist/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="http://cache.amap.com/lbs/static/main1119.css"/>
    <link rel="stylesheet" href="/static/uikit/css/uikit.min.css">
    <style type="text/css">
        * {/*适用ios终端的禁止手指长按*/
            -webkit-touch-callout: none;
            -webkit-user-select: none;
            -moz-user-select: none;
            -ms-user-select: none;
            -o-user-select: none;
            user-select: none;
        }

        .extremeSpeed {
            height: auto;
            position: relative;
            padding: 25px 30px 10px 30px;
        }

        .extremeSpeed .speed,
        .extremeSpeed .speed [class *="col-"] {
            min-height: 4px;
            font-size: 12px;
            color: #FFFFFF;
            line-height: 23px;
        }


        .userText [class *="col-"] {
            color: #FFFFFF;
        }

        .userImg {
            width: 51px;
            height: 51px;
            max-height: 51px;
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


        body {
            background-color: #1C1B21;
        }

        body {
            /*margin-bottom: -90px;*/
        }

        .logo {
            width: 25px;
            position: absolute;
            z-index: 1000;
            top: 10px;
            right: 15px;
        }

        .textColor_fb8815 {
            color:#FB8815;
        }
        .textColor_e6e5ea {
            color: #E6E5EA;
        }

        .textColor_7d7b7b{
            color:#7D7B7B;
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

        .i-activity {
            padding: 0px;
        }

        .list-right {
            font-size: 29px;
            padding: 0px;
            margin: 0px;
            text-align: right;
        }

        .img-div{
            padding: 0px;
            margin: 0px;
        }

        .img-list {
            width: 36px;
        }

        .div-td-left {
            width: 55px;
        }
        .div-td-center {
            padding-left: 3px;
        }
        .div-td-right {
        }
        .row {
            padding-top: 5px;
            width: 99%;
            padding: 0px;
            margin: 0px;
        }
        .row_div {
            padding-right: 0px;
            padding: 0px;
            margin: 0px;
        }
        .row_span {
            font-size: 30px;
            display: inline;
            width: 43%;
            float: left;
            text-align: center;
            padding: 0px;
            margin: 0px;
        }
        .col-width-50{
            width: 49%;
        }
    </style>

</head>

<body>
<div style="/*overflow-y:hidden;*/height: 100%;width: 100%">
    <div class="container extremeSpeed">
        <table style="width: 100%;color: #ffffff;font-size: 12px;">
            <tr>
                <td class="div-td-left" rowspan="4">
                    <c:choose>
                        <c:when test="${empty speedwayRunInfo.avatar}">
                            <img class="img-responsive img-rounded userImg" src="/imgs/default.png"/>
                        </c:when>
                        <c:otherwise><img class="img-responsive img-rounded userImg" src="${speedwayRunInfo.avatar}"/></c:otherwise>
                    </c:choose>
                </td>
            </tr>
            <tr>
                <td class="div-td-center">${speedwayRunInfo.name}</td>
                <td class="div-td-right">
                    今日排名：
                        <span>
                            <c:choose>
                                <c:when test="${speedwayRunInfo.sort == -1}">--</c:when>
                                <c:otherwise>${speedwayRunInfo.sort}</c:otherwise>
                            </c:choose>
                    </span>
                </td>
            </tr>
            <tr>
                <td class="div-td-center">ID:${speedwayRunInfo.id}</td>
                <td class="div-td-right">
                    历史排名：
                        <span>
                            <c:choose>
                                <c:when test="${speedwayRunInfo.sumSort == -1}">--</c:when>
                                <c:otherwise>${speedwayRunInfo.sumSort}</c:otherwise>
                            </c:choose>
                    </span>
                </td>
            </tr>
            <tr>
                <td class="div-td-center">${toDay}</td>
                <td class="div-td-right"></td>
            </tr>
        </table>

    </div>
    <div class="container" style="padding: 20px 0px;height: 160px">
        <%-- 当日积分 --%>
        <div class="row text-center" style="font-size: 11px">
            <div class="col-xs-4 col-sm-3 col-md-3 col-lg-3 textColor_e6e5ea i-activity">
                今日卡路里
            </div>
            <div class="col-xs-4 col-sm-3 col-md-3 col-lg-3 textColor_e6e5ea i-activity">
                今日里程
            </div>
            <div class="col-xs-4 col-sm-3 col-md-3 col-lg-3 textColor_e6e5ea i-activity">
                今日步数
            </div>
        </div>
        <div class="row text-center" style="font-size: 30px">
            <div class="col-xs-4 col-sm-3 col-md-3 col-lg-3 textColor_fb8815 i-activity">
                ${speedwayRunInfo.calorie}
            </div>
            <div class="col-xs-4 col-sm-3 col-md-3 col-lg-3 textColor_fb8815 i-activity">
                <fmt:formatNumber  value="${speedwayRunInfo.distance / 1000}" pattern="#0.0#" />
            </div>
            <div class="col-xs-4 col-sm-3 col-md-3 col-lg-3 textColor_fb8815 i-activity">
                ${speedwayRunInfo.step}
            </div>
        </div>

        <%-- 总积分 --%>
        <div class="row text-center" style="font-size: 11px">
            <div class="col-xs-4 col-sm-3 col-md-3 col-lg-3 textColor_e6e5ea i-activity">
                累计卡路里
            </div>
            <div class="col-xs-4 col-sm-3 col-md-3 col-lg-3 textColor_e6e5ea i-activity">
                累计里程
            </div>
            <div class="col-xs-4 col-sm-3 col-md-3 col-lg-3 textColor_e6e5ea i-activity">
                累计步数
            </div>
        </div>
        <div class="row text-center" style="font-size: 30px">
            <div class="col-xs-4 col-sm-3 col-md-3 col-lg-3 textColor_fb8815 i-activity">
                ${speedwayRunInfo.sumCalorie}
            </div>
            <div class="col-xs-4 col-sm-3 col-md-3 col-lg-3 textColor_fb8815 i-activity">
                <fmt:formatNumber  value="${speedwayRunInfo.sumDistance / 1000}" pattern="#0.0#" />
            </div>
            <div class="col-xs-4 col-sm-3 col-md-3 col-lg-3 textColor_fb8815 i-activity">
                ${speedwayRunInfo.sumStep}
            </div>
        </div>
    </div>

    <div style="padding: 0px 0px 0px 7px;margin: 0px;color: #FFF">
        <ul class="uk-tab" data-uk-tab>
            <li class="uk-active col-width-50" onclick="cutoverRank(1)"><a href="javascript:void(0)">今日英雄榜 (TOP 10)</a></li>
            <li class="col-width-50" onclick="cutoverRank(2)"><a href="javascript:void(0)">昨日英雄榜 (TOP 10)</a></li>
        </ul>
    </div>

    <div id = "rank-left" class="container container-fluid" style="top: -70px;height: 84%;font-size: 12px;background-color: #000000">

        <div style="/*overflow-y:auto;*/height: 100%;">
            <c:forEach var="todayRank" items="${todayRanks}">
                <div class="row" style="padding-top: 5px;width: 99%">
                    <div class="col-xs-7 col-sm-6 col-md-6 col-lg-6 row_div" >
                        <div class="col-xs-6 col-sm-3 col-md-3 col-lg-3 img-div textColor_b9b7b7">
                            <span class="textColor_7d7b7b row_span">${todayRank.sort}</span>
                            <c:choose>
                                <c:when test="${empty todayRank.user.avatar}">
                                    <img style="margin-left: 5px;width: 50%;max-width: 43px;max-height: 43px;" class="img-rounded img-list" src="/imgs/default.png"/>
                                </c:when>
                                <c:otherwise><img style="margin-left: 5px;width: 50%;max-width: 43px;max-height: 43px;" class="img-rounded img-list" src="${todayRank.user.avatar}"/></c:otherwise>
                            </c:choose>
                        </div>
                        <div class="col-xs-6 col-sm-5 col-md-5 col-lg-5" style="padding: 0px; margin: 0px;font-size: 13px;">
                            <ul class="list-unstyled" >
                                <li class="textColor_e6e5ea" style="white-space: nowrap;"><div style="width: 100%;overflow: hidden;text-overflow: ellipsis;white-space: nowrap;">${todayRank.user.name}</div></li>
                                <li class="textColor_7d7b7b">ID:${todayRank.user.id}</li>
                            </ul>
                        </div>
                    </div>
                    <div class="col-xs-5 col-sm-6 col-md-6 col-lg-6 list-right" >
                        <span class="textColor_fb8815">${todayRank.sumCalorie}</span>&nbsp;<span style="font-size: 10px" class="textColor_7d7b7b">卡路里</span>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>

    <div id = "rank-right" class="container container-fluid" style="top: -70px;height: 84%;font-size: 12px;background-color: #000000;" hidden>
        <div style="/*overflow-y:auto;*/height: 100%;">
            <c:forEach var="historyRank" items="${historyRanks}">
                <div class="row" style="padding-top: 5px;width: 99%">
                    <div class="col-xs-7 col-sm-6 col-md-6 col-lg-6 row_div" >
                        <div class="col-xs-6 col-sm-3 col-md-3 col-lg-3 img-div textColor_b9b7b7">
                            <span class="textColor_7d7b7b row_span">${historyRank.sort}</span>
                            <c:choose>
                                <c:when test="${empty historyRank.user.avatar}">
                                    <img style="margin-left: 5px;width: 50%;max-width: 43px;max-height: 43px;" class="img-rounded img-list" src="/imgs/default.png"/>
                                </c:when>
                                <c:otherwise><img style="margin-left: 5px;width: 50%;max-width: 43px;max-height: 43px;" class="img-rounded img-list" src="${historyRank.user.avatar}"/></c:otherwise>
                            </c:choose>
                        </div>
                        <div class="col-xs-6 col-sm-5 col-md-5 col-lg-5" style="padding: 0px; margin: 0px;font-size: 13px;">
                            <ul class="list-unstyled" >
                                <li class="textColor_e6e5ea" style="white-space: nowrap;"><div style="width: 100%;overflow: hidden;text-overflow: ellipsis;white-space: nowrap;">${historyRank.user.name}</div></li>
                                <li class="textColor_7d7b7b">ID:${historyRank.user.id}</li>
                            </ul>
                        </div>
                    </div>
                    <div class="col-xs-5 col-sm-6 col-md-6 col-lg-6 list-right" >
                        <span class="textColor_fb8815">${historyRank.sumCalorie}</span>&nbsp;<span style="font-size: 10px" class="textColor_7d7b7b">卡路里</span>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>
</div>

<script src="/static/jquery-1.11.2.min.js"></script>
<script src="/static/util/delete.js"></script>
<script src="/static/chart/chart.js"></script>
<script src="/static/uikit/js/uikit.min.js"></script>
<script>
    function cutoverRank(position) {
        if (position == 1) {
            $("#rank-left").attr("hidden", null);
            $("#rank-right").attr("hidden", "hidden");
        } else if (position == 2) {
            $("#rank-right").attr("hidden", null);
            $("#rank-left").attr("hidden", "hidden");
        }
    }
</script>
</body>