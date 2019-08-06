<%--
  Created by IntelliJ IDEA.
  User: edward
  Date: 2017/5/31
  Time: 18:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div id="task-add" class="uk-modal uk-open">
    <div class="uk-modal-dialog">
        <button type="button" class="uk-modal-close uk-close"></button>
        <h1>添加新板块</h1>
        <%--<h2>Some text above the scrollable box</h2>--%>
        <div class="uk-form-row">
            <form action="/category/add.json" method="post" class="uk-form" id="activity-add-form" isform>
                <div class="uk-form-row">
                    <input type="text" class="uk-width-1-1 uk-form-large" name="title" placeholder="板块标题" notnull>
                </div>
                <div class="uk-form-row">
                    <textarea class="uk-width-1-1 uk-form-large" name="des" placeholder="描述" notnull></textarea>
                </div>
                <div class="uk-form-row">
                    <select class="uk-width-1-1 uk-form-large" name="type" placeholder="活动类型" title="活动类型">
                        <option value="1">Mix</option>
                        <option value="2">电台</option>
                        <option value="3">赛事</option>
                        <option value="4">运动视界</option>
                        <option value="5">客服</option>
                        <option value="6">公告</option>
                        <option value="7">专业知识</option>
                        <option value="8" selected>自由板块</option>
                    </select>
                </div>
                <div class="uk-form-row">
                    <select class="uk-width-1-1 uk-form-large" name="isOpen" placeholder="是否开放" title="是否开放">
                        <option value="1" selected>开放板块</option>
                        <option value="2">非开放板块</option>
                    </select>
                </div>
                <div class="uk-form-row uk-text-right">
                    <button class="uk-button uk-button-primary" id="role-add-submit" target="role-add-form">add</button>
                    <button class="uk-button uk-modal-close">cancel</button>
                </div>
            </form>
        </div>
    </div>
</div>