<%--
  Created by IntelliJ IDEA.
  User: weird
  Date: 2016/8/9
  Time: 18:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/pages/common/head-taglib.jsp" %>
<div class="zone-nav">
    <div class="face-name">
        <div class="face" id="avatar"><img src="${sessionScope.user.avatar}"/><div class='change-avatar' >更换头像</div></div>
        <div class="name">${sessionScope.user.name}</div>
    </div>
    <div class="nav-content">
        <li><a class="checked" id="person-center">个人中心</a></li>
<%--        <li><a id="my-data">我的数据</a></li>
        <li><a id="my-music">我的音乐</a></li>--%>
        <li><a id="my-training-plan">训练计划</a></li>
<%--        <li><a id="club">俱乐部</a></li>--%>
    </div>
</div>