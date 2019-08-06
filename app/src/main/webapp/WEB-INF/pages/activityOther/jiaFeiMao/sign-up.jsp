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
    <title>加菲猫欢乐跑深圳</title>

    <link rel="stylesheet" href="/static/bootstrap/bootstrap.min.css">
    <script src="/static/jquery-1.11.2.min.js"></script>
    <script type="text/javascript" src="/static/jquery.form.js"></script>

    <script type="text/javascript">

        window.onload=function(){
            var ua = navigator.userAgent.toLowerCase();
            var isWeixin = ua.indexOf('micromessenger') != -1;
            var isAndroid = ua.indexOf('android') != -1;
            var isIos = (ua.indexOf('iphone') != -1) || (ua.indexOf('ipad') != -1);
            if (!isWeixin) {
                document.head.innerHTML = '<title>抱歉，出错了</title><meta charset="utf-8"><meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=0"><link rel="stylesheet" type="text/css" href="https://res.wx.qq.com/connect/zh_CN/htmledition/style/wap_err1a9853.css">';
                document.body.innerHTML = '<div class="page_msg"><div class="inner"><span class="msg_icon_wrp"><i class="icon80_smile"></i></span><div class="msg_content"><h4>请在微信客户端打开链接</h4></div></div></div>';
            }
        }

    </script>
    <script>


        /// TODO 找个时间把此类工具 弄成闭包工具类 to Sin
        /// 自动 json


        $.fn.extend({
            sequenceJson:function(){
                var jsonArray = [];
                var json={};
                $.each($(this).find("*[name]"),function(i, el){
                    if($(this).attr("fill") != "false" && !(/^\s*$/.test($(this).attr("name")))){
                        var k = $.trim($(this).attr("name"));
                        var v = $.trim($.sequenceRule($(this)[0].tagName, $(this)));
                        if (json[k]) {
                            jsonArray[jsonArray.length] = json;
                            json = {};
                        }

                        json[k] = v;

                    };
                });
                jsonArray[jsonArray.length] = json;
                return jsonArray;
            }
        });

        jQuery.extend({
            sequenceRule:function(k,o){
                switch (k) {
                    case "INPUT":
                    case "SELECT":
                    case "TEXTAREA":
                        return o.val();
                    case "TR":
                    case "TD":
                    case "H1":
                    case "H2":
                    case "H3":
                    case "H4":
                    case "H5":
                    case "H6":
                    case "DIV":
                    case "SPAN":
                    case "LABEL":
                    case "LI":
                    case "HEAD":
                        return o.text();
                    case "IMG":
                    case "SCRIPT":
                        return o.attr("src");
                }
            }
        });

        $.fn.extend({
            fillNode : function (json) {
                $.each($(this).find("[name]"), function (i, node) {
                    var k = $(this).attr("name");
                    $.fillRule($(this), json[k]);
                });
            }
        });

        $.extend({
            fillRule:function(el, v){
                switch (el[0].tagName) {
                    case "INPUT":
                    case "SELECT":
                    case "TEXTAREA":
                        return el.val(v);
                    case "TR":
                    case "TD":
                    case "H1":
                    case "H2":
                    case "H3":
                    case "H4":
                    case "H5":
                    case "H6":
                    case "DIV":
                    case "SPAN":
                    case "LI":
                    case "HEAD":
                        return el.text(v);
                    case "IMG":
                    case "SCRIPT":
                        return el.attr("src", v);
                }
            }
        });
    </script>

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
    </style>
</head>

<body>
    <script>
        var errorMsg = "${errorMsg}";
        $(function(){
            if (errorMsg.length > 1) {
                alert(errorMsg);
            }
        });
    </script>
    <%--   主图 --%>
    <img class="img-with" src="/imgs/activityOther/jiaFeiMao/sign-up-top2.jpg" title="加菲猫欢乐跑深圳"/>

    <%--  公告信息  --%>
    <div class="textarea-none">
        <p style="max-width: 100%; min-height: 1em; white-space: pre-wrap; color: rgb(62, 62, 62); background-color: rgb(255, 255, 255); text-align: center; line-height: 1.75em; box-sizing: border-box !important; word-wrap: break-word !important;"><span style="max-width: 100%; font-size: 14px; box-sizing: border-box !important; word-wrap: break-word !important;"><br style="max-width: 100%; box-sizing: border-box !important; word-wrap: break-word !important;"></span></p>


        <p style="max-width: 100%; min-height: 1em; white-space: pre-wrap; color: rgb(62, 62, 62); background-color: rgb(255, 255, 255); text-align: center; line-height: 1.75em; box-sizing: border-box !important; word-wrap: break-word !important;"><span style="max-width: 100%; color: rgb(255, 41, 65); font-size: 18px; box-sizing: border-box !important; word-wrap: break-word !important;"><strong style="max-width: 100%; box-sizing: border-box !important; word-wrap: break-word !important;"><span style="max-width: 100%; line-height: 1.6; box-sizing: border-box !important; word-wrap: break-word !important;">Hello</span></strong></span></p>
        <p style="max-width: 100%; min-height: 1em; white-space: pre-wrap; color: rgb(62, 62, 62); background-color: rgb(255, 255, 255); text-align: center; line-height: 1.75em; box-sizing: border-box !important; word-wrap: break-word !important;"><span style="max-width: 100%; font-size: 14px; box-sizing: border-box !important; word-wrap: break-word !important;">萌萌哒加菲猫</span></p>
        <p style="max-width: 100%; min-height: 1em; white-space: pre-wrap; color: rgb(62, 62, 62); background-color: rgb(255, 255, 255); text-align: center; line-height: 1.75em; box-sizing: border-box !important; word-wrap: break-word !important;"><span style="max-width: 100%; font-size: 14px; box-sizing: border-box !important; word-wrap: break-word !important;">有些日子没见到大家了</span></p>
        <p style="max-width: 100%; min-height: 1em; white-space: pre-wrap; color: rgb(62, 62, 62); background-color: rgb(255, 255, 255); text-align: center; line-height: 1.75em; box-sizing: border-box !important; word-wrap: break-word !important;"><span style="max-width: 100%; font-size: 14px; box-sizing: border-box !important; word-wrap: break-word !important;">狠是想念呢！</span></p>
        <p style="max-width: 100%; min-height: 1em; white-space: pre-wrap; color: rgb(62, 62, 62); background-color: rgb(255, 255, 255); text-align: center; line-height: 1.75em; box-sizing: border-box !important; word-wrap: break-word !important;"><span style="max-width: 100%; font-size: 14px; box-sizing: border-box !important; word-wrap: break-word !important;"><br style="max-width: 100%; box-sizing: border-box !important; word-wrap: break-word !important;"></span></p>
        <p style="max-width: 100%; min-height: 1em; white-space: pre-wrap; color: rgb(62, 62, 62); background-color: rgb(255, 255, 255); text-align: center; line-height: 1.75em; box-sizing: border-box !important; word-wrap: break-word !important;"><span style="max-width: 100%; font-size: 14px; line-height: 1.75em; box-sizing: border-box !important; word-wrap: break-word !important;">加菲猫给大家拜年啦</span><br style="max-width: 100%; box-sizing: border-box !important; word-wrap: break-word !important;"></p>
        <p style="max-width: 100%; min-height: 1em; white-space: pre-wrap; color: rgb(62, 62, 62); background-color: rgb(255, 255, 255); text-align: center; line-height: 1.75em; box-sizing: border-box !important; word-wrap: break-word !important;"><span style="max-width: 100%; font-size: 14.44444465637207px; line-height: 27.986112594604492px; box-sizing: border-box !important; word-wrap: break-word !important;">猴年开工大吉</span></p>
        <p style="max-width: 100%; min-height: 1em; white-space: pre-wrap; color: rgb(62, 62, 62); background-color: rgb(255, 255, 255); text-align: center; line-height: 1.75em; box-sizing: border-box !important; word-wrap: break-word !important;"><span style="max-width: 100%; font-size: 18px; box-sizing: border-box !important; word-wrap: break-word !important;"><strong style="max-width: 100%; box-sizing: border-box !important; word-wrap: break-word !important;"><span style="max-width: 100%; color: rgb(255, 41, 65); box-sizing: border-box !important; word-wrap: break-word !important;">Happy Monkey's Year</span></strong></span></p>

        <p style="max-width: 100%; min-height: 1em; white-space: pre-wrap; color: rgb(62, 62, 62); background-color: rgb(255, 255, 255); text-align: center; line-height: 1.75em; box-sizing: border-box !important; word-wrap: break-word !important;"><span style="max-width: 100%; font-size: 14.44444465637207px; line-height: 27.986112594604492px; box-sizing: border-box !important; word-wrap: break-word !important;">已报名用户点这里可以<a href="activity/jia-fei-mao/queryInput.htm">【查询报名信息】</a></span></p>

        <p style="max-width: 100%; min-height: 1em; white-space: pre-wrap; color: rgb(62, 62, 62); background-color: rgb(255, 255, 255); text-align: center; line-height: 1.75em; box-sizing: border-box !important; word-wrap: break-word !important;"><span style="max-width: 100%; font-size: 18px; box-sizing: border-box !important; word-wrap: break-word !important;"><strong style="max-width: 100%; box-sizing: border-box !important; word-wrap: break-word !important;"><span style="max-width: 100%; color: rgb(255, 41, 65); box-sizing: border-box !important; word-wrap: break-word !important;">【限时大优惠】</span></strong></span></p>

        <p style="max-width: 100%; min-height: 1em; white-space: pre-wrap; color: rgb(254, 78, 64); background-color: rgb(255, 255, 255); text-align: center; line-height: 1.75em; box-sizing: border-box !important; word-wrap: break-word !important;"><span style="max-width: 100%; font-size: 14px; box-sizing: border-box !important; word-wrap: break-word !important;">【公开组（个人组）】</span></p>
        <p style="max-width: 100%; min-height: 1em; white-space: pre-wrap; color: rgb(255, 72, 40); background-color: rgb(255, 255, 255); text-align: center; line-height: 1.75em; box-sizing: border-box !important; word-wrap: break-word !important;"><span style="max-width: 100%; font-size: 14px; box-sizing: border-box !important; word-wrap: break-word !important;">原价 188元  现价100元  限量300人</span></p>

        <p style="max-width: 100%; min-height: 1em; white-space: pre-wrap; color: rgb(244, 66, 64); background-color: rgb(255, 255, 255); text-align: center; line-height: 1.75em; box-sizing: border-box !important; word-wrap: break-word !important;"><span style="max-width: 100%; font-size: 14px; box-sizing: border-box !important; word-wrap: break-word !important;">【家庭三人组】</span></p>
        <p style="max-width: 100%; min-height: 1em; white-space: pre-wrap; color: rgb(249, 61, 34); background-color: rgb(255, 255, 255); text-align: center; line-height: 1.75em; box-sizing: border-box !important; word-wrap: break-word !important;"><span style="max-width: 100%; font-size: 14px; box-sizing: border-box !important; word-wrap: break-word !important;">原价398元  现价300元  限量300组</span></p>

                

    </div>

    <div style="background: #f7e852;padding:10px 0px; ">
        <div style="line-height: 3;">
            <img class="img-with" src="/imgs/activityOther/jiaFeiMao/sign-up-head.png">
        </div>
        <div style="margin: 20px;">
            <form action="/activity/jia-fei-mao/sign-up-data.json" method="post" isform>

                <div class="form-group">
                    <select class="form-control" name="group">
                        <option value="公开组">公开组</option>
                        <option value="情侣组">情侣组</option>
                        <option value="家庭2人组">家庭2人组</option>
                        <option value="家庭3人组">家庭3人组</option>
                        <option value="家庭4人组">家庭4人组</option>
                    </select>
                </div>

                <%--  teaplate modal  --%>
                <script type="text/html" id="group-template">
                    <div class="form-group">
                        <strong>{0}</strong>
                    </div>
                    <div class="form-group">
                        <input class="form-control" type="text" name="name" placeholder="姓名" notNull>
                    </div>
                    <div class="form-group">
                        <select class="form-control" name="gender" placeholder="性别" notNull>
                            <option value="">--- 选择性别 ---</option>
                            <option value="男">男</option>
                            <option value="女">女</option>
                        </select>
                    </div>
                    <div class="form-group">
                        <select class="form-control" name="clothesSize" placeholder="衣服尺码" notNull>
                            <option value="">--- 选择尺码 ---</option>
                            <option value="S">S</option>
                            <option value="M">M</option>
                            <option value="L">L</option>
                            <option value="XL">XL</option>
                            <option value="2XL">2XL</option>
                        </select>
                    </div>
                    <div class="form-group">
                        <textarea class="form-control" type="number" name="memo" maxlength="3" placeholder="备注"></textarea>
                    </div>

                    <div class="form-group">
                        <strong>证件信息</strong>
                    </div>

                    <div class="form-group">
                        <select class="form-control" name="idCardType" notNull>
                            <option value="身份证">身份证</option>
                            <option value="护照">护照</option>
                            <option value="回乡证">回乡证</option>
                        </select>
                    </div>
                    <div class="form-group">
                        <input class="form-control" type="text" name="idCard" maxlength="20" placeholder="证件信息" notNull>
                    </div>
                </script>

                <script>

                    ///
                    /// 重写 String.format

                    String.prototype.format = function(args) {
                        if (arguments.length>0) {
                            var result = this;
                            if (arguments.length == 1 && typeof (args) == "object") {
                                for (var key in args) {
                                    var reg=new RegExp ("({"+key+"})","g");
                                    result = result.replace(reg, args[key]);
                                }
                            }
                            else {
                                for (var i = 0; i < arguments.length; i++) {
                                    if(arguments[i]==undefined)
                                    {
                                        return "";
                                    }
                                    else
                                    {
                                        var reg=new RegExp ("({["+i+"]})","g");
                                        result = result.replace(reg, arguments[i]);
                                    }
                                }
                            }
                            return result;
                        }
                        else {
                            return this;
                        }
                    };


                    $(function(){
                        $("[isform]").submit(function(){
                            var form = $(this);
                            checkForm(form);

                            if (!checkError(form) || !window.confirm("请核实信息，提交后不可修改。")) {
                                return false;
                            }

                            if (checkError(form)) {
                                form.find("[type=submit]").attr("disabled", "disabled").val("正在提交数据...");

                                var memberJson = $("#append-group").sequenceJson();
                                $("[name=memberJson]").val(JSON.stringify(memberJson));
                                form.ajaxSubmit({
                                    success: function (result) {
                                        console.info(result);
                                        var code = result['code'];
                                        if (code == 0) {
                                            location.href = result["redirect"];
                                        }
                                        else {
                                            alert(result['msg']);
                                            form.find("[type=submit]").removeAttr("disabled").val("参加");
                                        }
                                    }
                                });
                            }
                            return false;
                        });


                        $("[isform] input").keyup(function(){
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

                        $("[name=group]").change(function(){

                            var publicGroupCount = ${publicGroupCount};
                            var sanRenGroupCount = ${sanRenGroupCount};

                            var group = $(this).val();
                            var template = $("#group-template").text();
                            var appendEl = $("#append-group");
                            var payMoneyEl = $("#pay-money");

                            appendEl.text("");
                            switch (group) {
                                case "公开组":
                                    appendEl.append(template.format("个人信息"));
                                    if (publicGroupCount <= 250) {
                                        payMoneyEl.html("<s>188</s>&nbsp;100");
                                    }
                                    else {
                                        payMoneyEl.html("188");
                                    }
                                    break;
                                case "情侣组":
                                    appendEl.append(template.format("情侣组 A"));
                                    appendEl.append(template.format("情侣组 B"));
                                    payMoneyEl.html("358");
                                    break;
                                case "家庭2人组":
                                    appendEl.append(template.format("家长 A"));
                                    appendEl.append(template.format("儿童 A"));
                                    payMoneyEl.text("328");
                                    break;
                                case "家庭3人组":
                                    appendEl.append(template.format("家长 A"));
                                    appendEl.append(template.format("家长 B"));
                                    appendEl.append(template.format("儿童 A"));
                                    if (sanRenGroupCount <= 250) {
                                        payMoneyEl.html("<s>398</s>&nbsp;300");
                                    }
                                    else {
                                        payMoneyEl.html("398");
                                    }
                                    break;
                                case "家庭4人组":
                                    appendEl.append(template.format("家长 A"));
                                    appendEl.append(template.format("家长 B"));
                                    appendEl.append(template.format("儿童 A"));
                                    appendEl.append(template.format("儿童 B"));
                                    payMoneyEl.text("498");
                                    break;
                            }

                            ///
                            /// 节点发生变化后 重新绑定
                            $("[isform] input").keyup(function(){
                                checkForm($(this).parent());
                            });
                        })
                        $("[name=group]").change();
                    })
                </script>

                <div id="append-group">

                </div>

                <div class="form-group">
                    <strong>通知信息</strong>
                </div>

                <div class="form-group">
                    <input class="form-control" type="email" name="email" placeholder="邮箱" email>
                </div>
                <div class="form-group">
                    <input class="form-control" type="text" name="mobileName" maxlength="20" placeholder="联系人姓名" notNull>
                </div>
                <div class="form-group">
                    <input class="form-control" type="text" name="mobilePhone" placeholder="联系人电话" isMobile>
                </div>
                <div class="form-group">
                    <input class="form-control" type="text" name="emergencyName" placeholder="紧急联系人姓名" notNull>
                </div>
                <div class="form-group">
                    <input class="form-control" type="text" name="emergencyPhone" placeholder="紧急联系人电话" isMobile>
                </div>

                <div class="form-group">
                    <strong>其他信息</strong>
                </div>
                <div class="form-group">
                    <input type="hidden" name="memberJson"/>
                    <p>参赛金额：<label id="pay-money">0.00</label></p>
                    <p>支付方式：微信支付</p>
                    <label><input type="checkbox" checked="checked" disabled/>&nbsp;&nbsp;我同意并接受 <a href="/activity/jia-fei-mao/sm.htm">查看说明</a></label>

                    <%--<input type="submit" value="活动结束" class="form-control btn btn-danger" placeholder="ID number" disabled aria-describedby="basic-addon1">--%>

                    <c:choose>
                        <c:when test="${signUpCount <= 1000}">
                            <input type="submit" value="立即报名" class="form-control btn btn-danger" placeholder="ID number" aria-describedby="basic-addon1">
                        </c:when>
                        <c:when test="${signUpCount > 1000}">
                            <input type="submit" value="名额已满" class="form-control btn btn-danger" placeholder="ID number" aria-describedby="basic-addon1" disabled>
                        </c:when>
                    </c:choose>

                </div>
            </form>
        </div>
    </div>
</body>
</html>
