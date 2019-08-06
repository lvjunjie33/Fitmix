<%--
  Created by IntelliJ IDEA.
  User: weird
  Date: 2016/10/8
  Time: 10:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/pages/common/nav.jsp" %>
<html>
<head>
    <title>RunPlanExtraStage</title>
</head>
<body>
<div class="uk-container-center uk-body">
    <div class="uk-form-row">
        <a href="#extra-stage-add" class="uk-button" data-uk-modal>Add</a>
    </div>
    <div>
        <table class="uk-table uk-table-striped uk-text-nowrap uk-table-striped uk-table uk-table-hover uk-overflow-container">
            <thead>
            <tr>
                <th name="id">编号</th>
                <th name="type">类型</th>
                <th name="classify">类别</th>
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
                    <td name="classify">${item.classify}</td>

                    <td>
                        <a href="/run-plan/templet-modify-content.htm?id=${item.id}">修改</a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
<%-- 分页条--%>
<lch:page page="${page}" href="/run-plan/extra-stage-list.htm"/>
<!-- add-->
<jsp:include page="extra-stage-add.jsp"/>
</body>
</html>