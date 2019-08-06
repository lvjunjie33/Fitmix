<%--
  Created by IntelliJ IDEA.
  User: sin
  Date: 15/4/13
  Time: 下午4:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/pages/common/nav.jsp" %>

<div class="uk-container-center uk-body">
    <h2>Video Modify</h2>
    <form action="/video/video-modify.json" method="post" class="uk-form" enctype="multipart/form-data" video-modify-form>
        <div class="<%--uk-container--%>">
            <input type="hidden" name="id" value="${video.id}">
            <div class="uk-form-row">
                <div class="uk-grid">
                    <div class="uk-width-1-2" style="display: inline-block">
                        <label>视频：</label>
                        <div class="uk-form-file" id="video-file">
                            <a href="#video-modify-modal" video-modify data-uk-modal>修改视频</a>
                        </div>
                        <div class="uk-form-row" style="width: 95%">
                            <div class="progress"></div>
                            <div class="progress-bar">0%</div>
                        </div>
                    </div>
                    <div class="uk-width-1-2" style="display: inline-block">
                        <label>图片：</label>
                        <div class="uk-form-file" id="video-image">
                            <a href="#image-modify-modal" image-modify data-uk-modal>修改图片</a>
                        </div>
                    </div>
                </div>
            </div>

            <div class="uk-form-row"  style="margin-top: 25px;">
                <label>视频类型：</label>
                <label>
                    <select name="type" class="uk-form-large uk-width-9-10">
                        <option value="0" <c:if test="${video.type == 0}">selected="selected"</c:if> >普通视频</option>
                        <option value="1" <c:if test="${video.type == 1}">selected="selected"</c:if> >360全景视频</option>
                    </select>
                </label>
            </div>

            <div class="uk-form-row">
                <label>标题：</label>
                <label><input type="text" name="title" value="${video.title}" class="uk-form-large uk-width-9-10" notnull></label>
            </div>

            <div class="uk-form-row">
                <label>时长：</label>
                <label><input type="text" name="notBuildLength" value="<fmt:parseNumber value="${(video.trackLength - video.trackLength % 60) / 60}"/>.${video.trackLength % 60}" class="uk-form-large uk-width-9-10" isnumber></label>
                <input type="hidden" name="trackLength">
            </div>

            <div class="uk-form-row">
                <label>介绍：</label>
                <textarea name="content" class="uk-form-large uk-width-9-10" style="min-height: 100px;" notnull>${video.content}</textarea>
            </div>

            <div class="uk-form-row">
                <label>排序：</label>
                <label><input type="number" name="sort" value="${video.sort}" class="uk-form-large uk-width-9-10" notnull></label>
            </div>


            <div class="uk-form-row">
                <button type="submit" class="uk-button uk-button-primary" target="role-add-form" style="width: 93%" video-modify-submit>modify</button>
            </div>

        </div>
    </form>
    <br/>
</div>


<%--  image file  --%>
<div id="image-modify-modal" class="uk-modal uk-open">
    <div class="uk-modal-dialog">
        <button type="button" class="uk-modal-close uk-close"></button>
        <h1>Video Image Modify</h1>
        <div class="uk-form-row">
            <form action="/video/video-image-modify.json" method="post" class="uk-form" video-image-form>
                <div class="uk-form-row">
                    <label>图片：</label>
                    <div class="uk-form-file">
                        <a href="javascript:" style="cursor: pointer;">选择</a>
                        <input type="file" name="image">
                    </div>
                </div>
                <div class="uk-form-row uk-text-right">
                    <input type="hidden" name="vid" value="${video.id}"/>
                    <button class="uk-button uk-button-primary" video-image-commit>modify</button>
                    <button class="uk-button uk-modal-close">cancel</button>
                </div>
            </form>
        </div>
    </div>
</div>

<%--  video  --%>
<div id="video-modify-modal" class="uk-modal uk-open">
    <div class="uk-modal-dialog">
        <button type="button" class="uk-modal-close uk-close"></button>
        <h1>video File Modify</h1>
        <div class="uk-form-row">
            <form action="/video/video-file-modify.json" method="post" class="uk-form" video-file-form>
                <div class="uk-form-row">
                    <label>video：</label>
                    <div class="uk-form-file">
                        <a href="javascript:" style="cursor: pointer;">选择</a>
                        <input type="file" name="file">
                    </div>
                </div>
                <div class="uk-form-row uk-text-right">
                    <input type="hidden" name="vid" value="${video.id}"/>
                    <button class="uk-button uk-button-primary" video-file-commit>modify</button>
                    <button class="uk-button uk-modal-close">cancel</button>
                </div>
            </form>
        </div>
    </div>
</div>


<style type="text/css">
    .progress{
        background: #00a8e6;
        min-height: 1px;
        max-height: 1px;
        width: 0%;
        display: none;
    }

    .progress-bar{
        background: #ddd;
        min-height: 1px;
        max-height: 1px;
        color: #00a8e6;
        width: 100%;
        display: none;
    }

</style>
<script>

(function(){

    $(".uk-form-file [type=file]").change(function(e){
        var audioFile = $(this).val();
        var audioName = audioFile.substring(audioFile.lastIndexOf("\\") + 1);
        if (/^\s*$/.test(audioName)) {
            $(this).parent().find("a").text("请选择..");
        }
        else {
            $(this).parent().find("a").text(audioName);
        }
    });

    $("[video-modify-form]").submit(function(){
        var form = $(this);
//        validate.trigger(form);
        // 处理视频时间， 分处理秒
        var notBuildLength = form.find("[name=notBuildLength]").val().trim();
        var notBuildLengthArray = notBuildLength.split(".");
        if (notBuildLengthArray.length == 2) {
            form.find("[name=trackLength]").val(notBuildLengthArray[0] * 60 + parseInt(notBuildLengthArray[1]));
        }
        else {
            form.find("[name=trackLength]").val(notBuildLengthArray[0] * 60);
        }

        form.find("button,input[type=button]").attr("disabled", "true");
        form.ajaxSubmit({
            success: function (d) {
                var code = d['code'];
                if (code == 0) {
                    if (form.attr("not-reload") == "") {
                        UIkit.notify("success", {status:'success', pos:'top-right'});
                        form.find("button,input[type=button]").removeAttr("disabled");
                    }
                    else {
                        UIkit.notify("修改成功", {status:'success', pos:'top-right'});
                        form.find("button,input[type=button]").removeAttr("disabled");
                    }
                }
                else {
                    form.find("button,input[type=button]").removeAttr("disabled");
                    UIkit.notify(code + d["msg"], {status:'danger', pos:'top-right'});
                }
            }
        });
        return false;
    });

})($);

$(function(){
    $("[video-file-commit]").click(function(){
        $(".progress-bar,.progress").show(0);
        var modal = UIkit.modal("#file-modify-modal");
        if ( modal.isActive()) {
            modal.hide();
        } else {
            modal.show();
        }
    });

    $("[video-image-commit]").click(function(){
        var modal = UIkit.modal("#image-modify-modal");
        if ( modal.isActive()) {
            modal.hide();
        } else {
            modal.show();
        }
    });




});

$("[video-file-form]").submit(function(){
    var form = $(this);
    var vid = form.find("[name=vid]").val();
    var file = form.find("[name=file]");

    var size =  file[0].files[0].size;
    var start = 0;

    var fileSplitSize = 1024 * 1024;　　//文件分段大小1M
    var partCount = 0;
    var uploadForm = function(){
        var data = new FormData();　　//利用FormData对象模拟表单
        data.append("name", file[0].files[0].name);
        data.append("vid", vid);
        data.append("file", file[0].files[0].slice(start, start + fileSplitSize));　　//核心切割file文件，下面详细解释①
        data.append("part", ++partCount);
        data.append("size", size);
        data.append("partSize", fileSplitSize);
//        data.append("uploadSize",start + fileSplitSize);

        var rate = Math.round(fileSplitSize * (partCount + 1) / size * 100 > 100 ? 100 : fileSplitSize * (partCount + 1) / size * 100); // 进度 百分百

        var xhr = new XMLHttpRequest();　　//XMLHttpRequest 2.0对象
        xhr.open("post", "/video/video-file-modify.json?vid=" + vid, true);
        xhr.send(data);
        xhr.onreadystatechange = function(){
            if(xhr.readyState == 4){
                if(xhr.status == 200){
                    $(".progress").animate({width: rate + "%"}, 2000);
                    $(".progress-bar").html(rate + "%" +  "&nbsp;&nbsp;&nbsp;&nbsp;总大小:" + Math.round(size / 1024 / 1024) + "MB" + "&nbsp;&nbsp;&nbsp;&nbsp;已上传:" + Math.round(fileSplitSize * (partCount) / 1024 / 1024) + "MB" );
                    $("#video-file").html("<div>" + file[0].files[0].name + "</div>");

                    var result= xhr.responseText;
                    console.info(result);
                    if(start + fileSplitSize >= size){
                        UIkit.notify("上传成功", {status:'success', pos:'top-right'});
                        console.info("success...");
                    }
                    else {
                        start += fileSplitSize;
                        uploadForm();
                    }
                }
            }
        }
    }
    uploadForm();
    return false;
});

$("[video-image-form]").submit(function(){
    var form = $(this);
    var file = form.find("[name=image]");
    form.ajaxSubmit({
        success: function (d) {
            var code = d['code'];
            if (code == 0) {
                $("#video-image").html("<div>" + file[0].files[0].name + "</div>");
            }
            else {
                form.find("button,input[type=button]").removeAttr("disabled");
                UIkit.notify(code + d["msg"], {status:'danger', pos:'top-right'});
            }
        }
    });
    return false;
});


</script>


<%@ include file="/WEB-INF/pages/common/bottom.jsp" %>
