<%--
  Created by IntelliJ IDEA.
  User: weird
  Date: 2016/5/20
  Time: 15:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/pages/common/nav.jsp" %>

<html>
<head>
    <title>RunPlanSpeed</title>
</head>
<body>
    <table class="uk-table uk-table-striped uk-text-nowrap uk-table-striped uk-table uk-table-hover uk-overflow-container">
        <thead>
            <tr>
                <th name="id">编号</th>
                <th name="type">类型</th>
                <th name="classify">类别</th>
                <th name="ages">年龄段</th>
                <th name="gender">性别</th>
                <th name="speed">所需时间</th>
                <th>操作</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="item" items="${page.result}">
                <tr>
                    <td name="id">${item.id}</td>
                    <td name="type">
                        <c:choose>
                            <c:when test="${item.type eq 0}">
                                5km
                            </c:when>
                            <c:when test="${item.type eq 1}">
                                10km
                            </c:when>
                            <c:when test="${item.type eq 2}">
                                半程马拉松
                            </c:when>
                            <c:when test="${item.type eq 3}">
                                马拉松
                            </c:when>
                        </c:choose>
                    </td>
                    <td name="classify">
                        <c:choose>
                            <c:when test="${item.classify eq 0}">
                                少于20分钟
                            </c:when>
                            <c:when test="${item.classify eq 1}">
                               20-30分钟
                            </c:when>
                            <c:when test="${item.classify eq 2}">
                                30-60分钟
                            </c:when>
                        </c:choose>
                    </td>
                    <td name="ages">${item.ages}</td>
                    <td name="gender">
                        <c:choose>
                            <c:when test="${item.gender eq 1}">
                                男
                            </c:when>
                            <c:when test="${item.gender eq 2}">
                                女
                            </c:when>
                        </c:choose>
                    </td>
                    <td name="speed">${item.speed}</td>
                    <td>
                        <a href="/run-plan/speed-modify.htm?id=${item.id}">修改</a>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>

    <%-- 分页条--%>
    <lch:page page="${page}" href="/run-plan/run-plan-speed.htm"/>

</body>
</html>
