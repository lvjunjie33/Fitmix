<%@ taglib prefix="lc" uri="http://tag.lch.com/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%--
  Created by IntelliJ IDEA.
  User: weird
  Date: 2016/8/9
  Time: 15:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--<%@ include file="/WEB-INF/pages/common/head-taglib.jsp" %>--%>
<div class="background">
    <div class="filter"></div>
    <div id="register-bar" class="register-bar">
        <div class="register-content">
            <div class="reg-head">
                <div class="logo"></div>
                <h4>注册乐享动账号</h4>
            </div>
            <div class="reg-main">
                <div class="reg-type">
                    <div class="reg-type-phone checked" id="reg-type-phone" >手机注册</div>
                    <div class="reg-type-email" id="reg-type-email" >邮箱注册</div>
                </div>
                <div class="reg-phone" id="ph-reg">
                    <div>
                        <label>
                            <input id="phone-item" type="tel" placeholder="+86 请输入手机号码" />
                        </label>
                        <label id="phone-null">
                            请输入手机号码
                        </label>
                        <label id="phone-pattern-error">
                            手机号码格式不正确
                        </label>
                        <label class="wap-resend-label">
                            <input class="code-item" id="verify-code" type="text" placeholder="请输入验证码" />
                            <button id="get-code-btn">获取验证码</button>
                        </label>
                        <label id="code-null">
                            请输入验证码
                        </label>
                        <label class="psw-label">
                            <input id="psw-ph" type="password" placeholder="请输入6-20位密码"/>
                        </label>
                        <label class="reinput-psw-label">
                            <input id="c-psw-ph" type="password" placeholder="请再次输入密码"/>
                        </label>
                        <label id="psw-difference">
                            密码不一致
                        </label>
                        <label id="psw-null">
                            密码为空
                        </label>
                        <div class="reg-btn">
                            <button class="ph-em-reg-btn" id="ph-reg-btn">立即注册</button>
                        </div>
                    </div>
                </div>
                <div class="reg-email" id="em-reg" style="display: none;">
                    <label class="input-email-label">
                        <input id="email-item" type="text" placeholder="请输入邮箱地址" />
                    </label>
                    <label id="em-null">
                        请输入邮箱地址
                    </label>
                    <label id="email-error">
                        邮箱不合法
                    </label>
                    <label class="input-psw-label">
                        <input id="psw-em" type="password" placeholder="请输入6-20位密码"/>
                    </label>
                    <label class="reinput-psw-label">
                        <input id="c-psw-em" type="password" placeholder="请再次输入密码"/>
                    </label>
                    <label id="em-psw-difference">
                        密码不一致
                    </label>
                    <label id="em-psw-null">
                        密码为空
                    </label>
                    <div id="drag"><div class="success-tip"></div></div>
                    <div class="reg-btn">
                        <button class="ph-em-reg-btn check-drag" id="reg-email-button" disabled>立即注册</button>
                    </div>
                </div>
                <div class="reg-msg">
                    点击“立即注册”即将表示您同意并遵守乐享动<a>用户协议</a>和<a>隐私政策</a>
                </div>
            </div>
        </div>
    </div>
</div>
