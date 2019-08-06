<%--
  Created by IntelliJ IDEA.
  User: Sin
  Date: 2016/2/18
  Time: 13:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/WEB-INF/pages/common/head-base-path.jsp" %>
<!DOCTYPE html>
<html>
<head>

    <meta charset="utf-8">
    <!-- 禁止缩放 -->
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <title>加菲猫欢乐跑深圳</title>

    <link rel="stylesheet" href="/static/bootstrap/bootstrap.min.css">
    <script src="/static/jquery-1.11.2.min.js"></script>
    <script type="text/javascript" src="/static/jquery.form.js"></script>
    <style>
        body, h1, h2, h3, h4, h5, h6, .uk-navbar-nav > li > a { font-family:  "Microsoft Yahei","微软雅黑","Proxima Nova", "Helvetica Neue", Helvetica, Arial, sans-serif; }

        body,html{
            max-width: 600px;
            margin: 10px auto 0px auto;
            width: 100%;
            height: 100%;;
            background: #f7e852;
        }

        .img-with{
            width: 100%;
        }

        .textarea-none{
            border: 0px;
        }
    </style>
    <script type="text/javascript">
        var ua = navigator.userAgent.toLowerCase();
        var isWeixin = ua.indexOf('micromessenger') != -1;
        var isAndroid = ua.indexOf('android') != -1;
        var isIos = (ua.indexOf('iphone') != -1) || (ua.indexOf('ipad') != -1);
        if (!isWeixin) {
            document.head.innerHTML = '<title>抱歉，出错了</title><meta charset="utf-8"><meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=0"><link rel="stylesheet" type="text/css" href="https://res.wx.qq.com/connect/zh_CN/htmledition/style/wap_err1a9853.css">';
            document.body.innerHTML = '<div class="page_msg"><div class="inner"><span class="msg_icon_wrp"><i class="icon80_smile"></i></span><div class="msg_content"><h4>请在微信客户端打开链接</h4></div></div></div>';
        }
    </script>

</head>
<body>

    <div style="padding:10px 0px; ">
        <div style="line-height: 3;">
            <img class="img-with" src="/imgs/activityOther/jiaFeiMao/sign-up-head.png">
        </div>

        <c:choose>
            <c:when test="${empty data}">
                <div style="text-align: center;padding-top: 20%;">
                    <h3>您还未报名哦.</h3>
                </div>

            </c:when>
            <c:when test="${not empty data}">
                <p style="text-align: center;line-height: 2">&nbsp;</p>
                <p style="text-align: center;line-height: 2">${data.group}</p>

                <p style="text-align: center;line-height: 2">============================</p>


                <c:forEach items="${data.jiaFeiMaoMembers}" var="item">
                    <p style="text-align: center;line-height: 2">&nbsp;</p>
                    <p style="text-align: center;line-height: 2">姓名：${item.name}</p>
                    <p style="text-align: center;line-height: 2">年龄：${item.memo}</p>
                    <p style="text-align: center;line-height: 2">性别：${item.gender}</p>
                    <p style="text-align: center;line-height: 2">尺码：${item.clothesSize}</p>
                    <p style="text-align: center;line-height: 2">证据类型：${item.idCardType}</p>
                    <p style="text-align: center;line-height: 2">身份证：${item.idCard}</p>
                </c:forEach>
                <p style="text-align: center;line-height: 2">============================</p>

                <p style="text-align: center;line-height: 2"><strong>通知信息</strong></p>
                <p style="text-align: center;line-height: 2">${data.email}</p>
                <p style="text-align: center;line-height: 2">${data.mobileName}</p>
                <p style="text-align: center;line-height: 2">${data.mobilePhone}</p>

                <p style="text-align: center;line-height: 2"><strong>紧急联系人</strong></p>
                <p style="text-align: center;line-height: 2">${data.emergencyName}</p>
                <p style="text-align: center;line-height: 2">${data.emergencyPhone}</p>
                <p style="text-align: center;line-height: 2">============================</p>
            </c:when>
        </c:choose>

    </div>
</body>
</html>
