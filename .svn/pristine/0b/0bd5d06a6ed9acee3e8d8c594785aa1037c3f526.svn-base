<%--
  Created by IntelliJ IDEA.
  User: edward
  Date: 2017/9/11
  Time: 18:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/pages/common/nav.jsp" %>
<div>
    <div>
        <%--<button type="button" class="uk-modal-close uk-close"></button>--%>
        <h1>app语音包修改</h1>
        <%--<h2>Some text above the scrollable box</h2>--%>
        <div class="uk-form-row">
            <form action="/voice/packets/modify.json" method="post" class="uk-form" id="activity-add-form" isform>
                <div class="uk-form-row">
                    <input class="uk-width-1-1 uk-form-large" type="file" name="uploadFile" title = "upload file">
                </div>
                <div class="uk-form-row">
                    <textarea class="uk-width-1-1 uk-form-large" name="des" placeholder="文件描述" notnull>${file.des}</textarea>
                </div>

                <div class="uk-form-row">
                    <input class="uk-width-1-1 uk-form-large" type="text" name="other[dirName]" placeholder="目录名" notnull value="${file.other.dirName}">
                </div>
                <div class="uk-form-row">
                    <input class="uk-width-1-1 uk-form-large" type="text" name="other[version]" placeholder="版本号" notnull value="${file.other.version}">
                </div>
                <div class="uk-form-row">
                    <input class="uk-width-1-1 uk-form-large" type="text" name="other[size]" placeholder="文件大小" notnull value="${file.other.size}">
                </div>
                <div class="uk-form-row">
                    <textarea class="uk-width-1-1 uk-form-large" name="other[targetApp]" placeholder="支持的app版本如:2.xx、3.xx" notnull>${file.other.targetApp}</textarea>
                </div>


                <div class="uk-form-row uk-text-right">
                    <input class="uk-width-1-1 uk-form-large" type="hidden" name="id" value="${file.id}">
                    <input class="uk-width-1-1 uk-form-large" type="hidden" name="other[voiceId]" value="${file.other.voiceId}">
                    <button class="uk-button uk-button-primary" id="role-add-submit" target="role-add-form">modify</button>
                    <button class="uk-button uk-modal-close">cancel</button>
                </div>
            </form>
        </div>
    </div>
</div>