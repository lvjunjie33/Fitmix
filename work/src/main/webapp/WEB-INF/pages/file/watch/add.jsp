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
        height: 500px;
    }
    .uk-modal-dialog {
        width: 80%;
    }
</style>

<div id="file-add" class="uk-modal uk-open">
    <div class="uk-modal-dialog">
        <button type="button" class="uk-modal-close uk-close"></button>
        <h1>添加手表升级包</h1>
        <div class="uk-form-row">
            <form action="/watch/add.json" method="post" class="uk-form" id="file-add-form" isform>
                <div class="uk-form-row">
                    <input class="uk-width-1-1 uk-form-large" type="file" name="uploadFile" notnull title = "upload file">
                </div>
                <div class="uk-form-row">
                    <select class="uk-width-1-1 uk-form-large" name="fileType" placeholder="类型">
                        <option value="21">手表升级包</option>
                        <option value="22">阿波罗升级包</option>
                        <option value="23">手表心率升级包</option>

                    </select>
                </div>
                <div style="font-size: 20px;text-align: left;font-size: 20px;text-align: left;border: 1px solid #ccc;padding: 10px;margin: 12px 0;border-radius: 5px;">
                    <span>是否强制升级：</span>
                    <input type="radio" name="isForce" value="0" id="isNo" checked><label for="isNo">否</label>
                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    <input type="radio" name="isForce" value="1" id="isYes"><label for="isYes">是</label>
                </div>
                <div class="uk-form-row">
                    <input class="uk-width-1-1 uk-form-large" type="text" name="title" placeholder="文件标题" notnull>
                </div>
                <div class="uk-form-row">
                    <input class="uk-width-1-1 uk-form-large" type="text" name="other[version]" placeholder="版本号" notnull>
                </div>
                <div class="uk-form-row">
                    <input class="uk-width-1-1 uk-form-large" type="text" name="other[serial]" placeholder="序列号" notnull>
                </div>
                <div class="uk-form-row">
                    <input class="uk-width-1-1 uk-form-large" type="text" name="other[number]" placeholder="外部版本号" notnull>
                </div>
                <div class="uk-form-row">
                    <select class="uk-width-1-1 uk-form-large" name="des_lan" placeholder="描述语言">
                        <option value="zh">中文描述</option>
                        <option value="en">英文描述</option>
                    </select>
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