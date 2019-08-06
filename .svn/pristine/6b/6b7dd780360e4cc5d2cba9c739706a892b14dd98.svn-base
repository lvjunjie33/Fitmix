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
    <link rel="stylesheet" type="text/css" href="../../../static/cropper/cropper.min.css" />
    <link rel="stylesheet" type="text/css" href="../../../static/loading/showLoading.css" />
    <script src="../../../static/less/less.min.js"></script>


</head>
<body id="loading">
<jsp:include page="../common/head.jsp"></jsp:include>

<div class="container-fluid">
    <div class="row">
        <jsp:include page="../common/sidebar.jsp"></jsp:include>
        <div class="col-md-12">
            <h4 class="col-md-10 col-md-push-2 page-header"> 专辑信息 </h4>
            <div class="col-md-10 col-md-push-2">
                <div class="albums-info-box row">
                    <div class="albums-cover">
                        <img id="back-image" src="../../../imgs/new-albums.png">
                        <%--<span class="albums-date">创建时间:2016/08/15</span>--%>
                    </div>
                    <div class="albums-desc">
                        <div class="title">专辑封面</div>
                        <div class="content">
                            <p>
                                建议尺寸为800*800<br>
                                支持JPG,PNG等格式<br>
                                图片大小不得超过5M<br>
                            </p>
                        </div>
                        <div class="manage">
                            <a id="upload-back-image" href="javascript:void(0);" class="btn">上传封面</a>
                            <input type="file" name="file" style="display: none;">
                        </div>
                    </div>
                </div>
                <div class="albums-info-form row">
                    <div class="col-md-7">
                        <form id="albums-form">
                            <input type="hidden" name="backImageFile" value="">
                            <input type="hidden" name="backImageName" value="">
                            <div class="form-group">
                                <label for="albumsTitle">专辑名称</label>
                                <input type="text" class="form-control" name="title" id="albumsTitle" placeholder="">
                            </div>
                            <div class="form-group">
                                <label>专辑简介</label>
                                <textarea id="desc" name="desc" class="form-control" rows="10"></textarea>
                            </div>
                            <a id="albums-submit" href="javascript:void(0);" class="btn btn-base">保存</a>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<%-- 图片裁剪模态框 --%>
<div class="modal fade" role="dialog" aria-labelledby="gridSystemModalLabel" id="jcrop-avatar">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <%--<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>--%>
                <h4 class="modal-title" id="gridSystemModalLabel">裁剪头像</h4>
            </div>
            <div class="modal-body">
                <div class="container-fluid" style="display: table;">
                    <div class="jcrop-avatar-box">
                        <img id="avatar-add" />
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <button id="cancel" type="button" class="btn btn-base btn-base-default" data-dismiss="modal">取消</button>
                <button id="avatar-submit" type="button" class="btn btn-base">保存</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->

<%--<jsp:include page="../common/footer.jsp"></jsp:include>--%>
<script src="../../../static/jquery-3.1.0.min.js"></script>
<script src="../../../static/bootstrap/dist/js/bootstrap.min.js"></script>
<script src="../../../js/common/sidebar.js"></script>
<script src="../../../static/cropper/cropper.min.js"></script>
<script src="../../../static/bootstrap/js/modal.js"></script>
<script src="../../../static/loading/jquery.showLoading.min.js"></script>
<script src="//cdn.bootcss.com/bootbox.js/4.4.0/bootbox.min.js"></script>
<script src="../../../js/common/Util.js"></script>
<script>
    //初始化
    var uid = '${user.id}';
    Sidebar.init("^/ugc/content/(\\S){1,}/albums$");
</script>
<script src="../../../js/content/content.js"></script>
<script>
    Content.AlbumsAdd.init();
</script>

</body>
</html>
