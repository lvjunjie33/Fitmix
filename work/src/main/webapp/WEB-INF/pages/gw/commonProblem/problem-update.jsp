<%--
  Created by IntelliJ IDEA.
  User: edward
  Date: 2017/9/11
  Time: 11:11
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
        <h1>修改常见问题</h1>
        <div class="uk-form-row">
            <form action="/gw/CommonProblem/problem-update.json" method="post" class="uk-form" id="file-add-form" isform>
                <div class="uk-form-row">
                    <select class="uk-width-1-1 uk-form-large" name="problem_lan" id="problem_lan_update_id"  placeholder="语言">
                        <option value="zh">中文</option>
                        <option value="en">英文</option>
                    </select>
                </div>
                <div class="uk-form-row">
                    <input class="uk-width-1-1 uk-form-large" type="text" name="title" id="problem_title_update_id" placeholder="问题标题" notnull value="${commonProblem.problemTitle}">
                </div>
                <div class="uk-form-row">
                    <input type="hidden" name="des" id="des" value="${commonProblem.problemContent}">
                    <div id="editorBox"></div>
                </div>
                <div class="uk-form-row uk-text-right">
                    <input class="uk-width-1-1 uk-form-large" type="hidden" name="id" id="id_update_id" value="${commonProblem.id}">
                    <button class="uk-button uk-button-primary" id="role-add-submit" target="role-add-form">modify</button>
                    <button class="uk-button uk-modal-close">cancel</button>
                </div>
            </form>
        </div>
    </div>
</div>

<script>
    $("#problem_lan_update_id").change(function(){
        var opt=$("#problem_lan_update_id").val();
        var ids=$("#id_update_id").val();
        $.ajax({
            type: "GET",
            url: "/gw/CommonProblem/problem-update-ajax.json",
            data: {id:ids,lan:opt},
            dataType: "json",
            success: function(data){
                if(data.commonProblem!=undefined){
                    if("zh"==data.commonProblem.problem_lan){
                        $("#problem_title_update_id").val(data.commonProblem.problemTitle);
                        $("#editorBox").html(data.commonProblem.problemContent);
                    }
                    if("en"==data.commonProblem.problem_lan){
                        $("#problem_title_update_id").val(data.commonProblem.problemTitle_en);
                        $("#editorBox").html(data.commonProblem.problemContent_en);
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