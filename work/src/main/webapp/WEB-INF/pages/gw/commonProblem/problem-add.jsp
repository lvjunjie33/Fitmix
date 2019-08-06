<%--
  Created by IntelliJ IDEA.
  User: edward
  Date: 2017/9/11
  Time: 11:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<style>
    .uk-pagination {
        font-size: inherit;
    }
    #editorBox {
        height: 400px;
    }
    .uk-modal-dialog {
        width: 60%;
    }
</style>

<div id="problem-add" class="uk-modal uk-open">
    <div class="uk-modal-dialog">
        <button type="button" class="uk-modal-close uk-close"></button>
        <h1>添加常见问题</h1>
        <div class="uk-form-row">
            <form action="/gw/CommonProblem/problem-add.json" method="post" class="uk-form" id="file-add-form" isform>
                <div class="uk-form-row">
                    <select class="uk-width-1-1 uk-form-large" name="problem_lan" placeholder="语言">
                        <option value="zh">中文</option>
                        <option value="en">英文</option>
                    </select>
                </div>
                <div class="uk-form-row">
                    <input class="uk-width-1-1 uk-form-large" type="text" name="title" placeholder="问题标题" notnull>
                </div>
                <div class="uk-form-row">
                    <input type="hidden" name="des" id="des">
                    <div id="editorBox"></div>
                </div>
                <div class="uk-form-row uk-text-right">
                    <button class="uk-button uk-button-primary" id="role-add-submit" target="role-add-form">add</button>
                    <button class="uk-button uk-modal-close">cancel</button>
                </div>
            </form>
        </div>
    </div>
</div>

<script>
    $(function () {
        var editor = new wangEditor('editorBox')
        editor.create()
        editor.onchange = function () {
            // html 即变化之后的内容
            console.log(editor.$txt.html())
            $('#des').val(editor.$txt.html())
        }
    })
</script>