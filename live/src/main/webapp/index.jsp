<%--
  Created by IntelliJ IDEA.
  User: sin
  Date: 15/3/30
  Time: 下午7:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" trimDirectiveWhitespaces="true"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<html>
<body>
<div id="messages"></div>
<script type="text/javascript" src="static/socket.js"></script>
<script type="text/javascript">
    lch.webSocket()('ws://localhost:8069/live/socket-server', function (event) {
        document.getElementById('messages').innerHTML += '<br />' + event.data;
    });


</script>
<h1>直播欢迎页面</h1>
<%--<script type="text/javascript">--%>
    <%--window.onbeforeunload = function () {--%>
        <%--return "是否关闭";--%>
    <%--};--%>
<%--</script>--%>
</body>
</html>
