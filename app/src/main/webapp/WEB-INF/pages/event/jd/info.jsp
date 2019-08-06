<%@ page import="java.util.Date" %><%--
  Created by IntelliJ IDEA.
  User: edward
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/WEB-INF/pages/common/head-base-path.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>是时候开始越野跑了--轻越野跑全线开“战”</title>
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
    <link rel="stylesheet" href="/static/bootstrap-datetimepicker/css/bootstrap-datetimepicker.min.css">


    <meta property="og:type" content="website">
    <meta property="og:title" content="感谢一路有你，悦跑年会开始报名~">
    <meta property="og:image" content="http://img1.maka.im/cover/97399912056990958d132a6.02210370.jpg@159-82-736-736a">
    <meta property="og:description" content="悦跑邀请你参加年度盛会……">

    <style type="text/css">

        body, h1, h2, h3, h4, h5, h6, .uk-navbar-nav > li > a { font-family:  "Microsoft Yahei","微软雅黑","Proxima Nova", "Helvetica Neue", Helvetica, Arial, sans-serif; }

        body{
            /*margin: 0px 15px 20px 15px;*/
            /*text-align: center;*/
            text-align: center;
            position: relative;
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




<%--<a href="http://mp.weixin.qq.com/s?__biz=MjM5MDk2ODA2MA==&mid=401226939&idx=1&sn=ea1a5a1655ee6716cf4bbf793f3902bc&scene=1&srcid=0115PGUxtiizivBTUWLXsQQo#rd"--%>
<%--style="color: #ff3d25;">点击了解活动详情</a>--%>
<%--<br>--%>
<%--<a href="http://appt.igeekery.com/activity/20160115/input.htm" style="color: #ff3d25;">报名查询</a>--%>

<div style="background: #eaeaea;line-height: 3;font-weight: bold;font-size: 15px;">
    -- 信息收集 --
</div>

<div style="text-align: center;text: red;">
    <h2>${altMsg}</h2>
</div>
<div id="panel-form">
    <form action="/activity/register-jd-data.htm" method="post" isform>

        <div class="form-group">
            <input class="form-control input-lg" type="text" name="name" placeholder="姓名" notNull>
        </div>

        <div class="form-group">
            <select class="form-control input-lg" name="gender">
                <option value="1">男</option>
                <option value="2">女</option>
            </select>
        </div>
        <div class="form-group">
            <input class="form-control input-lg" type="number"  name="age" placeholder="年龄" notNull>
        </div>
        <div class="form-group">
            <input class="form-control input-lg" type="text" name="idCard" placeholder="证件号码" userCard notNull>
        </div>
        <div class="form-group">
            <select class="form-control input-lg" name="nationality"><%--国籍(国家以及地区)--%>
                <option value="中国">中国</option>
                <option value="美国">美国</option>
            </select>
        </div>
        <div class="form-group">
            <input class="form-control input-lg" type="text" name="detail"  placeholder="详细地址" notNull>
        </div>
        <div class="form-group">
            <input class="form-control input-lg" type="text" name="mobilePhone"  placeholder="联系电话" isMobile notNull>
        </div>
        <div class="form-group">
            <input class="form-control input-lg" type="text" email name="email" placeholder="电子邮箱" notNull>
        </div>
        <div class="form-group">
            <input class="form-control input-lg" type="text" name="emergencyName" placeholder="紧急联系人" notNull>
        </div>
        <div class="form-group">
            <input class="form-control input-lg" type="text" name="emergencyPhone"  placeholder="紧急联系电话" isMobile notNull>
        </div>
        <div class="form-group">
            <select class="form-control input-lg" name="clothesSize"><%--服装尺码--%>
                <option value="S">S</option>
                <option value="M">M</option>
                <option value="L">L</option>
                <option value="XL">XL</option>
                <option value="2XL">2XL</option>
                <option value="3XL">3XL</option>
            </select>
        </div>

        <div class="form-group">
            <button type="submit" class="btn btn-info form-control input-lg">马上参与</button>
        </div>
    </form>
</div>

<script src="/static/jquery-1.11.2.min.js"></script>
<script type="text/javascript" src="/static/jquery.form.js"></script>
<script src="/static/bootstrap/bootstrap.min.js"></script>
<script type="text/javascript" src="/static/bootstrap-datetimepicker/bootstrap-datetimepicker.min.js"></script>
<script type="text/javascript" src="/static/bootstrap-datetimepicker/locales/bootstrap-datetimepicker.zh-CN.js"></script>
<script>

    $(function(){
        $("[isform]").submit(function(){
            var form = $(this);
            checkForm(form);

            if (!checkError(form) || !window.confirm("请核实信息，提交后不能修改。")) {
                return false;
            }

            if (checkError(form)) {
               // form.find("[type=submit]").attr("disabled", "disabled").val("正在提交数据...");
                return true;
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

            form.find("[email]").each(function(index, element){
                var th = $(this);
                var val = th.val();

                if (!/^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(.[a-zA-Z0-9_-])+/.test(val)) {
                    if (!th.parent().find("[errorEl]")[0]) {
                        th.parent().addClass("has-error");
                        th.parent().append('<label class="control-label" errorEl>邮箱不正确</label>');
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

<script>
    $('#datetimepicker').datetimepicker({
        format: 'yyyy-mm-dd',
        language:'zh-CN',
        weekStart: 1,
        todayBtn:  1,
        autoclose: 1,
        todayHighlight: 1,
        startView: 2,
        minView: 2,
        forceParse: 0
    });
</script>
</body>
</html>


