<%--
  Created by IntelliJ IDEA.
  User: weird
  Date: 2016/8/4
  Time: 19:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/pages/common/head-taglib.jsp" %>

<nav class="navbar navbar-default navbar-static-top" role="banner">
    <div class="container">
        <div class="navbar-header">
            <button class="navbar-toggle collapsed" type="button" data-toggle="collapse" data-target="#bs-navbar"
                    aria-controls="bs-navbar" aria-expanded="false">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a href="http://www.ifitmix.com/fitmix" class="navbar-brand"><img class="navbar-log"
                                                                              src="/imgs/logo-fitmix.png"/></a>
        </div>
        <nav id="bs-navbar" class="collapse navbar-collapse">
            <ul class="nav navbar-nav">
                <li>
                    <a href="http://www.ifitmix.com/product">产品</a>
                </li>
                <li>
                    <a href="http://www.ifitmix.com/whyChooseUs">体验</a>
                </li>
                <li>
                    <a href="http://www.ifitmix.com">趣事</a>
                </li>
                <li>
                    <a href="http://www.ifitmix.com/fitmix">APP</a>
                </li>
                <li>
                    <a href="<c:choose><c:when test="${sessionScope.user == null||sessionScope.user_plan == null}">/run-plan/run-plan-presentation.htm</c:when><c:when test="${sessionScope.user != null&&sessionScope.user_plan != null}">run-plan/present-plan.htm</c:when></c:choose>">训练计划</a>
                </li>
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <c:choose>
                    <c:when test="${sessionScope.user == null}">
                        <li><a href="/web-user/login-page.htm">登录</a></li>
                        <li><a href="/web-user/register-page.htm">注册</a></li>
                    </c:when>
                    <c:when test="${sessionScope.user != null}">
                        <li><a href="/web-user/my-zone.htm">${sessionScope.user.name}</a></li>
                        <li><a id="layout">注销</a></li>
                    </c:when>
                </c:choose>
                <li><a href="https://nongke.tmall.com/?spm=a220o.1000855.1997427721.d4918089.ZNVKlt">商城</a></li>
                <li><a href="http://www.ifitmix.com/radio">电台</a></li>
            </ul>
        </nav>
    </div>
</nav>
<script type="text/javascript">
    $("#layout").click(function () {
        $.ajax({
            type: "POST",
            async: true,
            dateType: "json",
            url: "/web-user/layout.json",
            data: {
                _lan: _lan,
                _ch: _ch,
                _v: _v,
                _nw: _nw,
                _sdk: _sdk,
            },
            success: function (data) {
                if (data.code == 0) {
                    window.location.href = "/web-user/login-page.htm";
                }
            }
        });
    });
</script>