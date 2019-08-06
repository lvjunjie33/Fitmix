<%--
  Created by IntelliJ IDEA.
  User: weird
  Date: 2016/10/9
  Time: 16:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/pages/common/nav.jsp" %>
<html>
<head>
    <title>RunPlanDescription-list</title>
</head>
<body>
<div class="uk-container-center uk-body">
    <div class="uk-form-row">
        <a class="uk-button" href="#des-add" data-uk-modal>Add</a>
    </div>
    <div id="classify_content">
        <table class="uk-table uk-table-striped uk-text-nowrap uk-table-striped uk-table uk-table-hover uk-overflow-container">
            <thead>
            <tr>
                <th>编号</th>
                <th>关键词</th>
                <th>内容</th>
                <th>操作</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="item" items="${page.result}">
                <tr>
                    <td name="id">${item.id}</td>
                    <td>${item.key}</td>
                    <td>${item.description}</td>
                    <td>
                        <div class="uk-form-row" id="modify">
                            <a href="javascript:" des-modify>修改</a>
                            <input type="hidden" name="key" value="${item.key}"/>
                            <input type="hidden" name="description" value="${item.description}"/>
                        </div>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
<!-- 分页 -->
<lch:page page="${page}" href="/run-plan/des-list.htm"/>

<!-- add -->
<jsp:include page="des-add.jsp"/>

<!-- modify -->
<jsp:include page="des-modify.jsp"/>

</body>
</html>
