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
            <h4 class="col-md-10 col-md-push-2 page-header"> 入驻审核 </h4>
            <div class="col-md-10 col-md-push-2">
                <p class="enter-info">恭喜您注册成功！ 接下来简单两步完成审核成为乐享动入驻主播，让上亿用户听见你的声音！</p>

            </div>
        </div>
        <div class="col-md-12 enter-box">
            <div class="col-md-10 col-md-push-2 enter-item">
                <div class="enter-step">
                    <span class="circle">1</span>
                    <a href="/ugc/user/${user.id}/identity" class="btn btn-base">提交身份信息</a>
                    <p>提供身份证信息，上传身份证照片与补充身份材料</p>
                </div>
                <div class="enter-step">
                    <span class="circle">2</span>
                    <a href="/ugc/content/${user.id}/albums" class="btn btn-base">上传第一个节目</a>
                    <p>创建一张新专辑，并上传第一期节目</p>
                </div>
                <div class="enter-vertical"></div>
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
    Sidebar.init("^/ugc/user/(\\S){1,}/enter$");
</script>


</body>
</html>
