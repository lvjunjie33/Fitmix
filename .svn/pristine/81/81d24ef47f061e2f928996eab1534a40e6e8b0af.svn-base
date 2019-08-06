<%--
  Created by IntelliJ IDEA.
  User: edward
  Date: 2017/8/15
  Time: 10:27
  To change this template use File | Settings | File Templates.
--%>
<%--
  Created by IntelliJ IDEA.
  User: edward
  Date: 2017/8/1
  Time: 15:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ include file="/WEB-INF/pages/common/head-base-path.jsp" %>

<html>
<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>

    <title>乐享动 | 运动话题</title>
    <script src="/static/mobile/mobile-util.js"></script>
    <link type="text/css" rel="stylesheet" href="/static/common/download.css">

    <style type="text/css">
        html{
            width: 100%;
            margin: 0px;
            padding: 0px;
        }
        body {
            -webkit-text-size-adjust: 100% !important;
            text-size-adjust: 100% !important;
            -moz-text-size-adjust: 100% !important;
            width: 100%;
            margin: 0px;
            padding: 0px;
        }
        .radius-img {
            border-radius: 65px;
            width: 100%;
            margin-bottom: 10px;
        }

        .txt-hidden {
            overflow: hidden;
            white-space: nowrap;
            text-overflow: ellipsis;
        }

        .div-content img {
            width:99.5%;
        }
    </style>
    <script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=98dfV32uFI52e1iUnlpYKdbbXSBYhiQY"></script>

</head>

<body>
<div style="overflow-x:hidden">
    <%--问题--%>
    <div style="background: #000;color: #FFF;padding: 10px 30px;word-wrap:break-word;">
        ${answer.parentTheme.title}
<%--        <p style="width: 80%;overflow: hidden;text-overflow:ellipsis;white-space: nowrap;margin: 0px auto">${answer.parentTheme.title}</p>--%>
    </div>

    <%-- 回答 --%>
    <div style="margin:0px;padding:0px;padding-top: 10px;margin: 0px 2%;">
        <div style="margin-top: 20px;">
            <ul style="list-style: none;margin: 0px;padding: 0px;width: 100%;font-size: 60%;">
                <li style="float: left;width: 15%;">
                    <c:choose>
                        <c:when test="${empty answer.avatar}">
                            <img class="radius-img" src="/imgs/default.png" />
                        </c:when>
                        <c:otherwise>
                            <img class="radius-img" src="${answer.avatar}" />
                        </c:otherwise>
                    </c:choose>
                </li>
                <li style="float: left;width: 75%;padding-top: 2%;padding-left: 2%;" class="txt-hidden">
                    楼主：${answer.name}
                </li>
                <li style="float: left;width: 75%;padding-top: 2%;padding-left: 2%;" class="txt-hidden">
                    签名：${answer.signature}
                </li>
            </ul>
        </div>
        <div style="border:2px solid rgba(54, 63, 98, 0.25);margin: 15px 0px;clear: left"></div>
        <div class="uk-form-row div-content" style="word-wrap: break-word;">
            ${answer.content}
        </div>
        <div style="margin-top: 20px;">
            <ul style="list-style: none;margin: 0px;padding: 0px;font-size: 60%">
                <li style="text-align: right">${addTime}&nbsp;&nbsp;</li>
                <li style="text-align: right">版权归作者所有&nbsp;&nbsp;</li>
            </ul>
        </div>
        <div  style="height: 15%"></div>
    </div>
</div>

<div class="footer">
    <div class="left">
        <div class="logo">
            <img src="/static/user-run-share/imgs/logo.png">
        </div>
        <div class="content">
            <div class="name">乐享动</div>
            <div class="slogan">跃动乐享动</div>
        </div>
    </div>
    <div class="right">
        <div class="download" style="margin-right: 15%">
            <button onclick="downloadApp();">立即下载</button>
        </div>
    </div>
</div>
<script>
    function downloadApp() {
        window.location.href = "http://a.app.qq.com/o/simple.jsp?pkgname=com.fitmix.sdk";
    }
    function openApp() {
        window.location.href = "FITMIX://";
    }

    (function(){
        if (typeof(WeixinJSBridge) == "undefined") {
            document.addEventListener("WeixinJSBridgeReady", function (e) {
                setTimeout(function(){
                    WeixinJSBridge.invoke('setFontSizeCallback',{"fontSize":0}, function(res) {
                        // alert(JSON.stringify(res));
                    });
                },0);
            });
        } else {
            setTimeout(function(){
                WeixinJSBridge.invoke('setFontSizeCallback',{"fontSize":0}, function(res) {
                    // alert(JSON.stringify(res));
                });
            },0);
        }
    })();
</script>

</body>
</html>