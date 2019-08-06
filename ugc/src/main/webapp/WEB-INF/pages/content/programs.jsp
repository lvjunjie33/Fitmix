<%--
  Created by IntelliJ IDEA.
  User: fenxio
  Date: 2016/8/18
  Time: 15:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="lc" uri="http://tag.lch.com/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ include file="../common/head-base-path.jsp" %>
<html>
<head>
    <title>UGC</title>
    <base href="<%=basePath%>">
    <link rel="stylesheet/less" type="text/css" href="../../../css/common/main.less"/>
    <script src="../../../static/less/less.min.js"></script>
    <style>
        body {
            padding-right: 0px !important;
        }
    </style>

</head>
<body id="loading">
<jsp:include page="../common/head.jsp"></jsp:include>

<div class="container-fluid">
    <div class="row">
        <jsp:include page="../common/sidebar.jsp"></jsp:include>
        <div class="col-md-12">
            <h4 class="col-md-10 col-md-push-2 page-header"> 节目列表 </h4>

            <c:if test="${mixAlbumsList == null || fn:length(mixAlbumsList) < 1}">
                <div class="col-md-10 col-md-push-2">
                    <div style="text-align: center;color: gray;">
                        <p style="font-size: 22px">欢迎加入乐享动!</p>
                        <p style="height: 30px;font-size:15px;line-height: 30px">您还没有创建专辑，立即 <a style="color:#d14946" href="/ugc/content/${user.id}/albums/add">新建专辑</a></p>
                    </div>
                </div>
            </c:if>

            <c:if test="${mixAlbums.checkStatus != 1 && fn:length(mixAlbumsList) > 0}">
                <div class="col-md-10 col-md-push-2">
                    <div style="text-indent: 1em">
                        <div class="alert alert-warning warning">
                            <p>专辑正在审核中，符合审核要求的专辑， 将于2个工作日后通过审核。</p>
                            <p>专辑审核通过后，此提示会自动消失。如有疑问可<a href="javascript:qq_hotline();">联系乐享动小编</a>;</p>
                            <p>专辑审核要求：</p>
                            <p>1.主播需已通过身份信息审核</p>
                            <p>2.专辑封面为不小于800*800像素的静态图片，图片上无水印、广告等信息；</p>
                            <p>3.专辑简介内完整填写对本专辑的文字描述，仅填写主播个人微博微信等信息不予通过；</p>
                            <p>4.至少已上传一期节目；<a href="/ugc/content/${user.id}/albums/${currentMixAlbums.id}/mix/upload">点我立刻去上传</a>；</p>
                            <p>5.上传节目符合所选分类特性；</p>
                            <p>6.上传内容无色情、赌博或侵犯第三方合法权益等违反中华人民共和国法律法规或公序良俗内容；</p>
                            <p>7.上传小说类有声内容，请提供相关授权文件，<a href="javascript:boot_box_alert(info_con)">点我查看需提供的授权材料</a>。</p>
                        </div>
                        <div class="alert alert-info">
                            <p>
                                <span>小编说:</span>
                                ${mixAlbums.checkMessage}
                            </p>
                        </div>
                    </div>
                </div>
            </c:if>
            <c:if test="${mixAlbumsList != null && fn:length(mixAlbumsList) >0}">
                <div class="col-md-10 col-md-push-2">
                <div class="programs-box">
                    <table class="programs-box-content table">
                        <thead>
                        <tr>
                            <th width="20px">
                            </th>
                            <th style="width: 50%;overflow: hidden">标题</th>
                            <th>创建时间</th>
                            <th style="text-align:center" width="12%">分享</th>
                            <th>播放次数</th>
                            <th>操作</th>
                        </tr>
                        </thead>
                        <tbody>
                            <tr id="template" style="display: none;">
                                <td>
                                    <div class="index">#index#</div>
                                </td>
                                <td>
                                    <div id="#id#" class="title">#name#</div>
                                </td>
                                <td>
                                    <h6>#addTime#</h6>
                                </td>
                                <td class="share">
                                    <span class="share-row">
                                        <a data-value="sina" title="分享到微博">微博</a>
                                        <a data-value="wechat" title="分享到微信">微信</a>
                                        <a data-value="copy-link" title="获取链接">链接</a>
                                    </span>
                                </td>
                                <td>
                                    <div class="play-times"><span
                                            class="glyphicon glyphicon-music"></span>&nbsp;<span>#auditionCount#</span></div>
                                </td>
                                <td>
                                    <div class="programs-manage">
                                        <a class="program-source hide" href="http://od.qingting.fm/live" target="_blank">音频链接</a>
                                        <a class="edit-mix" href="javascript:void(0)" data-id="#id#" data-name="#name#" data-introduce="#introduce#" > 编辑 </a>
                                        <a class="delete-mix" href="javascript:void(0)" data-id="#id#"> 删除 </a>
                                    </div>
                                </td>
                            </tr>
                        </tbody>
                    </table>
                    <a href="javascript:void (0);" id="load-btn" class="btn btn-base" style="margin: 0 auto 50px auto;width: 100%;border:none;display: none;">加载更多
                    </a>
                    <a href="#top" style="display:none;position: fixed;right: 30px;bottom: 80px;font-size: 30px;color:gray"><span
                            class="glyphicon glyphicon-arrow-up"></span></a>
                    <div id="share_wechat"></div>
                </div>
            </div>
            </c:if>
        </div>
    </div>
</div>


<%-- 图片裁剪模态框 --%>
<div class="modal fade" role="dialog" aria-labelledby="gridSystemModalLabel" id="edit-mix-modal">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <%--<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>--%>
                <h4 class="modal-title" id="gridSystemModalLabel">编辑节目信息</h4>
            </div>
            <div class="modal-body">
                <div class="container-fluid">
                    <form id="edit-mix-form" class="form-horizontal">
                        <input type="hidden" name="id" value="">
                        <div class="form-group">
                            <label class="col-sm-2 control-label">名称</label>
                            <div class="col-sm-10">
                                <input type="text" name="name" value="" class="form-control" />
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label">简介</label>
                            <div class="col-sm-10">
                                <textarea name="introduce" class="form-control" style="max-width: 100%;"></textarea>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
            <div class="modal-footer">
                <button id="cancel" type="button" class="btn btn-base btn-base-default" data-dismiss="modal">取消</button>
                <button id="mix-submit" type="button" class="btn btn-base">保存</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->

<%--<jsp:include page="../common/footer.jsp"></jsp:include>--%>
<script src="../../../static/jquery-3.1.0.min.js"></script>
<script src="../../../static/bootstrap/dist/js/bootstrap.min.js"></script>
<script src="../../../js/common/sidebar.js"></script>
<script src="//cdn.bootcss.com/bootbox.js/4.4.0/bootbox.min.js"></script>
<script src="../../../js/common/Util.js"></script>
<script src="../../../js/common/js.cookie.js"></script>
<script>
    //初始化
    var uid = '${user.id}';
    var albumsId = '${currentMixAlbums.id}';
    var checkStatus = '${user.userIdentityInfo.checkStatus}' == '' ? 0 : '${user.userIdentityInfo.checkStatus}';
    Sidebar.init("^/ugc/content/(\\S){1,}/albums/(\\S){1,}/programs$");
    //是否提示跳转身份验证框
    var identity = Cookies.get("identity_dialog");
    if(typeof identity == 'undefined') {
        Util.identityDialog(checkStatus, ${user.id});
        Cookies.set("identity_dialog", new Date().getTime(), { expires: 1});
    }
</script>
<script src="../../../js/content/content.js"></script>
<script>
    <c:if test="${mixAlbumsList == null || fn:length(mixAlbumsList) > 0}">
        Content.Programs.init();
    </c:if>
</script>
</body>
</html>
