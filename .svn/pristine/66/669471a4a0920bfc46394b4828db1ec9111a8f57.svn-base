<%@ taglib prefix="lc" uri="http://tag.lch.com/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%--
  Created by IntelliJ IDEA.
  User: weird
  Date: 2016/8/5
  Time: 15:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--<%@ include file="/WEB-INF/pages/common/head-taglib.jsp" %>--%>
<div class="background">
    <div class="filter"></div>
    <div id="login-bar" class="login-bar">
        <div class="login-content">
            <div class="lghead">
                <div class="logo"></div>
                <h4>乐享动账号登录</h4>
            </div>
            <div class="login-area">
                <label class="login-input-label">
                    <input class="account-item" type="text" placeholder="邮箱/手机号码/乐享动账号" id="login-account"
                           name="login-account"/>
                </label>
                <label class="psw-input-label">
                    <input class="psw-item" type="password" placeholder="密码" id="psw" name="psw"/>
                </label>
                <label class="error-msg"></label>
                <div id="drag" style="<c:if test='${sessionScope.mult_login || sessionScope.mult_login == null || mult_login || mult_login == null }'>display:none;</c:if>">
                    <div class="success-tip"></div>
                </div>
                <div class="login-btn">
                    <button id="login-btn">立即登录</button>
                </div>
                <div class="link-to-other">
                    <a class="link-to-reg" href="user/register-page.htm">立即注册></a>
                    <a class="link-to-psw" href="user/forget-account-page.htm">忘记密码?</a>
                </div>
                <div class="other-login-way">
                    <fieldset class="other-way-title">其它登录</fieldset>
                    <div class="other-way-link">
                        <a class="other-way-wechat" href="javascript:void(0)"></a>
     <%--                   <a class="other-way-qq" id="login-qq" href="web-user/web-login-qq.htm"></a>--%>
                        <%--<a class="other-way-weibo" href="/web-user/web-login-wb.htm"></a>--%>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
