<%--
  Created by IntelliJ IDEA.
  User: Sin
  Date: 2016/1/15
  Time: 14:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/WEB-INF/pages/common/head-base-path.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>悦跑 感谢一路有你 年度分享交生盛会 报名</title>
    <!-- 禁止缩放 -->

    <!--twitter meta-->
    <meta name="twitter:card" content="summary_large_image">
    <meta name="twitter:creator" content="@MAKA">
    <meta name="twitter:title" content="感谢一路有你，悦跑年会开始报名~">
    <meta name="twitter:description" content="悦跑年度盛会 报名">
    <meta name="twitter:image" content="http://img1.maka.im/cover/97399912056990958d132a6.02210370.jpg@159-82-736-736a">

    <title>感谢一路有你，悦跑年会开始报名~</title>
    <meta name="Description" content="悦跑年度盛会 报名">

    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <link rel="stylesheet" href="/static/bootstrap/bootstrap.min.css">
    <script src="/static/jquery-1.11.2.min.js"></script>
    <script type="text/javascript" src="/static/jquery.form.js"></script>


    <meta property="og:type" content="website">
    <meta property="og:title" content="感谢一路有你，悦跑年会开始报名~">
    <meta property="og:image" content="http://img1.maka.im/cover/97399912056990958d132a6.02210370.jpg@159-82-736-736a">
    <meta property="og:description" content="悦跑邀请你参加年度盛会……">



    <style type="text/css">

        body, h1, h2, h3, h4, h5, h6, .uk-navbar-nav > li > a { font-family:  "Microsoft Yahei","微软雅黑","Proxima Nova", "Helvetica Neue", Helvetica, Arial, sans-serif; }

        body{
            /*margin: 0px 15px 20px 15px;*/
            /*text-align: center;*/
            max-width: 500px;
            margin: 0px auto;
            text-align: center;
        }

        .image{
            width: 100%;
        }

        #panel-form{
            margin: 10px;
        }

        #desc{
            text-align: left;
            margin: 10px;
            line-height: 2;;
        }
    </style>
</head>
<body>

<div style="margin: 10px;">
    <table class="table table-bordered">
        <tr>
            <td><h3>${user.name}</h3></td>
        </tr>
        <tr>
            <td>性别：${user.gender}</td>
        </tr>
        <tr>
            <td>性别：${user.age}</td>
        </tr>
        <tr>
            <td>性别：${user.mobile}</td>
        </tr>
        <tr>
            <td>状态：
                <c:choose>
                    <c:when test="${user.status eq 'normal'}">
                        成功
                    </c:when>
                    <c:when test="${user.status eq 'unactivated'}">
                        未支付
                    </c:when>
                    <c:otherwise>
                        ${user.status}
                    </c:otherwise>
                </c:choose>

            </td>
        </tr>
    </table>
</div>

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