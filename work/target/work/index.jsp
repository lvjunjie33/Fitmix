<%--
  Created by IntelliJ IDEA.
  User: sin
  Date: 2015/4/23
  Time: 16:52
  To change this template use File | Settings | File Templates.
--%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
    response.sendRedirect(basePath + "login.htm");
%>
