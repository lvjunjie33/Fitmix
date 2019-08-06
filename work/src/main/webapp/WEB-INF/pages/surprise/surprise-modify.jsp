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
    <h2>彩蛋修改</h2>
    <form action="/surprise/surprise-modify.json" method="post" class="uk-form" enctype="multipart/form-data" surprise-modify-form>
        <div class="<%--uk-container--%>">
            <input type="hidden" name="id" value="${surprise.id}">
            <c:if test="${surprise.type == 1}">
                <div class="uk-form-row">
                    <div class="uk-grid">
                        <div class="uk-width-1-2" style="display: inline-block">
                            <label>图片：</label>
                            <div class="uk-form-file" id="surprise-image">
                                <a href="#image-modify-modal" image-modify data-uk-modal>修改图片</a>
                            </div>
                        </div>
                    </div>
                </div>
            </c:if>

            <div class="uk-form-row" style="margin-top: 25px;">
                <label>名称：</label>
                <label><input type="text" name="name" value="${surprise.name}" class="uk-form-large uk-width-9-10" notnull></label>
            </div>

            <%--<div class="uk-form-row" style="margin-top: 25px;">--%>
                <%--<label>类型：</label>--%>
                <%--<label>--%>
                    <%--<select name="type">--%>
                        <%--<option value="1" <c:if test="${surprise.type == 1}">selected="selected"</c:if> >静态图片</option>--%>
                        <%--<option value="2" <c:if test="${surprise.type == 2}">selected="selected"</c:if> >文字</option>--%>
                    <%--</select>--%>
                <%--</label>--%>
            <%--</div>--%>
            <c:if test="${surprise.type == 2}">
                <div class="uk-form-row" style="margin-top: 25px;">
                    <label>标题：</label>
                    <label><input type="text" name="title" value="${surprise.title}" class="uk-form-large uk-width-9-10" notnull></label>
                </div>

                <div class="uk-form-row">
                    <label>内容：</label>
                    <input type="text" name="content" value="${surprise.content}" class="uk-form-large uk-width-9-10"  notnull>
                </div>
            </c:if>

            <div class="uk-form-row">
                <label>开始时间：</label>
                <label>
                    <input type="text" name="startTimeStr" value="<lch:parse-date time="${surprise.startTime}" pattern="yyyy-MM-dd HH:mm:ss"/>" data-uk-datepicker="{format:'YYYY-MM-DD HH:mm:ss'}" placeholder="开始时间" notnull>
                    <input type="hidden" name="startTime" value="${surprise.startTime}">
                </label>
            </div>
            <div class="uk-form-row">
                <label>结束时间：</label>
                <label>
                    <input type="text" name="endTimeStr" value="<lch:parse-date time="${surprise.endTime}" pattern="yyyy-MM-dd HH:mm:ss"/>" data-uk-datepicker="{format:'YYYY-MM-DD HH:mm:ss'}" placeholder="结束时间" notnull>
                    <input type="hidden" name="endTime" value="${surprise.endTime}">
                </label>
            </div>


            <div class="uk-form-row">
                <button type="submit" class="uk-button uk-button-primary" target="surprise-modify-form" style="width: 93%" surprise-modify-submit>modify</button>
            </div>

        </div>
    </form>
    <br/>
</div>


<%--  image file  --%>
<div id="image-modify-modal" class="uk-modal uk-open">
    <div class="uk-modal-dialog">
        <button type="button" class="uk-modal-close uk-close"></button>
        <h1>Image Modify</h1>
        <div class="uk-form-row">
            <form action="/surprise/surprise-modify-image.json" method="post" class="uk-form" surprise-image-form>
                <div class="uk-form-row">
                    <label>图片：</label>
                    <div class="uk-form-file">
                        <a href="javascript:" style="cursor: pointer;">选择</a>
                        <input type="file" name="image">
                    </div>
                </div>
                <div class="uk-form-row uk-text-right">
                    <input type="hidden" name="id" value="${surprise.id}"/>
                    <button class="uk-button uk-button-primary" surprise-image-commit>modify</button>
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

    $("[surprise-modify-form]").submit(function(){
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


    $("[surprise-image-commit]").click(function(){
        var modal = UIkit.modal("#image-modify-modal");
        if ( modal.isActive()) {
            modal.hide();
        } else {
            modal.show();
        }
    });




});


$("[surprise-image-form]").submit(function(){
    var form = $(this);
    var file = form.find("[name=image]");
    form.ajaxSubmit({
        success: function (d) {
            var code = d['code'];
            if (code == 0) {
                $("#surprise-image").html("<div>" + file[0].files[0].name + "</div>");
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
