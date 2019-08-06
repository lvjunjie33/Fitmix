<%--
  Created by IntelliJ IDEA.
  User: fenxio
  Date: 2016/4/14
  Time: 13:54
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
    <!-- 禁止缩放 -->
    <meta name="keywords" content="igeekery 生活 运动 服务 一体化，我们为您提供最 智能的硬件 软件 服务平台">
    <meta name="description" content="igeekery 是深圳第一蓝筹科技有限公司提供的 软硬件（智能服务，智能硬件）服务平台, 我们提供最优质 Mix 运动音乐和最智能的软硬件，让运动更方便，让喜欢运动的人群得到更好的运动体验。">
    <title>${themeName}</title>
    <script src="/static/mobile/mobile-util.js"></script>
    <script src="/static/jquery-1.11.2.min.js"></script>
    <link rel="stylesheet" type="text/css" href="/static/video/video.css">


    <style type="text/css">
        *{
            padding: 0;
            margin: 0;
        }

        body div.videobox{
            width: 100%;
            height: 100%;
            background-color:#000;
        }

        body div.videobox video.video
        {
            width: 100%;
            height: 100%;
        }

        :-webkit-full-screen {

        }

        :-moz-full-screen {

        }

        :-ms-fullscreen {

        }

        :-o-fullscreen {

        }

        :full-screen {

        }

        :fullscreen {

        }

        :-webkit-full-screen video {
            width: 100%;
            height: 100%;
        }
        :-moz-full-screen video{
            width: 100%;
            height: 100%;
        }
        body{
            background: #201f26;
        }
    </style>
</head>
<body>
<c:choose>
    <c:when test="${ video.type == 1 }">
        <canvas id="main-canvas" style="background-image: url(${video.posterUrl});"></canvas>
        <div id="content">
            <div class="logo"></div>
            <div class="title">${video.title}</div>
            <div class="download">
                <button>观看更多  全景视频</button>
            </div>
        </div>
    </c:when>
    <c:otherwise>
        <div class="container">
            <div id="videobox">
                <video controls="controls" preload="preload" poster="${video.posterUrl}" x-webkit-airplay="true"
                       webkit-playsinline="true" id="video" width="100%" height="100%">
                    <source src="${video.videoUrl}" type="video/mp4" />
                </video>
                <div id="video-poster" style="background-image: url('${video.posterUrl}');"></div>
                <div id="video-play-btn"></div>
            </div>
            <div class="video-desc">
                <div class="titleBox">
                    <span class="title">${video.title}</span>
                    <span class="time">时长<fmt:parseNumber value="${(video.trackLength - video.trackLength % 60) / 60}"/>:${video.trackLength % 60}</span>
                </div>
                <div class="contentBox">
                    <p>
                            ${video.content}
                    </p>
                </div>
            </div>
        </div>

        <div class="video-recommend-container">
            <div class="video-box">
                <div class="video-tap">
                    最新推荐
                </div>
                <c:forEach items="${page.result}" var="each">
                    <div class="video-list">
                        <div class="video-cover">
                            <img src="${each.posterUrl}">
                        </div>
                        <div class="video-info">
                            <div class="video-title">
                                    ${each.title}
                            </div>
                            <div class="video-time">
                                时长<fmt:parseNumber value="${(video.trackLength - video.trackLength % 60) / 60}"/>:${video.trackLength % 60}
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>


        <div class="app-container">
            <div class="app-box">
                <div class="app-icon">
                    <img src="/imgs/video/FTIMIX.png">
                </div>
                <div class="app-content">
                    <div class="app-title">乐享动APP</div>
                    <div class="app-desc">下载APP观看更多内容</div>
                </div>
                <div class="app-download">
                    <button>观看更多</button>
                </div>
            </div>
        </div>
    </c:otherwise>
</c:choose>


<script type="text/javascript">
    $(".video-list").each(function() {
        $(this).click(function() {
            window.location.href = "http://a.app.qq.com/o/simple.jsp?pkgname=com.fitmix.sdk";
        });
    });
    $(".app-download").click(function() {
        window.location.href = "http://a.app.qq.com/o/simple.jsp?pkgname=com.fitmix.sdk";
    });
    $("#content .download").click(function() {
        window.location.href = "http://a.app.qq.com/o/simple.jsp?pkgname=com.fitmix.sdk";
    });

    window.onload = function() {
        var playBtn = document.getElementById("video-play-btn");
        if(playBtn != null) {
            playBtn.addEventListener("click",function() {
                var poster = document.getElementById("video-poster");
                poster.style.display = "none";
                playBtn.style.display = "none";
                var video = document.getElementById("video");
                video.play();
            });
        }
    }
</script>

</body>
</html>
