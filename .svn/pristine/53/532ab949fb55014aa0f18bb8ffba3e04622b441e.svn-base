<%--
  Created by IntelliJ IDEA.
  User: sin
  Date: 2015/8/4 0004
  Time: 15:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/pages/common/nav.jsp" %>

<style type="text/css">

    .avatar{
        height: 100px;
        width: 100px;
        border-radius: 50px;;
    }

    .photos{
        width: 200px;
    }

    .photos-panel{
        position: relative;
        margin-top: 20px;
    }

    .photos-close{
        position: absolute;
        top: -10px;
        margin-top: 10px;
    }
</style>

<div class="uk-container-center uk-body">
    <h2>头像</h2>
    <div>
        <a class="uk-thumbnail" href="javascript:"><img src="${mixAuthor.avatar}" class="avatar"></a>
    </div>
    <br/>
    <form action="/mix-author/upload-avatar.json" method="post" class="uk-form" enctype="multipart/form-data" isform>
        <div class="uk-form-row">
            <input type="file" name="file">
        </div>
        <div class="uk-form-row">
            <input type="hidden" name="id" value="${mixAuthor.id}"/>
            <button class="uk-button uk-button-primary">上传</button>
        </div>
    </form>


    <h2>相册</h2>
    <div class="uk-panel uk-grid">
        <c:forEach items="${mixAuthor.photos}" var="photo">
            <div class="uk-width-2-10 photos-panel">
                <a class="uk-thumbnail" href="javascript:">
                    <img src="${photo}" class="photos" data-id="${mixAuthor.id}">
                    <a href="javascript:" class="uk-close photos-close"></a>
                </a>
            </div>
        </c:forEach>
    </div>
    <br/>
    <form action="/mix-author/upload-photos.json" method="post" class="uk-form" enctype="multipart/form-data" isform>
        <div class="uk-form-row">
            <input type="file" name="file" multiple>
        </div>
        <div class="uk-form-row">
            <input type="hidden" name="id" value="${mixAuthor.id}"/>
            <button class="uk-button uk-button-primary">上传</button>
        </div>
    </form>
</div>


<script type="text/javascript">

    ///
    /// 删除文件
    $(function(){
        $(".photos-close").click(function(){
            var th = $(this);
            var fileUrl = th.parent().find("img").attr("src");
            var id = th.parent().find("img").attr("data-id");
            var execStr = /\/\d+\/.*/.exec(fileUrl);
            lch.post("/mix-author/remove-photos.json", {"id" : id, "url" : execStr[0]}, function(result){
                th.parent().hide(2, function(){
                    th.parent().remove();
                });
            });
        });
    });
</script>


<%@ include file="/WEB-INF/pages/common/bottom.jsp" %>