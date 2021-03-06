<%--
  Created by IntelliJ IDEA.
  User: edward
  Date: 2017/9/11
  Time: 12:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/pages/common/nav.jsp" %>
<style>
    #editorBox {
        height: 500px;
    }
</style>
<div>
    <div>
        <%--<button type="button" class="uk-modal-close uk-close"></button>--%>
        <h1>手表升级包修改</h1>
        <%--<h2>Some text above the scrollable box</h2>--%>
        <div class="uk-form-row">
            <form action="/watch/modify.json" method="post" class="uk-form" id="activity-add-form" isform>
                <div class="uk-form-row">
                    <input class="uk-width-1-1 uk-form-large" type="file" name="uploadFile" title = "upload file">
                </div>
                <div class="uk-form-row">
                    <select class="uk-width-1-1 uk-form-large" name="fileType" placeholder="类型" data-value="${file.fileType}">
                        <option value="21">手表升级包</option>
                        <option value="22">阿波罗升级包</option>
                    </select>
                </div>
                <div class="uk-form-row">
                    <input class="uk-width-1-1 uk-form-large" type="text" name="title" placeholder="文件标题" notnull value="${file.title}">
                </div>
                <c:choose>
                    <c:when test="${!empty file.other}">
                        <div class="uk-form-row">
                            <input class="uk-width-1-1 uk-form-large" type="text" name="other[version]" placeholder="版本号" notnull value="${file.other.version}">
                        </div>
                        <div class="uk-form-row">
                            <input class="uk-width-1-1 uk-form-large" type="text" name="other[serial]" placeholder="序列号" notnull value="${file.other.serial}">
                        </div>
                        <div class="uk-form-row">
                            <input class="uk-width-1-1 uk-form-large" type="text" name="other[number]" placeholder="外部版本号" notnull value="${file.other.number}">
                        </div>
                    </c:when>
                    <c:otherwise>
                        <div class="uk-form-row">
                            <input class="uk-width-1-1 uk-form-large" type="text" name="other[version]" placeholder="版本号" notnull value="">
                        </div>
                        <div class="uk-form-row">
                            <input class="uk-width-1-1 uk-form-large" type="text" name="other[serial]" placeholder="序列号" notnull value="">
                        </div>
                        <div class="uk-form-row">
                            <input class="uk-width-1-1 uk-form-large" type="text" name="other[number]" placeholder="外部版本号" notnull value="">
                        </div>
                    </c:otherwise>
                </c:choose>
                <div class="uk-form-row">
                    <select class="uk-width-1-1 uk-form-large" name="des_lan" id="des_lan_update_id" placeholder="描述语言">
                        <option value="zh">中文描述</option>
                        <option value="en">英文描述</option>
                    </select>
                </div>
                <div class="uk-form-row">
                    <%--<textarea class="uk-width-1-1 uk-form-large" name="des" placeholder="文件描述" notnull>${file.des}</textarea>--%>
                        <input type="hidden" name="des" id="des" value="${file.des}">
                        <div id="editorBox"></div>
                </div>
                <div class="uk-form-row">
                    <select class="uk-width-1-1 uk-form-large" name="status" placeholder="状态" data-value="${file.status}">
                        <option value="1">正常</option>
                        <option value="0">无效</option>
                    </select>
                </div>
                <div class="uk-form-row uk-text-right">
                    <input class="uk-width-1-1 uk-form-large" type="hidden" name="id" id="id_update_id" value="${file.id}">
                    <button class="uk-button uk-button-primary" id="role-add-submit" target="role-add-form">modify</button>
                    <button class="uk-button uk-modal-close">cancel</button>
                </div>
            </form>
        </div>
    </div>
</div>
<script>
    $("#des_lan_update_id").change(function(){
        var opt=$("#des_lan_update_id").val();
        var ids=$("#id_update_id").val();
        $.ajax({
            type: "GET",
            url: "/watch/modify-ajax.json",
            data: {id:ids,lan:opt},
            dataType: "json",
            success: function(data){
                if(data.file!=undefined){
                    if("zh"==data.file.des_lan){
                        $("#editorBox").html(data.file.des);
                    }
                    if("en"==data.file.des_lan_en){
                        $("#editorBox").html(data.file.des_en);
                    }

                }else{
                        $("#editorBox").text("");
                }

            }
        });
    });
    // 设置  type status releaseStatus 选项
    $(function(){
        $("[name=fileType]").val($("[name=fileType]").attr("data-value"));
        $("[name=status]").val($("[name=status]").attr("data-value"));
        var editor = new wangEditor('editorBox')
        editor.create()
        editor.$txt.html($('#des').val())
        editor.onchange = function () {
            // html 即变化之后的内容
            console.log(editor.$txt.html())
            $('#des').val(editor.$txt.html())
        }
    });
</script>
