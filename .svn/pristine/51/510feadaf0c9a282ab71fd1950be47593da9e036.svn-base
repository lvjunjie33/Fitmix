<%--
  Created by IntelliJ IDEA.
  User: fenxio
  Date: 2016/8/18
  Time: 15:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="../common/head-base-path.jsp"%>
<html>
<head>
    <title>UGC</title>
    <base href="<%=basePath%>">
    <link rel="stylesheet/less" type="text/css" href="../../../css/common/main.less" />
    <script src="../../../static/less/less.min.js"></script>


</head>
<body>
<jsp:include page="../common/head.jsp"></jsp:include>

<div class="container-fluid">
    <div class="row">
        <jsp:include page="../common/sidebar.jsp"></jsp:include>
        <div class="col-md-12">
            <h4 class="col-md-10 col-md-push-2 page-header"> 专辑管理 </h4>
            <div class="col-md-10 col-md-push-2">
                <div class="albums-list row">
                    <c:forEach items="${mixAlbumsList}" var="each">
                        <div class="albums-item">
                            <div class="albums-cover">
                                <img src="${base_uri}/${each.backImage}" />
                            </div>
                            <div class="albums-title">
                                ${each.title}
                            </div>
                            <div class="albums-manage">
                                <a class="btn" href="javascript:void(0)">分享</a>
                                <a class="btn" href="/ugc/content/${user.id}/albums/${each.id}">编辑</a>
                                <a class="btn albums-delete" href="javascript:void(0)" data-id="${each.id}">删除</a>
                            </div>
                        </div>
                    </c:forEach>
                    <div class="albums-item albums-new">
                        <div class="albums-cover">
                            <img src="../../../imgs/new-albums.png" />
                        </div>
                        <div class="albums-title">
                            创建专辑
                        </div>

                    </div>

                </div>
            </div>
        </div>
    </div>
</div>

<%--<jsp:include page="../common/footer.jsp"></jsp:include>--%>
<script src="../../../static/jquery-3.1.0.min.js"></script>
<script src="../../../static/bootstrap/dist/js/bootstrap.min.js"></script>
<script src="../../../js/common/sidebar.js"></script>
<script src="//cdn.bootcss.com/bootbox.js/4.4.0/bootbox.min.js"></script>
<script>
    //初始化
    var uid = '${user.id}';
    Sidebar.init("^/ugc/content/(\\S){1,}/albums$");
</script>
<script src="../../../js/content/content.js"></script>
<script>
    Content.Albums.init();
</script>

</body>
</html>
