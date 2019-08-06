<%--
  Created by IntelliJ IDEA.
  User: weird
  Date: 2016/8/10
  Time: 11:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/pages/common/head-taglib.jsp" %>
<div class="user-info">
    <div class="user-info-content">
        <div class="main-info">
            <div class="modify-btn">
                <button id="info-btn">编辑</button>
                <button id="info-save-btn" style="display: none;">保存</button>
            </div>
            <div class="base-info">
                <h4>个人信息</h4>
                <label class="userName">
                    <div>昵称:</div><div id="name">${sessionScope.user.name}</div>
                </label>
                <span id="name-error" style="display: none;"></span>
                <label class="gender">
                    <div>性别:</div><div id="gender"><c:choose><c:when test="${sessionScope.user.gender == 1}">男</c:when><c:when test="${sessionScope.user.gender == 2}">女</c:when></c:choose></div>
                </label>
                <label class="age">
                    <div>年龄:</div><div id="age">${sessionScope.user.age}</div>
                </label>
            </div>
            <div class="body-info">
                <h4>体型资料</h4>
                <label class="unit">
                    单位:cm/kg
                </label>
                <label class="height">
                    <div>身高:</div><div id="height"><fmt:parseNumber integerOnly="true" value="${sessionScope.user.height}" /></div>
                </label>
                <label class="weight">
                    <div>体重:</div><div id="weight"><fmt:parseNumber integerOnly="true" value="${sessionScope.user.weight}" /></div>
                </label>
            </div>
        </div>
    </div>
</div>
