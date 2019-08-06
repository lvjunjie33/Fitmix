<%--
  Created by IntelliJ IDEA.
  User: weird
  Date: 2016/8/9
  Time: 18:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/pages/common/head-taglib.jsp" %>
<%@ include file="/WEB-INF/pages/common/head-base-path.jsp" %>

<html>
<head>
    <title>My Zone</title>
    <meta charset="utf-8"/>
        <meta http-equiv="X-UA-Compatible" content="IE=Edge"/>
    <jsp:include page="/WEB-INF/pages/common/my-zone-path.jsp"/>
</head>
<body style="overflow: auto;">
<jsp:include page="../common/nav.jsp"/>
<c:if test="${sessionScope.user.id == null || sessionScope.user.id == '' }"><c:redirect url="/web-user/login-page.htm"/></c:if>
<div class="background">
    <div class="filter"></div>
    <div class="container">
        <jsp:include page="zone-nav.jsp"/>
        <div id="user-center">
            <jsp:include page="user-run-info.jsp"/>
            <jsp:include page="user-person-info.jsp"/>
            <jsp:include page="account-info.jsp"/>
        </div>
        <div id="training-plan">
            <jsp:include page="training-progress.jsp"/>
            <jsp:include page="user-history-plan.jsp"/>
        </div>
        <jsp:include page="upload-avatar.jsp"/>
        <jsp:include page="account-bind.jsp"/>
        <jsp:include page="modify-password.jsp"/>
    </div>
</div>
<jsp:include page="../common/footer.jsp"/>
<script type="text/javascript" src="/static/user/js/my-zone.js"></script>
</body>
</html>
