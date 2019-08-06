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
    <h2>Video Add</h2>
    <form action="/video/video-add.json" method="post" class="uk-form" enctype="multipart/form-data" video-add-form>
        <div class="<%--uk-container--%>">

            <div class="uk-form-row">
                <label>视频类型：</label>
                <label>
                    <select name="type" class="uk-form-large uk-width-9-10">
                        <option value="0">普通视频</option>
                        <option value="1">360全景视频</option>
                    </select>
                </label>
            </div>

            <div class="uk-form-row">
                <label>标题：</label>
                <label><input type="text" name="title" class="uk-form-large uk-width-9-10" placeholder="视频标题" notnull></label>
            </div>

            <div class="uk-form-row">
                <label>时长：</label>
                <label><input type="text" name="notBuildLength" class="uk-form-large uk-width-9-10" placeholder="视频时长,例如:3.14 表示3分14秒" isnumber></label>
                <input type="hidden" name="trackLength"/>
            </div>

            <div class="uk-form-row">
                <label>介绍：</label>
                <textarea name="content" class="uk-form-large uk-width-9-10" style="min-height: 100px;" notnull placeholder="视频简介" /></textarea>
            </div>

            <div class="uk-form-row">
                <label>排序：</label>
                <label><input type="number" name="sort" class="uk-form-large uk-width-9-10" placeholder="排序" notnull></label>
            </div>


            <div class="uk-form-row">
                <button type="submit" class="uk-button uk-button-primary" target="video-add-form" style="width: 93%" video-add-submit>add</button>
            </div>

            <div class="uk-form-row" style="width: 95%">
                <div class="progress">
                    <div class="progress-bar">0%</div>
                </div>
            </div>
        </div>
    </form>
    <br/>
</div>


<%--  setting file  --%>
<div id="setting-video-file" class="uk-modal uk-open">
    <div class="uk-modal-dialog">
        <button type="button" class="uk-modal-close uk-close"></button>
        <h3>设置 视频 文件</h3>
        <a href="/video/video-modify.htm">setting</a>
    </div>
</div>


<style type="text/css">
    .progress{
        background: #ddd;
        min-height: 1px;
        max-height: 1px;
        width: 100%;
        display: none;
    }

    .progress-bar{
        background: #00a8e6;
        min-height: 1px;
        max-height: 1px;
        color: #00a8e6;
        width: 0%;
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

    $("[video-add-form]").submit(function(){
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

        debugger
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
                        UIkit.notify("添加成功", {status:'success', pos:'top-right'});
                        var settingModel = $("#setting-video-file");
                        var href = settingModel.find("a").attr("href") + "?id=" + d["video"]["id"];
                        settingModel.find("a").attr("href", href);

                        var modal = UIkit.modal("#setting-video-file");
                        if ( modal.isActive() ) {
                            modal.hide();
                        } else {
                            modal.show();
                        }
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
//    $(".mix-scene")

    $("[name=bpm]").change(function(){
        var th = $(this);
        var value = $.trim(th.val());
        if (!/\s/.test(value)) {

            value = parseInt(value);
            console.info(typeof value);
            $(".mix-scene").find(":checkbox").removeAttr("checked");
            if (value >= 180) {
                $(".mix-scene").find("[value=1]").attr("checked", "true");
            }
            else if (value >= 140 && value < 180) {
                $(".mix-scene").find("[value=2]").attr("checked", "true");
            }
            else if (value >= 120 && value < 140) {
                $(".mix-scene").find("[value=3]").attr("checked", "true");
            }
            else if (value < 120) {
                $(".mix-scene").find("[value=4]").attr("checked", "true");
            }
        }
    });
});

</script>


<%@ include file="/WEB-INF/pages/common/bottom.jsp" %>
