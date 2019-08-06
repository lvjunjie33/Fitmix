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
    <link rel="stylesheet" type="text/css" href="../../../static/dropzone/dist/dropzone.css" />
    <script src="../../../static/less/less.min.js"></script>
    <style>
        .dropzone {
            border-width: 2px;
            border-style: dashed;
            border-color: rgb(0, 135, 247);
            border-radius: 5px;
        }

    </style>

</head>
<body>
<jsp:include page="../common/head.jsp"></jsp:include>

<div class="container-fluid">
    <div class="row">
        <jsp:include page="../common/sidebar.jsp"></jsp:include>
        <div class="col-md-12">
            <h4 class="col-md-10 col-md-push-2 page-header"> 音乐上传 </h4>
            <div class="col-md-10 col-md-push-2">
                <div class="upload-box row">
                    <div class="col-sm-12 col-md-10 col-lg-8">
                        <form action="/ugc/content/${user.id}/albums/${currentMixAlbums.id}/mix"
                              method="post" enctype="multipart/form-data"
                              class="dropzone"
                              id="my-awesome-dropzone">
                            <div class="dz-default dz-message"><span style="color: #00a8e6;">请将要上传的文件拖拽到此区域 或 点击上传</span></div>
                        </form>
                    </div>
                </div>
                <div class="upload-desc row">
                    <div class="col-md-12">
                        <p>支持 等格式，大小不超过150M</p>
                        <p>如上传内容存在色情、赌博或侵犯第三方合法权益等违反中华人民共和国法律法规或公序良俗内容的，乐享动收到举报/投诉后有权将节目下架</p>
                        <p>上传小说类有声内容，请提供相关授权文件，戳右边查看具体<span>授权材料</span></p>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<%--<jsp:include page="../common/footer.jsp"></jsp:include>--%>
<script src="../../../static/jquery-3.1.0.min.js"></script>
<script src="../../../static/bootstrap/dist/js/bootstrap.min.js"></script>
<script src="../../../static/dropzone/dist/min/dropzone.min.js"></script>
<script src="../../../js/common/sidebar.js"></script>
<script>
    //初始化
    var uid = '${user.id}';
    Sidebar.init("^/ugc/content/(\\S){1,}/albums/(\\S){1,}/mix/upload$");
</script>
<script>
    // "myAwesomeDropzone" is the camelized version of the HTML element's ID
    Dropzone.options.myAwesomeDropzone = {
        paramName: "file", // The name that will be used to transfer the file
        maxFilesize: 150, // MB
        parallelUploads: 2,
        acceptedFiles: ".mp3,.m4a,.wav",
        accept: function(file, done) {
             done();
        },
        success: function(file, result) {
            $(file.previewTemplate).children('.dz-progress').css('opacity', '0');
            if(result.code == 0) {
                if (file.previewElement) {
                    return file.previewElement.classList.add("dz-success");
                }
            } else {
                result = JSON.parse(result);
                var node, _i, _len, _ref, _results;
                file.previewElement.classList.add("dz-error");
                _ref = file.previewElement.querySelectorAll("[data-dz-errormessage]");
                _results = [];
                for (_i = 0, _len = _ref.length; _i < _len; _i++) {
                    node = _ref[_i];
                    _results.push(node.textContent = result.message);
                }
                return _results;
            }
        },
        error: function(file, result) {
            console.log("error: "+result);
            $(file.previewTemplate).children('.dz-progress').css('opacity', '0');
            var node, _i, _len, _ref, _results;
            file.previewElement.classList.add("dz-error");
            _ref = file.previewElement.querySelectorAll("[data-dz-errormessage]");
            _results = [];
            for (_i = 0, _len = _ref.length; _i < _len; _i++) {
                node = _ref[_i];
                _results.push(node.textContent = result);
            }
            return _results;
        },
        complete: function(file, result) {
            console.log("complete: "+result);
        }
    };
</script>


</body>
</html>
