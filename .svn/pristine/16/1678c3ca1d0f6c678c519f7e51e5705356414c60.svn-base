<%--
  Created by IntelliJ IDEA.
  User: wjcaozhi1314
  Date: 2016/1/11
  Time: 17:55
  To change this template use File | Settings | File Templates.
  音乐分享页面
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/pages/common/head-base-path.jsp" %>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">

    <title>乐享动 歌曲分享 ${mix.name}</title>

    <style>
        body, body * {
            padding: 0px;
            margin: 0px;
            color: #FFFFFF;
        }

        body {
            background-image: url("/static/mix/imgs/BG@2x.png");
            background-repeat: no-repeat;
            background-origin: border-box;
            /*background-size: 120%;*/
            background-size:cover;
            margin-bottom: -150px;
            background-attachment:fixed;
        }

        .audioImg, .audioImgContent, .audioImgTropsch {
            position: absolute;
            -webkit-border-radius: 95px;
            -moz-border-radius: 95px;
            border-radius: 95px;
            background-repeat: no-repeat;
            -webkit-background-size: 100% 100%;
            background-size: 100% 100%;
        }

        .audioImgTropsch {
            width: 279px;
            height: 279px;
            top: 10%;
            background-image: url("/static/mix/imgs/pictoumin.png");
            margin: 0px;
        }

        .audioImg {
            z-index: 2;
            background-image: url("${mix.albumUrl}");
            width: 260px;
            height: 260px;
            border-radius: 180px;
        }

        .audioImgContent {
            z-index: 3;
        }

        .audioImgContent img {
            width: 85px;
            height: 85px;
            position: absolute;
        }

        .bottomNav {
            position: fixed;
            height: 60px;
            background-color: #242424;
            bottom: 0px;
            z-index: 9999;
        }

        .fitmixImg {
            width: 40px;
            height: 40px;
            position: absolute;
            top: 10px;
            left: 20px;
        }

        .fitmixTextDiv {
            position: absolute;
            left: 80px;
            height: 60px;
            color: #ffffff;
        }

        .fitmixTitle {
            margin-top: 9px;
            font-size: 16px;
        }

        .fitmixTitleMin {
            /*margin-top: 4px;*/
            font-size: 14px;
        }

        .buttonText {
            position: absolute;
            width: 103px;
            height: 36px;
            -webkit-background-size: 100% 100%;
            background-size: 100% 100%;
            right: 20px;
            top: 15px;
            text-align: center;
            line-height: 36px;
            border-radius: 3px;
            background-color: #FFB10E;
            color: #FFFFFF;
        }

        a {
            color: #000;
            text-decoration: none;
        }

        .audioScheduleUl {
            position: absolute;
            width: 90%;
            list-style-type: none;
            height: 20px;
            top: 50%;
            margin-top: 86px;;
            left: 5%;
            /*margin-left: -140px;*/
        }

        .audioScheduleUl li {
            float: left;
        }

        .audioScheduleLeft, .audioScheduleRight {
            width: 40px;
            height: inherit;
            width: 22%;
        }

        .audioScheduleLeft {
            text-align: left
        }

        .audioScheduleRight {
            text-align: right
        }

        .audioScheduleLi {
            height: 2px;
            background-color: #000000;
            margin-top: 9px;
            width: 56%;
        }

        .audioScheduleLi p {
            width: 1%;
            height: inherit;
            background-color: #FF8B22;
        }

        .playerDiv {
            position: absolute;
            /*height: 30px;*/
            top: 70%;
            color: #FFFFFF;
        }

        .playerDiv div {
            position: absolute;
        }

        .pLeft {
            width: 85%;
        }

        .pRight {
            text-align: right;
            width: inherit;
        }

        .mixText {
            /*height: 90px;*/
            width: 90%;
            position: absolute;
            left: 5%;
            top: 78%;
            font-size: 12px;
            color: #c9c8d2;
            margin-bottom: 100px;
            padding-bottom: 60px;
        }
    </style>

</head>
<body>

<div id="shareBig" class="audioImgTropsch">
    <div class="audioImg"></div>
    <div class="audioImgContent">
        <img src="/static/mix/imgs/btn_bofa3.png"/>
    </div>
</div>


<div class="bottomNav">

    <img class="fitmixImg" src="/imgs/mixShare/FTIMIX@2x.png"/>

    <div class="fitmixTextDiv">
        <p class="fitmixTitle">乐享动APP</p>

        <p class="fitmixTitleMin">好玩的运动APP</p>
    </div>
    <a href="http://a.app.qq.com/o/simple.jsp?pkgname=com.fitmix.sdk">
        <div class="buttonText">
            开始运动
        </div>
    </a>
</div>

<ul class="audioScheduleUl">
    <li class="audioScheduleLeft">00:00:00</li>
    <li class="audioScheduleLi">
        <p></p>
    </li>
    <li class="audioScheduleRight">00:00:00</li>
</ul>

<div class="playerDiv">
    <div class="pLeft">
        <p>${mix.name}</p>
        <%--<p>${mix.mixAuthor.name}</p>--%>
    </div>
    <div class="pRight">
        节拍${mix.bpm}
    </div>
</div>

<div class="mixText">${mix.introduce}</div>

<audio id="audio" src="${mix.url}"></audio>

<script src="/static/jquery-1.11.2.min.js"></script>
<script src="/static/util/delete.js"></script>
<script src="/static/jquery.rotate.min.js"></script>
<script id="endScript">
    ///计算两个整数的百分比值
    function GetPercent(num, total) {
        num = parseFloat(num);
        total = parseFloat(total);
        if (isNaN(num) || isNaN(total)) {
            return "-";
        }
        return total <= 0 ? "0%" : (Math.round(num / total * 10000) / 100.00 + "%");
    }
    //秒转 时分秒
    function formatSeconds(value) {
        var ss = parseInt(value);// 秒
        var mm = parseInt(ss / 60);// 分
        var hh = parseInt(mm / 60);// 小时
        mm = mm - hh * 60;
        ss = ss - (mm + hh * 60) * 60;
        var result = fromatTime(hh) + ":" + fromatTime(mm) + ":" + fromatTime(ss);

        return result;
    }

    function fromatTime(number) {
        if (number < 10) {
            return "0" + number;
        } else {
            return number;
        }
    }
    var angle = 0;
    var time = 100;
    var rotation = function (){
        angle += 3;
        $(".audioImg").rotate({
            animateTo:angle+3,
            duration:time
        });

    }
    var rotationTimeOut = null;

    $(function () {
        $(".audioImgTropsch").css("left", ($(window).width() - $(".audioImgTropsch").width()) / 2);
        $(".audioImg").css("left", ($(".audioImgTropsch").width() - $(".audioImg").width()) / 2);
        $(".audioImg").css("top", ($(".audioImgTropsch").height() - $(".audioImg").height()) / 2);
        $(".audioImgContent img").css("left", ($(".audioImgTropsch").width() - $(".audioImgContent img").width()) / 2);
        $(".audioImgContent img").css("top", ($(".audioImgTropsch").height() - $(".audioImgContent img").height()) / 1.9);


        $(".playerDiv").css({
            width: $(window).width() * 0.9,
            left: $(window).width() * 0.05
        });

        /*
         * encodeURI()加密
         * decodeURIComponent()解密
         * */
        //var parameter = location.href.split("&");
        $(".gName").text(decodeURIComponent("${mix.name}"));//decodeURIComponent(parameter[1].split("=")[1])
        $(".gsName").text(decodeURIComponent("${mix.author}"));//decodeURIComponent(parameter[2].split("=")[1])
        $(".audioImg").css("backgroundImage", decodeURIComponent("${mix.albumUrl}"));//parameter[3].split("=")[1]

        $(".bottomNav").css("width", $(window).width());

        var audio = document.getElementById("audio");

        audio.addEventListener("playing", function () {
            $(".audioImgContent img").attr("src", "/static/mix/imgs/btn_zhantin3.png");
            $(".audioScheduleRight").text(formatSeconds(audio.duration));
            rotationTimeOut = setInterval(rotation,time);
        });
        audio.addEventListener("pause", function () {
            $(".audioImgContent img").attr("src", "/static/mix/imgs/btn_bofa3.png");
            $(".audioScheduleRight").text(formatSeconds(audio.duration));
            clearInterval(rotationTimeOut);
            rotationTimeOut = null;
        });
        audio.addEventListener("canplay", function () {
            $(".audioScheduleRight").text(formatSeconds(audio.duration));
        });

        $("#audio").attr("src", decodeURIComponent("${mix.url}"));//parameter[0].split("=")[1]


        audio.addEventListener("timeupdate", function () {
            /*
             * currentTime 设置或返回音频中的当前播放位置（以秒计）。
             * duration 返回音频的长度（以秒计）。
             * */
            //计算百分比
            $(".audioScheduleLi p").css("width", GetPercent(audio.currentTime, audio.duration));
            //当前播放时间
            $(".audioScheduleLeft").text(formatSeconds(audio.currentTime));
        });

        $(".audioImgContent img").click(function () {
            if ($("#audio").attr("src") == "") {//这个判断已经废弃没有用了 因为在页面加载的过程中 就已经给audio设置src了 此处应该直接跳转到else中进行处理
                $("#audio").attr("src", decodeURIComponent("${mix.url}"));//parameter[0].split("=")[1]
            } else {
                //判断音频是否正在播放
                if (!audio.paused) {
                    audio.pause();
                } else {
                    audio.play();
//                    rotation();
                }
            }
        });


        //屏蔽广告
        deleteAdvertising("shareBig","endScript");

        var top = $(".playerDiv").css("top");
        top = top.substring(0, top.length - 2);
        top = parseInt(top) + parseInt($(".pLeft").height()) + 10;
        $(".mixText").css("top", top + "px");
    });
</script>

</body>
</html>
