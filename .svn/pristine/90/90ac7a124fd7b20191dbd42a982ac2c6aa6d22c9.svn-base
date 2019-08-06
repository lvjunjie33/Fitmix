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
            <div style="margin-top: 25px;">
                <span style="color: #d14946;font-size: 25px;">乐享动DJ入驻</span>
                <span style="color: #A7A3A3;display: inline-block; ">注册成为乐享动DJ的主播，上传自己的音频节目到乐享动DJ。让上亿的听众听到你的声音！</span>
            </div>
            <form class="register-form">
                <div class="avatar-box">
                    <img id="avatar" class="avatar" src="//pic.qingting.fm/2016/0516/20160516133040720.png!200" width="100px">
                    <span></span>
                    <a class="btn btn-sm btn-base-default upload" style="vertical-align: bottom;">上传头像</a>
                    <input type="file" class="hide" accept=".jpg,.jpeg,.png,.bmg,.jPG,.jPEG,.PNG,.BMP">
                    <input class="hide" name="avatar" value="">
                </div>
                <input class="form-control register-input" type="text" style="width:300px" name="name" placeholder="主播名称 汉字/字母/数字(10字以内）">
                <span class="warning" for="name"></span>
                <hr>
                <input type="email" class="form-control register-input" style="width:300px" id="email" name="email" placeholder="填写登录邮箱">
                <span class="warning" for="email"></span>
                <hr>
                <input type="text" style="width:300px" class="form-control register-input" name="mobile" placeholder="请填写手机号码" value="">
                <span class="warning" for="mobile"></span><br>
                <input type="text" style=" display: inline-block;width:180px" class="form-control register-input" name="verifyCode" placeholder="填写验证码" value="">
                <button type="button" style="display: inline-block;width:120px " id="verifyCode" onclick="Admin.verifyCode.getVerifyCode()" class="btn btn-base-default" >获取验证码
                </button>
                <span class="warning" for="verifyCode"></span>
                <hr>
                <input type="password" class="form-control register-input"  style="width:300px" id="password1" name="password1" placeholder="填写密码">
                <span class="warning" for="password"></span><br>
                <input type="password" class="form-control register-input"  style="width:300px" id="password2" name="password2" placeholder="确认密码">
                <hr>
                <dl class="register-item">
                    <dd class="regForm-item-ct txt-tips">
                        <label></label>
                        <input id="mobileAcceptIpt" name="acceptDeclaration" type="checkbox" checked="checked">
                        我已阅读并同意《
                        <a href="/static_html/declaration.html" class="register-declaration" target="_blank" tabindex="-1">播客协议</a>
                        》
                    </dd>
                </dl>
                <a id="submit" class="btn btn-base" href="javascript:void(0);">提交</a>
                <button type="button" class="btn btn-base-default" onclick="window.history.back()">取消</button>
            </form>
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
    Admin.init();
</script>
</body>
</html>
