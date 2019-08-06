<%--
  Created by IntelliJ IDEA.
  User: sin
  Date: 15/3/30
  Time: 下午7:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/pages/common/head.jsp" %>
<style type="text/css">
    html,body{
        background: url("/imgs/back.png");
        height: 100%;
    }
    .my-container {
        height: 95%;
    }
    .left-nav {
        overflow: scroll;
    }
    .right-content {
        overflow: scroll;
    }
    .home-body{padding:40px 20px;}
    .home-body-head{text-align: right;margin-bottom: 8px;}

    .home-nav{margin-top: 60px;border-right: 1px solid #e2e2e2;}
    .home-nav a{color: #808080;}
    .home-nav a:hover{color: #000;background: #f6f6f6;}
    .home-nav a.nav-active {
        background: red;
        color: #fff;
        border-radius: 5px;
    }
</style>

<div class="uk-container-center uk-flex uk-width-9-10 my-container">
    <div class="tm-sidebar uk-width-medium-1-10 left-nav <%--data-uk-nav uk-navbar-nav uk-hidden-small--%>">
        <ul class="tm-nav uk-nav home-nav">
            <c:forEach var="menu" items="${authMenus}">
                <li class="uk-nav-header">${menu.name}</li>
                <c:forEach var="children" items="${menu.children}">
                    <li class="nav-item" data-id="${children.handling}"><a href="${children.handling}">${children.name}</a></li>
                </c:forEach>
            </c:forEach>
        </ul>
    </div>

    <div class="uk-width-medium-9-10 home-body right-content">
        <ul class="uk-breadcrumb home-body-head">
            <li><a href=""><%--系统管理--%>&nbsp;</a></li>
            <%--<li class="uk-active"><span>角色管理</span></li>--%>
        </ul>

        <script>
            $(function() {
                // 依靠data-id去跟url对比
                var pathname = location.pathname;
                var search = location.search;
                console.log(pathname);
                $('.nav-item').each(function(item,index) {
                    var id = $(this).data('id');
                    if(id == pathname || id == (pathname + search)) {
                        var offsetTop = $(this).offset().top;
                        $(this).find('a').addClass('nav-active');
                        document.querySelector('.tm-sidebar').scrollTo(0,offsetTop - 60);
                        return false;
                    }
                })
            })
        </script>

<%--</div>--%>
<%--</div>--%>
<%--  此处 2 div 在 bottom 中添加  --%>