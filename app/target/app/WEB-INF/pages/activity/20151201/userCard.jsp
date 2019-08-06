<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2015/12/13
  Time: 17:59
  To change this template use File | Settings | File Templates.
--%>
<%@ include file="/WEB-INF/pages/common/head-base-path.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <!-- 禁止缩放 -->
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <title>第三届深圳迎新跑 2016年1月1号深圳湾运动公园</title>

    <link rel="stylesheet" href="/static/bootstrap/bootstrap.min.css">
    <script src="/static/jquery-1.11.2.min.js"></script>
    <script type="text/javascript" src="/static/jquery.form.js"></script>

    <style>

        body, h1, h2, h3, h4, h5, h6, .uk-navbar-nav > li > a { font-family:  "Microsoft Yahei","微软雅黑","Proxima Nova", "Helvetica Neue", Helvetica, Arial, sans-serif; }

        body{
            /*margin: 0px 15px 20px 15px;*/
            text-align: center;
            max-width: 500px;
            margin: 0px auto;
            padding: 10px;;
        }
    </style>
</head>
<body>
    <input type="text" class="form-control" id="userCard"  value="" placeholder="请输入您的身份证号">
    <br/>
    <!-- Contextual button for informational alert messages -->
    <button type="button" class="btn btn-info form-control" queryClick>查询</button>
</body>
</html>

<script type="text/javascript">
    // 对浏览器的UserAgent进行正则匹配，不含有微信独有标识的则为其他浏览器
    var useragent = navigator.userAgent;
    if (useragent.match(/MicroMessenger/i) != 'MicroMessenger') {
        // 这里警告框会阻塞当前页面继续加载
//        alert('已禁止本次访问：您必须使用微信内置浏览器访问本页面！');
        $("body").html('<label style="color: #60d295;font-size: 50px"> :-)</label> &nbsp;&nbsp;请用微信打开.');
        // 以下代码是用javascript强行关闭当前页面
//        var opened = window.open('about:blank', '_self');
//        opened.opener = null;
//        opened.close();
    }
</script>

<script>

    $(function(){
        $("[queryClick]").click(function(){
            var userCard = $.trim($("#userCard").val());
            if (userCard.length >= 15) {
                location.href = "/activity/20151201/info.htm?userCard=" + userCard;
            }
            else {
                alert("您的身份证号不正确");
            }
        });
    });

</script>