<%--
  Created by IntelliJ IDEA.
  User: edward
  Date: 2016/5/25
  Time: 11:08
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
        <h1>设置用户报名信息</h1>
        <form action="/activity/set-sign-up-mode.json" method="post" class="uk-form" isform>
            <div class = "group-member" >

                <div class="form-group">
                    <strong>基本信息</strong>
                </div>

                <div class="form-group">
                    <label><input type="checkbox" name = "signUpMode" value="1" <c:if test="${signUpMode1 == 1}">checked</c:if>>&nbsp;&nbsp;姓名</label>
                </div>
                <div class="form-group">
                    <label><input type="checkbox" name = "signUpMode" value="2" <c:if test="${signUpMode2 == 1}">checked</c:if>>&nbsp;&nbsp;性别</label>
                </div>
                <div class="form-group">
                    <label><input type="checkbox" name = "signUpMode" value="10" <c:if test="${signUpMode10 == 1}">checked</c:if>>&nbsp;&nbsp;血型</label>
                </div>
                <div class="form-group">
                    <label><input type="checkbox" name = "signUpMode" value="3" <c:if test="${signUpMode3 == 1}">checked</c:if>>&nbsp;&nbsp;衣服尺码</label>
                </div>
                <div class="form-group">
                    <label><input type="checkbox" name = "signUpMode" value="4" <c:if test="${signUpMode4 == 1}">checked</c:if>>&nbsp;&nbsp;备注</label>
                </div>
                <div class="form-group">
                    <strong>证件信息</strong>
                </div>
                <div class="form-group">
                    <label><input type="checkbox" name = "signUpMode" value="5" <c:if test="${signUpMode5 == 1}">checked</c:if>>&nbsp;&nbsp;证件类型</label>
                </div>
                <div class="form-group">
                    <label><input type="checkbox" name = "signUpMode" value="6" <c:if test="${signUpMode6 == 1}">checked</c:if>>&nbsp;&nbsp;证件号码</label>
                </div>

                <div class="form-group">
                    <strong>通知信息</strong>
                </div>
                <div class="form-group">
                    <label><input type="checkbox" name = "signUpMode" value="14" <c:if test="${signUpMode14 == 1}">checked</c:if>>&nbsp;&nbsp;联系人姓名</label>
                </div>
                <div class="form-group">
                    <label><input type="checkbox" name = "signUpMode" value="7" <c:if test="${signUpMode7 == 1}">checked</c:if>>&nbsp;&nbsp;邮箱</label>
                </div>
                <div class="form-group">
                    <label><input type="checkbox" name = "signUpMode" value="8" <c:if test="${signUpMode8 == 1}">checked</c:if>>&nbsp;&nbsp;紧急联系人姓名</label>
                </div>
                <div class="form-group">
                    <label><input type="checkbox" name = "signUpMode" value="9" <c:if test="${signUpMode9 == 1}">checked</c:if>>&nbsp;&nbsp;紧急联系人电话</label>
                </div>

                <div class="form-group">
                    <strong>其他信息</strong>
                </div>
                <div class="form-group">
                    <label><input type="checkbox" name = "signUpMode" value="11" <c:if test="${signUpMode11 == 1}">checked</c:if>>&nbsp;&nbsp;参数金额</label>
                </div>
                <div class="form-group">
                    <label><input type="checkbox" name = "signUpMode" value="12" <c:if test="${signUpMode12 == 1}">checked</c:if>>&nbsp;&nbsp;支付方式</label>
                </div>
                <div class="form-group">
                    <label><input type="checkbox" name = "signUpMode" value="13" <c:if test="${signUpMode13 == 1}">checked</c:if>>&nbsp;&nbsp;条款</label>
                </div>

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
