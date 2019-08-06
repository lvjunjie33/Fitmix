<%--
  Created by IntelliJ IDEA.
  User: weird
  Date: 2016/5/18
  Time: 10:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/pages/common/nav.jsp" %>
<html>
<head>
    <title>runPlanList</title>
</head>
<body>
<div class="uk-container-center uk-body">
    <div class="uk-form-row">
        <a href="#module-add" class="uk-button" data-uk-modal>Add</a>
    </div>
    <div>
        <table class="uk-table uk-table-striped uk-text-nowrap uk-table-striped uk-table uk-table-hover uk-overflow-container">
            <thead>
            <tr>
                <th name="id">编号</th>
                <th name="type">类型</th>
                <th name="classify">类别</th>
                <th name="beginTime">开始时间</th>
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
                    <td name="beginTime">
                        <c:choose>
                            <c:when test="${item.beginTime eq 0}">
                                星期一
                            </c:when>
                            <c:when test="${item.beginTime eq 1}">
                                星期二
                            </c:when>
                            <c:when test="${item.beginTime eq 2}">
                                星期三
                            </c:when>
                            <c:when test="${item.beginTime eq 3}">
                                星期四
                            </c:when>
                            <c:when test="${item.beginTime eq 4}">
                                星期五
                            </c:when>
                            <c:when test="${item.beginTime eq 5}">
                                星期六
                            </c:when>
                            <c:when test="${item.beginTime eq 6}">
                                星期日
                            </c:when>
                        </c:choose>
                    </td>

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
<lch:page page="${page}" href="/run-plan/run-plan-templet.htm"/>
<!-- add-->
<jsp:include page="module-add.jsp"/>
</body>
</html>
