<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2015/12/13
  Time: 16:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/WEB-INF/pages/common/head-base-path.jsp" %>
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
        }
    </style>
</head>
<body>
    <h2>${user.name}</h2>
    <dl>
        <dt> 注意：</dt>
        <dd> 1、领取参赛物品 请携带好<strong>身份证</strong></dd>
        <dd> 2、领取参赛物品 我们会<strong>短信通知您</strong></dd>
        <dd> 3、衣服尺码调整 可以在领取 参赛物品 现场调整</dd>
    </dl>
    <strong>
        收不到短信的用户：
    </strong>
    <p>
        拨打营业厅电话是否屏蔽了 10 开头的短信
    </p>


    <table class="table table-bordered">
        <tr>
            <td>名称：${user.name}</td>
        </tr>
        <tr>
            <td>性别：${user.gender}</td>
        </tr>
        <tr>
            <td>组别：${user.type}</td>
        </tr>
        <tr>
            <td>邮箱：${user.email}</td>
        </tr>
        <tr>
            <td>手机号：${user.mobile}</td>
        </tr>
        <tr>
            <td>身份证：${user.userCard}</td>
        </tr>
        <tr>
            <td>衣服尺码：${user.clothesSize}</td>
        </tr>

        <tr>
            <td>紧急姓名：${user.emergencyUserName}</td>
        </tr>
        <tr>
            <td>紧急电话：${user.emergencyUserMobile}</td>
        </tr>
        <tr>
            <td>报名时间：${user.addTimeStr}</td>
        </tr>
        <tr>
            <td>支付时间：${user.prepayTimeStr}</td>
        </tr>
        <tr>
            <td>活动时间：${user.activityTime}</td>
        </tr>
        <tr>
            <td>状态：
                <c:choose>
                    <c:when test='${user.status eq "unactivated"}'>
                        <label style="color: red;">未付款</label>
                        &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;
                        <!-- Indicates a successful or positive action -->
                        <%--<a href="javascript:" isNoneStr>我已支付</a>--%>
                    </c:when>
                    <c:when test='${user.status eq "normal"}'>
                        已付款
                    </c:when>
                    <c:otherwise>
                        ${user.status}
                    </c:otherwise>
                </c:choose>
            </td>
        </tr>
        <tr>
            <td>
                <div id="noneStr" style="display: none;">
                    <input type="text" class="form-control" id="noneStrValue"  placeholder="支付凭证  商户订单号">
                    <br/>
                    <button type="button" class="btn btn-info form-control" checkNoneStr>核实</button>
                </div>
            </td>
        </tr>
        <tr>
            <td>
                <%--<c:choose>--%>
                    <%--<c:when test='${user.status eq "unactivated"}'>--%>
                        <%--<!-- Contextual button for informational alert messages -->--%>
                        <%--<a href="activity/20151201/pay/main.htm?id=${user.id}" class="btn btn-info form-control">微信支付</a>--%>
                    <%--</c:when>--%>
                <%--</c:choose>--%>
            </td>
        </tr>
    </table>
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
        $("[isNoneStr]").click(function(){

            if ($("#noneStr").css("display") == "none") {
                $("#noneStr").css("display", "block");
            }
            else {
                $("#noneStr").css("display", "none");
            }
        });
    });

    $(function(){
        $("[checkNoneStr]").click(function(){
            var th = $(this);


            var userCard = "${user.userCard}";
            var noneStrValue = $.trim($("#noneStrValue").val());

            if (noneStrValue.length != 20) {
                alert("格式不正确");
                return;
            }
            th.text("正在合适...").attr("disabled", "true");

            $.post("/activity/20151201/checkNoneStr.json", {"userCard": userCard, "noneStr": noneStrValue}, function(result){
                if (result.code == 0) {
                    if (result.result) {
                        alert("合适正确");
                        location.reload();
                    }
                    else {
                        alert("检测您未付款，检查单号后再试。")
                        th.text("核实").removeAttr("disabled");
                    }
                }
            });
        });
    });
</script>