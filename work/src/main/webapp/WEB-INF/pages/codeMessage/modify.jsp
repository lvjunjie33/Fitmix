<%--
  Created by IntelliJ IDEA.
  User: edward
  Date: 2016/6/2
  Time: 14:27
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/pages/common/nav.jsp" %>
<div>
    <div>
        <%--<button type="button" class="uk-modal-close uk-close"></button>--%>
        <h1>活动修改</h1>
        <%--<h2>Some text above the scrollable box</h2>--%>
        <div class="uk-form-row">
            <form action="/code-message/modify.json" method="post" class="uk-form" isform>
                <div class="uk-form-row">
                    <input class="uk-width-1-1 uk-form-large" type="number" name="code" placeholder="code" notnull value="${codeMessage.code}">
                </div>
                <div class="uk-form-row">
                    <input class="uk-width-1-1 uk-form-large" type="text" name="msgChina" placeholder="msgChina" notnull value="${codeMessage.msgChina}">
                </div>
                <div class="uk-form-row">
                    <input class="uk-width-1-1 uk-form-large" type="text" name="msgEnglish" placeholder="msgEnglish" notnull value="${codeMessage.msgEnglish}">
                </div>
                <div class="uk-form-row">
                    <select name="sys" class="uk-width-1-1 uk-form-large">
                        <option value="app">app</option>
                    </select>
                </div>
                <div class="uk-form-row">
                    <textarea name="memo" class="uk-width-1-1 uk-form-large" placeholder="memo">${codeMessage.memo}</textarea>
                </div>
                <div class="uk-form-row uk-text-right">
                    <input type="hidden" value="${codeMessage.id}" name = "id" />
                    <button class="uk-button uk-button-primary" id="role-add-submit" target="role-add-form">modify</button>
                    <button class="uk-button uk-modal-close">cancel</button>
                </div>
            </form>
        </div>
    </div>
</div>
