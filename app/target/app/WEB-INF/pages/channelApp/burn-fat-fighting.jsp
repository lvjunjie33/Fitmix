<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016-02-29
  Time: 11:03
  To change this template use File | Settings | File Templates.
  运动分享 新风格
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ include file="/WEB-INF/pages/common/head-base-path.jsp" %>
<html>
<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>

    <title>乐享动 燃脂战场</title>
    <script src="/static/mobile/mobile-util.js"></script>
    <link href="//cdn.bootcss.com/minireset.css/0.0.2/minireset.min.css" rel="stylesheet">
    <link href="/static/channelApp/css/burn-fat-fighting.css" rel="stylesheet">

</head>

<body>

<div class="container">
    <div class="fighting-wrapper">
        <div class="fighting-item">
            <div class="bg-wrapper bg0">
                <img src="/static/channelApp/images/burnFatFighting/bg-1.jpeg">
                <div class="down-arrow">
                    <img src="/static/channelApp/images/burnFatFighting/down_arrow.png">
                </div>
            </div>
        </div>
        <%--<div class="fighting-item">--%>
            <%--<div class="bg-wrapper bg1">--%>
                <%--<img src="/static/channelApp/images/burnFatFighting/1.jpg">--%>
                <%--<div class="down-arrow">--%>
                    <%--<img src="/static/channelApp/images/burnFatFighting/down_arrow.png">--%>
                <%--</div>--%>
            <%--</div>--%>
        <%--</div>--%>
        <%--<div class="fighting-item">--%>
            <%--<div class="bg-wrapper bg2">--%>
                <%--<img src="/static/channelApp/images/burnFatFighting/3.jpg">--%>
                <%--<div class="down-arrow">--%>
                    <%--<img src="/static/channelApp/images/burnFatFighting/down_arrow.png">--%>
                <%--</div>--%>
            <%--</div>--%>
        <%--</div>--%>
        <%--<div class="fighting-item">--%>
            <%--<div class="bg-wrapper bg3">--%>
                <%--<img src="/static/channelApp/images/burnFatFighting/2.jpg">--%>
                <%--<div class="down-arrow">--%>
                    <%--<img src="/static/channelApp/images/burnFatFighting/down_arrow.png">--%>
                <%--</div>--%>
            <%--</div>--%>
        <%--</div>--%>
        <div class="fighting-item">
            <div class="bg-wrapper bg4">
                <img src="/static/channelApp/images/burnFatFighting/4.jpeg">
                <div class="down-arrow">
                    <img src="/static/channelApp/images/burnFatFighting/down_arrow.png">
                </div>
            </div>
        </div>
        <div class="fighting-item">
            <div class="video-wrapper">
                <div class="video-poster">
                    <img src="/static/channelApp/images/burnFatFighting/video-poster.png" />
                    <video id="video" preload="auto" controls>
                        <source src="http://yyssb.ifitmix.com/2014/92b35457ccea4a61ba33993eaabfbaf7.mp4" type="video/mp4" link-a = "fighting.mp4">
                    </video>
                    <div class="video-desc">
                        <img src="/static/channelApp/images/burnFatFighting/video-desc.png"/>
                    </div>
                </div>
                <div class="skip-video">
                    <img src="/static/channelApp/images/burnFatFighting/down_arrow.png" />
                </div>
            </div>
        </div>
        <div class="fighting-item">
            <div class="ready-wrapper">
                <div class="bg-3">
                    <img src="/static/channelApp/images/burnFatFighting/bg-3.png" />
                </div>
                <div class="go">
                    <img src="/static/channelApp/images/burnFatFighting/go.png" />
                </div>
            </div>
        </div>
        <div class="fighting-item">
            <div class="content-wrapper">
                <div class="hifi-box">
                    <div class="title">
                        <img src="/static/channelApp/images/burnFatFighting/title.png" />
                    </div>
                    <div class="time">
                        <span class="audioScheduleLeft">00:00:00</span>
                        <span class="audioScheduleRight">00:00:00</span>
                    </div>
                    <ul class="audioScheduleUl">
                        <li class="audioScheduleLi">
                            <p></p>
                            <span id="drag"></span>
                        </li>
                    </ul>
                    <div class="controller">
                        <img src="/static/channelApp/images/burnFatFighting/play.png" />
                    </div>
                    <div class="click">
                        <img src="/static/channelApp/images/burnFatFighting/click.png" />
                    </div>
                    <audio id="audio" src="http://yyssb.ifitmix.com/2014/154099dafe334920ab1110b94699cc9d.mp3" link-a = "FR_0312.mp3"></audio>
                </div>
                <div class="img-box">
                    <div class="bg-4">
                        <img src="/static/channelApp/images/burnFatFighting/fighting.png">
                    </div>
                    <div class="form">
                        <ul>
                            <li><span><img src="/static/channelApp/images/burnFatFighting/check.PNG" class="active"/><input checked type="radio" name="type" value="1"></span><span id="easy">${easy + 1}</span>A、小菜一碟，期待更难的挑战</li>
                            <li><span><img src="/static/channelApp/images/burnFatFighting/uncheck.PNG"/><input type="radio" name="type" value="2"></span><span id="middle">${middle}</span>B、爽！血脉喷张</li>
                            <li><span><img src="/static/channelApp/images/burnFatFighting/uncheck.PNG"/><input type="radio" name="type" value="3"></span><span id="hard">${hard}</span>C、捡回一条命，真是死里逃生！</li>
                        </ul>
                        <div class="submit">
                            <img src="/static/channelApp/images/burnFatFighting/submit.png" />
                        </div>
                    </div>
                </div>
                <div class="share">
                    <img src="/static/channelApp/images/burnFatFighting/share.png"/>
                </div>
            </div>
        </div>
    </div>

</div>

<div class="bgm">
    <img src="/static/channelApp/images/burnFatFighting/bgm-play.png">
</div>

<audio id="bgmusic" preload="preload">
    <source src="/static/channelApp/images/burnFatFighting/bgm.m4a" type="audio/mpeg">
    <source src="/static/channelApp/images/burnFatFighting/bgm.ogg" type="audio/ogg">
</audio>

<script src="/static/jquery-1.11.2.min.js"></script>
<script src="/static/util/delete.js"></script>
<script src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>
<script src="/static/jquery.rotate.min.js"></script>

<script>
    var browser = {
        versions: function () {
            var u = navigator.userAgent,
                app = navigator.appVersion;
            return {
                trident: u.indexOf('Trident') > -1, //IE内核
                presto: u.indexOf('Presto') > -1, //opera内核
                webKit: u.indexOf('AppleWebKit') > -1, //苹果、谷歌内核
                gecko: u.indexOf('Gecko') > -1 && u.indexOf('KHTML') == -1, //火狐内核
                mobile: !!u.match(/AppleWebKit.*Mobile.*/), //是否为移动终端
                ios: !!u.match(/\(i[^;]+;( U;)? CPU.+Mac OS X/), //ios终端
                android: u.indexOf('Android') > -1 || u.indexOf('Linux') > -1, //android终端或者uc浏览器
                iPhone: u.indexOf('iPhone') > -1, //是否为iPhone或者QQHD浏览器
                iPad: u.indexOf('iPad') > -1, //是否iPad
                webApp: u.indexOf('Safari') == -1, //是否web应该程序，没有头部与底部
                weixin: u.indexOf('MicroMessenger') > -1, //是否微信 （2015-01-22新增）
                qq: u.match(/\sQQ/i) == " qq", //是否QQ
                ios9: app.indexOf('OS 9_') > -1, //是否是IOS9
                ios10: app.indexOf('OS 10_') > -1 //是否是IOS10
            };
        }()
    };
    $.fn.extend({
        animateCss: function (animationName) {
            var animationEnd = 'webkitAnimationEnd mozAnimationEnd MSAnimationEnd oanimationend animationend';
            this.addClass('animated ' + animationName).one(animationEnd, function() {
                var th = $(this);
                th.removeClass('animated ' + animationName);
//                setTimeout(function() {
//                    th.removeClass('animated ' + animationName);
//                    th.animateCss('animated ' + animationName);
//                }, 500)
            });
        }
    });
</script>
<script>
    var Media = document.getElementById('bgmusic');
    if(Media.networkState) {
        Media.src = "/static/channelApp/images/burnFatFighting/bgm.m4a";
    }

    // 背景音乐 转动
    var angle = 0;
    var time = 100;
    var rotationInterval    ;
    var rotation = function () {
        angle += 3;
        $(".bgm img").rotate({
            animateTo:angle+3,
            duration:time
        });
    }
    rotationInterval = setInterval(rotation, 100);
    $(".bgm img").on("click", function () {
        if (Media.paused) {
           Media.play();
            $(".bgm img").attr("src", "/static/channelApp/images/burnFatFighting/bgm-play.png");
            rotationInterval = setInterval(rotation, 100);
        } else {
            Media.pause();
            clearInterval(rotationInterval);
            $(".bgm img").attr("src", "/static/channelApp/images/burnFatFighting/bgm-pause.png");
        }
    });

    var shareOption = {
        title: '首款3D全景跑步音频训练  燃脂战场', // 分享标题
        desc: '', // 分享描述
        link: 'http://app.igeekery.com/channel-app/burn-fat-fighting.htm', // 分享链接
        imgUrl: 'http://app.igeekery.com/static/user-run-share/imgs/logo.png', // 分享图标
        type: '', // 分享类型,music、video或link，不填默认为link
        dataUrl: '', // 如果type是music或video，则要提供数据链接，默认为空,
        defaultValue: 0,
        countFlag: 0,
        success: function () {
            // 用户确认分享后执行的回调函数
            $.ajax({
                    url: "/channel-app/get-signature.json",
                    type: "get",
                    dataType: "json",
                    async: false,
                    data: {
                        url: window.location.href.split('#')[0],
                        type: shareOption.defaultValue == 0 ? 0 : $("input[name=type]:checked").val(),
                        random: '${random}',
                        shareCount: 1
                    },
                    success: function () {

                    }
            })
        },
        cancel: function () {
            // 用户取消分享后执行的回调函数
        }
    };
    
    function createShare() {
        if (shareOption.countFlag != 0) {
            // 弹出遮罩层
            $(".share").css("display", "block");
            $(".share").addClass("fade");
        }
        if(shareOption.countFlag == 0) {
            Media.play();
        }
        $.ajax({
            url: "/channel-app/get-signature.json",
            type: "get",
            dataType: "json",
            async: false,
            data: {
                url: window.location.href.split('#')[0],
                type: shareOption.defaultValue == 0 ? 0 : $("input[name=type]:checked").val(),
                random: '${random}'
            },
            success: function (data) {
                wx.config({
                    debug: false, // 开启调试模式,调用的所有api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端打开，参数信息会通过log打出，仅在pc端时才会打印。
                    appId: data.appid, // 必填，公众号的唯一标识
                    timestamp: parseInt(data.timestamp), // 必填，生成签名的时间戳
                    nonceStr: data.nonceStr, // 必填，生成签名的随机串
                    signature: data.signature,// 必填，签名，见附录1
                    jsApiList: ['onMenuShareTimeline', 'onMenuShareAppMessage', 'onMenuShareQQ',
                        'onMenuShareWeibo', 'onMenuShareQZone'] // 必填，需要使用的JS接口列表，所有JS接口列表见附录2
                });

                wx.ready(function () {
                    if(shareOption.countFlag == 0) {
                        Media.play();
                    }
                    wx.onMenuShareTimeline({
                        title: shareOption.title, // 分享标题
                        link: shareOption.link, // 分享链接
                        imgUrl: shareOption.imgUrl, // 分享图标
                        success: shareOption.success,
                        cancel: function () {
                            // 用户取消分享后执行的回调函数
                        }
                    });
                    wx.onMenuShareAppMessage({
                        title: shareOption.title,
                        desc: shareOption.desc,
                        link: shareOption.link,
                        imgUrl: shareOption.imgUrl,
                        type: '', // 分享类型,music、video或link，不填默认为link
                        dataUrl: '', // 如果type是music或video，则要提供数据链接，默认为空
                        success: shareOption.success,
                        cancel: function () {}
                    });
                    wx.onMenuShareQQ({
                        title: shareOption.title,
                        desc: shareOption.desc,
                        link: shareOption.link,
                        imgUrl: shareOption.imgUrl,
                        success: shareOption.success,
                        cancel: function () {}
                    });
                    wx.onMenuShareWeibo({
                        title: shareOption.title,
                        desc: shareOption.desc,
                        link: shareOption.link,
                        imgUrl: shareOption.imgUrl,
                        success: shareOption.success,
                        cancel: function () {}
                    });


                });

                //分享朋友圈
                var share = function() {
                    WeixinJSBridge.invoke('shareTimeline',{
                        'appid': data.appid, // 公众号appID
                        'title': shareOption.title, // 分享标题
                        'desc': shareOption.desc, // 描述
                        'link': shareOption.link,// 点击后连接地址
                        'img_url': shareOption.imgUrl // 分享到朋友圈的缩略图url
                    },function(res){
                        //alert(res.err_msg);
                    });
                }
                if (typeof WeixinJSBridge == "object" && typeof WeixinJSBridge.invoke == "function") {
                    share();
                } else {
                    if (document.addEventListener) {
                        document.addEventListener("WeixinJSBridgeReady", share, false);
                    } else if (document.attachEvent) {
                        document.attachEvent("WeixinJSBridgeReady", share);
                        document.attachEvent("onWeixinJSBridgeReady", share);
                    }
                }

            }
        })
    }
    
    $(window).load(function () {
        createShare();
        setInterval(function() {
            $(".down-arrow img").animateCss("fadeOutDown");
            $(".skip-video img").animateCss("fadeOutDown");
        }, 500);

        var theWindow = $(window);
        var $bg = $('.bg-wrapper > img');
        var bgWidth = 1040;
        var bgHeight = 1314;
        var currentWidth = 0;

        function resizeBg() {
            if(!browser.versions.mobile) {
                $(".fighting-item > div:first-child").addClass("flex-box");
                $bg.css({
                    position: "relative",
                    height: "100%"
                });
                $(".fighting-item > div:first-child").css({
//                    height: $bg.height() + "px",
                    width: $bg.width() + "px",
                    margin: "0 auto"
                });
                $(".skip-video").css("display", "none");
                $(".bg-3 img").css({
                    paddingTop: "100px",
                    width: "80%",
                    height: "auto"
                });
            } else {
                $("html, body").css({
                    overflow: "hidden"
                });
                $bg.addClass('bg-height');
                currentWidth = (1040 / 1314) * $bg.height();
                var offsetLeft = (theWindow.width() - currentWidth) / 2;
                $bg.css({
                    maxWidth: currentWidth + "px",
                    marginLeft: offsetLeft + "px"
                });
                //设置 fighting-item 宽高
                $(".fighting-item").css({
                    height: $bg.height() + "px",
                    width: theWindow.width() + "px"
                });
                $(".fighting-item > div:first-child").css({
                    height: $bg.height() + "px",
                    width: theWindow.width() + "px"
                });
                $(".video-poster").css({
                    height: $bg.height() + "px",
                    width: theWindow.width() + "px"
                });
            }



//            } else if (theWindow.width() / theWindow.height() < aspectRatio) {
//                alert(1);
//                $bg.removeClass('bg-width').addClass('bg-height');
//            } else {
//                $bg.removeClass('bg-height').addClass('bg-height');
//            }
        }

        theWindow.resize(resizeBg).trigger('resize');

        $(".video-poster img").on("click", function () {
            Media.pause();
           $(this).css({
               display: "none"
           });
           var $video = $("#video");
           $video.css({
               width: theWindow.width() + "px",
               opacity: 1
           });
           document.getElementById("video").play();
        });

        // 音乐

        ///计算两个整数的百分比值
        function GetPercent(num, total) {
            num = parseFloat(num);
            total = parseFloat(total);
            if (isNaN(num) || isNaN(total)) {
                return "-";
            }
            return total <= 0 ? "0%" : (Math.round(num / total * 10000) / 100.00 + "%");
        }

        function fromatTime(number) {
            if (number < 10) {
                return "0" + number;
            } else {
                return number;
            }
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
        var audio = document.getElementById("audio");

        audio.addEventListener("playing", function () {
            Media.pause();
            $(".controller img").attr("src", "/static/channelApp/images/burnFatFighting/pause.png");
            $(".audioScheduleRight").text(formatSeconds(audio.duration));
        });
        audio.addEventListener("pause", function () {
            $(".controller img").attr("src", "/static/channelApp/images/burnFatFighting/play.png");
            $(".audioScheduleRight").text(formatSeconds(audio.duration));
        });
        audio.addEventListener("canplay", function () {
            $(".audioScheduleRight").text(formatSeconds(audio.duration));
        });
        audio.addEventListener("timeupdate", function () {
            /*
             * currentTime 设置或返回音频中的当前播放位置（以秒计）。
             * duration 返回音频的长度（以秒计）。
             * */
            //计算百分比
            $(".audioScheduleLi span").css("left", GetPercent(audio.currentTime, audio.duration));
            $(".audioScheduleLi p").css("width", GetPercent(audio.currentTime, audio.duration));
            //当前播放时间
            $(".audioScheduleLeft").text(formatSeconds(audio.currentTime));
        });

        //进度条
        var startX, x, aboveX = 0, startLeft = 0; //触摸时的坐标 //滑动的距离  //设一个全局变量记录上一次内部块滑动的位置
        var drag = document.getElementById("drag");
        //1拖动监听touch事件
        function addListenTouch() {
            document.getElementById("drag").addEventListener("touchstart", touchStart, false);
            document.getElementById("drag").addEventListener("touchmove", touchMove, false);
            document.getElementById("drag").addEventListener("touchend", touchEnd, false);
            document.getElementById("drag").addEventListener("mousedown", touchStart, false);
            document.getElementById("drag").addEventListener("mousemove", touchMove, false);
            document.getElementById("drag").addEventListener("mouseup", touchEnd, false);
        }

//touchstart,touchmove,touchend事件函数
        function touchStart(e) {
            e.preventDefault();
            audio.pause();
            var touch = e.touches[0];
            startX = touch.pageX;
        }
        function touchMove(e) { //滑动
            e.preventDefault();
            var touch = e.touches[0];
            x = touch.pageX - startX; //滑动的距离
            //drag.style.webkitTransform = 'translate(' + 0+ 'px, ' + y + 'px)';  //也可以用css3的方式
            console.log("width = " + $(".audioScheduleLi").css("width"));
            console.log("startX" + startX);
            console.log(aboveX + x );
            var width = $(".audioScheduleLi").css("width");
            width = width.replace("px", "");
            if ((aboveX + x) > parseInt(width)) {
                drag.style.left = parseInt(width) + "px"; //
            } else if((aboveX + x) <= 0) {
                drag.style.left = 0 + "px";
            } else {
                drag.style.left = aboveX + x + "px";
            }

        }
        function touchEnd(e) { //手指离开屏幕
            e.preventDefault();
            aboveX = parseInt(drag.style.left);
            var dragPaddingLeft = drag.style.left;
            var change = dragPaddingLeft.replace("px", "");
            numDragpaddingLeft = parseInt(change);
//            console.log(numDragpaddingLeft);
            var width = $(".audioScheduleLi").css("width");
            width = parseInt(width.replace("px", ""));
            var currentTime = (numDragpaddingLeft / width) * audio.duration;//30是拖动圆圈的长度，减掉是为了让歌曲结束的时候不会跑到window以外
            audio.currentTime = currentTime;
            audio.play();
        }

        $(".controller img").click(function () {
            addListenTouch();
            //判断音频是否正在播放
            if (!audio.paused) {
                audio.pause();
            } else {
                audio.play();
            }
        });

        $("input[name=type]").on("click", function () {
            $(".active").attr("src", "/static/channelApp/images/burnFatFighting/uncheck.PNG");
            if(shareOption.countFlag != 0) {
                $(".active").parent("span").next("span").text(parseInt($(".active").parent("span").next("span").text()) - 1);
            }
            $(".active").removeClass("active");
            $(this).prev().attr("src", "/static/channelApp/images/burnFatFighting/check.PNG").addClass("active");
            $(".active").parent("span").next("span").text(parseInt($(".active").parent("span").next("span").text()) + 1);
            shareOption.countFlag = 1;
        });
        $(".submit img").on("click", function () {
            if($("input[name=type]:checked").val() == 1) {
                shareOption.title = "我刚从燃脂战场归来，期待更难的挑战";
            } else if($("input[name=type]:checked").val() == 2) {
                shareOption.title = "我刚从燃脂战场归来，感到爽歪歪，血脉喷张";
            } else {
                shareOption.title = "我刚从燃脂战场归来，捡回一条命，真是死里逃生！";
            }
            shareOption.defaultValue = 1;
            shareOption.countFlag = 1;
            createShare();
        });

        $.fn.scrollView = function (time) {
            return this.each(function () {
                $('html, body').animate({
                    scrollTop: $(this).offset().top
                }, time);
            });
        };

        $(".bg0").on("click", function () {
            $(".bg4").scrollView(1000);
        });
//        $(".bg1").on("click", function () {
//            $(".bg2").scrollView(1000);
//        });
//        $(".bg2").on("click", function () {
//            $(".bg3").scrollView(1000);
//        });
//        $(".bg3").on("click", function () {
//            $(".bg4").scrollView(1000);
//        });
        $(".bg4").on("click", function () {
            $(".video-wrapper").scrollView(1000);
        });
        $(".skip-video img").on("click", function () {
            document.getElementById("video").pause();
            $(".ready-wrapper").scrollView(1000);
        });
        $(".go img").on("click", function () {
            $(".content-wrapper").scrollView(1000);
        });

        document.body.addEventListener('touchmove', function (event) {
            event.preventDefault();
        }, false);

        $("body").scrollView(1);

        $(".share").on("click", function(){
            $(this).removeClass("fade");
            $(this).css("display", "none");
        });
    });

</script>

</body>