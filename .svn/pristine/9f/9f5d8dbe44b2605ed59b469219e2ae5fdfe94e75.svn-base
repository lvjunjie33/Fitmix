<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: fenxio
  Date: 2017/1/4
  Time: 14:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>乐享动</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

    <meta name="keywords" content="igeekery 生活 运动 服务 一体化，我们为您提供最 智能的硬件 软件 服务平台">
    <meta name="description" content="igeekery 是深圳第一蓝筹科技有限公司提供的 软硬件（智能服务，智能硬件）服务平台, 我们提供最优质 Mix 运动音乐和最智能的软硬件，让运动更方便，让喜欢运动的人群得到更好的运动体验。">
    <script src="/static/mobile/mobile-util.js"></script>
    <link rel="stylesheet" type="text/css" href="/static/shop/flow.css" />
</head>
<body>
    <img src="/imgs/shop/share/qrcode.png"  class="qr"/>
    <div class="container">
        <div class="content">
            <div class="avatar">
                <c:if test="${user.avatar != ''}">
                    <img src="${user.avatar}" />
                </c:if>
                <c:if test="${user.avatar == ''}">
                    <img src="/imgs/shop/share/default.png" />
                </c:if>
            </div>
            <div class="text">
                <p>我刚在 <span>乐享动</span> </p>
                <p>兑换了 <span>${flow}M</span> 手机流量</p>
            </div>
            <div class="line"></div>
            <div class="text-1">
                <p>跟我一起甩掉脂肪，换取流量吧！</p>
                <p>你的脂肪终于有人要啦</p>
                <p>用乐享动运动 甩掉脂肪 即可兑换手机流量</p>
            </div>
        </div>

        <div class="QRcode">
            <div class="qrimg"></div>
            <%--<img src="/imgs/shop/share/qrcode.png" />--%>
            <p>长按二维码</p>
        </div>
        <div class="download">
            <img src="/imgs/shop/share/logo.png"/>
            <p>乐享动，好玩的运动APP</p>
            <a href="javascript:openApp();" id="openApp" class="btn">立即打开</a>
        </div>
    </div>

    <script>

        function openApp() {
            var ua = navigator.userAgent.toLowerCase();
            if (/iphone|ipad|ipod/.test(ua)) {
                // 对浏览器的UserAgent进行正则匹配，不含有微信独有标识的则为其他浏览器
                var userAgent = navigator.userAgent; //取得浏览器的userAgent字符串
                if (userAgent.indexOf("Safari") > -1) {
                    var openApp = "FITMIX://channel-app/generalize.htm?channelId=55";
                    window.location.href = openApp;
                    setTimeout(function(){window.location.href = 'http://a.app.qq.com/o/simple.jsp?pkgname=com.fitmix.sdk'}, 2000);
                } else {
                    alert("请在 Safari 浏览器中打开");
                    return false;
                }
            } else if (/android/.test(ua)) {

                if( isQQ() || isWeiXin()) {
                    alert("请在自带浏览器中打开");
                } else {
                    var openApp = "FITMIX://channel-app/generalize.htm?channelId=55";
                    window.location.href = openApp;
                    setTimeout(function(){window.location.href = 'http://a.app.qq.com/o/simple.jsp?pkgname=com.fitmix.sdk'}, 2000);
                }
            } else {
                window.location.href = 'http://a.app.qq.com/o/simple.jsp?pkgname=com.fitmix.sdk';
            }

        }

        function isWeiXin(){
            var ua = window.navigator.userAgent.toLowerCase();
            if(ua.match(/MicroMessenger/i) == 'micromessenger'){
                return true;
            }else{
                return false;
            }
        }

        function isQQ(){
            var ua = window.navigator.userAgent.toLowerCase();
            if(ua.match(/QQ/i) == 'qq'){
                return true;
            }else{
                return false;
            }
        }

    </script>

</body>
</html>
