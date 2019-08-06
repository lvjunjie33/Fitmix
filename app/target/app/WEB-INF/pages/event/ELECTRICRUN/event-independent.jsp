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
    <title>2016热波电跑ELECTRIC RUN全国站</title>

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
    <%-- 高德地图
    <script type="text/javascript" src="https://webapi.amap.com/maps?v=1.3&key=0e9cc8786d487525f8759b2e3bd9d476"></script>--%>
    <script src="/static/event/js/event.js"></script>


</head>
<body>

<div id="event" class="event">

    <img class="eventTitleImg" src="${titleImg}"/>

    <div class="divDfault">
        <p id="eventName" class="commonP2">2016热波电跑ELECTRIC RUN全国站</p>

        <p class="commonType">
            <%--<c:choose>
                <c:when test="${diffHour <= 0}">
                     <span>报名中</span>
                </c:when>
                <c:when test="${diffHour > 0}">
                    <span>${startDiffHour}&nbsp;小时后开始</span>
                </c:when>
            </c:choose>--%>
        </p>
    </div>

    <ul>
        <li class="leftLi">活动时间:</li>
        <li class="rightLi">2016.04—2016.08（各站具体时间待定）</li>
        <li class="leftLi">活动类型:</li>
        <li class="rightLi">夜跑</li>
        <li class="leftLi">报名费:</li>
        <li class="rightLi">待定</li>
    </ul>

    <div class="eventInformation" style="margin-top: 140px;">活动简介</div>
    <div class="eventInformation introduction" style="margin-top: 10px;">
        <p>Electric Run是全球首屈一指的夜间5公里跑步/步行娱乐体验活动。旨在</p>

        <p>通过选酷跑形式吸引喜爱电子音乐和关注流行文化的青年聚集起来，利用现场音</p>

        <p>乐和氛围的力量使“运动健康生活”的理念具象化，代领青年人将正向价值观和</p>

        <p>积极健康的生活运动习惯变为一种时尚。</p>

        <p>国内外知名DJ现场助阵，告别独自塞着耳机跑步的枯燥范围，颠覆传统运</p>

        <p>动，开启运动社交化新时代</p>

        <p></p>

        <p>听说，在热波电跑，跑步和电音更配。</p>

        <p>2016彩色跑中国Tour行程表（暂定）：</p>

        <p>4月 深圳</p>

        <p>5月 厦门</p>

        <p>6月 上海</p>

        <p>7月 西安</p>

        <p>8月 北京</p>

        <p>更多：大同、广州、泉州、苏州、福州、成都、重庆 敬请期待！</p>

        <p></p>
        <img class="eventTitleImg textImg" src="/static/event/images/ELECTRICRUN/62-141214210945.jpg"/>

        <p></p>
        <img class="eventTitleImg textImg" src="/static/event/images/ELECTRICRUN/52207c61af064621684.jpg"/>

        <p></p>
        <img class="eventTitleImg textImg" src="/static/event/images/ELECTRICRUN/1217123355335235.JPG"/></p>

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