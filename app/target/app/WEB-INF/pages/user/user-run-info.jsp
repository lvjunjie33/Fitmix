<%--
  Created by IntelliJ IDEA.
  User: weird
  Date: 2016/8/9
  Time: 19:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/pages/common/head-taglib.jsp" %>

<div class="run-info">
    <%--<div class="face"></div>--%>
    <div class="info">
        <div class="person-info">
            <div class="name-intro">
                <label class="info-name">${sessionScope.user.name}</label>
                <label class="info-intro" id="intro"><c:choose><c: when test="${sessionScope.user.signature == '' || sessionScope.user.signature == null}">编辑个性签名</c:when><c:when test="${sessionScope.user.signature != '' && sessionScope.user.signature != null}">${sessionScope.user.signature}</c:when></c:choose></label>
            </div>
            <%--<div class="modify-btn">
                <button>修改</button>
            </div>--%>
        </div>
        <div class="run-data">
            <label class="total-distance">
                <div class="data"><fmt:formatNumber type="number" value="${sessionScope.user.distance/1000}" pattern="0.00" maxFractionDigits="2"/></div>
                <div class="data-name">总里程(km)</div>
            </label>
            <label class="total-time">
                <div class="data"><fmt:formatNumber type="number" value="${ sessionScope.user.runTime/60}" pattern="0.00" maxFractionDigits="2"/></div>
                <div class="data-name">总时间(h)</div>
            </label>
            <label class="total-consume">
                <div class="data"><fmt:formatNumber type="number" value="${sessionScope.user.calorie/1000}" pattern="0.00" maxFractionDigits="2"/></div>
                <div class="data-name">总消耗(C)</div>
            </label>
            <label class="total-step">
                <div class="data">${sessionScope.user.step}</div>
                <div class="data-name">总步数</div>
            </label>
        </div>
    </div>
</div>
