
<%--
  Created by IntelliJ IDEA.
  User: sin
  Date: 2015/8/28
  Time: 9:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page trimDirectiveWhitespaces="true" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ include file="/WEB-INF/pages/common/head-base-path.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <!-- 禁止缩放 -->
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <title></title>

    <link rel="stylesheet" href="/static/bootstrap/bootstrap.min.css">
    <script src="/static/jquery-1.11.2.min.js"></script>
    <script type="text/javascript" src="/static/jquery.form.js"></script>

    <style type="text/css">

        body, h1, h2, h3, h4, h5, h6, .uk-navbar-nav > li > a { font-family:  "Microsoft Yahei","微软雅黑","Proxima Nova", "Helvetica Neue", Helvetica, Arial, sans-serif; }

        /*body{*/
            /*margin-left: 15px;*/
            /*margin-right: 15px;*/
        /*}*/

        body,html{height: 100%;width: 100%}


        .img{
            width: 100%;
            height: 100%;
        }

        .panel-form{
            position: relative;
        }

        .login-form{
            position: absolute;
            height: 120px;
            width: 100%;
            padding: 15px;;
            top:calc(50% + 12%);
            /*left:calc(50% - 30px);*/
        }
    </style>


</head>

<body class="text-center">

    <div class="panel-form">
        <img src="/imgs/activity20150910/img/20150911_4.JPG" class="img"/>
        <form action="/activity/20150910/user/activity/login/data.json" method="post" isform class="login-form">
            <div class="form-group">
                <input type="text" name="mobile" class="form-control input-lg" placeholder="请输入您的手机号" isMobile >
            </div>
            <div class="form-group">
                <input type="submit" value="查询" class="btn btn-info btn-lg btn-block">
            </div>
        </form>
    </div>

</body>
</html>


<script type="text/javascript">


    $(function(){
        $("[isform]").submit(function(){
            var form = $(this);

            checkForm(form);
            if (checkError(form)) {
                form.ajaxSubmit({
                    success: function (result) {
                        var code = result['code'];
                        if (code == 0) {
                            var href = "/activity/20150910/run/ream/member.htm?teamId=" + result["activityUser"]["teamId"];
                            if (result["activityUser"]["id"]) {
                                href += "&activityUserId=" + result["activityUser"]["id"];;
                            }
                            location.href = href;
                        }
                        else {
                            alert(result['msg']);
                        }
                    }
                });
            }
            return false;
        });


        $("[isform] input").change(function(){
            checkForm($(this).parent());
        });

        function checkError(form) {
            return form.find("[errorEl]").size() == 0;
        }


        function checkForm(form) {
            form.find("[notNull]").each(function(index, element){
                var th = $(this);
                var val = th.val();
                if (/^\s*$/.test(val)) {
                    if (!th.parent().find("[errorEl]")[0]) {
                        th.parent().addClass("has-error");
                        th.parent().append('<label class="control-label" errorEl>不能为空</label>');
                    }
                }
                else {
                    th.parent().removeClass("has-error");
                    th.parent().find("[errorEl]").remove();
                }
            });

            form.find("[isMobile]").each(function(index, element){
                var th = $(this);
                var val = th.val();

                if (!/^(13[0-9]|14[0-9]|15[0-9]|18[0-9])\d{8}$/.test(val)) {
                    if (!th.parent().find("[errorEl]")[0]) {
                        th.parent().addClass("has-error");
                        th.parent().append('<label class="control-label" errorEl>手机号不正确</label>');
                    }
                }
                else {
                    th.parent().removeClass("has-error");
                    th.parent().find("[errorEl]").remove();
                }
            })

            form.find("[userCard]").each(function(index, element){
                var th = $(this);
                var val = th.val();

                if (!/(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/.test(val)) {
                    if (!th.parent().find("[errorEl]")[0]) {
                        th.parent().addClass("has-error");
                        th.parent().append('<label class="control-label" errorEl>身份证不正确</label>');
                    }
                }
                else {
                    th.parent().removeClass("has-error");
                    th.parent().find("[errorEl]").remove();
                }
            })
        }

        function appendError(th, val) {
            if (/^\s*$/.test(val)) {
                if (!th.parent().find("[errorEl]")[0]) {
                    th.parent().addClass("has-error");
                    th.parent().append('<label class="control-label" errorEl>不能为空</label>');
                }
            }
            else {
                th.parent().removeClass("has-error");
                th.parent().find("[errorEl]").remove();
            }
        }
    });


</script>

