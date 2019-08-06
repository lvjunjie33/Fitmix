<%--
  Created by IntelliJ IDEA.
  User: wjcaozhi1314
  Date: 2016/1/20
  Time: 16:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ include file="/WEB-INF/pages/common/head-base-path.jsp" %>
<html>
<head>
    <meta charset="utf-8">
    <!-- 禁止缩放 -->
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <meta name="keywords" content="igeekery 生活 运动 服务 一体化，我们为您提供最 智能的硬件 软件 服务平台">
    <meta name="description"
          content="igeekery 是深圳第一蓝筹科技有限公司提供的 软硬件（智能服务，智能硬件）服务平台, 我们提供最优质 Mix 运动音乐和最智能的软硬件，让运动更方便，让喜欢运动的人群得到更好的运动体验。">
    <title>2016彩色跑The Color Run™全国站</title>

    <!--高德地图css-->
    <link rel="stylesheet" href="http://cache.amap.com/lbs/static/main.css"/>
    <link href="/static/event/css/event.css" type="text/css" rel="stylesheet">
    <style>
        .event ul {
            padding-top: 20px;
            padding-left: 10px;
            width: 90%;
        }

        .event ul li {
            background-color: inherit;
            float: left;
        }

        .event ul .leftLi {
            width: 30%;
        }

        .event ul .rightLi {
            width: 70%;
        }

        .introduction p {
            position: relative;
            /*width: 90%;*/
            height: auto;
            padding-top: 10px;
            font-size: 12px;
        }

        .introductionImg {
            width: inherit;
            height: auto;
        }

        .textColorRed {
            color: red;
        }

        .event {
            padding: 0px;
            padding-bottom: 20px;
            background-color: #FFFFFF;
        }

        .commonP2 {
            padding-left: 10px;

        }

        .eventInformation {
            padding-left: 10px;
            width: 97%;
        }

        .textImg {
            width: 97%;
        }
    </style>
    <script src="/static/jquery-1.11.2.min.js"></script>
    <script src="/static/event/js/event.js"></script>
</head>
<body>

<div id="event" class="event">

    <img class="eventTitleImg" src="${titleImg}"/>

    <div class="divDfault">
        <p id="eventName" class="commonP2">2016彩色跑The Color Run™全国站</p>

        <p class="commonType">
        </p>
    </div>

    <ul>
        <li class="leftLi">活动时间:</li>
        <li class="rightLi">2016.06—2016.11（各站具体时间待定）</li>
        <li class="leftLi">活动类型:</li>
        <li class="rightLi">彩色跑</li>
        <li class="leftLi">报名费:</li>
        <li class="rightLi">待定</li>
    </ul>

    <div class="eventInformation" style="margin-top: 140px;">活动简介</div>
    <div class="eventInformation introduction" style="margin-top: 10px;">
        <p>地球上最欢乐5公里跑，开始于美国，传递着健康、快乐、大众参与的跑步理念来到了中国。</p>

        <p>在这里，距离为5公里的The Color Run不计时，步伐快慢不要紧，只要你与成千上万认识的和即将认识的彩色跑者一起，在跑步的过程中充分享受运动和色彩带来的快乐！</p>

        <p>参加The Color Run只有两个规则：</p>

        <p>1、穿白色衣服来到起点； </p>

        <p>2、以最炫的色彩冲过终点！ </p>

        <p>冲过终点线后，欢乐并不会停止 – 等待彩色跑者的是在终点舞台前进行的地球上最High的色彩派对！届时大家会一起把手中的彩色粉向空中抛洒，在空中汇集成绚丽彩虹 –
            每个彩色跑者也都会像调色盘一样色彩缤纷，这快乐又神奇的时刻将深深印在每个人的脑海里。相信我们，这将是你所见过最炫酷的终点派对！</p>

        <p>2016彩色跑中国Tour行程表（暂定）：</p>

        <p>6月 北京</p>

        <p>7月 成都</p>

        <p>7月 沈阳</p>

        <p>7月 青岛</p>

        <p>9月 上海</p>

        <p>10月 武汉</p>

        <p>11月 深圳</p>

        <p>11月 广州</p>

        <p></p>
        <img class="eventTitleImg textImg" src="/static/event/images/201606201611/15.jpg"/>

        <p></p>
        <img class="eventTitleImg textImg" src="/static/event/images/201606201611/18141744mznr.jpg"/>

        <p></p>
        <img class="eventTitleImg textImg" src="/static/event/images/201606201611/maxresdefault.jpg"/>

    </div>
    <%--<div class="eventInformation" style="margin-top: 30px;">活动地点</div>
    <div class="eventInformation" style="margin-top: 10px;">深圳市盐田区梧桐山公园</div>
    <div class="eventInformation map" id="map" style="margin-top: 10px;"></div>
    <div class="eventInformation" style="margin-top: 10px;text-align: center;">参加人数：1800</div>--%>

    <c:choose>
        <c:when test="${apply}">
            <input type="button" id="baoMingBut" class="baoMingBut" value="立即报名" style="margin-top: 10px;"/>
            <input type="hidden" id="activityId" value="${activityId}"/>

            <script>

                /***
                 * 提供给app调用的方法
                 * @param userId
                 * @constructor
                 */
                function GetAppUserId(userId) {
                    var httpHost = "http://" + window.location.host;
                    var WIDsubject, activityId, uid;
                    WIDsubject = $("#eventName").text();//订单名称 赛事名称
                    activityId = $("#activityId").attr("value");//赛事活动id
                    uid = userId;//用户id

                    //跳转支付请求
                    location.href = httpHost + "/activity/register.htm?" +
                            "&activityId=" + activityId +
                            "&uid=" + uid;
                }

                $(function () {

                    //将报名按钮剧中
                    $("#baoMingBut").css({
                        "marginLeft": (parseInt($("#event").css("width")) - parseInt($("#baoMingBut").css("width"))) / 2
                    });

                    /*//初始化地图对象，加载地图
                     var map = new AMap.Map('map', {
                     zoom: 11,
                     center: [116.397428, 39.90923]
                     });

                     $(".amap-logo").remove();//删除高德地图中一些不需要的元素
                     $(".amap-copyright").remove();//删除高德地图中一些不需要的元素*/


                    //添加报名跳转支付宝url
                    $("#baoMingBut").click(function () {
                        if (${register == "true"}) {
                            alert("已经报名完成，不需要重复报名!");
                        } else {
                            app();
                        }
                    });
                });
            </script>
        </c:when>
    </c:choose>


</div>

</body>
</html>