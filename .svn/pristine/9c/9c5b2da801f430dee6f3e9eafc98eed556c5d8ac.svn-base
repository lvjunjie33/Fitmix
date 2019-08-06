<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016-02-29
  Time: 11:03
  To change this template use File | Settings | File Templates.
  跳绳分享 新风格
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ include file="/WEB-INF/pages/common/head-base-path.jsp" %>
<html>
<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta name="viewport" content="initial-scale=1.0, user-scalable=no"/>

    <title>乐享动 用户跳绳分享</title>
    <link rel="stylesheet" href="/static/bootstrap/dist/css/bootstrap.min.css"/>

    <%--  百度统计  --%>
    <script type="text/javascript">
        var _hmt = _hmt || [];
        (function () {
            var hm = document.createElement("script");
            hm.src = "//hm.baidu.com/hm.js?b5bf26e306096c5c177896b7d17f6d74";
            var s = document.getElementsByTagName("script")[0];
            s.parentNode.insertBefore(hm, s);
        })();
    </script>

</head>
<style>
    body {
        margin: 0;
        padding: 0;
        background-color: #1e1d23;
    }
    .share-content {
        max-width: 380px;
        margin: 0 auto;
    }
    .share-img {
        width: 100%;
    }
    .share-img img {
        max-width: 380px;
    }
    .user-info {
        width: 100%;
        height: 50px;
        background-color: #1e1d23;
        margin-top: 10px;
        margin-bottom: 10px;
        padding-left: 10px;
    }
    .user-info .user-avatar {
        display: inline-block;
        width: 50px;
        height: 50px;
        vertical-align: top;
    }
    .user-info .user-avatar img {
        width: 50px;
        height: 50px;
        border-radius: 50%;
    }
    .user-info .user-box {
        display: inline-block;
        height: 50px;
        vertical-align: top;
        margin-left: 10px;
    }
    .user-info .user-box span {
        color: #ffffff;
    }
    .user-info .user-box span:first-child {
        font-size: 20px;
    }
    .user-info .user-box span:last-child {
        font-size: 12px;
    }

    /* 下载悬浮banner */
    .app-container {
        bottom: 0;
        height: 80px;
        width: 100%;
        z-index: 300; }

    .app-box {
        bottom: 0;
        background-color: rgba(36, 36, 36, 0.57);
        margin: 0 auto;
        height: inherit; }

    .app-icon, .app-content, .app-download {
        display: inline-block;
    }

    .app-icon {
        margin-left: 0.42667rem;
        margin-top: 15px;
    }

    .app-icon img {
        width: 50px;
        height: 50px;
    }

    .app-content {
        vertical-align: top; }

    .app-title {
        font-family: "Microsoft Yahei";
        font-size: 20px;
        color: #FFFFFF;
        height: 80px;
        line-height: 80px;;
    }

    .app-desc {
        font-family: "Microsoft Yahei";
        font-size: 0.46933rem;
        color: #FFFFFF;
    }

    .app-download {
        margin-top: 25px;
        float: right;
        margin-right: 20px;
    }

    .app-download button {
        height: 30px;
        width: 80px;
        background-color: transparent;
        border-color: #FF9B07;
        border-radius: 0.21333rem;
        color: #FFFFFF;
        font-size: 14px;
        outline: none;
        cursor: pointer;
        z-index: 301; }

</style>
<body>
<div class="share-content">
    <div class="user-info">
        <div class="user-avatar"><img src="${user.avatar}" /></div>
        <div class="user-box">
            <span>${user.name}</span><br>
            <span>ID:${user.id}</span>
        </div>
    </div>

    <div class="share-img">
        <img src="${share.imgUrl}" />
    </div>

    <div class="app-container">
        <div class="app-box">
            <div class="app-icon">
                <img src="/imgs/video/FTIMIX.png">
            </div>
            <div class="app-content">
                <div class="app-title">乐享动APP</div>
            </div>
            <div class="app-download">
                <button>下载</button>
            </div>
        </div>
    </div>
</div>

<script src="/static/jquery-1.11.2.min.js"></script>
<script>
    $(".app-download").click(function() {
        window.location.href = "http://a.app.qq.com/o/simple.jsp?pkgname=com.fitmix.sdk";
    });
    $("#content .download").click(function() {
        window.location.href = "http://a.app.qq.com/o/simple.jsp?pkgname=com.fitmix.sdk";
    });
</script>


</body>