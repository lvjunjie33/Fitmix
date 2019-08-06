<%--
  Created by IntelliJ IDEA.
  User: Cheri
  Date: 2015/9/10
  Time: 22:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/head-base-path.jsp" %>

<!DOCTYPE html>
<html>

<head>
  <meta charset="utf-8" />
  <!-- 禁止缩放 -->
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">

  <title>2015南山半程马拉松创客百团摇滚跑招募邀请函</title>
  <link rel="icon" href="../image/title%20icon.ico" />

  <link rel="stylesheet" type="text/css" href="/static/bootstrap/bootstrap.min.css" />
  <style type="text/css">

    body, h1, h2, h3, h4, h5, h6, .uk-navbar-nav > li > a { font-family:  "微软雅黑", "Microsoft Yahei","Proxima Nova", "Helvetica Neue", Helvetica, Arial, sans-serif; }
    body,body *,* {
      padding: 0px;
      margin: 0px;
    }
    /*p {*/
      /*padding-left: 5%;*/
      /*padding-right: 5%;*/
    /*}*/
    .row > div {
      margin-bottom: 30px;
      overflow: hidden;
      padding: 0px;
      margin: 0px;
    }
    .imgY {
      width: inherit;height: inherit;
    }
    .ptextN {
      padding-right: 10%;
    }
    .ptext {
      text-align: right;
    }


    body，html{width: 100%;
      height: 100%;}

    .img{
      width: 100%;
      height: 100%;
    }

    .aaa{
      display: -webkit-box;
      -webkit-box-orient: horizontal;
      -webkit-box-pack: center;
      display: -moz-box;
      -moz-box-orient: horizontal;
      -moz-box-pack: center;
      -o-box-orient: horizontal;
      -o-box-pack: center;
      -ms-box-orient: horizontal;
      -ms-box-pack: center;
      box-orient: horizontal;
      box-pack: center;
    }
  </style>
</head>

<body>
<audio src="http://yyssb.ifitmix.com/2014/60254a97097747e1bafae154b021b1b0.mp3" autoplay="autoplay" link-a = "The 1975 - Chocolate.mp3"></audio>
<div class="container"></div>
<div>


  <div style="position:relative;">
    <img src="/imgs/activity20150910/img/20150911_1.JPG" class="img"/>
    <a class="aaa" href="/activity/20150910/run/ream/add.htm"><img src="/imgs/activity20150910/img/20150911_5button.PNG" style="width: 50%;position: absolute;bottom: 12%;text-align: center;left: 25%;"></a>
  </div>

  <img src="/imgs/activity20150910/img/20150911_1.5.JPG" class="img">

  <div style="background-color: #FFFFFF;letter-spacing:1px;line-height:22px;font-size: 14px;padding: 0px 15px 0px 15px;text-align: left;">
    <p>联系方式：</p>
    <p><b>尹   &nbsp;丹:</b>&nbsp;0755-86721269&nbsp;&nbsp;13714486310</p>
    <p><b>陈桦茵:</b>&nbsp;0755-86721992&nbsp;&nbsp;13480110206</p>
    <p><b>传&nbsp;&nbsp;&nbsp;真:</b>&nbsp;0755-86313800</p>
    <p><b>邮&nbsp;&nbsp;&nbsp;箱:</b><a href="mailto:mls100@3nod.com.cn">&nbsp;mls100@3nod.com.cn</a></p>
    <p>活动官网：<a href="http://www.szns-marathon.com">&nbsp;2015 深圳南山半程马拉松</a></p>
    <p>主办单位：创客百团摇滚跑组委会</p>
    <p>指导单位：深圳市南山区人民政府、深圳市南山区文体局</p>
    <p>协办单位：三诺集团、蓝筹科技、深圳鲲鹏汇、南极圈、深圳湾创客创业社区、我爱方案网</p>
    <br/>
    <br/>
    <p class="ptext ptextN">三诺集团</p>
    <p class="ptext">二零一五年九月九日</p>
  </div>

  <img src="/imgs/activity20150910/img/20150911_2.JPG" name="img1" class="img" />


  <img src="/imgs/activity20150910/img/20150911_3.JPG" name="img1" class="img" />

  <img src="/imgs/activity20150910/img/20150911_3.5.JPG" name="img1" class="img" />

  <%-- userClick --%>
  <div style="position: relative;">
    <img src="/imgs/activity20150910/img/20150911_4.JPG" class="img">

    <a class="aaa"  href="/activity/20150910/run/ream/add.htm"><img src="/imgs/activity20150910/img/20150911_5button.PNG" style="width: 50%;position: absolute;bottom: 29%;text-align: center;left: 25%;"></a>
    <a class="aaa"  href="/activity/20150910/user/activity/login.htm"><img src="/imgs/activity20150910/img/20150911_index_query.PNG" style="width: 50%;position: absolute;bottom: 21%;text-align: center;left: 25%;"></a>
    <%--<input style="background: image('imgs/activity20150910/img/20150911_5button.PNG')" value="啊啊啊">--%>
  </div>

</div>

<script type="text/javascript" src="/static/jquery-1.11.2.min.js"></script>
<script type="text/javascript">
  var browser = {
    versions : function() {
      var u = navigator.userAgent, app = navigator.appVersion;
      return { // 移动终端浏览器版本信息
        trident : u.indexOf('Trident') > -1, // IE内核
        presto : u.indexOf('Presto') > -1, // opera内核
        webKit : u.indexOf('AppleWebKit') > -1, // 苹果、谷歌内核
        gecko : u.indexOf('Gecko') > -1 && u.indexOf('KHTML') == -1, // 火狐内核
        mobile : !!u.match(/AppleWebKit.*Mobile.*/), // 是否为移动终端
        ios : !!u.match(/\(i[^;]+;( U;)? CPU.+Mac OS X/), // ios终端
        android : u.indexOf('Android') > -1 || u.indexOf('Linux') > -1, // android终端或uc浏览器
        iPhone : u.indexOf('iPhone') > -1, // 是否为iPhone或者QQHD浏览器
        iPad : u.indexOf('iPad') > -1, // 是否iPad
        webApp : u.indexOf('Safari') == -1
        // 是否web应该程序，没有头部与底部
      };
    }(),
    language : (navigator.browserLanguage || navigator.language).toLowerCase()
  }

  $(function (){
    document.body.parentNode.style.overflowX = "hidden";//隐藏横向滚动条
    var wWidth = $(window).width();
    var wHeight = $(window).height();
    console.log("wWidth",wWidth);
    console.log("wHeight",wHeight);
    //$(".row > div > img").css("maxWidth",wWidth);
    if(browser.versions.mobile){
      $("img[name='img1']").addClass("imgY");
    }else{
      var ii = $("#iimg").css("width");
      $(".row > div").css({
        maxWidth:ii,
        position: "relative",
        left: (parseInt(wWidth) - parseInt(ii))/2+"px"
      });
    }
  });
</script>
</body>

</html>
