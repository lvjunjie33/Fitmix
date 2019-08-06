<%--
  Created by IntelliJ IDEA.
  User: edward
  Date: 2016/11/25
  Time: 16:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/pages/common/nav.jsp" %>

<link rel="stylesheet" href="/static/bootstrap/bootstrap.min.css">

<style>
    .uk-form .other-info{
        width:66px;
        margin-left: 30px;;
    }
    .auto-cla{
        min-width: 150px;
        display: inline-block;
    }

    .form-group>label {
        font-size: 12px;
        font-weight: 500;
    }
</style>
<div>
    <div>
        <h1>设置团体信息</h1>
        <form action="/activity/set-sign-up-mode.json" method="post" class="uk-form" isform>
            <div class = "group-member" >

                <div class="form-group">
                    <strong>团体、组织、群体、机构信息(例如:跑团名称)</strong>
                </div>
                <div class="form-group">
                    <label><input type="checkbox" name = "signUpMode" value="15" <c:if test="${signUpMode15 == 1}">checked</c:if>>&nbsp;&nbsp;团体名称</label>
                </div>
                <div class="form-group">
                    <label><input type="checkbox" name = "signUpMode" value="16" <c:if test="${signUpMode16 == 1}">checked</c:if>>&nbsp;&nbsp;团体口号</label>
                </div>

                <div class="uk-form-row uk-text-right">
                    <input type="hidden" name="activityId" value="${activityId}">
                    <button class="uk-button uk-button-primary" id="role-add-submit" target="role-add-form">submit</button>
                    <button class="uk-button uk-modal-close">cancel</button>
                </div>
            </div>
        </form>
    </div>
</div>
