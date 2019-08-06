
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

        body{
            margin-left: 15px;
            margin-right: 15px;
        }

        .teamName{
            color: #56b6d3
        }

        .userInfoBody{
            height: auto;
            text-align: left;
            width: 90%;
            text-align: center;
        }
    </style>

</head>

<body class="text-center">
    <h3 class="teamName">${runTeam.teamName}</h3>


    <div style="text-align: center;">
        当前人数：${fn:length(runTeam.userActivityList)}/20
    </div>


    <c:if test="${empty runTeam.userActivityList}">
        <br/>
        <div>还没有小伙伴们加入.</div>
    </c:if>

    <table class="table table-condensed table-hover ">

        <tbody>
            <c:forEach var="item" items="${runTeam.userActivityList}">
                <c:choose>
                    <c:when test="${runTeam.teamUserMobile eq item.mobile}">
                        <tr>
                            <td style="width: 49%;text-align: center;color: #ff4d2f;">${item.name}</td>
                            <td style="width: 49%;text-align: center;color: #ff4d2f;">${item.gender}(${item.id})</td>
                        </tr>
                    </c:when>
                    <c:otherwise>
                        <tr>
                            <td style="width: 49%;text-align: center">${item.name}</td>
                            <td style="width: 49%;text-align: center">${item.gender}(${item.id})</td>
                        </tr>
                    </c:otherwise>
                </c:choose>
            </c:forEach>
        </tbody>
    </table>

    <div style="text-align: center">
        <span style="position: relative;bottom: -20px;">扫扫 即可让您的小伙伴们加入.</span>
        <img src="${runTeam.EWUrl}" style="width: 90%"/>
    </div>

    <br/>

    <c:if test="${not (empty runTeam.currentUserActivity)}">

    <div class="userInfoPanel container-fluid" data-example-id="textarea-form-control">
        <div class="userInfoBody">
            <div class="form-group">
                <span>个人资料</span>
            </div>
            <div class="form-group">
                <span>姓名：${runTeam.currentUserActivity.name}</span>
            </div>
            <div class="form-group">
                <span>年龄：${runTeam.currentUserActivity.age}</span>
            </div>
            <div class="form-group">
                <span>性别：${runTeam.currentUserActivity.gender}</span>
            </div>

            <div class="form-group">
                <span>手机：${runTeam.currentUserActivity.mobile}</span>
            </div>
            <div class="form-group">
                <span>职业：${runTeam.currentUserActivity.professional}</span>
            </div>
            <div class="form-group">
                <span>单位：${runTeam.currentUserActivity.company}</span>
            </div>
            <div class="form-group">
                <span>血型：${runTeam.currentUserActivity.bloodType}</span>
            </div>

            <div class="form-group">
                <span>身份证：${runTeam.currentUserActivity.userCard}</span>
            </div>
            <div class="form-group">
                <span>通讯地址：${runTeam.currentUserActivity.communicationAddress}</span>
            </div>
            <div class="form-group">
                <span>衣服尺码：${runTeam.currentUserActivity.clothesSize}</span>
            </div>

            <div class="form-group">
                <span>紧急人联系姓名：${runTeam.currentUserActivity.emergencyUserName}</span>
            </div>
            <div class="form-group">
                <span>紧急人联系电话：${runTeam.currentUserActivity.emergencyUserMobile}</span>
            </div>

            <div class="form-group">
                <span>备注：${runTeam.currentUserActivity.memo}</span>
            </div>
        </div>
    </c:if>

    </div>
</body>
</html>

<%--<script>--%>
    <%--window.setInterval(function(){--%>
        <%--location.reload();--%>
    <%--}, 5000)--%>
<%--</script>--%>
