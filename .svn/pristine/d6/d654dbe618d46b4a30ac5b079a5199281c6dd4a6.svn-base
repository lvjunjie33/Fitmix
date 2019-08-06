<%--
  Created by IntelliJ IDEA.
  User: edward
  Date: 2018/1/29
  Time: 17:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/pages/common/nav.jsp" %>
<div>
    <div>
        <h1>文件修改</h1>
        <div class="uk-form-row">
            <form action="/file/modify.json" method="post" class="uk-form" id="activity-add-form" isform>
                <div class="uk-form-row">
                    <input class="uk-width-1-1 uk-form-large" type="file" name="uploadFile" title = "upload file">
                </div>
                <div class="uk-form-row">
                    <input class="uk-width-1-1 uk-form-large" type="text" name="title" placeholder="文件标题" notnull value="${file.title}">
                </div>
                <div class="uk-form-row">
                    <textarea class="uk-width-1-1 uk-form-large" name="des" placeholder="文件描述" notnull>${file.des}</textarea>
                </div>


                <div class="uk-form-row uk-text-right">
                    <input class="uk-width-1-1 uk-form-large" type="hidden" name="id" value="${file.id}">
                    <button class="uk-button uk-button-primary" id="role-add-submit" target="role-add-form">modify</button>
                    <button class="uk-button uk-modal-close">cancel</button>
                </div>
            </form>
        </div>
    </div>
</div>

