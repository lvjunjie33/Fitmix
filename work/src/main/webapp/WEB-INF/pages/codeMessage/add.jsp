<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2015/11/23
  Time: 14:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div id="code-add" class="uk-modal uk-open">
    <div class="uk-modal-dialog">
        <button type="button" class="uk-modal-close uk-close"></button>
        <h1>CodeMessage Add</h1>
        <div class="uk-form-row">
            <form action="/code-message/add.json" method="post" class="uk-form" isform>
                <div class="uk-form-row">
                    <input class="uk-width-1-1 uk-form-large" type="number" name="code" placeholder="code" notnull>
                </div>
                <div class="uk-form-row">
                    <input class="uk-width-1-1 uk-form-large" type="text" name="msgChina" placeholder="msgChina" notnull>
                </div>
                <div class="uk-form-row">
                    <input class="uk-width-1-1 uk-form-large" type="text" name="msgEnglish" placeholder="msgEnglish" notnull>
                </div>
                <div class="uk-form-row">
                    <select name="sys" class="uk-width-1-1 uk-form-large">
                        <option value="app">app</option>
                    </select>
                </div>
                <div class="uk-form-row">
                    <textarea name="memo" class="uk-width-1-1 uk-form-large" placeholder="memo"></textarea>
                </div>
                <div class="uk-form-row uk-text-right">
                    <button class="uk-button uk-button-primary" id="role-add-submit" target="role-add-form">add</button>
                    <button class="uk-button uk-modal-close">cancel</button>
                </div>
            </form>
        </div>
    </div>
</div>
