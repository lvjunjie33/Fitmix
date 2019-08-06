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
    <link type="text/css" rel="stylesheet" href="/static/channelApp/css/beats-generalize.css">
</head>
<body>
    <div class="container" style="display: none;">

        <%--<div class="logo">--%>
            <%--<img src="/imgs/app/beatsChannelApp/icon@2x.png" />--%>
        <%--</div>--%>
        <%--<div class="content">--%>
            <%--<span class="title">乐享动，运动音乐专家</span>--%>
            <%--<span class="text">请点击右上角按钮，然后在弹出的菜单中选择"在浏览器中打开"以下载乐享动APP</span>--%>
        <%--</div>--%>

        <img src="/imgs/app/bg.jpeg">

    </div>

    <div class="download" style="display: none">
        <div class="mark">
            乐享动Android版APP开始下载，若下载未自动开始，您可手动点击下方按钮：
        </div>
        <div class="logo1">
            <img src="/imgs/app/beatsChannelApp/logo@3x.png" />
        </div>
        <div class="slogan">
            <img src="/imgs/app/beatsChannelApp/slogan@2x.png" />
        </div>
        <div class="download-wrapper">
            <img src="/imgs/app/beatsChannelApp/download-button@2x.png"  data-href="${channelApp.androidApkUrl}" onclick="downloadStatistics(${channelApp.channelId},'ANDROID', '${channelApp.androidApkUrl}')" />
        </div>

    </div>

    <%--<div class="drop" style="display: none;">--%>
        <%--<div class="drop-content">--%>
            <%--<div class="text">--%>
                <%--<img src="/imgs/app/beatsChannelApp/download@2x.png">--%>
            <%--</div>--%>
            <%--<div class="button">--%>
                <%--<img src="/imgs/app/beatsChannelApp/button@2x.png">--%>
            <%--</div>--%>
        <%--</div>--%>
        <%--<div class="drop-arrow">--%>
            <%--<img src="/imgs/app/beatsChannelApp/arrow@2x.png">--%>
        <%--</div>--%>
    <%--</div>--%>

    <script type="text/javascript" src="/static/channelApp/js/mobile/201506/jquery.min.js"></script>
    <script>
        function downloadStatistics(channelId, type, url){
            $.ajax({
                url:"/channel-app/download-statistics.json",
                type:"post",
                dataType:"json",
                data:{
                    channelId:channelId,
                    type:type
                },
                success:function(){
                    window.location.href = url;
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
                window.location.href = 'http://a.app.qq.com/o/simple.jsp?pkgname=com.fitmix.sdk';
                $(".container").show();
            } else if (ua.match(/MicroMessenger/i) == "micromessenger") {
                $(".container").show();
//                $(".drop").show();
            } else {
                // 非微信浏览器
                $(".download").show();
                downloadStatistics('${channelApp.channelId}','ANDROID','${channelApp.androidApkUrl}');
            }
            //  todo pc 调试用
//            $(".container").show();
//            $(".drop").show();
//            $(".download").show();


        });

//        $(".drop").click(function () {
//           $(".drop").hide();
//        });


    </script>

</body>
</html>
