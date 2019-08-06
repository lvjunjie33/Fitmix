<%--
  Created by IntelliJ IDEA.
  User: weird
  Date: 2016/9/2
  Time: 17:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/pages/common/head-taglib.jsp" %>

<div class="bind-account">
    <div id="ph-bind-bar">
        <div class="bind-title">
            <h4>手机绑定</h4>
            <div class="bind-close" id="bind-close"></div>
        </div>
        <div class="bind-body">
            <div class="bind-form" id="bind-form">
                <h4>请绑定手机</h4>
                <div class="bind-main-content" >
                    <div  id="ph-bind-content" style="display: none">
                        <label>
                            <input class="phone-item" id="phone-item" type="tel" placeholder="+86 请输入手机号码"/>
                        </label>
                        <label id="phone-pattern-error">
                            手机号码格式不正确
                        </label>
                        <label id="phone-null">
                            请输入手机号码
                        </label>
                        <label class="wap-resend-label">
                            <input class="code-item" id="verify-code" type="text" placeholder="请输入验证码"/>
                            <button id="get-code-btn">获取验证码</button>
                        </label>
                        <label id="code-null">
                            请输入验证码
                        </label>
                    </div>
                    <div id="em-bind-content" >
                        <label>
                             <input class="em-item" id="em-item" type="text" placeholder="请输入邮箱">
                        </label>
                        <label id="em-null">
                            请输入邮箱地址
                        </label>
                        <label id="email-error">
                            邮箱不合法
                        </label>
                    </div>
                    <div class="bind-btn">
                        <button id="bind-btn">绑定</button>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <input type="hidden" id="bindingName">
    <input type="hidden" id="bindType">
</div>
<div class="ub-account" style="display: none">
    <div class="bind-title">
        <h4>手机解绑</h4>
        <div class="ub-close" id="ub-close"></div>
    </div>
    <div class="ub-body">
        <div class="ub-form" id="ub-form">
            <div class="ub-main-content" >
                <div  id="ph-ub-content">
                    <label class="wap-resend-label">
                        <input class="code-item" id="verify-code-ub" type="text" placeholder="请输入验证码"/>
                        <button id="ub-get-code-btn">获取验证码</button>
                    </label>
                    <label id="ub-code-null">
                        请输入验证码
                    </label>
                </div>
                <div id="em-ub-content" style="display: none;">
                    <label>
                        确定解绑邮箱:${sessionScope.user.email} ?
                    </label>
                </div>
                <div id="wx-ub-content" style="display: none;">
                    <label>
                        确定解绑微信: ${sessionScope.user.wxName} ?
                    </label>
                </div>
                <div id="wb-ub-content" style="display: none;">
                    <label>
                        确定解绑微博: ${sessionScope.user.wbName} ?
                    </label>
                </div>
                <div id="qq-ub-content" style="display: none;">
                    <label>
                        确定解绑QQ: ${sessionScope.user.qqName} ?
                    </label>
                </div>
                <div class="ub-btn">
                    <button id="ub-btn">解绑</button>
                </div>
            </div>
        </div>
    </div>
    <input type="hidden" id="ubType">
</div>