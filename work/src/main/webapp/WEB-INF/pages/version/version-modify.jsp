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
    <h2>Version Modify</h2>
    <form action="/version/version-modify.json" method="post" class="uk-form" enctype="multipart/form-data" version-modify-form>
        <div class="<%--uk-container--%>">
            <div class="uk-form-row">
                <div class="uk-grid">
                    <div class="uk-width-1-2" style="display: inline-block">
                        <label>安卓包：</label>
                        <div class="uk-form-file" id="android-file">
                            <a href="#android-modify-modal" android-modify data-uk-modal>修改安卓包</a>
                        </div>
                        <div class="uk-form-row" style="width: 95%">
                            <div class="progress"></div>
                            <div class="progress-bar">0%</div>
                        </div>
                    </div>
                </div>
            </div>
            <input type="hidden" name="id" value="${version.id}">
            <div class="uk-form-row" style="margin-top: 20px;">
                <label>类型：</label>
                <label>
                    <select name="type" class="uk-form-large uk-width-9-10">
                        <option value="1">Android</option>
                    </select>
                    <%--<input type="text" name="channelId" value="${channelApp.channelId}" class="uk-form-large uk-width-9-10" notnull>--%>
                </label>
            </div>

            <div class="uk-form-row">
                <label>版本号：</label>
                <label><input type="text" name="versionCode" value="${version.versionCode}"  class="uk-form-large uk-width-9-10" notnull></label>
            </div>

            <div class="uk-form-row">
                <button type="submit" class="uk-button uk-button-primary" target="version-modify-form" style="width: 93%" version-modify-submit>modify</button>
            </div>
        </div>
    </form>
    <br/>
</div>


<%--  video  --%>
<div id="android-modify-modal" class="uk-modal uk-open">
    <div class="uk-modal-dialog">
        <button type="button" class="uk-modal-close uk-close"></button>
        <h1>android File Modify</h1>
        <div class="uk-form-row">
            <form action="/version/version-modify-file.json" method="post" class="uk-form" android-file-form>
                <div class="uk-form-row">
                    <label>APK：</label>
                    <div class="uk-form-file">
                        <a href="javascript:" style="cursor: pointer;">选择</a>
                        <input type="file" name="file">
                    </div>
                </div>
                <div class="uk-form-row uk-text-right">
                    <input type="hidden" name="id" value="${version.id}"/>
                    <button class="uk-button uk-button-primary" android-file-commit>modify</button>
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


    $(function(){
        $("[android-file-commit]").click(function(){
            $(".progress-bar,.progress").show(0);
            var modal = UIkit.modal("#android-modify-modal");
            if ( modal.isActive()) {
                modal.hide();
            } else {
                modal.show();
            }
        });
    });


    $("[android-file-form]").submit(function(){
        var form = $(this);
        var id = form.find("[name=id]").val();
        var file = form.find("[name=file]");

        var size =  file[0].files[0].size;
        var start = 0;

        var fileSplitSize = 1024 * 1024;　　//文件分段大小1M
        var partCount = 0;
        var uploadForm = function(){
            var data = new FormData();　　//利用FormData对象模拟表单
            data.append("name", file[0].files[0].name);
            data.append("id", id);
            data.append("file", file[0].files[0].slice(start, start + fileSplitSize));　　//核心切割file文件，下面详细解释①
            data.append("part", ++partCount);
            data.append("size", size);
            data.append("partSize", fileSplitSize);
//        data.append("uploadSize",start + fileSplitSize);

            var rate = Math.round(fileSplitSize * (partCount + 1) / size * 100 > 100 ? 100 : fileSplitSize * (partCount + 1) / size * 100); // 进度 百分百

            var xhr = new XMLHttpRequest();　　//XMLHttpRequest 2.0对象
            xhr.open("post", "/version/version-modify-file.json?id=" + id, true);
            xhr.send(data);
            xhr.onreadystatechange = function(){
                if(xhr.readyState == 4){
                    if(xhr.status == 200){
                        $(".progress").animate({width: rate + "%"}, 2000);
                        $(".progress-bar").html(rate + "%" +  "&nbsp;&nbsp;&nbsp;&nbsp;总大小:" + Math.round(size / 1024 / 1024) + "MB" + "&nbsp;&nbsp;&nbsp;&nbsp;已上传:" + Math.round(fileSplitSize * (partCount) / 1024 / 1024) + "MB" );
                        $("#android-file").html("<div>" + file[0].files[0].name + "</div>");

                        var result = xhr.responseText;
                        console.info(result);
                        if(start + fileSplitSize >= size){
                            UIkit.notify("修改成功", {status:'success', pos:'top-right'});
                            console.info("success...");
                        }
                        else {
                            result = JSON.parse(result);
                            if(result.code == 0) {
                                console.info("upload success..."+ start);
                                start += fileSplitSize;
                                console.info(start);
                                uploadForm();
                            } else {
                                UIkit.notify(result.code + result.msg, {status:'danger', pos:'top-right'});
                            }

                        }
                    }
                }
            }
        }
        uploadForm();
        return false;
    });


    $("[version-modify-form]").submit(function(){
        var form = $(this);

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

</script>


<%@ include file="/WEB-INF/pages/common/bottom.jsp" %>
