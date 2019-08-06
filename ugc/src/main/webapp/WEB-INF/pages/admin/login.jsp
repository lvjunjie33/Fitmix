<%--
  Created by IntelliJ IDEA.
  User: fenxio
  Date: 2016/8/23
  Time: 14:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../common/head-base-path.jsp"%>
<html>
<head>
    <title>UGC 登录</title>
    <base href="<%=basePath%>">
    <link rel="stylesheet/less" type="text/css" href="../../../css/common/main.less" />
    <script src="../../../static/less/less.min.js"></script>
    <link rel="stylesheet" type="text/css" href="../../../static/iconfont/iconfont.css" />
    <style>
        #footer {
            bottom: 0;
        }
    </style>
</head>
<body>

<div class="ugc-admin-bg"></div>

<div class="ugc-admin-container">
    <div class="ugc-admin-info-box">
        <p>乐享动FM公众平台</p>
        <p>百万DJ</p>
        <p>正在招募</p>
        <p>寻找热爱音乐的你</p>
    </div>
    <div class="ugc-admin-login-box">
        <p>我是电台使用<span>电台</span>登录</p>
        <form id="login-form">
            <div class="form-group">
                <input class="form-control" style="margin-top: 10px;" type="email" name="email" id="email" placeholder="登录邮箱" />
                <input class="form-control" style="margin-top: 10px;" type="password" name="password" id="password" placeholder="登录密码" />
            </div>
            <div class="form-group">
                <div class="checkbox pull-left" style="margin-top: 0 !important;margin-bottom: 0 !important;color: #ffffff;">
                    <label>
                        <input type="checkbox" name="rememberMe" id="rememberMe" value="1">
                        记住我
                    </label>
                </div>
                <div class="pull-right">
                    <a href="/ugc/admin/forget">忘记密码</a>
                </div>
            </div>
            <a class="btn btn-base login-btn" href="javascript:void(0);">登录</a>
            <div class="login-social">
                <i class="iconfont icon-qq"></i>
                <i class="iconfont icon-weixin"></i>
                <i class="iconfont icon-weibo"></i>
            </div>
            <div class="login-footer">
                <p>没有账号?<a href="/ugc/admin/register/web">立即注册</a></p>
            </div>
        </form>
    </div>
</div>

<jsp:include page="../common/footer.jsp"></jsp:include>
<script src="../../../static/jquery-3.1.0.min.js"></script>
<script src="../../../static/bootstrap/dist/js/bootstrap.min.js"></script>
<script src="../../../js/common/js.cookie.js"></script>
<script>
    var rememberMe = Cookies.get('rememberMe');
    if(rememberMe == 1) {
        $("#rememberMe").attr("checked", "checked");
    }

    $(".login-btn").click(function() {
        $.ajax({
            url:"/ugc/admin/login",
            type:"post",
            dataType:"json",
            data:$("#login-form").serialize(),
            success: function(result) {
                if(result.code == 0) {
                    window.location.href = "/ugc/data/" + result.data + "/type/7";
                } else {
                    alert(result.message);
                }
            }
        });
    });
</script>
</body>
</html>
