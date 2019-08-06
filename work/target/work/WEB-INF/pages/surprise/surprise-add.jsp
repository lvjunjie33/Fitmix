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
    <h2>添加彩蛋信息</h2>
    <form action="/surprise/surprise-add.json" method="post" class="uk-form  uk-form-horizontal" enctype="multipart/form-data" surprise-add-form>
        <div class="<%--uk-container--%>">

            <div class="uk-form-row">
                <label class="uk-form-label">名称：</label>
                <div class="uk-form-controls">
                    <input type="text" name="name" class="uk-form-large uk-width-9-10" notnull>
                </div>
            </div>

            <div class="uk-form-row">
                <label class="uk-form-label">类型：</label>
                <div class="uk-form-controls">
                    <select name="type">
                        <option value="0">默认</option>
                        <option value="1">静态图片</option>
                        <option value="2">文字</option>
                        <option value="3">天气</option>
                    </select>
                </div>
            </div>

            <div class="word-type uk-form-row">
                <div class="uk-form-row">
                    <label class="uk-form-label">标题：</label>
                    <div class="uk-form-controls">
                        <input type="text" name="title" class="uk-form-large uk-width-9-10" notnull>
                    </div>
                </div>
                <div class="uk-form-row">
                    <label class="uk-form-label">内容：</label>
                    <div class="uk-form-controls">
                        <input type="text" name="content" class="uk-form-large uk-width-9-10" notnull>
                    </div>
                </div>
            </div>
            <div class="uk-form-row">
                <label class="uk-form-label">开始时间：</label>
                <div class="uk-form-controls">
                    <input type="text" name="startTimeStr" data-uk-datepicker="{format:'YYYY-MM-DD HH:mm:ss'}" placeholder="开始时间" notnull>
                    <input type="hidden" name="startTime">
                </div>
            </div>
            <div class="uk-form-row">
                <label class="uk-form-label">结束时间：</label>
                <div class="uk-form-controls">
                    <input type="text" name="endTimeStr" data-uk-datepicker="{format:'YYYY-MM-DD HH:mm:ss'}" placeholder="结束时间" notnull>
                    <input type="hidden" name="endTime">
                </div>
            </div>


            <div class="uk-form-row">
                <button type="submit" class="uk-button uk-button-primary" target="surprise-add-form" style="width: 93%" surprise-add-submit>add</button>
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
<div id="setting-surprise-image" class="uk-modal uk-open">
    <div class="uk-modal-dialog">
        <button type="button" class="uk-modal-close uk-close"></button>
        <h3>设置 静态 图片</h3>
        <a href="/surprise/surprise-modify.htm">setting</a>
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

    $(".word-type").css("display", "none");

    $("select[name=type]").change(function(e){
        var typeValue = $(this).val();
        if(typeValue == 1) {
            $(".word-type").css({
                display:"none"
            });
        } else if(typeValue == 2) {
            $(".word-type").css({
                display:""
            });
        }
    });


    $("[surprise-add-form]").submit(function(){
        var form = $(this);
        //处理开始时间和结束时间
        var startTimeStr = form.find("input[name=startTimeStr]").val();
        var endTimeStr = form.find("input[name=endTimeStr]").val();
        var startTime = new Date(startTimeStr.replace(/-/g,"/")).getTime();
        var endTime = new Date(endTimeStr.replace(/-/g,"/")).getTime();
        form.find("input[name=startTime]").val(startTime);
        form.find("input[name=endTime]").val(endTime);
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
                        //如果是静态图片 则上传图片
                        if(d["surprise"]["type"] == 1){
                            var settingModel = $("#setting-surprise-image");
                            var href = settingModel.find("a").attr("href") + "?id=" + d["surprise"]["id"];
                            settingModel.find("a").attr("href", href);

                            var modal = UIkit.modal("#setting-surprise-image");
                            if ( modal.isActive() ) {
                                modal.hide();
                            } else {
                                modal.show();
                            }
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
