<%--
  Created by IntelliJ IDEA.
  User: weird
  Date: 2016/8/4
  Time: 19:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<nav class="navbar navbar-default navbar-static-top" role="banner">
        <div class="navbar-header">
            <button class="navbar-toggle collapsed" type="button" data-toggle="collapse" data-target="#bs-navbar" aria-controls="bs-navbar" aria-expanded="false">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a href="#" class="navbar-brand"><img class="navbar-log" src="/imgs/logo-fitmix.png" /></a>
        </div>
        <nav id="bs-navbar" class="collapse navbar-collapse">
            <%--<ul class="nav navbar-nav">--%>
                <%--<li>--%>
                    <%--<a href="#">产品</a>--%>
                <%--</li>--%>
                <%--<li>--%>
                    <%--<a href="#">体验</a>--%>
                <%--</li>--%>
                <%--<li>--%>
                    <%--<a href="#">趣事</a>--%>
                <%--</li>--%>
                <%--<li>--%>
                    <%--<a href="#">APP</a>--%>
                <%--</li>--%>
            <%--</ul>--%>
            <ul class="nav navbar-nav navbar-right">
                <li><a href="/ugc/user/${user.id}/info"><span class="glyphicon glyphicon-user" style="margin-right: 5px;"></span>${user.name}</a></li>
                <li><a href="/ugc/admin/logout"><span class="glyphicon glyphicon-off" style="margin-right: 5px;"></span>退出</a></li>
            </ul>
        </nav>
</nav>