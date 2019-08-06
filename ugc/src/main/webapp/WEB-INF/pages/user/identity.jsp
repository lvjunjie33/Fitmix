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
<body id="loading">
<jsp:include page="../common/head.jsp"></jsp:include>

<div class="container-fluid">
    <div class="row">
        <jsp:include page="../common/sidebar.jsp"></jsp:include>
        <div class="col-md-12">
            <h4 class="col-md-10 col-md-push-2 page-header"> 身份信息 </h4>
            <div class="col-md-10 col-md-push-2">
                <form method="post" id="identity-form" class="identity-form" action="/ugc/user/${user.id}" accept-charset="UTF-8" enctype="multipart/form-data">
                    <input name="_method" type="hidden" value="PATCH">
                    <div class="form-group">
                        <p>*<span>必填</span>你没有通过身份审核，部分平台功能将无法开通使用</p>
                    </div>
                    <div class="form-group">
                        <label>手机号码</label>
                        <p>${user.mobile}</p><a id="modify-mobile" data-mobile="${user.mobile}" class="btn btn-base">修改</a>
                    </div>
                    <div class="form-group">
                        <label>真实姓名</label>
                        <input class="form-control" value="${user.userIdentityInfo.realName}" name="userIdentityInfo.realName" type="text" style="width: 150px;"/>
                    </div>
                    <div class="form-group">
                        <label>身份证号码</label>
                        <input class="form-control" value="${user.userIdentityInfo.idCard}" name="userIdentityInfo.idCard" type="text" style="width: 300px;"/>
                    </div>
                    <div class="form-group">
                        <label>手持身份证照片</label>
                        <dl style="font-size: 10px; position: relative;margin-bottom: 0;">
                            <p>身份证将会进行人工审核，请务必遵循以下要求：</p>
                            <p>1.上传一张主播手持身份证原件的正面照片并确保本人面部完整出相</p>
                            <p>2.图像与文字显示清晰</p>
                            <p>3.图片格式为：png， jpg</p>
                            <p>4.图片大小不超过5M</p>
                        </dl>
                        <a id="idCardUpload" href="javascript:void(0);" class="btn btn-base identity-upload">上传照片</a>
                        <input type="text"  name="userIdentityInfo.idCardImg" value="${user.userIdentityInfo.idCardImg}" style="display: none;" accept=".jpg,.jpeg,.png,.bmg,.jPG,.jPEG,.PNG,.BMP">
                        <input type="file" id="idCardFile" name="idCardFile" style="display: none;" accept=".jpg,.jpeg,.png,.bmg,.jPG,.jPEG,.PNG,.BMP">
                    </div>
                    <div class="form-group">
                        <p>*以下内容为选填部分，可不填。若添加您的更多信息，未来将有助于您的节目加速通过审核或获得更多推荐资源。</p>
                    </div>
                    <div class="form-group">
                        <label>1.您是否是电台主播？（如果是，请填写您所属的电台及节目名称）</label>
                        <input class="form-control" name="userIdentityInfo.radio" value="${user.userIdentityInfo.radio}" type="text"/>
                    </div>
                    <div class="form-group">
                        <label>2.您现在或曾经是否在其他音频APP平台上上传过自己的节目或专辑？（如有请于下栏填写并上传截图）</label>
                        <textarea name="userIdentityInfo.radioUploadDesc" class="form-control">${user.userIdentityInfo.radioUploadDesc}</textarea>
                        <a id="radioUpload" href="javascript:void(0);" class="btn btn-base identity-upload">上传照片</a>
                        <input type="hidden" name="userIdentityInfo.radioUploadImg" value="${user.userIdentityInfo.radioUploadImg}" />
                        <input type="file" id="radioUploadFile" name="radioUploadFile" style="display: none;" accept=".jpg,.jpeg,.png,.bmg,.jPG,.jPEG,.PNG,.BMP">
                    </div>
                    <div class="form-group">
                        <label>3.您是否在各社交平台拥有粉丝？（如有请填写平台及粉丝数并截图验证）</label>
                        <textarea name="userIdentityInfo.follower" class="form-control">${user.userIdentityInfo.follower}</textarea>
                        <a id="followerUpload" href="javascript:void(0);" class="btn btn-base identity-upload">上传照片</a>
                        <input type="hidden" name="userIdentityInfo.followerImg" value="${user.userIdentityInfo.followerImg}" />
                        <input type="file" id="followerFile" name="followerFile" style="display: none;" accept=".jpg,.jpeg,.png,.bmg,.jPG,.jPEG,.PNG,.BMP">
                    </div>
                    <hr style="border-color: #999999;">
                    <a type="submit" id="identity-submit" class="btn btn-base">保存</a>
                </form>
            </div>
        </div>
    </div>
</div>

<%--<jsp:include page="../common/footer.jsp"></jsp:include>--%>
<script src="../../../static/jquery-3.1.0.min.js"></script>
<script src="../../../static/bootstrap/dist/js/bootstrap.min.js"></script>
<script src="//cdn.bootcss.com/bootbox.js/4.4.0/bootbox.min.js"></script>
<script src="../../../js/common/sidebar.js"></script>
<script src="../../../js/common/VerificationUtil.js"></script>
<script src="../../../static/loading/jquery.showLoading.min.js"></script>
<script src="../../../static/jquery.form.js"></script>
<script src="../../../js/admin/admin.js"></script>

<script>
    //初始化
    var uid = '${user.id}';
    var BASE_URI = '${base_uri}';
    Sidebar.init("^/ugc/user/(\\S){1,}/identity$");
</script>
<script src="../../../js/user/user.js"></script>
<script>

    User.identityInit();
</script>


</body>
</html>
