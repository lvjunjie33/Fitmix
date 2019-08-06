<%--
  Created by IntelliJ IDEA.
  User: weird
  Date: 2016/8/4
  Time: 16:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/pages/common/head-taglib.jsp" %>
<%@ include file="/WEB-INF/pages/common/head-base-path.jsp" %>

<html>
<head>
    <title>login</title>
    <meta http-equiv="X-UA-Compatible" content="IE=Edge"/>
    <jsp:include page="/WEB-INF/pages/common/user-path.jsp"/>
</head>
<body>
<c:if test="${sessionScope.user.id != null && sessionScope.user.id != '' }"><c:redirect url="/web-user/my-zone.htm"/></c:if>
    <jsp:include page="../common/nav.jsp"/>
    <jsp:include page="login-bar.jsp"/>
    <jsp:include page="../common/footer.jsp"/>
</body>
</html>
