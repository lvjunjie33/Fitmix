<%--
  Created by IntelliJ IDEA.
  User: edward
  Date: 2016/4/14
  Time: 13:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%@ include file="/WEB-INF/pages/common/head-base-path.jsp" %>
<html>
<head>
    <meta charset="utf-8">
    <!-- 禁止缩放 -->
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <meta name="keywords" content="igeekery 生活 运动 服务 一体化，我们为您提供最 智能的硬件 软件 服务平台">
    <meta name="description" content="igeekery 是深圳第一蓝筹科技有限公司提供的 软硬件（智能服务，智能硬件）服务平台, 我们提供最优质 Mix 运动音乐和最智能的软硬件，让运动更方便，让喜欢运动的人群得到更好的运动体验。">
    <title>${themeName}</title>

    <link rel="stylesheet" href="/static/bootstrap/bootstrap.min.css">
    <script src="/static/jquery-1.11.2.min.js"></script>

    <style>
        body, h1, h2, h3, h4, h5, h6, .uk-navbar-nav > li > a { font-family:  "Microsoft Yahei","微软雅黑","Proxima Nova", "Helvetica Neue", Helvetica, Arial, sans-serif; }

        body, html{
            position: relative;
            height: 100%;
            background-color: #FFFFFF;
            padding: 0px;
            margin:0px;
        }

        #activity{
            max-width: 600px;
            margin: 0px auto;
        }

        img{
            width: 100%;
        }

        .btn-radius {
            padding: 5px 30px;
            background-color: transparent;
            border-radius: 20px;
            -moz-border-radius: 20px;
            -webkit-border-radius: 20px;

            color: #FC9A39;
            text-decoration: none;
            border: 2px solid #FC9A39;
            display: inline-block;
        }

        .btn-radius:hover{
            color: #0CB4E8;
            text-decoration: none;
            border: 2px solid #0CB4E8;
        }

        #register{
            /*position: fixed;*/
            /*bottom: 0px;*/
            /*width: 100%;*/
            /*background-color: rgba(0,0,0,0.5);*/
            text-align: center;
            padding: 10px 0px;
        }
        .bao-ming{
            max-width: 100%;
            min-height: 1em;
            white-space: pre-wrap;
            color: rgb(62, 62, 62);
            background-color: rgb(255, 255, 255);
            text-align: center;
            line-height: 1.75em;
            box-sizing: border-box !important;
            word-wrap: break-word !important;
            margin-top: -10px;
        }
        .bao-ming span{
            max-width: 100%;
            font-size: 14.44444465637207px;
            line-height: 27.986112594604492px;
            box-sizing: border-box !important;
            word-wrap: break-word !important;
        }

        .bottomNav {
            position: fixed;
            height: 60px;
            background-color: #242424;
            bottom: 0px;
            width: 100%;
        }

        .fitmixImg {
            width: 40px;
            height: 40px;
            position: absolute;
            top: 10px;
            left: 20px;
        }

        .fitmixTextDiv {
            position: absolute;
            left: 80px;
            height: 60px;
            color: #ffffff;
        }

        .fitmixTitle {
            margin-top: 8px;
            font-size: 16px;
        }

        .fitmixTitleMin {
            /*margin-top: 4px;*/
            font-size: 14px;
        }
        .buttonText {
            position: absolute;
            width: 103px;
            height: 36px;
            -webkit-background-size: 100% 100%;
            background-size: 100% 100%;
            right: 20px;
            top: 15px;
            text-align: center;
            line-height: 36px;
            border-radius: 3px;
            background-color: #FFB10E;
            color: #FFFFFF;
        }
    </style>
</head>
<body>
<div id="activity">
    ${htmlContent}
</div>

<c:if test = "${!empty appUserInfo}">
    <div>
        <p class = "bao-ming">已报名用户点这里可以<a href="/activity/find-activity-sign-up.htm?activityId=${activityId}">【查询报名信息】</a></p>
    </div>
</c:if>

<c:choose>
    <c:when test = "${activityType == 0}" ><%-- 普通赛事 --%>
        <jsp:include page="type/common-activity.jsp"/>
    </c:when>
    <c:when test = "${activityType == 1}" ><%-- 积分赛事 --%>
        <jsp:include page="type/integral-activity.jsp"/>
    </c:when>
    <c:when test = "${activityType == 2}" ><%-- 三方赛事 --%>

    </c:when>
    <c:when test = "${activityType == 3}" ><%-- 标准赛事 --%>
        <jsp:include page="type/common-activity.jsp"/>
<%--        <jsp:include page="type/normal-activity.jsp"/>--%>
    </c:when>
    <c:otherwise>
        <%--<jsp:include page="type/history-activity.jsp"/>--%>
    </c:otherwise>
</c:choose>
<c:if test="${empty groups and activityType != 2 and empty activityUid}">
    <div class="bottomNav" >

        <img class="fitmixImg" src="/imgs/mixShare/FTIMIX@2x.png"/>

        <div class="fitmixTextDiv">
            <p class="fitmixTitle" style="margin-bottom: 3px">乐享动APP</p>

            <p class="fitmixTitleMin">好玩的运动APP</p>
        </div>
        <a href="http://a.app.qq.com/o/simple.jsp?pkgname=com.fitmix.sdk">
            <div class="buttonText">
                下载
            </div>
        </a>
    </div>
</c:if>

<script>
    $("#save-activity-sign-up-submit").click(function(){

        //条款校验
        var $this = $("#agree-clause-id");
        if ($this != null && $this != undefined) {
            var isChecked = $this.attr("checked");
            if (isChecked == undefined) {
                alert("亲！先看下条款哦");
                return false;
            }
        }

        $("[isform]").submit();
    });

</script>

<script>
    $(function(){

        $("[isform]").submit(function(){
            var form = $(this);
            $fromTools.checkForm(form);

            if (!$fromTools.checkError(form) || !window.confirm("请核实信息，提交后不可修改。")) {
                return false;
            }

            if ($fromTools.checkError(form)) {
                $("#register").find("[type=submit]").attr("disabled", "disabled").html("正在提交数据...");
                var signUpMembersInfo = new Array();
                var groupId = "";
                $("[isForm]").find(".group-member").each(function(i, element){
                    var $this = $(this);
                    var memberJson = $this.sequenceJson()[0];
                    signUpMembersInfo[i] = memberJson;
                    groupId = $this.attr("group-id");
                });

                var email = form.find("[name=email]").val();
                var mobileName = form.find("[name=mobileName]").val();
                var mobilePhone = form.find("[name=mobilePhone]").val();
                var emergencyName = form.find("[name=emergencyName]").val();
                var emergencyPhone = form.find("[name=emergencyPhone]").val();
                var userTeamName = form.find("[name=userTeamName]").val();
                var userTeamSlogan = form.find("[name=userTeamSlogan]").val();


                $.ajax({
                    type: "POST",
                    url: "activity/save-activity-sign-up.json",
                    data:{
                        "activityId" : ${activityId},
                        "email" : email,
                        "mobileName" : mobileName,
                        "mobilePhone" : mobilePhone,
                        "emergencyName" : emergencyName,
                        "emergencyPhone" : emergencyPhone,
                        "userTeamName" : userTeamName,
                        "userTeamSlogan": userTeamSlogan,
                        "signUpMembersInfo" : JSON.stringify(signUpMembersInfo),
                        "groupId" : groupId
                    },
                    dataType:"json",
                    success:function (result) {
                        if (result.code == 6001) {
                            location.href = result["redirect"];
                        } else if (result.code == 6002) {
                            alert(result['msg']);
                        } else {
                            alert(result['msg']);
                        }
                        location.href = location.href;
                    },
                    error:function (XMLHttpRequest,textStatus,errorThrom){
                        console.log("不明原因造成数据获取失败... ...");
                    }
                });
            }
            return false;
        });
    })
</script>

<script>
    if (typeof $fromTools == "undefined") {
        var $fromTools = function () {

            function reloadFormCheck() {
                $("[isform] input").keyup(function(){
                    checkForm($(this).parent());
                });
            }

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

                    var isTrue = isIdCardNo(val);

                    if (!/(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/.test(val)) {
                        if (!th.parent().find("[errorEl]")[0]) {
                            th.parent().addClass("has-error");
                            th.parent().append('<label class="control-label" errorEl>身份证不正确</label>');
                        }
                    } else if(!isTrue) {
                        th.parent().addClass("has-error");
                        th.parent().append('<label class="control-label" errorEl>身份证不正确</label>');
                    } else {
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

            return {
                reloadFormCheck:reloadFormCheck,
                checkError:checkError,
                checkForm:checkForm,
                appendError:appendError
            };
        }();
    }

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

<script>
    $(function(){
        $(".form-group").find("[name=group]").change(function() {
            var $this= $(this);
            var _id = "#group" + $this.val();
            $("#bao-ming-info").html($(_id).html());
            $("#pay-money").html($(_id).attr("money"));

            $fromTools.reloadFormCheck();
        });
        $(".form-group").find("[name=group]").change();
    });
</script>
</body>
</html>

<script type="text/javascript">
    function isIdCardNo(num) {
        var factorArr = new Array(7,9,10,5,8,4,2,1,6,3,7,9,10,5,8,4,2,1);
        var error;
        var varArray = new Array();
        var intValue;
        var lngProduct = 0;
        var intCheckDigit;
        var intStrLen = num.length;
        var idNumber = num;
        // initialize
        if ((intStrLen != 15) && (intStrLen != 18)) {
            //error = "输入身份证号码长度不对！";
            //alert(error);
            //frmAddUser.txtIDCard.focus();
            return false;
        }
        // check and set value
        for(i=0;i<intStrLen;i++) {
            varArray[i] = idNumber.charAt(i);
            if ((varArray[i] < '0' || varArray[i] > '9') && (i != 17)) {
                //error = "错误的身份证号码！.";
                //alert(error);
                //frmAddUser.txtIDCard.focus();
                return false;
            } else if (i < 17) {
                varArray[i] = varArray[i]*factorArr[i];
            }
        }
        if (intStrLen == 18) {
            //check date
            var date8 = idNumber.substring(6,14);
            if (checkDate(date8) == false) {
                //error = "身份证中日期信息不正确！.";
                //alert(error);
                return false;
            }
        // calculate the sum of the products
        for(i=0;i<17;i++) {
            lngProduct = lngProduct + varArray[i];
        }
        // calculate the check digit
        intCheckDigit = 12 - lngProduct % 11;
        switch (intCheckDigit) {
            case 10:
            intCheckDigit = 'X';
            break;
            case 11:
            intCheckDigit = 0;
            break;
            case 12:
            intCheckDigit = 1;
            break;
        }
            // check last digit
            if (varArray[17].toUpperCase() != intCheckDigit) {
                //error = "身份证效验位错误!...正确为： " + intCheckDigit + ".";
                //alert(error);
                return false;
            }
        }
        else {        //length is 15
            //check date
            var date6 = idNumber.substring(6,12);
            if (checkDate(date6) == false) {
                //alert("身份证日期信息有误！.");
                return false;
            }
    }
        //alert ("Correct.");
        return true;
    }
    function checkDate(date)
    {
        return true;
    }
</script>
