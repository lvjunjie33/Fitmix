<%--
  Created by IntelliJ IDEA.
  User: fenxio
  Date: 2017/1/3
  Time: 16:03
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
    <link type="text/css" rel="stylesheet" href="/static/channelApp/css/generalize.css">
</head>
<body>
    <div class="container">
        <div class="logo">
            <img src="/imgs/app/logo.png" />
        </div>
        <div class="slogan">
            <img src="/imgs/app/slogan.png" />
        </div>
        <div class="ios">
            <img src="/imgs/app/ios.png" data-href="https://itunes.apple.com/cn/app/le-xiang-dong-you-yun-dong/id999239164?l=zh&mt=8" onclick="downloadStatistics(${channelApp.channelId},'IOS',this)" />
        </div>
        <div class="android">
            <img src="/imgs/app/android.png" data-href="http://a.app.qq.com/o/simple.jsp?pkgname=com.fitmix.sdk" onclick="downloadStatistics(${channelApp.channelId},'ANDROID',this)" />
        </div>
    </div>

    <script type="text/javascript" src="/static/channelApp/js/mobile/201506/jquery.min.js"></script>
    <script>
        function downloadStatistics(channelId,type,self){
            $.ajax({
                url:"/channel-app/download-statistics.json",
                type:"post",
                dataType:"json",
                data:{
                    channelId:channelId,
                    type:type
                },
                success:function(){
                    window.location.href=$(self).data("href");
                }
            });
        }

        $(function(){
            $.ajax({
                url:"/channel-app/modify-open-status.json",
                type:"post",
                data:{id:${channelAppStatistics.id}},
                dataType:"json",
                success:function(){

                }
            });

            var ua = navigator.userAgent.toLowerCase();
            if (/iphone|ipad|ipod/.test(ua)) {
                // 对浏览器的UserAgent进行正则匹配，不含有微信独有标识的则为其他浏览器
                var userAgent = navigator.userAgent; //取得浏览器的userAgent字符串
                if (userAgent.indexOf("Safari") > -1) {

                } else {
//                    alert("请在 Safari 浏览器中打开.");
                }
            } else if (ua.match(/MicroMessenger/i) == "micromessenger") {
//                alert("请在自带浏览器中打开.");
            }

        });

    </script>

</body>
</html>
