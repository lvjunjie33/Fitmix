<%--
  Created by IntelliJ IDEA.
  User: edward
  Date: 2016/12/5
  Time: 11:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div id="mix-recommend" class="uk-modal uk-open">
    <div class="uk-modal-dialog">
        <button type="button" class="uk-modal-close uk-close"></button>
        <h1>Mix音乐推荐</h1>
        <%--<h2>Some text above the scrollable box</h2>--%>
        <div class="uk-form-row">
            <form action="/mix/add-recommend.json" method="post" class="uk-form" id="mix-recommend-form" isform>
                <div class="uk-form-row">
                    <input type="text" class="uk-width-1-1 uk-form-large" name="bTime" data-uk-datepicker="{format:'YYYY-MM-DD HH:mm:ss'}" placeholder="推荐开始日期" notnull>
                </div>
                <div class="uk-form-row">
                    <input type="text" class="uk-width-1-1 uk-form-large" name="eTime" data-uk-datepicker="{format:'YYYY-MM-DD HH:mm:ss'}" placeholder="推荐结束日期" notnull>
                </div>
                <div class="uk-form-row uk-text-right">
                    <input type="hidden" name="mid" value="" />
                    <button class="uk-button uk-button-primary" id="mix-recommend-submit" target="role-add-form">add</button>
                    <button class="uk-button uk-modal-close">cancel</button>
                </div>
            </form>
        </div>
    </div>
</div>