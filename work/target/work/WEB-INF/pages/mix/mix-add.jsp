<%--
  Created by IntelliJ IDEA.
  User: sin
  Date: 15/4/13
  Time: 下午4:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/pages/common/nav.jsp" %>

<style type="text/css">

    /*#mix-forms .uk-placeholder {padding: 10px;}*/
    .mix-scene{margin-right: 16px;}
    .mix-scene [type=radio],.mix-scene [type=checkbox]{margin-right: 4px;}
</style>

<div class="uk-container-center uk-body">
    <h2>Mix Add</h2>
    <form action="/mix/mix-add.json" method="post" class="uk-form" enctype="multipart/form-data" mix-add-form>
        <div class="<%--uk-container--%>">
            <%--<div class="uk-form-row">--%>
                <%--<div class="uk-grid">--%>
                    <%--<div class="uk-width-1-2" style="display: inline-block">--%>
                        <%--<label>歌曲：</label>--%>
                        <%--<div class="uk-form-file">--%>
                            <%--<a href="javascript:" style="cursor: pointer;">选择</a>--%>
                            <%--<input type="file" name="file">--%>
                        <%--</div>--%>
                    <%--</div>--%>
                    <%--<div class="uk-width-1-2" style="display: inline-block">--%>
                        <%--<label>图片：</label>--%>
                        <%--<div class="uk-form-file">--%>
                            <%--<a href="javascript:" style="cursor: pointer;">选择</a>--%>
                            <%--<input type="file" name="image">--%>
                        <%--</div>--%>
                    <%--</div>--%>
                <%--</div>--%>
            <%--</div>--%>
            <div class="uk-form-row">
                <label>类型：</label>
                <label class="uk-width-1-6"><input type="radio" name="type" value="1" checked="checked" />&nbsp;运动歌单</label>
                <label class="uk-width-1-6"><input type="radio" name="type" value="2"/>&nbsp;电台</label>
            </div>
            <div class="uk-form-row">
                <label>场景：</label>
                <span id="scene"><lch:dic-option type="2" label="<label class=\"mix-scene\"><input type=\"checkbox\" name=\"scene\" value=\"%s\">%s</label>"/></span>
            </div>

            <lch:mix-genre-parent split="" higValue="${mix.genre}"
                                  label="<label class=\"uk-width-1-6\"><input type=\"checkbox\" name=\"genre\" value=\"%s\">&nbsp;%s</label>"
                                  higLabel="<label class=\"uk-width-1-6\"><input type=\"checkbox\" name=\"genre\" value=\"%s\" checked>&nbsp;%s</label>"/>

            <div class="uk-form-row uk-grid uk-grid-collapse">
                <div>
                    <label>歌手：</label>
                </div>
                <div class="uk-width-9-10 uk-grid uk-grid-collapse">
                    <c:forEach items="${mixAuthorList}" var="mixAuthor">
                        <label class="uk-width-1-6"><input type="radio" name="maid" value="${mixAuthor.id}"/>&nbsp;${mixAuthor.name}</label>
                    </c:forEach>
                </div>
            </div>

            <div class="uk-form-row">
                <label>标识：</label>
                <label><input type="number" name="customIdentification" class="uk-form-large uk-width-9-10" notnull></label>
            </div>

            <div class="uk-form-row">
                <label>BPM：</label>
                <label><input type="text" name="bpm" class="uk-form-large uk-width-9-10" notnull></label>
            </div>

            <div class="uk-form-row">
                <label>时长：</label>
                <label><input type="text" name="notBuildLength" class="uk-form-large uk-width-9-10" isnumber></label>
                <input type="hidden" name="trackLength"/>
            </div>

            <div class="uk-form-row">
                <label>介绍：</label>
                <textarea name="introduce" class="uk-form-large uk-width-9-10" style="min-height: 100px;width: 80%;" notnull /></textarea>
                <label>语言：</label>
                <select name="mixDetails[0].introduce_lan">
                    <option value="zh" <c:if test="${page.filter.isDownload == 0}">selected</c:if>>简体中文</option>
                    <option value="en" <c:if test="${page.filter.isDownload == 1}">selected</c:if>>英文</option>
                </select>
            </div>

            <%--  隐藏区， 额外格式化信息  --%>
            <div class="uk-form-row">
                <div id="add-pzh">
                    <input type="hidden" name="mixMusicStr"/>
                </div>
            </div>

            <div class="uk-form-row">
                <button type="button" class="uk-button uk-form-large" style="width: 93%" add-pzh>添加配置</button>
            </div>

            <div class="uk-form-row">
                <button type="submit" class="uk-button uk-button-primary" target="role-add-form" style="width: 93%" mix-add-submit>add</button>
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
<div id="setting-mix-file" class="uk-modal uk-open">
    <div class="uk-modal-dialog">
        <button type="button" class="uk-modal-close uk-close"></button>
        <h3>设置 Mix 歌曲文件</h3>
        <a href="/mix/mix-modify.htm">setting</a>
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

    $("[name=type]").click(function() {
        if($("[name=type]:checked").val() == 2) {
            $("#scene").html('<lch:dic-option type="9" label="<label class=\"mix-scene\"><input type=\"checkbox\" name=\"scene\" value=\"%s\">%s</label>"/>');
        } else {
            $("#scene").html('<lch:dic-option type="2" label="<label class=\"mix-scene\"><input type=\"checkbox\" name=\"scene\" value=\"%s\">%s</label>"/>');
        }
    });

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


    $("[add-pzh]").click(function () {
        var htmlPz = '<div class="uk-form-row"><label>配置：</label> <input type="text" class="uk-form-large uk-width-4-10" name="mixMusicsName" placeholder="歌曲名称" notnull>' +
                ' <input type="text" class="uk-form-large uk-width-4-10" name="mixMusicsPosition" placeholder="时间"  isnumber/>' +
                ' <a href="javascript:" onclick="$(this).parent().remove();" style="color: #ff4d66;">删除</a></div>';
        var th = $(this);
        $("#add-pzh").append(htmlPz);
        console.info(window.validate());
    });

    $("[mix-add-form]").submit(function(){
        var form = $(this);
        validate.trigger(form);

        var errorEl = $(this).find("." + $.errorAlertClass + ":not(:hidden)");
        if (errorEl.size() > 0) {
            UIkit.notify("信息存在错误", {status:'danger', pos:'top-right'});
            return false;
        }
        // 处理歌曲时间， 分处理秒
        var notBuildLength = form.find("[name=notBuildLength]").val().trim();
        var notBuildLengthArray = notBuildLength.split(".");
        if (notBuildLengthArray.length == 2) {
            form.find("[name=trackLength]").val(notBuildLengthArray[0] * 60 + parseInt(notBuildLengthArray[1]));
        }
        else {
            form.find("[name=trackLength]").val(notBuildLengthArray[0] * 60);
        }
        // mixMusicStr 处理 mix 配置 子音乐时间， 分处理成秒

        var mixMusics = [];
        form.find("#add-pzh .uk-form-row").each(function(i, posi){
            var th = $(this);
            var mixMusicsName = th.find("[name=mixMusicsName]").val().trim();
            var mixMusicsPosition = th.find("[name=mixMusicsPosition]").val().trim();
            var positionArray = mixMusicsPosition.split(".");
            if (positionArray.length == 2) {
                mixMusics.push({"name" : $.trim(mixMusicsName), "position" : positionArray[0] * 60 + parseInt(positionArray[1])});
            }
            else {
                mixMusics.push({"name" : $.trim(mixMusicsName), "position" : positionArray[0] * 60});
            }
        });
        $("[name=mixMusicStr]").val(JSON.stringify(mixMusics));

        /// 认证bpmStr 格式 （99~1009）
        var bpmStr = $.trim(form.find("[name=bpm]").val());
        if (!(/^(\d+\-\d+|\d+)$/.test(bpmStr))) {
            UIkit.notify("bpm格式不正确 ( 22~23 or 22)", {status:'danger', pos:'top-right'});
            return false;
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
                        UIkit.notify("添加成功", {status:'success', pos:'top-right'});

                        var settingModel = $("#setting-mix-file");
                        var href = settingModel.find("a").attr("href") + "?id=" + d["mix"]["id"];
                        settingModel.find("a").attr("href", href);

                        var modal = UIkit.modal("#setting-mix-file");
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
