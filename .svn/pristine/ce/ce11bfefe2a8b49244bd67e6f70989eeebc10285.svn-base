<%--
  Created by IntelliJ IDEA.
  User: Sin
  Date: 2016/2/16
  Time: 16:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div id="activity-add" class="uk-modal uk-open">
    <div class="uk-modal-dialog">
        <button type="button" class="uk-modal-close uk-close"></button>
        <h1>活动添加</h1>
        <%--<h2>Some text above the scrollable box</h2>--%>
        <div class="uk-form-row">
            <form action="/activity/add.json" method="post" class="uk-form" id="activity-add-form" isform>
                <div class="uk-form-row">
                    <input class="uk-width-1-1 uk-form-large" type="file" name="themeImage" notnull>
                </div>
                <div class="uk-form-row">
                    <input class="uk-width-1-1 uk-form-large" type="number" name="activityMoney" placeholder="活动金额" notnull>
                </div>
                <div class="uk-form-row">
                    <input class="uk-width-1-1 uk-form-large" type="text" name="themeName" placeholder="活动主题" notnull>
                </div>
                <div class="uk-form-row">
                    <input type="text" class="uk-width-1-1 uk-form-large" name="signUpBeginTime" data-uk-datepicker="{format:'YYYY-MM-DD HH:mm'}" placeholder="报名开始时间" notnull>
                </div>
                <div class="uk-form-row">
                    <input type="text" class="uk-width-1-1 uk-form-large" name="signUpEndTime" data-uk-datepicker="{format:'YYYY-MM-DD HH:mm'}" placeholder="报名结束时间" notnull>
                </div>
                <div class="uk-form-row">
                    <input type="text" class="uk-width-1-1 uk-form-large" name="activityBeginTime" data-uk-datepicker="{format:'YYYY-MM-DD HH:mm'}" placeholder="活动开始时间" notnull>
                </div>
                <div class="uk-form-row">
                    <input type="text" class="uk-width-1-1 uk-form-large" name="activityEndTime" data-uk-datepicker="{format:'YYYY-MM-DD HH:mm'}" placeholder="活动结束时间" notnull>
                </div>
                <div class="uk-form-row">
                    <input class="uk-width-1-1 uk-form-large" type="number" name="activityMaxNumber" placeholder="活动最大人数" notnull>
                </div>
                <div class="uk-form-row">
                    <input class="uk-width-1-1 uk-form-large" type="number" name="activityFalseNumber" placeholder="虚假人数" value="0" notnull>
                </div>
                <div class="uk-form-row">
                    <select class="uk-width-1-1 uk-form-large" name="type" placeholder="活动类型" data-value="${activity.status}" title="活动类型">
                        <option value="0">普通赛事</option>
                        <option value="1">积分赛事</option>
                        <%--<option value="2">三方赛事</option> todo 现在不支持手动创建第三方赛事 --%>
                        <option value="3">标准赛事</option>
                    </select>
                </div>
                <div class="uk-form-row">
                    <textarea class="uk-width-1-1 uk-form-large" name="desc" placeholder="描述" notnull></textarea>
                </div>

                <div class="uk-form-row uk-text-right">
                    <button class="uk-button uk-button-primary" id="role-add-submit" target="role-add-form">add</button>
                    <button class="uk-button uk-modal-close">cancel</button>
                </div>
            </form>
        </div>
    </div>
</div>

