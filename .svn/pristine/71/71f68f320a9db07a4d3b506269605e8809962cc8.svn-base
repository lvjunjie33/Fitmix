<%--
  Created by IntelliJ IDEA.
  User: edward
  Date: 2017/6/28
  Time: 10:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div id="watch-add" class="uk-modal uk-open">
    <div class="uk-modal-dialog">
        <button type="button" class="uk-modal-close uk-close"></button>
        <h1>添加手表测试报告</h1>
        <div class="uk-form-row">
            <form action="/report/watch/add.json" method="post" class="uk-form" id="watch-add-form" isform>
                <div class="uk-form-row">
                    <input class="uk-width-1-1 uk-form-large" type="text" name="title" placeholder="标题" notnull>
                </div>
                <div class="uk-form-row">
                    <input type="text" class="uk-width-1-1 uk-form-large" name="testTime" data-uk-datepicker="{format:'YYYY-MM-DD'}" placeholder="测试时间" notnull>
                </div>
                <div class="uk-form-row">
                    <textarea class="uk-width-1-1 uk-form-large" name="des" placeholder="描述" notnull></textarea>
                </div>
                <div class="uk-form-row uk-text-right">
                    <button class="uk-button uk-button-primary" id="role-add-submit" target="role-add-form">add</button>
                    <button class="uk-button uk-modal-close">cancel</button>
                </div>
            </form>
        </div>
    </div>
</div>

