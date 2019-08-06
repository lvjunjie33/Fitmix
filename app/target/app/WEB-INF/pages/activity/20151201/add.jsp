
<%--
  Created by IntelliJ IDEA.
  User: sin
  Date: 2015/8/28
  Time: 9:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/WEB-INF/pages/common/head-base-path.jsp" %>

<%
    String msg = request.getParameter("msg");
%>
<!DOCTYPE html>
<html>
<head>

  <meta charset="utf-8">
  <!-- 禁止缩放 -->
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
  <title>第三届深圳迎新跑 2016年1月1号深圳湾运动公园</title>

  <link rel="stylesheet" href="/static/bootstrap/bootstrap.min.css">
  <script src="/static/jquery-1.11.2.min.js"></script>
  <script type="text/javascript" src="/static/jquery.form.js"></script>

  <style type="text/css">

    body, h1, h2, h3, h4, h5, h6, .uk-navbar-nav > li > a { font-family:  "Microsoft Yahei","微软雅黑","Proxima Nova", "Helvetica Neue", Helvetica, Arial, sans-serif; }

    body{
      /*margin: 0px 15px 20px 15px;*/
      /*text-align: center;*/
        max-width: 500px;
        margin: 0px auto;
    }

    .teamName{
      color: #56b6d3
    }

  .content-panel{
      margin: 0px 15px 20px 15px;
      text-align: center;
  }


  .controls{
      background: #f5f5f5;;
      margin: 10px;;
      margin-top: 20px;
      margin-bottom: 20px;
  }

  .form-group{
      margin-top: 5px;;
      margin-bottom: 5px;;
  }

  </style>
</head>
<body>
<div style="background: #d9534f;text-align: center;color: #FFF;line-height: 3;position: fixed;top: 0px;width: 100%;">
    <%--蛇口赤湾 8 公里--%>
    <%--名额已满  赶快核实下信息吧↓--%>
        <%--尊敬的跑友您好：--%>
        <%--迎新跑设定名额已满，报名通道现已关闭，赛会后台将对已报名选手进行统计，如有多出名额，我们将及时对外公布，并再次开通报名通道，详情请留意悦跑官微的最新通告，谢谢您的参与。--%>
        迎新跑设定名额已满，报名通道现已关闭
</div>

<div style="height: 30px;width: 100%;">

</div>

<img src="/imgs/yinchupao.jpg" style="width: 100%;">

<div style="background: #e3e3e3;text-align: center;padding: 10px;">
    <h4>- 活动基本信息 -</h4>
    <p>比赛时间：2016年1月1号</p>
    <p>报名开始：2015年12月11号</p>
    <p>报名截止：额满截止</p>
    <p>活动地点：深圳湾运动公园 - 蛇口赤湾 8 公里</p>
    <%--<p>参赛人数：1000 人</p>--%>
    <p>参赛项目：8 公里</p>
    <p>选手包费用：100 人民币</p>
    <p>支付方式：微信支付</p>
    <%--<p>报名信息有误联系：0755-26413937</p>--%>
    <p>报名信息有误联系：zhangbn@igeekery.com</p>
    <p style="color:#ff3f44;">迎新跑公益，您的选手包费用内 10% 将捐赠深圳市青少年发展基金会</p>
    <h4>- 选手包领取 -</h4>
    <p>时间：29日—31日9:00—21:00</p>
    <p>地址：华侨城华夏艺术中心前广场</p>
    <p>深南大道华侨城段路北，地铁一号线华侨城站A出口</p>

    <%--<P><strong>注意：</strong></P>--%>
    <ul>
        <li>携带身份证件，凭个人报名回执的手机短信领取</li>
        <li>帮他人代领请提供对方的相关证件信息及报名回执信息</li>
    </ul>



    <%--【乐享动】［迎新跑组委会］亲爱的跑友：迎新跑新想事成选手包已准备就绪，请于29日—31日9:00—21:00前往华侨城华夏艺术中心前广场领取，请携带身份证件，凭个人报名回执的手机短信领取，帮他人代领请提供对方的相关证件信息及报名回执信息。领取地址：深南大道华侨城段路北，地铁一号线华侨城站A出口。--%>

<%--<p>--%>
        <%--尊敬的跑友您好：--%>
        <%--迎新跑设定名额已满，报名通道现已关闭，赛会后台将对已报名选手进行统计，如有多出名额，我们将及时对外公布，并再次开通报名通道，详情请留意悦跑官微的最新通告，谢谢您的参与。--%>
    <%--</p>--%>
</div>

<div class="controls">
    <!-- Indicates caution should be taken with this action -->
    <div class="form-group">
        <a href="http://mp.weixin.qq.com/s?__biz=MjM5MDk2ODA2MA==&mid=400494049&idx=1&sn=1ba3fbcc24adb7ba84fd494ae6316a02&scene=1&srcid=1204ZpwnfBQCs4jjEWsejHbS#rd" class="btn btn-danger form-control">关于迎新跑</a>
    </div>
    <!-- Indicates caution should be taken with this action -->
    <div class="form-group">
        <a href="http://mp.weixin.qq.com/s?__biz=MjM5MDk2ODA2MA==&mid=400408259&idx=3&sn=a521e4ceb4a49f30aaaa7991b80c4e34&scene=1&srcid=1127tKBh3sGkzk7btmvqCObk&from=singlemessage&isappinstalled=0#wechat_redirect" class="btn btn-danger form-control">为公益而跑</a>
    </div>
    <!-- Indicates caution should be taken with this action -->
    <div class="form-group">
        <a href="http://mp.weixin.qq.com/s?__biz=MjM5MDk2ODA2MA==&mid=400408259&idx=2&sn=2c16c4d91dd4c04b6ae712c947703958&scene=1&srcid=1127C7OuooepBZ4gJxWVgsI0&from=singlemessage&isappinstalled=0#wechat_redirect" class="btn btn-danger form-control">历届迎新跑回顾</a>
    </div>
    <!-- Indicates caution should be taken with this action -->
    <div class="form-group">
        <a href="/activity/20151201/userCard.htm" class="btn btn-info form-control">选手报名信息查询</a>
    </div>
</div>

<div style="background: #e3e3e3;text-align: center;line-height:3;">- 报名通道关闭 谢谢支持 -</div>


<%--<div class="content-panel" style="margin-top: 20px;">--%>
    <%--&lt;%&ndash;<c:choose>&ndash;%&gt;--%>
        <%--&lt;%&ndash;<c:when test='${year eq "2015" and month lt 12 and day lt 25}'>&ndash;%&gt;--%>
        <%--&lt;%&ndash;</c:when>&ndash;%&gt;--%>
        <%--&lt;%&ndash;<c:otherwise>&ndash;%&gt;--%>
            <%--&lt;%&ndash;<h4 style="color: red;">活动结束</h4>&ndash;%&gt;--%>
        <%--&lt;%&ndash;</c:otherwise>&ndash;%&gt;--%>
    <%--&lt;%&ndash;</c:choose>&ndash;%&gt;--%>


    <%--<div class="error-message">--%>
        <%--<%--%>
            <%--if (msg != null && msg.length() > 0) {--%>
        <%--%>--%>
        <%--<label style="color:#ff5d4d">用户已注册</label>--%>
        <%--<%--%>
            <%--}--%>
        <%--%>--%>
    <%--</div>--%>


    <%--<form action="activity/20151201/user/add.htm" method="post" isform>--%>
        <%--<div class="form-group">--%>
            <%--<input class="form-control" type="text" name="name" placeholder="姓名" notNull>--%>
        <%--</div>--%>
        <%--<div class="form-group">--%>
            <%--<select class="form-control" name="gender">--%>
                <%--<option value="男">男</option>--%>
                <%--<option value="女">女</option>--%>
            <%--</select>--%>
        <%--</div>--%>
        <%--<div class="form-group">--%>
            <%--<input class="form-control" type="number" name="age" maxlength="3" placeholder="年龄" notNull>--%>
        <%--</div>--%>
        <%--<div class="form-group">--%>
            <%--<input class="form-control" type="text" name="mobile" placeholder="手机号" isMobile>--%>
        <%--</div>--%>

        <%--<div class="form-group">--%>
            <%--<input class="form-control" type="text" name="userCard" placeholder="身份证" userCard>--%>
        <%--</div>--%>

        <%--<div class="form-group">--%>
            <%--<input class="form-control" type="text" name="email" placeholder="电子邮箱" email>--%>
        <%--</div>--%>

        <%--<div class="form-group">--%>
            <%--<select class="form-control" name="type" placeholder="类型" notNull>--%>
                <%--<option value="">-类型-</option>--%>
                <%--&lt;%&ndash;<option value="5公里">5 公里</option>&ndash;%&gt;--%>
                <%--<option value="8公里">8 公里</option>--%>
            <%--</select>--%>
        <%--</div>--%>

        <%--<div class="form-group">--%>
            <%--<select class="form-control" name="clothesSize" placeholder="衣服尺码" notNull>--%>
                <%--<option value="">-衣服尺码-</option>--%>
                <%--<option value="S">S</option>--%>
                <%--<option value="M">M</option>--%>
                <%--<option value="L">L</option>--%>
                <%--<option value="XL">XL</option>--%>
                <%--<option value="XXL">XXL</option>--%>
                <%--<option value="X XXL">X XXL</option>--%>
            <%--</select>--%>
        <%--</div>--%>
        <%--<div class="form-group">--%>
            <%--<input class="form-control" type="text" name="emergencyUserName" placeholder="紧急人联系姓名" notNull>--%>
        <%--</div>--%>
        <%--<div class="form-group">--%>
            <%--<input class="form-control" type="text" name="emergencyUserMobile" placeholder="紧急人联系电话" isMobile>--%>
        <%--</div>--%>
        <%--<div class="form-group">--%>
            <%--<input class="form-control" type="text" name="memo" placeholder="备注">--%>
        <%--</div>--%>

        <%--<div class="form-group row">--%>
            <%--<div class="col-xs-7">--%>
                <%--<input class="form-control" type="text" name="mobileCode" placeholder="验证码" isMobileCode isTrue="false">--%>
            <%--</div>--%>
            <%--<div class="col-xs-1">--%>
                <%--&lt;%&ndash;<input type="button" value="获取验证码" class="btn btn-default" clickMobile/>&ndash;%&gt;--%>
                <%--<input type="button" value="获取验证码" class="btn btn-default" disabled/>--%>
                <%--&lt;%&ndash;<c:choose>&ndash;%&gt;--%>
                    <%--&lt;%&ndash;<c:when test='${year eq 2015 and month + 1 eq 12 and day gt 10 and day lt 26 and userCount lt 5000}'>&ndash;%&gt;--%>
                        <%--&lt;%&ndash;<input type="button" value="获取验证码" class="btn btn-default" clickMobile/>&ndash;%&gt;--%>
                    <%--&lt;%&ndash;</c:when>&ndash;%&gt;--%>
                    <%--&lt;%&ndash;<c:otherwise>&ndash;%&gt;--%>
                        <%--&lt;%&ndash;<input type="button" value="获取验证码" class="btn btn-default" disabled/>&ndash;%&gt;--%>
                    <%--&lt;%&ndash;</c:otherwise>&ndash;%&gt;--%>
                <%--&lt;%&ndash;</c:choose>&ndash;%&gt;--%>
            <%--</div>--%>
        <%--</div>--%>
        <%--<div class="form-group clause-group">--%>
            <%--<label><input type="checkbox" checked clickClause/> 我已阅读并接受</label>&nbsp;<a href="activity/20151201/clause.htm" target="_self">查看活动声明</a>--%>
        <%--</div>--%>

        <%--<input type="hidden" value="2016年1月1号深圳湾运动公园" name="activityTime"/>--%>

        <%--&lt;%&ndash;<input type="button" disabled value="名额已满" class="form-control btn btn-info" >&ndash;%&gt;--%>
        <%--&lt;%&ndash;<c:choose>&ndash;%&gt;--%>
            <%--&lt;%&ndash;<c:when test='${year eq 2015 and month + 1 eq 12 and day gt 10 and day lt 26 and userCount lt 5000}'>&ndash;%&gt;--%>
                <%--&lt;%&ndash;<p>成功注册后<strong>未收到短信</strong>请及时跟我们联系</p>&ndash;%&gt;--%>
                <%--&lt;%&ndash;<input type="submit" disabled value="报名 蛇口赤湾 8 公里" class="form-control btn btn-info" placeholder="ID number" aria-describedby="basic-addon1">&ndash;%&gt;--%>
            <%--&lt;%&ndash;</c:when>&ndash;%&gt;--%>
            <%--&lt;%&ndash;<c:when test='${year eq 2015 and month + 1 eq 12 and day lt 12}'>&ndash;%&gt;--%>
                <%--&lt;%&ndash;<input type="submit" value="未开放 （2015-12-11 11 点整开放）" disabled class="form-control btn btn-info" placeholder="ID number" aria-describedby="basic-addon1">&ndash;%&gt;--%>
            <%--&lt;%&ndash;</c:when>&ndash;%&gt;--%>
            <%--&lt;%&ndash;<c:when test="${userCount gt 2999}">&ndash;%&gt;--%>
                <%--&lt;%&ndash;<input type="submit" value="名额已满 谢谢支持" disabled class="form-control btn btn-info" placeholder="ID number" aria-describedby="basic-addon1">&ndash;%&gt;--%>
            <%--&lt;%&ndash;</c:when>&ndash;%&gt;--%>
            <%--&lt;%&ndash;<c:otherwise>&ndash;%&gt;--%>
                <%--&lt;%&ndash;<h2 style="color: red;">活动结束</h2>&ndash;%&gt;--%>
            <%--&lt;%&ndash;</c:otherwise>&ndash;%&gt;--%>
        <%--&lt;%&ndash;</c:choose>&ndash;%&gt;--%>
        <%--&lt;%&ndash;<p>成功注册后<strong>未收到短信</strong>请及时跟我们联系</p>&ndash;%&gt;--%>
        <%--&lt;%&ndash;<input type="submit" disabled value="报名 蛇口赤湾 8 公里" class="form-control btn btn-info" placeholder="ID number" aria-describedby="basic-addon1">&ndash;%&gt;--%>

        <%--<input type="submit" value="报名通道关闭 谢谢支持" disabled class="form-control btn btn-info" placeholder="ID number" aria-describedby="basic-addon1">--%>
    <%--</form>--%>
<%--</div>--%>

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

<script type="text/javascript">


    $(function(){
        $("[clickClause]").click(function(){
            var clauseSize = $(".clause-group").find(":checked").size()
            var isTrue = $("[isMobileCode]").attr("isTrue");
            var submitButton = $("[isform]").find("[type=submit]");
            console.info(clauseSize, submitButton);
            if (clauseSize > 0 && isTrue == "true") {
                submitButton.removeAttr("disabled");
            }
            else {
                submitButton.attr("disabled", "true");
            }
        });
    });

    $(function(){
        $("[clickMobile]").click(function(){
            var th = $(this);
            var form = $("[isForm]");
            var submitButton = $("[isform]").find("[type=submit]");
            var mobile = form.find("[name=mobile]");

            if (!/^(13[0-9]|14[0-9]|15[0-9]|18[0-9])\d{8}$/.test(mobile.val())) {
                return alert("手机号不正确");
            }

            $.get("/activity/20151201/mobile/check.json", {"mobile": mobile.val()}, function(result) {
                var code = result['code'];
                if (code == 0) {
                    th.attr("disabled", true);

                    var retryTime = 60;
                    var intervalTime =  window.setInterval(function(){
                        th.val("(" + retryTime + ")重试");
                        retryTime --;
                        if (retryTime <= 0) {
                            th.val("获取验证码");
                            th.removeAttr("disabled");
                            clearInterval(intervalTime);
                        }
                    }, 1000);

                    var mobileCode = result['mobileCode'];
                    $("[isMobileCode]").keyup(function(){
                        var isMobileCodeTh = $(this);
                        if (mobileCode == $.trim(isMobileCodeTh.val())) {
                            submitButton.removeAttr("disabled");
                            isMobileCodeTh.parent().removeClass("has-error");
                            isMobileCodeTh.parent().find("[errorEl]").remove();
                            clearInterval(intervalTime);
                            th.removeClass("btn-default").addClass("btn-success").val("验证码正确");
                            isMobileCodeTh.attr("isTrue", "true");
                        }
                        else {
                            isMobileCodeTh.parent().addClass("has-error");
                            isMobileCodeTh.parent().find(".control-label").remove();
                            isMobileCodeTh.parent().append('<label class="control-label" errorEl>验证码不正确</label>');
                            isMobileCodeTh.attr("isTrue", "false");
                            submitButton.attr("disabled", "true");
                        }
                    });
                }
            });
        });
    });

    $(function(){
        $("[isform]").submit(function(){
            var form = $(this);
            checkForm(form);

            if (!checkError(form) || !window.confirm("请核实信息哟，咱不支持修改哟。")) {
                return false;
            }

            if (checkError(form)) {
                form.find("[type=submit]").attr("disabled", "disabled").val("正在提交数据...");
//       不可用         form.ajaxSubmit({
//                    success: function (result) {
//                        var code = result['code'];
//                        if (code == 0) {
//                            location.href = "activity/20151201/pay/main.htm?id=" + result.user.id;
//                        }
//                        else {
//                            alert(result['msg']);
//                            form.find("[type=submit]").removeAttr("disabled").val("参加");
//                        }
//                    }
//                });
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
    $(function(){
        if (!/^\s*$/.test($(".error-message").text())) {
            alert($(".error-message").text());
        }
    });
</script>