<%--
  Created by IntelliJ IDEA.
  User: Sin
  Date: 2016/2/17
  Time: 16:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/WEB-INF/pages/common/head-base-path.jsp" %>
<!DOCTYPE html>
<html>
<head>

    <meta charset="utf-8">
    <!-- 禁止缩放 -->
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <title>加菲猫 微信支付</title>

    <link rel="stylesheet" href="/static/bootstrap/bootstrap.min.css">
    <script src="/static/jquery-1.11.2.min.js"></script>
    <script type="text/javascript" src="/static/jquery.form.js"></script>

    <style>
        body, h1, h2, h3, h4, h5, h6, .uk-navbar-nav > li > a { font-family:  "Microsoft Yahei","微软雅黑","Proxima Nova", "Helvetica Neue", Helvetica, Arial, sans-serif; }

        body{
            max-width: 600px;
            margin: 10px auto 0px auto;

        }

        .img-with{
            width: 100%;
        }

        .textarea-none{
            border: 0px;
        }

        .btn {
            min-width: 60px;
            display: inline-block;
            overflow: visible;
            padding: 0 22px;
            height: 30px;
            line-height: 30px;
            vertical-align: middle;
            text-align: center;
            text-decoration: none;
            border-radius: 3px;
            -moz-border-radius: 3px;
            -webkit-border-radius: 3px;
            font-size: 14px;
            border-width: 1px;
            border-style: solid;
            cursor: pointer;
        }

        .btn_primary {
            background-color: #44b549;
            background-image: -moz-linear-gradient(top,#44b549 0,#44b549 100%);
            background-image: -webkit-gradient(linear,0 0,0 100%,from(#44b549),to(#44b549));
            background-image: -webkit-linear-gradient(top,#44b549 0,#44b549 100%);
            background-image: -o-linear-gradient(top,#44b549 0,#44b549 100%);
            background-image: linear-gradient(to bottom,#44b549 0,#44b549 100%);
            border-color: #44b549;
            color: #fff;
        }
    </style>
</head>
<body>
    <div style="text-align: center;position: fixed;top: 10%;left: 0px;right: 0px;">
        正在支付...
    </div>

    <div style="position: fixed;top: 60%;left: 0px;right: 0px;text-align: center;">
        <button type="button" class="btn btn_primary"  onclick="wxPay()">微信支付</button>
    </div>
    <script type="text/javascript">
        var ua = navigator.userAgent.toLowerCase();
        var isWeixin = ua.indexOf('micromessenger') != -1;
        var isAndroid = ua.indexOf('android') != -1;
        var isIos = (ua.indexOf('iphone') != -1) || (ua.indexOf('ipad') != -1);
        if (!isWeixin) {
            document.head.innerHTML = '<title>抱歉，出错了</title><meta charset="utf-8"><meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=0"><link rel="stylesheet" type="text/css" href="https://res.wx.qq.com/connect/zh_CN/htmledition/style/wap_err1a9853.css">';
            document.body.innerHTML = '<div class="page_msg"><div class="inner"><span class="msg_icon_wrp"><i class="icon80_smile"></i></span><div class="msg_content"><h4>请在微信客户端打开链接</h4></div></div></div>';
        }
    </script>


    <script type="text/javascript">

        function wxPay() {
            WeixinJSBridge.invoke('getBrandWCPayRequest',{
                "appId" : "${appId}",
                "timeStamp" : "${timeStamp}",
                "nonceStr" : "${nonceStr}",
                "package" : "${packageValue}",
                "signType" : "MD5",
                "paySign" : "${sign}"
            },function(res){
                if(res.err_msg == "get_brand_wcpay_request:ok"){
//						alert("微信支付成功!");
                    location.href = "${callbackUrl}";
                }else if(res.err_msg == "get_brand_wcpay_request:cancel"){
//				  alert("用户取消支付!");
                }else{
                    alert("支付失败!");
                }
            })
        }

        window.onload = function(){
            window.setTimeout(function(){
                wxPay();
            }, 500);
        };

        javascript:window.history.forward(1);
    </script>
</body>
</html>
