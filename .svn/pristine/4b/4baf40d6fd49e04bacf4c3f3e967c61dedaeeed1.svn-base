<%--
  Created by IntelliJ IDEA.
  User: edward
  Date: 2016/6/23
  Time: 10:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div id="task-add" class="uk-modal uk-open">
    <div class="uk-modal-dialog">
        <button type="button" class="uk-modal-close uk-close"></button>
        <h1>添加新任务</h1>
        <%--<h2>Some text above the scrollable box</h2>--%>
        <div class="uk-form-row">
            <form action="/scheduler/add.json" method="post" class="uk-form" id="activity-add-form" isform>
                <div class="uk-form-row">
                    <input type="text" class="uk-width-1-1 uk-form-large" name="topicName" placeholder="消息通道名" notnull>
                </div>
                <div class="uk-form-row">
                    <input type="text" class="uk-width-1-1 uk-form-large"  name="cron" placeholder="cron表达式" notnull>
                </div>
                <div class="uk-form-row">
                    <input type="text" class="uk-width-1-1 uk-form-large" name="beginTime" data-uk-datepicker="{format:'YYYY-MM-DD HH:mm:ss'}" placeholder="任务生效时间" notnull>
                </div>
                <div class="uk-form-row">
                    <input type="text" class="uk-width-1-1 uk-form-large" name="endTime" data-uk-datepicker="{format:'YYYY-MM-DD HH:mm:ss'}" placeholder="任务失效时间" notnull>
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