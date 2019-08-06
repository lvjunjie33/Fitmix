<%--
  Created by IntelliJ IDEA.
  User: sin
  Date: 2015/8/28
  Time: 9:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ include file="/WEB-INF/pages/common/head-base-path.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <!-- 禁止缩放 -->
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <meta name="keywords" content="igeekery 生活 运动 服务 一体化，我们为您提供最 智能的硬件 软件 服务平台">
    <meta name="description"
          content="igeekery 是深圳第一蓝筹科技有限公司提供的 软硬件（智能服务，智能硬件）服务平台, 我们提供最优质 Mix 运动音乐和最智能的软硬件，让运动更方便，让喜欢运动的人群得到更好的运动体验。">
    <title>活动列表</title>

    <link href="/static/event/css/event.css" type="text/css" rel="stylesheet">
    <script>
        (function (window) {
            var event = {
                SYSTIME: "${SYSTIME}"//系统当前时间
            }
            window.event = event;
        })(window);

        function time(date) {
            var yyyy = date.getFullYear();
            var mm = date.getMonth() + 1;
            var dd = date.getDate();
            mm = mm < 10 ? "0" + mm : mm;
            dd = dd < 10 ? "0" + dd : dd;
            //document.getElementById(id).innerHTML = "活动时间 " + yyyy + "/" + mm + "/" + dd;
            return yyyy + "/" + mm + "/" + dd;
        }
        /***
         *
         * 计算两个时间的时间差 精确到 天 小时
         * 大于1天返回天数 小于一天返回小时
         *
         * */
        function timeDifference(beginDate, endDate) {
            var time = (endDate - beginDate) / 1000;
            var dd = time / 60 / 60 / 24;
            var HH = (dd - parseInt(dd)) * 24;
            var mm = (HH - parseInt(HH)) * 60;
            if (parseInt(dd) > 0) {
                return parseInt(dd) + "天";
            } else if (parseInt(HH) > 0) {
                return parseInt(HH) + "小时";
            } else if (parseInt(mm) > 0) {
                return parseInt(mm) + "分";
            }
        }

        /****
         *
         * 获取当前赛事状态
         *
         * @param sysTime            系统时间
         * @param applyBeginTime     报名开始时间
         * @param applyEndTime       报名结束时间
         * @param activityBeginTime  活动开始时间
         * @param activityEndTime    活动结束时间
         * @param themeName          活动名称 log使用
         * @param id                 活动id 设置状态使用
         * @param registerCount      报名人数
         * @param activityMaxNumber  最大报名人数
         */
        function eventType(sysTime, applyBeginTime, applyEndTime, activityBeginTime, activityEndTime, themeName, id
                , registerCount, activityMaxNumber) {

            //赛事状态显示
            //条件                              状态                人数状况              相关时间
            //当前时间<报名开始时间                即将开始报名          最大报名人数          活动开始时间
            //报名开始时间<=当前时间<活动结束时间    正在报名             报名数/最大报名人数    距离报名结束还剩XX
            //报名结束时间<=当前时间<活动结束时间    报名结束             已报名数/最大报名数    活动开始时间
            //当前时间>=活动结束时间               活动结束             已报名数/最大报名数    活动开始时间

            var type = "活动结束",apply = false;

            if (sysTime != "" && applyBeginTime != "" && applyEndTime != ""
                    && activityBeginTime != "" && activityEndTime != "") {
                if (sysTime < applyBeginTime) {
                    type = "即将开始报名";

                } else if (applyEndTime <= sysTime && sysTime < activityEndTime) {
                    type = "报名结束";
                    document.getElementById(id + "_activityMaxNumber").innerHTML = "名额: " + registerCount + "/" + activityMaxNumber;

                } else if (applyBeginTime <= sysTime && sysTime < activityEndTime) {
                    type = "正在报名";
                    apply = true;
                    //计算距离报名结束还剩下多少天
                    var time = "距离报名结束还剩下" + timeDifference(sysTime, applyEndTime);
                    document.getElementById(id + "_activityBeginTime").innerHTML = time;
                    document.getElementById(id + "_activityMaxNumber").innerHTML = "名额: " + registerCount + "/" + activityMaxNumber;

                } else if (sysTime >= activityEndTime) {
                    type = "活动结束";
                    document.getElementById(id + "_activityMaxNumber").innerHTML = "名额: " + registerCount + "/" + activityMaxNumber;

                } else {


                }

            } else {
                console.log(themeName + " 信息错误 无法判断出来赛事状态 ... ... ");
            }

            document.getElementById(id + "_diffHour").innerHTML = type;

            document.getElementById(id+"_a").href+="&apply="+apply;

        }

    </script>

    <style>

        * {/*适用ios终端的禁止手指长按*/
            -webkit-touch-callout: none;
            -webkit-user-select: none;
            -moz-user-select: none;
            -ms-user-select: none;
            -o-user-select: none;
            user-select: none;
        }
        .diffHour {
            padding: 3px 6px 3px 6px;
            border: 1px #fff solid;
            border-radius: 4px;
            display: -webkit-inline-box;
            height: auto;
            font-size: 12px;
            margin-top: 8px;
        }

        .colorDiffHourText {
            color: #FF8B22;
        }

        body > iframe,div#event ~ div {
            display: none;
            opacity:0;
        }

    </style>

</head>
<body>
<div id="event">

    <c:forEach items="${page.result}" var="obj">
        <c:if test="${obj.releaseStatus != 1}"><%-- 1为待发布状态 --%>
            <a id="${obj.id}_a" href="/activity/to-activity.htm?activityId=${obj.id}" class="list_A" style="display: block;text-decoration: none;">
            <%--<a id="${obj.id}_a" href="event/event-independent.htm?activityId=${obj.id}&register=${obj.register}&titleImg=${obj.themeImage}&diffHour=${obj.startDiffHour}&startDiffHour=${obj.startDiffHour}" class="list_A" style="display: block;text-decoration: none;">--%>
                <div class="event_new">
                    <img id="themeImage_${obj.id}" class="event_new_all themeImage" src="${obj.themeImage}">

                    <div class="event_new_all zhong"></div>
                    <div class="event_new_all">

                        <c:if test="${obj.register}"> <%--isRegister 用户是否已经参与此活动转台 releaseStatus为0是表示用户正在发布中--%>
                            <img class="userTypeImg" style="width: 60px ;height:auto;"
                                 src="/static/event/images/525916898283847903.png"/>
                            <input type="hidden" id="${obj.id}_register" value="${obj.register}"/>
                        </c:if>

                        <ul class="title">
                            <li style="font-size: 20px;"><b>${obj.themeName}</b></li>
                            <li>
                                <div id="${obj.id}_diffHour" class="diffHour"></div>
                            </li>
                        </ul>

                        <div class="bottom_data">
                            <div class="bottom_data_left" id="${obj.id}_activityMaxNumber">
                                名额:${obj.activityMaxNumber}</div>
                            <div class="bottom_data_right" id="${obj.id}_activityBeginTime"></div>
                        </div>
                    </div>
                </div>
                <script>

                    document.getElementById("${obj.id}_activityBeginTime").innerHTML = "活动时间: " + time(new Date(${obj.activityBeginTime}));

                    eventType(
                            parseInt(window.event.SYSTIME),
                            parseInt("${obj.signUpBeginTime}"),
                            parseInt("${obj.signUpEndTime}"),
                            parseInt("${obj.activityBeginTime}"),
                            parseInt("${obj.activityEndTime}"),
                            "${obj.themeName}",
                            "${obj.id}",
                            "${obj.activityFalseNumber + obj.registerCount}",
                            "${obj.activityMaxNumber}"
                    );
                </script>
            </a>
        </c:if>
    </c:forEach>

</div>


<script src="/static/jquery-1.11.2.min.js"></script>
<script src="/static/util/delete.js"></script>
<script src="/static/event/js/event.js"></script>
<script id="endScript">
    $(function () {
        //触摸赛事突出亮度
        $(".zhong").on("touchstart", function () {
            $(this).css("opacity", 0);
        });
        $(".zhong").on("touchend", function () {
            $(this).css("opacity", 0.6);
        });

        //屏蔽广告
        deleteAdvertising("event","endScript");
    });
</script>

</body>
</html>