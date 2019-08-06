<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
    String httpsPath = "https://" + request.getServerName() + ":443";
%>
<script>
    window.location.href = '${basePath}bbs';
</script>
