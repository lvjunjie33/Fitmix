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
    <title>UGC 注册</title>
    <base href="<%=basePath%>">
    <link rel="stylesheet/less" type="text/css" href="../../../css/common/main.less" />
    <link rel="stylesheet" type="text/css" href="../../../static/loading/showLoading.css" />
    <script src="../../../static/less/less.min.js"></script>
</head>
<body id="loading">
<nav class="navbar navbar-default navbar-static-top" role="banner">
    <div class="navbar-header">
        <button class="navbar-toggle collapsed" type="button" data-toggle="collapse" data-target="#bs-navbar" aria-controls="bs-navbar" aria-expanded="false">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
        </button>
        <a href="#" class="navbar-brand"><img class="navbar-log" src="/imgs/logo-fitmix.png" /></a>
    </div>
    <nav id="bs-navbar" class="collapse navbar-collapse">
        <ul class="nav navbar-nav navbar-right">
            <li><a href="javascript:window.history.go(-1);">返回</a></li>
        </ul>
    </nav>
</nav>

<div class="container-fluid">
    <div class="row">
        <div class="col-md-8 col-md-push-2">
            <div id="step1" class="text-center form-group">
                <label style="width: 300px;margin: 50px auto 0 auto;text-align: left;">你的邮箱</label>
                <input type="email" name="email" class="form-control" style="width: 300px;margin: 10px auto;">
                <a id="next2" class="btn btn-base" style="display: inline-block; width: 145px;">继续</a>
                <a href="javascript:history.back();" class="btn btn-base" style="display: inline-block; width: 145px;">返回</a>
            </div>
            <div id="step2" class="text-center form-group" style="display: none;">
                <label id="email" style="width: 300px;margin: 50px auto 0 auto;text-align: left;display: block;font-size: 24px;">你的邮箱</label>
                <div style="margin: 0 auto;width: 300px; text-align: left;">
                    <label id="mobile" style="width: 300px;margin: 20px auto 0 auto;text-align: left;display: inline-block;width: 150px;">你的邮箱</label>
                    <a id="verifyCode" class="btn btn-base" style="display: inline-block;">获取验证码</a>
                </div>
                <input type="text" name="verifyCode" class="form-control" style="width: 300px;margin: 10px auto;" placeholder="验证码">
                <input type="password" name="password" class="form-control" style="width: 300px;margin: 10px auto;" placeholder="新密码">
                <a id="next3" class="btn btn-base" style="display: inline-block; width: 145px;">提交</a>
                <a href="javascript:history.back();" class="btn btn-base" style="display: inline-block; width: 145px;">返回</a>
            </div>
        </div>
    </div>
</div>

<script src="../../../static/jquery-3.1.0.min.js"></script>
<script src="../../../static/bootstrap/dist/js/bootstrap.min.js"></script>
<script src="../../../static/loading/jquery.showLoading.min.js"></script>
<script src="//cdn.bootcss.com/bootbox.js/4.4.0/bootbox.min.js"></script>
<script src="../../../js/common/VerificationUtil.js"></script>
<script src="../../../js/admin/admin.js"></script>
<script>
    Admin.forget.init();
</script>
</body>
</html>
