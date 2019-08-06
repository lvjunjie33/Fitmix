<%--
  Created by IntelliJ IDEA.
  User: weird
  Date: 2016/5/22
  Time: 14:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/pages/common/nav.jsp" %>

<html>
<head>
    <title>StageRatio</title>
</head>
<body>
    <table class="uk-table uk-table-striped uk-text-nowrap uk-table-striped uk-table uk-table-hover uk-overflow-container">
        <thead>
            <tr>
                <th>类型</th>
                <th>操作</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="item" items="${page.result}">
                <tr>

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
                    <%--<td name="jog">
                        <c:choose>
                            <c:when test="${fn:length(item.jog) eq 0}">
                                -
                            </c:when>
                            <c:when test="${fn:length(item.jog) eq 1}">
                                ${item.jog[0]}
                            </c:when>
                            <c:when test="${fn:length(item.jog) eq 2}">
                                ${item.jog[0]} - ${item.jog[1]}
                            </c:when>
                        </c:choose>
                    </td>
                    <td name="comfortable">
                        <c:choose>
                            <c:when test="${fn:length(item.comfortable) eq 0}">
                                -
                            </c:when>
                            <c:when test="${fn:length(item.comfortable) eq 1}">
                                ${item.comfortable[0]}
                            </c:when>
                            <c:when test="${fn:length(item.comfortable) eq 2}">
                                ${item.comfortable[0]} - ${item.comfortable[1]}
                            </c:when>
                        </c:choose>
                    </td>
                    <td name="interval">
                        <c:choose>
                            <c:when test="${fn:length(item.interval) eq 0}">
                                -
                            </c:when>
                            <c:when test="${fn:length(item.interval) eq 1}">
                                ${item.interval[0]}
                            </c:when>
                            <c:when test="${fn:length(item.interval) eq 2}">
                                ${item.interval[0]} - ${item.interval[1]}
                            </c:when>
                        </c:choose>
                    </td>
                    <td name="buildUp">
                        <c:choose>
                            <c:when test="${fn:length(item.buildUp) eq 0}">
                                -
                            </c:when>
                            <c:when test="${fn:length(item.buildUp) eq 1}">
                                ${item.buildUp[0]}
                            </c:when>
                            <c:when test="${fn:length(item.buildUp) eq 2}">
                                ${item.buildUp[0]} - ${item.buildUp[1]}
                            </c:when>
                        </c:choose>
                    </td>
                    <td name="fast">
                        <c:choose>
                            <c:when test="${fn:length(item.fast) eq 0}">
                                -
                            </c:when>
                            <c:when test="${fn:length(item.fast) eq 1}">
                                ${item.fast[0]}
                            </c:when>
                            <c:when test="${fn:length(item.fast) eq 2}">
                                ${item.fast[0]} - ${item.fast[1]}
                            </c:when>
                        </c:choose>
                    </td>
                    <td name="pace">
                        <c:choose>
                            <c:when test="${fn:length(item.pace) eq 0}">
                                -
                            </c:when>
                            <c:when test="${fn:length(item.pace) eq 1}">
                                ${item.pace[0]}
                            </c:when>
                            <c:when test="${fn:length(item.pace) eq 2}">
                                ${item.pace[0]} - ${item.pace[1]}
                            </c:when>
                        </c:choose>
                    </td>--%>
                    <td>
                        <a href="/run-plan/stage-ratio-modify.htm?type=${item.type}">修改</a>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>

    <%-- 分页条--%>
    <lch:page page="${page}" href="/run-plan/rn-uplan-stage-ratio.htm"/>

</body>
</html>
