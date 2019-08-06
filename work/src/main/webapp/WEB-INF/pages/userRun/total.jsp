<%--
  Created by IntelliJ IDEA.
  User: edward
  Date: 2016/9/21
  Time: 12:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<style>
    .font-size-16 {
        font-size: 16px;
        text-align: left;
    }
</style>

<div id="activity-add" class="uk-modal uk-open">
    <div class="uk-modal-dialog" style="width: 300px">
        <button type="button" class="uk-modal-close uk-close"></button>
        <h1>运动数据总计</h1>
        <div class="uk-form-row font-size-16">
            总里程:<span id = "distance-id" style="margin-left: 50px;"></span>&nbsp;&nbsp;(米)
        </div>
        <div class="uk-form-row font-size-16">
            总步数:<span id = "step-id" style="margin-left: 50px;"></span>&nbsp;&nbsp;(步)
        </div>
        <div class="uk-form-row font-size-16">
            总时间:<span id = "runTime-id" style="margin-left: 50px;"></span>(分钟)
        </div>
        <div class="uk-form-row font-size-16">
            总卡路里:<span id = "calorie-id" style="margin-left: 33px;"></span>&nbsp;&nbsp;(卡)
        </div>
        <div class="uk-form-row">
            <form action="/user-run/restore/total.json" method="post" class="uk-form" id="user-run-total-form" isform>
                <div class="uk-form-row uk-text-right">
                    <input type="hidden" name="uid" value="${page.filter.uid}" />
                    <button class="uk-button uk-button-primary" id="role-add-submit" target="role-add-form">修复总的运动数据</button>
                </div>
            </form>
        </div>
    </div>
</div>
