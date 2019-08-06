<%--
  Created by IntelliJ IDEA.
  User: weird
  Date: 2016/6/1
  Time: 14:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/pages/common/nav.jsp" %>

<html>
<head>
    <title>runPlanClassify</title>
</head>
<body>
<div  class="uk-container-center uk-body">
    <div class="uk-form-row">
        <a href="#classify-add" class="uk-button" data-uk-modal>Add</a>
    </div>
    <div id="classify_content">
        <table class="uk-table uk-table-striped uk-text-nowrap uk-table-striped uk-table uk-table-hover uk-overflow-container">
            <thead>
                <tr>
                    <th>编号</th>
                    <th>类型</th>
                    <th>能力</th>
                    <th>类别</th>
                    <th>范围</th>
                    <th>操作</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="item" items="${page.result}">
                    <tr>
                        <td name="id">${item.id}</td>
                        <td>
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
                        <td>
                            <c:choose>
                                <c:when test="${item.ability eq 0}">
                                    少于20分钟
                                </c:when>
                                <c:when test="${item.ability eq 1}">
                                    20-30分钟
                                </c:when>
                                <c:when test="${item.ability eq 2}">
                                    30-60分钟
                                </c:when>
                                <c:when test="${item.ability eq 3}">
                                    5km
                                </c:when>
                                <c:when test="${item.ability eq 4}">
                                    10km
                                </c:when>
                                <c:when test="${item.ability eq 5}">
                                    半程马拉松
                                </c:when>
                                <c:when test="${item.ability eq 6}">
                                    马拉松
                                </c:when>
                            </c:choose>
                        </td>
                        <td name="classify">${item.classify}</td>
                        <td name="speed">${item.speed_min}-${item.speed_max}</td>
                        <td>
                            <div class="uk-form-row" id="modify">
                                <a href="javascript:" classify-modify>修改</a>
                                <input type="hidden" name="type" value="${item.type}">
                                <input type="hidden" name="aid" value="${item.ability}">
                            </div>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
</div>
<!-- 分页 -->
<lch:page page="${page}" href="/run-plan/run-plan-classify.htm"/>
<!-- add -->
<jsp:include page="classify-add.jsp"/>

<!-- modify -->
<jsp:include page="classify-modify.jsp"/>

</body>
</html>