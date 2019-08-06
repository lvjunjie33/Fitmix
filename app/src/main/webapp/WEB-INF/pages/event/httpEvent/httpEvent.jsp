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

    <link href="/static/bootstrap/bootstrap.min.css">
    <link href="/static/event/css/event.css" type="text/css" rel="stylesheet">
    <style>
        body, .navbar-fixed-bottom {
            background-color: #FFFFFF;
            border: 0px;
        }

        .navbar-fixed-bottom {
            height: 50px;
            border: 0px;
        }

        iframe {
            border: 0px;
        }
    </style>
    <script src="/static/jquery-1.11.2.min.js"></script>
    <script src="/static/event/js/event.js"></script>
</head>
<body>
<iframe id="eventFrame" src="" width="100%"></iframe>

<c:choose>
    <c:when test="${apply}">

        <nav class="navbar navbar-default navbar-fixed-bottom">
            <div class="container">
                <input type="button" id="baoMingBut" class="baoMingBut" value="立即报名" style="margin-top: 10px;"/>
            </div>
        </nav>
        <script>

            /***
             * 提供给app调用的方法
             * @param userId
             * @constructor
             */
            function GetAppUserId(userId) {
                var httpHost = "http://" + window.location.host;

                //跳转支付请求
                location.href = httpHost + "/activity/register.htm?" +
                        "&activityId=" + ${activityId} +
                                "&uid=" + userId;
            }

            $(function () {

                //将报名按钮剧中
                $("#baoMingBut").css({
                    "marginLeft": (parseInt($(window).width()) - parseInt($("#baoMingBut").css("width"))) / 2
                });


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

<script>
    $(function (){
        $("#eventFrame").css({
            height: $(document).height() + "px"
        });
        $("#eventFrame").attr("src", "${url}");
    });
</script>


</div>

</body>
</html>