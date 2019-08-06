<%--
  Created by IntelliJ IDEA.
  User: edward
  Date: 2016/4/14
  Time: 15:01
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
    <title>${themeName}</title>

    <link rel="stylesheet" href="/static/bootstrap/bootstrap.min.css">
    <script src="/static/jquery-1.11.2.min.js"></script>
    <script type="text/javascript" src="static/jquery.form.js"></script>

    <style>
        body, h1, h2, h3, h4, h5, h6, .uk-navbar-nav > li > a { font-family:  "Microsoft Yahei","微软雅黑","Proxima Nova", "Helvetica Neue", Helvetica, Arial, sans-serif; }

        body, html{
            position: relative;
            height: 100%;
            background-color: #FFFFFF;
            padding: 0px;
            margin:0px;
            max-width: 600px;
            width: 100%;
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
        .span-left{
            display: inline-block;
            width: 120px;
            text-align: right;
            margin-right: 20px;
        }
    </style>
</head>
<script type="text/javascript">
    var ua = navigator.userAgent.toLowerCase();
    var isWeixin = ua.indexOf('micromessenger') != -1;
    var isAndroid = ua.indexOf('android') != -1;
    var isIos = (ua.indexOf('iphone') != -1) || (ua.indexOf('ipad') != -1);
    /*if (!isWeixin) {
        document.head.innerHTML = '<title>抱歉，出错了</title><meta charset="utf-8"><meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=0"><link rel="stylesheet" type="text/css" href="https://res.wx.qq.com/connect/zh_CN/htmledition/style/wap_err1a9853.css">';
        document.body.innerHTML = '<div class="page_msg"><div class="inner"><span class="msg_icon_wrp"><i class="icon80_smile"></i></span><div class="msg_content"><h4>请在微信客户端打开链接</h4></div></div></div>';
    }*/
</script>

<body>
<form action="/activity/find-activity-sign-up.htm">
    <div class="form-group">
        <input type="number" name="mobile" class="form-control input-lg" placeholder="手机号码"/>
        <input type="hidden" name = "activityId" value="${activityId}" />
    </div>
    <div class="form-group">
        <input type="submit" class="btn btn-info form-control input-lg" value="报名查询" />
    </div>
    <div class="form-group" style="height: 30px;text-align: center">
        <a href="/activity/to-activity.htm?activityId=${activityId}&uid=${activityUid}&enforce=1">活动详情介绍></a>
    </div>
</form>

<c:choose>
    <c:when test="${!empty activitySignUp}">
        <div>
            <div style="margin: 20px 20px 5px 20px;">
                    <div class="form-group">
                        <div class="form-group" style = "text-align: center;">
                            <strong >${activitySignUp.groupName}</strong>
                        </div>
                        <div id = "bao-ming-info" style = "padding-top: 12px;">
                            <c:forEach var="member" items="${activitySignUp.activitySignUpMembers}">
                                <div>
                                    <div class="form-group">
                                        <strong>${member.groupMemberName}</strong>
                                    </div>
                                    <c:if test="${signUpMode1 == 1}">
                                        <div class="form-group">
                                            <span class = "span-left">姓名：</span><span>${member.name}</span>
                                        </div>
                                    </c:if>
                                    <c:if test="${!empty member.mobile}">
                                        <div class="form-group">
                                            <span class = "span-left">手机号码：</span><span>${member.mobile}</span>
                                        </div>
                                    </c:if>
                                    <c:if test="${signUpMode2 == 1}">
                                        <div class="form-group">
                                            <span class = "span-left">性别：</span><span>${member.gender}</span>
                                        </div>
                                    </c:if>
                                    <c:if test="${signUpMode3 == 1}">
                                        <div class="form-group">
                                            <span class = "span-left">衣服尺码：</span><span>${member.clothesSize}</span>
                                        </div>
                                    </c:if>
                                    <c:if test="${signUpMode6 == 1}">
                                        <div class="form-group">
                                            <span class = "span-left">${member.idCardType}：</span><span>${member.idCard}</span>
                                        </div>
                                    </c:if>
                                    <c:if test="${signUpMode4 == 1}">
                                        <div class="form-group">
                                            <span class = "span-left">备注：</span><span>${member.memo}</span>
                                        </div>
                                    </c:if>
                                </div>
                            </c:forEach>
                        </div>
                    </div>

                    <div class="form-group">
                        <strong>通知信息</strong>
                    </div>

                <c:if test="${signUpMode7 == 7}">
                    <div class="form-group">
                        <span class = "span-left">邮箱：</span><span>${activitySignUp.email}</span>
                    </div>
                </c:if>
                    <div class="form-group">
                        <span class = "span-left">联系人：</span><span>${activitySignUp.mobileName}</span>
                    </div>
                    <div class="form-group">
                        <span class = "span-left">联系人电话：</span><span>${activitySignUp.mobilePhone}</span>
                    </div>
                <c:if test="${signUpMode8 == 8}">
                    <div class="form-group">
                        <span class = "span-left">紧急联系人：</span><span>${activitySignUp.emergencyName}</span>
                    </div>
                </c:if>
                <c:if test="${signUpMode9 == 9}">
                    <div class="form-group">
                        <span class = "span-left">紧急联系人电话：</span><span>${activitySignUp.emergencyPhone}</span>
                    </div>
                </c:if>
                    <div class="form-group">
                        <strong>其他信息</strong>
                    </div>
                    <div class="form-group">
                        <p>参赛金额：<label id="pay-money">${activitySignUp.needMoney}</label></p>
                    </div>
            </div>
        </div>
        <c:if test="${activitySignUp.tradeStatus != 2}">
            <%--  register btn submit  --%>
            <c:choose>
                <c:when test="${closeSignUp eq 1}">
                    <div id="register">
                        <label style="color: red" href="javascript:void(0)">活动报名结束，未支付</label>
                    </div>
                </c:when>
                <c:otherwise>
                    <div id="register">
                        <a class="btn-radius" href="/activity/to-activity-pay.htm?activityId=${activityId}&mobile=${activitySignUp.mobilePhone}">去支付</a>
                    </div>
                </c:otherwise>
            </c:choose>
        </c:if>
    </c:when>
    <c:otherwise>
        <c:if test="${!empty mobile}" >
        <div>
            <div style="margin: 20px 20px 5px 20px;">
                <div class="form-group">
                    <div class="form-group" style = "text-align: center;">
                        <strong >亲！该赛事您没有报名哦</strong>
                    </div>
                </div>
            </div>
        </div>
        </c:if>
    </c:otherwise>
</c:choose>
</body>
</html>
