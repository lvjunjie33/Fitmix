<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2015-05-22
  Time: 11:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ include file="/WEB-INF/pages/common/head-base-path.jsp" %>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">

    <title>乐享动 用户运动分享</title>
   </head>


<style type="text/css">
    @font-face {
        font-family: "HelveticaNeueLTStd-UltLtCn";
        src: url("/fonts/HelveticaNeueLTStd-UltLtCn.otf");
        font-weight: normal;
        font-style: normal;
    }

    body {
        background-color: #5e5e5e;
    }

    ul {
        margin: 0px;
        padding: 0px;
    }

    body, h1, h2, h3, h4, h5, h6, .uk-navbar-nav > li > a {
        /*font-family: "Microsoft Yahei", "微软雅黑", "Proxima Nova", "Helvetica Neue", Helvetica, Arial, sans-serif;*/
        font-family: "HelveticaNeueLTStd-UltLtCn";
    }

    body, body * {
        padding: 0px;
        margin: 0px;
    }

    .home-div {
        background-color: #000000;
        background-image: url("${share.imageUrl}");
        background-size: 100% 100%;
        overflow: hidden;
    }

    .left_20 {
        left: 20px;
    }

    .distance {
        color: #FFFFFF;
        font-size: 120px;
        bottom: 230px;
        font-family: "HelveticaNeueLTStd-UltLtCn";
        font-weight: lighter;
        height:90px;
    }

   /* .distance::after {
        content: "公里";
        color: #FFFFFF;
        font-size: 26px;
        margin-left: 10px;
        text-shadow: 1px 3px 2px #000000;
        font-family: "微软雅黑";
    }*/

    .userUl {
        color: #FFFFFF;
        height: 40px;
        list-style-type: none;
        bottom: 160px;
    }

    .userUl li {
        float: left;
        height: 40px;
        line-height: 40px;
        width: initial;
        text-align: center;
        font-size: 18px;
        padding-right: 4px;
        font-family: "Microsoft Yahei", "微软雅黑", "Proxima Nova", "Helvetica Neue", Helvetica, Arial, sans-serif;
    }

    .address {
        color: #FFFFFF;
        height: 23px;
        line-height: 23px;
        font-size: 12px;
        bottom: 120px;
        height:20px;
        max-height:20px;
    }

    .address::before {
        content: url('/imgs/bgImgs/icon_weizhi@2x.png');
        margin-right: 10px;
    }


    .duttom-div {
        width: inherit;
        height: 50px;
        background-image: url('/imgs/bgImgs/bg_dibubofan.png');
        -webkit-background-size: 100% 100%;
        background-size: 100% 100%;
        background-repeat: no-repeat;
        position: absolute;
        bottom: 0px;
    }

    p, ul, li {
        color: #FFFFFF;
    }

    .bottom_ALL {
        position: absolute;
    }

    .audio_img {
        top: 11px;
        left: 20px;
        height: 28px;
    }

    .downloadImg {
        height: 32px;
        margin-top: 9px;
    }

    .fTextP {
        left: 58px;
        font-size: 12px;
        line-height: 14px;
    }

    .fTextP_1 {
        top: 14px;
    }

    .fTextP_2 {
        top: 30px;
    }

    .downloadImg_a {
        right: 20px;
    }

    .liLeftW {
        width: 40px;
    }

    .voiceActivation {
        bottom: 90px;
        min-width: 10px;
        background-color: #FF8F31;
        padding: 4px 10px 4px 10px;
        left: 20px;
        font-size:12px;
    }

    .icoImg {
        width: 20px;
        height: 20px;
        margin-top: 10px;
    }
    .myFontShadow {
        text-shadow: 1px 5px 5px #000;
    }
    .myFontShadowText {
        text-shadow: 1px 1px 1px #000;
    }
    .liG {
        margin-right: 10px;
    }
</style>

<body>
    <div class="home-div">
        <%--
        <p>随机背景:${share.imageUrl}随机背景URL;</p>
        <p>随机文字:${share.content}随机文字图片URL</p>
        ${share.address.city}.${share.address.country}  城市.国家
        ${share.mix.url} 音乐地址
        --%>

        <%--公里数--%>
        <span class="bottom_ALL left_20 distance myFontShadow"><fmt:formatNumber value="${share.distance/1000}" pattern="#,###,###.##"/><span style="font-size: 26px;">公里</span></span>
        <%--用户数据--%>
        <ul class="bottom_ALL left_20 userUl">

            <li class="lileftw">
                <img class="icoImg" src="/imgs/bgImgs/icon_bushu.png"/>
            </li>
            <li class="myFontShadowText">
                ${share.step}
            </li>
            <li class="liG"></li>
            <li class="lileftw">
                <img class="icoImg" src="/imgs/bgImgs/icon_kaluli.png"/>
            </li>
            <li class="myFontShadowText">
                ${share.calorie}
            </li>
            <li class="liG"></li>
            <li class="lileftw">
                <img class="icoImg" src="/imgs/bgImgs/icon_shijian.png"/>
            </li>
            <li class="myFontShadowText">
                <c:if test="${((share.runTime - share.runTime % 3600) / 3600) lt 10 }" >
                    0<fmt:formatNumber value="${(share.runTime - share.runTime % 3600) / 3600}" pattern="0"/>
                </c:if>
                <c:if test="${((share.runTime - share.runTime % 3600) / 3600) ge 10 }" >
                    <fmt:formatNumber value="${(share.runTime - share.runTime % 3600) / 3600}" pattern="0"/>
                </c:if>
                :
                <c:if test="${((share.runTime - share.runTime % 60) / 60) lt 10 }" >
                    0<fmt:formatNumber value="${(share.runTime - share.runTime % 60) / 60}" pattern="0"/>
                </c:if>
                <c:if test="${((share.runTime - share.runTime % 60) / 60) ge 10 }" >
                    <fmt:formatNumber value="${(share.runTime - share.runTime % 60) / 60}" pattern="0"/>
                </c:if>
                :
                ${share.runTime % 60}
            </li>

        </ul>
        <%--分享位置--%>
        <span class="bottom_ALL left_20 address myFontShadowText">${share.address.city}.${share.address.country}</span>
        <%--底部信息--%>
        <div class="duttom-div">
            <audio id="audio" src="${share.mix.url}"></audio>
            <img class="bottom_ALL audio_img" src="/imgs/bgImgs/icon_bofan.png"/>
            <%-- 歌曲名称--%>
            <p class="bottom_ALL fTextP fTextP_1 myFontShadow">${share.mix.name}</p>
            <%-- DJ 作者--%>
            <p class="bottom_ALL fTextP fTextP_2 myFontShadow">${share.mix.author}</p>
            <a class="bottom_ALL downloadImg_a" href="http://a.app.qq.com/o/simple.jsp?pkgname=com.fitmix.sdk">
                <img class="downloadImg" src="/imgs/bgImgs/dianjixiazai.png"/>
            </a>
        </div>
        <%--随机文字--%>
        <div class="bottom_ALL voiceActivation">${share.content}</div>

    </div>

</body>

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
<script defer async="true" src="/static/require/require.js" data-main="static/share/share" ></script>
