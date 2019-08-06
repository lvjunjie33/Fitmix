
<%--
  Created by IntelliJ IDEA.
  User: sin
  Date: 2015/8/28
  Time: 9:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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

        body{
            margin-left: 15px;
            margin-right: 15px;
        }
        .teamName{
            color: #56b6d3
        }
    </style>

</head>

<%--
<div class="form-group has-error">
    <label class="control-label" for="inputError1">Input with error</label>
    <input type="text" class="form-control" id="inputError1">
</div>--%>
<body class="text-center">
    <h3 class="teamName">创客百团摇滚马拉松团队报名</h3>

    <form action="/activity/20150910/run/ream/add/data.json" method="post" isform>

        <div class="form-group">
            <input type="text" name="company" class="form-control" id="affiliation" placeholder="请输入跑团所属企业名称" notNull>
        </div>

        <div class="form-group">
            <input type="text" name="teamName" class="form-control" id="paoName" placeholder="请输入跑团具体名称" notNull>
        </div>

        <div class="form-group">
            <input type="text" name="teamUserName" class="form-control" id="groupName" placeholder="请输入跑团负责人名称" notNull>
        </div>

        <div class="form-group">
            <input type="text" name="teamUserMobile" maxlength="11" class="form-control" id="contactNumber" maxlength="18" placeholder="请输入您的手机号" isMobile>
        </div>

        <div class="form-group">
            <input type="text" name="teamUserCard" class="form-control" id="idNumber" maxlength="18" placeholder="身份证号" userCard>
        </div>

        <div class="form-group">
            <input id="mailingAddress" name="teamUserCommunicationAddress" type="text" class="form-control" placeholder="通讯地址" notNull>
        </div>
        <div class="form-group">
            <textarea class="form-control" name="memo" placeholder="备注" id="remark"></textarea>
        </div>
        <input type="submit" value="认领团队" class="form-control btn btn-info" placeholder="ID number" aria-describedby="basic-addon1">

        <%--<div class="form-group">
            <input type="text" name="company" class="form-control" id="affiliation" placeholder="所属单位" notNull>
        </div>

        <div class="form-group">
            <input type="text" name="teamName" class="form-control" id="paoName" placeholder="跑团名称" notNull>
        </div>
        <div class="form-group">
            <input type="text" name="teamUserName" class="form-control" id="groupName" placeholder="组联系人" isMobile>
        </div>

        <div class="form-group">
            <input type="text" name="teamUserCard" class="form-control" id="idNumber" maxlength="18" placeholder="身份证号" userCard>
        </div>
        <div class="form-group">
            <input type="text" name="teamUserMobile" maxlength="11" class="form-control" id="contactNumber" maxlength="18" placeholder="联系电话" isMobile>
        </div>
        <div class="form-group">
            <input id="mailingAddress" name="teamUserCommunicationAddress" type="text" class="form-control" placeholder="通讯地址" notNull>
        </div>
        <div class="form-group">
            <textarea class="form-control" name="memo" placeholder="备注" id="remark"></textarea>
        </div>
        <input type="submit" value="认领团队" class="form-control btn btn-info" placeholder="ID number" aria-describedby="basic-addon1">--%>
    </form>

    <div class="error-content" style="color: #ff4e64;"></div>
</body>
</html>

<script type="text/javascript">

    $(function(){

        $("[isform]").submit(function(){
            var form = $(this);
            checkForm(form);
            if (checkError(form)) {
                form.find("[type=submit]").attr("disabled", "disabled").val("正在提交数据...");
                form.ajaxSubmit({
                    success: function (result) {
                        var code = result['code'];
                        if (code == 0) {
                            location.href = "activity/20150910/run/ream/info.htm?teamId=" + result["teamId"];
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