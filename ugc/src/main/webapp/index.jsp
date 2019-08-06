<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
    String httpsPath = "https://" + request.getServerName() + ":443";
%>
<html>
<head>
    <title>UGC 登录</title>
    <base href="<%=basePath%>">
</head>
<body>
<script>
    window.location.href = "/ugc/admin/login";
</script>
<%--<jsp:forward page="/ugc/admin/login"></jsp:forward>--%>
</body>
</html>
