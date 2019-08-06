<%--
  Created by IntelliJ IDEA.
  User: edward
  Date: 2016/11/23
  Time: 15:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%@ include file="/WEB-INF/pages/common/head-base-path.jsp" %>
<html>
<head>
    <meta charset="utf-8">
    <!-- 禁止缩放 -->
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <meta name="keywords" content="igeekery 生活 运动 服务 一体化，我们为您提供最 智能的硬件 软件 服务平台">
    <meta name="description" content="igeekery 是深圳第一蓝筹科技有限公司提供的 软硬件（智能服务，智能硬件）服务平台, 我们提供最优质 Mix 运动音乐和最智能的软硬件，让运动更方便，让喜欢运动的人群得到更好的运动体验。">
    <title>${themeName}</title>

    <link rel="stylesheet" href="/static/bootstrap/bootstrap.min.css">
    <script src="/static/jquery-1.11.2.min.js"></script>

    <style>
        body, h1, h2, h3, h4, h5, h6, .uk-navbar-nav > li > a { font-family:  "Microsoft Yahei","微软雅黑","Proxima Nova", "Helvetica Neue", Helvetica, Arial, sans-serif; }

        body, html{
            position: relative;
            height: 100%;
            background-color: #FFFFFF;
            padding: 0px;
            margin:0px;
        }

        img{
            width: 100%;
        }
    </style>
</head>
<body>

<div class="form-group">
    <a href="/wx-run/update-redis/access-token.htm" class="uk-button" >更新redis AccessToken</a>
</div>
<div class="form-group">
    <a href="/wx-run/to-wx/get-new-access-token.htm" class="uk-button" >获取新的AccessToken</a>
</div>
<div class="form-group">
    JsapiTicket - DB：<br/>
    <a href="javascript:void(0)" class="uk-button" >${info.jsapiTicket}</a>
</div>
<p>===================================================================================</p>
<div class="form-group">
        accessToken - DB：<br/>
    <a href="javascript:void(0)" class="uk-button" >${info.wxMqAccessToken}</a>
</div>
<div class="form-group">
    accessToken - old：<br/>
    <a href="javascript:void(0)" class="uk-button" >${info.oldAccessToken}</a>
</div>
<div class="form-group">
    update time：<br/>
    <a href="javascript:void(0)" class="uk-button" >
        <lch:parse-date time="${file.lastModifyTime}" pattern="yyyy-MM-dd HH:mm:ss"></lch:parse-date></a>
</div>
<div class="form-group">
    accessToken - Redis：<br/>
    <a href="javascript:void(0)" class="uk-button" >${info.redisAccessToken}</a>
</div>
</body>
</html>
