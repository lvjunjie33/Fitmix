<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2015/11/24
  Time: 9:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/pages/common/head-base-path.jsp" %>
<html>
<head>
    <title>乐享动 俱乐部邀请</title>
    <meta charset="utf-8">
    <!-- 禁止缩放 -->
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <script src="/static/jquery-1.11.2.min.js"></script>

    <style>
        body, h1, h2, h3, h4, h5, h6, .uk-navbar-nav > li > a { font-family:  "Microsoft Yahei","微软雅黑","Proxima Nova", "Helvetica Neue", Helvetica, Arial, sans-serif; }

        html,body{
            margin: 0px;
            padding: 0px;
            height: 100%;
            width: 100%;
        }

        #bg{
            position: fixed;
            background: #000000;
            opacity: 0.8;
            z-index: 20;
            left: 0px;
            right: 0px;
            top: 0px;
            bottom: 0px;

            -webkit-filter: blur(10px); /* Chrome, Opera */
            -moz-filter: blur(10px);
            -ms-filter: blur(10px);
            filter: blur(10px);

            filter: progid:DXImageTransform.Microsoft.Blur(PixelRadius=10, MakeShadow=false); /* IE6~IE9 */
        }

        #clubImage{
            position: fixed;
            height: 200%;
            width: 200%;
            top: -50%;
            bottom: -50%;
            left: -50%;
            right: -50%;
            z-index: 10;
        }

        #wrapper{
            position: absolute;
            z-index:40;
            width: 100%;
            text-align: center;
            margin-top:20%;
            color: #FFF;
        }


        #log{
            width: 120px;
        }


        a {
            text-decoration: none;
            color: white;
            text-decoration: none;
        }

        .signup {
            color: white;
            background: #FF6118;
            padding: 16px 26px;
            display: inline-block;
            margin: 16px 26px;
            border-radius: 6px;
            font-weight: 700;
            /*float: left;*/
            /*position: absolute;*/
        }

        #footer-down{
            position: fixed;
            bottom: 0px;
            padding: 5px;
            height: 50px;
            width: 100%;
            background-color: #242424;
            z-index: 50;
            color:#F6F6F6;
        }

        #footer-down .down-log{
            height: 100%;
            display: inline-block;
        }

        #footer-down .btn-down{
            height: 40px;
            width: 100px;
            position: absolute;
            right: 20px;;
        }

        #footer-down .btn-down-img{
            height: 40px;
            width: 100px;
            text-align: center;
            position: absolute;
            top: 5px;;
        }
    </style>
</head>

<body>

<div id="bg">
    <%--<img src="${club.backImageUrl}" id="clubImage">--%>
    <img src="/imgs/club/club-share-back@2x.png" id="clubImage">
</div>


<section id="wrapper">
    <section id="copy">
        <a class="humanlogo" href="#"><img src="/imgs/club/logo.png" alt="igeekery" id="log"/></a>
        <h1>${club.user.name}</h1>
        <p>邀请你加入</p>
        <h1>`${club.name}` 俱乐部</h1>
        <a class="signup" href="javascript:" onclick="linktoHuman()">加入俱乐部</a>
        <div class="nohuman">
            <%--<a href="http://a.app.qq.com/o/simple.jsp?pkgname=com.fitmix.sdk" >没有 乐享动, 请先下载应用.</a>--%>
        </div>
    </section>
</section>


<div id="footer-down">
    <img class="down-log" src="/imgs/fitmix-logo.png"/>
    <div style="display: inline-block;min-height: 50px;!important;position: absolute;top: 9px;padding-left: 8px;">
        <label style="display: block;">乐享动</label>
        <label style="font-size: 60%;">下载 APP 即可加入俱乐部</label>
    </div>
    <a  class="btn-down"  href="http://a.app.qq.com/o/simple.jsp?pkgname=com.fitmix.sdk" style="display: inline-block;height: 50px;">
        <img class="btn-down-img" src="/imgs/club/btn_dwon.png"/>
    </a>
</div>

<script type="text/javascript">
    var ua = navigator.userAgent.toLowerCase();
    if (/iphone|ipad|ipod/.test(ua)) {
        // 对浏览器的UserAgent进行正则匹配，不含有微信独有标识的则为其他浏览器
        var userAgent = navigator.userAgent; //取得浏览器的userAgent字符串
        if (userAgent.indexOf("Safari") > -1) {

        } else {
            $(".nohuman").append("<p style='color: red'>请在 Safari 浏览器中打开.</p>");
        }
    } else if (/android/.test(ua)) {
        $(".nohuman").append("<p style='color: red'>请在自带浏览器中打开.</p>");
    }
</script>

<script>

    function linktoHuman() {
        var openApp = "FITMIX://club-member/add.json?clubId=${club.id}&clubName=${club.name}";
        window.location.href = openApp;
    }
</script>
</body>