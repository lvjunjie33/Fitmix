<%--
  Created by IntelliJ IDEA.
  User: edward
  Date: 2016/4/25
  Time: 17:17
  To change this template use File | Settings | File Templates.
--%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
    String httpsPath = "https://" + request.getServerName() + ":443";
%>
